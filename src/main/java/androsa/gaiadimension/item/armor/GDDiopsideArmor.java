package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class GDDiopsideArmor extends ItemArmor implements ModelRegisterCallback {

    public GDDiopsideArmor(ArmorMaterial material, EntityEquipmentSlot armorType) {
        super(material, 0, armorType);

        this.setCreativeTab(GDTabs.tabArmor);
        this.setMaxStackSize(1);
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        if (slot == EntityEquipmentSlot.LEGS) {
            return GaiaDimension.ARMOR_DIR + "diopside_armor_2.png";
        } else {
            return GaiaDimension.ARMOR_DIR + "diopside_armor_1.png";
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (isInCreativeTab(tab)) {
            ItemStack istack = new ItemStack(this);
            list.add(istack);
        }
    }
}
