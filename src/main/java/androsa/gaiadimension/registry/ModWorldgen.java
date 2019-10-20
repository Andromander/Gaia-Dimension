package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.carver.ChasmsWorldCarver;
import androsa.gaiadimension.world.gen.carver.CoatedCavesWorldCarver;
import androsa.gaiadimension.world.gen.feature.*;
import androsa.gaiadimension.world.surface.GaiaDefaultSurfaceBuilder;
import androsa.gaiadimension.world.surface.VolcanicSurfaceBuilder;
import androsa.gaiadimension.world.surface.WastelandSurfaceBuilder;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorldgen {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = new DeferredRegister<>(ForgeRegistries.SURFACE_BUILDERS, GaiaDimensionMod.MODID);
    public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = new DeferredRegister<>(ForgeRegistries.WORLD_CARVERS, GaiaDimensionMod.MODID);

    //Feature
    public static final RegistryObject<Feature<LakesConfig>> POOL = FEATURES.register(
            "pool", () -> new GaiaLakesFeature(LakesConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> PINK_AGATE_TREE = FEATURES.register(
            "pink_agate_tree", () -> new PinkAgateTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> BLUE_AGATE_TREE = FEATURES.register(
            "blue_agate_tree", () -> new BlueAgateTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> GREEN_AGATE_TREE = FEATURES.register(
            "green_agate_tree", () -> new GreenAgateTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> PURPLE_AGATE_TREE = FEATURES.register(
            "purple_agate_tree", () -> new PurpleAgateTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> FOSSILIZED_TREE = FEATURES.register(
            "fossilized_tree", () -> new FossilizedTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> GOLDSTONE_TREE = FEATURES.register(
            "goldstone_tree", () -> new GoldstoneCorruptTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> BURNT_AGATE_TREE = FEATURES.register(
            "burnt_agate_tree", () -> new BurntAgateTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> FIERY_AGATE_TREE = FEATURES.register(
            "fiery_agate_tree", () -> new FieryAgateTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> AURA_TREE = FEATURES.register(
            "aura_tree", () -> new AuraTreeFeature(NoFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> GREEN_AGATE_BUSH = FEATURES.register(
            "green_agate_bush", () -> new ShrubFeature(NoFeatureConfig::deserialize, ModBlocks.green_agate_log.get().getDefaultState(), ModBlocks.green_agate_leaves.get().getDefaultState()));
    public static final RegistryObject<Feature<NoFeatureConfig>> AURA_SHOOT = FEATURES.register(
            "aura_shoot", () -> new AuraShootsFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<SphereReplaceConfig>> BOG_PATCH = FEATURES.register(
            "bog_patch", () -> new BogPatchFeature(SphereReplaceConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> BISMUTH_SPIRE = FEATURES.register(
            "bismuth_spire", () -> new BismuthSpireFeature(NoFeatureConfig::deserialize, 7));
    public static final RegistryObject<Feature<NoFeatureConfig>> BISMUTH_GEYSER = FEATURES.register(
            "bismuth_geyser", () -> new BismuthGeyserFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> STATIC_SPIKE = FEATURES.register(
            "static_spike", () -> new StaticSpikeFeature(NoFeatureConfig::deserialize, 8));
    public static final RegistryObject<Feature<NoFeatureConfig>> FRAIL_BLOB = FEATURES.register(
            "frail_blob", () -> new FrailGlitterBlobFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<BushConfig>> UNDERGROUND_FUNGI = FEATURES.register(
            "underground_fungi", () -> new UndergroundFungusFeature(BushConfig::deserialize));
    public static final RegistryObject<Feature<SphereReplaceConfig>> GAIA_DISK = FEATURES.register(
            "gaia_disk", () -> new DiskNoWaterFeature(SphereReplaceConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> MUTANT_GROWTH = FEATURES.register(
            "mutant_growth", () -> new MutantGrowthFeature(NoFeatureConfig::deserialize));

    public static final SurfaceBuilder<SurfaceBuilderConfig> s_gaia = new GaiaDefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> s_volcanic = new VolcanicSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> s_static =  new WastelandSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    //SurfaceBuilder
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DEFAULT_GAIA = SURFACE_BUILDERS.register("default_gaia", () -> s_gaia);
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> VOLCANIC = SURFACE_BUILDERS.register("volcanic", () -> s_volcanic);
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> STATIC = SURFACE_BUILDERS.register("static", () -> s_static);

    //WorldCarver
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CRYSTAL_CAVES = WORLD_CARVERS.register(
            "crystal_caves", () -> new CoatedCavesWorldCarver(ProbabilityConfig::deserialize, 256));
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CHASMS = WORLD_CARVERS.register(
            "chasms", () -> new ChasmsWorldCarver(ProbabilityConfig::deserialize, 32));
}
