package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBiomeProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.world.chunk.warp.TerrainPoint;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class GaiaBiomeMaker extends GaiaBiomeProvider {

    public static void init(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(GaiaBiomes.pink_agate_forest, makePinkAgateForest(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.blue_agate_taiga, makeBlueAgateTaiga(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.green_agate_jungle, makeGreenAgateJungle(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.purple_agate_swamp, makePurpleAgateSwamp(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.fossil_woodland, makeFossilWoodland(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.mutant_agate_wildwood, makeMutantAgateWildwood(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.volcanic_lands, makeVolcanicLands(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.static_wasteland, makeStaticWasteland(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.goldstone_lands, makeGoldstoneLands(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.crystal_plains, makeCrystalPlains(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.salt_dunes, makeSaltDunes(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.mookaite_mesa, makeMookaite(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.shining_grove, makeShiningGrove(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.smoldering_bog, makeSmolderingBog(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.mineral_reservoir, makeMineralReservoir(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.mineral_river, makeMineralRiver(placedFeatures, worldCarvers));

        context.register(GaiaBiomes.golden_forest, makeGoldenForest(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.golden_plains, makeGoldenPlains(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.golden_hills, makeGoldenHills(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.golden_sands, makeGoldenSands(placedFeatures, worldCarvers));
        context.register(GaiaBiomes.golden_marsh, makeGoldenMarsh(placedFeatures, worldCarvers));
    }

    public static List<Pair<TerrainPoint, Holder<Biome>>> makeBiomeList(HolderGetter<Biome> registry) {
        return List.of(
                pairBiome(registry, 0.1F, 0.2F, GaiaBiomes.blue_agate_taiga),
                pairBiome(registry, 0.05F, 0.05F, GaiaBiomes.crystal_plains),
                pairBiome(registry, 0.1F, 0.05F, GaiaBiomes.fossil_woodland),
                pairBiome(registry, 0.125F, 0.05F, GaiaBiomes.goldstone_lands),
                pairBiome(registry, 0.1F, 0.2F, GaiaBiomes.green_agate_jungle),
                pairBiome(registry, 0.35F, 0.15F, GaiaBiomes.golden_forest),
                pairBiome(registry, 0.8F, 0.5F, GaiaBiomes.golden_hills),
                pairBiome(registry, 0.15F, 0.05F, GaiaBiomes.golden_marsh),
                pairBiome(registry, 0.35F, 0.1F, GaiaBiomes.golden_plains),
                pairBiome(registry, 0.25F, 0.05F, GaiaBiomes.golden_sands),
                pairBiome(registry, -1.8F, 0.1F, GaiaBiomes.mineral_reservoir),
                pairBiome(registry, -0.8F, 0.0F, GaiaBiomes.mineral_river),
                pairBiome(registry, 2.0F, 0.075F, GaiaBiomes.mookaite_mesa),
                pairBiome(registry, 0.1F, 0.1F, GaiaBiomes.mutant_agate_wildwood),
                pairBiome(registry, 0.1F, 0.1F, GaiaBiomes.pink_agate_forest),
                pairBiome(registry, 0.0F, 0.05F, GaiaBiomes.purple_agate_swamp),
                pairBiome(registry, 0.2F, 0.05F, GaiaBiomes.salt_dunes),
                pairBiome(registry, 0.4F, 0.05F, GaiaBiomes.shining_grove),
                pairBiome(registry, 0.2F, 0.02F, GaiaBiomes.smoldering_bog),
                pairBiome(registry, 3.0F, 0.05F, GaiaBiomes.static_wasteland),
                pairBiome(registry, 1.0F, 0.7F, GaiaBiomes.volcanic_lands)
        );
    }

    private static Pair<TerrainPoint, Holder<Biome>> pairBiome(HolderGetter<Biome> registry, float depth, float scale, ResourceKey<Biome> biome) {
        return Pair.of(new TerrainPoint(depth, scale), registry.getOrThrow(biome));
    }

    public static Biome makePinkAgateForest(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        pinkAgateForest(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AGATE_GOLEM, 15, 1, 3)))
                ).temperature(0.66F));
    }

    public static Biome makeBlueAgateTaiga(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(6851272, 9815527, 15381216),
                        blueAgateTaiga(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.HOWLITE_WOLF, 15, 2, 4))
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.BLUE_HOWLITE_WOLF, 1, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5)))
                ).temperature(0.4F));
    }

    public static Biome makeGreenAgateJungle(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(4961870, 8437662, 15381216),
                        greenAgateJungle(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5))
                                        .addSpawn(MobCategory.AMBIENT, mobData(ModEntities.MARKUZAR_PLANT, 15, 2, 4)))
                ).temperature(0.75F));
    }

    public static Biome makePurpleAgateSwamp(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(8417209, 11234801, 15381216),
                        purpleAgateSwamp(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 3, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.SPELLBOUND_ELEMENTAL, 10, 2, 4)))
                ).temperature(0.66F));
    }

    public static Biome makeFossilWoodland(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(12298105, 13016408, 15381216),
                        fossilWoodland(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.ANCIENT_LAGRAHK, 10, 1, 2))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.ROCKY_LUGGEROTH, 10, 4, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.RUGGED_LURMORUS, 10, 1, 3)))
                ).temperature(0.66F));
    }

    public static Biome makeMutantAgateWildwood(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(13948848, 15833793, 15381216),
                        mutantAgateWildwood(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 40, 3, 5))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.MUTANT_GROWTH_EXTRACTOR, 5, 2, 4)))
                ).temperature(0.66F));
    }

    public static Biome makeVolcanicLands(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(2302755, 4922905, 16086896),
                        volcanicLands(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.LESSER_SPITFIRE, 10, 2, 4))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 3)))
                ).temperature(0.9F));
    }

    public static Biome makeStaticWasteland(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(2837910, 2633554, 5690794),
                        staticWasteland(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.LESSER_SHOCKSHOOTER, 10, 2, 4))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 3)))
                ).temperature(0.4F));
    }

    public static Biome makeGoldstoneLands(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(2302755, 2236962, 12352044),
                        goldstoneLands(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.CORRUPT_SAPPER, 20, 2, 4))
                                        .addSpawn(MobCategory.MONSTER, mobData(ModEntities.CONTORTED_NAGA, 10, 2, 3)))
                ).temperature(0.55F));
    }

    public static Biome makeCrystalPlains(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        crystalPlains(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_SAPPER, 20, 4, 6))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.CRYSTAL_GOLEM, 15, 1, 3)))
                ).temperature(0.66F));
    }

    public static Biome makeSaltDunes(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        saltDunes(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 3))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.SALTION, 15, 1, 3)))
                ).temperature(0.8F));
    }

    public static Biome makeSmolderingBog(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(2500135, 1118482, 3287859, 8284598),
                        smolderingBog(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1))
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.BISMUTH_ULETRUS, 20, 2, 3)))
                ).temperature(0.9F));
    }

    public static Biome makeShiningGrove(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(7982765, 14546943, 15004627, 16764489),
                        shiningGrove(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.CREATURE, mobData(ModEntities.NOMADIC_LAGRAHK, 15, 1, 1)))
                ).temperature(0.5F));
    }

    public static Biome makeMineralReservoir(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(features, carvers),
                        buildSpawns(
                                createSpawns()
                                        .addSpawn(MobCategory.WATER_CREATURE, mobData(ModEntities.MINERAL_ARENTHIS, 10, 1, 4)))
                ).temperature(0.66F));
    }

    public static Biome makeSaltyCoast(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(features, carvers),
                        buildSpawns(createSpawns())
                ).temperature(0.66F));
    }

    public static Biome makeMineralRiver(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(features, carvers),
                        buildSpawns(createSpawns())
                ).temperature(0.66F));
    }

    public static Biome makeGoldenHills(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenHillsBiome(features, carvers),
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AUREATE_EVRAUN, 20, 1, 2)))
                ).temperature(0.3F));
    }

    public static Biome makeGoldenForest(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenForestBiome(features, carvers),
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_GRAZER, 20, 2, 4))
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AUREATE_EVRAUN, 20, 1, 2)))
                ).temperature(0.3F));
    }

    public static Biome makeGoldenPlains(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenPlainsBiome(features, carvers),
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.GROWTH_GRAZER, 20, 2, 4))
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AUREATE_EVRAUN, 20, 1, 2)))
                ).temperature(0.3F));
    }

    public static Biome makeGoldenMarsh(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenMarshBiome(features, carvers),
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AUREATE_EVRAUN, 20, 1, 2)))
                ).temperature(0.3F));
    }

    public static Biome makeGoldenSands(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(4997150, 3415307, 13801728),
                        goldenSandsBiome(features, carvers),
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.AUREATE_EVRAUN, 20, 1, 2)))
                ).temperature(0.7F));
    }

    public static Biome makeMookaite(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return buildBiome(
                createBuilder(
                        createAmbience(14646073, 16165141, 12793637),
                        mookaiteMesa(features, carvers),
                        buildSpawns(createSpawns()
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.MOOKAITE_CONSTRUCT, 10, 1, 2))
                                .addSpawn(MobCategory.CREATURE, mobData(ModEntities.OPALITE_CONSTRUCT, 10, 1, 2)))
                ).temperature(0.65F));
    }
}
