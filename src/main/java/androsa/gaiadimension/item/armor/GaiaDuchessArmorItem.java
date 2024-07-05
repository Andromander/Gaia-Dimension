package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import java.util.List;

public class GaiaDuchessArmorItem extends BasicGaiaArmorItem {

    public GaiaDuchessArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.BIXBITE, slot, props.durability(630));
    }

    //TODO: May teleport user if they are dealt damage. Will check if area is safe to do so

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString(), ChatFormatting.RED);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("bixbite_armor.tooltip"));
    }
}
