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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers {
    public static final ContainerType<AgateCraftingTableContainer> AGATE_TABLE = new ContainerType<>(AgateCraftingTableContainer::new);
    public static final ContainerType<GaiaStoneFurnaceContainer> GAIA_FURNACE = new ContainerType<>(GaiaStoneFurnaceContainer::new);
    public static final ContainerType<GemPouchContainer> GEM_POUCH = new ContainerType<>(GemPouchContainer::new);
    public static final ContainerType<SmallCrateContainer> SMALL_CRATE = new ContainerType<>(SmallCrateContainer::new);
    public static final ContainerType<LargeCrateContainer> LARGE_CRATE = new ContainerType<>(LargeCrateContainer::new);
    public static final ContainerType<RestructurerContainer> RESTRUCTURER = new ContainerType<>(RestructurerContainer::new);
    public static final ContainerType<PurifierContainer> PURIFIER = new ContainerType<>(PurifierContainer::new);

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> e) {
        e.getRegistry().register(AGATE_TABLE.setRegistryName("agate_crafting_table"));
        e.getRegistry().register(GAIA_FURNACE.setRegistryName("gaia_stone_furnace"));
        e.getRegistry().register(GEM_POUCH.setRegistryName("gemstone_pouch"));
        e.getRegistry().register(SMALL_CRATE.setRegistryName("small_crate"));
        e.getRegistry().register(LARGE_CRATE.setRegistryName("large_crate"));
        e.getRegistry().register(RESTRUCTURER.setRegistryName("restructurer"));
        e.getRegistry().register(PURIFIER.setRegistryName("purifier"));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens() {
        ScreenManager.registerFactory(AGATE_TABLE, AgateCraftingScreen::new);
        ScreenManager.registerFactory(GAIA_FURNACE, GaiaStoneFurnaceScreen::new);
        ScreenManager.registerFactory(GEM_POUCH, GemPouchScreen::new);
        ScreenManager.registerFactory(SMALL_CRATE, SmallCrateScreen::new);
        ScreenManager.registerFactory(LARGE_CRATE, LargeCrateScreen::new);
        ScreenManager.registerFactory(RESTRUCTURER, RestructurerScreen::new);
        ScreenManager.registerFactory(PURIFIER, PurifierScreen::new);
    }
}
