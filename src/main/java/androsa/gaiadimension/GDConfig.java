package androsa.gaiadimension;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = GaiaDimension.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimension.MODID)
public class GDConfig {
    private final static String config = GaiaDimension.MODID + ".config.";

    @Config.LangKey(config + "dimension")
    @Config.Comment("A restart is required if changes are made here.")
    public static Dimension dimension = new Dimension();

    public static class Dimension {
        @Config.LangKey(config + "dimension_id")
        @Config.RequiresMcRestart
        @Config.Comment("The ID of the Gaia dimension. This setting should only be changed if mod conflicts are present.")
        public int dimensionID = -30;

        @Config.LangKey(config + "dimension_seed")
        @Config.RequiresMcRestart
        @Config.Comment("Overrides the seed of the dimension. Changing this setting will always generate the Gaia dimension to the specified seed.")
        public String gaiaSeed = "";

    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GaiaDimension.MODID));
        ConfigManager.sync(GaiaDimension.MODID, Config.Type.INSTANCE);
    }
}
