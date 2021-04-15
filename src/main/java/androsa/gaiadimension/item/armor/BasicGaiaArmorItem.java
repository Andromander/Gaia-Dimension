package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class BasicGaiaArmorItem extends ArmorItem {

    public BasicGaiaArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Properties().tab(GaiaItemGroups.GAIA_ARMOR));
    }
}
