package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.configurations.GaiaConfiguredFeatures;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class AuraTree extends GaiaTree {

    @Override
    public Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> createTreeFeature(Random random) {
        return GaiaConfiguredFeatures.aura_tree;
    }
}
