package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import net.minecraftforge.common.IPlantable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

//VanillaCopy class because I'm not extending TreeFeature
//TODO: Comb through and see if we can use the vanilla tree system.
public abstract class GaiaTreeFeature<T extends GaiaTreeFeatureConfig> extends Feature<T> {

    public GaiaTreeFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel world, Random random, BlockPos pos, T config) {
        Set<BlockPos> logs = Sets.newHashSet();
        Set<BlockPos> leaves = Sets.newHashSet();
        BiConsumer<BlockPos, BlockState> logconsumer = (cpos, cstate) -> {
            logs.add(cpos.immutable());
            world.setBlock(cpos, cstate, 19);
        };
        BiConsumer<BlockPos, BlockState> leavesconsumer = (cpos, cstate) -> {
            leaves.add(cpos.immutable());
            world.setBlock(cpos, cstate, 19);
        };
        boolean flag = this.generate(world, random, pos, logconsumer, leavesconsumer, config);
        if (flag && (!logs.isEmpty() || !leaves.isEmpty())) {
            List<BlockPos> loglist = Lists.newArrayList(logs);
            List<BlockPos> leaveslist = Lists.newArrayList(leaves);
            loglist.sort(Comparator.comparingInt(Vec3i::getY));
            leaveslist.sort(Comparator.comparingInt(Vec3i::getY));
            
            return BoundingBox.encapsulatingPositions(Iterables.concat(logs, leaves)).map((bb) -> {
                DiscreteVoxelShape voxelshapepart = getVoxelShapePart(world, bb, logs);
                StructureTemplate.updateShapeAtEdge(world, 3, voxelshapepart, bb.minX(), bb.minY(), bb.minZ());
                return true;
            }).orElse(false);
        } else {
            return false;
        }
    }

    //TreeFeature.updateLeaves copy, modified to remove decorations
    private static DiscreteVoxelShape getVoxelShapePart(LevelAccessor world, BoundingBox mbb, Set<BlockPos> logPosSet) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        DiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(mbb.getXSpan(), mbb.getYSpan(), mbb.getZSpan());

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        Iterator<BlockPos> logiterator = Lists.newArrayList(logPosSet).iterator();
        BlockPos pos;

        while(logiterator.hasNext()) {
            pos = logiterator.next();
            if (mbb.isInside(pos)) {
                voxelshapepart.fill(pos.getX() - mbb.minX(), pos.getY() - mbb.minY(), pos.getZ() - mbb.minZ());
            }

            for(Direction direction : Direction.values()) {
                mutable.setWithOffset(pos, direction);
                if (!logPosSet.contains(mutable)) {
                    BlockState blockstate = world.getBlockState(mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE)) {
                        list.get(0).add(mutable.immutable());
                        setBlockKnownShape(world, mutable, blockstate.setValue(BlockStateProperties.DISTANCE, 1));
                        if (mbb.isInside(mutable)) {
                            voxelshapepart.fill(mutable.getX() - mbb.minX(), mutable.getY() - mbb.minY(), mutable.getZ() - mbb.minZ());
                        }
                    }
                }
            }
        }

        for(int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for (BlockPos position : set) {
                if (mbb.isInside(position)) {
                    voxelshapepart.fill(position.getX() - mbb.minX(), position.getY() - mbb.minY(), position.getZ() - mbb.minZ());
                }

                for (Direction direction1 : Direction.values()) {
                    mutable.setWithOffset(position, direction1);
                    if (!set.contains(mutable) && !set1.contains(mutable)) {
                        BlockState blockstate1 = world.getBlockState(mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE)) {
                            int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, l + 1);
                                setBlockKnownShape(world, mutable, blockstate2);
                                if (mbb.isInside(mutable)) {
                                    voxelshapepart.fill(mutable.getX() - mbb.minX(), mutable.getY() - mbb.minY(), mutable.getZ() - mbb.minZ());
                                }

                                set1.add(mutable.immutable());
                            }
                        }
                    }
                }
            }
        }

        return voxelshapepart;
    }

    public abstract boolean generate(WorldGenLevel world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> logPos, BiConsumer<BlockPos, BlockState> leavesPos, T config);

    //AbstractTrunkPlacer.func_236911_a_ copy - Use that one instead when extending that Abstract
    protected boolean setLog(LevelSimulatedReader world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> log, T config) {
        return setLog(world, log, random, pos, config, Function.identity());
    }

    protected boolean setLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> log, Random random, BlockPos pos, T config, Function<BlockState, BlockState> function) {
        if (validTreePos(level, pos)) {
            log.accept(pos, function.apply(config.trunkProvider.getState(random, pos)));
            return true;
        } else {
            return false;
        }
    }

    protected boolean setLeaves(LevelSimulatedReader world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> leaves, T config) {
        return setLeaves(world, leaves, random, pos, config, Function.identity());
    }

    protected boolean setLeaves(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> leaves, Random random, BlockPos pos, T config, Function<BlockState, BlockState> function) {
        if (validTreePos(level, pos)) {
            leaves.accept(pos, function.apply(config.leavesProvider.getState(random, pos)));
            return true;
        } else {
            return false;
        }
    }

    private static void setBlockKnownShape(LevelWriter level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, 19);
    }

    public static boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
        return TreeFeature.validTreePos(world, pos);
    }

    public static boolean isAirOrLeaves(LevelSimulatedReader world, BlockPos pos) {
        return TreeFeature.isAirOrLeaves(world, pos);
    }

    public static boolean isTallPlants(LevelSimulatedReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> {
            Material mat = state.getMaterial();
            return mat == Material.REPLACEABLE_PLANT;
        });
    }

    public static boolean isSoil(LevelSimulatedReader world, BlockPos pos, IPlantable sapling) {
        if (world instanceof BlockGetter) {
            return world.isStateAtPosition(pos, (state) -> state.canSustainPlant((BlockGetter)world, pos, Direction.DOWN, sapling));
        }
        return world.isStateAtPosition(pos, Feature::isDirt);
    }
}
