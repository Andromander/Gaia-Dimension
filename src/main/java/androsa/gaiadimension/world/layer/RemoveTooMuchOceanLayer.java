package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;

public enum RemoveTooMuchOceanLayer implements CastleTransformer {
    INSTANCE;

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
        return isOcean(center) && isOcean(north) && isOcean(east) && isOcean(west) && isOcean(south) && context.nextRandom(7) == 0 ? center : 1;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench) || biome == 0;
    }
}
