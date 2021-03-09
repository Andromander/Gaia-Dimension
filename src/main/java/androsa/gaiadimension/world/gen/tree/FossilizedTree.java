package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class FossilizedTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return ModWorldgen.FOSSILIZED_TREE.get().withConfiguration(GaiaBiomeFeatures.FOSSILIZED_TREE_CONFIG);
    }
}
