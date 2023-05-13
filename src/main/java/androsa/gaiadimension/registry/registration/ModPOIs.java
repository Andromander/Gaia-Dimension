package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<PoiType> GAIA_PORTAL = POI_TYPES.register("gaia_portal", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.gaia_portal.get().getStateDefinition().getPossibleStates()), 0, 1));

}
