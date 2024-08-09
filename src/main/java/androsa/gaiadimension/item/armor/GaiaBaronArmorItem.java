package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import java.util.List;

public class GaiaBaronArmorItem extends BasicGaiaArmorItem {

    public GaiaBaronArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.TSAVORITE, slot, props.durability(slot.getDurability(709)));
    }

    //TODO: Small chance to not be dealt damage through melee

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.GREEN);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("tsavorite_armor.tooltip"));
    }
}
