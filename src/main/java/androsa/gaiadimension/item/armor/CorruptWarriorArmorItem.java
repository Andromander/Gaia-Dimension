package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class CorruptWarriorArmorItem extends ArmorItem {

    public CorruptWarriorArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Properties().maxStackSize(1).defaultMaxDamage(material.getDurability(slot)).rarity(Rarity.RARE).group(GaiaItemGroups.GAIA_ARMOR));
    }

    //TODO: Half damage from normal targets, but extra damage from bosses
    //This set is identical to Gaia Champion, but needs serious repercussions when worn

    @Override
    @Nonnull
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(super.getDisplayName(stack).getString(), TextFormatting.DARK_PURPLE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("corrupt_armor.tooltip"));
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return GaiaDimensionMod.ARMOR_DIR + "corrupt_armor_2.png";
        } else {
            return GaiaDimensionMod.ARMOR_DIR + "corrupt_armor_1.png";
        }
    }
}