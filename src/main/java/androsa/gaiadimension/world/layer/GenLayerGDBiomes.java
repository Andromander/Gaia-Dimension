package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerGDBiomes extends GenLayer {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected Biome commonBiomes[] = (new Biome[]{
            GDBiomes.pinkAgateForest,
            GDBiomes.blueAgateForest,
            GDBiomes.greenAgateForest,
            GDBiomes.crystalPlains
    });
    protected Biome uncommonBiomes[] = (new Biome[]{
            GDBiomes.fossilForest,
            GDBiomes.mineralReservoir,
            GDBiomes.volcaniclands,
            GDBiomes.staticWasteland
    });
    protected Biome rareBiomes[] = (new Biome[]{
            GDBiomes.purpleAgateForest, //This wil make the sacred biome less likely to generate
            GDBiomes.goldstonelands //It's basically the sacred biome turned corrupt, so there's that. //TODO: Should it be changed to uncommon to better reflect the spread?
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
