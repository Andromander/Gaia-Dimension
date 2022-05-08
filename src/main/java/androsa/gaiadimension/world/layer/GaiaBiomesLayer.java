package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.C0Transformer;
import androsa.gaiadimension.world.layer.util.Context;

public class GaiaBiomesLayer implements C0Transformer {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected int[] commonBiomes = new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.pink_agate_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.blue_agate_taiga),
            GaiaLayerUtil.getBiomeId(ModBiomes.green_agate_jungle),
            GaiaLayerUtil.getBiomeId(ModBiomes.crystal_plains),
            GaiaLayerUtil.getBiomeId(ModBiomes.fossil_woodland),
    };
    protected int[] uncommonBiomes = (new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog),
            GaiaLayerUtil.getBiomeId(ModBiomes.shining_grove),
            GaiaLayerUtil.getBiomeId(ModBiomes.mookaite_mesa)
    });
    protected int[] rareBiomes = (new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.purple_agate_swamp), //This will make the sacred biome less likely to generate
            GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood) //A rare kind of Agate Forest with different Agate Trees
    });
    protected int[] goldBiomes = new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_sands),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_marsh),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_hills),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_forest),
            GaiaLayerUtil.getBiomeId(ModBiomes.golden_plains)
    };

    @Override
    public int apply(Context context, int biome) {
        if (!isOcean(biome)) {
            if (isGold(biome)) {
                return goldBiomes[context.nextRandom(goldBiomes.length)];
            } else {
                if (context.nextRandom(RARE_BIOME_CHANCE) == 0) {
                    //magic number!
                    return rareBiomes[context.nextRandom(rareBiomes.length)];
                } else if (context.nextRandom(UNCOMMON_BIOME_CHANCE) == 0) {
                    //Well, it's no rare biome, but it will suffice
                    return uncommonBiomes[context.nextRandom(uncommonBiomes.length)];
                } else {
                    //aww...
                    return commonBiomes[context.nextRandom(commonBiomes.length)];
                }
            }
        }
        return biome;
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
