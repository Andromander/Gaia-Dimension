package androsa.gaiadimension.world.gen.feature.tree;

import androsa.gaiadimension.registry.configurations.GaiaFeatures;
import androsa.gaiadimension.world.gen.feature.config.GaiaTreeFeatureConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class AuraTree extends GaiaTree {

    @Override
    public Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> createTreeFeature(Random random) {
        return GaiaFeatures.Configured.aura_tree;
    }
}
