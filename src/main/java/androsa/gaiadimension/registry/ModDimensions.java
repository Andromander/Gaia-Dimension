package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<PoiType> GAIA_PORTAL = POI_TYPES.register("gaia_portal", () -> new PoiType("gaia_portal", PoiType.getBlockStates(ModBlocks.gaia_portal.get()), 0, 1));
    public static final ResourceKey<Level> gaia_world = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final ResourceKey<DimensionType> gaia_dimension = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));

    public static void initDimension() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
    }
}
