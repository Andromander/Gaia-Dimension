package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import androsa.gaiadimension.world.gen.feature.GaiaTree;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class BlueAgateTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return ModWorldgen.BLUE_AGATE_TREE.get().configure(GaiaBiomeFeatures.BLUE_AGATE_TREE_CONFIG);
    }
}
