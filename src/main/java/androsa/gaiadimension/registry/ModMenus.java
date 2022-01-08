package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.*;
import androsa.gaiadimension.block.screen.*;
import androsa.gaiadimension.item.inventory.GemPouchContainer;
import androsa.gaiadimension.item.inventory.GemPouchScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, GaiaDimensionMod.MODID);

    public static final RegistryObject<MenuType<AgateCraftingTableMenu>> AGATE_CRAFTING_TABLE = CONTAINERS.register(
            "agate_crafting_table", () -> new MenuType<>(AgateCraftingTableMenu::new));
    public static final RegistryObject<MenuType<GaiaStoneFurnaceMenu>> GAIA_STONE_FURNACE = CONTAINERS.register(
            "gaia_stone_furnace", () -> new MenuType<>(GaiaStoneFurnaceMenu::new));
    public static final RegistryObject<MenuType<GemPouchContainer>> GEMSTONE_POUCH = CONTAINERS.register(
            "gemstone_pouch", () -> new MenuType<>(GemPouchContainer::new));
    public static final RegistryObject<MenuType<SmallCrateContainer>> SMALL_CRATE = CONTAINERS.register(
            "small_crate", () -> new MenuType<>(SmallCrateContainer::new));
    public static final RegistryObject<MenuType<LargeCrateMenu>> LARGE_CRATE = CONTAINERS.register(
            "large_crate", () -> new MenuType<>(LargeCrateMenu::new));
    public static final RegistryObject<MenuType<RestructurerMenu>> RESTRUCTURER = CONTAINERS.register(
            "restructurer", () -> new MenuType<>(RestructurerMenu::new));
    public static final RegistryObject<MenuType<PurifierMenu>> PURIFIER = CONTAINERS.register(
            "purifier", () -> new MenuType<>(PurifierMenu::new));

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens() {
        MenuScreens.register(AGATE_CRAFTING_TABLE.get(), AgateCraftingScreen::new);
        MenuScreens.register(GAIA_STONE_FURNACE.get(), GaiaStoneFurnaceScreen::new);
        MenuScreens.register(GEMSTONE_POUCH.get(), GemPouchScreen::new);
        MenuScreens.register(SMALL_CRATE.get(), SmallCrateScreen::new);
        MenuScreens.register(LARGE_CRATE.get(), LargeCrateScreen::new);
        MenuScreens.register(RESTRUCTURER.get(), RestructurerScreen::new);
        MenuScreens.register(PURIFIER.get(), PurifierScreen::new);
    }
}
