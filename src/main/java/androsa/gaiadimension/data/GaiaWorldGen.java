package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.WorldGenerationProvider;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.registry.configurations.GaiaConfiguredCarvers;
import androsa.gaiadimension.registry.configurations.GaiaConfiguredFeatures;
import androsa.gaiadimension.registry.configurations.GaiaConfiguredStructures;
import androsa.gaiadimension.registry.configurations.GaiaPlacedFeatures;
import androsa.gaiadimension.world.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GaiaWorldGen extends WorldGenerationProvider<JsonElement> {

    public GaiaWorldGen(DataGenerator generator) {
        super(generator, JsonOps.INSTANCE, GSON::toJson, new RegistryAccess.RegistryHolder());
    }

    @Override
    protected JsonElement intercept(ResourceKey<?> key, JsonElement element) {
        if (key == Registry.LEVEL_STEM_REGISTRY) {
            element.getAsJsonObject().get("generator").getAsJsonObject().remove("seed");
            element.getAsJsonObject().get("generator").getAsJsonObject().get("biome_source").getAsJsonObject().remove("seed");
        }
        return super.intercept(key, element);
    }

    @Override
    public void generate(HashCache cache) {
        GaiaConfiguredCarvers.registerCarvers(this.registries.registryOrThrow(Registry.CONFIGURED_CARVER_REGISTRY));
        GaiaConfiguredStructures.registerStructureFeatures(this.registries.registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY));
        GaiaPlacedFeatures.registerPlacedFeatures(this.registries.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY));
        GaiaConfiguredFeatures.registerFeatures(this.registries.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY));

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
        StructureSettings structureSettings = new GaiaStructureSettings(getStructures());
        NoiseSettings noiseSettings = NoiseSettings.create(
                -64,
                256,
                new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D),
                new NoiseSlider(-10, 3, 0),
                new NoiseSlider(15, 3, 0),
                1,
                2,
                false,
                false,
                false,
                TerrainShaper.overworld(false)); //TODO
        NoiseGeneratorSettings noiseGeneratorSettings = new NoiseGeneratorSettings(
                structureSettings,
                noiseSettings,
                ModBlocks.gaia_stone.get().defaultBlockState(),
                ModBlocks.mineral_water.get().defaultBlockState(),
                GaiaSurfaceRuleData.gaia(true, false, true),
                63, // TODO: Sea Level. Bump?
                false,
                false,
                false,
                false,
                false,
                false);

        this.getOrCreateRegistry(this.registries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), ModDimensions.gaia_noise, () -> noiseGeneratorSettings);

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
                -64, //minY
                64+256, //maxY
                64+256, //logical
                new ResourceLocation("infiniburn_overworld"), //infiniburn
                new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), //effects
                0.0F //ambient
        );

        ModDimensions.initDimension();
        this.getOrCreateRegistry(this.registries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY), ModDimensions.gaia_dimension, () -> gaiaDimension);

        //BiomeSource biomeSource = new GaiaBiomeProvider(0L, new MappedRegistry<>(Registry.BIOME_REGISTRY, Lifecycle.experimental()));
        MultiNoiseBiomeSource.Preset gaiaPreset = new MultiNoiseBiomeSource.Preset(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), (registry) -> {
            ImmutableList.Builder<Pair<Climate.ParameterPoint, Supplier<Biome>>> builder = ImmutableList.builder();
            (new GaiaBiomeBuilder()).addBiomes((biome) -> {
                builder.add(biome.mapSecond((name) -> () -> registry.getOrThrow(name)));
            });
            return new Climate.ParameterList<>(builder.build());
        });
        BiomeSource biomeSource = gaiaPreset.biomeSource(registries.registryOrThrow(Registry.BIOME_REGISTRY), false);
        ChunkGenerator chunkGenerator = new GaiaChunkGenerator(RegistryAccess.builtin().registryOrThrow(Registry.NOISE_REGISTRY), biomeSource, 0L, () -> noiseGeneratorSettings);

        return new LevelStem(() -> gaiaDimension, chunkGenerator);
    }


    private Map<StructureFeature<?>, StructureFeatureConfiguration> getStructures() {
        Map<StructureFeature<?>, StructureFeatureConfiguration> map = Maps.newHashMap();
        map.put(ModWorldgen.MINI_TOWER.get(), new StructureFeatureConfiguration(30, 10, 38474253));
        map.put(ModWorldgen.MALACHITE_WATCHTOWER.get(), new StructureFeatureConfiguration(35, 15, 39482048));
        return map;
    }
}
