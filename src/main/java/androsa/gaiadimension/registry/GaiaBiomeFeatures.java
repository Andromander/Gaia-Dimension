package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.Set;

public final class GaiaBiomeFeatures {

    public static final RuleTest GAIA_STONE = new BlockMatchRuleTest(ModBlocks.gaia_stone);
    public static final RuleTest VOLCANIC = new TagMatchRuleTest(GaiaDimensionMod.VOLCANIC);
    public static final RuleTest STATIC = new TagMatchRuleTest(GaiaDimensionMod.STATIC);

    public static final Set<BlockState> cave_blacklist = ImmutableSet.of(ModBlocks.glitter_grass.getDefaultState(), ModBlocks.heavy_soil.getDefaultState(), ModBlocks.corrupt_grass.getDefaultState(), ModBlocks.corrupt_soil.getDefaultState(), ModBlocks.murky_grass.getDefaultState(), ModBlocks.boggy_soil.getDefaultState(), ModBlocks.soft_grass.getDefaultState(), ModBlocks.light_soil.getDefaultState(), ModBlocks.salt.getDefaultState());

    public static final BlockState GLITTER_GRASS = ModBlocks.glitter_grass.getDefaultState();
    public static final BlockState CORRUPT_GRASS = ModBlocks.corrupt_grass.getDefaultState();
    public static final BlockState MURKY_GRASS = ModBlocks.murky_grass.getDefaultState();
    public static final BlockState SOFT_GRASS = ModBlocks.soft_grass.getDefaultState();
    public static final BlockState HEAVY_SOIL = ModBlocks.heavy_soil.getDefaultState();
    public static final BlockState CORRUPT_SOIL = ModBlocks.corrupt_soil.getDefaultState();
    public static final BlockState BOGGY_SOIL = ModBlocks.boggy_soil.getDefaultState();
    public static final BlockState LIGHT_SOIL = ModBlocks.light_soil.getDefaultState();
    public static final BlockState SALT = ModBlocks.salt.getDefaultState();
    public static final BlockState WASTELAND_STONE = ModBlocks.wasteland_stone.getDefaultState();
    public static final BlockState VOLCANIC_ROCK = ModBlocks.volcanic_rock.getDefaultState();
    public static final BlockState SUPERHOT_MAGMA = ModBlocks.superhot_magma.getDefaultState();
    public static final BlockState MINERAL_WATER = ModBlocks.mineral_water.getDefaultState();
    public static final BlockState SWEET_MUCK = ModBlocks.sweet_muck.getDefaultState();
    public static final BlockState LIQUID_AURA = ModBlocks.liquid_aura.getDefaultState();
    public static final BlockState LIQUID_BISMUTH = ModBlocks.liquid_bismuth.getDefaultState();
    public static final BlockState GUMMY_GLITTER = ModBlocks.gummy_glitter_block.getDefaultState();
    public static final BlockState PRIMAL_MASS = ModBlocks.primal_mass.getDefaultState();
    public static final BlockState THICK_GLITTER = ModBlocks.thick_glitter_block.getDefaultState();
    public static final BlockState SEARING_ROCK = ModBlocks.searing_rock.getDefaultState();
    public static final BlockState STATIC_STONE = ModBlocks.static_stone.getDefaultState();
    public static final BlockState PEBBLES = ModBlocks.pebbles.getDefaultState();
    public static final BlockState SPECKLED_ROCK = ModBlocks.speckled_rock.getDefaultState();
    public static final BlockState COARSE_ROCK = ModBlocks.coarse_rock.getDefaultState();
    public static final BlockState PRECIOUS_ROCK = ModBlocks.precious_rock.getDefaultState();
    public static final BlockState RAW_AMETHYST = ModBlocks.raw_amethyst.getDefaultState();
    public static final BlockState RAW_COPAL = ModBlocks.raw_copal.getDefaultState();
    public static final BlockState RAW_JADE = ModBlocks.raw_jade.getDefaultState();
    public static final BlockState RAW_JET = ModBlocks.raw_jet.getDefaultState();
    public static final BlockState SUGILITE_ORE = ModBlocks.sugilite_ore.getDefaultState();
    public static final BlockState HEMATITE_ORE = ModBlocks.hematite_ore.getDefaultState();
    public static final BlockState PYRITE_ORE = ModBlocks.pyrite_ore.getDefaultState();
    public static final BlockState CINNABAR_ORE = ModBlocks.cinnabar_ore.getDefaultState();
    public static final BlockState LABRADORITE_ORE = ModBlocks.labradorite_ore.getDefaultState();
    public static final BlockState MOONSTONE_ORE = ModBlocks.moonstone_ore.getDefaultState();
    public static final BlockState RED_OPAL_ORE = ModBlocks.opal_ore_red.getDefaultState();
    public static final BlockState BLUE_OPAL_ORE = ModBlocks.opal_ore_blue.getDefaultState();
    public static final BlockState GREEN_OPAL_ORE = ModBlocks.opal_ore_green.getDefaultState();
    public static final BlockState WHITE_OAL_ORE = ModBlocks.opal_ore_white.getDefaultState();
    public static final BlockState PINK_AGATE_LOG = ModBlocks.pink_agate_log.getDefaultState();
    public static final BlockState PINK_AGATE_LEAVES = ModBlocks.pink_agate_leaves.getDefaultState();
    public static final BlockState BLUE_AGATE_LOG = ModBlocks.blue_agate_log.getDefaultState();
    public static final BlockState BLUE_AGATE_LEAVES = ModBlocks.blue_agate_leaves.getDefaultState();
    public static final BlockState GREEN_AGATE_LOG = ModBlocks.green_agate_log.getDefaultState();
    public static final BlockState GREEN_AGATE_LEAVES = ModBlocks.green_agate_leaves.getDefaultState();
    public static final BlockState PURPLE_AGATE_LOG = ModBlocks.purple_agate_log.getDefaultState();
    public static final BlockState PURPLE_AGATE_LEAVES = ModBlocks.purple_agate_leaves.getDefaultState();
    public static final BlockState FOSSIL_LOG = ModBlocks.fossilized_log.getDefaultState();
    public static final BlockState FOSSIL_LEAVES = ModBlocks.fossilized_leaves.getDefaultState();
    public static final BlockState CORRUPTED_LOG = ModBlocks.corrupted_log.getDefaultState();
    public static final BlockState CORRUPTED_LEAVES = ModBlocks.corrupted_leaves.getDefaultState();
    public static final BlockState BURNT_LOG = ModBlocks.burnt_log.getDefaultState();
    public static final BlockState BURNT_LEAVES = ModBlocks.burnt_leaves.getDefaultState();
    public static final BlockState BURNING_LOG = ModBlocks.burning_log.getDefaultState();
    public static final BlockState BURNING_LEAVES = ModBlocks.burning_leaves.getDefaultState();
    public static final BlockState AURA_LOG = ModBlocks.aura_log.getDefaultState();
    public static final BlockState AURA_LEAVES = ModBlocks.aura_leaves.getDefaultState();
    public static final BlockState CRYSTAL_GROWTH = ModBlocks.crystal_growth.getDefaultState();
    public static final BlockState CRYSTAL_GROWTH_MUTANT = ModBlocks.crystal_growth_mutant.getDefaultState();
    public static final BlockState CRYSTAL_GROWTH_SEARED = ModBlocks.crystal_growth_seared.getDefaultState();
    public static final BlockState CRYSTAL_GROWTH_RED = ModBlocks.crystal_growth_red.getDefaultState();
    public static final BlockState CRYSTAL_GROWTH_BLACK = ModBlocks.crystal_growth_black.getDefaultState();
    public static final BlockState CRYSTAL_GROWTH_AURA = ModBlocks.crystal_growth_aura.getDefaultState();
    public static final BlockState THISCUS = ModBlocks.thiscus.getDefaultState();
    public static final BlockState OUZIUM = ModBlocks.ouzium.getDefaultState();
    public static final BlockState AGATHUM = ModBlocks.agathum.getDefaultState();
    public static final BlockState CORRUPTED_VARLOOM = ModBlocks.corrupted_varloom.getDefaultState();
    public static final BlockState SPOTTED_KERSEI = ModBlocks.spotted_kersei.getDefaultState();
    public static final BlockState THORNY_WILTHA = ModBlocks.thorny_wiltha.getDefaultState();
    public static final BlockState ROOFED_AGARIC = ModBlocks.roofed_agaric.getDefaultState();
    public static final BlockState BULBOUS_HOBINA = ModBlocks.bulbous_hobina.getDefaultState();
    public static final BlockState STICKLY_CUPSIR = ModBlocks.stickly_cupsir.getDefaultState();
    public static final BlockState MYSTICAL_MURGNI = ModBlocks.mystical_murgni.getDefaultState();
    public static final BlockState CORRUPTED_GAIA_EYE = ModBlocks.corrupted_gaia_eye.getDefaultState();
    public static final BlockState ELDER_IMKLIA = ModBlocks.elder_imklia.getDefaultState();
    public static final BlockState GOLD_ORB_TUCHER = ModBlocks.gold_orb_tucher.getDefaultState();

    public static final SurfaceBuilderConfig GLITTER_HEAVY_SALT = new SurfaceBuilderConfig(GLITTER_GRASS, HEAVY_SOIL, SALT);
    public static final SurfaceBuilderConfig CORRUPT_SALT = new SurfaceBuilderConfig(CORRUPT_GRASS, CORRUPT_SOIL, SALT);
    public static final SurfaceBuilderConfig SALTY_SURFACE = new SurfaceBuilderConfig(SALT, SALT, SALT);
    public static final SurfaceBuilderConfig MURKY_BOGGY_PEBBLES = new SurfaceBuilderConfig(MURKY_GRASS, BOGGY_SOIL, PEBBLES);
    public static final SurfaceBuilderConfig SOFT_LIGHT_SALT = new SurfaceBuilderConfig(SOFT_GRASS, LIGHT_SOIL, PEBBLES);
    public static final SurfaceBuilderConfig WASTELAND_STONE_SURFACE = new SurfaceBuilderConfig(WASTELAND_STONE, WASTELAND_STONE, WASTELAND_STONE);
    public static final SurfaceBuilderConfig GLITTER_HEAVY_VOLROCK = new SurfaceBuilderConfig(GLITTER_GRASS, HEAVY_SOIL, VOLCANIC_ROCK);

    public static final GaiaTreeFeatureConfig PINK_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(PINK_AGATE_LOG), new SimpleBlockStateProvider(PINK_AGATE_LEAVES), 5).setSapling(ModBlocks.pink_agate_sapling)).build();
    public static final GaiaTreeFeatureConfig BLUE_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BLUE_AGATE_LOG), new SimpleBlockStateProvider(BLUE_AGATE_LEAVES), 6).setSapling(ModBlocks.blue_agate_sapling)).build();
    public static final GaiaTreeFeatureConfig GREEN_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GREEN_AGATE_LOG), new SimpleBlockStateProvider(GREEN_AGATE_LEAVES), 10).setSapling(ModBlocks.green_agate_sapling)).build();
    public static final BaseTreeFeatureConfig GREEN_AGATE_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(GREEN_AGATE_LOG), new SimpleBlockStateProvider(GREEN_AGATE_LEAVES), new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();
    public static final GaiaTreeFeatureConfig PURPLE_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(PURPLE_AGATE_LOG), new SimpleBlockStateProvider(PURPLE_AGATE_LEAVES), 7).setSapling(ModBlocks.purple_agate_sapling)).build();
    public static final GaiaTreeFeatureConfig FOSSILIZED_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(FOSSIL_LOG), new SimpleBlockStateProvider(FOSSIL_LEAVES), 5).setSapling(ModBlocks.fossilized_sapling)).build();
    public static final GaiaTreeFeatureConfig CORRUPTED_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CORRUPTED_LOG), new SimpleBlockStateProvider(CORRUPTED_LEAVES), 7).setSapling(ModBlocks.corrupted_sapling)).build();
    public static final GaiaTreeFeatureConfig BURNT_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BURNT_LOG), new SimpleBlockStateProvider(BURNT_LEAVES), 5).setSapling(ModBlocks.burnt_sapling)).build();
    public static final GaiaTreeFeatureConfig BURNING_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BURNING_LOG), new SimpleBlockStateProvider(BURNING_LEAVES), 5).setSapling(ModBlocks.burning_sapling)).build();
    public static final GaiaTreeFeatureConfig AURA_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(AURA_LOG), new SimpleBlockStateProvider(AURA_LEAVES), 10).setSapling(ModBlocks.aura_sapling)).build();

    public static final BlockClusterFeatureConfig NORMAL_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CRYSTAL_GROWTH), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig MUTANT_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CRYSTAL_GROWTH_MUTANT), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig SEARED_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CRYSTAL_GROWTH_SEARED), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig CORRUPT_GROWTH = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(CRYSTAL_GROWTH_RED, 2).addWeightedBlockstate(CRYSTAL_GROWTH_BLACK, 2), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig AURA_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CRYSTAL_GROWTH_AURA), new SimpleBlockPlacer())).tries(32).build();

    public static final BlockClusterFeatureConfig COMMON_BLOOM = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(THISCUS, 4).addWeightedBlockstate(OUZIUM, 1), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig RARE_BLOOM = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(OUZIUM, 4).addWeightedBlockstate(THISCUS, 1), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig MUTANT_BLOOM = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(OUZIUM, 4).addWeightedBlockstate(AGATHUM, 1), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig CORRUPT_BLOOM = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CORRUPTED_VARLOOM), new SimpleBlockPlacer())).tries(64).build();

    public static final BlockClusterFeatureConfig KERSEI = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SPOTTED_KERSEI), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig WILTHA = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(THORNY_WILTHA), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig AGARIC = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ROOFED_AGARIC), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig HOBINA = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BULBOUS_HOBINA), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig CUPSIR = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(STICKLY_CUPSIR), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig MURGNI = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(MYSTICAL_MURGNI), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig CORRUPT_EYE = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CORRUPTED_GAIA_EYE), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig CAVE_FUNGI = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(ELDER_IMKLIA, 2).addWeightedBlockstate(GOLD_ORB_TUCHER, 2), new SimpleBlockPlacer())).tries(64).blacklist(cave_blacklist).func_227317_b_().build();

    //SurfaceBuilders
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> d_glitter_heavy_salt = registerSurfaceBuilder("glitter_grass", ModWorldgen.DEFAULT_GAIA.func_242929_a(GLITTER_HEAVY_SALT));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> d_corrupt_salt = registerSurfaceBuilder("corrupt_grass", ModWorldgen.DEFAULT_GAIA.func_242929_a(CORRUPT_SALT));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> d_salty = registerSurfaceBuilder("salty", ModWorldgen.DEFAULT_GAIA.func_242929_a(SALTY_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> d_murky_boggy_pebble = registerSurfaceBuilder("murky_grass", ModWorldgen.DEFAULT_GAIA.func_242929_a(MURKY_BOGGY_PEBBLES));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> d_soft_light_salt = registerSurfaceBuilder("soft_grass", ModWorldgen.DEFAULT_GAIA.func_242929_a(SOFT_LIGHT_SALT));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> s_wasteland_stone = registerSurfaceBuilder("wasteland_stone", ModWorldgen.STATIC.func_242929_a(WASTELAND_STONE_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> v_glitter_heavy_volrock = registerSurfaceBuilder("volcanic_grass", ModWorldgen.VOLCANIC.func_242929_a(GLITTER_HEAVY_VOLROCK));

    //Carvers
    public static final ConfiguredCarver<ProbabilityConfig> crystal_caves = registerCarver("crystal_caves", ModWorldgen.CRYSTAL_CAVES.func_242761_a(new ProbabilityConfig(0.15F)));
    public static final ConfiguredCarver<ProbabilityConfig> chasms = registerCarver("chasms", ModWorldgen.CHASMS.func_242761_a(new ProbabilityConfig(0.03F)));

    //StructureFeatures
    public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> mini_tower = registerStructureFeature("mini_tower", ModWorldgen.MINI_TOWER.func_236391_a_(NoFeatureConfig.field_236559_b_));
    public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> malachite_watchtower = registerStructureFeature("malachite_watchtower", ModWorldgen.MALACHITE_WATCHTOWER.func_236391_a_(NoFeatureConfig.field_236559_b_));

    //Lakes
    public static final ConfiguredFeature<?, ?> lake_superhot_magma_common = registerFeature("lake_superhot_magma_common", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(SUPERHOT_MAGMA)).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(15))));
    public static final ConfiguredFeature<?, ?> lake_superhot_magma_rare = registerFeature("lake_superhot_magma_rare", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(SUPERHOT_MAGMA)).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(80))));
    public static final ConfiguredFeature<?, ?> lake_mineral_water_common = registerFeature("lake_mineral_water_common", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(MINERAL_WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
    public static final ConfiguredFeature<?, ?> lake_mineral_water_uncommon = registerFeature("lake_mineral_water_uncommon", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(MINERAL_WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(40))));
    public static final ConfiguredFeature<?, ?> lake_mineral_water_rare = registerFeature("lake_mineral_water_rare", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(MINERAL_WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(50))));
    public static final ConfiguredFeature<?, ?> lake_sweet_muck = registerFeature("lake_sweet_muck", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(SWEET_MUCK)).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(10))));
    public static final ConfiguredFeature<?, ?> lake_liquid_aura = registerFeature("lake_liquid_aura", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(LIQUID_AURA)).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(20))));
    public static final ConfiguredFeature<?, ?> lake_liquid_bismuth = registerFeature("lake_liquid_bismuth", ModWorldgen.POOL.withConfiguration(new BlockStateFeatureConfig(LIQUID_BISMUTH)).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(20))));

    //Local Modifications
    public static final ConfiguredFeature<?, ?> gummy_glitter_blob = registerFeature("gummy_glitter_blob", ModWorldgen.GAIA_BLOB.withConfiguration(new BlockStateFeatureConfig(GUMMY_GLITTER)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242732_c(2));
    public static final ConfiguredFeature<?, ?> static_spikes = registerFeature("static_spikes", ModWorldgen.STATIC_SPIKE.withConfiguration(new FeatureHeightConfig(8)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242732_c(2));
    public static final ConfiguredFeature<?, ?> bismuth_spires = registerFeature("bismuth_spires", ModWorldgen.BISMUTH_SPIRE.withConfiguration(new FeatureHeightConfig(7)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242732_c(2));
    public static final ConfiguredFeature<?, ?> bismuth_geysers = registerFeature("bismuth_geysers", ModWorldgen.BISMUTH_GEYSER.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242732_c(2));

    //Underground Ores
    public static final ConfiguredFeature<?, ?> ore_primal_mass = registerFeature("ore_primal_mass", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, PRIMAL_MASS, 33)).func_242733_d(25).func_242728_a().func_242731_b(33));
    public static final ConfiguredFeature<?, ?> ore_thick_glitter = registerFeature("ore_thick_glitter", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, THICK_GLITTER, 33)).func_242733_d(100).func_242728_a().func_242731_b(9));
    public static final ConfiguredFeature<?, ?> ore_searing_rock = registerFeature("ore_searing_rock", Feature.ORE.withConfiguration(new OreFeatureConfig(VOLCANIC, SEARING_ROCK, 33)).func_242733_d(100).func_242728_a().func_242733_d(9));
    public static final ConfiguredFeature<?, ?> ore_static_stone = registerFeature("ore_static_stone", Feature.ORE.withConfiguration(new OreFeatureConfig(STATIC, STATIC_STONE, 33)).func_242733_d(100).func_242728_a().func_242731_b(9));
    public static final ConfiguredFeature<?, ?> ore_pebbles = registerFeature("ore_pebbles", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, PEBBLES, 25)).func_242733_d(128).func_242728_a().func_242731_b(25));
    public static final ConfiguredFeature<?, ?> ore_speckled_rock = registerFeature("ore_speckled_rock", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, SPECKLED_ROCK, 8)).func_242733_d(120).func_242728_a().func_242731_b(10));
    public static final ConfiguredFeature<?, ?> ore_coarse_rock = registerFeature("ore_coarse_rock", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, COARSE_ROCK, 8)).func_242733_d(60).func_242728_a().func_242731_b(10));
    public static final ConfiguredFeature<?, ?> ore_precious_rock = registerFeature("ore_precious_rock", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, PRECIOUS_ROCK, 8)).func_242733_d(30).func_242728_a().func_242731_b(10));
    public static final ConfiguredFeature<?, ?> ore_raw_amethyst = registerFeature("ore_raw_amethyst", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, RAW_AMETHYST, 12)).func_242733_d(120).func_242728_a().func_242731_b(15));
    public static final ConfiguredFeature<?, ?> ore_raw_copal = registerFeature("ore_raw_copal", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, RAW_COPAL, 12)).func_242733_d(120).func_242728_a().func_242731_b(15));
    public static final ConfiguredFeature<?, ?> ore_raw_jade = registerFeature("ore_raw_jade", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, RAW_JADE, 12)).func_242733_d(120).func_242728_a().func_242731_b(15));
    public static final ConfiguredFeature<?, ?> ore_raw_jet = registerFeature("ore_raw_jet", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, RAW_JET, 12)).func_242733_d(120).func_242728_a().func_242731_b(15));
    public static final ConfiguredFeature<?, ?> ore_sugilite = registerFeature("ore_sugilite", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, SUGILITE_ORE, 17)).func_242733_d(100).func_242728_a().func_242731_b(8));
    public static final ConfiguredFeature<?, ?> ore_hematite = registerFeature("ore_hematite", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, HEMATITE_ORE, 17)).func_242733_d(100).func_242728_a().func_242731_b(8));
    public static final ConfiguredFeature<?, ?> ore_pyrite = registerFeature("ore_pyrite", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, PYRITE_ORE, 9)).func_242733_d(80).func_242728_a().func_242731_b(8));
    public static final ConfiguredFeature<?, ?> ore_cinnabar = registerFeature("ore_cinnabar", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, CINNABAR_ORE, 9)).func_242733_d(60).func_242728_a().func_242731_b(7));
    public static final ConfiguredFeature<?, ?> ore_labradorite = registerFeature("ore_labradorite", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, LABRADORITE_ORE, 9)).func_242733_d(40).func_242728_a().func_242731_b(6));
    public static final ConfiguredFeature<?, ?> ore_moonstone = registerFeature("ore_moonstone", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, MOONSTONE_ORE, 9)).func_242733_d(40).func_242728_a().func_242731_b(6));
    public static final ConfiguredFeature<?, ?> ore_red_opal = registerFeature("ore_red_opal", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, RED_OPAL_ORE, 8)).func_242733_d(30).func_242728_a().func_242731_b(4));
    public static final ConfiguredFeature<?, ?> ore_blue_opal = registerFeature("ore_blue_opal", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, BLUE_OPAL_ORE, 8)).func_242733_d(30).func_242728_a().func_242731_b(4));
    public static final ConfiguredFeature<?, ?> ore_green_opal = registerFeature("ore_green_opal", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, GREEN_OPAL_ORE, 8)).func_242733_d(30).func_242728_a().func_242731_b(4));
    public static final ConfiguredFeature<?, ?> ore_white_opal_common = registerFeature("ore_white_opal_common", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, WHITE_OAL_ORE, 8)).func_242733_d(25).func_242728_a().func_242731_b(4));
    public static final ConfiguredFeature<?, ?> ore_white_opal_rare = registerFeature("ore_white_opal_rare", Feature.ORE.withConfiguration(new OreFeatureConfig(GAIA_STONE, WHITE_OAL_ORE, 8)).func_242733_d(20).func_242728_a().func_242731_b(3));
    public static final ConfiguredFeature<?, ?> disk_static_stone = registerFeature("disk_static_stone", ModWorldgen.GAIA_DISK.withConfiguration(new SphereReplaceConfig(STATIC_STONE, FeatureSpread.func_242253_a(4, 2), 3, ImmutableList.of(WASTELAND_STONE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
    public static final ConfiguredFeature<?, ?> disk_bog_patch = registerFeature("disk_bog_patch", ModWorldgen.BOG_PATCH.withConfiguration(new SphereReplaceConfig(ModBlocks.impure_sludge.getDefaultState(), FeatureSpread.func_242253_a(4, 1), 2, Lists.newArrayList(ModBlocks.murky_grass.getDefaultState(), ModBlocks.boggy_soil.getDefaultState()))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

    //Underground Decoration
    public static final ConfiguredFeature<?, ?> underground_glitter_blob = registerFeature("underground_glitter_blob", ModWorldgen.FRAIL_BLOB.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).func_242733_d(64).func_242728_a().func_242731_b(100));
    public static final ConfiguredFeature<?, ?> crystal_fungi_caves = registerFeature("crystal_fungi_caves", Feature.RANDOM_PATCH.withConfiguration(CAVE_FUNGI).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(2));

    //Vegetal Decoration
    public static final ConfiguredFeature<?, ?> pink_agate_tree_common = registerFeature("pink_agate_tree_common", ModWorldgen.PINK_AGATE_TREE.withConfiguration(PINK_AGATE_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(4, 0.3F, 1))));
    public static final ConfiguredFeature<?, ?> pink_agate_tree_rare = registerFeature("pink_agate_tree_rare", ModWorldgen.PINK_AGATE_TREE.withConfiguration(PINK_AGATE_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> blue_agate_tree = registerFeature("blue_agate_tree", ModWorldgen.BLUE_AGATE_TREE.withConfiguration(BLUE_AGATE_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(1, 0.3F, 1))));
    public static final ConfiguredFeature<?, ?> green_agate_tree = registerFeature("green_agate_tree", ModWorldgen.GREEN_AGATE_TREE.withConfiguration(GREEN_AGATE_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(5, 0.3F, 1))));
    public static final ConfiguredFeature<?, ?> green_agate_bush = registerFeature("green_agate_bush", Feature.TREE.withConfiguration(GREEN_AGATE_BUSH_CONFIG)).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 3)));
    public static final ConfiguredFeature<?, ?> purple_agate_tree = registerFeature("purple_agate_tree", ModWorldgen.PURPLE_AGATE_TREE.withConfiguration(PURPLE_AGATE_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 2))));
    public static final ConfiguredFeature<?, ?> various_agate_trees = registerFeature("various_agate_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
            ModWorldgen.PINK_AGATE_TREE.withConfiguration(PINK_AGATE_TREE_CONFIG).withChance(0.1F),
                ModWorldgen.BLUE_AGATE_TREE.withConfiguration(BLUE_AGATE_TREE_CONFIG).withChance(0.1F),
                ModWorldgen.GREEN_AGATE_TREE.withConfiguration(GREEN_AGATE_TREE_CONFIG).withChance(0.1F),
                ModWorldgen.PURPLE_AGATE_TREE.withConfiguration(PURPLE_AGATE_TREE_CONFIG).withChance(0.1F)
        ), Feature.RANDOM_PATCH.withConfiguration(MUTANT_GROWTH))).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> fossilized_tree = registerFeature("fossilized_tree", ModWorldgen.FOSSILIZED_TREE.withConfiguration(FOSSILIZED_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(1, 0.3F, 1))));
    public static final ConfiguredFeature<?, ?> goldstone_tree = registerFeature("goldstone_tree", ModWorldgen.GOLDSTONE_TREE.withConfiguration(CORRUPTED_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> burnt_agate_tree = registerFeature("burnt_agate_tree", ModWorldgen.BURNT_AGATE_TREE.withConfiguration(BURNT_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> fiery_agate_tree = registerFeature("fiery_agate_tree", ModWorldgen.FIERY_AGATE_TREE.withConfiguration(BURNING_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> aura_tree = registerFeature("aura_tree", ModWorldgen.AURA_TREE.withConfiguration(AURA_TREE_CONFIG).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> aura_shoots = registerFeature("aura_shoots", ModWorldgen.AURA_SHOOT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(6));
    public static final ConfiguredFeature<?, ?> crystal_growth_02 = registerFeature("crystal_growth_02", Feature.RANDOM_PATCH.withConfiguration(NORMAL_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(2));
    public static final ConfiguredFeature<?, ?> crystal_growth_03 = registerFeature("crystal_growth_03", Feature.RANDOM_PATCH.withConfiguration(NORMAL_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(3));
    public static final ConfiguredFeature<?, ?> crystal_growth_04 = registerFeature("crystal_growth_04", Feature.RANDOM_PATCH.withConfiguration(NORMAL_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(4));
    public static final ConfiguredFeature<?, ?> crystal_growth_05 = registerFeature("crystal_growth_05", Feature.RANDOM_PATCH.withConfiguration(NORMAL_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(5));
    public static final ConfiguredFeature<?, ?> crystal_growth_seared = registerFeature("crystal_growth_seared", Feature.RANDOM_PATCH.withConfiguration(SEARED_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(1));
    public static final ConfiguredFeature<?, ?> crystal_growth_corrupt = registerFeature("crystal_growth_corrupt", Feature.RANDOM_PATCH.withConfiguration(CORRUPT_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(1));
    public static final ConfiguredFeature<?, ?> crystal_growth_aura = registerFeature("crystal_growth_aura", Feature.RANDOM_PATCH.withConfiguration(AURA_GROWTH).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242729_a(2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_common = registerFeature("crystal_blooms_common", Feature.RANDOM_PATCH.withConfiguration(COMMON_BLOOM).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_rare = registerFeature("crystal_blooms_rare", Feature.RANDOM_PATCH.withConfiguration(RARE_BLOOM).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_mutant = registerFeature("crystal_blooms_mutant", Feature.RANDOM_PATCH.withConfiguration(MUTANT_BLOOM).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2));
    public static final ConfiguredFeature<?, ?> crystal_blooms_corrupt = registerFeature("crystal_blooms_corrupt", Feature.RANDOM_PATCH.withConfiguration(CORRUPT_BLOOM).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> spotted_kersei = registerFeature("spotted_kersei", Feature.RANDOM_PATCH.withConfiguration(KERSEI).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> thorny_wiltha = registerFeature("thorny_wiltha", Feature.RANDOM_PATCH.withConfiguration(WILTHA).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> roofed_agaric = registerFeature("roofed_agaric", Feature.RANDOM_PATCH.withConfiguration(AGARIC).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> bulbous_hobina = registerFeature("bulbous_hobina", Feature.RANDOM_PATCH.withConfiguration(HOBINA).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> stickly_cupsir = registerFeature("stickly_cupsir", Feature.RANDOM_PATCH.withConfiguration(CUPSIR).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> mystical_murgni = registerFeature("mystical_murgni", Feature.RANDOM_PATCH.withConfiguration(MURGNI).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> corrupted_gaia_eye = registerFeature("corrupted_gaia_eye", Feature.RANDOM_PATCH.withConfiguration(CORRUPT_EYE).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> registerSurfaceBuilder(String name, ConfiguredSurfaceBuilder<SC> surface) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(GaiaDimensionMod.MODID, name), surface);
    }

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> registerStructureFeature(String name, StructureFeature<FC, F> structure) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name), structure);
    }

    private static <WC extends ICarverConfig> ConfiguredCarver<WC> registerCarver(String name, ConfiguredCarver<WC> carver) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, new ResourceLocation(GaiaDimensionMod.MODID, name), carver);
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> registerFeature(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(GaiaDimensionMod.MODID, name), feature);
    }
}
