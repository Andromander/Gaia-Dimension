package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.client.GaiaSkyRender;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDimensions {

    public static final PointOfInterestType GAIA_PORTAL = RegistryHelper.registerPOI("gaia_portal", PointOfInterestType.getAllStates(ModBlocks.gaia_portal), 0, 1);
    public static final RegistryKey<World> gaia_world = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final RegistryKey<DimensionType> gaia_dimension = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));

    public static void initDimension() {
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
    }

    @SubscribeEvent
    public static void registerPOIs(RegistryEvent.Register<PointOfInterestType> event) {
        IForgeRegistry<PointOfInterestType> registry = event.getRegistry();
        for (PointOfInterestType poi : RegistryHelper.POI_TYPES) {
            registry.register(poi);
        }
    }
}
