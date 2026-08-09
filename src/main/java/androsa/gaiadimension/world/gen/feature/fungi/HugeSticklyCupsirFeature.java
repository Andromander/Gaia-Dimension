package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;

public class HugeSticklyCupsirFeature extends HugeCrystalFungiFeature {

    public HugeSticklyCupsirFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        int height = config.stemHeight();
        for (int y = 0; y <= height; y++) {
            placeBlock(level, origin.above(y), config.primaryStem());

            if (y <= height / 2 && random.nextBoolean()) {
                Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                generateBranch(level, random, origin.above(y), config, dir);
            }
        }

        return origin.offset(0, height + 1, 0);
    }

    protected void generateBranch(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config, Direction dir) {
        BlockPos.MutableBlockPos mutable = origin.mutable();

        for (int i = 1; i <= 3; i++) {
            placeBlock(level, mutable.setWithOffset(mutable, dir.getUnitVec3i()), config.primaryStem());
            mutable.setY(mutable.above().getY());
        }

        generateCap(level, random, mutable, config);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        for (int x = -getMaxCapRadius(); x <= getMaxCapRadius(); x++) {
            for (int z = -getMaxCapRadius(); z <= getMaxCapRadius(); z++) {
                for (int y = 0; y < getCapHeight(); y++) {
                    if ((y == getCapHeight() - 1 && x == 0 && z == 0)) {
                        placeBlock(level, origin.offset(x, y, z), config.primaryCap());
                    } else if (y == getCapHeight() - 2 && !(Mth.abs(x) == getMaxCapRadius() && Mth.abs(z) == getMaxCapRadius())) {
                        placeBlock(level, origin.offset(x, y, z), config.primaryCap());
                    } else if (y == 0) {
                        placeBlock(level, origin.offset(x, y, z), config.primaryCap());
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
        return 1;
    }
}
