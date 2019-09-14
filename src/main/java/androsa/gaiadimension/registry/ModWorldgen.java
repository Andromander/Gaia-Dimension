package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.carver.ChasmsWorldCarver;
import androsa.gaiadimension.world.gen.carver.CoatedCavesWorldCarver;
import androsa.gaiadimension.world.gen.config.GaiaOreFeatureConfig;
import androsa.gaiadimension.world.gen.feature.*;
import androsa.gaiadimension.world.surface.GaiaDefaultSurfaceBuilder;
import androsa.gaiadimension.world.surface.VolcanicSurfaceBuilder;
import androsa.gaiadimension.world.surface.WastelandSurfaceBuilder;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWorldgen {
    //Feaure
    public static final Feature<LakesConfig> POOL = new GaiaLakesFeature(LakesConfig::deserialize);
    public static final Feature<GaiaOreFeatureConfig> GAIA_ORE = new GaiaOreFeature(GaiaOreFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> PINK_AGATE_TREE = new PinkAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> BLUE_AGATE_TREE = new BlueAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> GREEN_AGATE_TREE = new GreenAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> PURPLE_AGATE_TREE = new PurpleAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> FOSSILIZED_TREE = new FossilizedTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> GOLDSTONE_TREE = new GoldstoneCorruptTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> BURNT_AGATE_TREE = new BurntAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> FIERY_AGATE_TREE = new FieryAgateTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> AURA_TREE = new AuraTreeFeature(NoFeatureConfig::deserialize, false);
    public static final Feature<NoFeatureConfig> GREEN_AGATE_BUSH = new ShrubFeature(NoFeatureConfig::deserialize, ModBlocks.green_agate_log.getDefaultState(), ModBlocks.green_agate_leaves.getDefaultState());
    public static final Feature<NoFeatureConfig> AURA_SHOOT = new AuraShootsFeature(NoFeatureConfig::deserialize);
    public static final Feature<SphereReplaceConfig> BOG_PATCH = new BogPatchFeature(SphereReplaceConfig::deserialize);
    public static final Feature<NoFeatureConfig> BISMUTH_SPIRE = new BismuthSpireFeature(NoFeatureConfig::deserialize, 7);
    public static final Feature<NoFeatureConfig> BISMUTH_GEYSER = new BismuthGeyserFeature(NoFeatureConfig::deserialize);
    public static final Feature<NoFeatureConfig> STATIC_SPIKE = new StaticSpikeFeature(NoFeatureConfig::deserialize, 8);
    public static final Feature<NoFeatureConfig> FRAIL_BLOB = new FrailGlitterBlobFeature(NoFeatureConfig::deserialize);
    public static final Feature<BushConfig> UNDERGROUND_FUNGI = new UndergroundFungusFeature(BushConfig::deserialize);

    //SurfaceBuilder
    public static final SurfaceBuilder<SurfaceBuilderConfig> DEFAULT_GAIA = new GaiaDefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANIC = new VolcanicSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> STATIC = new WastelandSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    //WorldCarver
    public static final WorldCarver<ProbabilityConfig> CRYSTAL_CAVES = new CoatedCavesWorldCarver(ProbabilityConfig::deserialize, 256);
    public static final WorldCarver<ProbabilityConfig> CHASMS = new ChasmsWorldCarver(ProbabilityConfig::deserialize, 32);

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> e) {
        final IForgeRegistry<Feature<?>> registry = e.getRegistry();

        registry.register(POOL.setRegistryName("pool"));
        registry.register(GAIA_ORE.setRegistryName("gaia_ore"));
        registry.register(PINK_AGATE_TREE.setRegistryName("pink_agate_tree"));
        registry.register(BLUE_AGATE_TREE.setRegistryName("blue_agate_tree"));
        registry.register(GREEN_AGATE_TREE.setRegistryName("green_agate_tree"));
        registry.register(PURPLE_AGATE_TREE.setRegistryName("purple_agate_tree"));
        registry.register(FOSSILIZED_TREE.setRegistryName("fossilized_tree"));
        registry.register(GOLDSTONE_TREE.setRegistryName("goldstone_tree"));
        registry.register(BURNT_AGATE_TREE.setRegistryName("burnt_agate_tree"));
        registry.register(FIERY_AGATE_TREE.setRegistryName("fiery_agate_tree"));
        registry.register(AURA_TREE.setRegistryName("aura_tree"));
        registry.register(GREEN_AGATE_BUSH.setRegistryName("green_agate_bush"));
        registry.register(AURA_SHOOT.setRegistryName("aura_shoot"));
        registry.register(BOG_PATCH.setRegistryName("bog_patch"));
        registry.register(BISMUTH_SPIRE.setRegistryName("bismuth_spire"));
        registry.register(BISMUTH_GEYSER.setRegistryName("bismuth_geyser"));
        registry.register(STATIC_SPIKE.setRegistryName("static_spike"));
        registry.register(FRAIL_BLOB.setRegistryName("frail_blob"));
    }

    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> e) {
        e.getRegistry().register(DEFAULT_GAIA.setRegistryName("default_gaia"));
        e.getRegistry().register(VOLCANIC.setRegistryName("volcanic"));
        e.getRegistry().register(STATIC.setRegistryName("static"));
    }

    @SubscribeEvent
    public static void registerWorldCarvers(RegistryEvent.Register<WorldCarver<?>> e) {
        e.getRegistry().register(CRYSTAL_CAVES.setRegistryName("crystal_caves"));
        e.getRegistry().register(CHASMS.setRegistryName("chasms"));
    }
}
