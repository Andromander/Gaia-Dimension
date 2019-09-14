package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.storage.WorldInfo;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static androsa.gaiadimension.registry.ModBiomes.*;

public class GaiaBiomeProvider extends BiomeProvider {

    private final Layer genBiomes;
    private final Layer biomeFactoryLayer;
    private final Biome[] biomes = new Biome[]{
            pink_agate_forest,
            blue_agate_taiga,
            green_agate_jungle,
            purple_agate_swamp,
            fossil_woodland,
            mutant_agate_wildwood,
            volcanic_lands,
            static_wasteland,
            goldstone_lands,
            crystal_plains,
            salt_dunes,
            shining_grove,
            smoldering_bog,
            mineral_reservoir,
            mineral_river};


    public GaiaBiomeProvider(GaiaBiomeProviderSettings settings) {
        WorldInfo worldinfo = settings.getWorldInfo();
        Layer[] alayer = GaiaLayerUtil.makeLayers(worldinfo.getSeed());
        this.genBiomes = alayer[0];
        this.biomeFactoryLayer = alayer[1];

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest);
        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga);
        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle);
        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp);
        getBiomesToSpawnIn().add(ModBiomes.crystal_plains);
    }

    @Override
    public Biome getBiome(int x, int y) {
        return this.biomeFactoryLayer.func_215738_a(x, y);
    }

    @Override
    public Biome func_222366_b(int x, int y) {
        return this.genBiomes.func_215738_a(x, y);
    }

    @Override
    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
        return this.biomeFactoryLayer.generateBiomes(x, z, width, length);
    }

    @Override
    public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
        int i = centerX - sideLength >> 2;
        int j = centerZ - sideLength >> 2;
        int k = centerX + sideLength >> 2;
        int l = centerZ + sideLength >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Set<Biome> set = Sets.newHashSet();
        Collections.addAll(set, this.genBiomes.generateBiomes(i, j, i1, j1));
        return set;
    }

    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Biome[] abiome = this.genBiomes.generateBiomes(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for(int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            if (biomes.contains(abiome[l1])) {
                if (blockpos == null || random.nextInt(k1 + 1) == 0) {
                    blockpos = new BlockPos(i2, 0, j2);
                }

                ++k1;
            }
        }

        return blockpos;
    }

    //TODO: When we get Structures, make this conditionally true.
    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty()) {
            for(Biome biome : this.biomes) {
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
            }
        }

        return this.topBlocksCache;
    }
}
