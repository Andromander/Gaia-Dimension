package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class FieryAgateTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return ModWorldgen.FIERY_AGATE_TREE.get().configured(GaiaBiomeFeatures.BURNING_TREE_CONFIG);
    }
}
