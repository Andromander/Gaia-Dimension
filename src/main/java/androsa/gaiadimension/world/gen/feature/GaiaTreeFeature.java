package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.common.IPlantable;

import java.util.List;
import java.util.Random;
import java.util.Set;

//VanillaCopy class because I'm not extending TreeFeature
public abstract class GaiaTreeFeature<T extends GaiaTreeFeatureConfig> extends Feature<T> {

    public GaiaTreeFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, T config) {
        Set<BlockPos> logs = Sets.newHashSet();
        Set<BlockPos> leaves = Sets.newHashSet();
        MutableBoundingBox mutableboundingbox = MutableBoundingBox.getUnknownBox();
        boolean flag = this.generate(world, random, pos, logs, leaves, mutableboundingbox, config);
        if (mutableboundingbox.x0 <= mutableboundingbox.x1 && flag && !logs.isEmpty()) {
            VoxelShapePart voxelshapepart = this.getVoxelShapePart(world, mutableboundingbox, logs);
            Template.updateShapeAtEdge(world, 3, voxelshapepart, mutableboundingbox.x0, mutableboundingbox.y0, mutableboundingbox.z0);
            return true;
        } else {
            return false;
        }
    }

    //TreeFeature.updateLeaves copy, modified to remove decorations
    private VoxelShapePart getVoxelShapePart(IWorld world, MutableBoundingBox mbb, Set<BlockPos> logPosSet) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(mbb.getXSpan(), mbb.getYSpan(), mbb.getZSpan());

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(BlockPos logPos : Lists.newArrayList(logPosSet)) {
            if (mbb.isInside(logPos)) {
                voxelshapepart.setFull(logPos.getX() - mbb.x0, logPos.getY() - mbb.y0, logPos.getZ() - mbb.z0, true, true);
            }

            for(Direction direction : Direction.values()) {
                mutable.setWithOffset(logPos, direction);
                if (!logPosSet.contains(mutable)) {
                    BlockState blockstate = world.getBlockState(mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE)) {
                        list.get(0).add(mutable.immutable());
                        TreeFeature.setBlockKnownShape(world, mutable, blockstate.setValue(BlockStateProperties.DISTANCE, 1));
                        if (mbb.isInside(mutable)) {
                            voxelshapepart.setFull(mutable.getX() - mbb.x0, mutable.getY() - mbb.y0, mutable.getZ() - mbb.z0, true, true);
                        }
                    }
                }
            }
        }

        for(int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for(BlockPos blockpos2 : set) {
                if (mbb.isInside(blockpos2)) {
                    voxelshapepart.setFull(blockpos2.getX() - mbb.x0, blockpos2.getY() - mbb.y0, blockpos2.getZ() - mbb.z0, true, true);
                }

                for(Direction direction1 : Direction.values()) {
                    mutable.setWithOffset(blockpos2, direction1);
                    if (!set.contains(mutable) && !set1.contains(mutable)) {
                        BlockState blockstate1 = world.getBlockState(mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE)) {
                            int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, l + 1);
                                TreeFeature.setBlockKnownShape(world, mutable, blockstate2);
                                if (mbb.isInside(mutable)) {
                                    voxelshapepart.setFull(mutable.getX() - mbb.x0, mutable.getY() - mbb.y0, mutable.getZ() - mbb.z0, true, true);
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

    public abstract boolean generate(ISeedReader world, Random random, BlockPos pos, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox mbb, T config);

    //AbstractTrunkPlacer.func_236911_a_ copy - Use that one instead when extending that Abstract
    protected boolean setLogBlockState(IWorld world, Random random, BlockPos pos, Set<BlockPos> logPos, MutableBoundingBox mbb, GaiaTreeFeatureConfig config) {
        if (validTreePos(world, pos)) {
            this.setBlockState(world, pos, config.trunkProvider.getState(random, pos), mbb);
            logPos.add(pos.immutable());
            return true;
        } else {
            return false;
        }
    }

    protected boolean setLeavesBlockState(IWorld world, Random random, BlockPos pos, Set<BlockPos> leavesPos, MutableBoundingBox mbb, T config) {
        if (validTreePos(world, pos)) {
            this.setBlockState(world, pos, config.leavesProvider.getState(random, pos), mbb);
            leavesPos.add(pos.immutable());
            return true;
        } else {
            return false;
        }
    }

    protected final void setBlockState(IWorldWriter world, BlockPos pos, BlockState state, MutableBoundingBox mbb) {
        world.setBlock(pos, state, 19);
        mbb.expand(new MutableBoundingBox(pos, pos));
    }

    public static boolean validTreePos(IWorldGenerationBaseReader world, BlockPos pos) {
        return TreeFeature.validTreePos(world, pos);
    }

    public static boolean isAirOrLeaves(IWorldGenerationBaseReader world, BlockPos pos) {
        return TreeFeature.isAirOrLeaves(world, pos);
    }

    public static boolean isTallPlants(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> {
            Material mat = state.getMaterial();
            return mat == Material.REPLACEABLE_PLANT;
        });
    }

    public static boolean isSoil(IWorldGenerationBaseReader world, BlockPos pos, IPlantable sapling) {
        if (world instanceof IBlockReader) {
            return world.isStateAtPosition(pos, (state) -> state.canSustainPlant((IBlockReader)world, pos, Direction.DOWN, sapling));
        }
        return world.isStateAtPosition(pos, state -> isDirt(state.getBlock()));
    }
}
