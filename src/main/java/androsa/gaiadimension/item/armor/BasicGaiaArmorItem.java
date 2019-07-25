package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class BasicGaiaArmorItem extends ArmorItem {

    public BasicGaiaArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Properties().maxDamage(1).defaultMaxDamage(material.getDurability(slot)).group(GaiaItemGroups.GAIA_ARMOR));
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return GaiaDimensionMod.ARMOR_DIR + material.getName() + "armor_2.png";
        } else {
            return GaiaDimensionMod.ARMOR_DIR + material.getName() + "armor_1.png";
        }
    }
}
