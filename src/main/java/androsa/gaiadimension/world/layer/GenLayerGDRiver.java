package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerGDRiver extends GenLayer {

    public GenLayerGDRiver(long l, GenLayer genlayer) {
        super(l);
        super.parent = genlayer;
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int nx = x - 1;
        int nz = z - 1;
        int nwidth = width + 2;
        int ndepth = depth + 2;
        int input[] = parent.getInts(nx, nz, nwidth, ndepth);
        int output[] = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                int left = input[dx + 0 + (dz + 1) * nwidth];
                int right = input[dx + 2 + (dz + 1) * nwidth];
                int down = input[dx + 1 + (dz + 0) * nwidth];
                int up = input[dx + 1 + (dz + 2) * nwidth];
                int mid = input[dx + 1 + (dz + 1) * nwidth];

                if (shouldRiver(mid, left, down, right, up)) {
                    output[dx + dz * width] = Biome.getIdForBiome(GDBiomes.mineralRiver);
                } else {
                    output[dx + dz * width] = -1;
                }
            }
        }

        return output;
    }

    boolean shouldRiver(int mid, int left, int down, int right, int up) {
        if (shouldRiver(mid, left)) {
            return true;
        } else if (shouldRiver(mid, right)) {
            return true;
        } else if (shouldRiver(mid, down)) {
            return true;
        } else if (shouldRiver(mid, up)) {
            return true;
        } else {
            return false;
        }
    }

    boolean shouldRiver(int biome1, int biome2) {
        if (biome1 == biome2) {
            return false;
        }
        if (biome1 == -biome2) {
            return false;
        }
        //The Volcanic Biomes will use a special river, which means we cannot have this river
        if (biome1 == Biome.getIdForBiome(GDBiomes.volcaniclands) && biome2 == Biome.getIdForBiome(GDBiomes.pinkAgateForest)) {
            return false;
        }
        if (biome1 == Biome.getIdForBiome(GDBiomes.volcaniclands) && biome2 == Biome.getIdForBiome(GDBiomes.blueAgateForest)) {
            return false;
        }
        if (biome1 == Biome.getIdForBiome(GDBiomes.volcaniclands) && biome2 == Biome.getIdForBiome(GDBiomes.greenAgateForest)) {
            return false;
        }
        if (biome1 == Biome.getIdForBiome(GDBiomes.volcaniclands) && biome2 == Biome.getIdForBiome(GDBiomes.purpleAgateForest)) {
            return false;
        }
        if (biome1 == Biome.getIdForBiome(GDBiomes.volcaniclands) && biome2 == Biome.getIdForBiome(GDBiomes.crystalPlains)) {
            return false;
        }
        if (biome1 == Biome.getIdForBiome(GDBiomes.volcaniclands) && biome2 == Biome.getIdForBiome(GDBiomes.fossilForest)) {
            return false;
        }
        //Crystal Plains and Pink Agate Forest are too similar for rivers
        if (biome1 == Biome.getIdForBiome(GDBiomes.pinkAgateForest) && biome2 == Biome.getIdForBiome(GDBiomes.crystalPlains)) {
            return false;
        }
        //If a reservoir gens near another reservoir, remove the river because it would look goofy
        if (biome1 == Biome.getIdForBiome(GDBiomes.mineralReservoir) && biome2 == Biome.getIdForBiome(GDBiomes.mineralReservoir)) {
            return false;
        }

        return true;
    }
}
