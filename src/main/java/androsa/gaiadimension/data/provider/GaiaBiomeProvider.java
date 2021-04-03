package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.BiomeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GaiaBiomeProvider extends BiomeProvider {

    public GaiaBiomeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void run(DirectoryCache dir) {
        GaiaDimensionMod.LOGGER.info("Gaia Biomes starting generation...");

        Path out = this.generator.getOutputFolder();

        for(Map.Entry<RegistryKey<Biome>, Biome> biome : registerBiomes().entrySet()) {
            Path path = getPath(out, biome.getKey().location());
            Biome obj = biome.getValue();
            obj.setRegistryName(biome.getKey().location());
            Function<Supplier<Biome>, DataResult<JsonElement>> biomedata = JsonOps.INSTANCE.withEncoder(Biome.CODEC);

            try {
                 Optional<JsonElement> element = biomedata.apply(biome::getValue).result();
                if (element.isPresent()) {
                    IDataProvider.save(GSON, dir, element.get(), path);
                } else {
                    GaiaDimensionMod.LOGGER.error("Couldn't serialize biome {}", path);
                }
            } catch (IOException e) {
                GaiaDimensionMod.LOGGER.error("Couldn't save biome {}", path, e);
            }
        }

        GaiaDimensionMod.LOGGER.info("Gaia Biomes finished generating!");
    }

    private static Path getPath(Path path, ResourceLocation loc) {
        return path.resolve("data/" + loc.getNamespace() + "/worldgen/biome/" + loc.getPath() + ".json");
    }

    protected abstract Map<RegistryKey<Biome>, Biome> registerBiomes();

    public static Biome buildBiome(Biome.Builder builder) {
        return builder.build();
    }

    public static Biome.Builder createBuilder(BiomeAmbience ambience, BiomeGenerationSettings settings, MobSpawnInfo info) {
        return (new Biome.Builder())
                .precipitation(Biome.RainType.NONE)
                .downfall(0.0F)
                .biomeCategory(Biome.Category.NONE) //Note: This is to prevent unwanted feature gens
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(ambience)
                .generationSettings(settings)
                .mobSpawnSettings(info);
    }

    public static BiomeAmbience createAmbience(int plant, int sky, int fog) {
        return createAmbience(plant, plant, sky, fog);
    }

    public static BiomeAmbience createAmbience(int grass, int foliage, int sky, int fog) {
        return (new BiomeAmbience.Builder())
                .grassColorOverride(grass)
                .foliageColorOverride(foliage)
                .skyColor(sky)
                .fogColor(fog)
                .waterColor(7117233)
                .waterFogColor(9617108)
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                .build();
    }

    public static BiomeGenerationSettings.Builder createSettings() {
        return new BiomeGenerationSettings.Builder()
                .addCarver(GenerationStage.Carving.AIR, GaiaBiomeFeatures.crystal_caves)
                .addCarver(GenerationStage.Carving.AIR, GaiaBiomeFeatures.chasms)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_primal_mass)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_pebbles)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_speckled_rock)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_coarse_rock)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_precious_rock)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_sugilite)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_hematite)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_pyrite)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_cinnabar)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_labradorite)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_moonstone)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.crystal_fungi_caves);
    }

    public static MobSpawnInfo buildSpawns(MobSpawnInfo.Builder builder) {
        return builder.build();
    }

    public static MobSpawnInfo.Builder createSpawns() {
        return new MobSpawnInfo.Builder()
                .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.CAVERN_TICK, 65, 2, 4))
                .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.SHALURKER, 65, 2, 4))
                .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.ARCHAIC_WARRIOR, 65, 2, 4))
                .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.MUCKLING, 65, 2, 4))
                .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 5, 1, 2))
                .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.PRIMAL_BEAST, 15, 1, 2))
                .addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(ModEntities.SHALLOW_ARENTHIS, 10, 2, 4));
    }

    public static BiomeGenerationSettings pinkAgateForest() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_red_opal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.pink_agate_tree_common)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_03)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.spotted_kersei)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .addStructureStart(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings blueAgateTaiga() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_blue_opal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.blue_agate_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.thorny_wiltha)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings greenAgateJungle() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_green_opal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.green_agate_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.green_agate_bush)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_04)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.roofed_agaric)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .addStructureStart(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings purpleAgateSwamp() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_sweet_muck)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_uncommon)
                .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.gummy_glitter_blob)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_thick_glitter)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.purple_agate_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_rare)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.bulbous_hobina)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings fossilWoodland() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.fossilized_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.stickly_cupsir)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings mutantAgateWildwood() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_red_opal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_blue_opal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_green_opal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.various_agate_trees)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_mutant)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.mystical_murgni)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings volcanicLands() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.v_glitter_heavy_volrock)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_common)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_rare)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_searing_rock)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.burnt_agate_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.fiery_agate_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_seared)
                .build();
    }

    public static BiomeGenerationSettings staticWasteland() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.s_wasteland_stone)
                .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.static_spikes)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_static_stone)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_static_stone)
                .build();
    }

    public static BiomeGenerationSettings goldstoneLands() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_corrupt_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.goldstone_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_corrupt)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_corrupt)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.corrupted_gaia_eye)
                .build();
    }

    public static BiomeGenerationSettings crystalPlains() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.pink_agate_tree_rare)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_05)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.spotted_kersei)
                .addStructureStart(GaiaBiomeFeatures.mini_tower)
                .addStructureStart(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings saltDunes() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_salty)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_rare)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .build();
    }

    public static BiomeGenerationSettings smolderingBog() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_murky_boggy_pebble)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_liquid_bismuth)
                .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_spires)
                .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_geysers)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_bog_patch)
                .build();
    }

    public static BiomeGenerationSettings shiningGrove() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_soft_light_salt)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_liquid_aura)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.aura_tree)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.aura_shoots)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_aura)
                .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .build();
    }

    public static BiomeGenerationSettings mineralBiome() {
        return createSettings()
                .surfaceBuilder(GaiaBiomeFeatures.d_salty)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .addFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .build();
    }
}
