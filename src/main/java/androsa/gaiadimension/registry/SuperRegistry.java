package androsa.gaiadimension.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;

public interface SuperRegistry {

    void registerBlock(RegistryEvent.Register<Block> e);

    void registerItem(RegistryEvent.Register<Item> e);

    void registerModel(ModelRegistryEvent e);
}
