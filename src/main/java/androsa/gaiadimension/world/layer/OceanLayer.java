package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.AreaTransformer0;
import androsa.gaiadimension.world.layer.util.Context;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;

public enum OceanLayer implements AreaTransformer0 {
    INSTANCE;

    private HolderGetter<Biome> registry;

    public OceanLayer setup(HolderGetter<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int applyPixel(Context context, int x, int z) {
        return GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir, registry);
    }
}
