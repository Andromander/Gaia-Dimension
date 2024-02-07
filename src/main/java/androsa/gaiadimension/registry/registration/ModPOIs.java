package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, GaiaDimensionMod.MODID);

    public static final DeferredHolder<PoiType, PoiType> GAIA_PORTAL = POI_TYPES.register("gaia_portal", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.gaia_portal.get().getStateDefinition().getPossibleStates()), 0, 1));

}
