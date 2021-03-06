package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {

    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<PointOfInterestType> GAIA_PORTAL = POI_TYPES.register("gaia_portal", () -> new PointOfInterestType("gaia_portal", PointOfInterestType.getBlockStates(ModBlocks.gaia_portal.get()), 0, 1));
    public static final RegistryKey<World> gaia_world = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final RegistryKey<DimensionType> gaia_dimension = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));

    public static void initDimension() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
    }
}
