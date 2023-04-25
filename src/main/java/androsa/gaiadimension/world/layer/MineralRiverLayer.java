package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;
import com.google.common.collect.ImmutableList;

import java.util.List;

public enum MineralRiverLayer implements CastleTransformer {

    INSTANCE;

    private List<Integer> agateBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.blue_agate_taiga),
            GaiaLayerUtil.getBiomeId(ModBiomes.green_agate_jungle),
            GaiaLayerUtil.getBiomeId(ModBiomes.purple_agate_swamp));
    private List<Integer> omitBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_sands),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_marsh),
            GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir),
            0 // We don't see oceans, have this here, too
    );

    @Override
    public int apply(Context random, int north, int west, int south, int east, int center) {
        if (shouldRiver(center, west, south, east, north)) {
            return GaiaLayerUtil.getBiomeId(ModBiomes.mineral_river);
        } else {
            return -1;
        }
    }

    boolean shouldRiver(int mid, int left, int down, int right, int up) {
        return shouldRiver(mid, left) || shouldRiver(mid, right) || shouldRiver(mid, down) || shouldRiver(mid, up);
    }

    boolean shouldRiver(int id1, int id2) {
        if (id1 == id2)
            return false;
        if (id1 == -id2)
            return false;

        //Any biomes here should never make a river
        if (omitBiomes.contains(id1) || omitBiomes.contains(id2))
            return false;

        //Crystal Plains and Pink Agate Forest are too similar for rivers
        if (isMatch(id1, id2, GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest), GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains)))
            return false;

        //Mutated Agate Wildwoods should look like they were any Agate Forest, but with strange growth patterns
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood) && agateBiomes.contains(id2)) || (agateBiomes.contains(id1) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood)))
            return false;

        return true;
    }

    private boolean isMatch(int id1, int id2, int biome1, int biome2) {
        return (id1 == biome1 && id2 == biome2) || (id1 == biome2 && id2 == biome1);
    }
}
