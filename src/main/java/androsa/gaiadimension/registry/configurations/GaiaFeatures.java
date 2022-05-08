package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.feature.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.feature.config.GaiaTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GaiaFeatures extends GaiaBiomeFeatures {

    public static class Config {
        public static final GaiaTreeFeatureConfig PINK_AGATE_TREE_CONFIG = configureTree(PINK_AGATE_LOG, PINK_AGATE_LEAVES, 5, ModBlocks.pink_agate_sapling.get());
        public static final GaiaTreeFeatureConfig BLUE_AGATE_TREE_CONFIG = configureTree(BLUE_AGATE_LOG, BLUE_AGATE_LEAVES, 6, ModBlocks.blue_agate_sapling.get());
        public static final GaiaTreeFeatureConfig GREEN_AGATE_TREE_CONFIG = configureTree(GREEN_AGATE_LOG, GREEN_AGATE_LEAVES, 10, ModBlocks.green_agate_sapling.get());
        public static final TreeConfiguration GREEN_AGATE_BUSH_CONFIG = (
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(GREEN_AGATE_LOG),
                        new StraightTrunkPlacer(1, 0, 0),
                        BlockStateProvider.simple(GREEN_AGATE_LEAVES),
                        new BushFoliagePlacer(
                                ConstantInt.of(2),
                                ConstantInt.of(1),
                                2),
                        new TwoLayersFeatureSize(0, 0, 0)))
                .build();
        public static final GaiaTreeFeatureConfig PURPLE_AGATE_TREE_CONFIG = configureTree(PURPLE_AGATE_LOG, PURPLE_AGATE_LEAVES, 7, ModBlocks.purple_agate_sapling.get());
        public static final GaiaTreeFeatureConfig FOSSILIZED_TREE_CONFIG = configureTree(FOSSIL_LOG, FOSSIL_LEAVES, 5, ModBlocks.fossilized_sapling.get());
        public static final GaiaTreeFeatureConfig CORRUPTED_TREE_CONFIG = configureTree(CORRUPTED_LOG, CORRUPTED_LEAVES, 7, ModBlocks.corrupted_sapling.get());
        public static final GaiaTreeFeatureConfig BURNT_TREE_CONFIG = configureTree(BURNT_LOG, BURNT_LEAVES, 5, ModBlocks.burnt_sapling.get());
        public static final GaiaTreeFeatureConfig BURNING_TREE_CONFIG = configureTree(BURNING_LOG, BURNING_LEAVES, 5, ModBlocks.burning_sapling.get());
        public static final GaiaTreeFeatureConfig AURA_TREE_CONFIG = configureTree(AURA_LOG, AURA_LEAVES, 10, ModBlocks.aura_sapling.get());

        public static GaiaTreeFeatureConfig configureTree(BlockState log, BlockState leaves, int height, SaplingBlock sapling) {
            return (new GaiaTreeFeatureConfig.Builder(BlockStateProvider.simple(log), BlockStateProvider.simple(leaves), height).setSapling(sapling)).build();
        }
    }

    public static class Configured {
        public static final HolderSet<PlacedFeature> BUSH_WORKAROUND = HolderSet.direct(PlacementUtils.inlinePlaced(Feature.TREE, Config.GREEN_AGATE_BUSH_CONFIG));

        //Lakes
        public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> lake_superhot_magma = registerFeature("lake_superhot_magma", ModWorldgen.POOL.get(), new BlockStateConfiguration(SUPERHOT_MAGMA));
        public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> lake_mineral_water = registerFeature("lake_mineral_water", ModWorldgen.POOL.get(), new BlockStateConfiguration(MINERAL_WATER));
        public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> lake_sweet_muck = registerFeature("lake_sweet_muck", ModWorldgen.POOL.get(), new BlockStateConfiguration(SWEET_MUCK));
        public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> lake_liquid_aura = registerFeature("lake_liquid_aura", ModWorldgen.POOL.get(), new BlockStateConfiguration(LIQUID_AURA));
        public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> lake_liquid_bismuth = registerFeature("lake_liquid_bismuth", ModWorldgen.POOL.get(), new BlockStateConfiguration(LIQUID_BISMUTH));

        //Local Modifications
        public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> gummy_glitter_blob = registerFeature("gummy_glitter_blob", ModWorldgen.GAIA_BLOB.get(), new BlockStateConfiguration(GUMMY_GLITTER));
        public static final Holder<ConfiguredFeature<FeatureHeightConfig, ?>> static_spikes = registerFeature("static_spikes", ModWorldgen.STATIC_SPIKE.get(), new FeatureHeightConfig(8));
        public static final Holder<ConfiguredFeature<FeatureHeightConfig, ?>> bismuth_spires = registerFeature("bismuth_spires", ModWorldgen.BISMUTH_SPIRE.get(), new FeatureHeightConfig(7));
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> bismuth_geysers = registerFeature("bismuth_geysers", ModWorldgen.BISMUTH_GEYSER.get(), FeatureConfiguration.NONE);

        //Underground Ores
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_primal_mass = registerFeature("ore_primal_mass", Feature.ORE, configureOre(GAIA_STONE, PRIMAL_MASS, 33));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_thick_glitter = registerFeature("ore_thick_glitter", Feature.ORE, configureOre(GAIA_STONE, THICK_GLITTER, 33));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_searing_rock = registerFeature("ore_searing_rock", Feature.ORE, configureOre(VOLCANIC, SEARING_ROCK, 33));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_static_stone = registerFeature("ore_static_stone", Feature.ORE, configureOre(STATIC, STATIC_STONE, 33));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_pebbles = registerFeature("ore_pebbles", Feature.ORE, configureOre(GAIA_STONE, PEBBLES, 25));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_speckled_rock = registerFeature("ore_speckled_rock", Feature.ORE, configureOre(GAIA_STONE, SPECKLED_ROCK, 8));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_coarse_rock = registerFeature("ore_coarse_rock", Feature.ORE, configureOre(GAIA_STONE, COARSE_ROCK, 8));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_precious_rock = registerFeature("ore_precious_rock", Feature.ORE, configureOre(GAIA_STONE, PRECIOUS_ROCK, 8));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_raw_amethyst = registerFeature("ore_raw_amethyst", Feature.ORE, configureOre(GAIA_STONE, RAW_AMETHYST, 12));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_raw_copal = registerFeature("ore_raw_copal", Feature.ORE, configureOre(GAIA_STONE, RAW_COPAL, 12));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_raw_jade = registerFeature("ore_raw_jade", Feature.ORE, configureOre(GAIA_STONE, RAW_JADE, 12));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_raw_jet = registerFeature("ore_raw_jet", Feature.ORE, configureOre(GAIA_STONE, RAW_JET, 12));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_sugilite = registerFeature("ore_sugilite", Feature.ORE, configureOre(GAIA_STONE, SUGILITE_ORE, 17));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_hematite = registerFeature("ore_hematite", Feature.ORE, configureOre(GAIA_STONE, HEMATITE_ORE, 17));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_pyrite = registerFeature("ore_pyrite", Feature.ORE, configureOre(GAIA_STONE, PYRITE_ORE, 9));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_cinnabar = registerFeature("ore_cinnabar", Feature.ORE, configureOre(GAIA_STONE, CINNABAR_ORE, 9));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_labradorite = registerFeature("ore_labradorite", Feature.ORE, configureOre(GAIA_STONE, LABRADORITE_ORE, 9));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_moonstone = registerFeature("ore_moonstone", Feature.ORE, configureOre(GAIA_STONE, MOONSTONE_ORE, 9));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_red_opal = registerFeature("ore_red_opal", Feature.ORE, configureOre(GAIA_STONE, RED_OPAL_ORE, 8));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_blue_opal = registerFeature("ore_blue_opal", Feature.ORE, configureOre(GAIA_STONE, BLUE_OPAL_ORE, 8));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_green_opal = registerFeature("ore_green_opal", Feature.ORE, configureOre(GAIA_STONE, GREEN_OPAL_ORE, 8));
        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_white_opal = registerFeature("ore_white_opal", Feature.ORE, configureOre(GAIA_STONE, WHITE_OPAL_ORE, 8));
        public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> disk_static_stone = registerFeature("disk_static_stone", ModWorldgen.GAIA_DISK.get(), new DiskConfiguration(STATIC_STONE, UniformInt.of(2, 4), 3, ImmutableList.of(WASTELAND_STONE)));
        public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> disk_bog_patch = registerFeature("disk_bog_patch", ModWorldgen.BOG_PATCH.get(), new DiskConfiguration(ModBlocks.impure_sludge.get().defaultBlockState(), UniformInt.of(1, 4), 2, Lists.newArrayList(MURKY_GRASS, BOGGY_SOIL)));

        //Underground Decoration
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> underground_glitter_blob = registerFeature("underground_glitter_blob", ModWorldgen.FRAIL_BLOB.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> cave_fungi = registerFeature("cave_fungi", Feature.RANDOM_PATCH, new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(weight().add(ELDER_IMKLIA, 2).add(GOLD_ORB_TUCHER, 2))), BlockPredicate.not(BlockPredicate.matchesBlocks(cave_blacklist)))));

        //Vegetal Decoration
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> aura_shoots = registerFeature("aura_shoots", ModWorldgen.AURA_SHOOT.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> normal_growth = registerFeature("crystal_growth", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> mutant_growth = registerFeature("mutant_crystal_growth", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_MUTANT)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> seared_growth = registerFeature("seared_crystal_growth", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_SEARED)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> corrupt_growth = registerFeature("corrupt_crystal_growth", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(CRYSTAL_GROWTH_RED, 2).add(CRYSTAL_GROWTH_BLACK, 2))));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> aura_growth = registerFeature("aura_crystal_growth", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_AURA)));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> common_bloom = registerFeature("common_bloom", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(THISCUS, 4).add(OUZIUM, 1))));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> rare_bloom = registerFeature("rare_bloom", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(OUZIUM, 4).add(THISCUS, 1))));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> mutant_bloom = registerFeature("mutant_bloom", Feature.RANDOM_PATCH, configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(OUZIUM, 4).add(AGATHUM, 1))));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> corrupt_bloom = registerFeature("corrupt_bloom", Feature.RANDOM_PATCH, configurePatch(64, 7, 3, BlockStateProvider.simple(CORRUPTED_VARLOOM)));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> kersei = registerFeature("spotted_kersei", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(SPOTTED_KERSEI)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> wiltha = registerFeature("thorny_wiltha", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(THORNY_WILTHA)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> agaric = registerFeature("roofed_agaric", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(ROOFED_AGARIC)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> hobina = registerFeature("bulbous_hobina", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(BULBOUS_HOBINA)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> cupsir = registerFeature("stickly_cupsir", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(STICKLY_CUPSIR)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> murgni = registerFeature("mystical_murgni", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(MYSTICAL_MURGNI)));
        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> corrupt_eye = registerFeature("corrupt_gaia_eye", Feature.RANDOM_PATCH, configurePatch(16, 7, 3, BlockStateProvider.simple(CORRUPTED_GAIA_EYE)));

        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> pink_agate_tree = registerFeature("pink_agate_tree", ModWorldgen.PINK_AGATE_TREE.get(), Config.PINK_AGATE_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> blue_agate_tree = registerFeature("blue_agate_tree", ModWorldgen.BLUE_AGATE_TREE.get(), Config.BLUE_AGATE_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> green_agate_tree = registerFeature("green_agate_tree", ModWorldgen.GREEN_AGATE_TREE.get(), Config.GREEN_AGATE_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> purple_agate_tree = registerFeature("purple_agate_tree", ModWorldgen.PURPLE_AGATE_TREE.get(), Config.PURPLE_AGATE_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> fossilized_tree = registerFeature("fossilized_tree", ModWorldgen.FOSSILIZED_TREE.get(), Config.FOSSILIZED_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> goldstone_tree = registerFeature("goldstone_tree", ModWorldgen.GOLDSTONE_TREE.get(), Config.CORRUPTED_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> burnt_agate_tree = registerFeature("burnt_agate_tree", ModWorldgen.BURNT_AGATE_TREE.get(), Config.BURNT_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> fiery_agate_tree = registerFeature("fiery_agate_tree", ModWorldgen.FIERY_AGATE_TREE.get(), Config.BURNING_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> aura_tree = registerFeature("aura_tree", ModWorldgen.AURA_TREE.get(), Config.AURA_TREE_CONFIG);
        public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> green_agate_bush = registerFeature("green_agate_bush", Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(BUSH_WORKAROUND));

        private static SimpleWeightedRandomList.Builder<BlockState> weight() {
            return SimpleWeightedRandomList.builder();
        }

        public static OreConfiguration configureOre(RuleTest test, BlockState ore, int count) {
            return new OreConfiguration(test, ore, count);
        }

        public static RandomPatchConfiguration configurePatch(int tries, int xzspread, int yspread, BlockStateProvider provider) {
            return new RandomPatchConfiguration(tries, xzspread, yspread, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider)));
        }

        private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> registerFeature(String name, F feature, FC config) {
            ConfiguredFeature<FC, ?> configuredFeature = new ConfiguredFeature<>(feature, config);
            return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name).toString(), configuredFeature);
        }
    }

    public static class Placed {
        //Lakes
        public static final Holder<PlacedFeature> LAKE_SUPERHOT_MAGMA_COMMON = registerPlacedFeature("lake_superhot_magma_common", Configured.lake_superhot_magma,
                RarityFilter.onAverageOnceEvery(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                EnvironmentScanPlacement.scanningFor(
                        Direction.DOWN,
                        BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                        32),
                SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_SUPERHOT_MAGMA_RARE = registerPlacedFeature("lake_superhot_magma_rare", Configured.lake_superhot_magma,
                RarityFilter.onAverageOnceEvery(80),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                EnvironmentScanPlacement.scanningFor(
                        Direction.DOWN,
                        BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                        32),
                SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_MINERAL_WATER_COMMON = registerPlacedFeature("lake_mineral_water_common", Configured.lake_mineral_water,
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_MINERAL_WATER_UNCOMMON = registerPlacedFeature("lake_mineral_water_uncommon", Configured.lake_mineral_water,
                RarityFilter.onAverageOnceEvery(40),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_MINERAL_WATER_RARE = registerPlacedFeature("lake_mineral_water_rare", Configured.lake_mineral_water,
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_SWEET_MUCK = registerPlacedFeature("lake_sweet_muck", Configured.lake_sweet_muck,
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_LIQUID_AURA = registerPlacedFeature("lake_liquid_aura", Configured.lake_liquid_aura,
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> LAKE_LIQUID_BISMUTH = registerPlacedFeature("lake_liquid_bismuth", Configured.lake_liquid_bismuth,
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        //Local Modifications
        public static final Holder<PlacedFeature> GUMMY_GLITTER_BLOB = registerPlacedFeature("gummy_glitter_blob", Configured.gummy_glitter_blob,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(2),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> STATIC_SPIKES = registerPlacedFeature("static_spikes", Configured.static_spikes,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(2),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> BISMUTH_SPIRES = registerPlacedFeature("bismuth_spires", Configured.bismuth_spires,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(2),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> BISMUTH_GEYSERS = registerPlacedFeature("bismuth_geysers", Configured.bismuth_geysers,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(2),
                BiomeFilter.biome());

        //Underground Ores
        public static final Holder<PlacedFeature> ORE_PRIMAL_MASS = placedOre("ore_primal_mass", Configured.ore_primal_mass, 25, 33);
        public static final Holder<PlacedFeature> ORE_THICK_GLITTER = placedOre("ore_thick_glitter", Configured.ore_thick_glitter, 100, 9);
        public static final Holder<PlacedFeature> ORE_SEARING_ROCK = placedOre("ore_searing_rock", Configured.ore_searing_rock, 100, 9);
        public static final Holder<PlacedFeature> ORE_STATIC_STONE = placedOre("ore_static_stone", Configured.ore_static_stone, 100, 9);
        public static final Holder<PlacedFeature> ORE_PEBBLES = placedOre("ore_pebbles", Configured.ore_pebbles, 128, 25);
        public static final Holder<PlacedFeature> ORE_SPECKLED_ROCK = placedOre("ore_speckled_rock", Configured.ore_speckled_rock, 120, 10);
        public static final Holder<PlacedFeature> ORE_COARSE_ROCK = placedOre("ore_coarse_rock", Configured.ore_coarse_rock, 60, 10);
        public static final Holder<PlacedFeature> ORE_PRECIOUS_ROCK = placedOre("ore_precious_rock", Configured.ore_precious_rock, 30, 10);
        public static final Holder<PlacedFeature> ORE_RAW_AMETHYST = placedOre("ore_raw_amethyst", Configured.ore_raw_amethyst, 120, 15);
        public static final Holder<PlacedFeature> ORE_RAW_COPAL = placedOre("ore_raw_copal", Configured.ore_raw_copal, 120, 15);
        public static final Holder<PlacedFeature> ORE_RAW_JADE = placedOre("ore_raw_jade", Configured.ore_raw_jade, 120, 15);
        public static final Holder<PlacedFeature> ORE_RAW_JET = placedOre("ore_raw_jet", Configured.ore_raw_jet, 120, 15);
        public static final Holder<PlacedFeature> ORE_SUGILITE = placedOre("ore_sugilite", Configured.ore_sugilite, 100, 8);
        public static final Holder<PlacedFeature> ORE_HEMATITE = placedOre("ore_hematite", Configured.ore_hematite, 100, 8);
        public static final Holder<PlacedFeature> ORE_PYRITE = placedOre("ore_pyrite", Configured.ore_pyrite, 80, 8);
        public static final Holder<PlacedFeature> ORE_CINNABAR = placedOre("ore_cinnabar", Configured.ore_cinnabar, 60, 7);
        public static final Holder<PlacedFeature> ORE_LABRADORITE = placedOre("ore_labradorite", Configured.ore_labradorite, 40, 6);
        public static final Holder<PlacedFeature> ORE_MOONSTONE = placedOre("ore_moonstone", Configured.ore_moonstone, 40, 6);
        public static final Holder<PlacedFeature> ORE_RED_OPAL = placedOre("ore_red_opal", Configured.ore_red_opal, 30, 4);
        public static final Holder<PlacedFeature> ORE_BLUE_OPAL = placedOre("ore_blue_opal", Configured.ore_blue_opal, 30, 4);
        public static final Holder<PlacedFeature> ORE_GREEN_OPAL = placedOre("ore_green_opal", Configured.ore_green_opal, 30, 4);
        public static final Holder<PlacedFeature> ORE_WHITE_OPAL_COMMON = placedOre("ore_white_opal_common", Configured.ore_white_opal, 25, 4);
        public static final Holder<PlacedFeature> ORE_WHITE_OPAL_RARE = placedOre("ore_white_opal_rare", Configured.ore_white_opal, 20, 3);
        public static final Holder<PlacedFeature> DISK_STATIC_STONE = registerPlacedFeature("disk_static_stone", Configured.disk_static_stone,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_TOP_SOLID,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> DISK_BOG_PATCH = registerPlacedFeature("disk_bog_patch", Configured.disk_bog_patch,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_TOP_SOLID,
                BiomeFilter.biome());

        //Underground Decoration
        public static final Holder<PlacedFeature> UNDERGROUND_GLITTER_BLOB = registerPlacedFeature("underground_glitter_blob", Configured.underground_glitter_blob,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(70)),
                InSquarePlacement.spread(),
                CountPlacement.of(100),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> CRYSTAL_FUNGI_CAVES = registerPlacedFeature("crystal_fungi_caves", Configured.cave_fungi,
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()),
                RarityFilter.onAverageOnceEvery(2),
                BiomeFilter.biome());

        //Vegetal Decoration
        public static final Holder<PlacedFeature> PINK_AGATE_TREE_COMMON = placedTree("pink_agate_tree_common", Configured.pink_agate_tree, 4, 0.1F, 1, ModBlocks.pink_agate_sapling);
        public static final Holder<PlacedFeature> PINK_AGATE_TREE_RARE = placedTree("pink_agate_tree_rare", Configured.pink_agate_tree, 0, 0.1F, 1, ModBlocks.pink_agate_sapling);
        public static final Holder<PlacedFeature> BLUE_AGATE_TREE = placedTree("blue_agate_tree", Configured.blue_agate_tree, 1, 0.1F, 1, ModBlocks.blue_agate_sapling);
        public static final Holder<PlacedFeature> GREEN_AGATE_TREE = placedTree("green_agate_tree", Configured.green_agate_tree, 5, 0.1F, 1, ModBlocks.green_agate_sapling);
        public static final Holder<PlacedFeature> PURPLE_AGATE_TREE = placedTree("purple_agate_tree", Configured.purple_agate_tree, 1, 0.1F, 2, ModBlocks.purple_agate_sapling);
        public static final Holder<PlacedFeature> FOSSILIZED_TREE = placedTree("fossilized_tree", Configured.fossilized_tree, 1, 0.1F, 1, ModBlocks.fossilized_sapling);
        public static final Holder<PlacedFeature> GOLDSTONE_TREE = placedTree("goldstone_tree", Configured.goldstone_tree, 1, 0.1F, 1, ModBlocks.corrupted_sapling);
        public static final Holder<PlacedFeature> BURNT_AGATE_TREE = placedTree("burnt_agate_tree", Configured.burnt_agate_tree, 0, 0.1F, 1, ModBlocks.burnt_sapling);
        public static final Holder<PlacedFeature> FIERY_AGATE_TREE = placedTree("fiery_agate_tree", Configured.fiery_agate_tree, 0, 0.1F, 1, ModBlocks.burning_sapling);
        public static final Holder<PlacedFeature> AURA_TREE = placedTree("aura_tree", Configured.aura_tree, 2, 0.1F, 1, ModBlocks.aura_sapling);
        public static final Holder<PlacedFeature> GREEN_AGATE_BUSH = registerPlacedFeature("green_agate_bush", Configured.green_agate_bush,
                CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> AURA_SHOOTS = registerPlacedFeature("aura_shoots", Configured.aura_shoots,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(6),
                BiomeFilter.biome());
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_02 = placedPlant("crystal_growth_02", Configured.normal_growth, 2);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_03 = placedPlant("crystal_growth_03", Configured.normal_growth, 3);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_04 = placedPlant("crystal_growth_04", Configured.normal_growth, 4);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_05 = placedPlant("crystal_growth_05", Configured.normal_growth, 5);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_SEARED = placedPlant("crystal_growth_seared", Configured.seared_growth, 1);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_CORRUPT = placedPlant("crystal_growth_corrupt", Configured.corrupt_growth, 1);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_AURA = placedPlant("crystal_growth_aura", Configured.aura_growth, 2);
        public static final Holder<PlacedFeature> CRYSTAL_GROWTH_MUTANT = placedPlant("crystal_growth_mutant", Configured.mutant_growth, 2);
        public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_COMMON = placedPlant("crystal_blooms_common", Configured.common_bloom, 2);
        public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_RARE = placedPlant("crystal_blooms_rare", Configured.rare_bloom, 2);
        public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_MUTANT = placedPlant("crystal_blooms_mutant", Configured.mutant_bloom, 2);
        public static final Holder<PlacedFeature> CRYSTAL_BLOOMS_CORRUPT = placedPlant("crystal_blooms_corrupt", Configured.corrupt_bloom, 1);
        public static final Holder<PlacedFeature> SPOTTED_KERSEI = placedFungi("spotted_kersei", Configured.kersei, 1);
        public static final Holder<PlacedFeature> THORNY_WILTHA = placedFungi("thorny_wiltha", Configured.wiltha, 1);
        public static final Holder<PlacedFeature> ROOFED_AGARIC = placedFungi("roofed_agaric", Configured.agaric, 1);
        public static final Holder<PlacedFeature> BULBOUS_HOBINA = placedFungi("bulbous_hobina", Configured.hobina, 1);
        public static final Holder<PlacedFeature> STICKLY_CUPSIR = placedFungi("stickly_cupsir", Configured.cupsir, 1);
        public static final Holder<PlacedFeature> MYSTICAL_MURGNI = placedFungi("mystical_murgni", Configured.murgni, 1);
        public static final Holder<PlacedFeature> CORRUPTED_GAIA_EYE = placedFungi("corrupted_gaia_eye", Configured.corrupt_eye, 1);

        public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> various_agate_trees = Configured.registerFeature("various_agate_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(Placed.PINK_AGATE_TREE_COMMON, 0.25F),
                new WeightedPlacedFeature(Placed.BLUE_AGATE_TREE, 0.25F),
                new WeightedPlacedFeature(Placed.GREEN_AGATE_TREE, 0.25F),
                new WeightedPlacedFeature(Placed.PURPLE_AGATE_TREE, 0.25F)),
                Placed.CRYSTAL_GROWTH_MUTANT));
        public static final Holder<PlacedFeature> VARIOUS_AGATE_TREES = registerPlacedFeature("various_agate_trees", various_agate_trees,
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                PlacementUtils.countExtra(2, 0.1F, 1),
                BiomeFilter.biome());

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

        private static Holder<PlacedFeature> registerPlacedFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
            PlacedFeature placedFeature = new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers));
            return BuiltinRegistries.registerExact(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name).toString(), placedFeature);
        }
    }
}
