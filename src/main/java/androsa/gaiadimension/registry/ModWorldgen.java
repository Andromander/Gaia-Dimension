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
import net.minecraftforge.registries.ObjectHolder;

import java.util.Locale;

@ObjectHolder(GaiaDimensionMod.MODID)
public class ModWorldgen {

    public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, GaiaDimensionMod.MODID);

    public static final IStructureProcessorType BLOCK_DEGRADE = registerProcessor("block_degrade", BlockDegradeProcessor.CODEC);
    public static final IStructureProcessorType MALACHITE_DEGRADE = registerProcessor("malachite_degrade", MalachiteDegradeProcessor.CODEC);

    public static final IStructurePieceType MITO = registerPiece("MITO", MiniTowerPieces.Piece::new);
    public static final IStructurePieceType MAWA = registerPiece("MAWA", MalachiteWatchtowerPieces.Piece::new);

    //Feature
    public static final Feature<BlockStateFeatureConfig> POOL = RegistryHelper.registerFeature("pool", new GaiaLakesFeature<>(BlockStateFeatureConfig.field_236455_a_));
    public static final Feature<GaiaTreeFeatureConfig> PINK_AGATE_TREE = RegistryHelper.registerFeature("pink_agate_tree", new PinkAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> BLUE_AGATE_TREE = RegistryHelper.registerFeature("blue_agate_tree", new BlueAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> GREEN_AGATE_TREE = RegistryHelper.registerFeature("green_agate_tree", new GreenAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> PURPLE_AGATE_TREE = RegistryHelper.registerFeature("purple_agate_tree", new PurpleAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> FOSSILIZED_TREE = RegistryHelper.registerFeature("fossilized_tree", new FossilizedTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> GOLDSTONE_TREE = RegistryHelper.registerFeature("goldstone_tree", new GoldstoneCorruptTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> BURNT_AGATE_TREE = RegistryHelper.registerFeature("burnt_agate_tree", new BurntAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> FIERY_AGATE_TREE = RegistryHelper.registerFeature("fiery_agate_tree", new FieryAgateTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
    public static final Feature<GaiaTreeFeatureConfig> AURA_TREE = RegistryHelper.registerFeature("aura_tree", new AuraTreeFeature<>(GaiaTreeFeatureConfig.CODEC));
//  public static final Feature<BaseTreeFeatureConfig> GREEN_AGATE_BUSH = RegistryHelper.registerFeature()("green_agate_bush", new ShrubFeature(BaseTreeFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> AURA_SHOOT = RegistryHelper.registerFeature("aura_shoot", new AuraShootsFeature<>(NoFeatureConfig.field_236558_a_));
    public static final Feature<SphereReplaceConfig> BOG_PATCH = RegistryHelper.registerFeature("bog_patch", new BogPatchFeature<>(SphereReplaceConfig.field_236516_a_));
    public static final Feature<FeatureHeightConfig> BISMUTH_SPIRE = RegistryHelper.registerFeature("bismuth_spire", new BismuthSpireFeature<>(FeatureHeightConfig.CODEC));
    public static final Feature<NoFeatureConfig> BISMUTH_GEYSER = RegistryHelper.registerFeature("bismuth_geyser", new BismuthGeyserFeature<>(NoFeatureConfig.field_236558_a_));
    public static final Feature<FeatureHeightConfig> STATIC_SPIKE = RegistryHelper.registerFeature("static_spike", new StaticSpikeFeature<>(FeatureHeightConfig.CODEC));
    public static final Feature<NoFeatureConfig> FRAIL_BLOB = RegistryHelper.registerFeature("frail_blob", new FrailGlitterBlobFeature<>(NoFeatureConfig.field_236558_a_));
    public static final Feature<SphereReplaceConfig> GAIA_DISK = RegistryHelper.registerFeature("gaia_disk", new DiskNoWaterFeature<>(SphereReplaceConfig.field_236516_a_));
    public static final Feature<BlockStateFeatureConfig> GAIA_BLOB = RegistryHelper.registerFeature("gaia_blob", new GaiaBlobFeature<>(BlockStateFeatureConfig.field_236455_a_));

    //Structures
    public static final Structure<NoFeatureConfig> MINI_TOWER = RegistryHelper.registerStructure("mini_tower", new MiniTowerStructure<>(NoFeatureConfig.field_236558_a_));
    public static final Structure<NoFeatureConfig> MALACHITE_WATCHTOWER = RegistryHelper.registerStructure("malachite_watchtower", new MalachiteWatchtowerStructure<>(NoFeatureConfig.field_236558_a_));

    //SurfaceBuilder
    public static final SurfaceBuilder<SurfaceBuilderConfig> DEFAULT_GAIA = RegistryHelper.registerSurfaceBuilder("default_gaia", new GaiaDefaultSurfaceBuilder<>(SurfaceBuilderConfig.field_237203_a_));
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANIC = RegistryHelper.registerSurfaceBuilder("volcanic", new VolcanicSurfaceBuilder<>(SurfaceBuilderConfig.field_237203_a_));
    public static final SurfaceBuilder<SurfaceBuilderConfig> STATIC = RegistryHelper.registerSurfaceBuilder("static", new WastelandSurfaceBuilder<>(SurfaceBuilderConfig.field_237203_a_));

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
}
