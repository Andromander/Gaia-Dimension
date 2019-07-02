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

public class GaiaDukeArmorItem extends ArmorItem {

    public GaiaDukeArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        super(material, slot, new Properties().maxStackSize(1).defaultMaxDamage(material.getDurability(slot)).rarity(Rarity.RARE).group(ModItemGroups.GAIA_ARMOR));
    }

    //TODO: If I can, allows for temporary Elytra flight. Otherwise, teleports the attacker randomly

    @Override
    @Nonnull
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(super.getDisplayName(stack).getString(), TextFormatting.BLUE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("larvikite_armor.tooltip"));
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer) {
        if (slot == EquipmentSlotType.LEGS) {
            return GaiaDimension.ARMOR_DIR + "gaia_duke_2.png";
        } else {
            return GaiaDimension.ARMOR_DIR + "gaia_duke_1.png";
        }
    }
}
