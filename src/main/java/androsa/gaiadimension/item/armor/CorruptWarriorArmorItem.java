package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.GaiaArmorMaterials;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
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

public class CorruptWarriorArmorItem extends BasicGaiaArmorItem {

    public CorruptWarriorArmorItem(EquipmentSlotType slot) {
        super(GaiaArmorMaterials.CORRUPT, slot);
    }

    //TODO: Half damage from normal targets, but extra damage from bosses
    //This set is identical to Gaia Champion, but needs serious repercussions when worn

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        return new TranslationTextComponent(super.getName(stack).getString(), TextFormatting.DARK_PURPLE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("corrupt_armor.tooltip"));
    }
}