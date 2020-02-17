package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import androsa.gaiadimension.world.gen.feature.GaiaTree;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class AuraTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random random) {
        return ModWorldgen.AURA_TREE.get().configure(GaiaBiomeFeatures.AURA_TREE_CONFIG);
    }
}
