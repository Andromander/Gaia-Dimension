package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;

import java.util.Set;

import static androsa.gaiadimension.registry.ModBiomes.*;

public class GaiaBiomeProvider extends BiomeProvider {

    private final Layer genBiomes;
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
        this.genBiomes = GaiaLayerUtil.makeLayers(settings.getSeed());

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());
    }

    @Override
    public Biome getBiomeForNoiseGen(int x, int y, int z) {
        return this.genBiomes.func_215738_a(x, z);
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
