package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.storage.WorldInfo;

import java.util.Set;

import static androsa.gaiadimension.registry.ModBiomes.*;

public class GaiaBiomeProvider extends BiomeProvider {

    private final Layer genBiomes;
    //private final Layer biomeFactoryLayer;
    private static final Set<Biome> biomes = ImmutableSet.of(
            pink_agate_forest.get(),
            blue_agate_taiga.get(),
            green_agate_jungle.get(),
            purple_agate_swamp.get(),
            fossil_woodland.get(),
            mutant_agate_wildwood.get(),
            volcanic_lands.get(),
            static_wasteland.get(),
            goldstone_lands.get(),
            crystal_plains.get(),
            salt_dunes.get(),
            shining_grove.get(),
            smoldering_bog.get(),
            mineral_reservoir.get(),
            mineral_river.get());


    public GaiaBiomeProvider(GaiaBiomeProviderSettings settings) {
        super(biomes);
        WorldInfo worldinfo = settings.getWorldInfo();
        this.genBiomes = GaiaLayerUtil.makeLayers(worldinfo.getSeed());
        //this.genBiomes = alayer[0];
        //this.biomeFactoryLayer = alayer[1];

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());
    }

    @Override
    public Biome getBiomeForNoiseGen(int x, int y, int z) {
        return this.genBiomes.func_215738_a(x, y);
    }

//    @Override
//    public Biome getBiome(int x, int y) {
//        return this.biomeFactoryLayer.func_215738_a(x, y);
//    }

//    @Override
//    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
//        return this.biomeFactoryLayer.generateBiomes(x, z, width, length);
//    }

//    @Override
//    public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
//        int i = centerX - sideLength >> 2;
//        int j = centerZ - sideLength >> 2;
//        int k = centerX + sideLength >> 2;
//        int l = centerZ + sideLength >> 2;
//        int i1 = k - i + 1;
//        int j1 = l - j + 1;
//        Set<Biome> set = Sets.newHashSet();
//        Collections.addAll(set, this.genBiomes.generateBiomes(i, j, i1, j1));
//        return set;
//    }

//    @Override
//    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
//        int i = x - range >> 2;
//        int j = z - range >> 2;
//        int k = x + range >> 2;
//        int l = z + range >> 2;
//        int i1 = k - i + 1;
//        int j1 = l - j + 1;
//        Biome[] abiome = this.genBiomes.generateBiomes(i, j, i1, j1);
//        BlockPos blockpos = null;
//        int k1 = 0;
//
//        for(int l1 = 0; l1 < i1 * j1; ++l1) {
//            int i2 = i + l1 % i1 << 2;
//            int j2 = j + l1 / i1 << 2;
//            if (biomes.contains(abiome[l1])) {
//                if (blockpos == null || random.nextInt(k1 + 1) == 0) {
//                    blockpos = new BlockPos(i2, 0, j2);
//                }
//
//                ++k1;
//            }
//        }
//
//        return blockpos;
//    }

    //TODO: When we get Structures, make this conditionally true.
    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty()) {
            for(Biome biome : biomes) {
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
            }
        }

        return this.topBlocksCache;
    }
}
