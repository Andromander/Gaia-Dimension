package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.BishopTransformer;
import androsa.gaiadimension.world.layer.util.Context;

public enum GoldIslandLayer implements BishopTransformer {
    INSTANCE;

    @Override
    public int apply(Context context, int sw, int se, int ne, int nw, int center) {
        if (isOcean(sw) && isOcean(se) && isOcean(ne) && isOcean(nw) && isOcean(center)) {
            if (context.nextRandom(5) == 0) {
                return GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest);
            }
        }
        return center;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench) || biome == 0;
    }
}
