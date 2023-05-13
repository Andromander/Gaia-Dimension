package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.world.layer.util.BishopTransformer;
import androsa.gaiadimension.world.layer.util.Context;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;

public enum AddIslandLayer implements BishopTransformer {
    INSTANCE;

    private HolderGetter<Biome> registry;

    public AddIslandLayer setup(HolderGetter<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(Context context, int sw, int se, int ne, int nw, int center) {
        int basebiome = GaiaLayerUtil.getBiomeId(GaiaBiomes.pink_agate_forest, registry);
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
        return biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.mineral_reservoir, registry) || biome == 0;
    }
}
