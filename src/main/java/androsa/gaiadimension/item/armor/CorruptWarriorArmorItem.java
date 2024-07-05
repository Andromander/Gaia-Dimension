package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import java.util.List;

public class CorruptWarriorArmorItem extends BasicGaiaArmorItem {

    public CorruptWarriorArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.CORRUPT, slot, props.durability(slot.getDurability(1000)));
    }

    //TODO: Half damage from normal targets, but extra damage from bosses
    //This set is identical to Gaia Champion, but needs serious repercussions when worn

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString(), ChatFormatting.DARK_PURPLE);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("corrupt_armor.tooltip"));
    }
}