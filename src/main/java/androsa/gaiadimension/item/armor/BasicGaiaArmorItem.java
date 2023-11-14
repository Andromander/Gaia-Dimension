package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class BasicGaiaArmorItem extends ArmorItem implements HoningEquipment {

    public BasicGaiaArmorItem(ArmorMaterial material, ArmorItem.Type slot, Properties props) {
        super(material, slot, props);
    }
}
