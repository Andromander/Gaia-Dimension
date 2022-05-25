package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

public enum CoastLayer implements CastleTransformer {
    INSTANCE;

    private final IntSet tourmaline = new IntOpenHashSet(new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.igneous_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.hotspot),
            GaiaLayerUtil.getBiomeId(ModBiomes.wasteland_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog)
    });

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
//        if (torumaline.contains(center)) {
//            if (isOcean(north) || isOcean(east) || isOcean(south) || isOcean(west)) {
//                return GaiaLayerUtil.getBiomeId(ModBiomes.tourmaline_coast);
//            }
//        } else if (!isGold(center) && center != GaiaLayerUtil.getBiomeId(ModBiomes.mineral_river)) {
//            if (!isOcean(center) && (isOcean(north) || isOcean(east) || isOcean(south) || isOcean(west))) {
//                return GaiaLayerUtil.getBiomeId(ModBiomes.salty_coast);
//            }
//        }
//        return center;

        if (shouldTourmaline(center, west, south, east, north)) {
            return GaiaLayerUtil.getBiomeId(ModBiomes.tourmaline_coast);
        } else if (shouldSalt(center, west, south, east, north)) {
            return GaiaLayerUtil.getBiomeId(ModBiomes.salty_coast);
        } else {
            return center;
        }
    }

    boolean shouldTourmaline(int mid, int left, int down, int right, int up) {
        return tourmaline(mid, left) || tourmaline(mid, right) || tourmaline(mid, down) || tourmaline(mid, up);
    }

    boolean shouldSalt(int mid, int left, int down, int right, int up) {
        return salt(mid, left) || salt(mid, right) || salt(mid, down) || salt(mid, up);
    }

    boolean tourmaline(int mid, int offset) {
        if (mid == offset)
            return false;
        if (mid == -offset)
            return false;

        return (isOcean(mid) && tourmaline.contains(offset)) || (tourmaline.contains(mid) && isOcean(offset));
    }

    boolean salt(int mid, int offset) {
        if (mid == offset)
            return false;
        if (mid == -offset)
            return false;

        //No coast for Gold biomes
        if (isGold(mid) || isGold(offset))
            return false;

        //Don't make a coast between oceans
        if (isOcean(mid) && isOcean(offset))
            return false;

        //Also don't make a coast when there is no ocean
        if (!isOcean(mid) && !isOcean(offset))
            return false;

        // Ignore tourmaline coast biomes
        if (tourmaline.contains(mid) || tourmaline.contains(offset))
            return false;

        //failsafe to prevent tourmaline coast from having salty coasts
        if (mid == GaiaLayerUtil.getBiomeId(ModBiomes.tourmaline_coast) || offset == GaiaLayerUtil.getBiomeId(ModBiomes.tourmaline_coast))
            return false;

        return true;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) || biome == GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench) || biome == 0;
    }

    private boolean isGold(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_sands) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_marsh) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_hills) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_plains);
    }
}
