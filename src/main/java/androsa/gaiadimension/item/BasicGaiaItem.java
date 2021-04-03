package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BasicGaiaItem extends Item {

    public BasicGaiaItem() {
        super(new Properties().tab(GaiaItemGroups.GAIA_ITEMS));
    }

    public BasicGaiaItem(Food food) {
        super(new Properties().tab(ItemGroup.TAB_FOOD).food(food));
    }
}
