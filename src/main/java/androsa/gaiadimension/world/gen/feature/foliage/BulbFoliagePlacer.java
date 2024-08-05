package androsa.gaiadimension.world.gen.feature.foliage;

import androsa.gaiadimension.registry.registration.ModWorldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class BulbFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<BulbFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).apply(instance, BulbFoliagePlacer::new));

    public BulbFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModWorldgen.BULB_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int height, FoliageAttachment attachment, int foliageheight, int trunkheight, int offset) {
        boolean flag = attachment.doubleTrunk();

        for (int y = 1; y >= -1; y--) {
            this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset(), foliageheight - y, flag);
        }
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int height, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int radius, boolean doubletrunk) {
        return Math.abs(x) == radius && Math.abs(y) == radius && Math.abs(z) == radius;
    }
}
