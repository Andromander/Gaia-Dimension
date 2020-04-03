package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.util.ResourceLocation;

public class GaiaChestTables {
    public static final ResourceLocation CHESTS_MINITOWER_AMETHYST = register("minitower/tower_amethyst");
    public static final ResourceLocation CHESTS_MINITOWER_COPAL = register("minitower/tower_copal");
    public static final ResourceLocation CHESTS_MINITOWER_JADE = register("minitower/tower_jade");
    public static final ResourceLocation CHESTS_MINITOWER_JET = register("minitower/tower_jet");

    public static ResourceLocation register(String name) {
        return new ResourceLocation(GaiaDimensionMod.MODID, "chests/" + name);
    }
}
