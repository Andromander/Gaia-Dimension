package androsa.gaiadimension.world.gen.feature.trunk;

import androsa.gaiadimension.registry.ModWorldgen;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ThickTrunkPlacer extends TrunkPlacer {

    public static final Codec<ThickTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, ThickTrunkPlacer::new));

    public ThickTrunkPlacer(int base, int randA, int randB) {
        super(base, randA, randB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModWorldgen.THICK_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> statepos, RandomSource random, int height, BlockPos origin, TreeConfiguration config) {
        setDirtAt(level, statepos, random, origin.below(), config);

        for (int y = 0; y < height; y++) {
            if (y == 0) {
                placeLog(level, statepos, random, origin.above(y).north(2), config);
                placeLog(level, statepos, random, origin.above(y).south(2), config);
                placeLog(level, statepos, random, origin.above(y).east(2), config);
                placeLog(level, statepos, random, origin.above(y).west(2), config);
            } if (y < height / 4) {
                placeLog(level, statepos, random, origin.offset(1, y, 1), config);
                placeLog(level, statepos, random, origin.offset(1, y, -1), config);
                placeLog(level, statepos, random, origin.offset(-1, y, 1), config);
                placeLog(level, statepos, random, origin.offset(-1, y, -1), config);
            }
            placeLog(level, statepos, random, origin.above(y), config);
            placeLog(level, statepos, random, origin.above(y).north(), config);
            placeLog(level, statepos, random, origin.above(y).south(), config);
            placeLog(level, statepos, random, origin.above(y).east(), config);
            placeLog(level, statepos, random, origin.above(y).west(), config);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(origin.above(height), 0, false));
    }
}
