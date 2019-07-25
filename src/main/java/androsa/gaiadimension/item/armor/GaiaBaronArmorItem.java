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

public class GaiaBaronArmorItem extends ArmorItem {

    public GaiaBaronArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Properties().maxStackSize(1).defaultMaxDamage(material.getDurability(slot)).rarity(Rarity.RARE).group(GaiaItemGroups.GAIA_ARMOR));
    }

    //TODO: Small chance to not be dealt damage through melee

    @Override
    @Nonnull
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(this.getTranslationKey(stack), TextFormatting.GREEN);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("tsavorite_armor.tooltip"));
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return GaiaDimensionMod.ARMOR_DIR + "gaia_baron_2.png";
        } else {
            return GaiaDimensionMod.ARMOR_DIR + "gaia_baron_1.png";
        }
    }
}
