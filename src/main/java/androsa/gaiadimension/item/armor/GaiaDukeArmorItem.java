package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import java.util.List;

public class GaiaDukeArmorItem extends BasicGaiaArmorItem {

    public GaiaDukeArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.LARVIKITE, slot, props.durability(slot.getDurability(788)));
    }

    //TODO: If I can, allows for temporary Elytra flight. Otherwise, teleports the attacker randomly

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.BLUE);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("larvikite_armor.tooltip"));
    }
}
