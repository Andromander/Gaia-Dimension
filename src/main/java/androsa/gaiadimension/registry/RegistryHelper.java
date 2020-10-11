package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHelper {

    public static final List<Block> BLOCKS = Lists.newArrayList();
    public static final List<Item> ITEMS = Lists.newArrayList();
    public static final List<ContainerType<?>> CONTAINER_TYPES = Lists.newArrayList();
    public static final List<EntityType<?>> ENTITY_TYPES = Lists.newArrayList();
    public static final List<Fluid> FLUIDS = Lists.newArrayList();

    public static <T extends Block> T registerBlockOnly(String name, T block) {
        block.setRegistryName(name);
        BLOCKS.add(block);
        return block;
    }

    public static <T extends Block> T registerBlock(String name, T block) {
        return registerBlock(name, block, 0);
    }

    public static <T extends Block> T registerBlock(String name, T block, int burntime) {
        return registerBlock(name, block, new BlockItem(block, new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack) {
                return burntime;
            }
        });
    }

    public static <T extends Block> T registerBlock(String name, T block, Item item) {
        block.setRegistryName(name);
        BLOCKS.add(block);
        ITEMS.add(registerBlockItem(name, item));
        return block;
    }

    public static Item registerBlockItem(String name, Item item) {
        item.setRegistryName(name);
        return item;
    }

    public static Item registerItem(String name, Item item) {
        item.setRegistryName(name);
        ITEMS.add(item);
        return item;
    }

    public static Item registerWallOrFloorItem(String name, Block floor, Block wall) {
        Item item = new WallOrFloorItem(wall, floor, new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS));
        item.setRegistryName(name);
        ITEMS.add(item);
        return item;
    }

    public static <C extends Container> ContainerType<C> registerContainer(String name, ContainerType.IFactory<C> container) {
        ContainerType<C> type = new ContainerType<>(container);
        type.setRegistryName(name);
        CONTAINER_TYPES.add(type);
        return type;
    }

    public static <E extends Entity> EntityType<E> registerEntity(String name, EntityType<E> entity) {
        entity.setRegistryName(name);
        ENTITY_TYPES.add(entity);
        return entity;
    }

    public static <T extends Fluid> T registerFluid(String name, T fluid) {
        fluid.setRegistryName(name);
        FLUIDS.add(fluid);
        return fluid;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        for (Block block : BLOCKS) {
            registry.register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item : ITEMS) {
            registry.register(item);
        }
    }

    @SubscribeEvent
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> event) {
        IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
        for (ContainerType<?> container : CONTAINER_TYPES) {
            registry.register(container);
        }
    }

    @SubscribeEvent
    public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        for (EntityType<?> entity : ENTITY_TYPES) {
            registry.register(entity);
        }
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event) {
        IForgeRegistry<Fluid> registry = event.getRegistry();
        for (Fluid fluid : FLUIDS) {
            registry.register(fluid);
        }
    }
}
