package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.CastleTransformer;
import androsa.gaiadimension.world.layer.oldgen.Context;
import com.google.common.collect.ImmutableList;

import java.util.List;

public enum MineralRiverLayer implements CastleTransformer {

    INSTANCE;

    private List<Integer> seaBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir),
            GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench));
    private List<Integer> agateBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.blue_agate_taiga),
            GaiaLayerUtil.getBiomeId(ModBiomes.green_agate_jungle),
            GaiaLayerUtil.getBiomeId(ModBiomes.purple_agate_swamp));
    private List<Integer> omitBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.wasteland_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.igneous_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.hotspot));
    private List<Integer> goldBiomes = ImmutableList.of(
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_sands),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_marsh));

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
        if (omitBiomes.contains(id1) || omitBiomes.contains(id2)) {
            return false;
        }

        //Gold biomes are considered identical
        if (goldBiomes.contains(id1) && goldBiomes.contains(id2)) {
            return false;
        }

        //Oceans should not make a river
        if (seaBiomes.contains(id1) || seaBiomes.contains(id2))
            return false;

        //Crystal Plains and Pink Agate Forest are too similar for rivers
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains)) || (id1 == GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest)))
            return false;

        //Mutated Agate Wildwoods should look like they were any Agate Forest, but with strange growth patterns
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood) && agateBiomes.contains(id2)) || (agateBiomes.contains(id1) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood)))
            return false;

        //Central biomes do not river with Surrounding biomes
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.igneous_plains)) || (id2 == GaiaLayerUtil.getBiomeId(ModBiomes.igneous_plains) && id1 == GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands))) {
            return false;
        }
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.wasteland_hills)) || (id2 == GaiaLayerUtil.getBiomeId(ModBiomes.wasteland_hills) && id1 == GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland))) {
            return false;
        }
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.goldstone_lands) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.weirded_goldstone_lands)) || (id2 == GaiaLayerUtil.getBiomeId(ModBiomes.weirded_goldstone_lands) && id1 == GaiaLayerUtil.getBiomeId(ModBiomes.goldstone_lands))) {
            return false;
        }
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.crystal_salt_dunes) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.salt_dunes)) || (id2 == GaiaLayerUtil.getBiomeId(ModBiomes.salt_dunes) && id1 == GaiaLayerUtil.getBiomeId(ModBiomes.crystal_salt_dunes))) {
            return false;
        }
        if ((id1 == GaiaLayerUtil.getBiomeId(ModBiomes.hotspot) && id2 == GaiaLayerUtil.getBiomeId(ModBiomes.prismatic_steppe)) || (id2 == GaiaLayerUtil.getBiomeId(ModBiomes.prismatic_steppe) && id1 == GaiaLayerUtil.getBiomeId(ModBiomes.hotspot))) {
            return false;
        }

        return true;
    }
}
