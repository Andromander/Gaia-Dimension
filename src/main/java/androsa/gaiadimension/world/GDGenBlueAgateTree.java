package androsa.gaiadimension.world;

import androsa.gaiadimension.block.GDGaiaLeaves;
import androsa.gaiadimension.block.GDGaiaLog;
import androsa.gaiadimension.block.enums.GaiaLeavesVariant;
import androsa.gaiadimension.block.enums.GaiaLogVariant;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class GDGenBlueAgateTree extends WorldGenTrees {

    public GDGenBlueAgateTree(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random rand, BlockPos pos) {
        int height = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = height - j;
        int l = 2 + rand.nextInt(2);
        boolean allClear = true;

        if (pos.getY() >= 1 && pos.getY() + height + 1 <= world.getHeight()) {
            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + height && allClear; ++i1) {
                int j1;

                if (i1 - pos.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1 && allClear; ++k1) {
                    for (int l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1 && allClear; ++l1) {
                        if (i1 >= 0 && i1 < world.getHeight()) {
                            IBlockState state = world.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1));

                            if (!state.getBlock().isAir(state, world, blockpos$mutableblockpos.setPos(k1, i1, l1)) && !state.getBlock().isLeaves(state, world, blockpos$mutableblockpos.setPos(k1, i1, l1))) {
                                allClear = false;
                            }
                        } else {
                            allClear = false;
                        }
                    }
                }
            }

            if (!allClear) {
                return false;
            } else {
                BlockPos down = pos.down();
                IBlockState state = world.getBlockState(down);

                if (state.getBlock().canSustainPlant(state, world, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.SAPLING) && pos.getY() < world.getHeight() - height - 1)
                {
                    state.getBlock().onPlantGrow(state, world, down, pos);
                    int i3 = rand.nextInt(2);
                    int j3 = 1;
                    int k3 = 0;

                    for (int l3 = 0; l3 <= k; ++l3) {
                        int j4 = pos.getY() + height - l3;

                        for (int i2 = pos.getX() - i3; i2 <= pos.getX() + i3; ++i2) {
                            int j2 = i2 - pos.getX();

                            for (int k2 = pos.getZ() - i3; k2 <= pos.getZ() + i3; ++k2) {
                                int l2 = k2 - pos.getZ();

                                if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                                    BlockPos blockpos = new BlockPos(i2, j4, k2);
                                    state = world.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
                                        this.setBlockAndNotifyAdequately(world, blockpos, GDBlocks.gaiaLeaves.getDefaultState().withProperty(GDGaiaLeaves.VARIANT, GaiaLeavesVariant.BLUE_AGATE));
                                    }
                                }
                            }
                        }

                        if (i3 >= j3) {
                            i3 = k3;
                            k3 = 1;
                            ++j3;

                            if (j3 > l) {
                                j3 = l;
                            }
                        } else {
                            ++i3;
                        }
                    }

                    int i4 = rand.nextInt(3);

                    for (int k4 = 0; k4 < height - i4; ++k4) {
                        BlockPos upN = pos.up(k4);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
                            this.setBlockAndNotifyAdequately(world, pos.up(k4), GDBlocks.gaiaLog.getDefaultState().withProperty(GDGaiaLog.VARIANT, GaiaLogVariant.BLUE_AGATE));
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}