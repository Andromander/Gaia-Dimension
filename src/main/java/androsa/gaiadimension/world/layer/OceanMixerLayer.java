package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.Area;
import androsa.gaiadimension.world.layer.oldgen.AreaTransformer2;
import androsa.gaiadimension.world.layer.oldgen.Context;
import androsa.gaiadimension.world.layer.oldgen.DimensionOffset0Transformer;

public enum OceanMixerLayer implements AreaTransformer2, DimensionOffset0Transformer {
    INSTANCE;

    @Override
    public int applyPixel(Context context, Area first, Area second, int x, int z) {
        int i = first.get(this.getParentX(x), this.getParentY(z));
        int j = second.get(this.getParentX(z), this.getParentY(z));
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
