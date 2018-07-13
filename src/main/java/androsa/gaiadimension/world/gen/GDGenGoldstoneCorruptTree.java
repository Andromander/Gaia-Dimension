package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDSpecialLeaves;
import androsa.gaiadimension.block.GDSpecialLog;
import androsa.gaiadimension.block.enums.SpecialGaiaLeavesVariant;
import androsa.gaiadimension.block.enums.SpecialGaiaLogVariant;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import javax.annotation.Nonnull;
import java.util.Random;

public class GDGenGoldstoneCorruptTree extends WorldGenTrees {

    public GDGenGoldstoneCorruptTree(boolean flag) {
        super(flag);
    }

    public boolean generate(@Nonnull World world, Random rand, BlockPos pos) {
        int height = rand.nextInt(5) + 7;
        int j = height - rand.nextInt(2) - 3;
        int k = height - j;
        int l = 1 + rand.nextInt(k + 1);
        boolean allClear = true;

        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256) {
            Block blockID;

            for (int cy = pos.getY(); cy <= pos.getY() + 1 + height && allClear; ++cy) {
                int j1 = 1;

                if (cy - pos.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                for (int cx = pos.getX() - j1; cx <= pos.getX() + j1 && allClear; ++cx) {
                    for (int cz = pos.getZ() - j1; cz <= pos.getZ() + j1 && allClear; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            BlockPos cPos = new BlockPos(cx, cy, cz);

                            IBlockState block = world.getBlockState(cPos);
                            blockID = block.getBlock();

                            if (blockID != Blocks.AIR &&
                                    !blockID.isLeaves(block, world, cPos) &&
                                    blockID != Blocks.GRASS &&
                                    blockID != Blocks.DIRT &&
                                    blockID != GDBlocks.corruptGrass &&
                                    blockID != GDBlocks.corruptSoil &&
                                    !blockID.isWood(world, cPos)) {
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
                Block blockUsing = world.getBlockState(pos.down()).getBlock();

                if ((blockUsing == GDBlocks.corruptGrass || blockUsing == GDBlocks.heavySoil) && pos.getY() < 256 - height - 1) {
                    setBlockAndNotifyAdequately(world, pos.down(), GDBlocks.corruptSoil.getDefaultState());
                    int k2 = 0;

                    for (int l2 = pos.getY() + height; l2 >= pos.getY() + j; --l2) {
                        for (int j3 = pos.getX() - k2; j3 <= pos.getX() + k2; ++j3) {
                            int k3 = j3 - pos.getX();

                            for (int i2 = pos.getZ() - k2; i2 <= pos.getZ() + k2; ++i2) {
                                int j2 = i2 - pos.getZ();

                                BlockPos tPos = new BlockPos(j3, l2, i2);

                                IBlockState state = world.getBlockState(tPos);

                                if (Math.abs(k3) != k2 || Math.abs(j2) != k2 || k2 <= 0) {
                                    state.getBlock().canBeReplacedByLeaves(state, world, tPos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, world, tPos)) {
                                        this.setBlockAndNotifyAdequately(world, tPos, GDBlocks.gaiaLeavesSpecial.getDefaultState().withProperty(GDSpecialLeaves.VARIANT, SpecialGaiaLeavesVariant.CORRUPTED));
                                    }
                                }
                            }
                        }

                        if (k2 >= 1 && l2 == pos.getY() + j + 1) {
                            --k2;
                        }
                        else if (k2 < l) {
                            ++k2;
                        }
                    }

                    for (int i3 = 0; i3 < height - 1; ++i3) {
                        BlockPos cPos = pos.up(i3);
                        IBlockState block = world.getBlockState(cPos);
                        blockID = block.getBlock();

                        if (blockID == Blocks.AIR || blockID.isLeaves(block, world, cPos)) {
                            this.setBlockAndNotifyAdequately(world, cPos, GDBlocks.gaiaLogSpecial.getDefaultState().withProperty(GDSpecialLog.VARIANT, SpecialGaiaLogVariant.CORRUPTED));
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
