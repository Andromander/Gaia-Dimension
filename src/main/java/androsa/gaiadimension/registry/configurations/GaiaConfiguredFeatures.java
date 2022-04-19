package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.registry.RegistryHelper;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;
import java.util.Map;

public final class GaiaConfiguredFeatures extends GaiaBiomeFeatures {

    public static final HolderSet<PlacedFeature> BUSH_WORKAROUND = HolderSet.direct(PlacementUtils.inlinePlaced(Feature.TREE, GREEN_AGATE_BUSH_CONFIG));

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
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ore_white_opal = registerFeature("ore_white_opal_common", Feature.ORE, configureOre(GAIA_STONE, WHITE_OPAL_ORE, 8));
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

    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> pink_agate_tree = registerFeature("pink_agate_tree_common", ModWorldgen.PINK_AGATE_TREE.get(), PINK_AGATE_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> blue_agate_tree = registerFeature("blue_agate_tree", ModWorldgen.BLUE_AGATE_TREE.get(), BLUE_AGATE_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> green_agate_tree = registerFeature("green_agate_tree", ModWorldgen.GREEN_AGATE_TREE.get(), GREEN_AGATE_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> purple_agate_tree = registerFeature("purple_agate_tree", ModWorldgen.PURPLE_AGATE_TREE.get(), PURPLE_AGATE_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> fossilized_tree = registerFeature("fossilized_tree", ModWorldgen.FOSSILIZED_TREE.get(), FOSSILIZED_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> goldstone_tree = registerFeature("goldstone_tree", ModWorldgen.GOLDSTONE_TREE.get(), CORRUPTED_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> burnt_agate_tree = registerFeature("burnt_agate_tree", ModWorldgen.BURNT_AGATE_TREE.get(), BURNT_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> fiery_agate_tree = registerFeature("fiery_agate_tree", ModWorldgen.FIERY_AGATE_TREE.get(), BURNING_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<GaiaTreeFeatureConfig, ?>> aura_tree = registerFeature("aura_tree", ModWorldgen.AURA_TREE.get(), AURA_TREE_CONFIG);
    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> green_agate_bush = registerFeature("green_agate_bush", Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(BUSH_WORKAROUND));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> various_agate_trees = registerFeature("various_agate_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
            new WeightedPlacedFeature(GaiaPlacedFeatures.PINK_AGATE_TREE_COMMON, 0.25F),
            new WeightedPlacedFeature(GaiaPlacedFeatures.BLUE_AGATE_TREE, 0.25F),
            new WeightedPlacedFeature(GaiaPlacedFeatures.GREEN_AGATE_TREE, 0.25F),
            new WeightedPlacedFeature(GaiaPlacedFeatures.PURPLE_AGATE_TREE, 0.25F)),
            GaiaPlacedFeatures.CRYSTAL_GROWTH_MUTANT));

    private static SimpleWeightedRandomList.Builder<BlockState> weight() {
        return SimpleWeightedRandomList.builder();
    }

    public static OreConfiguration configureOre(RuleTest test, BlockState ore, int count) {
        return new OreConfiguration(test, ore, count);
    }

    public static RandomPatchConfiguration configurePatch(int tries, int xzspread, int yspread, BlockStateProvider provider) {
        return new RandomPatchConfiguration(tries, xzspread, yspread, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider)));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerFeature(String name, ConfiguredFeature<FC, ?> feature) {
        RegistryHelper.CONFIGURED_FEATURES.put(feature, name);
        return feature;
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> registerFeature(String name, F feature, FC config) {
        ConfiguredFeature<FC, ?> configuredFeature = new ConfiguredFeature<>(feature, config);
        RegistryHelper.CONFIGURED_FEATURES.put(configuredFeature, name);
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name).toString(), configuredFeature);
    }

    public static void registerFeatures(Registry<ConfiguredFeature<?,?>> registry) {
        for (Map.Entry<ConfiguredFeature<?,?>, String> entry : RegistryHelper.CONFIGURED_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
