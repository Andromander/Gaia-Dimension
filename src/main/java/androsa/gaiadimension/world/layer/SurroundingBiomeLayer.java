package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;

public enum SurroundingBiomeLayer implements CastleTransformer {
    INSTANCE;

    private final int[] central = new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.crystal_salt_dunes),
            GaiaLayerUtil.getBiomeId(ModBiomes.goldstone_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.hotspot)
    };
    private final int[] surrounding = new int[] {
            GaiaLayerUtil.getBiomeId(ModBiomes.igneous_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.wasteland_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.salt_dunes),
            GaiaLayerUtil.getBiomeId(ModBiomes.weirded_goldstone_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.prismatic_steppe)
    };

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
        for (int i = 0; i < central.length; i++) {
            if (isCentral(central[i], center, east, west, north, south)) {
                return surrounding[i];
            }
        }
        return center;
    }

    private boolean isCentral(int biome, int center, int east, int west, int north, int south) {
        return center != biome && (east == biome || west == biome || north == biome || south == biome);
    }
}
