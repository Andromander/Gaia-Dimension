package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.WorldGenerationProvider;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.world.chunk.GaiaBiomeSource;
import androsa.gaiadimension.world.chunk.GaiaChunkGenerator;
import androsa.gaiadimension.world.chunk.warp.TerrainPoint;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GaiaWorldGen extends WorldGenerationProvider {

    public GaiaWorldGen(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void dumpRegistries(RegistryAccess access, HashCache cache, Path path, DynamicOps<JsonElement> ops) {
        //Registries
        WritableRegistry<LevelStem> stemRegistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental(), null);
        WritableRegistry<Biome> biomeRegistry = new MappedRegistry<>(Registry.BIOME_REGISTRY, Lifecycle.experimental(), null);

        stemRegistry.register(ModDimensions.GAIA_DIMENSION_STEM, createGaia(access), Lifecycle.experimental());

        Map<ResourceLocation, Biome> biomes = this.getBiomes();
        biomes.forEach((rl, biome) -> biomeRegistry.register(ResourceKey.create(Registry.BIOME_REGISTRY, rl), biome, Lifecycle.experimental()));

        StreamSupport.stream(RegistryAccess.knownRegistries().spliterator(), false)
                .filter(data -> access.ownedRegistry(data.key()).isPresent() && !data.key().equals(Registry.BIOME_REGISTRY))
                .forEach((consumer) -> dumpRegistryCap(cache, path, access, ops, consumer));

        LOGGER.info("Dumping biomes");
        this.dumpRegistry(path, cache, ops, Registry.BIOME_REGISTRY, biomeRegistry, Biome.DIRECT_CODEC);

        LOGGER.info("Dumping level stem");
        this.dumpRegistry(path, cache, ops, Registry.LEVEL_STEM_REGISTRY, stemRegistry, LevelStem.CODEC);
    }

    private LevelStem createGaia(RegistryAccess access) {
        Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
        Registry<StructureSet> structureRegistry = access.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY);
        Registry<NormalNoise.NoiseParameters> noiseparamRegistry = access.registryOrThrow(Registry.NOISE_REGISTRY);
        Registry<NoiseGeneratorSettings> noisegenRegistry = access.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);

        BiomeSource source = new GaiaBiomeSource(makeBiomeList(biomeRegistry), 0L, 0.0F, 1.0F, biomeRegistry);
        Holder<NoiseGeneratorSettings> noisegen = noisegenRegistry.getHolderOrThrow(ModDimensions.gaia_noise);
        NoiseBasedChunkGenerator chunkgen = new GaiaChunkGenerator(source, structureRegistry, noiseparamRegistry, noisegen, 0L);

        return new LevelStem(Holder.direct(dimType()), chunkgen, true);
    }

    private DimensionType dimType() {
        return DimensionType.create(
                OptionalLong.of(6000L), //time
                true, //skylight
                false, //ceiling
                false, //warm
                true, //natural
                1.0D, //scale
                false, //dragon
                false, //piglin
                true, //bed
                true, //anchor TODO: until alternative
                false, //raids
                -64, //minY
                64+256, //maxY
                64+256, //logical
                BlockTags.INFINIBURN_OVERWORLD, //infiniburn
                new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), //effects
                0.0F //ambient
        );
    }

    private Map<ResourceLocation, Biome> getBiomes() {
        return GaiaBiomes.BIOMES
                .entrySet()
                .stream()
                .peek(entry -> entry.getValue().setRegistryName(entry.getKey().location()))
                .collect(Collectors.toMap(entry -> entry.getKey().location(), Map.Entry::getValue));
    }

    private List<Pair<TerrainPoint, Holder<Biome>>> makeBiomeList(Registry<Biome> registry) {
        return List.of(
                pairBiome(registry, 0.1F, 0.2F, ModBiomes.blue_agate_taiga),
                pairBiome(registry, 0.05F, 0.05F, ModBiomes.crystal_plains),
                pairBiome(registry, 0.1F, 0.05F, ModBiomes.fossil_woodland),
                pairBiome(registry, 0.125F, 0.05F, ModBiomes.goldstone_lands),
                pairBiome(registry, 0.1F, 0.2F, ModBiomes.green_agate_jungle),
                pairBiome(registry, 0.35F, 0.15F, ModBiomes.golden_forest),
                pairBiome(registry, 0.8F, 0.5F, ModBiomes.golden_hills),
                pairBiome(registry, 0.15F, 0.05F, ModBiomes.golden_marsh),
                pairBiome(registry, 0.35F, 0.1F, ModBiomes.golden_plains),
                pairBiome(registry, 0.25F, 0.05F, ModBiomes.golden_sands),
                pairBiome(registry, -1.8F, 0.1F, ModBiomes.mineral_reservoir),
                pairBiome(registry, -0.8F, 0.0F, ModBiomes.mineral_river),
                pairBiome(registry, 2.0F, 0.075F, ModBiomes.mookaite_mesa),
                pairBiome(registry, 0.1F, 0.1F, ModBiomes.mutant_agate_wildwood),
                pairBiome(registry, 0.1F, 0.1F, ModBiomes.pink_agate_forest),
                pairBiome(registry, 0.0F, 0.05F, ModBiomes.purple_agate_swamp),
                pairBiome(registry, 0.2F, 0.05F, ModBiomes.salt_dunes),
                pairBiome(registry, 0.4F, 0.05F, ModBiomes.shining_grove),
                pairBiome(registry, 0.2F, 0.02F, ModBiomes.smoldering_bog),
                pairBiome(registry, 3.0F, 0.05F, ModBiomes.static_wasteland),
                pairBiome(registry, 1.0F, 0.7F, ModBiomes.volcanic_lands)
        );
    }

    private Pair<TerrainPoint, Holder<Biome>> pairBiome(Registry<Biome> registry, float depth, float scale, ResourceKey<Biome> biome) {
        return Pair.of(new TerrainPoint(depth, scale), getBiome(registry, biome));
    }

    private Holder<Biome> getBiome(Registry<Biome> registry, ResourceKey<Biome> key) {
        return Holder.Reference.createStandAlone(registry, key);
    }
}
