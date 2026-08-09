package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;

public class HugeRoofedAgaricFeature extends HugeCrystalFungiFeature {

    public HugeRoofedAgaricFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        for (int i = 0; i <= config.stemHeight(); i++) {
            if (i > config.stemHeight() * 0.55) {
                placeBlock(level, origin.offset(1, i, 0), config.primaryStem());
                placeBlock(level, origin.offset(-1, i, 0), config.primaryStem());
                placeBlock(level, origin.offset(0, i, 1), config.primaryStem());
                placeBlock(level, origin.offset(0, i, -1), config.primaryStem());
            }

            if (i == config.stemHeight()) {
                placeBlock(level, origin.offset(1, i, 1), config.primaryStem());
                placeBlock(level, origin.offset(-1, i, 1), config.primaryStem());
                placeBlock(level, origin.offset(-1, i, -1), config.primaryStem());
                placeBlock(level, origin.offset(1, i, -1), config.primaryStem());
            }

            placeBlock(level, origin.offset(0, i, 0), config.primaryStem());
        }

        return origin.offset(0, config.stemHeight(), 0);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        BlockPos.MutableBlockPos mutable = origin.mutable();

        for (int x = -getMaxCapRadius(); x <= getMaxCapRadius(); x++) {
            for (int z = -getMaxCapRadius(); z <= getMaxCapRadius(); z++) {
                if (!(Mth.abs(x) == 3 && Mth.abs(z) == 3)) {
                    placeBlock(level, mutable.offset(x, 0, z), config.primaryCap());
                }
            }
        }

        mutable.setWithOffset(origin, 0, 1, 0);

        for (int x = -getMaxCapRadius() + 1; x <= getMaxCapRadius() - 1; x++) {
            for (int z = -getMaxCapRadius() + 1; z <= getMaxCapRadius() - 1; z++) {
                if (!(Mth.abs(x) == 2 && Mth.abs(z) == 2)) {
                    placeBlock(level, mutable.offset(x, 0, z), config.primaryCap());
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
        return 3;
    }
}
