package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.feature.config.GaiaTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
public class FieryAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends GaiaTreeFeature<T> {

    public FieryAgateTreeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(WorldGenLevel worldIn, Random rand, BlockPos position, BiConsumer<BlockPos, BlockState> logPos, BiConsumer<BlockPos, BlockState> leavesPos, T config) {
        int height = rand.nextInt(3) + rand.nextInt(3) + config.minHeight;
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
                            if (!validTreePos(worldIn, blockpos$mutableblockpos.set(cx, cy, cz))) {
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
            } else if (isSoil(worldIn, position.below(), config.getSapling(rand, position)) && position.getY() < worldIn.getHeight() - height - 1) {
                this.setBlock(worldIn, position.below(), ModBlocks.heavy_soil.get().defaultBlockState());
                int posX = position.getX();
                int posZ = position.getZ();
                int k1 = 0;

                for (int base = 0; base < height; ++base) {
                    int i2 = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, i2, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.setLog(worldIn, rand, blockpos, logPos, config);
                        k1 = i2;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, k1, posZ);
                for (int j3 = -2; j3 <= 2; ++j3) {
                    for (int i4 = -2; i4 <= 2; ++i4) {
                        if (Math.abs(j3) != 2 || Math.abs(i4) != 2){
                            this.setLeaves(worldIn, rand, blockpos2.offset(j3, 0, i4), leavesPos, config);
                        }
                    }
                }

                blockpos2 = blockpos2.above();
                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        this.setLeaves(worldIn, rand, blockpos2.offset(k3, 0, j4), leavesPos, config);
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
