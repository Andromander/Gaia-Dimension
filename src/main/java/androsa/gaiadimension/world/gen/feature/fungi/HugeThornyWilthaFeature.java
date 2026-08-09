package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HugeThornyWilthaFeature extends HugeCrystalFungiFeature {

    public HugeThornyWilthaFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        BlockPos.MutableBlockPos mutable = origin.mutable();
        boolean offset = false;

        for (int i = 0; i <= config.stemHeight(); i++) {
            if (i > config.stemHeight() / 2 && !offset) {
                placeBlock(level, mutable.offset(0, i, 0), config.primaryStem());
                mutable.setWithOffset(origin, Direction.Plane.HORIZONTAL.getRandomDirection(random).getUnitVec3i());
                offset = true;
            }

            BlockPos placement = mutable.offset(0, i, 0);
            placeBlock(level, placement, config.primaryStem());

            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockState thorn;
            if (config.secondaryStem().isPresent()) {
                thorn = config.secondaryStem().get().get(random.nextInt(config.secondaryStem().get().size()));
            } else {
                thorn = config.primaryStem();
            }

            if (thorn.hasProperty(RotatedPillarBlock.AXIS)) {
                thorn = thorn.setValue(RotatedPillarBlock.AXIS, direction.getAxis());
            }
            placeBlock(level, placement.offset(direction.getUnitVec3i()), thorn);
        }

        return mutable.above(config.stemHeight() + 1);
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {
        BlockState thorn;
        if (config.secondaryStem().isPresent()) {
            thorn = config.secondaryCap().get().get(rand.nextInt(config.secondaryCap().get().size()));
        } else {
            thorn = config.primaryCap();
        }

        for (int x = -getMaxCapRadius(); x <= getMaxCapRadius(); x++) {
            for (int z = -getMaxCapRadius(); z <= getMaxCapRadius(); z++) {
                if (Mth.abs(x) == 3 && z == 0 || Mth.abs(z) == 3 && x == 0) {
                    placeBlock(level, origin.offset(x, 0, z), config.primaryCap());
                    placeBlock(level, origin.offset(x, 1, z), thorn);
                }

                if (Mth.abs(x) <= 2 && Mth.abs(z) <= 2 && !(Mth.abs(x) == 2 && Mth.abs(z) == 2)) {
                    placeBlock(level, origin.offset(x, 0, z), config.primaryCap());

                    if (Mth.abs(x) % 2 != 0 && Mth.abs(z) % 2 != 0) {
                        placeBlock(level, origin.offset(x, 1, z), thorn);
                    }
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
