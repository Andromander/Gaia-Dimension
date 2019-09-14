package androsa.gaiadimension.registry;

import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.world.gen.config.GaiaOreFeatureConfig;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GaiaBiomeFeatures {
    public static final SurfaceBuilderConfig GENERIC_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.glitter_grass.getDefaultState(), ModBlocks.heavy_soil.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig VOLCANIC_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.glitter_grass.getDefaultState(), ModBlocks.heavy_soil.getDefaultState(), ModBlocks.volcanic_rock.getDefaultState());
    public static final SurfaceBuilderConfig WASTELAND_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.wasteland_stone.getDefaultState(), ModBlocks.wasteland_stone.getDefaultState(), ModBlocks.wasteland_stone.getDefaultState());
    public static final SurfaceBuilderConfig CORRUPT_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.corrupt_grass.getDefaultState(), ModBlocks.corrupt_soil.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig SALT_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.salt.getDefaultState(), ModBlocks.salt.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig AURA_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.soft_grass.getDefaultState(), ModBlocks.light_soil.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig BISMUTH_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.murky_grass.getDefaultState(), ModBlocks.boggy_soil.getDefaultState(), ModBlocks.pebbles.getDefaultState());

    public static void addCarver(Biome biomeIn) {
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModWorldgen.CRYSTAL_CAVES, new ProbabilityConfig(0.15F)));
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModWorldgen.CHASMS, new ProbabilityConfig(0.02F)));
    }

    public static void addMuckLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.sweet_muck.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(4)));
    }

    public static void addBismuthLakes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.liquid_bismuth.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(20)));
    }

    public static void addAuraLake(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.liquid_aura.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(20)));
    }

    public static void addMagmaLakes(Biome biomeIn) {
        if (biomeIn instanceof VolcaniclandsBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.superhot_magma.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(20)));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.superhot_magma.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(80)));
        }
    }

    public static void addMineralLakes(Biome biomeIn) {
        if (biomeIn instanceof PurpleAgateSwampBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.mineral_water.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(40)));
        } else if (biomeIn instanceof SaltDunesBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.mineral_water.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(50)));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModWorldgen.POOL, new LakesConfig(ModBlocks.mineral_water.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));
        }
    }

    public static void addGlitterBlobUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(ModWorldgen.FRAIL_BLOB, IFeatureConfig.NO_FEATURE_CONFIG, Placement.LIGHT_GEM_CHANCE, new FrequencyConfig(150)));
    }

    public static void addPocketsUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.pebbles.getDefaultState(), 25), Placement.COUNT_RANGE, new CountRangeConfig(25, 0, 0, 128)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.speckled_rock.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 120)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.coarse_rock.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 60)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.precious_rock.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 30)));
    }

    public static void addPrimalMassUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.primal_mass.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(33, 0, 0, 25)));
    }

    public static void addThickGlitterUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.thick_glitter_block.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(9, 0, 0, 100)));
    }

    public static void addStaticStoneUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STATIC, ModBlocks.static_stone.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(9, 0, 0, 100)));
    }

    public static void addSearingRockUnderground(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.VOLCANIC, ModBlocks.searing_rock.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(9, 0, 0, 100)));
    }

    public static void addBasicOres(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.sugilite_ore.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 100)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.hematite_ore.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 100)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.pyrite_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 80)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.cinnabar_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(7, 0, 0, 60)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.labradorite_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(6, 0, 0, 40)));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.moonstone_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(6, 0, 0, 40)));
    }

    public static void addRedOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.opal_ore_red.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 30)));
    }

    public static void addBlueOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.opal_ore_blue.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 30)));
    }

    public static void addGreenOpals(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.opal_ore_green.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 30)));
    }

    public static void addWhiteOpals(Biome biomeIn) {
        if (biomeIn instanceof MutantAgateWildwoodBiome) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.opal_ore_white.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 0, 0, 25)));
        } else {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.GAIA_ORE, new GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType.STONE, ModBlocks.opal_ore_white.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 20)));
        }
    }

    public static void addPinkAgateTreesSparse(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PINK_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addPinkAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PINK_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(4, 0.3F, 1)));
    }

    public static void addBlueAgateTreesSparse(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.BLUE_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addBlueAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.BLUE_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addGreenAgateTreesSparse(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GREEN_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addGreenAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GREEN_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addGreenAgateBushes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GREEN_AGATE_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addPurpleAgateTreesSparse(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PURPLE_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addPurpleAgateTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.PURPLE_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addGummyBlobs(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.FOREST_ROCK, new BlockBlobConfig(ModBlocks.gummy_glitter_block.getDefaultState(), 0), Placement.FOREST_ROCK, new FrequencyConfig(3))); //FIXME: Tweak this
    }

    public static void addFossilTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.FOSSILIZED_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addGoldstoneTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.GOLDSTONE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addBurntTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.BURNT_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addBurningTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.FIERY_AGATE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addAuraTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.AURA_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1))); //FIXME: Tweak it
    }

    public static void addAuraShoots(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.AURA_SHOOT, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
    }

    public static void addCrystalGrowthNormal(Biome biomeIn, int frequency) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(frequency)));
    }

    public static void addCrystalGrowthCorrupt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_red.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_black.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
    }

    public static void addCrystalGrowthBurnt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_seared.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
    }

    public static void addCrystalGrowthMutant(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_mutant.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
    }

    public static void addCrystalGrowthAura(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.crystal_growth_aura.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
    }

    public static void addBloomsNormal(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.thiscus.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.125F))); //FIXME: Tweak it
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.ouzium.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.0625F))); //FIXME: Tweak it
    }

    public static void addBloomsRare(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.ouzium.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.125F))); //FIXME: Tweak it
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.thiscus.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.0625F))); //FIXME: Tweak it
    }

    public static void addBloomsMutant(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.ouzium.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.125F))); //FIXME: Tweak it
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.agathum.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.0625F))); //FIXME: Tweak it
    }

    public static void addBloomsCorrupt(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.corrupted_varloom.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addPinkMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.spotted_kersei.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.0625F))); //FIXME: Tweak it
    }

    public static void addBlueMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.thorny_wiltha.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addGreenMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.roofed_agaric.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addPurpleMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.bulbous_hobina.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addFossilMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.stickly_cupsir.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addMysteryMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.mystical_murgni.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addCorruptMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.corrupted_gaia_eye.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(8, 0.25F))); //FIXME: Tweak it
    }

    public static void addUndergroundMushrooms(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.UNDERGROUND_FUNGI, new BushConfig(ModBlocks.elder_imklia.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(16))); //FIXME: Tweak it
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModWorldgen.UNDERGROUND_FUNGI, new BushConfig(ModBlocks.gold_orb_tucher.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(16))); //FIXME: Tweak it
    }

    public static void addBogPatches(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(ModWorldgen.BOG_PATCH, new SphereReplaceConfig(ModBlocks.impure_sludge.getDefaultState(), 4, 2, Lists.newArrayList(ModBlocks.murky_grass.getDefaultState(), ModBlocks.boggy_soil.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));
    }

    public static void addBismuthSpires(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModWorldgen.BISMUTH_SPIRE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(2)));
    }

    public static void addBismuthGeysers(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModWorldgen.BISMUTH_GEYSER, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(2)));
    }

    public static void addStaticPatches(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(ModBlocks.static_stone.getDefaultState(), 6, 2, Lists.newArrayList(ModBlocks.wasteland_stone.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(2)));
    }

    public static void addStaticSpikes(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModWorldgen.STATIC_SPIKE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(2)));
    }
}
