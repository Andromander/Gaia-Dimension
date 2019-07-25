package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BasicGaiaItem extends Item {

    public BasicGaiaItem() {
        super(new Properties().group(GaiaItemGroups.GAIA_ITEMS));
    }

    public BasicGaiaItem(Food food) {
        super(new Properties().group(ItemGroup.FOOD).food(food));
    }
}
