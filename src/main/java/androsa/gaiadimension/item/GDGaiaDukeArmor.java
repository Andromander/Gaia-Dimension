package androsa.gaiadimension.item;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;

public class GDGaiaDukeArmor extends ItemArmor implements ModelRegisterCallback {

    public GDGaiaDukeArmor(ArmorMaterial par2EnumArmorMaterial, EntityEquipmentSlot armorType) {
        super(par2EnumArmorMaterial, 0, armorType);
        this.setCreativeTab(GDTabs.tabArmor);
    }

    //TODO: Can I make this armor kind-of swanky?

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack) {

        return TextFormatting.BLUE + super.getItemStackDisplayName(par1ItemStack);
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        if (slot == EntityEquipmentSlot.LEGS) {
            return GaiaDimension.ARMOR_DIR + "gaia_duke_2.png";
        } else {
            return GaiaDimension.ARMOR_DIR + "gaia_duke_1.png";
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
