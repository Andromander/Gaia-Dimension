package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class GDGaiaChampArmor extends ItemArmor implements ModelRegisterCallback {

    public GDGaiaChampArmor(ArmorMaterial par2EnumArmorMaterial, EntityEquipmentSlot armorType) {
        super(par2EnumArmorMaterial, 0, armorType);
        this.setCreativeTab(GDTabs.tabArmor);
        this.setMaxStackSize(1);
    }

    //TODO: Half damage from Corrupt and Non-Gaian mobs

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(I18n.format("champion_armor.tooltip"));
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        if (slot == EntityEquipmentSlot.LEGS) {
            return GaiaDimension.ARMOR_DIR + "gaia_champion_2.png";
        } else {
            return GaiaDimension.ARMOR_DIR + "gaia_champion_1.png";
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
