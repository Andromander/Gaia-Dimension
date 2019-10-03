package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class GaiaBiomesLayer implements IAreaTransformer0 {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected LazyInt[] commonBiomes = new LazyInt[]{
            GaiaLayerUtil.lazyId(ModBiomes.pink_agate_forest),
            GaiaLayerUtil.lazyId(ModBiomes.blue_agate_taiga),
            GaiaLayerUtil.lazyId(ModBiomes.green_agate_jungle),
            GaiaLayerUtil.lazyId(ModBiomes.crystal_plains),
            GaiaLayerUtil.lazyId(ModBiomes.fossil_woodland),
    };
    protected LazyInt[] uncommonBiomes = (new LazyInt[]{
            GaiaLayerUtil.lazyId(ModBiomes.mineral_reservoir),
            GaiaLayerUtil.lazyId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.lazyId(ModBiomes.static_wasteland),
            GaiaLayerUtil.lazyId(ModBiomes.salt_dunes),
            GaiaLayerUtil.lazyId(ModBiomes.smoldering_bog),
            GaiaLayerUtil.lazyId(ModBiomes.shining_grove)
    });
    protected LazyInt[] rareBiomes = (new LazyInt[]{
            GaiaLayerUtil.lazyId(ModBiomes.purple_agate_swamp), //This wil make the sacred biome less likely to generate
            GaiaLayerUtil.lazyId(ModBiomes.goldstone_lands), //It's basically the sacred biome turned corrupt, so there's that
            GaiaLayerUtil.lazyId(ModBiomes.mutant_agate_wildwood) //A rare kind of Agate Forest with different Agate Trees
    });

    public GaiaBiomesLayer() { }

    @Override
    public int apply(INoiseRandom iNoiseRandom, int rand1, int rand2) {
        if (iNoiseRandom.random(RARE_BIOME_CHANCE) == 0) {
            //magic number!
            return rareBiomes[iNoiseRandom.random(rareBiomes.length)].getAsInt();
        } else if (iNoiseRandom.random(UNCOMMON_BIOME_CHANCE) == 0) {
            //Well, it's no rare biome, but it will suffice
            return uncommonBiomes[iNoiseRandom.random(uncommonBiomes.length)].getAsInt();
        } else {
            //aww...
            return commonBiomes[iNoiseRandom.random(commonBiomes.length)].getAsInt();
        }
    }
}
