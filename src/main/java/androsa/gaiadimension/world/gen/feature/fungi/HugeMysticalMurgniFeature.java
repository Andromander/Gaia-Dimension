package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public class HugeMysticalMurgniFeature extends HugeCrystalFungiFeature {

    public HugeMysticalMurgniFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        int height = config.stemHeight();
        for (int y = 0; y <= height; y++) {
            placeBlock(level, origin.above(y), config.primaryStem());
            placeBlock(level, origin.north().above(y), config.primaryStem());
            placeBlock(level, origin.south().above(y), config.primaryStem());
            placeBlock(level, origin.east().above(y), config.primaryStem());
            placeBlock(level, origin.west().above(y), config.primaryStem());
        }

        return origin.offset(0, height + 1, 0);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        for (int x = -getMaxCapRadius(); x <= getMaxCapRadius(); x++) {
            for (int z = -getMaxCapRadius(); z <= getMaxCapRadius(); z++) {
                if (Mth.abs(x) + Mth.abs(z) <= 5) {
                    placeBlock(level, origin.offset(x, 0, z), config.primaryCap());

                    if (Mth.abs(x) % 2 != 1 && Mth.abs(z) % 2 != 1) {
                        placeBlock(level, origin.offset(x, -1, z), config.primaryCap());

                        BlockState state;
                        if (config.secondaryCap().isPresent()) {
                            state = config.secondaryCap().get().get(rand.nextInt(config.secondaryCap().get().size()));
                        } else {
                            state = config.primaryCap();
                        }
                        placeBlock(level, origin.offset(x, -2, z), state);
                    }
                }
            }
        }

        for (int x = -getMaxCapRadius() + 1; x <= getMaxCapRadius() - 1; x++) {
            for (int z = -getMaxCapRadius() + 1; z <= getMaxCapRadius() - 1; z++) {
                if (Mth.abs(x) + Mth.abs(z) <= 4) {
                    placeBlock(level, origin.offset(x, 1, z), config.primaryCap());
                }
            }
        }
    }

    @Override
    public int getCapHeight() {
        return 2;
    }

    @Override
    protected int getMaxCapRadius() {
        return 4;
    }
}
