package androsa.gaiadimension.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;

public class GaiaSpawnEggItem extends SpawnEggItem {

    public GaiaSpawnEggItem(EntityType<?> entity, int back, int front) {
        super(entity, back, front, new Item.Properties().tab(ItemGroup.TAB_MISC));
    }
}
