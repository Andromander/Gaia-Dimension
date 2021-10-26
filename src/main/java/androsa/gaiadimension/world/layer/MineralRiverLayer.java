package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.CastleTransformer;

import java.util.List;

public enum MineralRiverLayer implements CastleTransformer {

    INSTANCE;

    private List<Integer> agateBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.blue_agate_taiga),
            GaiaLayerUtil.getBiomeId(ModBiomes.green_agate_jungle),
            GaiaLayerUtil.getBiomeId(ModBiomes.purple_agate_swamp));
    private List<Integer> dryBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands));

    MineralRiverLayer() { }

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

        //Biomes not allowed rivers are omitted entirely
        if (dryBiomes.contains(id1) || dryBiomes.contains(id2)) {
            return false;
        }

        //Crystal Plains and Pink Agate Forest are too similar for rivers
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains)) || (id1 == GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest)))
            return false;

        //If a reservoir gens near another reservoir, remove the river because it would look goofy
        if (id1 == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir))
            return false;

        //Salt Dunes and Mineral Reservoirs are similar, no need for river
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.salt_dunes) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir)) || (id1 == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.salt_dunes)))
            return false;

        //Mutated Agate Wildwoods should look like they were any Agate Forest, but with strange growth patterns
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood) && agateBiomes.contains(id2)) || (agateBiomes.contains(id1) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood)))
            return false;

        return true;
    }
}
