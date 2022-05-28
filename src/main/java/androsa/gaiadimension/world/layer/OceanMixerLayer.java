package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.Area;
import androsa.gaiadimension.world.layer.util.AreaTransformer2;
import androsa.gaiadimension.world.layer.util.Context;
import androsa.gaiadimension.world.layer.util.DimensionOffset0Transformer;

public enum OceanMixerLayer implements AreaTransformer2, DimensionOffset0Transformer {
    INSTANCE;

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
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench) || biome == 0;
    }
}
