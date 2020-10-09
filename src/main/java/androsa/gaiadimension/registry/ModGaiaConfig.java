package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.Collections;
import java.util.List;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class ModGaiaConfig {
    private static final String config = GaiaDimensionMod.MODID + ".config.";

    public static ConfigValue<List<? extends String>> starsInSky;

    public static List<? extends String> starBiomes = Collections.singletonList("gaiadimension:purple_agate_swamp");

    public static class ClientConfig {
        public ClientConfig(Builder builder) {
            starsInSky = builder
                    .translation(config + "stars_in_sky")
                    .comment("A list of biomes to always display stars. This will only work in the dimension as this is where stars are rendered per biome")
                    .defineList("starsInSky", starBiomes, p -> true);
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

    public static boolean canDisplayStars(RegistryKey<Biome> key) {
        starBiomes = starsInSky.get();

        if (starBiomes != null) {
            for (String biome : starBiomes) {
                if (biome.equals(key.toString())) {
                    return true;
                }
            }
        }
        return false;
    }
}
