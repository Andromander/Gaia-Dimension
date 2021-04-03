package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;

import java.util.List;

import static androsa.gaiadimension.registry.ModBiomes.*;

public class GaiaBiomeProvider extends BiomeProvider {

    public static final Codec<GaiaBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.LONG.fieldOf("seed")
                    .orElse(GaiaChunkGenerator.hackSeed)
                    .forGetter((obj) -> obj.seed),
            RegistryLookupCodec.create(Registry.BIOME_REGISTRY)
                    .forGetter((obj) -> obj.registry)
    ).apply(instance, instance.stable(GaiaBiomeProvider::new)));

    private final long seed;
    private final Registry<Biome> registry;
    private final Layer genBiomes;
    private static final List<RegistryKey<Biome>> biomes = ImmutableList.of(
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
            mineral_river);

    public GaiaBiomeProvider(long seed, Registry<Biome> registry) {
        super(biomes.stream().map(define -> () -> registry.getOrThrow(define)));
        this.seed = seed;
        this.registry = registry;
        this.genBiomes = GaiaLayerUtil.makeLayers(seed, registry);

//        getBiomesToSpawnIn().clear();
//        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
//        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
//        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
//        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
//        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());
    }

    @Override
    public BiomeProvider withSeed(long s) {
        return new GaiaBiomeProvider(s, registry);
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.getBiomeFromPos(registry, x, z);
    }

    public Biome getBiomeFromPos(Registry<Biome> registry, int x, int z) {
        int i = genBiomes.area.get(x, z);
        Biome biome = registry.byId(i);
        if (biome == null) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
            } else {
                GaiaDimensionMod.LOGGER.warn("Unknown biome id: ", i);
                return registry.get(BiomeRegistry.byId(0));
            }
        } else {
            return biome;
        }
    }
}
