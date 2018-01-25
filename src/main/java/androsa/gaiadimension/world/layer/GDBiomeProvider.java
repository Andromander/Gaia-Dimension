package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class GDBiomeProvider extends BiomeProvider {
    public GDBiomeProvider(World world) {
        //Do not spawn in Volcanic, Goldstone or "Sacred" biomes
        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(GDBiomes.pinkAgateForest);
        getBiomesToSpawnIn().add(GDBiomes.blueAgateForest);
        getBiomesToSpawnIn().add(GDBiomes.greenAgateForest);
        getBiomesToSpawnIn().add(GDBiomes.purpleAgateForest);
        getBiomesToSpawnIn().add(GDBiomes.crystalPlains);

        makeLayers(world.getSeed());
    }

    private void makeLayers(long seed) {
        GenLayer biomes = new GenLayerGDBiomes(1L);

        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001, biomes);

        biomes = new GenLayerZoom(1002, biomes);
        biomes = new GenLayerZoom(1003, biomes);
        biomes = new GenLayerZoom(1004, biomes);
        biomes = new GenLayerZoom(1005, biomes);

        GenLayer riverLayer = new GenLayerGDRiver(1L, biomes);
        riverLayer = new GenLayerSmooth(7000L, riverLayer);
        biomes = new GenLayerGDRiverMix(100L, biomes, riverLayer);

        //oof, I like Voronoi. Looks cool
        GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

        biomes.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);

        genBiomes = biomes;
        biomeIndexLayer = genlayervoronoizoom;
    }
}
