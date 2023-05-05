package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.feature.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.feature.config.TwoBlockStateConfig;
import androsa.gaiadimension.world.gen.feature.decorator.GoldenVineDecorator;
import androsa.gaiadimension.world.gen.feature.foliage.BulbFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.foliage.CappedFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.foliage.CubeFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.foliage.ThickFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.trunk.CardinalTrunkPlacer;
import androsa.gaiadimension.world.gen.feature.trunk.FourBranchTrunkPlacer;
import androsa.gaiadimension.world.gen.feature.trunk.ThickTrunkPlacer;
import androsa.gaiadimension.world.gen.feature.trunk.VaryingFourBranchTrunkPlacer;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
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
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GaiaFeatures extends GaiaBiomeFeatures {

    public static class Config {
        public static final TreeConfiguration PINK_AGATE_TREE_CONFIG = configureTree(PINK_AGATE_LOG, new StraightTrunkPlacer(5, 3, 3), PINK_AGATE_LEAVES, new CappedFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1)), 1, 0, 1, HEAVY_SOIL);
        public static final TreeConfiguration BLUE_AGATE_TREE_CONFIG = configureTree(BLUE_AGATE_LOG, new StraightTrunkPlacer(6, 2, 1), BLUE_AGATE_LEAVES, new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), 2, 0, 2, HEAVY_SOIL);
        public static final TreeConfiguration GREEN_AGATE_TREE_CONFIG = configureTree(GREEN_AGATE_LOG, new ThickTrunkPlacer(10, 3, 3), GREEN_AGATE_LEAVES, new ThickFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1)), 1, 0, 1, HEAVY_SOIL);
        public static final TreeConfiguration GREEN_AGATE_BUSH_CONFIG = configureTree(GREEN_AGATE_LOG, new StraightTrunkPlacer(1, 0, 0), GREEN_AGATE_LEAVES, new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), 0, 0, 0, HEAVY_SOIL);
        public static final TreeConfiguration PURPLE_AGATE_TREE_CONFIG = configureTree(PURPLE_AGATE_LOG, new CardinalTrunkPlacer(7, 3, 3), PURPLE_AGATE_LEAVES, new BulbFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1)), 1, 0, 1, HEAVY_SOIL);
        public static final TreeConfiguration FOSSILIZED_TREE_CONFIG = configureTree(FOSSIL_LOG, new StraightTrunkPlacer(5, 3, 3), FOSSIL_LEAVES, new CappedFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1)), 1, 0, 1, HEAVY_SOIL);
        public static final TreeConfiguration CORRUPTED_TREE_CONFIG = configureTree(CORRUPTED_LOG, new StraightTrunkPlacer(7, 4, 0), CORRUPTED_LEAVES, new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)), 2, 0, 2, CORRUPT_SOIL);
        public static final TreeConfiguration BURNT_TREE_CONFIG = configureTree(BURNT_LOG, new StraightTrunkPlacer(5, 3, 3), BURNT_LEAVES, new CappedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), 1, 0, 1, HEAVY_SOIL);
        public static final TreeConfiguration BURNING_TREE_CONFIG = configureTree(BURNING_LOG, new StraightTrunkPlacer(5, 3, 3), BURNING_LEAVES, new CappedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), 1, 0, 1, HEAVY_SOIL);
        public static final TreeConfiguration AURA_TREE_CONFIG = configureTree(AURA_LOG, new FourBranchTrunkPlacer(10, 3, 3), AURA_LEAVES, new CappedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), 1, 0, 1, LIGHT_SOIL);
        public static final TreeConfiguration SMALL_GOLDEN_TREE_CONFIG = configureTree(GOLDEN_LOG, new VaryingFourBranchTrunkPlacer(7, 2, 2), GOLDEN_LEAVES, new CubeFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1)), 1, 0, 1, AURUM_SOIL);
        public static final TreeConfiguration SMALL_GOLDEN_TREE_VINES_CONFIG = configureTree(GOLDEN_LOG, new VaryingFourBranchTrunkPlacer(7, 2, 2), GOLDEN_LEAVES, new CubeFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1)), 1, 0, 1, AURUM_SOIL, ImmutableList.of(GoldenVineDecorator.INSTANCE));
        public static final TreeConfiguration BIG_GOLDEN_TREE_CONFIG = configureTree(GOLDEN_LOG, new VaryingFourBranchTrunkPlacer(9, 3, 4), GOLDEN_LEAVES, new CubeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), 1, 0, 1, AURUM_SOIL);

        public static TreeConfiguration configureTree(BlockState log, TrunkPlacer trunk, BlockState leaves, FoliagePlacer foliage, int limit, int lower, int upper, BlockState dirt) {
            return configureTree(log, trunk, leaves, foliage, limit, lower, upper, dirt, ImmutableList.of());
        }

        public static TreeConfiguration configureTree(BlockState log, TrunkPlacer trunk, BlockState leaves, FoliagePlacer foliage, int limit, int lower, int upper, BlockState dirt, List<TreeDecorator> decorators) {
            TreeConfiguration.TreeConfigurationBuilder builder = new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(log),
                    trunk,
                    BlockStateProvider.simple(leaves),
                    foliage,
                    new TwoLayersFeatureSize(limit, lower, upper))
                    .dirt(BlockStateProvider.simple(dirt));
            if (!decorators.isEmpty()) builder.decorators(decorators);

            return builder.build();
        }
    }

    public static class Configured {
        public static final HolderSet<PlacedFeature> BUSH_WORKAROUND = HolderSet.direct(PlacementUtils.inlinePlaced(ModWorldgen.STRICT_TREE.get(), Config.GREEN_AGATE_BUSH_CONFIG));

        //Lakes
        public static final ResourceKey<ConfiguredFeature<?, ?>> lake_superhot_magma = registerFeature("lake_superhot_magma");
        public static final ResourceKey<ConfiguredFeature<?, ?>> lake_mineral_water = registerFeature("lake_mineral_water");
        public static final ResourceKey<ConfiguredFeature<?, ?>> lake_sweet_muck = registerFeature("lake_sweet_muck");
        public static final ResourceKey<ConfiguredFeature<?, ?>> lake_liquid_aura = registerFeature("lake_liquid_aura");
        public static final ResourceKey<ConfiguredFeature<?, ?>> lake_liquid_bismuth = registerFeature("lake_liquid_bismuth");

        //Local Modifications
        public static final ResourceKey<ConfiguredFeature<?, ?>> gummy_glitter_blob = registerFeature("gummy_glitter_blob");
        public static final ResourceKey<ConfiguredFeature<?, ?>> static_spikes = registerFeature("static_spikes");
        public static final ResourceKey<ConfiguredFeature<?, ?>> bismuth_spires = registerFeature("bismuth_spires");
        public static final ResourceKey<ConfiguredFeature<?, ?>> bismuth_geysers = registerFeature("bismuth_geysers");
        public static final ResourceKey<ConfiguredFeature<?, ?>> brilliant_stone_spikes = registerFeature("brilliant_stone_spikes");
        public static final ResourceKey<ConfiguredFeature<?, ?>> balancing_rocks = registerFeature("balancing_rocks");
        public static final ResourceKey<ConfiguredFeature<?, ?>> tough_golden_stone_monolith = registerFeature("tough_golden_stone_monolith");
        public static final ResourceKey<ConfiguredFeature<?, ?>> tough_golden_stone_menhir = registerFeature("tough_golden_stone_menhir");
        public static final ResourceKey<ConfiguredFeature<?, ?>> marsh_lake = registerFeature("marsh_lake");

        //Underground Ores
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_primal_mass = registerFeature("ore_primal_mass");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_thick_glitter = registerFeature("ore_thick_glitter");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_searing_rock = registerFeature("ore_searing_rock");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_static_stone = registerFeature("ore_static_stone");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_pebbles = registerFeature("ore_pebbles");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_speckled_rock = registerFeature("ore_speckled_rock");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_coarse_rock = registerFeature("ore_coarse_rock");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_precious_rock = registerFeature("ore_precious_rock");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_raw_amethyst = registerFeature("ore_raw_amethyst");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_raw_copal = registerFeature("ore_raw_copal");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_raw_jade = registerFeature("ore_raw_jade");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_raw_jet = registerFeature("ore_raw_jet");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_sugilite = registerFeature("ore_sugilite");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_hematite = registerFeature("ore_hematite");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_pyrite = registerFeature("ore_pyrite");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_cinnabar = registerFeature("ore_cinnabar");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_labradorite = registerFeature("ore_labradorite");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_moonstone = registerFeature("ore_moonstone");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_red_opal = registerFeature("ore_red_opal");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_blue_opal = registerFeature("ore_blue_opal");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_green_opal = registerFeature("ore_green_opal");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ore_white_opal = registerFeature("ore_white_opal");
        public static final ResourceKey<ConfiguredFeature<?, ?>> disk_static_stone = registerFeature("disk_static_stone");
        public static final ResourceKey<ConfiguredFeature<?, ?>> disk_bog_patch = registerFeature("disk_bog_patch");
        public static final ResourceKey<ConfiguredFeature<?, ?>> disk_gilded_stone = registerFeature("disk_gilded_brilliant_stone");
        public static final ResourceKey<ConfiguredFeature<?, ?>> disk_marsh_patch = registerFeature("disk_marsh_patch");

        //Underground Decoration
        public static final ResourceKey<ConfiguredFeature<?, ?>> underground_glitter_blob = registerFeature("underground_glitter_blob");
        public static final ResourceKey<ConfiguredFeature<?, ?>> cave_fungi = registerFeature("cave_fungi");

        //Vegetal Decoration
        public static final ResourceKey<ConfiguredFeature<?, ?>> aura_shoots = registerFeature("aura_shoots");
        public static final ResourceKey<ConfiguredFeature<?, ?>> normal_growth = registerFeature("crystal_growth");
        public static final ResourceKey<ConfiguredFeature<?, ?>> mutant_growth = registerFeature("mutant_crystal_growth");
        public static final ResourceKey<ConfiguredFeature<?, ?>> seared_growth = registerFeature("seared_crystal_growth");
        public static final ResourceKey<ConfiguredFeature<?, ?>> corrupt_growth = registerFeature("corrupt_crystal_growth");
        public static final ResourceKey<ConfiguredFeature<?, ?>> aura_growth = registerFeature("aura_crystal_growth");
        public static final ResourceKey<ConfiguredFeature<?, ?>> golden_grass = registerFeature("golden_grass");
        public static final ResourceKey<ConfiguredFeature<?, ?>> tall_golden_grass = registerFeature("tall_golden_grass");
        public static final ResourceKey<ConfiguredFeature<?, ?>> golden_vines = registerFeature("golden_vines");
        public static final ResourceKey<ConfiguredFeature<?, ?>> sombre_cacti = registerFeature("sombre_cacti");
        public static final ResourceKey<ConfiguredFeature<?, ?>> sombre_shrub = registerFeature("sombre_shrub");

        public static final ResourceKey<ConfiguredFeature<?, ?>> common_bloom = registerFeature("common_bloom");
        public static final ResourceKey<ConfiguredFeature<?, ?>> rare_bloom = registerFeature("rare_bloom");
        public static final ResourceKey<ConfiguredFeature<?, ?>> mutant_bloom = registerFeature("mutant_bloom");
        public static final ResourceKey<ConfiguredFeature<?, ?>> corrupt_bloom = registerFeature("corrupt_bloom");
        public static final ResourceKey<ConfiguredFeature<?, ?>> golden_bloom = registerFeature("glamelea_bloom");

        public static final ResourceKey<ConfiguredFeature<?, ?>> kersei = registerFeature("spotted_kersei");
        public static final ResourceKey<ConfiguredFeature<?, ?>> wiltha = registerFeature("thorny_wiltha");
        public static final ResourceKey<ConfiguredFeature<?, ?>> agaric = registerFeature("roofed_agaric");
        public static final ResourceKey<ConfiguredFeature<?, ?>> hobina = registerFeature("bulbous_hobina");
        public static final ResourceKey<ConfiguredFeature<?, ?>> cupsir = registerFeature("stickly_cupsir");
        public static final ResourceKey<ConfiguredFeature<?, ?>> murgni = registerFeature("mystical_murgni");
        public static final ResourceKey<ConfiguredFeature<?, ?>> corrupt_eye = registerFeature("corrupt_gaia_eye");
        public static final ResourceKey<ConfiguredFeature<?, ?>> gilsri = registerFeature("twinkling_gilsri");

        public static final ResourceKey<ConfiguredFeature<?, ?>> pink_agate_tree = registerFeature("pink_agate_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> blue_agate_tree = registerFeature("blue_agate_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> green_agate_tree = registerFeature("green_agate_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> purple_agate_tree = registerFeature("purple_agate_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> fossilized_tree = registerFeature("fossilized_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> goldstone_tree = registerFeature("goldstone_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> burnt_agate_tree = registerFeature("burnt_agate_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> fiery_agate_tree = registerFeature("fiery_agate_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> aura_tree = registerFeature("aura_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> small_golden_tree = registerFeature("small_golden_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> small_golden_tree_vines = registerFeature("small_golden_tree_vines");
        public static final ResourceKey<ConfiguredFeature<?, ?>> big_golden_tree = registerFeature("big_golden_tree");

        public static final ResourceKey<ConfiguredFeature<?, ?>> pink_agate_trees = registerFeature("pink_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> blue_agate_trees = registerFeature("blue_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> green_agate_trees = registerFeature("green_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> purple_agate_trees = registerFeature("purple_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> fossilized_trees = registerFeature("fossilized_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> goldstone_trees = registerFeature("goldstone_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> burnt_agate_trees = registerFeature("burnt_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> fiery_agate_trees = registerFeature("fiery_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> aura_trees = registerFeature("aura_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> small_golden_trees_with_vines = registerFeature("small_golden_trees_with_vines");
        public static final ResourceKey<ConfiguredFeature<?, ?>> green_agate_bush = registerFeature("green_agate_bush");
        public static final ResourceKey<ConfiguredFeature<?, ?>> various_agate_trees = registerFeature("various_agate_trees");
        public static final ResourceKey<ConfiguredFeature<?, ?>> golden_trees = registerFeature("golden_trees");

        private static SimpleWeightedRandomList.Builder<BlockState> weight() {
            return SimpleWeightedRandomList.builder();
        }

        public static OreConfiguration configureOre(RuleTest test, BlockState ore, int count) {
            return new OreConfiguration(test, ore, count);
        }

        public static RandomPatchConfiguration configurePatch(int tries, int xzspread, int yspread, BlockStateProvider provider) {
            return new RandomPatchConfiguration(tries, xzspread, yspread, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider)));
        }

        private static ResourceKey<ConfiguredFeature<?, ?>> registerFeature(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name));
        }

        private static ConfiguredFeature<BlockStateConfiguration, ?> poolFeature(BlockState state) {
            return new ConfiguredFeature<>(ModWorldgen.POOL.get(), new BlockStateConfiguration(state));
        }

        private static ConfiguredFeature<OreConfiguration, ?> oreFeature(RuleTest test, BlockState ore, int count) {
            return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(test, ore, count));
        }

        private static DiskConfiguration diskConfig(BlockState state, BlockPredicate target, IntProvider range, int height) {
            return new DiskConfiguration(RuleBasedBlockStateProvider.simple(BlockStateProvider.simple(state)), target, range, height);
        }

        private static ConfiguredFeature<RandomPatchConfiguration, ?> patchFeature(int tries, int xzspread, int yspread, BlockStateProvider provider) {
            return new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(tries, xzspread, yspread, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider))));
        }

        private static ConfiguredFeature<TreeConfiguration, ?> treeFeature(TreeConfiguration config) {
            return new ConfiguredFeature<>(ModWorldgen.STRICT_TREE.get(), config);
        }

        private static <FC extends FeatureConfiguration, F extends Feature<FC>> ConfiguredFeature<FC, ?> registerFeature(F feature, FC config) {
            return new ConfiguredFeature<>(feature, config);
        }

        public static void init(BootstapContext<ConfiguredFeature<?, ?>> context) {
            HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
            HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

            //Lakes
            context.register(lake_superhot_magma, poolFeature(SUPERHOT_MAGMA));
            context.register(lake_mineral_water, poolFeature(MINERAL_WATER));
            context.register(lake_sweet_muck, poolFeature(SWEET_MUCK));
            context.register(lake_liquid_aura, poolFeature(LIQUID_AURA));
            context.register(lake_liquid_bismuth, poolFeature(LIQUID_BISMUTH));

            //Local Modifications
            context.register(gummy_glitter_blob, registerFeature(ModWorldgen.GAIA_BLOB.get(), new BlockStateConfiguration(GUMMY_GLITTER)));
            context.register(static_spikes, registerFeature(ModWorldgen.STATIC_SPIKE.get(), new FeatureHeightConfig(8)));
            context.register(bismuth_spires, registerFeature(ModWorldgen.BISMUTH_SPIRE.get(), new FeatureHeightConfig(7)));
            context.register(bismuth_geysers, registerFeature(ModWorldgen.BISMUTH_GEYSER.get(), FeatureConfiguration.NONE));
            context.register(brilliant_stone_spikes, registerFeature(ModWorldgen.TERRAIN_SPIKE.get(), new BlockStateConfiguration(BRILLIANT_STONE)));
            context.register(balancing_rocks, registerFeature(ModWorldgen.BALANCING_ROCKS.get(), new TwoBlockStateConfig(GOLDEN_STONE, TOUGH_GOLDEN_STONE)));
            context.register(tough_golden_stone_monolith, registerFeature(ModWorldgen.MONOLITH.get(), new BlockStateConfiguration(TOUGH_GOLDEN_STONE)));
            context.register(tough_golden_stone_menhir, registerFeature(ModWorldgen.MENHIR.get(), new BlockStateConfiguration(TOUGH_GOLDEN_STONE)));
            context.register(marsh_lake, registerFeature(ModWorldgen.MARSH_LAKE.get(), FeatureConfiguration.NONE));

            //Underground Ores
            context.register(ore_primal_mass, oreFeature(GAIA_STONE, PRIMAL_MASS, 33));
            context.register(ore_thick_glitter, oreFeature(GAIA_STONE, THICK_GLITTER, 33));
            context.register(ore_searing_rock, oreFeature(VOLCANIC, SEARING_ROCK, 33));
            context.register(ore_static_stone, oreFeature(STATIC, STATIC_STONE, 33));
            context.register(ore_pebbles, oreFeature(GAIA_STONE, PEBBLES, 25));
            context.register(ore_speckled_rock, oreFeature(GAIA_STONE, SPECKLED_ROCK, 8));
            context.register(ore_coarse_rock, oreFeature(GAIA_STONE, COARSE_ROCK, 8));
            context.register(ore_precious_rock, oreFeature(GAIA_STONE, PRECIOUS_ROCK, 8));
            context.register(ore_raw_amethyst, oreFeature(GAIA_STONE, RAW_AMETHYST, 12));
            context.register(ore_raw_copal, oreFeature(GAIA_STONE, RAW_COPAL, 12));
            context.register(ore_raw_jade, oreFeature(GAIA_STONE, RAW_JADE, 12));
            context.register(ore_raw_jet, oreFeature(GAIA_STONE, RAW_JET, 12));
            context.register(ore_sugilite, oreFeature(GAIA_STONE, SUGILITE_ORE, 17));
            context.register(ore_hematite, oreFeature(GAIA_STONE, HEMATITE_ORE, 17));
            context.register(ore_pyrite, oreFeature(GAIA_STONE, PYRITE_ORE, 9));
            context.register(ore_cinnabar, oreFeature(GAIA_STONE, CINNABAR_ORE, 9));
            context.register(ore_labradorite, oreFeature(GAIA_STONE, LABRADORITE_ORE, 9));
            context.register(ore_moonstone, oreFeature(GAIA_STONE, MOONSTONE_ORE, 9));
            context.register(ore_red_opal, oreFeature(GAIA_STONE, RED_OPAL_ORE, 8));
            context.register(ore_blue_opal, oreFeature(GAIA_STONE, BLUE_OPAL_ORE, 8));
            context.register(ore_green_opal, oreFeature(GAIA_STONE, GREEN_OPAL_ORE, 8));
            context.register(ore_white_opal, oreFeature(GAIA_STONE, WHITE_OPAL_ORE, 8));
            context.register(disk_static_stone, registerFeature(Feature.DISK, diskConfig(STATIC_STONE, match(ModBlocks.wasteland_stone), UniformInt.of(2, 4), 3)));
            context.register(disk_bog_patch, registerFeature(ModWorldgen.BOG_PATCH.get(), diskConfig(IMPURE_SLUDGE, match(ModBlocks.murky_grass, ModBlocks.boggy_soil), UniformInt.of(1, 4), 2)));
            context.register(disk_gilded_stone, registerFeature(Feature.DISK, diskConfig(GILDED_BRILLIANT_STONE, match(ModBlocks.brilliant_stone), UniformInt.of(2, 4), 2)));
            context.register(disk_marsh_patch, registerFeature(ModWorldgen.BOG_PATCH.get(), diskConfig(AURUM_MUD, match(ModBlocks.gilded_grass, ModBlocks.aurum_soil), UniformInt.of(3, 4), 3)));

            //Underground Decoration
            context.register(underground_glitter_blob, registerFeature(ModWorldgen.FRAIL_BLOB.get(), FeatureConfiguration.NONE));
            context.register(cave_fungi, registerFeature(
                    Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(64, 7, 3,
                            PlacementUtils.filtered(
                                    Feature.SIMPLE_BLOCK,
                                    new SimpleBlockConfiguration(new WeightedStateProvider(weight().add(ELDER_IMKLIA, 2).add(GOLD_ORB_TUCHER, 2))),
                                    BlockPredicate.allOf(
                                            BlockPredicate.matchesBlocks(Blocks.CAVE_AIR),
                                            BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), cave_blacklist))
                                    )))));

            //Vegetal Decoration
            context.register(aura_shoots, registerFeature(ModWorldgen.AURA_SHOOT.get(), FeatureConfiguration.NONE));
            context.register(normal_growth, patchFeature(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH)));
            context.register(mutant_growth, patchFeature(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_MUTANT)));
            context.register(seared_growth, patchFeature(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_SEARED)));
            context.register(corrupt_growth, patchFeature(32, 7, 3, new WeightedStateProvider(weight().add(CRYSTAL_GROWTH_RED, 2).add(CRYSTAL_GROWTH_BLACK, 2))));
            context.register(aura_growth, patchFeature(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_AURA)));
            context.register(golden_grass, patchFeature(32, 7, 3, BlockStateProvider.simple(GOLDEN_GRASS)));
            context.register(tall_golden_grass, patchFeature(32, 7, 3, BlockStateProvider.simple(TALL_GOLDEN_GRASS)));
            context.register(golden_vines, registerFeature(ModWorldgen.GOLDEN_VINES.get(), FeatureConfiguration.NONE));
            context.register(sombre_cacti, patchFeature(16, 4, 3, BlockStateProvider.simple(ModBlocks.sombre_cacti.get())));
            context.register(sombre_shrub, patchFeature(32, 7, 3, BlockStateProvider.simple(SOMBRE_SHRUB)));

            context.register(common_bloom, patchFeature(32, 7, 3, new WeightedStateProvider(weight().add(THISCUS, 4).add(OUZIUM, 1))));
            context.register(rare_bloom, patchFeature(32, 7, 3, new WeightedStateProvider(weight().add(OUZIUM, 4).add(THISCUS, 1))));
            context.register(mutant_bloom, patchFeature(32, 7, 3, new WeightedStateProvider(weight().add(OUZIUM, 4).add(AGATHUM, 1))));
            context.register(corrupt_bloom, patchFeature(64, 7, 3, BlockStateProvider.simple(CORRUPTED_VARLOOM)));
            context.register(golden_bloom, patchFeature(32, 6, 3, BlockStateProvider.simple(GLAMELEA)));

            context.register(kersei, patchFeature(16, 7, 3, BlockStateProvider.simple(SPOTTED_KERSEI)));
            context.register(wiltha, patchFeature(16, 7, 3, BlockStateProvider.simple(THORNY_WILTHA)));
            context.register(agaric, patchFeature(16, 7, 3, BlockStateProvider.simple(ROOFED_AGARIC)));
            context.register(hobina, patchFeature(16, 7, 3, BlockStateProvider.simple(BULBOUS_HOBINA)));
            context.register(cupsir, patchFeature(16, 7, 3, BlockStateProvider.simple(STICKLY_CUPSIR)));
            context.register(murgni, patchFeature(16, 7, 3, BlockStateProvider.simple(MYSTICAL_MURGNI)));
            context.register(corrupt_eye, patchFeature(16, 7, 3, BlockStateProvider.simple(CORRUPTED_GAIA_EYE)));
            context.register(gilsri, patchFeature(16, 7, 3, BlockStateProvider.simple(TWINKLING_GILSRI)));

            context.register(pink_agate_tree, treeFeature(Config.PINK_AGATE_TREE_CONFIG));
            context.register(blue_agate_tree, treeFeature(Config.BLUE_AGATE_TREE_CONFIG));
            context.register(green_agate_tree, treeFeature(Config.GREEN_AGATE_TREE_CONFIG));
            context.register(purple_agate_tree, treeFeature(Config.PURPLE_AGATE_TREE_CONFIG));
            context.register(fossilized_tree, treeFeature(Config.FOSSILIZED_TREE_CONFIG));
            context.register(goldstone_tree, treeFeature(Config.CORRUPTED_TREE_CONFIG));
            context.register(burnt_agate_tree, treeFeature(Config.BURNT_TREE_CONFIG));
            context.register(fiery_agate_tree, treeFeature(Config.BURNING_TREE_CONFIG));
            context.register(aura_tree, treeFeature(Config.AURA_TREE_CONFIG));
            context.register(small_golden_tree, treeFeature(Config.SMALL_GOLDEN_TREE_CONFIG));
            context.register(small_golden_tree_vines, treeFeature(Config.SMALL_GOLDEN_TREE_VINES_CONFIG));
            context.register(big_golden_tree, treeFeature(Config.BIG_GOLDEN_TREE_CONFIG));

            context.register(pink_agate_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.PINK_AGATE_TREE_CHECKED)))));
            context.register(blue_agate_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.BLUE_AGATE_TREE_CHECKED)))));
            context.register(green_agate_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.GREEN_AGATE_TREE_CHECKED)))));
            context.register(purple_agate_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.PURPLE_AGATE_TREE_CHECKED)))));
            context.register(fossilized_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.FOSSILIZED_TREE_CHECKED)))));
            context.register(goldstone_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.GOLDSTONE_TREE_CHECKED)))));
            context.register(burnt_agate_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.BURNT_AGATE_TREE_CHECKED)))));
            context.register(fiery_agate_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.FIERY_AGATE_TREE_CHECKED)))));
            context.register(aura_trees, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.AURA_TREE_CHECKED)))));
            context.register(small_golden_trees_with_vines, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(Tree.SMALL_GOLDEN_TREE_VINES_CHECKED)))));
            context.register(green_agate_bush, registerFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(BUSH_WORKAROUND)));
            context.register(various_agate_trees, registerFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(placed.getOrThrow(Tree.PINK_AGATE_TREE_CHECKED), 0.25F),
                    new WeightedPlacedFeature(placed.getOrThrow(Tree.BLUE_AGATE_TREE_CHECKED), 0.25F),
                    new WeightedPlacedFeature(placed.getOrThrow(Tree.GREEN_AGATE_TREE_CHECKED), 0.25F),
                    new WeightedPlacedFeature(placed.getOrThrow(Tree.PURPLE_AGATE_TREE_CHECKED), 0.25F)),
                    PlacementUtils.inlinePlaced(features.getOrThrow(mutant_growth)))));
            context.register(golden_trees, registerFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(placed.getOrThrow(Tree.BIG_GOLDEN_TREE_CHECKED), 0.25F)),
                    placed.getOrThrow(Tree.SMALL_GOLDEN_TREE_CHECKED))));

        }
    }

    public static class Tree {
        public static final ResourceKey<PlacedFeature> PINK_AGATE_TREE_CHECKED = registerPlacedFeature("pink_agate_tree_checked");
        public static final ResourceKey<PlacedFeature> BLUE_AGATE_TREE_CHECKED = registerPlacedFeature("blue_agate_tree_checked");
        public static final ResourceKey<PlacedFeature> GREEN_AGATE_TREE_CHECKED = registerPlacedFeature("green_agate_tree_checked");
        public static final ResourceKey<PlacedFeature> PURPLE_AGATE_TREE_CHECKED = registerPlacedFeature("purple_agate_tree_checked");
        public static final ResourceKey<PlacedFeature> FOSSILIZED_TREE_CHECKED = registerPlacedFeature("fossilized_tree_checked");
        public static final ResourceKey<PlacedFeature> GOLDSTONE_TREE_CHECKED = registerPlacedFeature("goldstone_tree_checked");
        public static final ResourceKey<PlacedFeature> FIERY_AGATE_TREE_CHECKED = registerPlacedFeature("fiery_agate_tree_checked");
        public static final ResourceKey<PlacedFeature> BURNT_AGATE_TREE_CHECKED = registerPlacedFeature("burnt_agate_tree_checked");
        public static final ResourceKey<PlacedFeature> AURA_TREE_CHECKED = registerPlacedFeature("aura_tree_checked");
        public static final ResourceKey<PlacedFeature> SMALL_GOLDEN_TREE_CHECKED = registerPlacedFeature("small_golden_tree_checked");
        public static final ResourceKey<PlacedFeature> SMALL_GOLDEN_TREE_VINES_CHECKED = registerPlacedFeature("small_golden_tree_vines_checked");
        public static final ResourceKey<PlacedFeature> BIG_GOLDEN_TREE_CHECKED = registerPlacedFeature("big_golden_tree_checked");

        private static ResourceKey<PlacedFeature> registerPlacedFeature(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name));
        }

        private static PlacedFeature checkTree(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> feature, RegistryObject<SaplingBlock> sapling) {
            return new PlacedFeature(getter.getOrThrow(feature), List.of(PlacementUtils.filteredByBlockSurvival(sapling.get())));
        }

        public static void init(BootstapContext<PlacedFeature> context) {
            HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
            context.register(PINK_AGATE_TREE_CHECKED, checkTree(features, Configured.pink_agate_tree, ModBlocks.pink_agate_sapling));
            context.register(BLUE_AGATE_TREE_CHECKED, checkTree(features, Configured.blue_agate_tree, ModBlocks.blue_agate_sapling));
            context.register(GREEN_AGATE_TREE_CHECKED, checkTree(features, Configured.green_agate_tree, ModBlocks.green_agate_sapling));
            context.register(PURPLE_AGATE_TREE_CHECKED, checkTree(features, Configured.purple_agate_tree, ModBlocks.purple_agate_sapling));
            context.register(FOSSILIZED_TREE_CHECKED, checkTree(features, Configured.fossilized_tree, ModBlocks.fossilized_sapling));
            context.register(GOLDSTONE_TREE_CHECKED, checkTree(features, Configured.goldstone_tree, ModBlocks.corrupted_sapling));
            context.register(FIERY_AGATE_TREE_CHECKED, checkTree(features, Configured.fiery_agate_tree, ModBlocks.burning_sapling));
            context.register(BURNT_AGATE_TREE_CHECKED, checkTree(features, Configured.burnt_agate_tree, ModBlocks.burnt_sapling));
            context.register(AURA_TREE_CHECKED, checkTree(features, Configured.aura_tree, ModBlocks.aura_sapling));
            context.register(SMALL_GOLDEN_TREE_CHECKED, checkTree(features, Configured.small_golden_tree, ModBlocks.golden_sapling));
            context.register(SMALL_GOLDEN_TREE_VINES_CHECKED, checkTree(features, Configured.small_golden_tree_vines, ModBlocks.golden_sapling));
            context.register(BIG_GOLDEN_TREE_CHECKED, checkTree(features, Configured.big_golden_tree, ModBlocks.golden_sapling));
        }
    }

    public static class Placed {
        //Lakes
        public static final ResourceKey<PlacedFeature> LAKE_SUPERHOT_MAGMA_COMMON = registerPlaced("lake_superhot_magma_common");
        public static final ResourceKey<PlacedFeature> LAKE_SUPERHOT_MAGMA_RARE = registerPlaced("lake_superhot_magma_rare");
        public static final ResourceKey<PlacedFeature> LAKE_MINERAL_WATER_COMMON = registerPlaced("lake_mineral_water_common");
        public static final ResourceKey<PlacedFeature> LAKE_MINERAL_WATER_UNCOMMON = registerPlaced("lake_mineral_water_uncommon");
        public static final ResourceKey<PlacedFeature> LAKE_MINERAL_WATER_RARE = registerPlaced("lake_mineral_water_rare");
        public static final ResourceKey<PlacedFeature> LAKE_SWEET_MUCK = registerPlaced("lake_sweet_muck");
        public static final ResourceKey<PlacedFeature> LAKE_LIQUID_AURA = registerPlaced("lake_liquid_aura");
        public static final ResourceKey<PlacedFeature> LAKE_LIQUID_BISMUTH = registerPlaced("lake_liquid_bismuth");

        //Local Modifications
        public static final ResourceKey<PlacedFeature> GUMMY_GLITTER_BLOB = registerPlaced("gummy_glitter_blob");
        public static final ResourceKey<PlacedFeature> STATIC_SPIKES = registerPlaced("static_spikes");
        public static final ResourceKey<PlacedFeature> BISMUTH_SPIRES = registerPlaced("bismuth_spires");
        public static final ResourceKey<PlacedFeature> BISMUTH_GEYSERS = registerPlaced("bismuth_geysers");
        public static final ResourceKey<PlacedFeature> BRILLIANT_STONE_SPIKES = registerPlaced("brilliant_stone_spikes");
        public static final ResourceKey<PlacedFeature> BALANCING_ROCKS = registerPlaced("balancing_rocks");
        public static final ResourceKey<PlacedFeature> TOUGH_GOLDEN_STONE_MONOLITHS = registerPlaced("tough_golden_stone_monoliths");
        public static final ResourceKey<PlacedFeature> TOUGH_GOLDEN_STONE_MENHIRS = registerPlaced("tough_golden_stone_menhirs");
        public static final ResourceKey<PlacedFeature> MARSH_LAKES = registerPlaced("marsh_lakes");

        //Underground Ores
        public static final ResourceKey<PlacedFeature> ORE_PRIMAL_MASS = registerPlaced("ore_primal_mass");
        public static final ResourceKey<PlacedFeature> ORE_THICK_GLITTER = registerPlaced("ore_thick_glitter");
        public static final ResourceKey<PlacedFeature> ORE_SEARING_ROCK = registerPlaced("ore_searing_rock");
        public static final ResourceKey<PlacedFeature> ORE_STATIC_STONE = registerPlaced("ore_static_stone");
        public static final ResourceKey<PlacedFeature> ORE_PEBBLES = registerPlaced("ore_pebbles");
        public static final ResourceKey<PlacedFeature> ORE_SPECKLED_ROCK = registerPlaced("ore_speckled_rock");
        public static final ResourceKey<PlacedFeature> ORE_COARSE_ROCK = registerPlaced("ore_coarse_rock");
        public static final ResourceKey<PlacedFeature> ORE_PRECIOUS_ROCK = registerPlaced("ore_precious_rock");
        public static final ResourceKey<PlacedFeature> ORE_RAW_AMETHYST = registerPlaced("ore_raw_amethyst");
        public static final ResourceKey<PlacedFeature> ORE_RAW_COPAL = registerPlaced("ore_raw_copal");
        public static final ResourceKey<PlacedFeature> ORE_RAW_JADE = registerPlaced("ore_raw_jade");
        public static final ResourceKey<PlacedFeature> ORE_RAW_JET = registerPlaced("ore_raw_jet");
        public static final ResourceKey<PlacedFeature> ORE_SUGILITE = registerPlaced("ore_sugilite");
        public static final ResourceKey<PlacedFeature> ORE_HEMATITE = registerPlaced("ore_hematite");
        public static final ResourceKey<PlacedFeature> ORE_PYRITE = registerPlaced("ore_pyrite");
        public static final ResourceKey<PlacedFeature> ORE_CINNABAR = registerPlaced("ore_cinnabar");
        public static final ResourceKey<PlacedFeature> ORE_LABRADORITE = registerPlaced("ore_labradorite");
        public static final ResourceKey<PlacedFeature> ORE_MOONSTONE = registerPlaced("ore_moonstone");
        public static final ResourceKey<PlacedFeature> ORE_RED_OPAL = registerPlaced("ore_red_opal");
        public static final ResourceKey<PlacedFeature> ORE_BLUE_OPAL = registerPlaced("ore_blue_opal");
        public static final ResourceKey<PlacedFeature> ORE_GREEN_OPAL = registerPlaced("ore_green_opal");
        public static final ResourceKey<PlacedFeature> ORE_WHITE_OPAL_COMMON = registerPlaced("ore_white_opal_common");
        public static final ResourceKey<PlacedFeature> ORE_WHITE_OPAL_RARE = registerPlaced("ore_white_opal_rare");
        public static final ResourceKey<PlacedFeature> DISK_STATIC_STONE = registerPlaced("disk_static_stone");
        public static final ResourceKey<PlacedFeature> DISK_BOG_PATCH = registerPlaced("disk_bog_patch");
        public static final ResourceKey<PlacedFeature> DISK_GILDED_BRILLIANT_STONE = registerPlaced("disk_gilded_brilliant_stone");
        public static final ResourceKey<PlacedFeature> DISK_MARSH_PATCH = registerPlaced("disk_marsh_patch");

        //Underground Decoration
        public static final ResourceKey<PlacedFeature> UNDERGROUND_GLITTER_BLOB = registerPlaced("underground_glitter_blob");
        public static final ResourceKey<PlacedFeature> CRYSTAL_FUNGI_CAVES = registerPlaced("crystal_fungi_caves");

        //Vegetal Decoration
        public static final ResourceKey<PlacedFeature> PINK_AGATE_TREE_COMMON = registerPlaced("pink_agate_tree_common");
        public static final ResourceKey<PlacedFeature> PINK_AGATE_TREE_RARE = registerPlaced("pink_agate_tree_rare");
        public static final ResourceKey<PlacedFeature> BLUE_AGATE_TREE = registerPlaced("blue_agate_tree");
        public static final ResourceKey<PlacedFeature> GREEN_AGATE_TREE = registerPlaced("green_agate_tree");
        public static final ResourceKey<PlacedFeature> PURPLE_AGATE_TREE = registerPlaced("purple_agate_tree");
        public static final ResourceKey<PlacedFeature> FOSSILIZED_TREE = registerPlaced("fossilized_tree");
        public static final ResourceKey<PlacedFeature> GOLDSTONE_TREE = registerPlaced("goldstone_tree");
        public static final ResourceKey<PlacedFeature> BURNT_AGATE_TREE = registerPlaced("burnt_agate_tree");
        public static final ResourceKey<PlacedFeature> FIERY_AGATE_TREE = registerPlaced("fiery_agate_tree");
        public static final ResourceKey<PlacedFeature> AURA_TREE = registerPlaced("aura_tree");
        public static final ResourceKey<PlacedFeature> SMALL_GOLDEN_TREE_WITH_VINES = registerPlaced("small_golden_tree");
        public static final ResourceKey<PlacedFeature> GREEN_AGATE_BUSH = registerPlaced("green_agate_bush");
        public static final ResourceKey<PlacedFeature> VARIOUS_AGATE_TREES = registerPlaced("mutant_agate_trees");
        public static final ResourceKey<PlacedFeature> GOLDEN_TREES = registerPlaced("golden_trees");
        public static final ResourceKey<PlacedFeature> AURA_SHOOTS = registerPlaced("aura_shoots");
        public static final ResourceKey<PlacedFeature> GOLDEN_VINES = registerPlaced("golden_vines");
        public static final ResourceKey<PlacedFeature> SOMBRE_CACTI = registerPlaced("sombre_cacti");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_02 = registerPlaced("crystal_growth_02");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_03 = registerPlaced("crystal_growth_03");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_04 = registerPlaced("crystal_growth_04");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_05 = registerPlaced("crystal_growth_05");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_SEARED = registerPlaced("crystal_growth_seared");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_CORRUPT = registerPlaced("crystal_growth_corrupt");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_AURA = registerPlaced("crystal_growth_aura");
        public static final ResourceKey<PlacedFeature> CRYSTAL_GROWTH_MUTANT = registerPlaced("crystal_growth_mutant");
        public static final ResourceKey<PlacedFeature> GOLDEN_GRASS_COMMON = registerPlaced("golden_grass_common");
        public static final ResourceKey<PlacedFeature> GOLDEN_GRASS_UNCOMMON = registerPlaced("golden_grass_uncommon");
        public static final ResourceKey<PlacedFeature> GOLDEN_GRASS_RARE = registerPlaced("golden_grass_rare");
        public static final ResourceKey<PlacedFeature> TALL_GOLDEN_GRASS = registerPlaced("tall_golden_grass");
        public static final ResourceKey<PlacedFeature> CRYSTAL_BLOOMS_COMMON = registerPlaced("crystal_blooms_common");
        public static final ResourceKey<PlacedFeature> CRYSTAL_BLOOMS_RARE = registerPlaced("crystal_blooms_rare");
        public static final ResourceKey<PlacedFeature> CRYSTAL_BLOOMS_MUTANT = registerPlaced("crystal_blooms_mutant");
        public static final ResourceKey<PlacedFeature> CRYSTAL_BLOOMS_CORRUPT = registerPlaced("crystal_blooms_corrupt");
        public static final ResourceKey<PlacedFeature> CRYSTAL_BLOOMS_GOLDEN = registerPlaced("crystal_blooms_golden");
        public static final ResourceKey<PlacedFeature> SOMBRE_SHRUBS = registerPlaced("sombre_shrubs");
        public static final ResourceKey<PlacedFeature> SPOTTED_KERSEI = registerPlaced("spotted_kersei");
        public static final ResourceKey<PlacedFeature> THORNY_WILTHA = registerPlaced("thorny_wiltha");
        public static final ResourceKey<PlacedFeature> ROOFED_AGARIC = registerPlaced("roofed_agaric");
        public static final ResourceKey<PlacedFeature> BULBOUS_HOBINA = registerPlaced("bulbous_hobina");
        public static final ResourceKey<PlacedFeature> STICKLY_CUPSIR = registerPlaced("stickly_cupsir");
        public static final ResourceKey<PlacedFeature> MYSTICAL_MURGNI = registerPlaced("mystical_murgni");
        public static final ResourceKey<PlacedFeature> CORRUPTED_GAIA_EYE = registerPlaced("corrupted_gaia_eye");
        public static final ResourceKey<PlacedFeature> TWINKLING_GILSRI = registerPlaced("twinkling_gilsri");

        private static ResourceKey<PlacedFeature> registerPlaced(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name));
        }

        private static PlacedFeature placedOre(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> ore, int height, int count) {
            return registerPlacedFeature(getter, ore,
                    CountPlacement.of(count),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(height)),
                    BiomeFilter.biome());
        }

        public static PlacedFeature placedTree(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> tree, int count, float chance, int extra) {
            return registerPlacedFeature(getter, tree,
                    PlacementUtils.countExtra(count, chance, extra),
                    InSquarePlacement.spread(),
                    SurfaceWaterDepthFilter.forMaxDepth(0),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome());
        }

        private static PlacedFeature placedPlant(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> growth, int count) {
            return registerPlacedFeature(getter, growth,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(count));
        }

        private static PlacedFeature placedFungi(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> patch, int count) {
            return registerPlacedFeature(getter, patch,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(count),
                    BiomeFilter.biome());
        }

        private static PlacedFeature registerPlacedFeature(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
            return new PlacedFeature(getter.getOrThrow(feature), List.of(modifiers));
        }

        public static void init(BootstapContext<PlacedFeature> context) {
            HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

            //Lakes
            context.register(LAKE_SUPERHOT_MAGMA_COMMON, registerPlacedFeature(features, Configured.lake_superhot_magma,
                    RarityFilter.onAverageOnceEvery(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                            32),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                    BiomeFilter.biome()));
            context.register(LAKE_SUPERHOT_MAGMA_RARE, registerPlacedFeature(features, Configured.lake_superhot_magma,
                    RarityFilter.onAverageOnceEvery(80),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)),
                    EnvironmentScanPlacement.scanningFor(
                            Direction.DOWN,
                            BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))),
                            32),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                    BiomeFilter.biome()));
            context.register(LAKE_MINERAL_WATER_COMMON, registerPlacedFeature(features, Configured.lake_mineral_water,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
            context.register(LAKE_MINERAL_WATER_UNCOMMON, registerPlacedFeature(features, Configured.lake_mineral_water,
                    RarityFilter.onAverageOnceEvery(40),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
            context.register(LAKE_MINERAL_WATER_RARE, registerPlacedFeature(features, Configured.lake_mineral_water,
                    RarityFilter.onAverageOnceEvery(50),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
            context.register(LAKE_SWEET_MUCK, registerPlacedFeature(features, Configured.lake_sweet_muck,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
            context.register(LAKE_LIQUID_AURA, registerPlacedFeature(features, Configured.lake_liquid_aura,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));
            context.register(LAKE_LIQUID_BISMUTH, registerPlacedFeature(features, Configured.lake_liquid_bismuth,
                    RarityFilter.onAverageOnceEvery(4),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()));

            //Local Modifications
            context.register(GUMMY_GLITTER_BLOB, registerPlacedFeature(features, Configured.gummy_glitter_blob,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(2),
                    BiomeFilter.biome()));
            context.register(STATIC_SPIKES, registerPlacedFeature(features, Configured.static_spikes,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(2),
                    BiomeFilter.biome()));
            context.register(BISMUTH_SPIRES, registerPlacedFeature(features, Configured.bismuth_spires,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(2),
                    BiomeFilter.biome()));
            context.register(BISMUTH_GEYSERS, registerPlacedFeature(features, Configured.bismuth_geysers,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(2),
                    BiomeFilter.biome()));
            context.register(BRILLIANT_STONE_SPIKES, registerPlacedFeature(features, Configured.brilliant_stone_spikes,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()));
            context.register(BALANCING_ROCKS, registerPlacedFeature(features, Configured.balancing_rocks,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(1),
                    BiomeFilter.biome()));
            context.register(TOUGH_GOLDEN_STONE_MONOLITHS, registerPlacedFeature(features, Configured.tough_golden_stone_monolith,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    RarityFilter.onAverageOnceEvery(5),
                    BiomeFilter.biome()));
            context.register(TOUGH_GOLDEN_STONE_MENHIRS, registerPlacedFeature(features, Configured.tough_golden_stone_menhir,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    RarityFilter.onAverageOnceEvery(5),
                    BiomeFilter.biome()));
            context.register(MARSH_LAKES, registerPlacedFeature(features, Configured.marsh_lake,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    BiomeFilter.biome()));

            //Underground Ores
            context.register(ORE_PRIMAL_MASS, placedOre(features, Configured.ore_primal_mass, 25, 33));
            context.register(ORE_THICK_GLITTER, placedOre(features, Configured.ore_thick_glitter, 100, 9));
            context.register(ORE_SEARING_ROCK, placedOre(features, Configured.ore_searing_rock, 100, 9));
            context.register(ORE_STATIC_STONE, placedOre(features, Configured.ore_static_stone, 100, 9));
            context.register(ORE_PEBBLES, placedOre(features, Configured.ore_pebbles, 128, 25));
            context.register(ORE_SPECKLED_ROCK, placedOre(features, Configured.ore_speckled_rock, 120, 10));
            context.register(ORE_COARSE_ROCK, placedOre(features, Configured.ore_coarse_rock, 60, 10));
            context.register(ORE_PRECIOUS_ROCK, placedOre(features, Configured.ore_precious_rock, 30, 10));
            context.register(ORE_RAW_AMETHYST, placedOre(features, Configured.ore_raw_amethyst, 120, 15));
            context.register(ORE_RAW_COPAL, placedOre(features, Configured.ore_raw_copal, 120, 15));
            context.register(ORE_RAW_JADE, placedOre(features, Configured.ore_raw_jade, 120, 15));
            context.register(ORE_RAW_JET, placedOre(features, Configured.ore_raw_jet, 120, 15));
            context.register(ORE_SUGILITE, placedOre(features, Configured.ore_sugilite, 100, 8));
            context.register(ORE_HEMATITE, placedOre(features, Configured.ore_hematite, 100, 8));
            context.register(ORE_PYRITE, placedOre(features, Configured.ore_pyrite, 80, 8));
            context.register(ORE_CINNABAR, placedOre(features, Configured.ore_cinnabar, 60, 7));
            context.register(ORE_LABRADORITE, placedOre(features, Configured.ore_labradorite, 40, 6));
            context.register(ORE_MOONSTONE, placedOre(features, Configured.ore_moonstone, 40, 6));
            context.register(ORE_RED_OPAL, placedOre(features, Configured.ore_red_opal, 30, 4));
            context.register(ORE_BLUE_OPAL, placedOre(features, Configured.ore_blue_opal, 30, 4));
            context.register(ORE_GREEN_OPAL, placedOre(features, Configured.ore_green_opal, 30, 4));
            context.register(ORE_WHITE_OPAL_COMMON, placedOre(features, Configured.ore_white_opal, 25, 4));
            context.register(ORE_WHITE_OPAL_RARE, placedOre(features, Configured.ore_white_opal, 20, 3));
            context.register(DISK_STATIC_STONE, registerPlacedFeature(features, Configured.disk_static_stone,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()));
            context.register(DISK_BOG_PATCH, registerPlacedFeature(features, Configured.disk_bog_patch,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()));
            context.register(DISK_GILDED_BRILLIANT_STONE, registerPlacedFeature(features, Configured.disk_gilded_stone,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()));
            context.register(DISK_MARSH_PATCH, registerPlacedFeature(features, Configured.disk_marsh_patch,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    CountPlacement.of(3),
                    BiomeFilter.biome()));

            //Underground Decoration
            context.register(UNDERGROUND_GLITTER_BLOB, registerPlacedFeature(features, Configured.underground_glitter_blob,
                    CountPlacement.of(100),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(70)),
                    BiomeFilter.biome()));
            context.register(CRYSTAL_FUNGI_CAVES, registerPlacedFeature(features, Configured.cave_fungi,
                    CountPlacement.of(2),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(70)),
                    BiomeFilter.biome()));

            //Vegetal Decoration
            context.register(PINK_AGATE_TREE_COMMON, placedTree(features, Configured.pink_agate_trees, 4, 0.1F, 1));
            context.register(PINK_AGATE_TREE_RARE, placedTree(features, Configured.pink_agate_trees, 0, 0.1F, 1));
            context.register(BLUE_AGATE_TREE, placedTree(features, Configured.blue_agate_trees, 1, 0.1F, 1));
            context.register(GREEN_AGATE_TREE, placedTree(features, Configured.green_agate_trees, 5, 0.1F, 1));
            context.register(PURPLE_AGATE_TREE, placedTree(features, Configured.purple_agate_trees, 1, 0.1F, 2));
            context.register(FOSSILIZED_TREE, placedTree(features, Configured.fossilized_trees, 1, 0.1F, 1));
            context.register(GOLDSTONE_TREE, placedTree(features, Configured.goldstone_trees, 1, 0.1F, 1));
            context.register(BURNT_AGATE_TREE, placedTree(features, Configured.burnt_agate_trees, 0, 0.1F, 1));
            context.register(FIERY_AGATE_TREE, placedTree(features, Configured.fiery_agate_trees, 0, 0.1F, 1));
            context.register(AURA_TREE, placedTree(features, Configured.aura_trees, 2, 0.1F, 1));
            context.register(SMALL_GOLDEN_TREE_WITH_VINES, placedTree(features, Configured.small_golden_trees_with_vines, 1, 0.1F, 1));
            context.register(GREEN_AGATE_BUSH, registerPlacedFeature(features, Configured.green_agate_bush,
                    CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    BiomeFilter.biome()));
            context.register(VARIOUS_AGATE_TREES, registerPlacedFeature(features, Configured.various_agate_trees,
                    InSquarePlacement.spread(),
                    SurfaceWaterDepthFilter.forMaxDepth(0),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    PlacementUtils.countExtra(2, 0.1F, 1),
                    BiomeFilter.biome()));
            context.register(GOLDEN_TREES, registerPlacedFeature(features, Configured.golden_trees,
                    InSquarePlacement.spread(),
                    SurfaceWaterDepthFilter.forMaxDepth(0),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    PlacementUtils.countExtra(2, 0.1F, 1),
                    BiomeFilter.biome()));
            context.register(AURA_SHOOTS, registerPlacedFeature(features, Configured.aura_shoots,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    CountPlacement.of(6),
                    BiomeFilter.biome()));
            context.register(GOLDEN_VINES, registerPlacedFeature(features, Configured.golden_vines,
                    InSquarePlacement.spread(),
                    CountPlacement.of(127),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(100)),
                    BiomeFilter.biome()));
            context.register(SOMBRE_CACTI, registerPlacedFeature(features, Configured.sombre_cacti,
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP,
                    RarityFilter.onAverageOnceEvery(2),
                    BiomeFilter.biome()));
            context.register(CRYSTAL_GROWTH_02, placedPlant(features, Configured.normal_growth, 2));
            context.register(CRYSTAL_GROWTH_03, placedPlant(features, Configured.normal_growth, 3));
            context.register(CRYSTAL_GROWTH_04, placedPlant(features, Configured.normal_growth, 4));
            context.register(CRYSTAL_GROWTH_05, placedPlant(features, Configured.normal_growth, 5));
            context.register(CRYSTAL_GROWTH_SEARED, placedPlant(features, Configured.seared_growth, 1));
            context.register(CRYSTAL_GROWTH_CORRUPT, placedPlant(features, Configured.corrupt_growth, 1));
            context.register(CRYSTAL_GROWTH_AURA, placedPlant(features, Configured.aura_growth, 2));
            context.register(CRYSTAL_GROWTH_MUTANT, placedPlant(features, Configured.mutant_growth, 2));
            context.register(GOLDEN_GRASS_COMMON, placedPlant(features, Configured.golden_grass, 7));
            context.register(GOLDEN_GRASS_UNCOMMON, placedPlant(features, Configured.golden_grass, 3));
            context.register(GOLDEN_GRASS_RARE, placedPlant(features, Configured.golden_grass, 2));
            context.register(TALL_GOLDEN_GRASS, placedPlant(features, Configured.tall_golden_grass, 4));
            context.register(CRYSTAL_BLOOMS_COMMON, placedPlant(features, Configured.common_bloom, 2));
            context.register(CRYSTAL_BLOOMS_RARE, placedPlant(features, Configured.rare_bloom, 2));
            context.register(CRYSTAL_BLOOMS_MUTANT, placedPlant(features, Configured.mutant_bloom, 2));
            context.register(CRYSTAL_BLOOMS_CORRUPT, placedPlant(features, Configured.corrupt_bloom, 1));
            context.register(CRYSTAL_BLOOMS_GOLDEN, placedPlant(features, Configured.golden_bloom, 1));
            context.register(SOMBRE_SHRUBS, placedPlant(features, Configured.sombre_shrub, 2));
            context.register(SPOTTED_KERSEI, placedFungi(features, Configured.kersei, 1));
            context.register(THORNY_WILTHA, placedFungi(features, Configured.wiltha, 1));
            context.register(ROOFED_AGARIC, placedFungi(features, Configured.agaric, 1));
            context.register(BULBOUS_HOBINA, placedFungi(features, Configured.hobina, 1));
            context.register(STICKLY_CUPSIR, placedFungi(features, Configured.cupsir, 1));
            context.register(MYSTICAL_MURGNI, placedFungi(features, Configured.murgni, 1));
            context.register(CORRUPTED_GAIA_EYE, placedFungi(features, Configured.corrupt_eye, 1));
            context.register(TWINKLING_GILSRI, placedFungi(features, Configured.gilsri, 1));
        }
    }
}
