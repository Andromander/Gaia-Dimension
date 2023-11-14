package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaAxeItem extends AxeItem implements HoningEquipment {

    public BasicGaiaAxeItem(Tier material, Properties props) {
        super(material, 6F, -3.2F, props);
    }
}
