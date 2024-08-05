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

public class ThickFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<ThickFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((obj) ->
            foliagePlacerParts(obj).apply(obj, ThickFoliagePlacer::new));

    public ThickFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModWorldgen.THICK_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int height, FoliageAttachment attachment, int foliageheight, int trunkheight, int offset) {
        boolean flag = attachment.doubleTrunk();

        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset() - 2, -4, flag);
        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset() - 1, -3, flag);
        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset(), -2, flag);
        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset(), -1, flag);
        this.placeLeavesRow(reader, leaves, random, config, attachment.pos(), trunkheight + attachment.radiusOffset() - 1, 0, flag);
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int radius, boolean doubletrunk) {
        if (y == 0) {
            return (x > 1 || z > 1) && x != 0 && z != 0;
        } else if (y <= -4) {
            return false;
        } else {
            return x == radius && z == radius && radius > 0;
        }
    }
}
