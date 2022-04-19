package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.RegistryHelper;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
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
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

public class GaiaPlacedFeatures extends GaiaBiomeFeatures {

    //Lakes
    public static final Holder<PlacedFeature> LAKE_SUPERHOT_MAGMA_COMMON = registerPlacedFeature("lake_superhot_magma_common", GaiaConfiguredFeatures.lake_superhot_magma,
                    RarityFilter.onAverageOnceEvery(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                            32),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_SUPERHOT_MAGMA_RARE = registerPlacedFeature("lake_superhot_magma_rare", GaiaConfiguredFeatures.lake_superhot_magma,
                    RarityFilter.onAverageOnceEvery(80),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                            32),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_MINERAL_WATER_COMMON = registerPlacedFeature("lake_mineral_water_common", GaiaConfiguredFeatures.lake_mineral_water,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_MINERAL_WATER_UNCOMMON = registerPlacedFeature("lake_mineral_water_uncommon", GaiaConfiguredFeatures.lake_mineral_water,
                    RarityFilter.onAverageOnceEvery(40),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_MINERAL_WATER_RARE = registerPlacedFeature("lake_mineral_water_rare", GaiaConfiguredFeatures.lake_mineral_water,
                    RarityFilter.onAverageOnceEvery(50),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_SWEET_MUCK = registerPlacedFeature("lake_sweet_muck", GaiaConfiguredFeatures.lake_sweet_muck,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_LIQUID_AURA = registerPlacedFeature("lake_liquid_aura", GaiaConfiguredFeatures.lake_liquid_aura,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_LIQUID_BISMUTH = registerPlacedFeature("lake_liquid_bismuth", GaiaConfiguredFeatures.lake_liquid_bismuth,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome());

    //Local Modifications
    public static final Holder<PlacedFeature> GUMMY_GLITTER_BLOB = registerPlacedFeature("gummy_glitter_blob", GaiaConfiguredFeatures.gummy_glitter_blob,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> STATIC_SPIKES = registerPlacedFeature("static_spikes", GaiaConfiguredFeatures.static_spikes,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> BISMUTH_SPIRES = registerPlacedFeature("bismuth_spires", GaiaConfiguredFeatures.bismuth_spires,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> BISMUTH_GEYSERS = registerPlacedFeature("bismuth_geysers", GaiaConfiguredFeatures.bismuth_geysers,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(2),
            BiomeFilter.biome());

    //Underground Ores
    public static final Holder<PlacedFeature> ORE_PRIMAL_MASS = placedOre("ore_primal_mass", GaiaConfiguredFeatures.ore_primal_mass, 25, 33);
    public static final Holder<PlacedFeature> ORE_THICK_GLITTER = placedOre("ore_thick_glitter", GaiaConfiguredFeatures.ore_thick_glitter, 100, 9);
    public static final Holder<PlacedFeature> ORE_SEARING_ROCK = placedOre("ore_searing_rock", GaiaConfiguredFeatures.ore_searing_rock, 100, 9);
    public static final Holder<PlacedFeature> ORE_STATIC_STONE = placedOre("ore_static_stone", GaiaConfiguredFeatures.ore_static_stone, 100, 9);
    public static final Holder<PlacedFeature> ORE_PEBBLES = placedOre("ore_pebbles", GaiaConfiguredFeatures.ore_pebbles, 128, 25);
    public static final Holder<PlacedFeature> ORE_SPECKLED_ROCK = placedOre("ore_speckled_rock", GaiaConfiguredFeatures.ore_speckled_rock, 120, 10);
    public static final Holder<PlacedFeature> ORE_COARSE_ROCK = placedOre("ore_coarse_rock", GaiaConfiguredFeatures.ore_coarse_rock, 60, 10);
    public static final Holder<PlacedFeature> ORE_PRECIOUS_ROCK = placedOre("ore_precious_rock", GaiaConfiguredFeatures.ore_precious_rock, 30, 10);
    public static final Holder<PlacedFeature> ORE_RAW_AMETHYST = placedOre("ore_raw_amethyst", GaiaConfiguredFeatures.ore_raw_amethyst, 120, 15);
    public static final Holder<PlacedFeature> ORE_RAW_COPAL = placedOre("ore_raw_copal", GaiaConfiguredFeatures.ore_raw_copal, 120, 15);
    public static final Holder<PlacedFeature> ORE_RAW_JADE = placedOre("ore_raw_jade", GaiaConfiguredFeatures.ore_raw_jade, 120, 15);
    public static final Holder<PlacedFeature> ORE_RAW_JET = placedOre("ore_raw_jet", GaiaConfiguredFeatures.ore_raw_jet, 120, 15);
    public static final Holder<PlacedFeature> ORE_SUGILITE = placedOre("ore_sugilite", GaiaConfiguredFeatures.ore_sugilite, 100, 8);
    public static final Holder<PlacedFeature> ORE_HEMATITE = placedOre("ore_hematite", GaiaConfiguredFeatures.ore_hematite, 100, 8);
    public static final Holder<PlacedFeature> ORE_PYRITE = placedOre("ore_pyrite", GaiaConfiguredFeatures.ore_pyrite, 80, 8);
    public static final Holder<PlacedFeature> ORE_CINNABAR = placedOre("ore_cinnabar", GaiaConfiguredFeatures.ore_cinnabar, 60, 7);
    public static final Holder<PlacedFeature> ORE_LABRADORITE = placedOre("ore_labradorite", GaiaConfiguredFeatures.ore_labradorite, 40, 6);
    public static final Holder<PlacedFeature> ORE_MOONSTONE = placedOre("ore_moonstone", GaiaConfiguredFeatures.ore_moonstone, 40, 6);
    public static final Holder<PlacedFeature> ORE_RED_OPAL = placedOre("ore_red_opal", GaiaConfiguredFeatures.ore_red_opal, 30, 4);
    public static final Holder<PlacedFeature> ORE_BLUE_OPAL = placedOre("ore_blue_opal", GaiaConfiguredFeatures.ore_blue_opal, 30, 4);
    public static final Holder<PlacedFeature> ORE_GREEN_OPAL = placedOre("ore_green_opal", GaiaConfiguredFeatures.ore_green_opal, 30, 4);
    public static final Holder<PlacedFeature> ORE_WHITE_OPAL_COMMON = placedOre("ore_white_opal_common", GaiaConfiguredFeatures.ore_white_opal, 25, 4);
    public static final Holder<PlacedFeature> ORE_WHITE_OPAL_RARE = placedOre("ore_white_opal_rare", GaiaConfiguredFeatures.ore_white_opal, 20, 3);
    public static final Holder<PlacedFeature> DISK_STATIC_STONE = registerPlacedFeature("disk_static_stone", GaiaConfiguredFeatures.disk_static_stone,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_BOG_PATCH = registerPlacedFeature("disk_bog_patch", GaiaConfiguredFeatures.disk_bog_patch,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome());

    //Underground Decoration
    public static final Holder<PlacedFeature> UNDERGROUND_GLITTER_BLOB = registerPlacedFeature("underground_glitter_blob", GaiaConfiguredFeatures.underground_glitter_blob,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(70)),
            InSquarePlacement.spread(),
            CountPlacement.of(100),
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> CRYSTAL_FUNGI_CAVES = registerPlacedFeature("crystal_fungi_caves", GaiaConfiguredFeatures.cave_fungi,
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()),
            RarityFilter.onAverageOnceEvery(2),
            BiomeFilter.biome());

    //Vegetal Decoration
    public static final Holder<PlacedFeature> PINK_AGATE_TREE_COMMON = placedTree("pink_agate_tree_common", GaiaConfiguredFeatures.pink_agate_tree, 4, 0.1F, 1, ModBlocks.pink_agate_sapling);
    public static final Holder<PlacedFeature> PINK_AGATE_TREE_RARE = placedTree("pink_agate_tree_rare", GaiaConfiguredFeatures.pink_agate_tree, 0, 0.1F, 1, ModBlocks.pink_agate_sapling);
    public static final Holder<PlacedFeature> BLUE_AGATE_TREE = placedTree("blue_agate_tree", GaiaConfiguredFeatures.blue_agate_tree, 1, 0.1F, 1, ModBlocks.blue_agate_sapling);
    public static final Holder<PlacedFeature> GREEN_AGATE_TREE = placedTree("green_agate_tree", GaiaConfiguredFeatures.green_agate_tree, 5, 0.1F, 1, ModBlocks.green_agate_sapling);
    public static final Holder<PlacedFeature> PURPLE_AGATE_TREE = placedTree("purple_agate_tree", GaiaConfiguredFeatures.purple_agate_tree, 1, 0.1F, 2, ModBlocks.purple_agate_sapling);
    public static final Holder<PlacedFeature> FOSSILIZED_TREE = placedTree("fossilized_tree", GaiaConfiguredFeatures.fossilized_tree, 1, 0.1F, 1, ModBlocks.fossilized_sapling);
    public static final Holder<PlacedFeature> GOLDSTONE_TREE = placedTree("goldstone_tree", GaiaConfiguredFeatures.goldstone_tree, 1, 0.1F, 1, ModBlocks.corrupted_sapling);
    public static final Holder<PlacedFeature> BURNT_AGATE_TREE = placedTree("burnt_agate_tree", GaiaConfiguredFeatures.burnt_agate_tree, 0, 0.1F, 1, ModBlocks.burnt_sapling);
    public static final Holder<PlacedFeature> FIERY_AGATE_TREE = placedTree("fiery_agate_tree", GaiaConfiguredFeatures.fiery_agate_tree, 0, 0.1F, 1, ModBlocks.burning_sapling);
    public static final Holder<PlacedFeature> AURA_TREE = placedTree("aura_tree", GaiaConfiguredFeatures.aura_tree, 2, 0.1F, 1, ModBlocks.aura_sapling);
    public static final Holder<PlacedFeature> GREEN_AGATE_BUSH = registerPlacedFeature("green_agate_bush", GaiaConfiguredFeatures.green_agate_bush,
            CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> VARIOUS_AGATE_TREES = registerPlacedFeature("various_agate_trees", GaiaConfiguredFeatures.various_agate_trees,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            PlacementUtils.countExtra(2, 0.1F, 1),
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> AURA_SHOOTS = registerPlacedFeature("aura_shoots", GaiaConfiguredFeatures.aura_shoots,
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(6),
            BiomeFilter.biome());
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_02 = placedPlant("crystal_growth_02", GaiaConfiguredFeatures.normal_growth, 2);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_03 = placedPlant("crystal_growth_03", GaiaConfiguredFeatures.normal_growth, 3);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_04 = placedPlant("crystal_growth_04", GaiaConfiguredFeatures.normal_growth, 4);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_05 = placedPlant("crystal_growth_05", GaiaConfiguredFeatures.normal_growth, 5);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_SEARED = placedPlant("crystal_growth_seared", GaiaConfiguredFeatures.seared_growth, 1);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_CORRUPT = placedPlant("crystal_growth_corrupt", GaiaConfiguredFeatures.corrupt_growth, 1);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_AURA = placedPlant("crystal_growth_aura", GaiaConfiguredFeatures.aura_growth, 2);
    public static final Holder<PlacedFeature> CRYSTAL_GROWTH_MUTANT = placedPlant("crystal_growth_mutant", GaiaConfiguredFeatures.mutant_growth, 2);
    public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_COMMON = placedPlant("crystal_blooms_common", GaiaConfiguredFeatures.common_bloom, 2);
    public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_RARE = placedPlant("crystal_blooms_rare", GaiaConfiguredFeatures.rare_bloom, 2);
    public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_MUTANT = placedPlant("crystal_blooms_mutant", GaiaConfiguredFeatures.mutant_bloom, 2);
    public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_CORRUPT = placedPlant("crystal_blooms_corrupt", GaiaConfiguredFeatures.corrupt_bloom, 1);
    public static final Holder<PlacedFeature> SPOTTED_KERSEI = placedFungi("spotted_kersei", GaiaConfiguredFeatures.kersei, 1);
    public static final Holder<PlacedFeature> THORNY_WILTHA = placedFungi("thorny_wiltha", GaiaConfiguredFeatures.wiltha, 1);
    public static final Holder<PlacedFeature> ROOFED_AGARIC = placedFungi("roofed_agaric", GaiaConfiguredFeatures.agaric, 1);
    public static final Holder<PlacedFeature> BULBOUS_HOBINA = placedFungi("bulbous_hobina", GaiaConfiguredFeatures.hobina, 1);
    public static final Holder<PlacedFeature> STICKLY_CUPSIR = placedFungi("stickly_cupsir", GaiaConfiguredFeatures.cupsir, 1);
    public static final Holder<PlacedFeature> MYSTICAL_MURGNI = placedFungi("mystical_murgni", GaiaConfiguredFeatures.murgni, 1);
    public static final Holder<PlacedFeature> CORRUPTED_GAIA_EYE = placedFungi("corrupted_gaia_eye", GaiaConfiguredFeatures.corrupt_eye, 1);

    private static Holder<PlacedFeature> placedOre(String name, Holder<ConfiguredFeature<OreConfiguration,?>> ore, int height, int count) {
        return registerPlacedFeature(name, ore,
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(height)),
                BiomeFilter.biome());
    }

    public static Holder<PlacedFeature> placedTree(String name, Holder<ConfiguredFeature<GaiaTreeFeatureConfig,?>> tree, int count, float chance, int extra, RegistryObject<SaplingBlock> sapling) {
        return registerPlacedFeature(name, tree,
                PlacementUtils.countExtra(count, chance, extra),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(sapling.get().defaultBlockState(), BlockPos.ZERO)),
                InSquarePlacement.spread(),
                VegetationPlacements.TREE_THRESHOLD,
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome());
    }

    private static Holder<PlacedFeature> placedPlant(String name, Holder<ConfiguredFeature<RandomPatchConfiguration,?>> growth, int count) {
        return registerPlacedFeature(name, growth,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                CountPlacement.of(count));
    }

    private static Holder<PlacedFeature> placedFungi(String name, Holder<ConfiguredFeature<RandomPatchConfiguration,?>> patch, int count) {
        return registerPlacedFeature(name, patch,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(count),
                BiomeFilter.biome());
    }

    private static PlacedFeature registerPlacedFeature(String name, PlacedFeature feature) {
        RegistryHelper.PLACED_FEATURES.put(feature, name);
        return feature;
    }

    private static Holder<PlacedFeature> registerPlacedFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        PlacedFeature placedFeature = new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers));
        RegistryHelper.PLACED_FEATURES.put(placedFeature, name);
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name), placedFeature);
    }

    public static void registerPlacedFeatures(Registry<PlacedFeature> registry) {
        for (Map.Entry<PlacedFeature, String> entry : RegistryHelper.PLACED_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
