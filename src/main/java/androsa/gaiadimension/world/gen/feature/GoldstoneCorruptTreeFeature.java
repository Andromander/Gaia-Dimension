package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;

@ParametersAreNonnullByDefault
public class GoldstoneCorruptTreeFeature<T extends GaiaTreeFeatureConfig> extends GaiaTreeFeature<T> {

    public GoldstoneCorruptTreeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(ISeedReader world, Random rand, BlockPos pos, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
        int height = rand.nextInt(5) + config.minHeight;
        int j = height - rand.nextInt(2) - 3;
        int k = height - j;
        int l = 1 + rand.nextInt(k + 1);
        boolean allClear = true;

        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256) {
            for (int cy = pos.getY(); cy <= pos.getY() + 1 + height && allClear; ++cy) {
                int j1;

                if (cy - pos.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                for (int cx = pos.getX() - j1; cx <= pos.getX() + j1 && allClear; ++cx) {
                    for (int cz = pos.getZ() - j1; cz <= pos.getZ() + j1 && allClear; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            BlockPos cPos = new BlockPos(cx, cy, cz);

                            if (!isReplaceableAt(world, cPos)) {
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
            } else if (isSoil(world, pos.down(), config.getSapling(rand, pos)) && pos.getY() < world.getHeight() - height - 1) {
                this.setBlockState(world, pos.down(), ModBlocks.corrupt_soil.get().getDefaultState(), boundingBox);
                int k2 = 0;

                for (int l2 = pos.getY() + height; l2 >= pos.getY() + j; --l2) {
                    for (int j3 = pos.getX() - k2; j3 <= pos.getX() + k2; ++j3) {
                        int k3 = j3 - pos.getX();

                        for (int i2 = pos.getZ() - k2; i2 <= pos.getZ() + k2; ++i2) {
                            int j2 = i2 - pos.getZ();

                            BlockPos tPos = new BlockPos(j3, l2, i2);
                            if (Math.abs(k3) != k2 || Math.abs(j2) != k2 || k2 <= 0) {
                                if (isAirOrLeaves(world, tPos)) {
                                    this.setLeavesBlockState(world, rand, tPos, leavesPos, boundingBox, config);
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
                    if (isAirOrLeaves(world, cPos)) {
                        this.setLogBlockState(world, rand, cPos, logPos, boundingBox, config);
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
