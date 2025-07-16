package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.bootstrap.GaiaArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class GaiaDuchessArmorItem extends BasicGaiaArmorItem {

    public GaiaDuchessArmorItem(ArmorType slot, Properties props) {
        super(GaiaArmorMaterials.BIXBITE, slot, props.durability(630));
    }

    //TODO: May teleport user if they are dealt damage. Will check if area is safe to do so

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.RED);
    }

    @Override
    @Deprecated
    public void appendHoverText(ItemStack stack, TooltipContext world, TooltipDisplay display, Consumer<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, display, tooltips, flags);
        tooltips.accept(Component.translatable("bixbite_armor.tooltip"));
    }
}
