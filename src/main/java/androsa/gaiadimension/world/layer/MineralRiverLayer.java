package androsa.gaiadimension.world.layer;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import java.util.List;

public enum MineralRiverLayer implements ICastleTransformer {

    INSTANCE;

    private List<Integer> agateBiomes = ImmutableList.of(
            GaiaLayerUtil.PINK_FOREST.getAsInt(),
            GaiaLayerUtil.BLUE_FOREST.getAsInt(),
            GaiaLayerUtil.GREEN_FOREST.getAsInt(),
            GaiaLayerUtil.PURPLE_FOREST.getAsInt());
    private List<Integer> dryBiomes = ImmutableList.of(
            GaiaLayerUtil.BOG.getAsInt(),
            GaiaLayerUtil.STATIC.getAsInt(),
            GaiaLayerUtil.VOLCANIC.getAsInt());

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
        if (id1 == id2)
            return false;
        if (id1 == -id2)
            return false;

        //Biomes not allowed rivers are omitted entirely
        if (dryBiomes.contains(id1) || dryBiomes.contains(id2)) {
            return false;
        }

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
        if ((id1 == GaiaLayerUtil.WILDWOOD.getAsInt() && agateBiomes.contains(id2)) || (agateBiomes.contains(id1) && id2 == GaiaLayerUtil.WILDWOOD.getAsInt()))
            return false;

        return true;
    }
}
