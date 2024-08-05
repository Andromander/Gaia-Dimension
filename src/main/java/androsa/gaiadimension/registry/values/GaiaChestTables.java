package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class GaiaChestTables {
    public static final ResourceKey<LootTable> CHESTS_MINITOWER_AMETHYST = register("minitower/tower_amethyst");
    public static final ResourceKey<LootTable> CHESTS_MINITOWER_COPAL = register("minitower/tower_copal");
    public static final ResourceKey<LootTable> CHESTS_MINITOWER_JADE = register("minitower/tower_jade");
    public static final ResourceKey<LootTable> CHESTS_MINITOWER_JET = register("minitower/tower_jet");
    public static final ResourceKey<LootTable> CHESTS_MALACHITE_WATCHTOWER = register("watchtower");

    public static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "chests/" + name));
    }
}
