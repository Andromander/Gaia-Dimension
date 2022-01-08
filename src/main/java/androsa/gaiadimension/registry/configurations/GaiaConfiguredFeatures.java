package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.registry.RegistryHelper;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.Registry;
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
import java.util.function.Supplier;

public final class GaiaConfiguredFeatures extends GaiaBiomeFeatures {

    public static final ImmutableList<Supplier<PlacedFeature>> BUSH_WORKAROUND = ImmutableList.of(
            () -> Feature.TREE.configured(GREEN_AGATE_BUSH_CONFIG).placed());

    //Lakes
    public static final ConfiguredFeature<?, ?> lake_superhot_magma = registerFeature("lake_superhot_magma", ModWorldgen.POOL.get().configured(new BlockStateConfiguration(SUPERHOT_MAGMA)));
    public static final ConfiguredFeature<?, ?> lake_mineral_water = registerFeature("lake_mineral_water", ModWorldgen.POOL.get().configured(new BlockStateConfiguration(MINERAL_WATER)));
    public static final ConfiguredFeature<?, ?> lake_sweet_muck = registerFeature("lake_sweet_muck", ModWorldgen.POOL.get().configured(new BlockStateConfiguration(SWEET_MUCK)));
    public static final ConfiguredFeature<?, ?> lake_liquid_aura = registerFeature("lake_liquid_aura", ModWorldgen.POOL.get().configured(new BlockStateConfiguration(LIQUID_AURA)));
    public static final ConfiguredFeature<?, ?> lake_liquid_bismuth = registerFeature("lake_liquid_bismuth", ModWorldgen.POOL.get().configured(new BlockStateConfiguration(LIQUID_BISMUTH)));

    //Local Modifications
    public static final ConfiguredFeature<?, ?> gummy_glitter_blob = registerFeature("gummy_glitter_blob", ModWorldgen.GAIA_BLOB.get().configured(new BlockStateConfiguration(GUMMY_GLITTER)));
    public static final ConfiguredFeature<?, ?> static_spikes = registerFeature("static_spikes", ModWorldgen.STATIC_SPIKE.get().configured(new FeatureHeightConfig(8)));
    public static final ConfiguredFeature<?, ?> bismuth_spires = registerFeature("bismuth_spires", ModWorldgen.BISMUTH_SPIRE.get().configured(new FeatureHeightConfig(7)));
    public static final ConfiguredFeature<?, ?> bismuth_geysers = registerFeature("bismuth_geysers", ModWorldgen.BISMUTH_GEYSER.get().configured(FeatureConfiguration.NONE));

    //Underground Ores
    public static final ConfiguredFeature<?, ?> ore_primal_mass = registerFeature("ore_primal_mass", Feature.ORE.configured(configureOre(GAIA_STONE, PRIMAL_MASS, 33)));
    public static final ConfiguredFeature<?, ?> ore_thick_glitter = registerFeature("ore_thick_glitter", Feature.ORE.configured(configureOre(GAIA_STONE, THICK_GLITTER, 33)));
    public static final ConfiguredFeature<?, ?> ore_searing_rock = registerFeature("ore_searing_rock", Feature.ORE.configured(configureOre(VOLCANIC, SEARING_ROCK, 33)));
    public static final ConfiguredFeature<?, ?> ore_static_stone = registerFeature("ore_static_stone", Feature.ORE.configured(configureOre(STATIC, STATIC_STONE, 33)));
    public static final ConfiguredFeature<?, ?> ore_pebbles = registerFeature("ore_pebbles", Feature.ORE.configured(configureOre(GAIA_STONE, PEBBLES, 25)));
    public static final ConfiguredFeature<?, ?> ore_speckled_rock = registerFeature("ore_speckled_rock", Feature.ORE.configured(configureOre(GAIA_STONE, SPECKLED_ROCK, 8)));
    public static final ConfiguredFeature<?, ?> ore_coarse_rock = registerFeature("ore_coarse_rock", Feature.ORE.configured(configureOre(GAIA_STONE, COARSE_ROCK, 8)));
    public static final ConfiguredFeature<?, ?> ore_precious_rock = registerFeature("ore_precious_rock", Feature.ORE.configured(configureOre(GAIA_STONE, PRECIOUS_ROCK, 8)));
    public static final ConfiguredFeature<?, ?> ore_raw_amethyst = registerFeature("ore_raw_amethyst", Feature.ORE.configured(configureOre(GAIA_STONE, RAW_AMETHYST, 12)));
    public static final ConfiguredFeature<?, ?> ore_raw_copal = registerFeature("ore_raw_copal", Feature.ORE.configured(configureOre(GAIA_STONE, RAW_COPAL, 12)));
    public static final ConfiguredFeature<?, ?> ore_raw_jade = registerFeature("ore_raw_jade", Feature.ORE.configured(configureOre(GAIA_STONE, RAW_JADE, 12)));
    public static final ConfiguredFeature<?, ?> ore_raw_jet = registerFeature("ore_raw_jet", Feature.ORE.configured(configureOre(GAIA_STONE, RAW_JET, 12)));
    public static final ConfiguredFeature<?, ?> ore_sugilite = registerFeature("ore_sugilite", Feature.ORE.configured(configureOre(GAIA_STONE, SUGILITE_ORE, 17)));
    public static final ConfiguredFeature<?, ?> ore_hematite = registerFeature("ore_hematite", Feature.ORE.configured(configureOre(GAIA_STONE, HEMATITE_ORE, 17)));
    public static final ConfiguredFeature<?, ?> ore_pyrite = registerFeature("ore_pyrite", Feature.ORE.configured(configureOre(GAIA_STONE, PYRITE_ORE, 9)));
    public static final ConfiguredFeature<?, ?> ore_cinnabar = registerFeature("ore_cinnabar", Feature.ORE.configured(configureOre(GAIA_STONE, CINNABAR_ORE, 9)));
    public static final ConfiguredFeature<?, ?> ore_labradorite = registerFeature("ore_labradorite", Feature.ORE.configured(configureOre(GAIA_STONE, LABRADORITE_ORE, 9)));
    public static final ConfiguredFeature<?, ?> ore_moonstone = registerFeature("ore_moonstone", Feature.ORE.configured(configureOre(GAIA_STONE, MOONSTONE_ORE, 9)));
    public static final ConfiguredFeature<?, ?> ore_red_opal = registerFeature("ore_red_opal", Feature.ORE.configured(configureOre(GAIA_STONE, RED_OPAL_ORE, 8)));
    public static final ConfiguredFeature<?, ?> ore_blue_opal = registerFeature("ore_blue_opal", Feature.ORE.configured(configureOre(GAIA_STONE, BLUE_OPAL_ORE, 8)));
    public static final ConfiguredFeature<?, ?> ore_green_opal = registerFeature("ore_green_opal", Feature.ORE.configured(configureOre(GAIA_STONE, GREEN_OPAL_ORE, 8)));
    public static final ConfiguredFeature<?, ?> ore_white_opal = registerFeature("ore_white_opal_common", Feature.ORE.configured(configureOre(GAIA_STONE, WHITE_OPAL_ORE, 8)));
    public static final ConfiguredFeature<?, ?> disk_static_stone = registerFeature("disk_static_stone", ModWorldgen.GAIA_DISK.get().configured(new DiskConfiguration(STATIC_STONE, UniformInt.of(2, 4), 3, ImmutableList.of(WASTELAND_STONE))));
    public static final ConfiguredFeature<?, ?> disk_bog_patch = registerFeature("disk_bog_patch", ModWorldgen.BOG_PATCH.get().configured(new DiskConfiguration(ModBlocks.impure_sludge.get().defaultBlockState(), UniformInt.of(1, 4), 2, Lists.newArrayList(MURKY_GRASS, BOGGY_SOIL))));

    //Underground Decoration
    public static final ConfiguredFeature<?, ?> underground_glitter_blob = registerFeature("underground_glitter_blob", ModWorldgen.FRAIL_BLOB.get().configured(FeatureConfiguration.NONE));
    public static final ConfiguredFeature<?, ?> cave_fungi = registerFeature("cave_fungi", Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(64, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(weight().add(ELDER_IMKLIA, 2).add(GOLD_ORB_TUCHER, 2)))).filtered(BlockPredicate.not(BlockPredicate.matchesBlocks(cave_blacklist))))));

    //Vegetal Decoration
    public static final ConfiguredFeature<?, ?> aura_shoots = registerFeature("aura_shoots", ModWorldgen.AURA_SHOOT.get().configured(FeatureConfiguration.NONE));
    public static final ConfiguredFeature<?, ?> normal_growth = registerFeature("crystal_growth", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH))));
    public static final ConfiguredFeature<?, ?> mutant_growth = registerFeature("mutant_crystal_growth", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_MUTANT))));
    public static final ConfiguredFeature<?, ?> seared_growth = registerFeature("seared_crystal_growth", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_SEARED))));
    public static final ConfiguredFeature<?, ?> corrupt_growth = registerFeature("corrupt_crystal_growth", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(CRYSTAL_GROWTH_RED, 2).add(CRYSTAL_GROWTH_BLACK, 2)))));
    public static final ConfiguredFeature<?, ?> aura_growth = registerFeature("aura_crystal_growth", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, BlockStateProvider.simple(CRYSTAL_GROWTH_AURA))));

    public static final ConfiguredFeature<?, ?> common_bloom = registerFeature("common_bloom", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(THISCUS, 4).add(OUZIUM, 1)))));
    public static final ConfiguredFeature<?, ?> rare_bloom = registerFeature("rare_bloom", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(OUZIUM, 4).add(THISCUS, 1)))));
    public static final ConfiguredFeature<?, ?> mutant_bloom = registerFeature("mutant_bloom", Feature.RANDOM_PATCH.configured(configurePatch(32, 7, 3, new WeightedStateProvider(weight().add(OUZIUM, 4).add(AGATHUM, 1)))));
    public static final ConfiguredFeature<?, ?> corrupt_bloom = registerFeature("corrupt_bloom", Feature.RANDOM_PATCH.configured(configurePatch(64, 7, 3, BlockStateProvider.simple(CORRUPTED_VARLOOM))));

    public static final ConfiguredFeature<?, ?> kersei = registerFeature("spotted_kersei", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(SPOTTED_KERSEI))));
    public static final ConfiguredFeature<?, ?> wiltha = registerFeature("thorny_wiltha", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(THORNY_WILTHA))));
    public static final ConfiguredFeature<?, ?> agaric = registerFeature("roofed_agaric", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(ROOFED_AGARIC))));
    public static final ConfiguredFeature<?, ?> hobina = registerFeature("bulbous_hobina", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(BULBOUS_HOBINA))));
    public static final ConfiguredFeature<?, ?> cupsir = registerFeature("stickly_cupsir", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(STICKLY_CUPSIR))));
    public static final ConfiguredFeature<?, ?> murgni = registerFeature("mystical_murgni", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(MYSTICAL_MURGNI))));
    public static final ConfiguredFeature<?, ?> corrupt_eye = registerFeature("corrupt_gaia_eye", Feature.RANDOM_PATCH.configured(configurePatch(16, 7, 3, BlockStateProvider.simple(CORRUPTED_GAIA_EYE))));

    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> pink_agate_tree = registerFeature("pink_agate_tree_common", ModWorldgen.PINK_AGATE_TREE.get().configured(PINK_AGATE_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> blue_agate_tree = registerFeature("blue_agate_tree", ModWorldgen.BLUE_AGATE_TREE.get().configured(BLUE_AGATE_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> green_agate_tree = registerFeature("green_agate_tree", ModWorldgen.GREEN_AGATE_TREE.get().configured(GREEN_AGATE_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> purple_agate_tree = registerFeature("purple_agate_tree", ModWorldgen.PURPLE_AGATE_TREE.get().configured(PURPLE_AGATE_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> fossilized_tree = registerFeature("fossilized_tree", ModWorldgen.FOSSILIZED_TREE.get().configured(FOSSILIZED_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> goldstone_tree = registerFeature("goldstone_tree", ModWorldgen.GOLDSTONE_TREE.get().configured(CORRUPTED_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> burnt_agate_tree = registerFeature("burnt_agate_tree", ModWorldgen.BURNT_AGATE_TREE.get().configured(BURNT_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> fiery_agate_tree = registerFeature("fiery_agate_tree", ModWorldgen.FIERY_AGATE_TREE.get().configured(BURNING_TREE_CONFIG));
    public static final ConfiguredFeature<GaiaTreeFeatureConfig, ?> aura_tree = registerFeature("aura_tree", ModWorldgen.AURA_TREE.get().configured(AURA_TREE_CONFIG));
    public static final ConfiguredFeature<?, ?> green_agate_bush = registerFeature("green_agate_bush", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(BUSH_WORKAROUND)));
    public static final ConfiguredFeature<?, ?> various_agate_trees = registerFeature("various_agate_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(
            new WeightedPlacedFeature(GaiaPlacedFeatures.PINK_AGATE_TREE_COMMON, 0.25F),
            new WeightedPlacedFeature(GaiaPlacedFeatures.BLUE_AGATE_TREE, 0.25F),
            new WeightedPlacedFeature(GaiaPlacedFeatures.GREEN_AGATE_TREE, 0.25F),
            new WeightedPlacedFeature(GaiaPlacedFeatures.PURPLE_AGATE_TREE, 0.25F)),
            GaiaPlacedFeatures.CRYSTAL_GROWTH_MUTANT)));

    private static SimpleWeightedRandomList.Builder<BlockState> weight() {
        return SimpleWeightedRandomList.builder();
    }

    public static OreConfiguration configureOre(RuleTest test, BlockState ore, int count) {
        return new OreConfiguration(test, ore, count);
    }

    public static RandomPatchConfiguration configurePatch(int tries, int xzspread, int yspread, BlockStateProvider provider) {
        return new RandomPatchConfiguration(tries, xzspread, yspread, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(provider)).onlyWhenEmpty());
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerFeature(String name, ConfiguredFeature<FC, ?> feature) {
        RegistryHelper.CONFIGURED_FEATURES.put(feature, name);
        return feature;
    }

    public static void registerFeatures(Registry<ConfiguredFeature<?,?>> registry) {
        for (Map.Entry<ConfiguredFeature<?,?>, String> entry : RegistryHelper.CONFIGURED_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
