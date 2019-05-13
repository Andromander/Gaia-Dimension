package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
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

        @Config.LangKey(config + "enable_sky_fog")
        @Config.Comment("For those bothered by the sky transtions or using shaders. Disables the differing sky and fog colour to the default preset.")
        public boolean enableSkyFog = true;

        @Config.LangKey(config + "sky_option")
        @Config.Comment("Set the Sky, Fog, and Cloud color to any of the existing biome options. This config option does not work if Enable Sky Transitions is true")
        public EnumSkyColors skyColors = EnumSkyColors.values()[0];
    }

    @Config.LangKey(config + "ore_dictionary")
    @Config.Comment("Some unwritten standards state that I need to register things to the Ore Dictionary. These can be changed, but be warned that this may alter balance of this or other mods.")
    public static OreDictionaryOptions oreDict = new OreDictionaryOptions();

    public static class OreDictionaryOptions {
        @Config.LangKey(config + "sugilite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Sugilite to oreSugilite, gemSugilite and blockSugilite")
        public boolean sugilite = false;

        @Config.LangKey(config + "hematite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Hematite to oreHematite, gemHematite and blockHematite")
        public boolean hematite = false;

        @Config.LangKey(config + "pyrite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Pyrite to orePyrite, gemPyrite and blockPyrite")
        public boolean pyrite = false;

        @Config.LangKey(config + "labradorite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Labradorite to oreLabradorite, gemLabradorite and blockLabradorite")
        public boolean labradorite = false;

        @Config.LangKey(config + "moonstone")
        @Config.RequiresMcRestart
        @Config.Comment("Register Moonstone to oreMoonstone, gemMoonstone and blockMoonstone")
        public boolean moonstone = false;

        @Config.LangKey(config + "cinnabar")
        @Config.RequiresMcRestart
        @Config.Comment("Register Cinnabar to oreCinnabar, gemCinnabar and blockCinnabar")
        public boolean cinnabar = false;

        @Config.LangKey(config + "red_opal")
        @Config.RequiresMcRestart
        @Config.Comment("Register Red Opal to oreRedOpal, gemRedOpal and blockRedOpal")
        public boolean red_opal = false;

        @Config.LangKey(config + "blue_opal")
        @Config.RequiresMcRestart
        @Config.Comment("Register Blue Opal to oreBlueOpal, gemBlueOpal and blockBlueOpal")
        public boolean blue_opal = false;

        @Config.LangKey(config + "green_opal")
        @Config.RequiresMcRestart
        @Config.Comment("Register Green Opal to oreGreenOpal, gemGreenOpal and blockGreenOpal")
        public boolean green_opal = false;

        @Config.LangKey(config + "white_opal")
        @Config.RequiresMcRestart
        @Config.Comment("Register White Opal to oreWhiteOpal, gemWhitenOpal and blockWhiteOpal")
        public boolean white_opal = false;

        @Config.LangKey(config + "ixiolite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Ixiolite to gemIxiolite and blockIxiolite")
        public boolean ixiolite = false;

        @Config.LangKey(config + "proustite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Proustite to gemProustite and blockProustite")
        public boolean proustite = false;

        @Config.LangKey(config + "euclase")
        @Config.RequiresMcRestart
        @Config.Comment("Register Euclase to gemEuclase and blockEuclase")
        public boolean euclase = false;

        @Config.LangKey(config + "leucite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Leucite to gemLeucite and blockLeucite")
        public boolean leucite = false;

        @Config.LangKey(config + "carnelian")
        @Config.RequiresMcRestart
        @Config.Comment("Register Carnelian to gemCarnelian and blockCarnelian")
        public boolean carnelian = false;

        @Config.LangKey(config + "benitoite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Benitoite to gemBenitoite and blockBenitoite")
        public boolean benitoite = false;

        @Config.LangKey(config + "diopside")
        @Config.RequiresMcRestart
        @Config.Comment("Register Diopside to gemDiopside and blockDiopside")
        public boolean diopside = false;

        @Config.LangKey(config + "chalcedony")
        @Config.RequiresMcRestart
        @Config.Comment("Register Chalcedony to gemChalcedony and blockChalcedony")
        public boolean chalcedony = false;

        @Config.LangKey(config + "tektite")
        @Config.RequiresMcRestart
        @Config.Comment("Register Tektite to gemTektite and blockTektite")
        public boolean tektite = false;

        @Config.LangKey(config + "goldstone")
        @Config.RequiresMcRestart
        @Config.Comment("Register Goldstone to gemGoldstone and blockGoldstone")
        public boolean goldstone = false;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GaiaDimension.MODID)) {
            ConfigManager.sync(GaiaDimension.MODID, Config.Type.INSTANCE);
        }
    }
}
