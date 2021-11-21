package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.WorldGenerationProvider;
import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.FuzzyOffsetBiomeZoomer;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class GaiaWorldGen extends WorldGenerationProvider<JsonElement> {

    public GaiaWorldGen(DataGenerator generator) {
        super(generator, JsonOps.INSTANCE, GSON::toJson, new RegistryAccess.RegistryHolder());
    }

    @Override
    protected JsonElement intercept(ResourceKey<?> key, JsonElement element) {
        if (key == Registry.LEVEL_STEM_REGISTRY) {
            element.getAsJsonObject()
                    .get("generator").getAsJsonObject()
                    .get("biome_source").getAsJsonObject()
                    .remove("seed");
        }
        return super.intercept(key, element);
    }

    @Override
    public void generate(HashCache cache) {
        GaiaBiomeFeatures.registerCarvers(this.registries.registryOrThrow(Registry.CONFIGURED_CARVER_REGISTRY));
        GaiaBiomeFeatures.registerSurfaceBuilders(this.registries.registryOrThrow(Registry.CONFIGURED_SURFACE_BUILDER_REGISTRY));
        GaiaBiomeFeatures.registerStructureFeatures(this.registries.registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY));
        GaiaBiomeFeatures.registerFeatures(this.registries.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY));

        Map<ResourceLocation, Biome> biomes = this.getBiomes();
        biomes.forEach((rl, biome) -> this.registries.registry(Registry.BIOME_REGISTRY).ifPresent(reg -> Registry.register(reg, rl, biome)));
        biomes.forEach((rl, biome) -> this.serialize(Registry.BIOME_REGISTRY, rl, biome, Biome.DIRECT_CODEC));

        this.serialize(Registry.LEVEL_STEM_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), makeDimension(), LevelStem.CODEC);
    }

    private Map<ResourceLocation, Biome> getBiomes() {
        return GaiaBiomes.BIOMES
                .entrySet()
                .stream()
                .peek(entry -> entry.getValue().setRegistryName(entry.getKey().location()))
                .collect(Collectors.toMap(entry -> entry.getKey().location(), Map.Entry::getValue));
    }

    private LevelStem makeDimension() {
        StructureSettings structureSettings = new StructureSettings(
                Optional.empty(), // No Strongholds
                getStructures());
        NoiseSettings noiseSettings = NoiseSettings.create(
                0, // TODO: Bring this down once we get to this
                256,
                new NoiseSamplingSettings(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D),
                new NoiseSlideSettings(-10, 3, 0),
                new NoiseSlideSettings(15, 3, 0),
                1,
                2,
                1.0D,
                -0.46875D,
                false,
                true,
                false,
                false);
        NoiseGeneratorSettings noiseGeneratorSettings = new NoiseGeneratorSettings(
                structureSettings,
                noiseSettings,
                ModBlocks.gaia_stone.get().defaultBlockState(),
                ModBlocks.mineral_water.get().defaultBlockState(),
                Integer.MIN_VALUE,
                0,
                63, // TODO: Sea Level. Bump?
                0, // TODO: Min height. Bump in rework
                false,
                false,
                false,
                false, // TODO: Deepslate layer. Evaluate for Primal Mass
                false,
                false);

        this.getOrCreateRegistry(this.registries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY),
                ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_noise_settings")),
                () -> noiseGeneratorSettings);

        ModDimensions.initDimension();

        DimensionType gaiaDimension = DimensionType.create(
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
                0, //minY
                256, //maxY
                256, //logical
                FuzzyOffsetBiomeZoomer.INSTANCE, //zoom
                new ResourceLocation("infiniburn_overworld"), //infiniburn
                new ResourceLocation(GaiaDimensionMod.MODID, "gaia"),
                0.0F //ambient
        );

        this.getOrCreateRegistry(this.registries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY),
                ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension")),
                () -> gaiaDimension);

        BiomeSource biomeSource = new GaiaBiomeProvider(0L, new MappedRegistry<>(Registry.BIOME_REGISTRY, Lifecycle.experimental()));
        NoiseBasedChunkGenerator chunkGenerator = new GaiaChunkGenerator(biomeSource, 0L, () -> noiseGeneratorSettings);

        return new LevelStem(() -> gaiaDimension, chunkGenerator);
    }


    private Map<StructureFeature<?>, StructureFeatureConfiguration> getStructures() {
        Map<StructureFeature<?>, StructureFeatureConfiguration> map = Maps.newHashMap();
        map.put(ModWorldgen.MINI_TOWER.get(), new StructureFeatureConfiguration(30, 10, 38474253));
        map.put(ModWorldgen.MALACHITE_WATCHTOWER.get(), new StructureFeatureConfiguration(35, 15, 39482048));
        return map;
    }
}
