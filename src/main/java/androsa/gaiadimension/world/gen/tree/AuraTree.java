package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class AuraTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random random) {
        return ModWorldgen.AURA_TREE.withConfiguration(GaiaBiomeFeatures.AURA_TREE_CONFIG);
    }
}
