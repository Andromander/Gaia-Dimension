package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public enum MineralRiverLayer implements CastleTransformer {

    INSTANCE;

    private HolderGetter<Biome> registry;

    private final List<ResourceKey<Biome>> agateBiomes = ImmutableList.of(
            ModBiomes.pink_agate_forest,
            ModBiomes.blue_agate_taiga,
            ModBiomes.green_agate_jungle,
            ModBiomes.purple_agate_swamp);
    private final List<ResourceKey<Biome>> omitBiomes = ImmutableList.of(
            ModBiomes.smoldering_bog,
            ModBiomes.golden_forest,
            ModBiomes.golden_plains,
            ModBiomes.golden_hills,
            ModBiomes.golden_sands,
            ModBiomes.golden_marsh,
            ModBiomes.mineral_reservoir
    );

    public MineralRiverLayer setup(HolderGetter<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(Context random, int north, int west, int south, int east, int center) {
        if (shouldRiver(center, west, south, east, north)) {
            return GaiaLayerUtil.getBiomeId(ModBiomes.mineral_river, this.registry);
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
        if (matchesOmit(id1) || matchesOmit(id2))
            return false;

        if (id1 == 0 || id2 == 0) {
            return false;
        }

        //Crystal Plains and Pink Agate Forest are too similar for rivers
        if (isMatch(id1, id2, GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest, this.registry), GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains, this.registry)))
            return false;

        //Mutated Agate Wildwoods should look like they were any Agate Forest, but with strange growth patterns
        if (matchesWildwood(id1, id2) || matchesWildwood(id2, id1))
            return false;

        return true;
    }

    private boolean matchesOmit(int id) {
        return omitBiomes.stream().map((key) -> GaiaLayerUtil.getBiomeId(key, registry)).toList().contains(id);
    }

    private boolean matchesWildwood(int id1, int id2) {
        return id1 == GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood, this.registry) && agateBiomes.stream().map((key) -> GaiaLayerUtil.getBiomeId(key, registry)).toList().contains(id2);
    }

    private boolean isMatch(int id1, int id2, int biome1, int biome2) {
        return (id1 == biome1 && id2 == biome2) || (id1 == biome2 && id2 == biome1);
    }
}
