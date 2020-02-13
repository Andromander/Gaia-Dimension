package androsa.gaiadimension.world.gen.config;

import androsa.gaiadimension.registry.ModBlocks;
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
        BaseTreeFeatureConfig basetreefeatureconfig = BaseTreeFeatureConfig.deserialize(data);
        return new GaiaTreeFeatureConfig(basetreefeatureconfig.trunkProvider, basetreefeatureconfig.leavesProvider, basetreefeatureconfig.decorators, basetreefeatureconfig.baseHeight);
    }

    public static <T> GaiaTreeFeatureConfig deserializePinkAgate(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.pink_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeBlueAgate(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.blue_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeGreenAgate(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.green_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializePurpleAgate(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.purple_agate_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeFossilized(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.fossilized_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeCorrupted(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.corrupted_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeBurnt(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.burnt_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeBurning(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.burning_sapling.get());
    }

    public static <T> GaiaTreeFeatureConfig deserializeAura(Dynamic<T> data) {
        return deserialize(data).setSapling((IPlantable) ModBlocks.aura_sapling.get());
    }
}
