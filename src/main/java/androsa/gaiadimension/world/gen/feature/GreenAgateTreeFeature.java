package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
public class GreenAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends GaiaTreeFeature<T> {

    public GreenAgateTreeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(WorldGenLevel worldIn, Random rand, BlockPos position, BiConsumer<BlockPos, BlockState> logPos, BiConsumer<BlockPos, BlockState> leavesPos, T config) {
        int height = rand.nextInt(3) + rand.nextInt(3) + config.minHeight;
        boolean isValidBonemealTarget = true;

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

                for (int cx = position.getX() - k; cx <= position.getX() + k && isValidBonemealTarget; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && isValidBonemealTarget; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!validTreePos(worldIn, blockpos$mutableblockpos.set(cx, cy, cz))) {
                                isValidBonemealTarget = false;
                            }
                        } else {
                            isValidBonemealTarget = false;
                        }
                    }
                }
            }

            if (!isValidBonemealTarget) {
                return false;
            } else if (isSoil(worldIn, position.below(), config.getSapling(rand, position)) && position.getY() < worldIn.getHeight() - height - 1) {
                this.setBlock(worldIn, position.below(), ModBlocks.heavy_soil.get().defaultBlockState());
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int currentY = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, currentY, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        if (base == 0) {
                            this.setLog(worldIn, rand, blockpos.north(2), logPos, config);
                            this.setLog(worldIn, rand, blockpos.south(2), logPos, config);
                            this.setLog(worldIn, rand, blockpos.east(2), logPos, config);
                            this.setLog(worldIn, rand, blockpos.west(2), logPos, config);
                        }
                        if (base < height / 4) {
                            this.setLog(worldIn, rand, blockpos.offset(1, 0, 1), logPos, config);
                            this.setLog(worldIn, rand, blockpos.offset(1, 0, -1), logPos, config);
                            this.setLog(worldIn, rand, blockpos.offset(-1, 0, 1), logPos, config);
                            this.setLog(worldIn, rand, blockpos.offset(-1, 0, -1), logPos, config);
                        }
                        this.setLog(worldIn, rand, blockpos, logPos, config);
                        this.setLog(worldIn, rand, blockpos.north(), logPos, config);
                        this.setLog(worldIn, rand, blockpos.south(), logPos, config);
                        this.setLog(worldIn, rand, blockpos.east(), logPos, config);
                        this.setLog(worldIn, rand, blockpos.west(), logPos, config);
                        posY = currentY;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);
                for (int leafX1 = -3; leafX1 <= 3; ++leafX1) {
                    for (int leafZ1 = -3; leafZ1 <= 3; ++leafZ1) {
                        if (Math.abs(leafX1) != 3 || Math.abs(leafZ1) != 3) {
                            this.setLeaves(worldIn, rand, blockpos2.offset(leafX1, 0, leafZ1), leavesPos, config);
                            this.setLeaves(worldIn, rand, blockpos2.offset(leafX1, -1, leafZ1), leavesPos, config);
                        }
                    }
                }

                blockpos2 = blockpos2.above();

                for (int leafX2 = -1; leafX2 <= 1; ++leafX2) {
                    for (int leafZ = -1; leafZ <= 1; ++leafZ) {
                        this.setLeaves(worldIn, rand, blockpos2.offset(leafX2, 0, leafZ), leavesPos, config);
                    }
                }

                this.setLeaves(worldIn, rand, blockpos2.east(2), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos2.west(2), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos2.south(2), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos2.north(2), leavesPos, config);

                blockpos2 = blockpos2.below(3);
                for (int leafX3 = -2; leafX3 <= 2; ++leafX3) {
                    for (int leafZ3 = -2; leafZ3 <= 2; ++leafZ3) {
                        if (Math.abs(leafX3) != 2 || Math.abs(leafZ3) != 2) {
                            this.setLeaves(worldIn, rand, blockpos2.offset(leafX3, 0, leafZ3), leavesPos, config);
                        }
                    }
                }

                blockpos2 = blockpos2.below();

                this.setLeaves(worldIn, rand, blockpos2.offset(1, 0, 1), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos2.offset(1, 0, -1), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos2.offset(-1, 0, 1), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos2.offset(-1, 0, -1), leavesPos, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
