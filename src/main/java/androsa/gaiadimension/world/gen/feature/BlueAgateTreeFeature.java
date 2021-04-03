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
public class BlueAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends GaiaTreeFeature<T> {

    public BlueAgateTreeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(ISeedReader world, Random rand, BlockPos pos, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
        int height = rand.nextInt(4) + config.minHeight;
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

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1 && allClear; ++k1) {
                    for (int l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1 && allClear; ++l1) {
                        if (i1 >= 0 && i1 < world.getHeight()) {
                            if (!validTreePos(world, blockpos$mutableblockpos.set(k1, i1, l1))) {
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
            } else if (isSoil(world, pos.below(), config.getSapling(rand, pos)) && pos.getY() < world.getHeight() - height - 1) {
                this.setBlockState(world, pos.below(), ModBlocks.heavy_soil.get().defaultBlockState(), boundingBox);
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
                                if (isAirOrLeaves(world, blockpos) || isTallPlants(world, blockpos)) {
                                    this.setLeavesBlockState(world, rand, blockpos, leavesPos, boundingBox, config);
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

                for (int logY = 0; logY < height - i4; ++logY) {
                    BlockPos upN = pos.above(logY);
                    if (isAirOrLeaves(world, upN)) {
                        this.setLogBlockState(world, rand, pos.above(logY), logPos, boundingBox, config);
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