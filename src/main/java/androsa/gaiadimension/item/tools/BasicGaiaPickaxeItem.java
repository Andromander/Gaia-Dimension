package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaPickaxeItem extends PickaxeItem implements HoningEquipment {

    public BasicGaiaPickaxeItem(Tier material, Properties props) {
        super(material, 1, -2.8F, props);
    }
}
