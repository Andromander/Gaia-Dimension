package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class GreenAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends AbstractTreeFeature<T> {
    //TODO: Move to Builder
    private static final BlockState TRUNK = ModBlocks.green_agate_log.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.green_agate_leaves.get().getDefaultState();

    public GreenAgateTreeFeature(Function<Dynamic<?>, T> configIn) {
        super(configIn);
    }

    @Override
    protected boolean generate(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
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

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for (int cx = position.getX() - k; cx <= position.getX() + k && canGrow; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && canGrow; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(cx, cy, cz))) {
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
            } else if (isSoil(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight() - height - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int currentY = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, currentY, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        if (base == 0) {
                            this.setLogBlockState(worldIn, rand, blockpos.north(2), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, blockpos.south(2), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, blockpos.east(2), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, blockpos.west(2), logPos, boundingBox, config);
                        }
                        if (base < height / 4) {
                            this.setLogBlockState(worldIn, rand, blockpos.add(1, 0, 1), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, blockpos.add(1, 0, -1), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, blockpos.add(-1, 0, 1), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, blockpos.add(-1, 0, -1), logPos, boundingBox, config);
                        }
                        this.setLogBlockState(worldIn, rand, blockpos, logPos, boundingBox, config);
                        this.setLogBlockState(worldIn, rand, blockpos.north(), logPos, boundingBox, config);
                        this.setLogBlockState(worldIn, rand, blockpos.south(), logPos, boundingBox, config);
                        this.setLogBlockState(worldIn, rand, blockpos.east(), logPos, boundingBox, config);
                        this.setLogBlockState(worldIn, rand, blockpos.west(), logPos, boundingBox, config);
                        posY = currentY;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);
                for (int leafX1 = -3; leafX1 <= 3; ++leafX1) {
                    for (int leafZ1 = -3; leafZ1 <= 3; ++leafZ1) {
                        if (Math.abs(leafX1) != 3 || Math.abs(leafZ1) != 3) {
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(leafX1, 0, leafZ1), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(leafX1, -1, leafZ1), leavesPos, boundingBox, config);
                        }
                    }
                }

                blockpos2 = blockpos2.up();

                for (int leafX2 = -1; leafX2 <= 1; ++leafX2) {
                    for (int leafZ = -1; leafZ <= 1; ++leafZ) {
                        this.setLeavesBlockState(worldIn, rand, blockpos2.add(leafX2, 0, leafZ), leavesPos, boundingBox, config);
                    }
                }

                this.setLeavesBlockState(worldIn, rand, blockpos2.east(2), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.west(2), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.south(2), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.north(2), leavesPos, boundingBox, config);

                blockpos2 = blockpos2.down(3);
                for (int leafX3 = -2; leafX3 <= 2; ++leafX3) {
                    for (int leafZ3 = -2; leafZ3 <= 2; ++leafZ3) {
                        if (Math.abs(leafX3) != 2 || Math.abs(leafZ3) != 2) {
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(leafX3, 0, leafZ3), leavesPos, boundingBox, config);
                        }
                    }
                }

                blockpos2 = blockpos2.down();

                this.setLeavesBlockState(worldIn, rand, blockpos2.add(1, 0, 1), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.add(1, 0, -1), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.add(-1, 0, 1), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.add(-1, 0, -1), leavesPos, boundingBox, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
