package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class GaiaDukeSwordItem extends Item {

    public GaiaDukeSwordItem(Properties props) {
        super(props.sword(GaiaToolMaterials.LARVIKITE, 3, -3.0F));
    }

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.BLUE);
    }

    @Override
    @Deprecated
    public void appendHoverText(ItemStack stack, TooltipContext world, TooltipDisplay display, Consumer<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, display, tooltips, flags);
        tooltips.accept(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    @Override
    public boolean isCombineRepairable(ItemStack stack) {
        return false;
    }

    //TODO: Deals extra damage to Gaian mobs
    //TODO: [FUTURE] Can be used to unlock Unknown Stage 4 Final Boss Structure?
}
