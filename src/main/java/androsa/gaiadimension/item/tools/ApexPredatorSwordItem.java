package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ApexPredatorSwordItem extends SwordItem {

    public ApexPredatorSwordItem(Properties props) {
        super(GaiaToolMaterials.TIGER_EYE, props.attributes(createAttributes(GaiaToolMaterials.TIGER_EYE, 3, -3.2F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deal Bleeding damage/effect
    //TODO: [FUTURE] Can be used to unlock Unknown Spinel Princess Structure?
}

