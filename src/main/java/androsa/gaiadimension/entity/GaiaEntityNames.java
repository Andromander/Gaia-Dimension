package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.util.ResourceLocation;

public class GaiaEntityNames {
    //public static final ResourceLocation EARTH_SHOT = prefix("earth_shot");
    public static final ResourceLocation COMMON_SAPPER = prefix("common_growth_sapper");
    public static final ResourceLocation CHILLED_SAPPER = prefix("chilled_growth_sapper");
    public static final ResourceLocation NUTRIENT_SAPPER = prefix("nutrient_growth_sapper");
    public static final ResourceLocation MYSTIFIED_SAPPER = prefix("mystified_growth_sapper");
    public static final ResourceLocation CORRUPT_SAPPER = prefix("corrupt_sapper");
    public static final ResourceLocation MUTANT_EXTRACTOR = prefix("mutant_growth_extractor");
    public static final ResourceLocation AGATE_GOLEM = prefix("agate_golem");
    public static final ResourceLocation CRYSTAL_GOLEM = prefix("crystal_golem");
    public static final ResourceLocation HOWLITE_WOLF = prefix("howlite_wolf");
    public static final ResourceLocation BLUE_HOWLITE_WOLF = prefix("blue_howlite_wolf");
    public static final ResourceLocation MARKUZAR_PLANT = prefix("markuzar_plant");
    public static final ResourceLocation SPELLBOUND_ELEMENTAL = prefix("spellbound_elemental");
    public static final ResourceLocation MUCKLING = prefix("muckling");
    public static final ResourceLocation ROCKY_LUGGEROTH = prefix("rocky_luggeroth");
    public static final ResourceLocation RUGGED_LURMORUS = prefix("rugged_lurmorus");
    public static final ResourceLocation ANCIENT_LAGRAHK = prefix("ancient_lagrahk");
    public static final ResourceLocation NOMADIC_LAGRAHK = prefix("nomadic_lagrahk");
    public static final ResourceLocation SALTION = prefix("saltion");
    public static final ResourceLocation SHALLOW_ARENTHIS = prefix("shallow_arenthis");
    public static final ResourceLocation SHALURKER = prefix("shalurker");

    public static final ResourceLocation MALACHITE_GUARD = prefix("malachite_guard");

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(GaiaDimension.MODID, path);
    }
}
