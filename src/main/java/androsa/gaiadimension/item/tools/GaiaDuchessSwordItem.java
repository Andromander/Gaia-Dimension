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

public class GaiaDuchessSwordItem extends Item {

    public GaiaDuchessSwordItem(Properties props) {
        super(props.sword(GaiaToolMaterials.BIXBITE, 3, -2.2F));
    }

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.RED);
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

    //TODO: Make this sword shoot projectiles, but lowers durability by 2
    //TODO: [FUTURE] Can be used to unlock Unknown Stage 2 Final Boss Structure?
}
