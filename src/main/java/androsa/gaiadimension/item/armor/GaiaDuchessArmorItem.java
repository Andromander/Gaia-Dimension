package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.ModItemGroups;
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

public class GaiaDuchessArmorItem extends ArmorItem {

    public GaiaDuchessArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Properties().maxStackSize(1).defaultMaxDamage(material.getDurability(slot)).rarity(Rarity.RARE).group(ModItemGroups.GAIA_ARMOR));
    }

    //TODO: May teleport user if they are dealt damage. Will check if area is safe to do so

    @Override
    @Nonnull
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(super.getDisplayName(stack).getString(), TextFormatting.RED);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("bixbite_armor.tooltip"));
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return GaiaDimension.ARMOR_DIR + "gaia_duchess_2.png";
        } else {
            return GaiaDimension.ARMOR_DIR + "gaia_duchess_1.png";
        }
    }
}
