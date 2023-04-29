package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.Set;

public class GaiaBuiltinTables {
    private static final Set<ResourceLocation> BUILTIN = Sets.newHashSet();

    public static final ResourceLocation PINK_SAPPER_TABLE = makeTable("entities/common_sapper");
    public static final ResourceLocation BLUE_SAPPER_TABLE = makeTable("entities/chilled_sapper");
    public static final ResourceLocation GREEN_SAPPER_TABLE = makeTable("entities/nutrient_sapper");
    public static final ResourceLocation PURPLE_SAPPER_TABLE = makeTable("entities/mystified_sapper");

    private static ResourceLocation makeTable(String path) {
        ResourceLocation loc = new ResourceLocation(GaiaDimensionMod.MODID, path);

        if (BUILTIN.add(loc)) {
            return loc;
        } else {
            throw new IllegalArgumentException(String.format("%s already exists as a built-in loot table!", loc));
        }
    }

    public static Set<ResourceLocation> builtin() {
        return Collections.unmodifiableSet(BUILTIN);
    }
}
