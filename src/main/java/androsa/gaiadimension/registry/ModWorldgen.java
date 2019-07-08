package androsa.gaiadimension.registry;

import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModWorldgen {

    //Feaure
    public static final Feature<LakesConfig> POOL = new GaiaLakesFeature(LakesConfig::deserialize);
    public static final Feature<GaiaOreFeatureConfig> ORE = new GaiaOreFeature(GaiaOreFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> PINK_AGATE_TREE = new PinkAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> BLUE_AGATE_TREE = new BlueAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> GREEN_AGATE_TREE = new GreenAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> PURPLE_AGATE_TREE = new PurpleAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> FOSSIL_TREE = new FossilizedTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> GOLDSTONE_TREE = new GoldstoneTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> BURNT_TREE = new BurntTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> BURNING_TREE = new BurningTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> AURA_TREE = new AuraTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> GREEN_AGATE_BUSH = new ShrubFeature(NoFeatureConfig::deserialize, ModBlocks.green_agate_log.getDefaultState(), ModBlocks.green_agate_leaves.getDefaultState());
    public static final Feature<NoFeatureConfig> AURA_SHOOT = new AuraShootFeature(NoFeatureConfig::deserialize);
    public static final Feature<SphereReplaceConfig> BOG_PATCH = new BogPatchFeature(SphereReplaceConfig::deserialize);
    public static final Feature<NoFeatureConfig> BISMUTH_SPIRE = new BismuthSpireFeature(NoFeatureConfig::deserialize, 7);
    public static final Feature<NoFeatureConfig> BISMUTH_GEYSER = new BismuthGeyserFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> STATIC_SPIKE = new StaticSpikeFeature(NoFeatureConfig::deserialize, 8);

    //SurfaceBuilder
    public static final SurfaceBuilder<SurfaceBuilderConfig> DEFAULT_GAIA = new GaiaDefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANIC = new VolcanicSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> WASTELAND = new WastelandSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    //SurfaceBuilderConfig
    public static final SurfaceBuilderConfig GENERIC_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.glitter_grass.getDefaultState(), ModBlocks.heavy_soil.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig VOLCANIC_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.glitter_grass.getDefaultState(), ModBlocks.heavy_soil.getDefaultState(), ModBlocks.volcanic_rock.getDefaultState());
    public static final SurfaceBuilderConfig WASTELAND_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.wasteland_stone.getDefaultState(), ModBlocks.wasteland_stone.getDefaultState(), ModBlocks.wasteland_stone.getDefaultState());
    public static final SurfaceBuilderConfig CORRUPT_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.corrupt_grass.getDefaultState(), ModBlocks.corrupt_soil.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig SALT_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.salt.getDefaultState(), ModBlocks.salt.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig AURA_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.soft_grass.getDefaultState(), ModBlocks.light_soil.getDefaultState(), ModBlocks.salt.getDefaultState());
    public static final SurfaceBuilderConfig BISMUTH_SURFACE_CONFIG = new SurfaceBuilderConfig(ModBlocks.murky_grass.getDefaultState(), ModBlocks.boggy_soil.getDefaultState(), ModBlocks.pebbles.getDefaultState());

    //WorldCarver
    public static final WorldCarver<ProbabilityConfig> COATED_CAVES = new CoatedCavesWorldCarver(ProbabilityConfig::deserialize, true);
    public static final WorldCarver<ProbabilityConfig> UNCOATED_CAVES = new UncoatedCavesWorldCarver(ProbabilityConfig::deserialize, false);
    public static final WorldCarver<ProbabilityConfig> CHASMS = new ChasmsWorldCarver(ProbabilityConfig::deserialize);
}
