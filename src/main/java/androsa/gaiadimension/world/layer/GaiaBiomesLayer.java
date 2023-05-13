package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
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
            GaiaBiomes.pink_agate_forest,
            GaiaBiomes.blue_agate_taiga,
            GaiaBiomes.green_agate_jungle,
            GaiaBiomes.crystal_plains,
            GaiaBiomes.fossil_woodland
    );
    protected List<ResourceKey<Biome>> uncommonBiomes = ImmutableList.of(
            GaiaBiomes.volcanic_lands,
            GaiaBiomes.static_wasteland,
            GaiaBiomes.salt_dunes,
            GaiaBiomes.smoldering_bog,
            GaiaBiomes.shining_grove,
            GaiaBiomes.mookaite_mesa
    );
    protected List<ResourceKey<Biome>> rareBiomes = ImmutableList.of(
            GaiaBiomes.purple_agate_swamp, //This will make the sacred biome less likely to generate
            GaiaBiomes.goldstone_lands, //It's basically the sacred biome turned corrupt, so there's that
            GaiaBiomes.mutant_agate_wildwood //A rare kind of Agate Forest with different Agate Trees
    );
    protected List<ResourceKey<Biome>> goldBiomes = ImmutableList.of(
            GaiaBiomes.golden_sands,
            GaiaBiomes.golden_marsh,
            GaiaBiomes.golden_hills,
            GaiaBiomes.golden_forest,
            GaiaBiomes.golden_plains
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
        return biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.mineral_reservoir, registry) || biome == 0;
    }

    private boolean isGold(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.golden_sands, registry) ||
                biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.golden_marsh, registry) ||
                biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.golden_hills, registry) ||
                biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.golden_forest, registry) ||
                biome == GaiaLayerUtil.getBiomeId(GaiaBiomes.golden_plains, registry);
    }
}
