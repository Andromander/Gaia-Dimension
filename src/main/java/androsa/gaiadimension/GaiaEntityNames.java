package androsa.gaiadimension;

import net.minecraft.util.ResourceLocation;

public class GaiaEntityNames {
    public static final ResourceLocation HOWLITE_WOLF = prefix("howlite_wolf");
    public static final ResourceLocation SPELLBOUND_ELEMENTAL = prefix("spellbound_elemental");

    public static final ResourceLocation BLUE_HOWLITE_WOLF = prefix("blue_howlite_wolf");
    public static final ResourceLocation MALACHITE_GUARD = prefix("malachite_guard");

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(GaiaDimension.MODID, path);
    }
}
