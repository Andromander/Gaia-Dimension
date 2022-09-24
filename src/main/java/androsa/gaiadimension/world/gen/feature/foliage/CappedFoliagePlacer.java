package androsa.gaiadimension.world.gen.feature.foliage;

import androsa.gaiadimension.registry.ModWorldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class CappedFoliagePlacer extends FoliagePlacer {
    public static final Codec<CappedFoliagePlacer> CODEC = RecordCodecBuilder.create((obj) ->
            foliagePlacerParts(obj).apply(obj, CappedFoliagePlacer::new));

    public CappedFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModWorldgen.CAPPED_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> leaves, Random random, TreeConfiguration config, int height, FoliageAttachment attachment, int foliageheight, int trunkheight, int offset) {
        boolean flag = attachment.doubleTrunk();
        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset(), -1 - foliageheight, flag);
        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset() - 1, 0, flag);
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int x, int y, int z, int radius, boolean doubletrunk) {
        if (y == 0) {
            return (x > 1 || z > 1) && x != 0 && z != 0;
        } else {
            return x == radius && z == radius && radius > 0;
        }
    }
}
