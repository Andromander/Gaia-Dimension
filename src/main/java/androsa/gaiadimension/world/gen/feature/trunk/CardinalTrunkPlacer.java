package androsa.gaiadimension.world.gen.feature.trunk;

import androsa.gaiadimension.registry.ModWorldgen;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class CardinalTrunkPlacer extends TrunkPlacer {

    public static final Codec<CardinalTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, CardinalTrunkPlacer::new));

    public CardinalTrunkPlacer(int base, int randA, int randB) {
        super(base, randA, randB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModWorldgen.CARDINAL_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> statepos, Random random, int height, BlockPos origin, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        setDirtAt(level, statepos, random, origin.below(), config);

        for (int y = 0; y < height; y++) {
            if (y == height - 3) {
                placeLog(level, statepos, random, origin.above(y).north(), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                placeLog(level, statepos, random, origin.above(y).south(), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                placeLog(level, statepos, random, origin.above(y).east(), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                placeLog(level, statepos, random, origin.above(y).west(), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));

            }
            if (y == height - 2) {
                for (int length = 1; length <= 2; length++) {
                    placeLog(level, statepos, random, origin.above(y).north(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                    placeLog(level, statepos, random, origin.above(y).south(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                    placeLog(level, statepos, random, origin.above(y).east(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                    placeLog(level, statepos, random, origin.above(y).west(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                }
            } else if (y == height - 1) {
                for (int length = 3; length <= 4; length++) {
                    placeLog(level, statepos, random, origin.above(y).north(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                    placeLog(level, statepos, random, origin.above(y).south(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                    placeLog(level, statepos, random, origin.above(y).east(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                    placeLog(level, statepos, random, origin.above(y).west(length), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));

                    if (length == 4) {
                        list.add(new FoliagePlacer.FoliageAttachment(origin.above(y).north(length + 1), 0, false));
                        list.add(new FoliagePlacer.FoliageAttachment(origin.above(y).south(length + 1), 0, false));
                        list.add(new FoliagePlacer.FoliageAttachment(origin.above(y).east(length + 1), 0, false));
                        list.add(new FoliagePlacer.FoliageAttachment(origin.above(y).west(length + 1), 0, false));
                    }
                }
            } else {
                placeLog(level, statepos, random, origin.above(y), config);
            }
        }

        return list;
    }
}
