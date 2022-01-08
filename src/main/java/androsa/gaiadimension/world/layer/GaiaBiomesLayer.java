package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.AreaTransformer0;
import androsa.gaiadimension.world.layer.oldgen.Context;

public class GaiaBiomesLayer implements AreaTransformer0 {

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
            GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir),
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.salt_dunes),
            GaiaLayerUtil.getBiomeId(ModBiomes.smoldering_bog),
            GaiaLayerUtil.getBiomeId(ModBiomes.shining_grove)
    });
    protected int[] rareBiomes = (new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.purple_agate_swamp), //This wil make the sacred biome less likely to generate
            GaiaLayerUtil.getBiomeId(ModBiomes.goldstone_lands), //It's basically the sacred biome turned corrupt, so there's that
            GaiaLayerUtil.getBiomeId(ModBiomes.mutant_agate_wildwood) //A rare kind of Agate Forest with different Agate Trees
    });

    public GaiaBiomesLayer() { }

    @Override
    public int applyPixel(Context iNoiseRandom, int x, int z) {
        if (iNoiseRandom.nextRandom(RARE_BIOME_CHANCE) == 0) {
            //magic number!
            return rareBiomes[iNoiseRandom.nextRandom(rareBiomes.length)];
        } else if (iNoiseRandom.nextRandom(UNCOMMON_BIOME_CHANCE) == 0) {
            //Well, it's no rare biome, but it will suffice
            return uncommonBiomes[iNoiseRandom.nextRandom(uncommonBiomes.length)];
        } else {
            //aww...
            return commonBiomes[iNoiseRandom.nextRandom(commonBiomes.length)];
        }
    }
}
