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
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWorldgen {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, GaiaDimensionMod.MODID);

    //Feature
    public static final RegistryObject<Feature<BlockStateConfiguration>> POOL = FEATURES.register("pool", () ->
			new GaiaLakesFeature<>(BlockStateConfiguration.CODEC));
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
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AURA_SHOOT = FEATURES.register("aura_shoot", () ->
			new AuraShootsFeature<>(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<DiskConfiguration>> BOG_PATCH = FEATURES.register("bog_patch", () ->
			new BogPatchFeature<>(DiskConfiguration.CODEC));
    public static final RegistryObject<Feature<FeatureHeightConfig>> BISMUTH_SPIRE = FEATURES.register("bismuth_spire", () ->
			new BismuthSpireFeature<>(FeatureHeightConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BISMUTH_GEYSER = FEATURES.register("bismuth_geyser", () ->
			new BismuthGeyserFeature<>(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<FeatureHeightConfig>> STATIC_SPIKE = FEATURES.register("static_spike", () ->
			new StaticSpikeFeature<>(FeatureHeightConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FRAIL_BLOB = FEATURES.register("frail_blob", () ->
			new FrailGlitterBlobFeature<>(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<DiskConfiguration>> GAIA_DISK = FEATURES.register("gaia_disk", () ->
			new DiskNoWaterFeature(DiskConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> GAIA_BLOB = FEATURES.register("gaia_blob", () ->
			new GaiaBlobFeature<>(BlockStateConfiguration.CODEC));

    //Structures
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINI_TOWER = STRUCTURES.register("mini_tower", () ->
			new MiniTowerStructure(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MALACHITE_WATCHTOWER = STRUCTURES.register("malachite_watchtower", () ->
			new MalachiteWatchtowerStructure(NoneFeatureConfiguration.CODEC));

    //WorldCarver
    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> CRYSTAL_CAVES = WORLD_CARVERS.register("crystal_caves", () ->
			new CoatedCavesWorldCarver<>(CaveCarverConfiguration.CODEC));
    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> CHASMS = WORLD_CARVERS.register("chasms", () ->
			new ChasmsWorldCarver<>(CaveCarverConfiguration.CODEC));

//    @SubscribeEvent(priority = EventPriority.LOWEST)
//    public static void stupidShitEvent(RegistryEvent.Register<StructureFeature<?>> event) {
//        StructureFeature.STRUCTURES_REGISTRY.put(ModWorldgen.MINI_TOWER.getId().toString(), ModWorldgen.MINI_TOWER.get());
//        StructureFeature.STRUCTURES_REGISTRY.put(ModWorldgen.MALACHITE_WATCHTOWER.getId().toString(), ModWorldgen.MALACHITE_WATCHTOWER.get());
//    }

    public static class StructureTypes {
        public static final StructureProcessorType<BlockDegradeProcessor> BLOCK_DEGRADE = () -> BlockDegradeProcessor.CODEC;
        public static final StructureProcessorType<MalachiteDegradeProcessor> MALACHITE_DEGRADE = () -> MalachiteDegradeProcessor.CODEC;

        public static final StructurePieceType.StructureTemplateType MITO = MiniTowerPieces.Piece::new;
        public static final StructurePieceType.StructureTemplateType MAWA = MalachiteWatchtowerPieces.Piece::new;

        public static void init() {
            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(GaiaDimensionMod.MODID, "block_degrade"), BLOCK_DEGRADE);
            Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(GaiaDimensionMod.MODID, "malachite_degrade"), MALACHITE_DEGRADE);
            Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(GaiaDimensionMod.MODID, "mito"), MITO);
            Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(GaiaDimensionMod.MODID, "mawa"), MAWA);
        }
    }
}
