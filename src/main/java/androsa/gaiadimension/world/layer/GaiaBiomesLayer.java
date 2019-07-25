package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class GaiaBiomesLayer implements IC0Transformer {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected Biome commonBiomes[] = (new Biome[]{
            ModBiomes.pink_agate_forest,
            ModBiomes.blue_agate_taiga,
            ModBiomes.green_agate_jungle,
            ModBiomes.crystal_plains,
            ModBiomes.fossil_woodland,
    });
    protected Biome uncommonBiomes[] = (new Biome[]{
            ModBiomes.mineral_reservoir,
            ModBiomes.volcanic_lands,
            ModBiomes.static_wasteland,
            ModBiomes.salt_dunes,
            ModBiomes.smoldering_bog,
            ModBiomes.shining_grove
    });
    protected Biome rareBiomes[] = (new Biome[]{
            ModBiomes.purple_agate_swamp, //This wil make the sacred biome less likely to generate
            ModBiomes.goldstone_lands, //It's basically the sacred biome turned corrupt, so there's that
            ModBiomes.mutant_agate_wildwood //A rare kind of Agate Forest with different Agate Trees
    });

    public GaiaBiomesLayer() { }

    //FIXME: lmao idk if this will work
    /*
    Doctor! Are you sure this will work?
    Haha, I HAVE NO IDEA!
     */
    @Override
    public int apply(INoiseRandom iNoiseRandom, int value) {
        if (iNoiseRandom.random(RARE_BIOME_CHANCE) == 0) {
            //magic number!
            return Registry.BIOME.getId(rareBiomes[iNoiseRandom.random(rareBiomes.length)]);
        } else if (iNoiseRandom.random(UNCOMMON_BIOME_CHANCE) == 0) {
            //Well, it's no rare biome, but it will suffice
            return Registry.BIOME.getId(uncommonBiomes[iNoiseRandom.random(uncommonBiomes.length)]);
        } else {
            //aww...
            return Registry.BIOME.getId(commonBiomes[iNoiseRandom.random(commonBiomes.length)]);
        }
    }

    /*@Override
    public int[] getInts(int x, int z, int width, int depth) {
        int dest[] = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                initChunkSeed(dx + x, dz + z);
                if (nextInt(RARE_BIOME_CHANCE) == 0) {
                    //magic number!
                    dest[dx + dz * width] = Biome.getIdForBiome(rareBiomes[nextInt(rareBiomes.length)]);
                } else if (nextInt(UNCOMMON_BIOME_CHANCE) == 0) {
                    //Well, it's no rare biome, but it will suffice
                    dest[dx + dz * width] = Biome.getIdForBiome(uncommonBiomes[nextInt(uncommonBiomes.length)]);
                } else {
                    //aww...
                    dest[dx + dz * width] = Biome.getIdForBiome(commonBiomes[nextInt(commonBiomes.length)]);
                    }
                }
            }

        return dest;
    }*/
}
