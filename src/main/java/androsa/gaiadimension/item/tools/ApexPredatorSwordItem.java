package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class ApexPredatorSwordItem extends Item {

    public ApexPredatorSwordItem(Properties props) {
        super(props.sword(GaiaToolMaterials.TIGER_EYE, 3, -3.2F));
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

    //TODO: Deal Bleeding damage/effect
    //TODO: [FUTURE] Can be used to unlock Unknown Spinel Princess Structure?
}

