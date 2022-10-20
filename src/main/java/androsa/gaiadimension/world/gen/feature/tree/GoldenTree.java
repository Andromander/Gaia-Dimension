package androsa.gaiadimension.world.gen.feature.tree;

import androsa.gaiadimension.registry.configurations.GaiaFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GoldenTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean flowers) {
        return random.nextInt(4) == 0 ? GaiaFeatures.Configured.big_golden_tree : GaiaFeatures.Configured.small_golden_tree;
    }
}
