package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.RegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public final class GaiaPlacedFeatures extends GaiaBiomeFeatures {

    //Lakes
    public static final PlacedFeature LAKE_SUPERHOT_MAGMA_COMMON = registerPlacedFeature("lake_superhot_magma_common", GaiaConfiguredFeatures.lake_superhot_magma
            .placed(
                    RarityFilter.onAverageOnceEvery(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                            32),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_SUPERHOT_MAGMA_RARE = registerPlacedFeature("lake_superhot_magma_rare", GaiaConfiguredFeatures.lake_superhot_magma
            .placed(
                    RarityFilter.onAverageOnceEvery(80),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                            32),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_MINERAL_WATER_COMMON = registerPlacedFeature("lake_mineral_water_common", GaiaConfiguredFeatures.lake_mineral_water
            .placed(
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_MINERAL_WATER_UNCOMMON = registerPlacedFeature("lake_mineral_water_uncommon", GaiaConfiguredFeatures.lake_mineral_water
            .placed(
                    RarityFilter.onAverageOnceEvery(40),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_MINERAL_WATER_RARE = registerPlacedFeature("lake_mineral_water_rare", GaiaConfiguredFeatures.lake_mineral_water
            .placed(
                    RarityFilter.onAverageOnceEvery(50),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_SWEET_MUCK = registerPlacedFeature("lake_sweet_muck", GaiaConfiguredFeatures.lake_sweet_muck
            .placed(
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_LIQUID_AURA = registerPlacedFeature("lake_liquid_aura", GaiaConfiguredFeatures.lake_liquid_aura
            .placed(
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
    public static final PlacedFeature LAKE_LIQUID_BISMUTH = registerPlacedFeature("lake_liquid_bismuth", GaiaConfiguredFeatures.lake_liquid_bismuth
            .placed(
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));

    //Local Modifications
    public static final PlacedFeature GUMMY_GLITTER_BLOB = registerPlacedFeature("gummy_glitter_blob", GaiaConfiguredFeatures.gummy_glitter_blob.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome()));
    public static final PlacedFeature STATIC_SPIKES = registerPlacedFeature("static_spikes", GaiaConfiguredFeatures.static_spikes.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome()));
    public static final PlacedFeature BISMUTH_SPIRES = registerPlacedFeature("bismuth_spires", GaiaConfiguredFeatures.bismuth_spires.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome()));
    public static final PlacedFeature BISMUTH_GEYSERS = registerPlacedFeature("bismuth_geysers", GaiaConfiguredFeatures.bismuth_geysers.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome()));

    //Underground Ores
    public static final PlacedFeature ORE_PRIMAL_MASS = registerPlacedFeature("ore_primal_mass", placedOre(GaiaConfiguredFeatures.ore_primal_mass, 25, 33));
    public static final PlacedFeature ORE_THICK_GLITTER = registerPlacedFeature("ore_thick_glitter", placedOre(GaiaConfiguredFeatures.ore_thick_glitter, 100, 9));
    public static final PlacedFeature ORE_SEARING_ROCK = registerPlacedFeature("ore_searing_rock", placedOre(GaiaConfiguredFeatures.ore_searing_rock, 100, 9));
    public static final PlacedFeature ORE_STATIC_STONE = registerPlacedFeature("ore_static_stone", placedOre(GaiaConfiguredFeatures.ore_static_stone, 100, 9));
    public static final PlacedFeature ORE_PEBBLES = registerPlacedFeature("ore_pebbles", placedOre(GaiaConfiguredFeatures.ore_pebbles, 128, 25));
    public static final PlacedFeature ORE_SPECKLED_ROCK = registerPlacedFeature("ore_speckled_rock", placedOre(GaiaConfiguredFeatures.ore_speckled_rock, 120, 10));
    public static final PlacedFeature ORE_COARSE_ROCK = registerPlacedFeature("ore_coarse_rock", placedOre(GaiaConfiguredFeatures.ore_coarse_rock, 60, 10));
    public static final PlacedFeature ORE_PRECIOUS_ROCK = registerPlacedFeature("ore_precious_rock", placedOre(GaiaConfiguredFeatures.ore_precious_rock, 30, 10));
    public static final PlacedFeature ORE_RAW_AMETHYST = registerPlacedFeature("ore_raw_amethyst", placedOre(GaiaConfiguredFeatures.ore_raw_amethyst, 120, 15));
    public static final PlacedFeature ORE_RAW_COPAL = registerPlacedFeature("ore_raw_copal", placedOre(GaiaConfiguredFeatures.ore_raw_copal, 120, 15));
    public static final PlacedFeature ORE_RAW_JADE = registerPlacedFeature("ore_raw_jade", placedOre(GaiaConfiguredFeatures.ore_raw_jade, 120, 15));
    public static final PlacedFeature ORE_RAW_JET = registerPlacedFeature("ore_raw_jet", placedOre(GaiaConfiguredFeatures.ore_raw_jet, 120, 15));
    public static final PlacedFeature ORE_SUGILITE = registerPlacedFeature("ore_sugilite", placedOre(GaiaConfiguredFeatures.ore_sugilite, 100, 8));
    public static final PlacedFeature ORE_HEMATITE = registerPlacedFeature("ore_hematite", placedOre(GaiaConfiguredFeatures.ore_hematite, 100, 8));
    public static final PlacedFeature ORE_PYRITE = registerPlacedFeature("ore_pyrite", placedOre(GaiaConfiguredFeatures.ore_pyrite, 80, 8));
    public static final PlacedFeature ORE_CINNABAR = registerPlacedFeature("ore_cinnabar", placedOre(GaiaConfiguredFeatures.ore_cinnabar, 60, 7));
    public static final PlacedFeature ORE_LABRADORITE = registerPlacedFeature("ore_labradorite", placedOre(GaiaConfiguredFeatures.ore_labradorite, 40, 6));
    public static final PlacedFeature ORE_MOONSTONE = registerPlacedFeature("ore_moonstone", placedOre(GaiaConfiguredFeatures.ore_moonstone, 40, 6));
    public static final PlacedFeature ORE_RED_OPAL = registerPlacedFeature("ore_red_opal", placedOre(GaiaConfiguredFeatures.ore_red_opal, 30, 4));
    public static final PlacedFeature ORE_BLUE_OPAL = registerPlacedFeature("ore_blue_opal", placedOre(GaiaConfiguredFeatures.ore_blue_opal, 30, 4));
    public static final PlacedFeature ORE_GREEN_OPAL = registerPlacedFeature("ore_green_opal", placedOre(GaiaConfiguredFeatures.ore_green_opal, 30, 4));
    public static final PlacedFeature ORE_WHITE_OPAL_COMMON = registerPlacedFeature("ore_white_opal_common", placedOre(GaiaConfiguredFeatures.ore_white_opal, 25, 4));
    public static final PlacedFeature ORE_WHITE_OPAL_RARE = registerPlacedFeature("ore_white_opal_rare", placedOre(GaiaConfiguredFeatures.ore_white_opal, 20, 3));
    public static final PlacedFeature DISK_STATIC_STONE = registerPlacedFeature("disk_static_stone", GaiaConfiguredFeatures.disk_static_stone.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()));
    public static final PlacedFeature DISK_BOG_PATCH = registerPlacedFeature("disk_bog_patch", GaiaConfiguredFeatures.disk_bog_patch.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()));

    //Underground Decoration
    public static final PlacedFeature UNDERGROUND_GLITTER_BLOB = registerPlacedFeature("underground_glitter_blob", GaiaConfiguredFeatures.underground_glitter_blob.placed(
            HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(70)),
            InSquarePlacement.spread(),
            CountPlacement.of(100),
            BiomeFilter.biome()));
    public static final PlacedFeature CRYSTAL_FUNGI_CAVES = registerPlacedFeature("crystal_fungi_caves", GaiaConfiguredFeatures.cave_fungi.placed(
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()),
            RarityFilter.onAverageOnceEvery(2),
            BiomeFilter.biome()));

    //Vegetal Decoration
    public static final PlacedFeature PINK_AGATE_TREE_COMMON = registerPlacedFeature("pink_agate_tree_common", placedTree(GaiaConfiguredFeatures.pink_agate_tree, 4, 0.1F, 1, ModBlocks.pink_agate_sapling));
    public static final PlacedFeature PINK_AGATE_TREE_RARE = registerPlacedFeature("pink_agate_tree_rare", placedTree(GaiaConfiguredFeatures.pink_agate_tree, 0, 0.1F, 1, ModBlocks.pink_agate_sapling));
    public static final PlacedFeature BLUE_AGATE_TREE = registerPlacedFeature("blue_agate_tree", placedTree(GaiaConfiguredFeatures.blue_agate_tree, 1, 0.1F, 1, ModBlocks.blue_agate_sapling));
    public static final PlacedFeature GREEN_AGATE_TREE = registerPlacedFeature("green_agate_tree", placedTree(GaiaConfiguredFeatures.green_agate_tree, 5, 0.1F, 1, ModBlocks.green_agate_sapling));
    public static final PlacedFeature PURPLE_AGATE_TREE = registerPlacedFeature("purple_agate_tree", placedTree(GaiaConfiguredFeatures.purple_agate_tree, 1, 0.1F, 2, ModBlocks.purple_agate_sapling));
    public static final PlacedFeature FOSSILIZED_TREE = registerPlacedFeature("fossilized_tree", placedTree(GaiaConfiguredFeatures.fossilized_tree, 1, 0.1F, 1, ModBlocks.fossilized_sapling));
    public static final PlacedFeature GOLDSTONE_TREE = registerPlacedFeature("goldstone_tree", placedTree(GaiaConfiguredFeatures.goldstone_tree, 1, 0.1F, 1, ModBlocks.corrupted_sapling));
    public static final PlacedFeature BURNT_AGATE_TREE = registerPlacedFeature("burnt_agate_tree", placedTree(GaiaConfiguredFeatures.burnt_agate_tree, 0, 0.1F, 1, ModBlocks.burnt_sapling));
    public static final PlacedFeature FIERY_AGATE_TREE = registerPlacedFeature("fiery_agate_tree", placedTree(GaiaConfiguredFeatures.fiery_agate_tree, 0, 0.1F, 1, ModBlocks.burning_sapling));
    public static final PlacedFeature AURA_TREE = registerPlacedFeature("aura_tree", placedTree(GaiaConfiguredFeatures.aura_tree, 2, 0.1F, 1, ModBlocks.aura_sapling));
    public static final PlacedFeature GREEN_AGATE_BUSH = registerPlacedFeature("green_agate_bush", GaiaConfiguredFeatures.green_agate_bush.placed(
            CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()));
    public static final PlacedFeature VARIOUS_AGATE_TREES = registerPlacedFeature("various_agate_trees", GaiaConfiguredFeatures.various_agate_trees.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            PlacementUtils.countExtra(2, 0.1F, 1),
            BiomeFilter.biome()));
    public static final PlacedFeature AURA_SHOOTS = registerPlacedFeature("aura_shoots", GaiaConfiguredFeatures.aura_shoots.placed(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(6),
            BiomeFilter.biome()));
    public static final PlacedFeature CRYSTAL_GROWTH_02 = registerPlacedFeature("crystal_growth_02", placedPlant(GaiaConfiguredFeatures.normal_growth, 2));
    public static final PlacedFeature CRYSTAL_GROWTH_03 = registerPlacedFeature("crystal_growth_03", placedPlant(GaiaConfiguredFeatures.normal_growth, 3));
    public static final PlacedFeature CRYSTAL_GROWTH_04 = registerPlacedFeature("crystal_growth_04", placedPlant(GaiaConfiguredFeatures.normal_growth, 4));
    public static final PlacedFeature CRYSTAL_GROWTH_05 = registerPlacedFeature("crystal_growth_05", placedPlant(GaiaConfiguredFeatures.normal_growth, 5));
    public static final PlacedFeature CRYSTAL_GROWTH_SEARED = registerPlacedFeature("crystal_growth_seared", placedPlant(GaiaConfiguredFeatures.seared_growth, 1));
    public static final PlacedFeature CRYSTAL_GROWTH_CORRUPT = registerPlacedFeature("crystal_growth_corrupt", placedPlant(GaiaConfiguredFeatures.corrupt_growth, 1));
    public static final PlacedFeature CRYSTAL_GROWTH_AURA = registerPlacedFeature("crystal_growth_aura", placedPlant(GaiaConfiguredFeatures.aura_growth, 2));
    public static final PlacedFeature CRYSTAL_GROWTH_MUTANT = registerPlacedFeature("crystal_growth_mutant", placedPlant(GaiaConfiguredFeatures.mutant_growth, 2));
    public static final PlacedFeature CRYSTAL_BLOOMS_COMMON = registerPlacedFeature("crystal_blooms_common", placedPlant(GaiaConfiguredFeatures.common_bloom, 2));
    public static final PlacedFeature CRYSTAL_BLOOMS_RARE = registerPlacedFeature("crystal_blooms_rare", placedPlant(GaiaConfiguredFeatures.rare_bloom, 2));
    public static final PlacedFeature CRYSTAL_BLOOMS_MUTANT = registerPlacedFeature("crystal_blooms_mutant", placedPlant(GaiaConfiguredFeatures.mutant_bloom, 2));
    public static final PlacedFeature CRYSTAL_BLOOMS_CORRUPT = registerPlacedFeature("crystal_blooms_corrupt", placedPlant(GaiaConfiguredFeatures.corrupt_bloom, 1));
    public static final PlacedFeature SPOTTED_KERSEI = registerPlacedFeature("spotted_kersei", placedFungi(GaiaConfiguredFeatures.kersei, 1));
    public static final PlacedFeature THORNY_WILTHA = registerPlacedFeature("thorny_wiltha", placedFungi(GaiaConfiguredFeatures.wiltha, 1));
    public static final PlacedFeature ROOFED_AGARIC = registerPlacedFeature("roofed_agaric", placedFungi(GaiaConfiguredFeatures.agaric, 1));
    public static final PlacedFeature BULBOUS_HOBINA = registerPlacedFeature("bulbous_hobina", placedFungi(GaiaConfiguredFeatures.hobina, 1));
    public static final PlacedFeature STICKLY_CUPSIR = registerPlacedFeature("stickly_cupsir", placedFungi(GaiaConfiguredFeatures.cupsir, 1));
    public static final PlacedFeature MYSTICAL_MURGNI = registerPlacedFeature("mystical_murgni", placedFungi(GaiaConfiguredFeatures.murgni, 1));
    public static final PlacedFeature CORRUPTED_GAIA_EYE = registerPlacedFeature("corrupted_gaia_eye", placedFungi(GaiaConfiguredFeatures.corrupt_eye, 1));

    private static PlacedFeature placedOre(ConfiguredFeature<?,?> ore, int height, int count) {
        return ore.placed(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(height)),
                BiomeFilter.biome());
    }

    public static PlacedFeature placedTree(ConfiguredFeature<?,?> tree, int count, float chance, int extra, RegistryObject<SaplingBlock> sapling) {
        return tree.placed(
                PlacementUtils.countExtra(count, chance, extra),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(sapling.get().defaultBlockState(), BlockPos.ZERO)),
                InSquarePlacement.spread(),
                VegetationPlacements.TREE_THRESHOLD,
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome());
    }

    private static PlacedFeature placedPlant(ConfiguredFeature<?,?> growth, int count) {
        return growth.placed(
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                CountPlacement.of(count));
    }

    private static PlacedFeature placedFungi(ConfiguredFeature<?,?> patch, int count) {
        return patch.placed(
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(count),
                BiomeFilter.biome());
    }

    private static PlacedFeature registerPlacedFeature(String name, PlacedFeature feature) {
        RegistryHelper.PLACED_FEATURES.put(feature, name);
        return feature;
    }

    public static void registerPlacedFeatures(Registry<PlacedFeature> registry) {
        for (Map.Entry<PlacedFeature, String> entry : RegistryHelper.PLACED_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
