package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.*;
import androsa.gaiadimension.item.inventory.GemPouchContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, GaiaDimensionMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<AgateCraftingTableMenu>> AGATE_CRAFTING_TABLE = CONTAINERS.register(
            "agate_crafting_table", () -> new MenuType<>(AgateCraftingTableMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<GaiaStoneFurnaceMenu>> GAIA_STONE_FURNACE = CONTAINERS.register(
            "gaia_stone_furnace", () -> new MenuType<>(GaiaStoneFurnaceMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<GemPouchContainer>> GEMSTONE_POUCH = CONTAINERS.register(
            "gemstone_pouch", () -> new MenuType<>(GemPouchContainer::new, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<SmallCrateContainer>> SMALL_CRATE = CONTAINERS.register(
            "small_crate", () -> new MenuType<>(SmallCrateContainer::new, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<LargeCrateMenu>> LARGE_CRATE = CONTAINERS.register(
            "large_crate", () -> new MenuType<>(LargeCrateMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<RestructurerMenu>> RESTRUCTURER = CONTAINERS.register(
            "restructurer", () -> new MenuType<>(RestructurerMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<PurifierMenu>> PURIFIER = CONTAINERS.register(
            "purifier", () -> new MenuType<>(PurifierMenu::new, FeatureFlags.REGISTRY.allFlags()));
}
