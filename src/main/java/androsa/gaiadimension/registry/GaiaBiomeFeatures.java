package androsa.gaiadimension.registry;

import androsa.gaiadimension.biomes.*;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GaiaBiomeFeatures {

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

    public static void addCarver(Biome biomeIn) {
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModWorldgen.CRYSTAL_CAVES.get(), new ProbabilityConfig(0.15F)));
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModWorldgen.CHASMS.get(), new ProbabilityConfig(0.03F)));
    }

    public static void addMuckLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.sweet_muck.get().getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(10)));
    }

    public static void addBismuthLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.liquid_bismuth.get().getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(20)));
    }

    public static void addAuraLake(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.liquid_aura.get().getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(20)));
    }

    public static void addMagmaLakes(Biome biomeIn) {
        if (biomeIn instanceof VolcaniclandsBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.superhot_magma.get().getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(15)));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.superhot_magma.get().getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(80)));
        }
    }

    public static void addMineralLakes(Biome biomeIn) {
        if (biomeIn instanceof PurpleAgateSwampBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.mineral_water.get().getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(40)));
        } else if (biomeIn instanceof SaltDunesBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.mineral_water.get().getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(50)));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL.get(), new LakesConfig(ModBlocks.mineral_water.get().getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));
        }
    }

    public static void addGlitterBlobUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(ModWorldgen.FRAIL_BLOB.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.LIGHT_GEM_CHANCE, new FrequencyConfig(200)));
    }

    public static void addPocketsUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.pebbles.get().getDefaultState(), 25), Placement.COUNT_RANGE, new CountRangeConfig(25, 0, 0, 128)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.speckled_rock.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 120)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.coarse_rock.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 60)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.precious_rock.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 30)));
    }

    public static void addPrimalMassUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.primal_mass.get().getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(33, 0, 0, 25)));
    }

    public static void addThickGlitterUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.thick_glitter_block.get().getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(9, 0, 0, 100)));
    }

    public static void addStaticStoneUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(STATIC, ModBlocks.static_stone.get().getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(9, 0, 0, 100)));
    }

    public static void addSearingRockUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(VOLCANIC, ModBlocks.searing_rock.get().getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(9, 0, 0, 100)));
    }

    public static void addBasicOres(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.sugilite_ore.get().getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 100)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.hematite_ore.get().getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 100)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.pyrite_ore.get().getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 80)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.cinnabar_ore.get().getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(7, 0, 0, 60)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.labradorite_ore.get().getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(6, 0, 0, 40)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.moonstone_ore.get().getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(6, 0, 0, 40)));
    }

    public static void addRedOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_red.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 30)));
    }

    public static void addBlueOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_blue.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 30)));
    }

    public static void addGreenOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_green.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 30)));
    }

    public static void addWhiteOpals(Biome biomeIn) {
        if (biomeIn instanceof MutantAgateWildwoodBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_white.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 25)));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(GAIA_STONE, ModBlocks.opal_ore_white.get().getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 20)));
        }
    }

    public static void addPinkAgateTreesSparse(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PINK_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
    }

    public static void addPinkAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PINK_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(4, 0.3F, 1)));
    }

    public static void addBlueAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.BLUE_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.3F, 1)));
    }

    public static void addGreenAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GREEN_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(5, 0.3F, 1)));
    }

    public static void addGreenAgateBushes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GREEN_AGATE_BUSH.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(2, 0.1F, 3)));
    }

    public static void addPurpleAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PURPLE_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.1F, 2)));
    }

    public static void addGummyBlobs(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.GAIA_BLOB.get(), new BlockBlobConfig(ModBlocks.gummy_glitter_block.get().getDefaultState(), 0), Placement.COUNT_HEIGHTMAP, new FrequencyConfig(2)));
    }

    public static void addFossilTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.FOSSILIZED_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.3F, 1)));
    }

    public static void addGoldstoneTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GOLDSTONE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.1F, 1)));
    }

    public static void addBurntTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.BURNT_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
    }

    public static void addBurningTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.FIERY_AGATE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
    }

    public static void addAuraTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.AURA_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
    }

    public static void addAuraShoots(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.AURA_SHOOT.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(6)));
    }

    public static void addVariousTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                new Feature[]       {ModWorldgen.PINK_AGATE_TREE.get(), ModWorldgen.BLUE_AGATE_TREE.get(), ModWorldgen.GREEN_AGATE_TREE.get(), ModWorldgen.PURPLE_AGATE_TREE.get()},
                new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG,  IFeatureConfig.NO_FEATURE_CONFIG,  IFeatureConfig.NO_FEATURE_CONFIG,  IFeatureConfig.NO_FEATURE_CONFIG},
                new float[]         {0.1F,                              0.1F,                              0.1F,                              0.1F},
                ModWorldgen.MUTANT_GROWTH.get(), IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
    }

    public static void addCrystalGrowthNormal(Biome biomeIn, int frequency) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth.get().getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(frequency)));
    }

    public static void addCrystalGrowthCorrupt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_red.get().getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_black.get().getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
    }

    public static void addCrystalGrowthBurnt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_seared.get().getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
    }

    public static void addCrystalGrowthAura(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_aura.get().getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
    }

    public static void addBloomsNormal(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.DEFAULT_BLOOM.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
    }

    public static void addBloomsRare(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.RARE_BLOOM.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
    }

    public static void addBloomsMutant(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.MUTANT_BLOOM.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
    }

    public static void addBloomsCorrupt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.CORRUPT_BLOOM.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addPinkMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.KERSEI.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addBlueMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.WILTHA.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addGreenMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.AGARIC.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addPurpleMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.HOBINA.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addFossilMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.CUPSIR.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addMysteryMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.MURGNI.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addCorruptMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.CORRUPT_GAIA_EYE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
    }

    public static void addUndergroundMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.UNDERGROUND_FUNGI.get(), new BushConfig(ModBlocks.elder_imklia.get().getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.UNDERGROUND_FUNGI.get(), new BushConfig(ModBlocks.gold_orb_tucher.get().getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
    }

    public static void addBogPatches(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.BOG_PATCH.get(), new SphereReplaceConfig(ModBlocks.impure_sludge.get().getDefaultState(), 4, 2, Lists.newArrayList(ModBlocks.murky_grass.get().getDefaultState(), ModBlocks.boggy_soil.get().getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));
    }

    public static void addBismuthSpires(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModWorldgen.BISMUTH_SPIRE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)));
    }

    public static void addBismuthGeysers(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModWorldgen.BISMUTH_GEYSER.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)));
    }

    public static void addStaticPatches(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_DISK.get(), new SphereReplaceConfig(ModBlocks.static_stone.get().getDefaultState(), 6, 2, Lists.newArrayList(ModBlocks.wasteland_stone.get().getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(2)));
    }

    public static void addStaticSpikes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModWorldgen.STATIC_SPIKE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)));
    }
}
