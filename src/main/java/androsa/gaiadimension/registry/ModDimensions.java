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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.BiFunction;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDimensions {

    public static final ChunkGeneratorType<GaiaGenerationSettings, GaiaChunkGenerator> GAIA_GEN = new ChunkGeneratorType<>(GaiaChunkGenerator::new, true, GaiaGenerationSettings::new);
    public static final ModDimension GAIA_DIM = new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return GaiaDimension::new;
        }
    };
    public static final BiomeProviderType<GaiaBiomeProviderSettings, GaiaBiomeProvider> GAIA_PROVIDER = new BiomeProviderType<>(GaiaBiomeProvider::new, GaiaBiomeProviderSettings::new);

    @SubscribeEvent
    public static void registerChunkGenerators(RegistryEvent.Register<ChunkGeneratorType<?, ?>> e) {
        e.getRegistry().register(GAIA_GEN.setRegistryName("gaia"));
    }

    @SubscribeEvent
    public static void registerDimensions(RegistryEvent.Register<ModDimension> e) {
        e.getRegistry().register(GAIA_DIM.setRegistryName("gaia"));
    }

    @SubscribeEvent
    public static void registerBiomeProviders(RegistryEvent.Register<BiomeProviderType<?, ?>> e) {
        e.getRegistry().register(GAIA_PROVIDER.setRegistryName("gaia_dimension"));
    }
}
