package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.*;
import androsa.gaiadimension.block.screen.*;
import androsa.gaiadimension.item.inventory.GemPouchContainer;
import androsa.gaiadimension.item.inventory.GemPouchScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<MenuType<AgateCraftingTableMenu>> AGATE_CRAFTING_TABLE = CONTAINERS.register(
            "agate_crafting_table", () -> new MenuType<>(AgateCraftingTableMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final RegistryObject<MenuType<GaiaStoneFurnaceMenu>> GAIA_STONE_FURNACE = CONTAINERS.register(
            "gaia_stone_furnace", () -> new MenuType<>(GaiaStoneFurnaceMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final RegistryObject<MenuType<GemPouchContainer>> GEMSTONE_POUCH = CONTAINERS.register(
            "gemstone_pouch", () -> new MenuType<>(GemPouchContainer::new, FeatureFlags.REGISTRY.allFlags()));
    public static final RegistryObject<MenuType<SmallCrateContainer>> SMALL_CRATE = CONTAINERS.register(
            "small_crate", () -> new MenuType<>(SmallCrateContainer::new, FeatureFlags.REGISTRY.allFlags()));
    public static final RegistryObject<MenuType<LargeCrateMenu>> LARGE_CRATE = CONTAINERS.register(
            "large_crate", () -> new MenuType<>(LargeCrateMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final RegistryObject<MenuType<RestructurerMenu>> RESTRUCTURER = CONTAINERS.register(
            "restructurer", () -> new MenuType<>(RestructurerMenu::new, FeatureFlags.REGISTRY.allFlags()));
    public static final RegistryObject<MenuType<PurifierMenu>> PURIFIER = CONTAINERS.register(
            "purifier", () -> new MenuType<>(PurifierMenu::new, FeatureFlags.REGISTRY.allFlags()));
}
