package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.GDBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerGDBiomes extends GenLayer {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected Biome commonBiomes[] = (new Biome[]{
            GDBiomes.pink_agate_forest,
            GDBiomes.blue_agate_taiga,
            GDBiomes.green_agate_jungle,
            GDBiomes.crystal_plains,
            GDBiomes.fossil_woodland,
    });
    protected Biome uncommonBiomes[] = (new Biome[]{
            GDBiomes.mineral_reservoir,
            GDBiomes.volcaniclands,
            GDBiomes.static_wasteland,
            GDBiomes.salt_dunes,
            GDBiomes.smoldering_bog,
            GDBiomes.shining_grove
    });
    protected Biome rareBiomes[] = (new Biome[]{
            GDBiomes.purple_agate_swamp, //This wil make the sacred biome less likely to generate
            GDBiomes.goldstonelands, //It's basically the sacred biome turned corrupt, so there's that
            GDBiomes.mutant_agate_wildwood //A rare kind of Agate Forest with different Agate Trees
    });

    public GenLayerGDBiomes(long l, GenLayer genlayer) {
        super(l);
        parent = genlayer;
    }

    public GenLayerGDBiomes(long l) {
        super(l);
    }

    @Override
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
    }
}
