package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.BishopTransformer;
import androsa.gaiadimension.world.layer.oldgen.Context;

public enum AddIslandLayer implements BishopTransformer {
    INSTANCE;

    private int basebiome = GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest);

    @Override
    public int apply(Context context, int sw, int se, int ne, int nw, int center) {
        if (!isOcean(center) || isOcean(nw) && isOcean(ne) && isOcean(sw) && isOcean(se)) {
            if (!isOcean(center) && (isOcean(nw) || isOcean(sw) || isOcean(ne) || isOcean(se)) && context.nextRandom(5) == 0) {
                if (isOcean(nw)) {
                    return center == basebiome ? basebiome : nw;
                }

                if (isOcean(sw)) {
                    return center == basebiome ? basebiome : sw;
                }

                if (isOcean(ne)) {
                    return center == basebiome ? basebiome : ne;
                }

                if (isOcean(se)) {
                    return center == basebiome ? basebiome : se;
                }
            }

            return center;
        } else {
            int i = 1;
            int j = 1;
            if (!isOcean(nw) && context.nextRandom(i++) == 0) {
                j = nw;
            }

            if (!isOcean(ne) && context.nextRandom(i++) == 0) {
                j = ne;
            }

            if (!isOcean(sw) && context.nextRandom(i++) == 0) {
                j = sw;
            }

            if (!isOcean(se) && context.nextRandom(i++) == 0) {
                j = se;
            }

            if (context.nextRandom(3) == 0) {
                return j;
            } else {
                return j == basebiome ? basebiome : center;
            }
        }
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench) || biome == 0;
    }
}
