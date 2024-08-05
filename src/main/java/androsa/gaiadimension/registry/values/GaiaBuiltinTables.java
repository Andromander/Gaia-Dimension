package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.Set;

public class GaiaBuiltinTables {
    private static final Set<ResourceKey<LootTable>> BUILTIN = Sets.newHashSet();

    public static final ResourceKey<LootTable> PINK_SAPPER_TABLE = makeTable("entities/common_sapper");
    public static final ResourceKey<LootTable> BLUE_SAPPER_TABLE = makeTable("entities/chilled_sapper");
    public static final ResourceKey<LootTable> GREEN_SAPPER_TABLE = makeTable("entities/nutrient_sapper");
    public static final ResourceKey<LootTable> PURPLE_SAPPER_TABLE = makeTable("entities/mystified_sapper");

    private static ResourceKey<LootTable> makeTable(String path) {
        ResourceKey<LootTable> loc = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, path));

        if (BUILTIN.add(loc)) {
            return loc;
        } else {
            throw new IllegalArgumentException(String.format("%s already exists as a built-in loot table!", loc));
        }
    }

    public static Set<ResourceKey<LootTable>> builtin() {
        return Collections.unmodifiableSet(BUILTIN);
    }
}
