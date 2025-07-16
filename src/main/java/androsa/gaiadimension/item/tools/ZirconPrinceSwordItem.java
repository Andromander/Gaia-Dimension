package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class ZirconPrinceSwordItem extends Item {

    public ZirconPrinceSwordItem(Properties props) {
        super(props.sword(GaiaToolMaterials.ZIRCON, 3, -2.3F));
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

    //TODO: Deals lightning damage to those hit
    //TODO: [FUTURE] Can be used to unlock Unknown Goldstone Structure?
}
