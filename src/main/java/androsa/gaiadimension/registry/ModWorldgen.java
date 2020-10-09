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
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public class ModWorldgen {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, GaiaDimensionMod.MODID);
    public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, GaiaDimensionMod.MODID);

    public static final IStructureProcessorType BLOCK_DEGRADE = registerProcessor("block_degrade", BlockDegradeProcessor.CODEC);
    public static final IStructureProcessorType MALACHITE_DEGRADE = registerProcessor("malachite_degrade", MalachiteDegradeProcessor.CODEC);

    public static final IStructurePieceType MITO = registerPiece("MITO", MiniTowerPieces.Piece::new);
    public static final IStructurePieceType MAWA = registerPiece("MAWA", MalachiteWatchtowerPieces.Piece::new);

    //Feature
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> POOL = FEATURES.register(
            "pool", () -> new GaiaLakesFeature<>(BlockStateFeatureConfig.field_236455_a_));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> PINK_AGATE_TREE = FEATURES.register(
            "pink_agate_tree", () -> new PinkAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> BLUE_AGATE_TREE = FEATURES.register(
            "blue_agate_tree", () -> new BlueAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> GREEN_AGATE_TREE = FEATURES.register(
            "green_agate_tree", () -> new GreenAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> PURPLE_AGATE_TREE = FEATURES.register(
            "purple_agate_tree", () -> new PurpleAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> FOSSILIZED_TREE = FEATURES.register(
            "fossilized_tree", () -> new FossilizedTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> GOLDSTONE_TREE = FEATURES.register(
            "goldstone_tree", () -> new GoldstoneCorruptTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> BURNT_AGATE_TREE = FEATURES.register(
            "burnt_agate_tree", () -> new BurntAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> FIERY_AGATE_TREE = FEATURES.register(
            "fiery_agate_tree", () -> new FieryAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<GaiaTreeFeatureConfig>> AURA_TREE = FEATURES.register(
            "aura_tree", () -> new AuraTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
//    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> GREEN_AGATE_BUSH = FEATURES.register(
//            "green_agate_bush", () -> new ShrubFeature(BaseTreeFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> AURA_SHOOT = FEATURES.register(
            "aura_shoot", () -> new AuraShootsFeature<>(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<SphereReplaceConfig>> BOG_PATCH = FEATURES.register(
            "bog_patch", () -> new BogPatchFeature<>(SphereReplaceConfig.field_236516_a_));
    public static final RegistryObject<Feature<FeatureHeightConfig>> BISMUTH_SPIRE = FEATURES.register(
            "bismuth_spire", () -> new BismuthSpireFeature<>(FeatureHeightConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> BISMUTH_GEYSER = FEATURES.register(
            "bismuth_geyser", () -> new BismuthGeyserFeature<>(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<FeatureHeightConfig>> STATIC_SPIKE = FEATURES.register(
            "static_spike", () -> new StaticSpikeFeature<>(FeatureHeightConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> FRAIL_BLOB = FEATURES.register(
            "frail_blob", () -> new FrailGlitterBlobFeature<>(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<SphereReplaceConfig>> GAIA_DISK = FEATURES.register(
            "gaia_disk", () -> new DiskNoWaterFeature<>(SphereReplaceConfig.field_236516_a_));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> GAIA_BLOB = FEATURES.register(
            "gaia_blob", () -> new GaiaBlobFeature<>(BlockStateFeatureConfig.field_236455_a_));

    //Structures
    public static final RegistryObject<Structure<NoFeatureConfig>> MINI_TOWER = registerStructure("mini_tower", new MiniTowerStructure<>(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Structure<NoFeatureConfig>> MALACHITE_WATCHTOWER = registerStructure("malachite_watchtower", new MalachiteWatchtowerStructure<>(NoFeatureConfig.field_236558_a_));

    //SurfaceBuilder
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DEFAULT_GAIA = SURFACE_BUILDERS.register("default_gaia", () -> new GaiaDefaultSurfaceBuilder<>(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> VOLCANIC = SURFACE_BUILDERS.register("volcanic", () -> new VolcanicSurfaceBuilder<>(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> STATIC = SURFACE_BUILDERS.register("static", () -> new WastelandSurfaceBuilder<>(SurfaceBuilderConfig.field_237203_a_));

    //WorldCarver
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CRYSTAL_CAVES = WORLD_CARVERS.register(
            "crystal_caves", () -> new CoatedCavesWorldCarver<>(ProbabilityConfig.field_236576_b_, 256));
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CHASMS = WORLD_CARVERS.register(
            "chasms", () -> new ChasmsWorldCarver<>(ProbabilityConfig.field_236576_b_, 32));

    private static <P extends StructureProcessor> IStructureProcessorType<P> registerProcessor(String name, Codec<P> processor) {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(GaiaDimensionMod.MODID, name), () -> processor);
    }

    public static IStructurePieceType registerPiece(String name, IStructurePieceType piece) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(GaiaDimensionMod.MODID, name.toLowerCase(Locale.ROOT)), piece);
    }

    private static <T extends Structure<?>> RegistryObject<T> registerStructure(String name, T structure) {
        Structure.field_236365_a_.put(name, structure);
        return STRUCTURE_FEATURES.register(name, () -> structure);
    }
}
