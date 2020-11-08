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
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, T config) {
        Set<BlockPos> logs = Sets.newHashSet();
        Set<BlockPos> leaves = Sets.newHashSet();
        MutableBoundingBox mutableboundingbox = MutableBoundingBox.getNewBoundingBox();
        boolean flag = this.generate(world, random, pos, logs, leaves, mutableboundingbox, config);
        if (mutableboundingbox.minX <= mutableboundingbox.maxX && flag && !logs.isEmpty()) {
            VoxelShapePart voxelshapepart = this.getVoxelShapePart(world, mutableboundingbox, logs);
            Template.func_222857_a(world, 3, voxelshapepart, mutableboundingbox.minX, mutableboundingbox.minY, mutableboundingbox.minZ);
            return true;
        } else {
            return false;
        }
    }

    //TreeFeature.func_227214_a_ copy, modified to remove decorations
    private VoxelShapePart getVoxelShapePart(IWorld world, MutableBoundingBox mbb, Set<BlockPos> logPosSet) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(mbb.getXSize(), mbb.getYSize(), mbb.getZSize());

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(BlockPos logPos : Lists.newArrayList(logPosSet)) {
            if (mbb.isVecInside(logPos)) {
                voxelshapepart.setFilled(logPos.getX() - mbb.minX, logPos.getY() - mbb.minY, logPos.getZ() - mbb.minZ, true, true);
            }

            for(Direction direction : Direction.values()) {
                mutable.setAndMove(logPos, direction);
                if (!logPosSet.contains(mutable)) {
                    BlockState blockstate = world.getBlockState(mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE_1_7)) {
                        list.get(0).add(mutable.toImmutable());
                        TreeFeature.func_236408_b_(world, mutable, blockstate.with(BlockStateProperties.DISTANCE_1_7, 1));
                        if (mbb.isVecInside(mutable)) {
                            voxelshapepart.setFilled(mutable.getX() - mbb.minX, mutable.getY() - mbb.minY, mutable.getZ() - mbb.minZ, true, true);
                        }
                    }
                }
            }
        }

        for(int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for(BlockPos blockpos2 : set) {
                if (mbb.isVecInside(blockpos2)) {
                    voxelshapepart.setFilled(blockpos2.getX() - mbb.minX, blockpos2.getY() - mbb.minY, blockpos2.getZ() - mbb.minZ, true, true);
                }

                for(Direction direction1 : Direction.values()) {
                    mutable.setAndMove(blockpos2, direction1);
                    if (!set.contains(mutable) && !set1.contains(mutable)) {
                        BlockState blockstate1 = world.getBlockState(mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE_1_7)) {
                            int k = blockstate1.get(BlockStateProperties.DISTANCE_1_7);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.with(BlockStateProperties.DISTANCE_1_7, l + 1);
                                TreeFeature.func_236408_b_(world, mutable, blockstate2);
                                if (mbb.isVecInside(mutable)) {
                                    voxelshapepart.setFilled(mutable.getX() - mbb.minX, mutable.getY() - mbb.minY, mutable.getZ() - mbb.minZ, true, true);
                                }

                                set1.add(mutable.toImmutable());
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
        if (isReplaceableAt(world, pos)) {
            this.setBlockState(world, pos, config.trunkProvider.getBlockState(random, pos), mbb);
            logPos.add(pos.toImmutable());
            return true;
        } else {
            return false;
        }
    }

    protected boolean setLeavesBlockState(IWorld world, Random random, BlockPos pos, Set<BlockPos> leavesPos, MutableBoundingBox mbb, T config) {
        if (isReplaceableAt(world, pos)) {
            this.setBlockState(world, pos, config.leavesProvider.getBlockState(random, pos), mbb);
            leavesPos.add(pos.toImmutable());
            return true;
        } else {
            return false;
        }
    }

    protected final void setBlockState(IWorldWriter world, BlockPos pos, BlockState state, MutableBoundingBox mbb) {
        world.setBlockState(pos, state, 19);
        mbb.expandTo(new MutableBoundingBox(pos, pos));
    }

    public static boolean isReplaceableAt(IWorldGenerationBaseReader world, BlockPos pos) {
        return TreeFeature.isReplaceableAt(world, pos);
    }

    public static boolean isAirOrLeaves(IWorldGenerationBaseReader world, BlockPos pos) {
        return TreeFeature.isAirOrLeavesAt(world, pos);
    }

    public static boolean isTallPlants(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> {
            Material mat = state.getMaterial();
            return mat == Material.TALL_PLANTS;
        });
    }

    public static boolean isSoil(IWorldGenerationBaseReader world, BlockPos pos, IPlantable sapling) {
        if (world instanceof IBlockReader) {
            return world.hasBlockState(pos, (state) -> state.canSustainPlant((IBlockReader)world, pos, Direction.DOWN, sapling));
        }
        return world.hasBlockState(pos, state -> isDirt(state.getBlock()));
    }
}
