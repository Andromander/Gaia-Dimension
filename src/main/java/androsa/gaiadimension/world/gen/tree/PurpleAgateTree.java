package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.registry.configurations.GaiaConfiguredFeatures;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class PurpleAgateTree extends GaiaTree {

    @Override
    public Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> createTreeFeature(Random rand) {
        return GaiaConfiguredFeatures.purple_agate_tree;
    }
}
