package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class ModGaiaConfig {
    private static final String config = GaiaDimensionMod.MODID + ".config.";

    public static BooleanValue enableSkyFog;
    public static EnumValue<GaiaSkyColors> skyColors;

    public static class ClientConfig {
        public ClientConfig(Builder builder) {
            enableSkyFog = builder
                    .translation(config + "enable_sky_fog")
                    .comment("For those bothered by the sky transtions or using shaders. Disables the differing sky and fog colour to the default preset.")
                    .define("enableSkyFog", true);
            skyColors = builder
                    .translation(config + "sky_option")
                    .comment("Set the Sky, Fog, and Cloud color to any of the existing biome options. This config option does not work if Enable Sky Transitions is true")
                    .defineEnum("skyColors", GaiaSkyColors.values()[0]);
        }
    }

    public static BooleanValue portalCheck;

    public static class ServerConfig {
        public ServerConfig(Builder builder) {
            portalCheck = builder
                    .translation(config + "portal_creation")
                    .comment("Change how the portal can be created. If true, the portal will only be created in Dry, Mountainous, or Hot biomes, or anywhere in Gaia.")
                    .define("portalCheck", true);
        }
    }
}
