package androsa.gaiadimension.world.gen.feature.tree;

import androsa.gaiadimension.registry.bootstrap.GaiaFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BurntAgateTree extends AbstractTreeGrower {

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers) {
        return GaiaFeatures.Configured.burnt_agate_tree;
    }
}
