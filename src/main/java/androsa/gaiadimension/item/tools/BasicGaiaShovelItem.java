package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaShovelItem extends ShovelItem implements HoningEquipment {

    public BasicGaiaShovelItem(Tier material, Properties props) {
        super(material, 1.5F, -3.0F, props);
    }
}
