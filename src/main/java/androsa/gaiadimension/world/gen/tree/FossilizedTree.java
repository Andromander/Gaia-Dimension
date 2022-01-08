package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.configurations.GaiaConfiguredFeatures;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class FossilizedTree extends GaiaTree {

    @Override
    public ConfiguredFeature<GaiaTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return GaiaConfiguredFeatures.fossilized_tree;
    }
}
