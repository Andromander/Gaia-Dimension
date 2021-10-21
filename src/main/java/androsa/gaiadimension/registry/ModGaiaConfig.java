package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.Collections;
import java.util.List;

import static net.minecraftforge.common.ForgeConfigSpec.*;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGaiaConfig {
    private static final String config = GaiaDimensionMod.MODID + ".config.";

    public static ConfigValue<List<? extends String>> starsInSky;
    public static ResourceLocation startDimRL;
    public static ResourceKey<Level> startDimRK;

    public static List<? extends String> starBiomes = Collections.singletonList("gaiadimension:purple_agate_swamp");

    public static class ClientConfig {
        public ClientConfig(Builder builder) {
            starsInSky = builder
                    .translation(config + "stars_in_sky")
                    .comment("A list of biomes to always display stars. This will only work in the dimension as this is where stars are rendered per biome")
                    .defineList("starsInSky", starBiomes, p -> true);
        }
    }

    public static ConfigValue<? extends String> startDimension;
    public static BooleanValue portalCheck;
    public static EnumValue<ListType> listType;
    public static EnumValue<BiomeType> biomeType;
    public static ConfigValue<List<? extends String>> biomeList;
    public static ConfigValue<List<? extends String>> categoryList;
    public static ConfigValue<List<? extends String>> typeList;

    public static List<? extends String> biomes = ImmutableList.of(
            "minecraft:desert", "minecraft:desert_hills", "minecraft:jungle", "minecraft:jungle_hills", "minecraft:jungle_edge", "minecraft:savanna", "minecraft:savanna_plateau",
            "minecraft:warm_ocean", "minecraft:deep_warm_ocean", "minecraft:desert_lakes", "minecraft:modified_jungle", "minecraft:modified_jungle_edge", "minecraft:shattered_savanna",
            "minecraft:shattered_savanna_plateau", "minecraft:eroded_badlands", "minecraft:modified_wooded_badlands_plateau", "minecraft:modified_badlands_plateau",
            "minecraft:bamboo_jungle", "minecraft:bamboo_jungle_hills", "minecraft:badlands", "minecraft:wooded_badlands_plateau", "minecraft:badlands_plateau", "minecraft:mountains",
            "minecraft:snowy_mountains", "minecraft:mountain_edge", "minecraft:wooded_mountains", "minecraft:gravelly_mountains", "minecraft:taiga_mountains", "minecraft:tall_birch_hills",
            "minecraft:dark_forest_hills", "minecraft:snowy_taiga_mountains", "minecraft:modified_gravelly_mountains");
    public static List<? extends String> categories = ImmutableList.of(Biome.BiomeCategory.DESERT.getName(), Biome.BiomeCategory.EXTREME_HILLS.getName(), Biome.BiomeCategory.MESA.getName(), Biome.BiomeCategory.SAVANNA.getName(), Biome.BiomeCategory.JUNGLE.getName());
    public static List<? extends String> types = ImmutableList.of(BiomeDictionary.Type.HOT.getName(), BiomeDictionary.Type.DRY.getName(), BiomeDictionary.Type.MOUNTAIN.getName());

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
                    .comment("Changes whether the list to check is a blacklist or a whitelist. A blacklist will exclude biomes from the portal, anything not on the list is allowed. A whitelist will allow biomes for the portal, anything on the list is allowed. If portalCheck is false, this value is unused.")
                    .defineEnum("listType", ListType.WHITELIST);
            biomeType = builder
                    .translation(config + "biome_type")
                    .comment("Determines what list the biome checker should use. If set to BIOME, the list will check entries based on the individual biome. If set to CATEGORY, the list will check entries based on the biome's Category. If set to Type, the list will check entries based on the biome's BiomeDictionary Type. If portalCheck is false, this value is unused.")
                    .defineEnum("biomeType", BiomeType.TYPE);
            biomeList = builder
                    .translation(config + "biome_list")
                    .comment("A list of biomes to be used by the check list. Blacklists will ignore the biomes while whitelists will allow the biomes. This value is only checked if biomeType is set to BIOME.")
                    .defineList("biomeList", biomes, p -> true);
            categoryList = builder
                    .translation(config + "category_list")
                    .comment("A list of biome Categories to be used by the check list. A Category is a single value assigned to a biome and is a vanilla system. Blacklists will ignore the Categories while whitelists will allow the Categories. This value is only checked if biomeType is set to CATEGORY.")
                    .defineList("categoryList", categories, p -> true);
            typeList = builder
                    .translation(config + "type_list")
                    .comment("A list of biome Types to be used by the check list. A Type is a value that is assigned to a biome by Forge's BiomeDictionary system; one biome can have multiple Types. Blacklists will ignore biomes with the Tag while whitelists will allow biomes with the Tag. This value is only checked if biomeType is set to TYPE.")
                    .defineList("typeList", types, p -> true);
        }
    }

    public static boolean canDisplayStars(ResourceKey<Biome> define) {
        return starsInSky.get().contains(define.location().toString());
    }

    @SubscribeEvent
    public static void onConfigLoaded(ModConfigEvent.Loading event) {
        System.out.println("Config Loading");
        if (event.getConfig().getModId().equals(GaiaDimensionMod.MODID)) {
            checkDimension();
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ModConfigEvent.Reloading event) {
        if (event.getConfig().getModId().equals(GaiaDimensionMod.MODID)) {
            checkDimension();
        }
    }

    private static void checkDimension() {
        ResourceLocation rl = ResourceLocation.tryParse(startDimension.get());
        if (rl == null) {
            GaiaDimensionMod.LOGGER.warn("Could not create a ResourceLocation with the Start Dimension! Is there a typo, or is there an incorrect character?");
            rl = Level.OVERWORLD.location();
        }
        startDimRL = rl;
        startDimRK = ResourceKey.create(Registry.DIMENSION_REGISTRY, startDimRL);
    }

    public enum ListType {
        BLACKLIST,
        WHITELIST
    }

    public enum BiomeType {
        BIOME,
        CATEGORY,
        TYPE,
    }
}
