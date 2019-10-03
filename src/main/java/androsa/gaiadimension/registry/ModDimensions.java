package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.GaiaDimension;
import androsa.gaiadimension.world.GaiaGenerationSettings;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import androsa.gaiadimension.world.layer.GaiaBiomeProviderSettings;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

public class ModDimensions {

    public static final DeferredRegister<BiomeProviderType<?,?>> BIOME_PROVIDER_TYPES = new DeferredRegister<>(ForgeRegistries.BIOME_PROVIDER_TYPES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<ChunkGeneratorType<?,?>> CHUNK_GENERATOR_TYPES = new DeferredRegister<>(ForgeRegistries.CHUNK_GENERATOR_TYPES, GaiaDimensionMod.MODID);
    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, GaiaDimensionMod.MODID);

    public static final RegistryObject<BiomeProviderType<GaiaBiomeProviderSettings, GaiaBiomeProvider>> GAIA_DIMENSION = BIOME_PROVIDER_TYPES.register(
            "gaia_dimension", () -> new BiomeProviderType<>(GaiaBiomeProvider::new, GaiaBiomeProviderSettings::new));
    public static final RegistryObject<ChunkGeneratorType<GaiaGenerationSettings, GaiaChunkGenerator>> GAIA_GEN = CHUNK_GENERATOR_TYPES.register(
            "gaia_gen", () -> new ChunkGeneratorType<>(GaiaChunkGenerator::new, true, GaiaGenerationSettings::new));
    public static final RegistryObject<ModDimension> GAIA = MOD_DIMENSIONS.register("gaia", () -> new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return GaiaDimension::new;
        }
    });
}
