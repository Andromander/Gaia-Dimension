package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.world.layer.util.BishopTransformer;
import androsa.gaiadimension.world.layer.util.Context;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;

public enum GoldIslandLayer implements BishopTransformer {
    INSTANCE;

    private HolderGetter<Biome> registry;

    public GoldIslandLayer setup(HolderGetter<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(Context context, int sw, int se, int ne, int nw, int center) {
        if (isOcean(sw) && isOcean(se) && isOcean(ne) && isOcean(nw) && isOcean(center)) {
            if (context.nextRandom(3) == 0) {
                return GaiaLayerUtil.getBiomeId(GaiaBiomes.golden_forest, registry);
            }
        }
        return center;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.mineral_reservoir, registry) || biome == 0;
    }
}
