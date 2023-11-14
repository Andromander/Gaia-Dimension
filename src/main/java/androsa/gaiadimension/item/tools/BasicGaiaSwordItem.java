package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaSwordItem extends SwordItem implements HoningEquipment {

    public BasicGaiaSwordItem(Tier material, Properties props) {
        super(material, 3, -2.4F, props);
    }
}
