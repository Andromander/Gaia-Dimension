package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.GaiaBiomeMaker;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.world.chunk.GaiaBiomeSource;
import androsa.gaiadimension.world.chunk.GaiaChunkGenerator;
import androsa.gaiadimension.world.chunk.GaiaSurfaceRuleData;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;

import java.util.List;
import java.util.OptionalLong;

public class GaiaDimensions {

    public static final ResourceKey<LevelStem> GAIA_DIMENSION_STEM = ResourceKey.create(Registries.LEVEL_STEM, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<Level> gaia_world = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<DimensionType> gaia_dimension = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<NoiseGeneratorSettings> gaia_noise = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_noise"));

    public static void initDimension() {
        Registry.register(BuiltInRegistries.BIOME_SOURCE, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeSource.CODEC);
        Registry.register(BuiltInRegistries.CHUNK_GENERATOR, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
    }

    public static void initStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseGens = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        BiomeSource source = new GaiaBiomeSource(GaiaBiomeMaker.makeBiomeList(biomes), 0.0F, 1.0F, biomes);
        NoiseBasedChunkGenerator chunkgen = new GaiaChunkGenerator(source, noiseGens.getOrThrow(gaia_noise));
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(gaia_dimension), chunkgen);

        context.register(GAIA_DIMENSION_STEM, stem);
    }

    public static void initType(BootstapContext<DimensionType> context) {
        DimensionType.MonsterSettings monsters = new DimensionType.MonsterSettings(
                false, //piglin
                false, //raids
                UniformInt.of(0, 7), //monsters
                7 //blocklight
        );
        DimensionType type = new DimensionType(
                OptionalLong.of(6000L), //time
                true, //skylight
                false, //ceiling
                false, //warm
                true, //natural
                1.0D, //scale
                //false, dragon
                true, //bed
                true, //anchor TODO: until alternative
                -64, //minY
                64+256, //maxY
                64+256, //logical
                BlockTags.INFINIBURN_OVERWORLD, //infiniburn
                new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), //effects
                0.0F, //ambient
                monsters //monsters
        );

        context.register(gaia_dimension, type);
    }

    public static void initNoise(BootstapContext<NoiseGeneratorSettings> context) {
        NoiseSettings noiseSettings = NoiseSettings.create(
                -64,
                256,
                1,
                2);
        NoiseRouter noiseRouter = new NoiseRouter(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero());
        NoiseGeneratorSettings noisegen = new NoiseGeneratorSettings(
                noiseSettings,
                ModBlocks.gaia_stone.get().defaultBlockState(),
                ModBlocks.mineral_water.get().defaultBlockState(),
                noiseRouter,
                GaiaSurfaceRuleData.gaia(true, false, true),
                List.of(),
                63, // TODO: Sea Level. Bump?
                false,
                false,
                false,
                false);

        context.register(gaia_noise, noisegen);
    }
}
