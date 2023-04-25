package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBiomeProvider;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModEntities;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;

import java.util.Map;

public class GaiaBiomes extends GaiaBiomeProvider {

    public static final Map<ResourceKey<Biome>, Biome> BIOMES = registerBiomes();

    private static Map<ResourceKey<Biome>, Biome> registerBiomes() {
        final ImmutableMap.Builder<ResourceKey<Biome>, Biome> biomes = ImmutableMap.builder();

        biomes.put(ModBiomes.pink_agate_forest, makePinkAgateForest());
        biomes.put(ModBiomes.blue_agate_taiga, makeBlueAgateTaiga());
        biomes.put(ModBiomes.green_agate_jungle, makeGreenAgateJungle());
        biomes.put(ModBiomes.purple_agate_swamp, makePurpleAgateSwamp());
        biomes.put(ModBiomes.fossil_woodland, makeFossilWoodland());
        biomes.put(ModBiomes.mutant_agate_wildwood, makeMutantAgateWildwood());
        biomes.put(ModBiomes.igneous_plains, makeDebug());
        biomes.put(ModBiomes.volcanic_lands, makeVolcanicLands());
        biomes.put(ModBiomes.wasteland_hills, makeDebug());
        biomes.put(ModBiomes.static_wasteland, makeStaticWasteland());
        biomes.put(ModBiomes.weirded_goldstone_lands, makeDebug());
        biomes.put(ModBiomes.goldstone_lands, makeGoldstoneLands());
        biomes.put(ModBiomes.crystal_plains, makeCrystalPlains());
        biomes.put(ModBiomes.salt_dunes, makeSaltDunes());
        biomes.put(ModBiomes.crystal_salt_dunes, makeDebug());
        biomes.put(ModBiomes.mookaite_mesa, makeMookaite());
        biomes.put(ModBiomes.shining_grove, makeShiningGrove());
        biomes.put(ModBiomes.smoldering_bog, makeSmolderingBog());
        biomes.put(ModBiomes.hotspot, makeDebug());
        biomes.put(ModBiomes.prismatic_steppe, makeDebug());
        biomes.put(ModBiomes.mineral_reservoir, makeMineralReservoir());
        biomes.put(ModBiomes.aquamarine_trench, makeDebug());
        biomes.put(ModBiomes.salty_coast, makeSaltyCoast());
        biomes.put(ModBiomes.tourmaline_coast, makeDebug());
        biomes.put(ModBiomes.mineral_river, makeMineralRiver());

        biomes.put(ModBiomes.golden_forest, makeGoldenForest());
        biomes.put(ModBiomes.golden_plains, makeGoldenPlains());
        biomes.put(ModBiomes.golden_hills, makeGoldenHills());
        biomes.put(ModBiomes.golden_sands, makeGoldenSands());
        biomes.put(ModBiomes.golden_marsh, makeGoldenMarsh());

        biomes.put(ModBiomes.glitter_caves, makeDebug());
        biomes.put(ModBiomes.energy_caves, makeDebug());
        biomes.put(ModBiomes.sludge_caves, makeDebug());
        biomes.put(ModBiomes.corrupt_caves, makeDebug());
        biomes.put(ModBiomes.static_caves, makeDebug());
        biomes.put(ModBiomes.magma_caves, makeDebug());
        biomes.put(ModBiomes.golden_caves, makeDebug());

        return biomes.build();
    }

    public static Biome makePinkAgateForest() {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        pinkAgateForest(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AGATE_GOLEM, 15, 1, 3)))
                ).temperature(0.66F));
    }

    public static Biome makeBlueAgateTaiga() {
        return buildBiome(
                createBuilder(
                        createAmbience(6851272, 9815527, 15381216),
                        blueAgateTaiga(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.HOWLITE_WOLF, 15, 2, 4))
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.BLUE_HOWLITE_WOLF, 1, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5)))
                ).temperature(0.4F));
    }

    public static Biome makeGreenAgateJungle() {
        return buildBiome(
                createBuilder(
                        createAmbience(4961870, 8437662, 15381216),
                        greenAgateJungle(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5))
                                        .addSpawn(MobCategory.AMBIENT, mobData(ModEntities.MARKUZAR_PLANT, 15, 2, 4)))
                ).temperature(0.75F));
    }

    public static Biome makePurpleAgateSwamp() {
        return buildBiome(
                createBuilder(
                        createAmbience(8417209, 11234801, 15381216),
                        purpleAgateSwamp(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.SPELLBOUND_ELEMENTAL, 10, 2, 4)))
                ).temperature(0.66F));
    }

    public static Biome makeFossilWoodland() {
        return buildBiome(
                createBuilder(
                        createAmbience(12298105, 13016408, 15381216),
                        fossilWoodland(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.ANCIENT_LAGRAHK, 10, 1, 2))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.ROCKY_LUGGEROTH, 10, 4, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.RUGGED_LURMORUS, 10, 1, 3)))
                ).temperature(0.66F));
    }

    public static Biome makeMutantAgateWildwood() {
        return buildBiome(
                createBuilder(
                        createAmbience(13948848, 15833793, 15381216),
                        mutantAgateWildwood(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 40, 3, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.MUTANT_GROWTH_EXTRACTOR, 5, 2, 4)))
                ).temperature(0.66F));
    }

    public static Biome makeVolcanicLands() {
        return buildBiome(
                createBuilder(
                        createAmbience(2302755, 4922905, 16086896),
                        volcanicLands(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.LESSER_SPITFIRE, 10, 2, 4))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 3)))
                ).temperature(0.9F));
    }

    public static Biome makeStaticWasteland() {
        return buildBiome(
                createBuilder(
                        createAmbience(2837910, 2633554, 5690794),
                        staticWasteland(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.LESSER_SHOCKSHOOTER, 10, 2, 4))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 3)))
                ).temperature(0.4F));
    }

    public static Biome makeGoldstoneLands() {
        return buildBiome(
                createBuilder(
                        createAmbience(2302755, 2236962, 12352044),
                        goldstoneLands(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.CORRUPT_SAPPER, 20, 2, 4))
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.CONTORTED_NAGA, 10, 2, 3)))
                ).temperature(0.55F));
    }

    public static Biome makeCrystalPlains() {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        crystalPlains(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 4, 6))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.CRYSTAL_GOLEM, 15, 1, 3)))
                ).temperature(0.66F));
    }

    public static Biome makeSaltDunes() {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        saltDunes(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 3))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.SALTION, 15, 1, 3)))
                ).temperature(0.8F));
    }

    public static Biome makeSmolderingBog() {
        return buildBiome(
                createBuilder(
                        createAmbience(2500135, 1118482, 3287859, 8284598),
                        smolderingBog(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.BISMUTH_ULETRUS, 20, 2, 3)))
                ).temperature(0.9F));
    }

    public static Biome makeShiningGrove() {
        return buildBiome(
                createBuilder(
                        createAmbience(7982765, 14546943, 15004627, 16764489),
                        shiningGrove(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1)))
                ).temperature(0.5F));
    }

    public static Biome makeMineralReservoir() {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.WATER_CREATURE, mobData(ModEntities.MINERAL_ARENTHIS, 10, 1, 4)))
                ).temperature(0.66F));
    }

    public static Biome makeSaltyCoast() {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.66F));
    }

    public static Biome makeMineralRiver() {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.66F));
    }

    public static Biome makeGoldenHills() {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenHillsBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.3F));
    }

    public static Biome makeGoldenForest() {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenForestBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.3F));
    }

    public static Biome makeGoldenPlains() {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenPlainsBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.3F));
    }

    public static Biome makeGoldenMarsh() {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenMarshBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.3F));
    }

    public static Biome makeGoldenSands() {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenSandsBiome(),
                        buildSpawns(createSpawns())
                ).temperature(0.7F));
    }

    public static Biome makeMookaite() {
        return buildBiome(
                createBuilder(
                        createAmbience(14646073, 16165141, 12793637),
                        BiomeGenerationSettings.EMPTY,
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.MOOKAITE_CONSTRUCT, 10, 1, 2))
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.OPALITE_CONSTRUCT, 10, 1, 2)))
                ).temperature(0.65F));
    }
}
