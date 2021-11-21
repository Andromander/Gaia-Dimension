package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class GaiaBiomeFeatures {

    public static final RuleTest GAIA_STONE = new BlockMatchTest(ModBlocks.gaia_stone.get());
    public static final RuleTest VOLCANIC = new TagMatchTest(GaiaTags.Blocks.VOLCANIC);
    public static final RuleTest STATIC = new TagMatchTest(GaiaTags.Blocks.STATIC);

    public static final BlockState GLITTER_GRASS = state(ModBlocks.glitter_grass);
    public static final BlockState CORRUPT_GRASS = state(ModBlocks.corrupt_grass);
    public static final BlockState MURKY_GRASS = state(ModBlocks.murky_grass);
    public static final BlockState SOFT_GRASS = state(ModBlocks.soft_grass);
    public static final BlockState HEAVY_SOIL = state(ModBlocks.heavy_soil);
    public static final BlockState CORRUPT_SOIL = state(ModBlocks.corrupt_soil);
    public static final BlockState BOGGY_SOIL = state(ModBlocks.boggy_soil);
    public static final BlockState LIGHT_SOIL = state(ModBlocks.light_soil);
    public static final BlockState SALT = state(ModBlocks.salt);
    public static final BlockState WASTELAND_STONE = state(ModBlocks.wasteland_stone);
    public static final BlockState VOLCANIC_ROCK = state(ModBlocks.volcanic_rock);
    public static final BlockState SUPERHOT_MAGMA = state(ModBlocks.superhot_magma);
    public static final BlockState MINERAL_WATER = state(ModBlocks.mineral_water);
    public static final BlockState SWEET_MUCK = state(ModBlocks.sweet_muck);
    public static final BlockState LIQUID_AURA = state(ModBlocks.liquid_aura);
    public static final BlockState LIQUID_BISMUTH = state(ModBlocks.liquid_bismuth);
    public static final BlockState GUMMY_GLITTER = state(ModBlocks.gummy_glitter_block);
    public static final BlockState PRIMAL_MASS = state(ModBlocks.primal_mass);
    public static final BlockState THICK_GLITTER = state(ModBlocks.thick_glitter_block);
    public static final BlockState SEARING_ROCK = state(ModBlocks.searing_rock);
    public static final BlockState STATIC_STONE = state(ModBlocks.static_stone);
    public static final BlockState PEBBLES = state(ModBlocks.pebbles);
    public static final BlockState SPECKLED_ROCK = state(ModBlocks.speckled_rock);
    public static final BlockState COARSE_ROCK = state(ModBlocks.coarse_rock);
    public static final BlockState PRECIOUS_ROCK = state(ModBlocks.precious_rock);
    public static final BlockState RAW_AMETHYST = state(ModBlocks.raw_amethyst);
    public static final BlockState RAW_COPAL = state(ModBlocks.raw_copal);
    public static final BlockState RAW_JADE = state(ModBlocks.raw_jade);
    public static final BlockState RAW_JET = state(ModBlocks.raw_jet);
    public static final BlockState SUGILITE_ORE = state(ModBlocks.sugilite_ore);
    public static final BlockState HEMATITE_ORE = state(ModBlocks.hematite_ore);
    public static final BlockState PYRITE_ORE = state(ModBlocks.pyrite_ore);
    public static final BlockState CINNABAR_ORE = state(ModBlocks.cinnabar_ore);
    public static final BlockState LABRADORITE_ORE = state(ModBlocks.labradorite_ore);
    public static final BlockState MOONSTONE_ORE = state(ModBlocks.moonstone_ore);
    public static final BlockState RED_OPAL_ORE = state(ModBlocks.opal_ore_red);
    public static final BlockState BLUE_OPAL_ORE = state(ModBlocks.opal_ore_blue);
    public static final BlockState GREEN_OPAL_ORE = state(ModBlocks.opal_ore_green);
    public static final BlockState WHITE_OAL_ORE = state(ModBlocks.opal_ore_white);
    public static final BlockState PINK_AGATE_LOG = state(ModBlocks.pink_agate_log);
    public static final BlockState PINK_AGATE_LEAVES = state(ModBlocks.pink_agate_leaves);
    public static final BlockState BLUE_AGATE_LOG = state(ModBlocks.blue_agate_log);
    public static final BlockState BLUE_AGATE_LEAVES = state(ModBlocks.blue_agate_leaves);
    public static final BlockState GREEN_AGATE_LOG = state(ModBlocks.green_agate_log);
    public static final BlockState GREEN_AGATE_LEAVES = state(ModBlocks.green_agate_leaves);
    public static final BlockState PURPLE_AGATE_LOG = state(ModBlocks.purple_agate_log);
    public static final BlockState PURPLE_AGATE_LEAVES = state(ModBlocks.purple_agate_leaves);
    public static final BlockState FOSSIL_LOG = state(ModBlocks.fossilized_log);
    public static final BlockState FOSSIL_LEAVES = state(ModBlocks.fossilized_leaves);
    public static final BlockState CORRUPTED_LOG = state(ModBlocks.corrupted_log);
    public static final BlockState CORRUPTED_LEAVES = state(ModBlocks.corrupted_leaves);
    public static final BlockState BURNT_LOG = state(ModBlocks.burnt_log);
    public static final BlockState BURNT_LEAVES = state(ModBlocks.burnt_leaves);
    public static final BlockState BURNING_LOG = state(ModBlocks.burning_log);
    public static final BlockState BURNING_LEAVES = state(ModBlocks.burning_leaves);
    public static final BlockState AURA_LOG = state(ModBlocks.aura_log);
    public static final BlockState AURA_LEAVES = state(ModBlocks.aura_leaves);
    public static final BlockState CRYSTAL_GROWTH = state(ModBlocks.crystal_growth);
    public static final BlockState CRYSTAL_GROWTH_MUTANT = state(ModBlocks.crystal_growth_mutant);
    public static final BlockState CRYSTAL_GROWTH_SEARED = state(ModBlocks.crystal_growth_seared);
    public static final BlockState CRYSTAL_GROWTH_RED = state(ModBlocks.crystal_growth_red);
    public static final BlockState CRYSTAL_GROWTH_BLACK = state(ModBlocks.crystal_growth_black);
    public static final BlockState CRYSTAL_GROWTH_AURA = state(ModBlocks.crystal_growth_aura);
    public static final BlockState THISCUS = state(ModBlocks.thiscus);
    public static final BlockState OUZIUM = state(ModBlocks.ouzium);
    public static final BlockState AGATHUM = state(ModBlocks.agathum);
    public static final BlockState CORRUPTED_VARLOOM = state(ModBlocks.corrupted_varloom);
    public static final BlockState SPOTTED_KERSEI = state(ModBlocks.spotted_kersei);
    public static final BlockState THORNY_WILTHA = state(ModBlocks.thorny_wiltha);
    public static final BlockState ROOFED_AGARIC = state(ModBlocks.roofed_agaric);
    public static final BlockState BULBOUS_HOBINA = state(ModBlocks.bulbous_hobina);
    public static final BlockState STICKLY_CUPSIR = state(ModBlocks.stickly_cupsir);
    public static final BlockState MYSTICAL_MURGNI = state(ModBlocks.mystical_murgni);
    public static final BlockState CORRUPTED_GAIA_EYE = state(ModBlocks.corrupted_gaia_eye);
    public static final BlockState ELDER_IMKLIA = state(ModBlocks.elder_imklia);
    public static final BlockState GOLD_ORB_TUCHER = state(ModBlocks.gold_orb_tucher);

    public static final Set<BlockState> cave_blacklist = ImmutableSet.of(GLITTER_GRASS, HEAVY_SOIL, CORRUPT_GRASS, CORRUPT_SOIL, MURKY_GRASS, BOGGY_SOIL, SOFT_GRASS, LIGHT_SOIL, SALT);

    public static final SurfaceBuilderBaseConfiguration GLITTER_HEAVY_SALT = new SurfaceBuilderBaseConfiguration(GLITTER_GRASS, HEAVY_SOIL, SALT);
    public static final SurfaceBuilderBaseConfiguration CORRUPT_SALT = new SurfaceBuilderBaseConfiguration(CORRUPT_GRASS, CORRUPT_SOIL, SALT);
    public static final SurfaceBuilderBaseConfiguration SALTY_SURFACE = new SurfaceBuilderBaseConfiguration(SALT, SALT, SALT);
    public static final SurfaceBuilderBaseConfiguration MURKY_BOGGY_PEBBLES = new SurfaceBuilderBaseConfiguration(MURKY_GRASS, BOGGY_SOIL, PEBBLES);
    public static final SurfaceBuilderBaseConfiguration SOFT_LIGHT_SALT = new SurfaceBuilderBaseConfiguration(SOFT_GRASS, LIGHT_SOIL, PEBBLES);
    public static final SurfaceBuilderBaseConfiguration WASTELAND_STONE_SURFACE = new SurfaceBuilderBaseConfiguration(WASTELAND_STONE, WASTELAND_STONE, WASTELAND_STONE);
    public static final SurfaceBuilderBaseConfiguration GLITTER_HEAVY_VOLROCK = new SurfaceBuilderBaseConfiguration(GLITTER_GRASS, HEAVY_SOIL, VOLCANIC_ROCK);

    public static final GaiaTreeFeatureConfig PINK_AGATE_TREE_CONFIG = configureTree(PINK_AGATE_LOG, PINK_AGATE_LEAVES, 5, ModBlocks.pink_agate_sapling.get());
    public static final GaiaTreeFeatureConfig BLUE_AGATE_TREE_CONFIG = configureTree(BLUE_AGATE_LOG, BLUE_AGATE_LEAVES, 6, ModBlocks.blue_agate_sapling.get());
    public static final GaiaTreeFeatureConfig GREEN_AGATE_TREE_CONFIG = configureTree(GREEN_AGATE_LOG, GREEN_AGATE_LEAVES, 10, ModBlocks.green_agate_sapling.get());
    public static final TreeConfiguration GREEN_AGATE_BUSH_CONFIG = (
            new TreeConfiguration.TreeConfigurationBuilder(
                    new SimpleStateProvider(GREEN_AGATE_LOG),
                    new StraightTrunkPlacer(1, 0, 0),
                    new SimpleStateProvider(GREEN_AGATE_LEAVES),
                    new SimpleStateProvider(ModBlocks.green_agate_sapling.get().defaultBlockState()),
                    new BushFoliagePlacer(
                            ConstantInt.of(2),
                            ConstantInt.of(1),
                            2),
                    new TwoLayersFeatureSize(0, 0, 0)))
            //.heightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES)
            .build();
    public static final GaiaTreeFeatureConfig PURPLE_AGATE_TREE_CONFIG = configureTree(PURPLE_AGATE_LOG, PURPLE_AGATE_LEAVES, 7, ModBlocks.purple_agate_sapling.get());
    public static final GaiaTreeFeatureConfig FOSSILIZED_TREE_CONFIG = configureTree(FOSSIL_LOG, FOSSIL_LEAVES, 5, ModBlocks.fossilized_sapling.get());
    public static final GaiaTreeFeatureConfig CORRUPTED_TREE_CONFIG = configureTree(CORRUPTED_LOG, CORRUPTED_LEAVES, 7, ModBlocks.corrupted_sapling.get());
    public static final GaiaTreeFeatureConfig BURNT_TREE_CONFIG = configureTree(BURNT_LOG, BURNT_LEAVES, 5, ModBlocks.burnt_sapling.get());
    public static final GaiaTreeFeatureConfig BURNING_TREE_CONFIG = configureTree(BURNING_LOG, BURNING_LEAVES, 5, ModBlocks.burning_sapling.get());
    public static final GaiaTreeFeatureConfig AURA_TREE_CONFIG = configureTree(AURA_LOG, AURA_LEAVES, 10, ModBlocks.aura_sapling.get());

    public static final RandomPatchConfiguration NORMAL_GROWTH = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CRYSTAL_GROWTH), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration MUTANT_GROWTH = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CRYSTAL_GROWTH_MUTANT), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration SEARED_GROWTH = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CRYSTAL_GROWTH_SEARED), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration CORRUPT_GROWTH = (new RandomPatchConfiguration.GrassConfigurationBuilder(new WeightedStateProvider(weight().add(CRYSTAL_GROWTH_RED, 2).add(CRYSTAL_GROWTH_BLACK, 2)), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration AURA_GROWTH = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CRYSTAL_GROWTH_AURA), new SimpleBlockPlacer())).tries(32).build();

    public static final RandomPatchConfiguration COMMON_BLOOM = (new RandomPatchConfiguration.GrassConfigurationBuilder(new WeightedStateProvider(weight().add(THISCUS, 4).add(OUZIUM, 1)), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration RARE_BLOOM = (new RandomPatchConfiguration.GrassConfigurationBuilder(new WeightedStateProvider(weight().add(OUZIUM, 4).add(THISCUS, 1)), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration MUTANT_BLOOM = (new RandomPatchConfiguration.GrassConfigurationBuilder(new WeightedStateProvider(weight().add(OUZIUM, 4).add(AGATHUM, 1)), new SimpleBlockPlacer())).tries(32).build();
    public static final RandomPatchConfiguration CORRUPT_BLOOM = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CORRUPTED_VARLOOM), new SimpleBlockPlacer())).tries(64).build();

    public static final RandomPatchConfiguration KERSEI = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(SPOTTED_KERSEI), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration WILTHA = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(THORNY_WILTHA), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration AGARIC = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(ROOFED_AGARIC), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration HOBINA = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(BULBOUS_HOBINA), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration CUPSIR = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(STICKLY_CUPSIR), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration MURGNI = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(MYSTICAL_MURGNI), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration CORRUPT_EYE = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(CORRUPTED_GAIA_EYE), new SimpleBlockPlacer())).tries(16).build();
    public static final RandomPatchConfiguration CAVE_FUNGI = (new RandomPatchConfiguration.GrassConfigurationBuilder(new WeightedStateProvider(weight().add(ELDER_IMKLIA, 2).add(GOLD_ORB_TUCHER, 2)), new SimpleBlockPlacer())).tries(64).blacklist(cave_blacklist).noProjection().build();

    public static final ImmutableList<Supplier<ConfiguredFeature<?,?>>> BUSH_WORKAROUND = ImmutableList.of(
            () -> Feature.TREE.configured(GREEN_AGATE_BUSH_CONFIG)
    );

    //SurfaceBuilders
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> d_glitter_heavy_salt = registerSurfaceBuilder("glitter_grass", ModWorldgen.DEFAULT_GAIA.get().configured(GLITTER_HEAVY_SALT));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> d_corrupt_salt = registerSurfaceBuilder("corrupt_grass", ModWorldgen.DEFAULT_GAIA.get().configured(CORRUPT_SALT));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> d_salty = registerSurfaceBuilder("salty", ModWorldgen.DEFAULT_GAIA.get().configured(SALTY_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> d_murky_boggy_pebble = registerSurfaceBuilder("murky_grass", ModWorldgen.DEFAULT_GAIA.get().configured(MURKY_BOGGY_PEBBLES));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> d_soft_light_salt = registerSurfaceBuilder("soft_grass", ModWorldgen.DEFAULT_GAIA.get().configured(SOFT_LIGHT_SALT));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> s_wasteland_stone = registerSurfaceBuilder("wasteland_stone", ModWorldgen.STATIC.get().configured(WASTELAND_STONE_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> v_glitter_heavy_volrock = registerSurfaceBuilder("volcanic_grass", ModWorldgen.VOLCANIC.get().configured(GLITTER_HEAVY_VOLROCK));

    //Carvers
    public static final ConfiguredWorldCarver<CaveCarverConfiguration> crystal_caves = registerCarver("crystal_caves", ModWorldgen.CRYSTAL_CAVES.get().configured(new CaveCarverConfiguration(
            0.15F,
            BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(256), 8),
            ConstantFloat.of(0.5F),
            VerticalAnchor.aboveBottom(10),
            false,
            CarverDebugSettings.DEFAULT,
            ConstantFloat.of(1.0F),
            ConstantFloat.of(1.0F),
            ConstantFloat.of(-0.7F))));
    public static final ConfiguredWorldCarver<CaveCarverConfiguration> chasms = registerCarver("chasms", ModWorldgen.CHASMS.get().configured(new CaveCarverConfiguration(
            0.03F,
            BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32), 8),
            ConstantFloat.of(0.5F),
            VerticalAnchor.aboveBottom(10),
            false,
            CarverDebugSettings.DEFAULT,
            ConstantFloat.of(1.0F),
            ConstantFloat.of(1.0F),
            ConstantFloat.of(-0.7F))));

    //StructureFeatures
    public static final ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> mini_tower = registerStructureFeature("mini_tower", ModWorldgen.MINI_TOWER.get().configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> malachite_watchtower = registerStructureFeature("malachite_watchtower", ModWorldgen.MALACHITE_WATCHTOWER.get().configured(NoneFeatureConfiguration.INSTANCE));

    //Lakes
    public static final ConfiguredFeature<?, ?> lake_superhot_magma_common = registerFeature("lake_superhot_magma_common", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(SUPERHOT_MAGMA))
            .decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(15)))
            .range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)))
            .squared()
            .rarity(4));
    public static final ConfiguredFeature<?, ?> lake_superhot_magma_rare = registerFeature("lake_superhot_magma_rare", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(SUPERHOT_MAGMA))
            .decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(80)))
            .range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8)))
            .squared()
            .rarity(8));
    public static final ConfiguredFeature<?, ?> lake_mineral_water_common = registerFeature("lake_mineral_water_common", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(MINERAL_WATER))
            .range(Features.Decorators.FULL_RANGE)
            .squared()
            .rarity(4));
    public static final ConfiguredFeature<?, ?> lake_mineral_water_uncommon = registerFeature("lake_mineral_water_uncommon", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(MINERAL_WATER))
            .range(Features.Decorators.FULL_RANGE)
            .squared()
            .rarity(40));
    public static final ConfiguredFeature<?, ?> lake_mineral_water_rare = registerFeature("lake_mineral_water_rare", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(MINERAL_WATER))
            .range(Features.Decorators.FULL_RANGE)
            .squared()
            .rarity(50));
    public static final ConfiguredFeature<?, ?> lake_sweet_muck = registerFeature("lake_sweet_muck", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(SWEET_MUCK))
            .decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(10)))
            .range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.absolute(40), VerticalAnchor.absolute(120), 8)))
            .squared()
            .rarity(4));
    public static final ConfiguredFeature<?, ?> lake_liquid_aura = registerFeature("lake_liquid_aura", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(LIQUID_AURA))
            .decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(20)))
            .range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.absolute(40), VerticalAnchor.absolute(120), 8)))
            .squared()
            .rarity(4));
    public static final ConfiguredFeature<?, ?> lake_liquid_bismuth = registerFeature("lake_liquid_bismuth", ModWorldgen.POOL.get()
            .configured(new BlockStateConfiguration(LIQUID_BISMUTH))
            .decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(20)))
            .range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.absolute(20), VerticalAnchor.absolute(80), 8)))
            .squared()
            .rarity(4));

    //Local Modifications
    public static final ConfiguredFeature<?, ?> gummy_glitter_blob = registerFeature("gummy_glitter_blob", ModWorldgen.GAIA_BLOB.get().configured(new BlockStateConfiguration(GUMMY_GLITTER)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(2));
    public static final ConfiguredFeature<?, ?> static_spikes = registerFeature("static_spikes", ModWorldgen.STATIC_SPIKE.get().configured(new FeatureHeightConfig(8)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(2));
    public static final ConfiguredFeature<?, ?> bismuth_spires = registerFeature("bismuth_spires", ModWorldgen.BISMUTH_SPIRE.get().configured(new FeatureHeightConfig(7)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(2));
    public static final ConfiguredFeature<?, ?> bismuth_geysers = registerFeature("bismuth_geysers", ModWorldgen.BISMUTH_GEYSER.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(2));

    //Underground Ores
    public static final ConfiguredFeature<?, ?> ore_primal_mass = registerFeature("ore_primal_mass", makeOreFeature(GAIA_STONE, PRIMAL_MASS, 33, 25, 33));
    public static final ConfiguredFeature<?, ?> ore_thick_glitter = registerFeature("ore_thick_glitter", makeOreFeature(GAIA_STONE, THICK_GLITTER, 33, 100, 9));
    public static final ConfiguredFeature<?, ?> ore_searing_rock = registerFeature("ore_searing_rock", makeOreFeature(VOLCANIC, SEARING_ROCK, 33, 100, 9));
    public static final ConfiguredFeature<?, ?> ore_static_stone = registerFeature("ore_static_stone", makeOreFeature(STATIC, STATIC_STONE, 33, 100, 9));
    public static final ConfiguredFeature<?, ?> ore_pebbles = registerFeature("ore_pebbles", makeOreFeature(GAIA_STONE, PEBBLES, 25, 128, 25));
    public static final ConfiguredFeature<?, ?> ore_speckled_rock = registerFeature("ore_speckled_rock", makeOreFeature(GAIA_STONE, SPECKLED_ROCK, 8, 120, 10));
    public static final ConfiguredFeature<?, ?> ore_coarse_rock = registerFeature("ore_coarse_rock", makeOreFeature(GAIA_STONE, COARSE_ROCK, 8, 60, 10));
    public static final ConfiguredFeature<?, ?> ore_precious_rock = registerFeature("ore_precious_rock", makeOreFeature(GAIA_STONE, PRECIOUS_ROCK, 8, 30, 10));
    public static final ConfiguredFeature<?, ?> ore_raw_amethyst = registerFeature("ore_raw_amethyst", makeOreFeature(GAIA_STONE, RAW_AMETHYST, 12, 120, 15));
    public static final ConfiguredFeature<?, ?> ore_raw_copal = registerFeature("ore_raw_copal", makeOreFeature(GAIA_STONE, RAW_COPAL, 12, 120, 15));
    public static final ConfiguredFeature<?, ?> ore_raw_jade = registerFeature("ore_raw_jade", makeOreFeature(GAIA_STONE, RAW_JADE, 12, 120, 15));
    public static final ConfiguredFeature<?, ?> ore_raw_jet = registerFeature("ore_raw_jet", makeOreFeature(GAIA_STONE, RAW_JET, 12, 120, 15));
    public static final ConfiguredFeature<?, ?> ore_sugilite = registerFeature("ore_sugilite", makeOreFeature(GAIA_STONE, SUGILITE_ORE, 17, 100, 8));
    public static final ConfiguredFeature<?, ?> ore_hematite = registerFeature("ore_hematite", makeOreFeature(GAIA_STONE, HEMATITE_ORE, 17, 100, 8));
    public static final ConfiguredFeature<?, ?> ore_pyrite = registerFeature("ore_pyrite", makeOreFeature(GAIA_STONE, PYRITE_ORE, 9, 80, 8));
    public static final ConfiguredFeature<?, ?> ore_cinnabar = registerFeature("ore_cinnabar", makeOreFeature(GAIA_STONE, CINNABAR_ORE, 9, 60, 7));
    public static final ConfiguredFeature<?, ?> ore_labradorite = registerFeature("ore_labradorite", makeOreFeature(GAIA_STONE, LABRADORITE_ORE, 9, 40, 6));
    public static final ConfiguredFeature<?, ?> ore_moonstone = registerFeature("ore_moonstone", makeOreFeature(GAIA_STONE, MOONSTONE_ORE, 9, 40, 6));
    public static final ConfiguredFeature<?, ?> ore_red_opal = registerFeature("ore_red_opal", makeOreFeature(GAIA_STONE, RED_OPAL_ORE, 8, 30, 4));
    public static final ConfiguredFeature<?, ?> ore_blue_opal = registerFeature("ore_blue_opal", makeOreFeature(GAIA_STONE, BLUE_OPAL_ORE, 8, 30, 4));
    public static final ConfiguredFeature<?, ?> ore_green_opal = registerFeature("ore_green_opal", makeOreFeature(GAIA_STONE, GREEN_OPAL_ORE, 8, 30, 4));
    public static final ConfiguredFeature<?, ?> ore_white_opal_common = registerFeature("ore_white_opal_common", makeOreFeature(GAIA_STONE, WHITE_OAL_ORE, 8, 25, 4));
    public static final ConfiguredFeature<?, ?> ore_white_opal_rare = registerFeature("ore_white_opal_rare", makeOreFeature(GAIA_STONE, WHITE_OAL_ORE, 8, 20, 3));
    public static final ConfiguredFeature<?, ?> disk_static_stone = registerFeature("disk_static_stone", ModWorldgen.GAIA_DISK.get().configured(new DiskConfiguration(STATIC_STONE, UniformInt.of(4, 2), 3, ImmutableList.of(WASTELAND_STONE))).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> disk_bog_patch = registerFeature("disk_bog_patch", ModWorldgen.BOG_PATCH.get().configured(new DiskConfiguration(ModBlocks.impure_sludge.get().defaultBlockState(), UniformInt.of(4, 1), 2, Lists.newArrayList(MURKY_GRASS, BOGGY_SOIL))).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE));

    //Underground Decoration
    public static final ConfiguredFeature<?, ?> underground_glitter_blob = registerFeature("underground_glitter_blob", ModWorldgen.FRAIL_BLOB.get().configured(FeatureConfiguration.NONE).range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(50), VerticalAnchor.absolute(70)))).squared().count(100));
    public static final ConfiguredFeature<?, ?> crystal_fungi_caves = registerFeature("crystal_fungi_caves", Feature.RANDOM_PATCH.configured(CAVE_FUNGI).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).rarity(2));

    //Vegetal Decoration
    public static final ConfiguredFeature<?, ?> pink_agate_tree_common = registerFeature("pink_agate_tree_common", makeTreeFeature(ModWorldgen.PINK_AGATE_TREE, PINK_AGATE_TREE_CONFIG, 4, 0.3F, 1));
    public static final ConfiguredFeature<?, ?> pink_agate_tree_rare = registerFeature("pink_agate_tree_rare", makeTreeFeature(ModWorldgen.PINK_AGATE_TREE, PINK_AGATE_TREE_CONFIG, 0, 0.1F, 1));
    public static final ConfiguredFeature<?, ?> blue_agate_tree = registerFeature("blue_agate_tree", makeTreeFeature(ModWorldgen.BLUE_AGATE_TREE, BLUE_AGATE_TREE_CONFIG, 1, 0.3F, 1));
    public static final ConfiguredFeature<?, ?> green_agate_tree = registerFeature("green_agate_tree", makeTreeFeature(ModWorldgen.GREEN_AGATE_TREE, GREEN_AGATE_TREE_CONFIG, 5, 0.3F, 1));
    public static final ConfiguredFeature<?, ?> green_agate_bush = registerFeature("green_agate_bush", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(BUSH_WORKAROUND)));
    public static final ConfiguredFeature<?, ?> purple_agate_tree = registerFeature("purple_agate_tree", makeTreeFeature(ModWorldgen.PURPLE_AGATE_TREE, PURPLE_AGATE_TREE_CONFIG, 1, 0.1F, 2));
    public static final ConfiguredFeature<?, ?> various_agate_trees = registerFeature("various_agate_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(
            ModWorldgen.PINK_AGATE_TREE.get().configured(PINK_AGATE_TREE_CONFIG).weighted(0.1F),
                ModWorldgen.BLUE_AGATE_TREE.get().configured(BLUE_AGATE_TREE_CONFIG).weighted(0.1F),
                ModWorldgen.GREEN_AGATE_TREE.get().configured(GREEN_AGATE_TREE_CONFIG).weighted(0.1F),
                ModWorldgen.PURPLE_AGATE_TREE.get().configured(PURPLE_AGATE_TREE_CONFIG).weighted(0.1F)
        ), Feature.RANDOM_PATCH.configured(MUTANT_GROWTH))).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(2, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> fossilized_tree = registerFeature("fossilized_tree", makeTreeFeature(ModWorldgen.FOSSILIZED_TREE, FOSSILIZED_TREE_CONFIG, 1, 0.3F, 1));
    public static final ConfiguredFeature<?, ?> goldstone_tree = registerFeature("goldstone_tree", makeTreeFeature(ModWorldgen.GOLDSTONE_TREE, CORRUPTED_TREE_CONFIG, 1, 0.1F, 1));
    public static final ConfiguredFeature<?, ?> burnt_agate_tree = registerFeature("burnt_agate_tree", makeTreeFeature(ModWorldgen.BURNT_AGATE_TREE, BURNT_TREE_CONFIG, 0, 0.1F, 1));
    public static final ConfiguredFeature<?, ?> fiery_agate_tree = registerFeature("fiery_agate_tree", makeTreeFeature(ModWorldgen.FIERY_AGATE_TREE, BURNING_TREE_CONFIG, 0, 0.1F, 1));
    public static final ConfiguredFeature<?, ?> aura_tree = registerFeature("aura_tree", makeTreeFeature(ModWorldgen.AURA_TREE, AURA_TREE_CONFIG, 2, 0.1F, 1));
    public static final ConfiguredFeature<?, ?> aura_shoots = registerFeature("aura_shoots", ModWorldgen.AURA_SHOOT.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(6));
    public static final ConfiguredFeature<?, ?> crystal_growth_02 = registerFeature("crystal_growth_02", makeGrowthFeature(NORMAL_GROWTH, 2));
    public static final ConfiguredFeature<?, ?> crystal_growth_03 = registerFeature("crystal_growth_03", makeGrowthFeature(NORMAL_GROWTH, 3));
    public static final ConfiguredFeature<?, ?> crystal_growth_04 = registerFeature("crystal_growth_04", makeGrowthFeature(NORMAL_GROWTH, 4));
    public static final ConfiguredFeature<?, ?> crystal_growth_05 = registerFeature("crystal_growth_05", makeGrowthFeature(NORMAL_GROWTH, 5));
    public static final ConfiguredFeature<?, ?> crystal_growth_seared = registerFeature("crystal_growth_seared", makeGrowthFeature(SEARED_GROWTH, 1));
    public static final ConfiguredFeature<?, ?> crystal_growth_corrupt = registerFeature("crystal_growth_corrupt", makeGrowthFeature(CORRUPT_GROWTH, 1));
    public static final ConfiguredFeature<?, ?> crystal_growth_aura = registerFeature("crystal_growth_aura", makeGrowthFeature(AURA_GROWTH, 2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_common = registerFeature("crystal_blooms_common", makePlantFeature(COMMON_BLOOM, 2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_rare = registerFeature("crystal_blooms_rare", makePlantFeature(RARE_BLOOM, 2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_mutant = registerFeature("crystal_blooms_mutant", makePlantFeature(MUTANT_BLOOM, 2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_corrupt = registerFeature("crystal_blooms_corrupt", makePlantFeature(CORRUPT_BLOOM, 1));
    public static final ConfiguredFeature<?, ?> spotted_kersei = registerFeature("spotted_kersei", makePlantFeature(KERSEI, 1));
    public static final ConfiguredFeature<?, ?> thorny_wiltha = registerFeature("thorny_wiltha", makePlantFeature(WILTHA, 1));
    public static final ConfiguredFeature<?, ?> roofed_agaric = registerFeature("roofed_agaric", makePlantFeature(AGARIC, 1));
    public static final ConfiguredFeature<?, ?> bulbous_hobina = registerFeature("bulbous_hobina", makePlantFeature(HOBINA, 1));
    public static final ConfiguredFeature<?, ?> stickly_cupsir = registerFeature("stickly_cupsir", makePlantFeature(CUPSIR, 1));
    public static final ConfiguredFeature<?, ?> mystical_murgni = registerFeature("mystical_murgni", makePlantFeature(MURGNI, 1));
    public static final ConfiguredFeature<?, ?> corrupted_gaia_eye = registerFeature("corrupted_gaia_eye", makePlantFeature(CORRUPT_EYE, 1));
    
    private static BlockState state(RegistryObject<? extends Block> block) {
        return block.get().defaultBlockState();
    }

    private static SimpleWeightedRandomList.Builder<BlockState> weight() {
        return SimpleWeightedRandomList.builder();
    }

    public static GaiaTreeFeatureConfig configureTree(BlockState log, BlockState leaves, int height, SaplingBlock sapling) {
        return (new GaiaTreeFeatureConfig.Builder(new SimpleStateProvider(log), new SimpleStateProvider(leaves), height).setSapling(sapling)).build();
    }

    public static ConfiguredFeature<?, ?> makeOreFeature(RuleTest test, BlockState ore, int size, int height, int count) {
        return Feature.ORE.configured(new OreConfiguration(test, ore, size))
                .rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(height))
                .squared()
                .count(count); //count per chunk
    }

    public static ConfiguredFeature<?, ?> makeTreeFeature(Supplier<Feature<GaiaTreeFeatureConfig>> feature, GaiaTreeFeatureConfig config, int count, float chance, int extra) {
        return feature.get().configured(config)
                .decorated(Features.Decorators.HEIGHTMAP_SQUARE)
                .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(count, chance, extra)));
    }

    public static ConfiguredFeature<?, ?> makeGrowthFeature(RandomPatchConfiguration config, int count) {
        return Feature.RANDOM_PATCH.configured(config)
                .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                .count(count); //count per chunk
    }

    public static ConfiguredFeature<?, ?> makePlantFeature(RandomPatchConfiguration config, int count) {
        return Feature.RANDOM_PATCH.configured(config)
                .decorated(Features.Decorators.ADD_32)
                .decorated(Features.Decorators.HEIGHTMAP_SQUARE)
                .count(count); //count per chunk
    }

    private static <SC extends SurfaceBuilderConfiguration> ConfiguredSurfaceBuilder<SC> registerSurfaceBuilder(String name, ConfiguredSurfaceBuilder<SC> surface) {
        RegistryHelper.CONFIGURED_SURFACE_BUILDERS.put(surface, name);
        return surface;
    }

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> registerStructureFeature(String name, ConfiguredStructureFeature<FC, F> structure) {
        RegistryHelper.CONFIGURED_STRUCTURE_FEATURES.put(structure, name);
        return structure;
    }

    private static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> registerCarver(String name, ConfiguredWorldCarver<WC> carver) {
        RegistryHelper.CONFIGURED_WORLD_CARVERS.put(carver, name);
        return carver;
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

    public static void registerSurfaceBuilders(Registry<ConfiguredSurfaceBuilder<?>> registry) {
        for (Map.Entry<ConfiguredSurfaceBuilder<?>, String> entry : RegistryHelper.CONFIGURED_SURFACE_BUILDERS.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }

    public static void registerCarvers(Registry<ConfiguredWorldCarver<?>> registry) {
        for (Map.Entry<ConfiguredWorldCarver<?>, String> entry : RegistryHelper.CONFIGURED_WORLD_CARVERS.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }

    public static void registerStructureFeatures(Registry<ConfiguredStructureFeature<?,?>> registry) {
        for (Map.Entry<ConfiguredStructureFeature<?,?>, String> entry : RegistryHelper.CONFIGURED_STRUCTURE_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
