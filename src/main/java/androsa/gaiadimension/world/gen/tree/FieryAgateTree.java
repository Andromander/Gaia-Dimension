package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.world.gen.feature.FieryAgateTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FieryAgateTree extends Tree {

    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return new FieryAgateTreeFeature(NoFeatureConfig::deserialize, true);
    }
}
