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
    public void act(DirectoryCache dir) {
        GaiaDimensionMod.LOGGER.info("Gaia Biomes starting generation...");

        Path out = this.generator.getOutputFolder();

        for(Map.Entry<RegistryKey<Biome>, Biome> biome : registerBiomes().entrySet()) {
            Path path = getPath(out, biome.getKey().getLocation());
            Function<Supplier<Biome>, DataResult<JsonElement>> biomedata = JsonOps.INSTANCE.withEncoder(Biome.BIOME_CODEC);

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
                .category(Biome.Category.NONE) //Note: This is to prevent unwanted feature gens
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(ambience)
                .withGenerationSettings(settings)
                .withMobSpawnSettings(info);
    }

    public static BiomeAmbience createAmbience(int plant, int sky, int fog) {
        return createAmbience(plant, plant, sky, fog);
    }

    public static BiomeAmbience createAmbience(int grass, int foliage, int sky, int fog) {
        return (new BiomeAmbience.Builder())
                .withGrassColor(grass)
                .withFoliageColor(foliage)
                .withSkyColor(sky)
                .setFogColor(fog)
                .setWaterColor(7117233)
                .setWaterFogColor(9617108)
                .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                .build();
    }

    public static BiomeGenerationSettings.Builder createSettings() {
        return new BiomeGenerationSettings.Builder()
                .withCarver(GenerationStage.Carving.AIR, GaiaBiomeFeatures.crystal_caves)
                .withCarver(GenerationStage.Carving.AIR, GaiaBiomeFeatures.chasms)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_primal_mass)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_pebbles)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_speckled_rock)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_coarse_rock)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_precious_rock)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_sugilite)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_hematite)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_pyrite)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_cinnabar)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_labradorite)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_moonstone)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.crystal_fungi_caves);
    }

    public static MobSpawnInfo buildSpawns(MobSpawnInfo.Builder builder) {
        return builder.copy();
    }

    public static MobSpawnInfo.Builder createSpawns() {
        return new MobSpawnInfo.Builder()
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.CAVERN_TICK, 65, 2, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.SHALURKER, 65, 2, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.ARCHAIC_WARRIOR, 65, 2, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.MUCKLING, 65, 2, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 5, 1, 2))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.PRIMAL_BEAST, 15, 1, 2))
                .withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(ModEntities.SHALLOW_ARENTHIS, 10, 2, 4));
    }

    public static BiomeGenerationSettings pinkAgateForest() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_red_opal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.pink_agate_tree_common)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_03)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.spotted_kersei)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .withStructure(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings blueAgateTaiga() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_blue_opal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.blue_agate_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.thorny_wiltha)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings greenAgateJungle() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_green_opal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.green_agate_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.green_agate_bush)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_04)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.roofed_agaric)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .withStructure(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings purpleAgateSwamp() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_sweet_muck)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_uncommon)
                .withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.gummy_glitter_blob)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_thick_glitter)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_rare)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.purple_agate_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_rare)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.bulbous_hobina)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings fossilWoodland() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.fossilized_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_02)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.stickly_cupsir)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings mutantAgateWildwood() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_red_opal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_blue_opal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_green_opal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_white_opal_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.various_agate_trees)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_mutant)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.mystical_murgni)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .build();
    }

    public static BiomeGenerationSettings volcanicLands() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.v_glitter_heavy_volrock)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_common)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_rare)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_searing_rock)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.burnt_agate_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.fiery_agate_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_seared)
                .build();
    }

    public static BiomeGenerationSettings staticWasteland() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.s_wasteland_stone)
                .withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.static_spikes)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_static_stone)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_static_stone)
                .build();
    }

    public static BiomeGenerationSettings goldstoneLands() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_corrupt_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.goldstone_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_corrupt)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_corrupt)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.corrupted_gaia_eye)
                .build();
    }

    public static BiomeGenerationSettings crystalPlains() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_glitter_heavy_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.pink_agate_tree_rare)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_05)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.spotted_kersei)
                .withStructure(GaiaBiomeFeatures.mini_tower)
                .withStructure(GaiaBiomeFeatures.malachite_watchtower)
                .build();
    }

    public static BiomeGenerationSettings saltDunes() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_salty)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_rare)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .build();
    }

    public static BiomeGenerationSettings smolderingBog() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_murky_boggy_pebble)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_liquid_bismuth)
                .withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_spires)
                .withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_geysers)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_amethyst)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jet)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_bog_patch)
                .build();
    }

    public static BiomeGenerationSettings shiningGrove() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_soft_light_salt)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_liquid_aura)
                .withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_spires)
                .withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, GaiaBiomeFeatures.bismuth_geysers)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_copal)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.ore_raw_jade)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GaiaBiomeFeatures.disk_bog_patch)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.aura_tree)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.aura_shoots)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_growth_aura)
                .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GaiaBiomeFeatures.crystal_blooms_common)
                .build();
    }

    public static BiomeGenerationSettings mineralBiome() {
        return createSettings()
                .withSurfaceBuilder(GaiaBiomeFeatures.d_salty)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_superhot_magma_rare)
                .withFeature(GenerationStage.Decoration.LAKES, GaiaBiomeFeatures.lake_mineral_water_common)
                .withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, GaiaBiomeFeatures.underground_glitter_blob)
                .build();
    }
}
