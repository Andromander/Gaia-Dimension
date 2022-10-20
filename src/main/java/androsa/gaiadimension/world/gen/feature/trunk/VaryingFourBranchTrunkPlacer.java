package androsa.gaiadimension.world.gen.feature.trunk;

import androsa.gaiadimension.registry.ModWorldgen;
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
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class VaryingFourBranchTrunkPlacer extends TrunkPlacer {

    public static final Codec<VaryingFourBranchTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, VaryingFourBranchTrunkPlacer::new));

    public VaryingFourBranchTrunkPlacer(int base, int randA, int randB) {
        super(base, randA, randB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModWorldgen.VARYING_FOUR_BRANCH_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> statepos, Random random, int height, BlockPos origin, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        setDirtAt(level, statepos, random, origin.below(), config);

        for (int y = 0; y <= height / 2; y++) {
            placeLog(level, statepos, random, origin.above(y), config);
        }

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            int x = 0;
            int z = 0;
            int offset = random.nextInt(3);
            int startY = height / 2 - offset;

            for (int i = 0; i <= random.nextInt(3) + 1; i++) {
                placeLog(level, statepos, random, origin.offset(x += dir.getStepX(), startY, z += dir.getStepZ()), config, (state) -> state.setValue(RotatedPillarBlock.AXIS, dir.getAxis()));
            }

            for (int i = startY; i <= height - offset; i++) {
                placeLog(level, statepos, random, origin.offset(x, i, z), config);
            }

            list.add(new FoliagePlacer.FoliageAttachment(origin.offset(x, height - offset, z), 0, false));
        }

        return list;
    }
}
