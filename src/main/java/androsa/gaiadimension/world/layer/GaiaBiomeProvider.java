package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.oldgen.Layer;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.Optional;

import static androsa.gaiadimension.registry.ModBiomes.*;

public class GaiaBiomeProvider extends BiomeSource {

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
    private static final List<ResourceKey<Biome>> biomes = ImmutableList.of(
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
//        super(biomes.stream().map(define -> () -> registry.getOrThrow(define)));
        super(biomes
                .stream()
                .map(ResourceKey::location)
                .map(registry::getOptional)
                .filter(Optional::isPresent)
                .map(opt -> opt::get));
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
    public BiomeSource withSeed(long s) {
        return new GaiaBiomeProvider(s, registry);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        return this.genBiomes.get(registry, x, z);
    }
}
