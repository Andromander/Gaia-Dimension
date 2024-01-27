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

public class GaiaBaronArmorItem extends BasicGaiaArmorItem {

    public GaiaBaronArmorItem(ArmorItem.Type slot, Properties props) {
        super(GaiaArmorMaterials.TSAVORITE, slot, props);
    }

    //TODO: Small chance to not be dealt damage through melee

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString(), ChatFormatting.GREEN);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("tsavorite_armor.tooltip"));
    }
}
