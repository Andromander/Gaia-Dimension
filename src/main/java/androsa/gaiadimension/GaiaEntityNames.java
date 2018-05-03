package androsa.gaiadimension;

import net.minecraft.util.ResourceLocation;

public class GaiaEntityNames {
    //public static final ResourceLocation EARTH_SHOT = prefix("earth_shot");
    public static final ResourceLocation COMMON_SAPPER = prefix("common_growth_sapper");
    public static final ResourceLocation CHILLED_SAPPER = prefix("chilled_growth_sapper");
    public static final ResourceLocation NUTRIENT_SAPPER = prefix("nutrient_growth_sapper");
    public static final ResourceLocation MYSTIFIED_SAPPER = prefix("mystified_growth_sapper");
    public static final ResourceLocation HOWLITE_WOLF = prefix("howlite_wolf");
    public static final ResourceLocation SPELLBOUND_ELEMENTAL = prefix("spellbound_elemental");
    public static final ResourceLocation ROCKY_LUGGEROTH = prefix("rocky_luggeroth");
    public static final ResourceLocation SHALURKER = prefix("shalurker");
    public static final ResourceLocation MUCKLING = prefix("muckling");

    public static final ResourceLocation BLUE_HOWLITE_WOLF = prefix("blue_howlite_wolf");
    public static final ResourceLocation MALACHITE_GUARD = prefix("malachite_guard");

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(GaiaDimension.MODID, path);
    }
}
