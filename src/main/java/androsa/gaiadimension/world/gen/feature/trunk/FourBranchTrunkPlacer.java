package androsa.gaiadimension.world.gen.feature.trunk;

import androsa.gaiadimension.registry.ModWorldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.BiConsumer;

public class FourBranchTrunkPlacer extends TrunkPlacer {

    public static final Codec<FourBranchTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, FourBranchTrunkPlacer::new));

    public FourBranchTrunkPlacer(int base, int randA, int randB) {
        super(base, randA, randB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModWorldgen.FOUR_BRANCH_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> statepos, RandomSource random, int height, BlockPos origin, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        setDirtAt(level, statepos, random, origin.below(), config);

        for (int y = 0; y < height; y++) {
            placeLog(level, statepos, random, origin.above(y), config);
        }

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            int x = dir.getStepX();
            int z = dir.getStepZ();

            for (int y = height / 2; y < height; y++) {
                placeLog(level, statepos, random, origin.offset(x, y, z), config);

                if (y == height - 1) {
                    list.add(new FoliagePlacer.FoliageAttachment(origin.offset(x, y + 1, z), 0, false));
                }

                if (y % 2 == 0) {
                    x += dir.getStepX();
                    z += dir.getStepZ();
                }
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(origin.above(height), 0, false));

        return list;
    }
}
