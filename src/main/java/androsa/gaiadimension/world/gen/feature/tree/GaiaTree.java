package androsa.gaiadimension.world.gen.feature.tree;

import androsa.gaiadimension.world.gen.feature.config.GaiaTreeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class GaiaTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<ConfiguredFeature<TreeConfiguration, ?>> getConfiguredFeature(Random random, boolean b) {
        return null;
    }

    public abstract Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> createTreeFeature(Random rand);

    @Override
    public boolean growTree(ServerLevel world, ChunkGenerator generator, BlockPos pos, BlockState state, Random rand) {
        Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> feature = this.createTreeFeature(rand);
        if (feature == null) {
            return false;
        } else {
            ConfiguredFeature<GaiaTreeFeatureConfig, ?> tree = feature.value();
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
            if (tree.place(world, generator, rand, pos)) {
                return true;
            } else {
                world.setBlock(pos, state, 4);
                return false;
            }
        }
    }
}
