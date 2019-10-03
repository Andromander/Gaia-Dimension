package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.biomes.IDryBiome;
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
            return GaiaLayerUtil.RIVER.getAsInt();
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
        if ((id1 == GaiaLayerUtil.PINK_FOREST.getAsInt() && id2 == GaiaLayerUtil.PLAINS.getAsInt()) || (id1 == GaiaLayerUtil.PLAINS.getAsInt() && id2 == GaiaLayerUtil.PINK_FOREST.getAsInt()))
            return false;

        //If a reservoir gens near another reservoir, remove the river because it would look goofy
        if (id1 == GaiaLayerUtil.RESERVOIR.getAsInt() && id2 == GaiaLayerUtil.RESERVOIR.getAsInt())
            return false;

        //Salt Dunes and Mineral Reservoirs are similar, no need for river
        if ((id1 == GaiaLayerUtil.DUNES.getAsInt() && id2 == GaiaLayerUtil.RESERVOIR.getAsInt()) || (id1 == GaiaLayerUtil.RESERVOIR.getAsInt() && id2 == GaiaLayerUtil.DUNES.getAsInt()))
            return false;

        //Mutated Agate Wildwoods should look like they were any Agate Forest, but with strange growth patterns
        if ((id1 == GaiaLayerUtil.WILDWOOD.getAsInt() && id2 == GaiaLayerUtil.PINK_FOREST.getAsInt()) || (id1 == GaiaLayerUtil.PINK_FOREST.getAsInt() && id2 == GaiaLayerUtil.WILDWOOD.getAsInt()))
            return false;
        if ((id1 == GaiaLayerUtil.WILDWOOD.getAsInt() && id2 == GaiaLayerUtil.BLUE_FOREST.getAsInt()) || (id1 == GaiaLayerUtil.BLUE_FOREST.getAsInt() && id2 == GaiaLayerUtil.WILDWOOD.getAsInt()))
            return false;
        if ((id1 == GaiaLayerUtil.WILDWOOD.getAsInt() && id2 == GaiaLayerUtil.GREEN_FOREST.getAsInt()) || (id1 == GaiaLayerUtil.GREEN_FOREST.getAsInt() && id2 == GaiaLayerUtil.WILDWOOD.getAsInt()))
            return false;
        if ((id1 == GaiaLayerUtil.WILDWOOD.getAsInt() && id2 == GaiaLayerUtil.PURPLE_FOREST.getAsInt()) || (id1 == GaiaLayerUtil.PURPLE_FOREST.getAsInt() && id2 == GaiaLayerUtil.WILDWOOD.getAsInt()))
            return false;

        return true;
    }
}
