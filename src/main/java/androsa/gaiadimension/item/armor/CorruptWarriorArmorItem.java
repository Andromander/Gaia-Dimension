package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.values.GaiaArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.List;

public class CorruptWarriorArmorItem extends BasicGaiaArmorItem {

    public CorruptWarriorArmorItem(ArmorItem.Type slot, Properties props) {
        super(GaiaArmorMaterials.CORRUPT, slot, props);
    }

    //TODO: Half damage from normal targets, but extra damage from bosses
    //This set is identical to Gaia Champion, but needs serious repercussions when worn

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString(), ChatFormatting.DARK_PURPLE);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("corrupt_armor.tooltip"));
    }
}