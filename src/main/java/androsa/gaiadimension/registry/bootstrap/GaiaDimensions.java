package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.GaiaBiomeMaker;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.values.GaiaTags;
import androsa.gaiadimension.world.chunk.GaiaBiomeSource;
import androsa.gaiadimension.world.chunk.GaiaChunkGenerator;
import androsa.gaiadimension.world.chunk.GaiaSurfaceRuleData;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.MoonPhase;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.timeline.Timeline;
import net.neoforged.neoforge.common.world.NeoForgeEnvironmentAttributes;

import java.util.List;

public class GaiaDimensions {

    public static final ResourceKey<LevelStem> GAIA_DIMENSION_STEM = ResourceKey.create(Registries.LEVEL_STEM, Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<Level> gaia_world = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<DimensionType> gaia_dimension = ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<NoiseGeneratorSettings> gaia_noise = ResourceKey.create(Registries.NOISE_SETTINGS, Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia_noise"));

    public static void initStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseGens = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        BiomeSource source = new GaiaBiomeSource(GaiaBiomeMaker.makeBiomeList(biomes), 0.0F, 1.0F, biomes);
        NoiseBasedChunkGenerator chunkgen = new GaiaChunkGenerator(source, noiseGens.getOrThrow(gaia_noise));
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(gaia_dimension), chunkgen);

        context.register(GAIA_DIMENSION_STEM, stem);
    }

    public static void initType(BootstrapContext<DimensionType> context) {
        HolderGetter<Timeline> timelines = context.lookup(Registries.TIMELINE);

        //TODO: Prevent piglins
        DimensionType.MonsterSettings monsters = new DimensionType.MonsterSettings(
                UniformInt.of(0, 7), //monsters
                7 //blocklight
        );

        DimensionType type = new DimensionType(
                true, //hasFixedTime
                true, //hasSkyLight
                false, //hasCeiling
                1.0D, //coordinateScale
                -64, //minY
                64+256, //height
                64+256, //logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, //infiniburn
                0.0F, //ambientLight
                monsters, //monsters
                DimensionType.Skybox.OVERWORLD, //skybox
                DimensionType.CardinalLightType.DEFAULT, //cardinalLightType
                EnvironmentAttributeMap.builder() //attributes
                        .set(EnvironmentAttributes.BED_RULE, BedRule.CAN_SLEEP_WHEN_DARK) //oh my god I can do more than this...
                        .set(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
                        .set(EnvironmentAttributes.CAN_START_RAID, false)
                        .set(EnvironmentAttributes.CLOUD_HEIGHT, 255.0F)
                        .set(EnvironmentAttributes.FAST_LAVA, true)
                        .set(EnvironmentAttributes.FOG_COLOR, 15381216)
                        .set(EnvironmentAttributes.MONSTERS_BURN, true)
                        .set(EnvironmentAttributes.MOON_ANGLE, 270.0F)
                        .set(EnvironmentAttributes.MOON_PHASE, MoonPhase.FULL_MOON)
                        .set(EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, false)
                        .set(EnvironmentAttributes.PIGLINS_ZOMBIFY, true)
                        .set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true)
                        .set(EnvironmentAttributes.SKY_COLOR, 13016408)
                        .set(EnvironmentAttributes.SUN_ANGLE, 90.0F)
                        .set(EnvironmentAttributes.WATER_EVAPORATES, true)
                        .set(NeoForgeEnvironmentAttributes.CUSTOM_SKYBOX, Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia"))
                        .build(),
                timelines.getOrThrow(GaiaTags.Timelines.IN_GAIA)
        );

        context.register(gaia_dimension, type);
    }

    public static void initNoise(BootstrapContext<NoiseGeneratorSettings> context) {
        NoiseSettings noiseSettings = NoiseSettings.create(
                -64,
                128,
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
