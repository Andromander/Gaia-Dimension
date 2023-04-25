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

        for (int y = 0; y <= height - 2; y++) {
            placeLog(level, statepos, random, origin.above(y), config);
        }

        for (Direction dir : Direction.Plane.HORIZONTAL.stream().toList()) {
            int x = dir.getStepX();
            int z = dir.getStepZ();

            placeLog(level, statepos, random, origin.offset(x, height - 2, z), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, dir.getAxis()));
            placeLog(level, statepos, random, origin.offset(x, height - 1, z), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, dir.getAxis()));
            placeLog(level, statepos, random, origin.offset(x += dir.getStepX(), height - 1, z += dir.getStepZ()), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, dir.getAxis()));

            for (int i = 0; i <= 1; i++) {
                placeLog(level, statepos, random, origin.offset(x += dir.getStepX(), height, z += dir.getStepZ()), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, dir.getAxis()));

                if (i == 1) {
                    list.add(new FoliagePlacer.FoliageAttachment(origin.offset(x += dir.getStepX(), height, z += dir.getStepZ()), 0, false));
                }
            }
        }

        return list;
    }
}
