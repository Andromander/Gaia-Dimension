package androsa.gaiadimension.world.gen.feature.foliage;

import androsa.gaiadimension.registry.ModWorldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CubeFoliagePlacer extends FoliagePlacer {
    public static final Codec<CubeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) ->
            foliagePlacerParts(instance).apply(instance, CubeFoliagePlacer::new));

    public CubeFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModWorldgen.CUBE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int height, FoliageAttachment attachment, int foliageheight, int radius, int offset) {
        boolean flag = attachment.doubleTrunk();

        for (int y = radius; y >= -radius; y--) {
            this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), radius, foliageheight - y, flag);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int radius, boolean doubletrunk) {
        return false;
    }
}
