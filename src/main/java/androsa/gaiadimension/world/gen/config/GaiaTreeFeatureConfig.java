package androsa.gaiadimension.world.gen.config;

import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class GaiaTreeFeatureConfig extends BaseTreeFeatureConfig {

    public GaiaTreeFeatureConfig(BlockStateProvider trunk, BlockStateProvider leaves, List<TreeDecorator> decorators, int height) {
        super(trunk, leaves, decorators, height);
    }

    @Override
    protected GaiaTreeFeatureConfig setSapling(IPlantable sapling) {
        super.setSapling(sapling);
        return this;
    }

    public static <T> GaiaTreeFeatureConfig deserialize(Dynamic<T> data) {
        BaseTreeFeatureConfig config = BaseTreeFeatureConfig.deserialize(data);
        return new GaiaTreeFeatureConfig(config.trunkProvider, config.leavesProvider, config.decorators, config.baseHeight);
    }

    public static <T> GaiaTreeFeatureConfig deserializePinkAgate(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.pink_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeBlueAgate(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.blue_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeGreenAgate(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.green_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializePurpleAgate(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.purple_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeFossilized(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.fossilized_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeCorrupted(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.corrupted_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeBurnt(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.burnt_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeBurning(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.burning_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeAura(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.aura_sapling.get());
    }

    public static class Builder extends BaseTreeFeatureConfig.Builder {
        private List<TreeDecorator> decorators = ImmutableList.of();
        private int baseHeight;

        public Builder(BlockStateProvider trunk, BlockStateProvider leaves) {
            super(trunk, leaves);
        }

        public GaiaTreeFeatureConfig.Builder baseHeight(int height) {
            this.baseHeight = height;
            return this;
        }

        @Override
        public GaiaTreeFeatureConfig.Builder setSapling(IPlantable sapling) {
            super.setSapling(sapling);
            return this;
        }

        @Override
        public GaiaTreeFeatureConfig build() {
            return new GaiaTreeFeatureConfig(trunkProvider, leavesProvider, decorators, baseHeight);
        }
    }
}
