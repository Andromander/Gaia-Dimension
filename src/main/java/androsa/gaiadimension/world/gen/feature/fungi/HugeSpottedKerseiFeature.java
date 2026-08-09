package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public class HugeSpottedKerseiFeature extends HugeCrystalFungiFeature {

    public HugeSpottedKerseiFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        int height = config.stemHeight();

        for (int y = 0; y <= height; y++) {
            placeBlock(level, origin.above(y), config.primaryStem());
        }

        for (Direction dir : Direction.Plane.HORIZONTAL.stream().toList()) {
            placeBlock(level, origin.offset(dir.getStepX(), height, dir.getStepZ()), config.primaryStem());
        }

        return origin.offset(0, height, 0);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        for (int y = 0; y <= getCapHeight() - 1; y++) {
            for (int x = -getMaxCapRadius(); x <= getMaxCapRadius(); x++) {
                for (int z = -getMaxCapRadius(); z <= getMaxCapRadius(); z++) {
                    if (y < 2 && !(Mth.abs(x) == 2 && Mth.abs(z) == 2)) {
                        placeBlock(level, origin.offset(x, y, z), getCapState(rand, config));
                    }

                    if (y < 4 && (Mth.abs(x) != 2 && Mth.abs(z) != 2)) {
                        placeBlock(level, origin.offset(x, y, z), getCapState(rand, config));
                    }

                    placeBlock(level, origin.offset(0, y, 0), getCapState(rand, config));
                }
            }
        }
    }

    private BlockState getCapState(RandomSource rand, CrystalFungiConfig config) {
        if (config.secondaryCap().isPresent()) {
            if (rand.nextInt(4) == 0) {
                return config.secondaryCap().get().get(rand.nextInt(config.secondaryCap().get().size()));
            }
        }

        return config.primaryCap();
    }

    @Override
    public int getCapHeight() {
        return 5;
    }

    @Override
    protected int getMaxCapRadius() {
        return 2;
    }
}
