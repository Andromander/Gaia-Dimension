package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.configurations.GaiaConfiguredCarvers;
import androsa.gaiadimension.registry.configurations.GaiaFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.function.Supplier;

public abstract class GaiaBiomeProvider {

    public static Biome buildBiome(Biome.BiomeBuilder builder) {
        return builder.build();
    }

    public static Biome.BiomeBuilder createBuilder(BiomeSpecialEffects ambience, BiomeGenerationSettings settings, MobSpawnSettings info) {
        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .downfall(0.0F)
                .biomeCategory(Biome.BiomeCategory.NONE) //Note: This is to prevent unwanted feature gens
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(ambience)
                .generationSettings(settings)
                .mobSpawnSettings(info);
    }

    public static BiomeSpecialEffects createAmbience(int plant, int sky, int fog) {
        return createAmbience(plant, plant, sky, fog);
    }

    public static BiomeSpecialEffects createAmbience(int grass, int foliage, int sky, int fog) {
        return (new BiomeSpecialEffects.Builder())
                .grassColorOverride(grass)
                .foliageColorOverride(foliage)
                .skyColor(sky)
                .fogColor(fog)
                .waterColor(7117233)
                .waterFogColor(9617108)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();
    }

    public static BiomeGenerationSettings.Builder createSettings() {
        return new BiomeGenerationSettings.Builder()
                .addCarver(GenerationStep.Carving.AIR, GaiaConfiguredCarvers.crystal_caves)
                .addCarver(GenerationStep.Carving.AIR, GaiaConfiguredCarvers.chasms)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_PRIMAL_MASS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_PEBBLES)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_SPECKLED_ROCK)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_COARSE_ROCK)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_PRECIOUS_ROCK)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_SUGILITE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_HEMATITE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_PYRITE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_CINNABAR)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_LABRADORITE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_MOONSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.CRYSTAL_FUNGI_CAVES);
    }

    public static MobSpawnSettings buildSpawns(MobSpawnSettings.Builder builder) {
        return builder.build();
    }

    public static MobSpawnSettings.Builder createSpawns() {
        return new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, mobData(ModEntities.CAVERN_TICK, 65, 2, 4))
                .addSpawn(MobCategory.MONSTER, mobData(ModEntities.SHALURKER, 65, 2, 4))
                .addSpawn(MobCategory.MONSTER, mobData(ModEntities.ARCHAIC_WARRIOR, 65, 2, 4))
                .addSpawn(MobCategory.MONSTER, mobData(ModEntities.MUCKLING, 65, 2, 4))
                .addSpawn(MobCategory.MONSTER, mobData(() -> EntityType.ENDERMAN, 5, 1, 2))
                .addSpawn(MobCategory.MONSTER, mobData(ModEntities.PRIMAL_BEAST, 15, 1, 2))
                .addSpawn(MobCategory.WATER_CREATURE, mobData(ModEntities.SHALLOW_ARENTHIS, 10, 2, 4));
    }

    public static MobSpawnSettings.SpawnerData mobData(Supplier<? extends EntityType<?>> entity, int weight, int min, int max) {
        return new MobSpawnSettings.SpawnerData(entity.get(), weight, min, max);
    }

    public static BiomeGenerationSettings pinkAgateForest() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_COPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RED_OPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_WHITE_OPAL_RARE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.PINK_AGATE_TREE_COMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_03)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_COMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.SPOTTED_KERSEI)
                .build();
    }

    public static BiomeGenerationSettings blueAgateTaiga() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JET)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_BLUE_OPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_WHITE_OPAL_RARE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.BLUE_AGATE_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_02)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_COMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.THORNY_WILTHA)
                .build();
    }

    public static BiomeGenerationSettings greenAgateJungle() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_COPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JADE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_GREEN_OPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_WHITE_OPAL_RARE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GREEN_AGATE_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GREEN_AGATE_BUSH)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_04)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_COMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.ROOFED_AGARIC)
                .build();
    }

    public static BiomeGenerationSettings purpleAgateSwamp() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SWEET_MUCK)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_UNCOMMON)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.GUMMY_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_THICK_GLITTER)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JADE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_WHITE_OPAL_RARE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.PURPLE_AGATE_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_02)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_RARE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.BULBOUS_HOBINA)
                .build();
    }

    public static BiomeGenerationSettings fossilWoodland() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_COPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JET)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.FOSSILIZED_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_02)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_COMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.STICKLY_CUPSIR)
                .build();
    }

    public static BiomeGenerationSettings mutantAgateWildwood() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JADE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RED_OPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_BLUE_OPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_GREEN_OPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_WHITE_OPAL_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.VARIOUS_AGATE_TREES)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_MUTANT)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.MYSTICAL_MURGNI)
                .build();
    }

    public static BiomeGenerationSettings volcanicLands() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_COMMON)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_RARE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_SEARING_ROCK)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_COPAL)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.BURNT_AGATE_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.FIERY_AGATE_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_SEARED)
                .build();
    }

    public static BiomeGenerationSettings staticWasteland() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.STATIC_SPIKES)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_STATIC_STONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.DISK_STATIC_STONE)
                .build();
    }

    public static BiomeGenerationSettings goldstoneLands() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JET)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDSTONE_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_CORRUPT)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_CORRUPT)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CORRUPTED_GAIA_EYE)
                .build();
    }

    public static BiomeGenerationSettings crystalPlains() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JADE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.PINK_AGATE_TREE_RARE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_05)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_COMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.SPOTTED_KERSEI)
                .build();
    }

    public static BiomeGenerationSettings saltDunes() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_RARE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JET)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .build();
    }

    public static BiomeGenerationSettings smolderingBog() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_LIQUID_BISMUTH)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.BISMUTH_SPIRES)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.BISMUTH_GEYSERS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_AMETHYST)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JET)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.DISK_BOG_PATCH)
                .build();
    }

    public static BiomeGenerationSettings shiningGrove() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_LIQUID_AURA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_COPAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.ORE_RAW_JADE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.AURA_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.AURA_SHOOTS)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_GROWTH_AURA)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_COMMON)
                .build();
    }

    public static BiomeGenerationSettings mineralBiome() {
        return createSettings()
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_SUPERHOT_MAGMA_RARE)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaFeatures.Placed.LAKE_MINERAL_WATER_COMMON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaFeatures.Placed.UNDERGROUND_GLITTER_BLOB)
                .build();
    }

    public static BiomeGenerationSettings goldenHillsBiome() {
        return new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.BRILLIANT_STONE_SPIKES)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.BALANCING_ROCKS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.DISK_GILDED_BRILLIANT_STONE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDEN_GRASS_RARE)
                .build();
    }

    public static BiomeGenerationSettings goldenForestBiome() {
        return new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDEN_TREES)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.CRYSTAL_BLOOMS_GOLDEN)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDEN_GRASS_UNCOMMON)
                .build();
    }

    public static BiomeGenerationSettings goldenPlainsBiome() {
        return new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.TOUGH_GOLDEN_STONE_MONOLITHS)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.TALL_GOLDEN_GRASS)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDEN_GRASS_COMMON)
                .build();
    }

    public static BiomeGenerationSettings goldenMarshBiome() {
        return new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaFeatures.Placed.MARSH_LAKES)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaFeatures.Placed.DISK_MARSH_PATCH)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.SMALL_GOLDEN_TREE_WITH_VINES)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDEN_GRASS_UNCOMMON)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.TWINKLING_GILSRI)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaFeatures.Placed.GOLDEN_VINES)
                .build();
    }
}
