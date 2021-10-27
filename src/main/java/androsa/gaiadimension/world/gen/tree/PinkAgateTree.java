package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class PinkAgateTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return ModWorldgen.PINK_AGATE_TREE.get().configured(GaiaBiomeFeatures.PINK_AGATE_TREE_CONFIG);
    }
}
