package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.CastleTransformer;
import androsa.gaiadimension.world.layer.oldgen.Context;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

public enum CoastLayer implements CastleTransformer {
    INSTANCE;

    private final IntSet torumaline = new IntOpenHashSet(new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.igneous_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.hotspot),
            GaiaLayerUtil.getBiomeId(ModBiomes.wasteland_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog)
    });

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
        if (torumaline.contains(center)) {
            if (isOcean(north) || isOcean(east) || isOcean(south) || isOcean(west)) {
                return GaiaLayerUtil.getBiomeId(ModBiomes.tourmaline_coast);
            }
        } else if (!isGold(center) && center != GaiaLayerUtil.getBiomeId(ModBiomes.mineral_river)) {
            if (!isOcean(center) && (isOcean(north) || isOcean(east) || isOcean(south) || isOcean(west))) {
                return GaiaLayerUtil.getBiomeId(ModBiomes.salty_coast);
            }
        }
        return center;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench);
    }

    private boolean isGold(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_sands) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_marsh) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_hills) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_plains);
    }
}
