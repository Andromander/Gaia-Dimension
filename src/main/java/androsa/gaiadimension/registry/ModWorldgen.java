package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.carver.ChasmsWorldCarver;
import androsa.gaiadimension.world.gen.carver.CoatedCavesWorldCarver;
import androsa.gaiadimension.world.gen.feature.*;
import androsa.gaiadimension.world.gen.feature.config.FeatureHeightConfig;
import androsa.gaiadimension.world.gen.feature.config.TwoBlockStateConfig;
import androsa.gaiadimension.world.gen.feature.decorator.GoldenVineDecorator;
import androsa.gaiadimension.world.gen.feature.foliage.BulbFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.foliage.CappedFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.foliage.CubeFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.foliage.ThickFoliagePlacer;
import androsa.gaiadimension.world.gen.feature.trunk.CardinalTrunkPlacer;
import androsa.gaiadimension.world.gen.feature.trunk.FourBranchTrunkPlacer;
import androsa.gaiadimension.world.gen.feature.trunk.ThickTrunkPlacer;
import androsa.gaiadimension.world.gen.feature.trunk.VaryingFourBranchTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWorldgen {

    public static final DeferredRegister<TreeDecoratorType<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, GaiaDimensionMod.MODID);
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, GaiaDimensionMod.MODID);

    //Trunk
    public static final RegistryObject<TrunkPlacerType<?>> THICK_TRUNK_PLACER = TRUNK_PLACERS.register("thick_trunk_placer", () -> new TrunkPlacerType<>(ThickTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<?>> FOUR_BRANCH_TRUNK_PLACER = TRUNK_PLACERS.register("four_branch_trunk_placer", () -> new TrunkPlacerType<>(FourBranchTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<?>> CARDINAL_TRUNK_PLACER = TRUNK_PLACERS.register("cardinal_trunk_placer", () -> new TrunkPlacerType<>(CardinalTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<?>> VARYING_FOUR_BRANCH_TRUNK_PLACER = TRUNK_PLACERS.register("varying_four_branch_trunk_placer", () -> new TrunkPlacerType<>(VaryingFourBranchTrunkPlacer.CODEC));

    //Foliage
    public static final RegistryObject<FoliagePlacerType<?>> CAPPED_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("capped_foliage_placer", () -> new FoliagePlacerType<>(CappedFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> THICK_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("thick_foliage_placer", () -> new FoliagePlacerType<>(ThickFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> BULB_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("bulb_foliage_placer", () -> new FoliagePlacerType<>(BulbFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> CUBE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("cube_foliage_placer", () -> new FoliagePlacerType<>(CubeFoliagePlacer.CODEC));

    //Decorator
    public static final RegistryObject<TreeDecoratorType<?>> GOLDEN_VINE_DECORATOR = DECORATORS.register("golden_vine", () -> new TreeDecoratorType<>(GoldenVineDecorator.CODEC));

    //Feature
    public static final RegistryObject<Feature<BlockStateConfiguration>> POOL = FEATURES.register("pool", () ->
			new GaiaLakesFeature<>(BlockStateConfiguration.CODEC));
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
    public static final RegistryObject<Feature<BlockStateConfiguration>> GAIA_BLOB = FEATURES.register("gaia_blob", () ->
			new GaiaBlobFeature<>(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> TERRAIN_SPIKE = FEATURES.register("terrain_spike", () ->
            new TerrainSpikeFeature<>(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<TwoBlockStateConfig>> BALANCING_ROCKS = FEATURES.register("balancing_rocks", () ->
            new BalancingRockFeature<>(TwoBlockStateConfig.CODEC));
    public static final RegistryObject<Feature<TreeConfiguration>> STRICT_TREE = FEATURES.register("strict_tree", () ->
            new StrictTreeFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> MONOLITH = FEATURES.register("monolith", () ->
            new MonolithFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> MENHIR = FEATURES.register("menhir", () ->
            new MenhirFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> MARSH_LAKE = FEATURES.register("marsh_lake", () ->
            new MarshLakeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GOLDEN_VINES = FEATURES.register("golden_vines", () ->
            new GoldenVinesFeature(NoneFeatureConfiguration.CODEC));

    //WorldCarver
    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> CRYSTAL_CAVES = WORLD_CARVERS.register("crystal_caves", () ->
			new CoatedCavesWorldCarver<>(CaveCarverConfiguration.CODEC));
    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> CHASMS = WORLD_CARVERS.register("chasms", () ->
			new ChasmsWorldCarver<>(CaveCarverConfiguration.CODEC));
}
