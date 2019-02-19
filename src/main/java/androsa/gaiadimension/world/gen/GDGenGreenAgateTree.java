package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDAgateLeaves;
import androsa.gaiadimension.block.GDAgateSapling;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class GDGenGreenAgateTree extends WorldGenAbstractTree {
    private static final IBlockState TRUNK = GDBlocks.green_agate_log.getDefaultState();
    private static final IBlockState LEAF = GDBlocks.green_agate_leaves.getDefaultState().withProperty(GDAgateLeaves.CHECK_DECAY, Boolean.FALSE);

    public GDGenGreenAgateTree(boolean canGrow) {
        super(canGrow);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 10;
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

            for (int cx = 0; cx < 3; cx++) {
                for (int cz = 0; cz < 3; cz++) {
                    BlockPos pos = position.add(cx - 1, 0, cz - 1);

                    if (worldIn.isBlockLoaded(pos)) {
                        Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();

                        if (blockBelow != GDBlocks.glitter_grass && blockBelow != GDBlocks.heavy_soil) {
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
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, ((GDAgateSapling)GDBlocks.green_agate_sapling));

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
                            if (base == 0) {
                                this.placeLogAt(worldIn, blockpos.north(2));
                                this.placeLogAt(worldIn, blockpos.south(2));
                                this.placeLogAt(worldIn, blockpos.east(2));
                                this.placeLogAt(worldIn, blockpos.west(2));
                            }
                            if (base < height / 4) {
                                this.placeLogAt(worldIn, blockpos.add(1, 0, 1));
                                this.placeLogAt(worldIn, blockpos.add(1, 0, -1));
                                this.placeLogAt(worldIn, blockpos.add(-1, 0, 1));
                                this.placeLogAt(worldIn, blockpos.add(-1, 0, -1));
                            }
                            this.placeLogAt(worldIn, blockpos);
                            this.placeLogAt(worldIn, blockpos.north());
                            this.placeLogAt(worldIn, blockpos.south());
                            this.placeLogAt(worldIn, blockpos.east());
                            this.placeLogAt(worldIn, blockpos.west());
                            posY = currentY;
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(posX, posY, posZ);

                    for (int leafX1 = -3; leafX1 <= 3; ++leafX1) {
                        for (int leafZ1 = -3; leafZ1 <= 3; ++leafZ1) {
                            if (Math.abs(leafX1) != 3 || Math.abs(leafZ1) != 3) {
                                this.placeLeafAt(worldIn, blockpos2.add(leafX1, 0, leafZ1));
                                this.placeLeafAt(worldIn, blockpos2.add(leafX1, -1, leafZ1));
                            }
                        }
                    }

                    blockpos2 = blockpos2.up();

                    for (int leafX2 = -1; leafX2 <= 1; ++leafX2) {
                        for (int leafZ = -1; leafZ <= 1; ++leafZ) {
                            this.placeLeafAt(worldIn, blockpos2.add(leafX2, 0, leafZ));
                        }
                    }

                    this.placeLeafAt(worldIn, blockpos2.east(2));
                    this.placeLeafAt(worldIn, blockpos2.west(2));
                    this.placeLeafAt(worldIn, blockpos2.south(2));
                    this.placeLeafAt(worldIn, blockpos2.north(2));

                    blockpos2 = blockpos2.down(3);

                    for (int leafX3 = -2; leafX3 <= 2; ++leafX3) {
                        for (int leafZ3 = -2; leafZ3 <= 2; ++leafZ3) {
                            if (Math.abs(leafX3) != 2 || Math.abs(leafZ3) != 2) {
                                this.placeLeafAt(worldIn, blockpos2.add(leafX3, 0, leafZ3));
                            }
                        }
                    }

                    blockpos2 = blockpos2.down();

                    this.placeLeafAt(worldIn, blockpos2.add(1, 0, 1));
                    this.placeLeafAt(worldIn, blockpos2.add(1, 0, -1));
                    this.placeLeafAt(worldIn, blockpos2.add(-1, 0, 1));
                    this.placeLeafAt(worldIn, blockpos2.add(-1, 0, -1));

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private void placeLogAt(World worldIn, BlockPos pos) {
        this.setBlockAndNotifyAdequately(worldIn, pos, TRUNK);
    }

    private void placeLeafAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);

        if (state.getBlock().isAir(state, worldIn, pos) || state.getBlock().isLeaves(state, worldIn, pos)) {
            this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
        }
    }
}
