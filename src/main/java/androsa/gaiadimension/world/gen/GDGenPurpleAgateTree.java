package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDAgateLeaves;
import androsa.gaiadimension.block.GDAgateSapling;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class GDGenPurpleAgateTree extends WorldGenAbstractTree {
    private static final IBlockState TRUNK = GDBlocks.purple_agate_log.getDefaultState();
    private static final IBlockState LEAF = GDBlocks.purple_agate_leaves.getDefaultState().withProperty(GDAgateLeaves.CHECK_DECAY, Boolean.FALSE);

    public GDGenPurpleAgateTree(boolean flag) {
        super(flag);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 7;
        boolean canGrow = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 256) {
            for (int cy = position.getY(); cy <= position.getY() + 1 + height; ++cy) {
                int k = 1;

                if (cy == position.getY()) {
                    k = 0;
                }

                if (cy >= position.getY() + 1 + height - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int cx = position.getX() - k; cx <= position.getX() + k && canGrow; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && canGrow; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(cx, cy, cz))) {
                                canGrow = false;
                            }
                        } else {
                            canGrow = false;
                        }
                    }
                }
            }

            if (!canGrow) {
                return false;
            } else {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, ((GDAgateSapling)GDBlocks.purple_agate_sapling));

                if (isSoil && position.getY() < worldIn.getHeight() - height - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    int posX = position.getX();
                    int posZ = position.getZ();
                    int posY = 0;

                    for (int base = 0; base < height; ++base) {
                        int currentY = position.getY() + base;

                        BlockPos blockpos = new BlockPos(posX, currentY, posZ);
                        state = worldIn.getBlockState(blockpos);

                        if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)) {
                            if (base == height - 2) {
                                for (int length = 1; length <= 2; ++length) {
                                    this.placeLogAt(worldIn, blockpos.north(length), BlockLog.EnumAxis.Z);
                                    this.placeLogAt(worldIn, blockpos.south(length), BlockLog.EnumAxis.Z);
                                    this.placeLogAt(worldIn, blockpos.east(length), BlockLog.EnumAxis.X);
                                    this.placeLogAt(worldIn, blockpos.west(length), BlockLog.EnumAxis.X);
                                }
                            } else if (base == height - 1) {
                                for (int length = 3; length <= 4; ++length) {
                                    this.placeLogAt(worldIn, blockpos.north(length), BlockLog.EnumAxis.Z);
                                    this.placeLogAt(worldIn, blockpos.south(length), BlockLog.EnumAxis.Z);
                                    this.placeLogAt(worldIn, blockpos.east(length), BlockLog.EnumAxis.X);
                                    this.placeLogAt(worldIn, blockpos.west(length), BlockLog.EnumAxis.X);
                                }
                            } else {
                                this.placeLogAt(worldIn, blockpos, BlockLog.EnumAxis.Y);
                            }
                            posY = currentY;
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(posX, posY, posZ);

                    for (int k3 = -1; k3 <= 1; ++k3) {
                        for (int j4 = -1; j4 <= 1; ++j4) {
                            for (int l5 = -1; l5 <= 1; ++l5) {
                                if (Math.abs(k3) != 1 || Math.abs(j4) != 1 || Math.abs(l5) != 1){
                                    this.placeLeafAt(worldIn, blockpos2.add(k3 + 4, l5, j4));
                                    this.placeLeafAt(worldIn, blockpos2.add(k3 - 4, l5, j4));
                                    this.placeLeafAt(worldIn, blockpos2.add(k3 , l5, j4 + 4));
                                    this.placeLeafAt(worldIn, blockpos2.add(k3, l5, j4 - 4));
                                }
                            }
                        }
                    }

                    BlockPos blockpos3 = blockpos2.down(2);

                    this.placeLeafAt(worldIn, blockpos3.north(1));
                    this.placeLeafAt(worldIn, blockpos3.south(1));
                    this.placeLeafAt(worldIn, blockpos3.east(1));
                    this.placeLeafAt(worldIn, blockpos3.west(1));

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private void placeLogAt(World worldIn, BlockPos pos, BlockLog.EnumAxis axis) {
        this.setBlockAndNotifyAdequately(worldIn, pos, TRUNK.withProperty(LOG_AXIS, axis));
    }

    private void placeLeafAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);

        if (state.getBlock().isAir(state, worldIn, pos) || state.getBlock().isLeaves(state, worldIn, pos)) {
            this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
        }
    }
}
