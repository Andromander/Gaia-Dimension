package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

public class HugeTwinklingGilsriFeature extends HugeCrystalFungiFeature {

    public HugeTwinklingGilsriFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        int height = config.stemHeight();

        for (int y = 0; y <= height; y++) {
            placeBlock(level, origin.offset(0, y, 0), config.primaryStem());
        }

        for (int x = -getMaxCapRadius() - 1; x <= getMaxCapRadius() + 1; x++) {
            for (int z = -getMaxCapRadius() - 1; z <= getMaxCapRadius() + 1; z++) {
                if (Math.abs(x) == 0 || Math.abs(z) == 0) {
                    if (!(x == 0 && z == 0)) {
                        placeBlock(level, origin.offset(x, height - 1 + Math.abs(x) + Math.abs(z), z), config.primaryStem());
                    }
                }

                if (Math.abs(x) == 1 && Math.abs(z) == 1) {
                    if (!(x == 0 && z == 0)) {
                        placeBlock(level, origin.offset(x, height + 1, z), config.primaryStem());
                    }
                }
            }
        }

        return origin.offset(0, height, 0);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        for (int x = -getMaxCapRadius() - 1; x <= getMaxCapRadius() + 1; x++) {
            for (int z = -getMaxCapRadius() - 1; z <= getMaxCapRadius() + 1; z++) {
                for (int i = 1; i <= 2; i++) {
                    if (Math.abs(x) + Math.abs(z) <= i) {
                        placeBlock(level, origin.offset(x, i, z), config.primaryCap());
                    }
                }
            }
        }
    }

    @Override
    public int getCapHeight() {
        return 3;
    }

    @Override
    protected int getMaxCapRadius() {
        return 2;
    }
}
