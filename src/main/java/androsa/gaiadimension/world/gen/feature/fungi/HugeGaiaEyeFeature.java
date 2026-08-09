package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class HugeGaiaEyeFeature extends HugeCrystalFungiFeature {

    public HugeGaiaEyeFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        int height = config.stemHeight();

        for (int y = 0; y <= height; y++) {
            placeBlock(level, origin.offset(0, y, 0), config.primaryStem());

            if (y == height) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        placeBlock(level, origin.offset(x, y, z), config.primaryStem());
                    }
                }
            }
        }

        return origin.offset(0, height, 0);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (!(Mth.abs(x) == 2 && Mth.abs(z) == 2)) {
                    placeBlock(level, origin.offset(x, 0 , z), config.primaryCap());
                }
            }
        }

        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (!(Mth.abs(x) == 3 && Mth.abs(z) == 3)) {
                    placeBlock(level, origin.offset(x, 1 , z), config.primaryCap());

                    if (Mth.abs(x) <= 2 && Mth.abs(z) <= 2) {
                        placeBlock(level, origin.offset(x, 2, z), getSecondaryCap(config, 0));
                    } else {
                        placeBlock(level, origin.offset(x, 2 , z), config.primaryCap());
                    }
                }
            }
        }

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Mth.abs(x) == 2 && Mth.abs(z) == 2) {
                    placeBlock(level, origin.offset(x, 3 , z), config.primaryCap());
                } else {
                    if ((Mth.abs(x) < 2 && Mth.abs(z) < 2) && !(x == 0 && z == 0)) {
                        placeBlock(level, origin.offset(x, 3, z), getSecondaryCap(config, 1));
                    } else {
                        placeBlock(level, origin.offset(x, 3, z), getSecondaryCap(config, 0));
                    }
                }
            }
        }

        for (int x = -getMaxCapRadius(); x <= getMaxCapRadius(); x++) {
            for (int z = -getMaxCapRadius(); z <= getMaxCapRadius(); z++) {
                if ((Mth.abs(x) == 2 && Mth.abs(z) == getMaxCapRadius()) || (Mth.abs(z) == 2 && Mth.abs(x) == getMaxCapRadius())) {
                    for (int y = 0; y >= -2; y--) {
                        placeBlock(level, origin.offset(x, y , z), config.primaryCap());
                    }
                }
            }
        }
    }

    protected BlockState getSecondaryCap(CrystalFungiConfig config, int index) {
        if (config.secondaryCap().isPresent()) {
            List<BlockState> states = config.secondaryCap().get();

            if (states.size() >= index - 1) {
                return states.get(index);
            }
        }
        return config.primaryCap();
    }

    @Override
    public int getCapHeight() {
        return 3;
    }

    @Override
    protected int getMaxCapRadius() {
        return 4;
    }
}
