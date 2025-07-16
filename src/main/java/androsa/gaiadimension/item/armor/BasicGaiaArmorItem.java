package androsa.gaiadimension.item.armor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class BasicGaiaArmorItem extends Item {

    public BasicGaiaArmorItem(ArmorMaterial material, ArmorType slot, Properties props) {
        super(props.humanoidArmor(material, slot));
    }

    //TODO: Yes, this looks redundant, but keep it here for Honing
}
