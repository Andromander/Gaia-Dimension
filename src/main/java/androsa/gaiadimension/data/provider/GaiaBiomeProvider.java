package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
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
                .addCarver(GenerationStep.Carving.AIR, GaiaBiomeFeatures.crystal_caves)
                .addCarver(GenerationStep.Carving.AIR, GaiaBiomeFeatures.chasms)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_primal_mass)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_pebbles)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_speckled_rock)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_coarse_rock)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_precious_rock)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_sugilite)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_hematite)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_pyrite)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_cinnabar)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_labradorite)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_moonstone)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.crystal_fungi_caves);
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
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_red_opal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.pink_agate_tree_common)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_03)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.spotted_kersei)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .addStructureStart(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings blueAgateTaiga() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_blue_opal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.blue_agate_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.thorny_wiltha)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings greenAgateJungle() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_green_opal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.green_agate_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.green_agate_bush)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_04)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.roofed_agaric)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .addStructureStart(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings purpleAgateSwamp() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_sweet_muck)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_uncommon)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.gummy_glitter_blob)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_thick_glitter)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.purple_agate_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_rare)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.bulbous_hobina)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings fossilWoodland() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.fossilized_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.stickly_cupsir)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings mutantAgateWildwood() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_red_opal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_blue_opal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_green_opal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.various_agate_trees)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_mutant)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.mystical_murgni)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings volcanicLands() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.v_glitter_heavy_volrock)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_common)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_rare)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_searing_rock)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.burnt_agate_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.fiery_agate_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_seared)
                .build();
    }

    public static BiomeGenerationSettings staticWasteland() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.s_wasteland_stone)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.static_spikes)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_static_stone)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_static_stone)
                .build();
    }

    public static BiomeGenerationSettings goldstoneLands() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_corrupt_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.goldstone_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_corrupt)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_corrupt)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.corrupted_gaia_eye)
                .build();
    }

    public static BiomeGenerationSettings crystalPlains() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.pink_agate_tree_rare)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_05)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.spotted_kersei)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .addStructureStart(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings saltDunes() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_salty)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_rare)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .build();
    }

    public static BiomeGenerationSettings smolderingBog() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_murky_boggy_pebble)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_liquid_bismuth)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_spires)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_geysers)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_bog_patch)
                .build();
    }

    public static BiomeGenerationSettings shiningGrove() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_soft_light_salt)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_liquid_aura)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.aura_tree)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.aura_shoots)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_aura)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .build();
    }

    public static BiomeGenerationSettings mineralBiome() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_salty)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStep.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .build();
    }
}
