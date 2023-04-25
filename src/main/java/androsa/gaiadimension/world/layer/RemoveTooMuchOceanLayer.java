package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;

public enum RemoveTooMuchOceanLayer implements CastleTransformer {
    INSTANCE;

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
        if (isOcean(center) && isOcean(north) && isOcean(east) && isOcean(west) && isOcean(south)) {
            int rand = context.nextRandom(2);
            return rand == 0 ? center : 1;
        }

        return 1;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == 0;
    }
}
