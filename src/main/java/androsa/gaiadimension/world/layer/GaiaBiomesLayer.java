package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.C0Transformer;
import androsa.gaiadimension.world.layer.util.Context;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class GaiaBiomesLayer implements C0Transformer {

    private final HolderGetter<Biome> registry;
    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected List<ResourceKey<Biome>> commonBiomes = ImmutableList.of(
            ModBiomes.pink_agate_forest,
            ModBiomes.blue_agate_taiga,
            ModBiomes.green_agate_jungle,
            ModBiomes.crystal_plains,
            ModBiomes.fossil_woodland
    );
    protected List<ResourceKey<Biome>> uncommonBiomes = ImmutableList.of(
            ModBiomes.volcanic_lands,
            ModBiomes.static_wasteland,
            ModBiomes.salt_dunes,
            ModBiomes.smoldering_bog,
            ModBiomes.shining_grove,
            ModBiomes.mookaite_mesa
    );
    protected List<ResourceKey<Biome>> rareBiomes = ImmutableList.of(
            ModBiomes.purple_agate_swamp, //This will make the sacred biome less likely to generate
            ModBiomes.goldstone_lands, //It's basically the sacred biome turned corrupt, so there's that
            ModBiomes.mutant_agate_wildwood //A rare kind of Agate Forest with different Agate Trees
    );
    protected List<ResourceKey<Biome>> goldBiomes = ImmutableList.of(
            ModBiomes.golden_sands,
            ModBiomes.golden_marsh,
            ModBiomes.golden_hills,
            ModBiomes.golden_forest,
            ModBiomes.golden_plains
    );

    public GaiaBiomesLayer(HolderGetter<Biome> registry) {
        this.registry = registry;
    }

    @Override
    public int apply(Context context, int biome) {
        if (!isOcean(biome)) {
            if (isGold(biome)) {
                return GaiaLayerUtil.getBiomeId(goldBiomes.get(context.nextRandom(goldBiomes.size())), registry);
            } else {
                if (context.nextRandom(RARE_BIOME_CHANCE) == 0) {
                    //magic number!
                    return GaiaLayerUtil.getBiomeId(rareBiomes.get(context.nextRandom(rareBiomes.size())), registry);
                } else if (context.nextRandom(UNCOMMON_BIOME_CHANCE) == 0) {
                    //Well, it's no rare biome, but it will suffice
                    return GaiaLayerUtil.getBiomeId(uncommonBiomes.get(context.nextRandom(uncommonBiomes.size())), registry);
                } else {
                    //aww...
                    return GaiaLayerUtil.getBiomeId(commonBiomes.get(context.nextRandom(commonBiomes.size())), registry);
                }
            }
        }
        return biome;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir, registry) || biome == 0;
    }

    private boolean isGold(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_sands, registry) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_marsh, registry) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_hills, registry) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest, registry) ||
                biome == GaiaLayerUtil.getBiomeId(ModBiomes.golden_plains, registry);
    }
}
