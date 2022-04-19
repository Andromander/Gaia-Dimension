package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.GaiaBiomeSource;
import androsa.gaiadimension.world.GaiaSurfaceRuleData;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModDimensions {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<PoiType> GAIA_PORTAL = POI_TYPES.register("gaia_portal", () -> new PoiType("gaia_portal", PoiType.getBlockStates(ModBlocks.gaia_portal.get()), 0, 1));
    public static final ResourceKey<LevelStem> GAIA_DIMENSION_STEM = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<Level> gaia_world = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<DimensionType> gaia_dimension = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<NoiseGeneratorSettings> gaia_noise = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_noise"));

    public static void initDimension() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeSource.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
        BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, gaia_noise, noiseGen());
    }

    private static NoiseGeneratorSettings noiseGen() {
        NoiseSettings noiseSettings = NoiseSettings.create(
                -64,
                256,
                new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D),
                new NoiseSlider(-10, 3, 0),
                new NoiseSlider(15, 3, 0),
                1,
                2,
                new TerrainShaper(CubicSpline.constant(10.0F), CubicSpline.constant(10.0F), CubicSpline.constant(0.0F))); //TODO
        NoiseRouterWithOnlyNoises noiseRouter = new NoiseRouterWithOnlyNoises(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero());
        return new NoiseGeneratorSettings(
                noiseSettings,
                ModBlocks.gaia_stone.get().defaultBlockState(),
                ModBlocks.mineral_water.get().defaultBlockState(),
                noiseRouter,
                GaiaSurfaceRuleData.gaia(true, false, true),
                63, // TODO: Sea Level. Bump?
                false,
                false,
                false,
                false);
    }
}
