package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.carver.ChasmsWorldCarver;
import androsa.gaiadimension.world.gen.carver.CoatedCavesWorldCarver;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import androsa.gaiadimension.world.gen.feature.*;
import androsa.gaiadimension.world.gen.structure.MalachiteWatchtowerStructure;
import androsa.gaiadimension.world.gen.structure.MiniTowerStructure;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import androsa.gaiadimension.world.gen.structure.processor.BlockDegradeProcessor;
import androsa.gaiadimension.world.gen.structure.processor.MalachiteDegradeProcessor;
import androsa.gaiadimension.world.surface.GaiaDefaultSurfaceBuilder;
import androsa.gaiadimension.world.surface.VolcanicSurfaceBuilder;
import androsa.gaiadimension.world.surface.WastelandSurfaceBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWorldgen {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, GaiaDimensionMod.MODID);
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, GaiaDimensionMod.MODID);

    //Feature
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> POOL = FEATURES.register("pool", () ->
			new GaiaLakesFeature<>(BlockStateFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> PINK_AGATE_TREE = FEATURES.register("pink_agate_tree", () ->
			new PinkAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> BLUE_AGATE_TREE = FEATURES.register("blue_agate_tree", () ->
			new BlueAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> GREEN_AGATE_TREE = FEATURES.register("green_agate_tree", () ->
			new GreenAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> PURPLE_AGATE_TREE = FEATURES.register("purple_agate_tree", () ->
			new PurpleAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> FOSSILIZED_TREE = FEATURES.register("fossilized_tree", () ->
			new FossilizedTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> GOLDSTONE_TREE = FEATURES.register("goldstone_tree", () ->
			new GoldstoneCorruptTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> BURNT_AGATE_TREE = FEATURES.register("burnt_agate_tree", () ->
			new BurntAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> FIERY_AGATE_TREE = FEATURES.register("fiery_agate_tree", () ->
			new FieryAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> AURA_TREE = FEATURES.register("aura_tree", () ->
			new AuraTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> AURA_SHOOT = FEATURES.register("aura_shoot", () ->
			new AuraShootsFeature<>(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<SphereReplaceConfig>> BOG_PATCH = FEATURES.register("bog_patch", () ->
			new BogPatchFeature<>(SphereReplaceConfig.CODEC));
    public static final RegistryObject<Feature<FeatureHeightConfig>> BISMUTH_SPIRE = FEATURES.register("bismuth_spire", () ->
			new BismuthSpireFeature<>(FeatureHeightConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> BISMUTH_GEYSER = FEATURES.register("bismuth_geyser", () ->
			new BismuthGeyserFeature<>(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<FeatureHeightConfig>> STATIC_SPIKE = FEATURES.register("static_spike", () ->
			new StaticSpikeFeature<>(FeatureHeightConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> FRAIL_BLOB = FEATURES.register("frail_blob", () ->
			new FrailGlitterBlobFeature<>(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<SphereReplaceConfig>> GAIA_DISK = FEATURES.register("gaia_disk", () ->
			new DiskNoWaterFeature<>(SphereReplaceConfig.CODEC));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> GAIA_BLOB = FEATURES.register("gaia_blob", () ->
			new GaiaBlobFeature<>(BlockStateFeatureConfig.CODEC));

    //Structures
    public static final RegistryObject<Structure<NoFeatureConfig>> MINI_TOWER = STRUCTURES.register("mini_tower", () ->
			new MiniTowerStructure<>(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> MALACHITE_WATCHTOWER = STRUCTURES.register("malachite_watchtower", () ->
			new MalachiteWatchtowerStructure<>(NoFeatureConfig.CODEC));

    //SurfaceBuilder
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DEFAULT_GAIA = SURFACE_BUILDERS.register("default_gaia", () ->
			new GaiaDefaultSurfaceBuilder<>(SurfaceBuilderConfig.CODEC));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> VOLCANIC = SURFACE_BUILDERS.register("volcanic", () ->
			new VolcanicSurfaceBuilder<>(SurfaceBuilderConfig.CODEC));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> STATIC = SURFACE_BUILDERS.register("static", () ->
			new WastelandSurfaceBuilder<>(SurfaceBuilderConfig.CODEC));

    //WorldCarver
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CRYSTAL_CAVES = WORLD_CARVERS.register("crystal_caves", () ->
			new CoatedCavesWorldCarver<>(ProbabilityConfig.CODEC, 256));
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CHASMS = WORLD_CARVERS.register("chasms", () ->
			new ChasmsWorldCarver<>(ProbabilityConfig.CODEC, 32));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void stupidShitEvent(RegistryEvent.Register<Structure<?>> event) {
        Structure.STRUCTURES_REGISTRY.put(ModWorldgen.MINI_TOWER.getId().toString(), ModWorldgen.MINI_TOWER.get());
        Structure.STRUCTURES_REGISTRY.put(ModWorldgen.MALACHITE_WATCHTOWER.getId().toString(), ModWorldgen.MALACHITE_WATCHTOWER.get());
    }

    public static class StructureTypes {
        public static final IStructureProcessorType<BlockDegradeProcessor> BLOCK_DEGRADE = () -> BlockDegradeProcessor.CODEC;
        public static final IStructureProcessorType<MalachiteDegradeProcessor> MALACHITE_DEGRADE = () -> MalachiteDegradeProcessor.CODEC;

        public static final IStructurePieceType MITO = MiniTowerPieces.Piece::new;
        public static final IStructurePieceType MAWA = MalachiteWatchtowerPieces.Piece::new;

        public static void init() {
            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(GaiaDimensionMod.MODID, "block_degrade"), BLOCK_DEGRADE);
            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(GaiaDimensionMod.MODID, "malachite_degrade"), MALACHITE_DEGRADE);
            Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(GaiaDimensionMod.MODID, "mito"), MITO);
            Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(GaiaDimensionMod.MODID, "mawa"), MAWA);
        }
    }
}
