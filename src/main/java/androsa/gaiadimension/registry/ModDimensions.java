package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ModDimensions {

//    public static final DeferredRegister<BiomeProviderType<?,?>> BIOME_PROVIDER_TYPES = new DeferredRegister<>(ForgeRegistries.BIOME_PROVIDER_TYPES, GaiaDimensionMod.MODID);
//    public static final DeferredRegister<ChunkGeneratorType<?,?>> CHUNK_GENERATOR_TYPES = new DeferredRegister<>(ForgeRegistries.CHUNK_GENERATOR_TYPES, GaiaDimensionMod.MODID);
//    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, GaiaDimensionMod.MODID);

//    public static final RegistryObject<BiomeProviderType<GaiaBiomeProviderSettings, GaiaBiomeProvider>> GAIA_DIMENSION = BIOME_PROVIDER_TYPES.register(
//            "gaia_dimension", () -> new BiomeProviderType<>(GaiaBiomeProvider::new, GaiaBiomeProviderSettings::new));
//    public static final RegistryObject<ChunkGeneratorType<GaiaGenerationSettings, GaiaChunkGenerator>> GAIA_GEN = CHUNK_GENERATOR_TYPES.register(
//            "gaia_gen", () -> new ChunkGeneratorType<>(GaiaChunkGenerator::new, true, GaiaGenerationSettings::new));
    public static final RegistryKey<World> gaia_world = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final RegistryKey<DimensionType> gaia_dimension = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final DimensionRenderInfo gaia = new DimensionRenderInfo(255.0F, true, DimensionRenderInfo.FogType.NORMAL, false, false) {
        @Override
        public Vector3d func_230494_a_(Vector3d vector3d, float v) {
            return vector3d;
        }

        @Override
        public boolean func_230493_a_(int i, int i1) {
            return false;
        }
    };

    public static void initDimension() {
        DimensionRenderInfo.field_239208_a_.put(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), gaia);
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
    }
}
