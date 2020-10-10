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
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = GaiaDimensionMod.MODID)
public class ModContainers {

    public static final ContainerType<AgateCraftingTableContainer> AGATE_CRAFTING_TABLE = RegistryHelper.registerContainer("agate_crafting_table", AgateCraftingTableContainer::new);
    public static final ContainerType<GaiaStoneFurnaceContainer> GAIA_STONE_FURNACE = RegistryHelper.registerContainer("gaia_stone_furnace", GaiaStoneFurnaceContainer::new);
    public static final ContainerType<GemPouchContainer> GEMSTONE_POUCH = RegistryHelper.registerContainer("gemstone_pouch", GemPouchContainer::new);
    public static final ContainerType<SmallCrateContainer> SMALL_CRATE = RegistryHelper.registerContainer("small_crate", SmallCrateContainer::new);
    public static final ContainerType<LargeCrateContainer> LARGE_CRATE = RegistryHelper.registerContainer("large_crate", LargeCrateContainer::new);
    public static final ContainerType<RestructurerContainer> RESTRUCTURER = RegistryHelper.registerContainer("restructurer", RestructurerContainer::new);
    public static final ContainerType<PurifierContainer> PURIFIER = RegistryHelper.registerContainer("purifier", PurifierContainer::new);

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens() {
        ScreenManager.registerFactory(AGATE_CRAFTING_TABLE, AgateCraftingScreen::new);
        ScreenManager.registerFactory(GAIA_STONE_FURNACE, GaiaStoneFurnaceScreen::new);
        ScreenManager.registerFactory(GEMSTONE_POUCH, GemPouchScreen::new);
        ScreenManager.registerFactory(SMALL_CRATE, SmallCrateScreen::new);
        ScreenManager.registerFactory(LARGE_CRATE, LargeCrateScreen::new);
        ScreenManager.registerFactory(RESTRUCTURER, RestructurerScreen::new);
        ScreenManager.registerFactory(PURIFIER, PurifierScreen::new);
    }
}
