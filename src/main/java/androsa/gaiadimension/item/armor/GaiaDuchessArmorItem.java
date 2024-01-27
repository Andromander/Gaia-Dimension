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

public class GaiaDuchessArmorItem extends BasicGaiaArmorItem {

    public GaiaDuchessArmorItem(ArmorItem.Type slot, Properties props) {
        super(GaiaArmorMaterials.BIXBITE, slot, props);
    }

    //TODO: May teleport user if they are dealt damage. Will check if area is safe to do so

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString(), ChatFormatting.RED);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("bixbite_armor.tooltip"));
    }
}
