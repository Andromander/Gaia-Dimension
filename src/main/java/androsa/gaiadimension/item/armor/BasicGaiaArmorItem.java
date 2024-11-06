package androsa.gaiadimension.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class BasicGaiaArmorItem extends ArmorItem {

    public BasicGaiaArmorItem(ArmorMaterial material, ArmorType slot, Properties props) {
        super(material, slot, props);
    }

    //TODO: Yes, this looks redundant, but keep it here for Honing
}
