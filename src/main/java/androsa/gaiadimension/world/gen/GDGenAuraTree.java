package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDAgateLeaves;
import androsa.gaiadimension.block.GDAgateSapling;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class GDGenAuraTree extends WorldGenAbstractTree {
    private static final IBlockState TRUNK = GDBlocks.aura_log.getDefaultState();
    private static final IBlockState LEAF = GDBlocks.aura_leaves.getDefaultState().withProperty(GDAgateLeaves.CHECK_DECAY, false);

    public GDGenAuraTree(boolean doBlockNotify) {
        super(doBlockNotify);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int baseHeight = rand.nextInt(3) + rand.nextInt(3) + 10;
        boolean canGrow = true;

        if (position.getY() >= 1 && position.getY() + baseHeight + 1 <= 256) {
            for (int yMark = position.getY(); yMark <= position.getY() + 1 + baseHeight; ++yMark) {
                int offset = 1;

                if (yMark == position.getY()) {
                    offset = 0;
                }

                if (yMark >= position.getY() + 1 + baseHeight - 2) {
                    offset = 2;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int xMark = position.getX() - offset; xMark <= position.getX() + offset && canGrow; ++xMark) {
                    for (int zMark = position.getZ() - offset; zMark <= position.getZ() + offset && canGrow; ++zMark) {
                        if (yMark >= 0 && yMark < 256) {
                            if (!this.isReplaceable(worldIn,mutablePos.setPos(xMark, yMark, zMark))) {
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
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, ((GDAgateSapling)GDBlocks.aura_sapling));

                if (isSoil && position.getY() < worldIn.getHeight() - baseHeight - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    int posX = position.getX();
                    int posZ = position.getZ();
                    int k1 = 0;
                    int offset = 1;

                    for (int base = 0; base < baseHeight; ++base) {
                        int i2 = position.getY() + base;

                        BlockPos blockpos = new BlockPos(posX, i2, posZ);
                        state = worldIn.getBlockState(blockpos);

                        if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)) {
                            this.placeLogAt(worldIn, blockpos);
                            k1 = i2;
                        }

                        if (base > baseHeight / 2) {
                            if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)) {
                                this.placeLogAt(worldIn, new BlockPos(posX - offset, i2, posZ));
                                this.placeLogAt(worldIn, new BlockPos(posX + offset, i2, posZ));
                                this.placeLogAt(worldIn, new BlockPos(posX, i2, posZ - offset));
                                this.placeLogAt(worldIn, new BlockPos(posX, i2, posZ + offset));
                                if (base % 2 == 0) {
                                    offset += 1;
                                }
                            }
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(posX, k1 + 1, posZ);

                    for (int j3 = -1; j3 <= 1; ++j3) {
                        for (int i4 = -1; i4 <= 1; ++i4) {
                            if (Math.abs(j3) != 1 || Math.abs(i4) != 1){
                                this.placeLeafAt(worldIn, blockpos2.add(j3 - 3, 0, i4));
                                this.placeLeafAt(worldIn, blockpos2.add(j3 + 3, 0, i4));
                                this.placeLeafAt(worldIn, blockpos2.add(j3, 0, i4 - 3));
                                this.placeLeafAt(worldIn, blockpos2.add(j3, 0, i4 + 3));
                            }
                        }
                    }

                    BlockPos blockpos3 = new BlockPos(posX, k1, posZ);

                    for (int j3 = -2; j3 <= 2; ++j3) {
                        for (int i4 = -2; i4 <= 2; ++i4) {
                            if (Math.abs(j3) != 2 || Math.abs(i4) != 2){
                                this.placeLeafAt(worldIn, blockpos3.add(j3 - 3, 0, i4));
                                this.placeLeafAt(worldIn, blockpos3.add(j3 + 3, 0, i4));
                                this.placeLeafAt(worldIn, blockpos3.add(j3, 0, i4 - 3));
                                this.placeLeafAt(worldIn, blockpos3.add(j3, 0, i4 + 3));
                            }
                        }
                    }

                    this.placeLeafAt(worldIn, blockpos3.up());

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private void placeLogAt(World worldIn, BlockPos pos)
    {
        this.setBlockAndNotifyAdequately(worldIn, pos, TRUNK);
    }

    private void placeLeafAt(World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);

        if (state.getBlock().isAir(state, worldIn, pos) || state.getBlock().isLeaves(state, worldIn, pos))
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
        }
    }
}
