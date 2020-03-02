package androsa.gaiadimension.registry;

import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Set;

public class GaiaBiomeFeatures {

    public static final Set<BlockState> cave_blacklist = ImmutableSet.of(ModBlocks.glitter_grass.get().getDefaultState(), ModBlocks.heavy_soil.get().getDefaultState(), ModBlocks.corrupt_grass.get().getDefaultState(), ModBlocks.corrupt_soil.get().getDefaultState(), ModBlocks.murky_grass.get().getDefaultState(), ModBlocks.boggy_soil.get().getDefaultState(), ModBlocks.soft_grass.get().getDefaultState(), ModBlocks.light_soil.get().getDefaultState(), ModBlocks.salt.get().getDefaultState());

    public static final OreFeatureConfig.FillerBlockType GAIA_STONE = OreFeatureConfig.FillerBlockType.create("GAIA_STONE", "gaia_stone", new BlockMatcher(ModBlocks.gaia_stone.get()));
    public static final OreFeatureConfig.FillerBlockType STATIC = OreFeatureConfig.FillerBlockType.create("STATIC", "wasteland", (stone) -> {
        if (stone == null) {
            return false;
        } else {
            Block block = stone.getBlock();
            return block == ModBlocks.gaia_stone.get() || block == ModBlocks.wasteland_stone.get();
        }
    });
    public static final OreFeatureConfig.FillerBlockType VOLCANIC = OreFeatureConfig.FillerBlockType.create("VOLCANIC", "volcanic", (stone) -> {
        if (stone == null) {
            return false;
        } else {
            Block block = stone.getBlock();
            return block == ModBlocks.gaia_stone.get() || block == ModBlocks.volcanic_rock.get();
        }
    });

    public static final SurfaceBuilderConfig GENERIC_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.glitter_grass.get().getDefaultState(), ModBlocks.heavy_soil.get().getDefaultState(), ModBlocks.salt.get().getDefaultState());
    public static final SurfaceBuilderConfig VOLCANIC_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.glitter_grass.get().getDefaultState(), ModBlocks.heavy_soil.get().getDefaultState(), ModBlocks.volcanic_rock.get().getDefaultState());
    public static final SurfaceBuilderConfig WASTELAND_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.wasteland_stone.get().getDefaultState(), ModBlocks.wasteland_stone.get().getDefaultState(), ModBlocks.wasteland_stone.get().getDefaultState());
    public static final SurfaceBuilderConfig CORRUPT_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.corrupt_grass.get().getDefaultState(), ModBlocks.corrupt_soil.get().getDefaultState(), ModBlocks.salt.get().getDefaultState());
    public static final SurfaceBuilderConfig SALT_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.salt.get().getDefaultState(), ModBlocks.salt.get().getDefaultState(), ModBlocks.salt.get().getDefaultState());
    public static final SurfaceBuilderConfig AURA_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.soft_grass.get().getDefaultState(), ModBlocks.light_soil.get().getDefaultState(), ModBlocks.salt.get().getDefaultState());
    public static final SurfaceBuilderConfig BISMUTH_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.murky_grass.get().getDefaultState(), ModBlocks.boggy_soil.get().getDefaultState(), ModBlocks.pebbles.get().getDefaultState());

    public static final GaiaTreeFeatureConfig PINK_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.pink_agate_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.pink_agate_leaves.get().getDefaultState())).setSapling(ModBlocks.pink_agate_sapling.get())).build();
    public static final GaiaTreeFeatureConfig BLUE_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.blue_agate_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.blue_agate_leaves.get().getDefaultState())).setSapling(ModBlocks.blue_agate_sapling.get())).build();
    public static final GaiaTreeFeatureConfig GREEN_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.green_agate_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.green_agate_leaves.get().getDefaultState())).setSapling(ModBlocks.green_agate_sapling.get())).build();
    public static final GaiaTreeFeatureConfig PURPLE_AGATE_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.purple_agate_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.purple_agate_leaves.get().getDefaultState())).setSapling(ModBlocks.purple_agate_sapling.get())).build();
    public static final GaiaTreeFeatureConfig FOSSILIZED_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.fossilized_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.fossilized_leaves.get().getDefaultState())).setSapling(ModBlocks.fossilized_sapling.get())).build();
    public static final GaiaTreeFeatureConfig CORRUPTED_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.corrupted_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.corrupted_leaves.get().getDefaultState())).setSapling(ModBlocks.corrupted_sapling.get())).build();
    public static final GaiaTreeFeatureConfig BURNT_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.burnt_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.burnt_leaves.get().getDefaultState())).setSapling(ModBlocks.burnt_sapling.get())).build();
    public static final GaiaTreeFeatureConfig BURNING_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.burning_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.burning_leaves.get().getDefaultState())).setSapling(ModBlocks.burning_sapling.get())).build();
    public static final GaiaTreeFeatureConfig AURA_TREE_CONFIG = (new GaiaTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.aura_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.aura_leaves.get().getDefaultState())).setSapling(ModBlocks.aura_sapling.get())).build();
    public static final BaseTreeFeatureConfig GREEN_AGATE_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.green_agate_log.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.green_agate_leaves.get().getDefaultState()))).build();

    public static final BlockClusterFeatureConfig NORMAL_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.crystal_growth.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig CORRUPT_GROWTH = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addState(ModBlocks.crystal_growth_red.get().getDefaultState(), 2).addState(ModBlocks.crystal_growth_black.get().getDefaultState(), 2), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig MUTANT_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.crystal_growth_mutant.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig SEARED_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.crystal_growth_seared.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig AURA_GROWTH = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.crystal_growth_aura.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();

    public static final BlockClusterFeatureConfig COMMON_BLOOM = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addState(ModBlocks.thiscus.get().getDefaultState(), 4).addState(ModBlocks.ouzium.get().getDefaultState(), 1), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig RARE_BLOOM = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addState(ModBlocks.ouzium.get().getDefaultState(), 4).addState(ModBlocks.thiscus.get().getDefaultState(), 1), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig MUTANT_BLOOM = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addState(ModBlocks.ouzium.get().getDefaultState(), 4).addState(ModBlocks.agathum.get().getDefaultState(), 1), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig CORRUPT_BLOOM = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.corrupted_varloom.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).build();

    public static final BlockClusterFeatureConfig KERSEI = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.spotted_kersei.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig WILTHA = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.thorny_wiltha.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig AGARIC = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.roofed_agaric.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig HOBINA = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.bulbous_hobina.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig CUPSIR = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.stickly_cupsir.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig MURGNI = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.mystical_murgni.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig CORRUPT_EYE = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.corrupted_gaia_eye.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
    public static final BlockClusterFeatureConfig CAVE_FUNGI = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addState(ModBlocks.elder_imklia.get().getDefaultState(), 2).addState(ModBlocks.gold_orb_tucher.get().getDefaultState(), 2), new SimpleBlockPlacer())).tries(64).blacklist(cave_blacklist).cannotProject().build();

    public static void addCarver(Biome biomeIn) {
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModWorldgen.CRYSTAL_CAVES.get(), new ProbabilityConfig(0.15F)));
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModWorldgen.CHASMS.get(), new ProbabilityConfig(0.03F)));
    }

    public static void addMuckLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.sweet_muck.get().getDefaultState())).createDecoratedFeature(Placement.LAVA_LAKE.configure(new ChanceConfig(10))));
    }

    public static void addBismuthLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.liquid_bismuth.get().getDefaultState())).createDecoratedFeature(Placement.LAVA_LAKE.configure(new ChanceConfig(20))));
    }

    public static void addAuraLake(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.liquid_aura.get().getDefaultState())).createDecoratedFeature(Placement.LAVA_LAKE.configure(new ChanceConfig(20))));
    }

    public static void addMagmaLakes(Biome biomeIn) {
        if (biomeIn instanceof VolcaniclandsBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.superhot_magma.get().getDefaultState())).createDecoratedFeature(Placement.LAVA_LAKE.configure(new ChanceConfig(15))));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.superhot_magma.get().getDefaultState())).createDecoratedFeature(Placement.LAVA_LAKE.configure(new ChanceConfig(80))));
        }
    }

    public static void addMineralLakes(Biome biomeIn) {
        if (biomeIn instanceof PurpleAgateSwampBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.mineral_water.get().getDefaultState())).createDecoratedFeature(Placement.WATER_LAKE.configure(new ChanceConfig(40))));
        } else if (biomeIn instanceof SaltDunesBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.mineral_water.get().getDefaultState())).createDecoratedFeature(Placement.WATER_LAKE.configure(new ChanceConfig(50))));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.POOL.get().configure(new BlockStateFeatureConfig(ModBlocks.mineral_water.get().getDefaultState())).createDecoratedFeature(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
        }
    }

    public static void addGlitterBlobUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModWorldgen.FRAIL_BLOB.get().configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.LIGHT_GEM_CHANCE.configure(new FrequencyConfig(200))));
    }

    public static void addPocketsUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.pebbles.get().getDefaultState(), 25)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(25, 0, 0, 128))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.speckled_rock.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 120))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.coarse_rock.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 60))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.precious_rock.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 30))));
    }

    public static void addPrimalMassUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.primal_mass.get().getDefaultState(), 33)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(33, 0, 0, 25))));
    }

    public static void addThickGlitterUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.thick_glitter_block.get().getDefaultState(), 33)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(9, 0, 0, 100))));
    }

    public static void addStaticStoneUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(STATIC, ModBlocks.static_stone.get().getDefaultState(), 33)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(9, 0, 0, 100))));
    }

    public static void addSearingRockUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(VOLCANIC, ModBlocks.searing_rock.get().getDefaultState(), 33)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(9, 0, 0, 100))));
    }

    public static void addBasicOres(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.sugilite_ore.get().getDefaultState(), 17)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 100))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.hematite_ore.get().getDefaultState(), 17)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 100))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.pyrite_ore.get().getDefaultState(), 9)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 80))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.cinnabar_ore.get().getDefaultState(), 9)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(7, 0, 0, 60))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.labradorite_ore.get().getDefaultState(), 9)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 40))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.moonstone_ore.get().getDefaultState(), 9)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 0, 0, 40))));
    }

    public static void addRedOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_red.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 30))));
    }

    public static void addBlueOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_blue.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 30))));
    }

    public static void addGreenOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_green.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 30))));
    }

    public static void addWhiteOpals(Biome biomeIn) {
        if (biomeIn instanceof MutantAgateWildwoodBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_white.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 25))));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_white.get().getDefaultState(), 8)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 20))));
        }
    }

    public static void addPinkAgateTreesSparse(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.PINK_AGATE_TREE.get().configure(PINK_AGATE_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    }

    public static void addPinkAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.PINK_AGATE_TREE.get().configure(PINK_AGATE_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(4, 0.3F, 1))));
    }

    public static void addBlueAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.BLUE_AGATE_TREE.get().configure(BLUE_AGATE_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.3F, 1))));
    }

    public static void addGreenAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.GREEN_AGATE_TREE.get().configure(GREEN_AGATE_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(5, 0.3F, 1))));
    }

    public static void addGreenAgateBushes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.GREEN_AGATE_BUSH.get().configure(GREEN_AGATE_BUSH_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 3))));
    }

    public static void addPurpleAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.PURPLE_AGATE_TREE.get().configure(PURPLE_AGATE_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 2))));
    }

    public static void addGummyBlobs(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModWorldgen.GAIA_BLOB.get().configure(new BlockBlobConfig(ModBlocks.gummy_glitter_block.get().getDefaultState(), 0)).createDecoratedFeature(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(2))));
    }

    public static void addFossilTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.FOSSILIZED_TREE.get().configure(FOSSILIZED_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.3F, 1))));
    }

    public static void addGoldstoneTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.GOLDSTONE_TREE.get().configure(CORRUPTED_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
    }

    public static void addBurntTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.BURNT_AGATE_TREE.get().configure(BURNT_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    }

    public static void addBurningTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.FIERY_AGATE_TREE.get().configure(BURNING_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    }

    public static void addAuraTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.AURA_TREE.get().configure(AURA_TREE_CONFIG).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    }

    public static void addAuraShoots(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldgen.AURA_SHOOT.get().configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(6))));
    }

    public static void addVariousTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new MultipleRandomFeatureConfig(ImmutableList.of(
                ModWorldgen.PINK_AGATE_TREE.get().configure(PINK_AGATE_TREE_CONFIG).withChance(0.1F),
                ModWorldgen.BLUE_AGATE_TREE.get().configure(BLUE_AGATE_TREE_CONFIG).withChance(0.1F),
                ModWorldgen.GREEN_AGATE_TREE.get().configure(GREEN_AGATE_TREE_CONFIG).withChance(0.1F),
                ModWorldgen.PURPLE_AGATE_TREE.get().configure(PURPLE_AGATE_TREE_CONFIG).withChance(0.1F)
        ), Feature.field_227248_z_.configure(MUTANT_GROWTH))).createDecoratedFeature(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    }

    public static void addCrystalGrowthNormal(Biome biomeIn, int frequency) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(NORMAL_GROWTH).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
    }

    public static void addCrystalGrowthCorrupt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(CORRUPT_GROWTH).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
    }

    public static void addCrystalGrowthBurnt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(SEARED_GROWTH).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
    }

    public static void addCrystalGrowthAura(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(AURA_GROWTH).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));
    }

    public static void addBloomsNormal(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(COMMON_BLOOM).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
    }

    public static void addBloomsRare(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(RARE_BLOOM).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
    }

    public static void addBloomsMutant(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(MUTANT_BLOOM).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
    }

    public static void addBloomsCorrupt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(CORRUPT_BLOOM).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addPinkMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(KERSEI).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addBlueMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(WILTHA).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addGreenMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(AGARIC).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addPurpleMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(HOBINA).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addFossilMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(CUPSIR).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addMysteryMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(MURGNI).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addCorruptMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(CORRUPT_EYE).createDecoratedFeature(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
    }

    public static void addUndergroundMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(CAVE_FUNGI).createDecoratedFeature(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(2))));
    }

    public static void addBogPatches(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModWorldgen.BOG_PATCH.get().configure(new SphereReplaceConfig(ModBlocks.impure_sludge.get().getDefaultState(), 4, 2, Lists.newArrayList(ModBlocks.murky_grass.get().getDefaultState(), ModBlocks.boggy_soil.get().getDefaultState()))).createDecoratedFeature(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));
    }

    public static void addBismuthSpires(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModWorldgen.BISMUTH_SPIRE.get().configure(new FeatureHeightConfig(7)).createDecoratedFeature(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
    }

    public static void addBismuthGeysers(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModWorldgen.BISMUTH_GEYSER.get().configure(IFeatureConfig.NO_FEATURE_CONFIG).createDecoratedFeature(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
    }

    public static void addStaticPatches(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModWorldgen.GAIA_DISK.get().configure(new SphereReplaceConfig(ModBlocks.static_stone.get().getDefaultState(), 6, 2, Lists.newArrayList(ModBlocks.wasteland_stone.get().getDefaultState()))).createDecoratedFeature(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(2))));
    }

    public static void addStaticSpikes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModWorldgen.STATIC_SPIKE.get().configure(new FeatureHeightConfig(8)).createDecoratedFeature(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
    }
}
