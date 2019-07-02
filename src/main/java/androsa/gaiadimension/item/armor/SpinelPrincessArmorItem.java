package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.ModItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class SpinelPrincessArmorItem extends ArmorItem {

    public SpinelPrincessArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Item.Properties().maxStackSize(1).defaultMaxDamage(material.getDurability(slot)).rarity(Rarity.RARE).group(ModItemGroups.GAIA_ARMOR));
    }

    //TODO: Reduces damage from fire damage and sources

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("spinel_armor.tooltip"));
    }


    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return GaiaDimension.ARMOR_DIR + "spinel_princess_2.png";
        } else {
            return GaiaDimension.ARMOR_DIR + "spinel_princess_1.png";
        }
    }
}

