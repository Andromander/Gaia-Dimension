package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.Area;
import androsa.gaiadimension.world.layer.util.AreaTransformer2;
import androsa.gaiadimension.world.layer.util.Context;
import androsa.gaiadimension.world.layer.util.DimensionOffset0Transformer;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;

public enum OceanMixerLayer implements AreaTransformer2, DimensionOffset0Transformer {
    INSTANCE;

    private HolderGetter<Biome> registry;

    public OceanMixerLayer setup(HolderGetter<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int applyPixel(Context context, Area first, Area second, int x, int z) {
        int i = first.get(this.getParentX(x), this.getParentY(z));
        int j = second.get(this.getParentX(x), this.getParentY(z));
        if (!isOcean(i)) {
            return i;
        } else {
            return j;
        }
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir, registry) || biome == 0;
    }
}
