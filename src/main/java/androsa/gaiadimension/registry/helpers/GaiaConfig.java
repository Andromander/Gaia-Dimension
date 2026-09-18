package androsa.gaiadimension.registry.helpers;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec.EnumValue;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

public class GaiaConfig {
    private static final String config = GaiaDimensionMod.MODID + ".config.";

    public static Identifier startDimRL;
    public static ResourceKey<Level> startDimRK;

    public static ConfigValue<? extends String> startDimension;
    public static BooleanValue portalCheck;
    public static EnumValue<ListType> listType;

    public static class CommonConfig {
        public CommonConfig(Builder builder) {
            startDimension = builder
                    .translation(config + "start_dimension")
                    .comment("The Dimension that Gaia will connect to. Results may vary based on the Level chosen. Existing portals will remain regardless of what is set here until they are broken, however they may no longer connect.")
                    .define("startDimension", "minecraft:overworld");
            portalCheck = builder
                    .translation(config + "portal_creation")
                    .comment("Change how the portal can be created. If true, the portal will check where it is allowed to spawn based on the type of list and what contents are in the list.")
                    .define("portalCheck", true);
            listType = builder
                    .translation(config + "list_type")
                    .comment("Changes whether the portal_biomes Biome Tag is a blacklist or a whitelist. A blacklist will exclude biomes from the portal, anything not on the list is allowed. A whitelist will allow biomes for the portal, anything on the list is allowed. If portalCheck is false, this value is unused.")
                    .defineEnum("listType", ListType.WHITELIST);
        }
    }

    public static void checkDimension() {
        Identifier rl = Identifier.tryParse(startDimension.get());
        if (rl == null) {
            GaiaDimensionMod.LOGGER.warn("Could not create an Identifier with the Start Dimension! Is there a typo, or is there an incorrect character?");
            rl = Level.OVERWORLD.identifier();
        }
        startDimRL = rl;
        startDimRK = ResourceKey.create(Registries.DIMENSION, startDimRL);
    }

    public enum ListType {
        BLACKLIST,
        WHITELIST
    }

    //TODO: Verify these two, it might be possible to move these elsewhere
    @EventBusSubscriber(modid = GaiaDimensionMod.MODID)
    public static class ForgeBus {
        @SubscribeEvent
        public static void onConfigLoaded(ServerStartingEvent event) {
            checkDimension();
        }
    }

    @EventBusSubscriber(modid = GaiaDimensionMod.MODID)
    public static class ModBus {
        @SubscribeEvent
        public static void onConfigChanged(ModConfigEvent.Reloading event) {
            if (event.getConfig().getModId().equals(GaiaDimensionMod.MODID)) {
                if (event.getConfig().getSpec() instanceof ModConfigSpec forgeSpec) {
                    if (forgeSpec.isLoaded()) {
                        GaiaDimensionMod.LOGGER.debug("""
                                ForgeConfigSpec is: {}.
                                This is to verify that there is or is not a ForgeConfigSpec.
                                Do report this if the ForgeConfigSpec comes out as a null, as this is impossible.""",
                                forgeSpec);
                        checkDimension();
                    }
                }
            }
        }
    }
}
