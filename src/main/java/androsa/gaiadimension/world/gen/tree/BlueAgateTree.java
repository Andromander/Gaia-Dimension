package androsa.gaiadimension.world.gen.tree;

import androsa.gaiadimension.world.gen.feature.BlueAgateTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BlueAgateTree extends Tree {

    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return new BlueAgateTreeFeature(NoFeatureConfig::deserialize, true);
    }
}
