package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.biomes.IDryBiome;
import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum MineralRiverLayer implements ICastleTransformer {

    INSTANCE;

    MineralRiverLayer() { }

    @Override
    public int apply(INoiseRandom random, int north, int west, int south, int east, int center) {
        if (shouldRiver(center, west, south, east, north)) {
            return Registry.BIOME.getId(ModBiomes.mineral_river);
        } else {
            return -1;
        }
    }

    boolean shouldRiver(int mid, int left, int down, int right, int up) {
        return shouldRiver(mid, left) || shouldRiver(mid, right) || shouldRiver(mid, down) || shouldRiver(mid, up);
    }

    boolean shouldRiver(int id1, int id2) {
        Biome biome1 = Registry.BIOME.getByValue(id1);
        Biome biome2 = Registry.BIOME.getByValue(id2);

        if (id1 == id2)
            return false;
        if (id1 == -id2)
            return false;

        //Biomes not allowed rivers are omitted entirely
        if (biome1 instanceof IDryBiome || biome2 instanceof IDryBiome)
            return false;

        //Crystal Plains and Pink Agate Forest are too similar for rivers
        if (biome1 == ModBiomes.pink_agate_forest && biome2 == ModBiomes.crystal_plains)
            return false;
        if (biome1 == ModBiomes.crystal_plains && biome2 == ModBiomes.pink_agate_forest)
            return false;

        //If a reservoir gens near another reservoir, remove the river because it would look goofy
        if (biome1 == ModBiomes.mineral_reservoir && biome2 == ModBiomes.mineral_reservoir)
            return false;

        //Salt Dunes and Mineral Reservoirs are similar, no need for river
        if (biome1 == ModBiomes.salt_dunes && biome2 == ModBiomes.mineral_reservoir)
            return false;
        if (biome1 == ModBiomes.mineral_reservoir && biome2 == ModBiomes.salt_dunes)
            return false;

        //Mutated Agate Wildwoods should look like they were any Agate Forest, but with strange growth patterns
        if (biome1 == ModBiomes.mutant_agate_wildwood && biome2 == ModBiomes.pink_agate_forest)
            return false;
        if (biome1 == ModBiomes.mutant_agate_wildwood && biome2 == ModBiomes.blue_agate_taiga)
            return false;
        if (biome1 == ModBiomes.mutant_agate_wildwood && biome2 == ModBiomes.green_agate_jungle)
            return false;
        if (biome1 == ModBiomes.mutant_agate_wildwood && biome2 == ModBiomes.purple_agate_swamp)
            return false;
        if (biome1 == ModBiomes.pink_agate_forest && biome2 == ModBiomes.mutant_agate_wildwood)
            return false;
        if (biome1 == ModBiomes.blue_agate_taiga && biome2 == ModBiomes.mutant_agate_wildwood)
            return false;
        if (biome1 == ModBiomes.green_agate_jungle && biome2 == ModBiomes.mutant_agate_wildwood)
            return false;
        if (biome1 == ModBiomes.purple_agate_swamp && biome2 == ModBiomes.mutant_agate_wildwood)
            return false;

        return true;
    }
}
