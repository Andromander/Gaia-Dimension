package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDAgateLeaves;
import androsa.gaiadimension.block.GDAgateSapling;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class GDGenBurntAgateTree extends WorldGenAbstractTree {
    private static final IBlockState TRUNK = GDBlocks.crusty_log.getDefaultState();
    private static final IBlockState LEAF = GDBlocks.crusty_leaves.getDefaultState().withProperty(GDAgateLeaves.CHECK_DECAY, Boolean.FALSE);

    public GDGenBurntAgateTree(boolean flag) {
        super(flag);
    }

    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 5;
        boolean flag = true;

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

                for (int cx = position.getX() - k; cx <= position.getX() + k && flag; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && flag; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(cx, cy, cz))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, ((GDAgateSapling)GDBlocks.burnt_sapling));

                if (isSoil && position.getY() < worldIn.getHeight() - height - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    int posX = position.getX();
                    int posZ = position.getZ();
                    int k1 = 0;

                    for (int base = 0; base < height; ++base) {
                        int i2 = position.getY() + base;

                        BlockPos blockpos = new BlockPos(posX, i2, posZ);
                        state = worldIn.getBlockState(blockpos);

                        if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)) {
                            this.placeLogAt(worldIn, blockpos);
                            k1 = i2;
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(posX, k1, posZ);

                    for (int j3 = -2; j3 <= 2; ++j3) {
                        for (int i4 = -2; i4 <= 2; ++i4) {
                            if (Math.abs(j3) != 2 || Math.abs(i4) != 2){
                                this.placeLeafAt(worldIn, blockpos2.add(j3, 0, i4));
                            }
                        }
                    }

                    blockpos2 = blockpos2.up();

                    for (int k3 = -1; k3 <= 1; ++k3) {
                        for (int j4 = -1; j4 <= 1; ++j4) {
                            this.placeLeafAt(worldIn, blockpos2.add(k3, 0, j4));
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
