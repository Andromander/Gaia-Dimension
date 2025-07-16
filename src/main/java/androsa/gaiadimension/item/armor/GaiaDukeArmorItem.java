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

public class GaiaDukeArmorItem extends BasicGaiaArmorItem {

    public GaiaDukeArmorItem(ArmorType slot, Properties props) {
        super(GaiaArmorMaterials.LARVIKITE, slot, props);
    }

    //TODO: If I can, allows for temporary Elytra flight. Otherwise, teleports the attacker randomly

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.BLUE);
    }

    @Override
    @Deprecated
    public void appendHoverText(ItemStack stack, TooltipContext world, TooltipDisplay display, Consumer<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, display, tooltips, flags);
        tooltips.accept(Component.translatable("larvikite_armor.tooltip"));
    }
}
