package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.container.*;
import androsa.gaiadimension.block.inventory.*;
import androsa.gaiadimension.item.inventory.GemPouchContainer;
import androsa.gaiadimension.item.inventory.GemPouchScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, GaiaDimensionMod.MODID);

    public static final RegistryObject<ContainerType<AgateCraftingTableContainer>> AGATE_CRAFTING_TABLE = CONTAINERS.register(
            "agate_crafting_table", () -> new ContainerType<>(AgateCraftingTableContainer::new));
    public static final RegistryObject<ContainerType<GaiaStoneFurnaceContainer>> GAIA_STONE_FURNACE = CONTAINERS.register(
            "gaia_stone_furnace", () -> new ContainerType<>(GaiaStoneFurnaceContainer::new));
    public static final RegistryObject<ContainerType<GemPouchContainer>> GEMSTONE_POUCH = CONTAINERS.register(
            "gemstone_pouch", () -> new ContainerType<>(GemPouchContainer::new));
    public static final RegistryObject<ContainerType<SmallCrateContainer>> SMALL_CRATE = CONTAINERS.register(
            "small_crate", () -> new ContainerType<>(SmallCrateContainer::new));
    public static final RegistryObject<ContainerType<LargeCrateContainer>> LARGE_CRATE = CONTAINERS.register(
            "large_crate", () -> new ContainerType<>(LargeCrateContainer::new));
    public static final RegistryObject<ContainerType<RestructurerContainer>> RESTRUCTURER = CONTAINERS.register(
            "restructurer", () -> new ContainerType<>(RestructurerContainer::new));
    public static final RegistryObject<ContainerType<PurifierContainer>> PURIFIER = CONTAINERS.register(
            "purifier", () -> new ContainerType<>(PurifierContainer::new));

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens() {
        ScreenManager.register(AGATE_CRAFTING_TABLE.get(), AgateCraftingScreen::new);
        ScreenManager.register(GAIA_STONE_FURNACE.get(), GaiaStoneFurnaceScreen::new);
        ScreenManager.register(GEMSTONE_POUCH.get(), GemPouchScreen::new);
        ScreenManager.register(SMALL_CRATE.get(), SmallCrateScreen::new);
        ScreenManager.register(LARGE_CRATE.get(), LargeCrateScreen::new);
        ScreenManager.register(RESTRUCTURER.get(), RestructurerScreen::new);
        ScreenManager.register(PURIFIER.get(), PurifierScreen::new);
    }
}
