package androsa.gaiadimension.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class GaiaSpawnEggItem extends SpawnEggItem {

    public GaiaSpawnEggItem(EntityType<? extends Mob> entity, int back, int front) {
        super(entity, back, front, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }
}
