package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BasicGaiaItem extends Item {

    public BasicGaiaItem() {
        super(new Properties().group(ModItemGroups.GAIA_ITEMS));
    }

    public BasicGaiaItem(Food food) {
        super(new Properties().group(ItemGroup.FOOD).food(food));
    }
}
