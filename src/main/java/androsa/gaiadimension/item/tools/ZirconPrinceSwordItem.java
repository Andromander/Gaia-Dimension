package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ZirconPrinceSwordItem extends SwordItem {

    public ZirconPrinceSwordItem(Properties props) {
        super(GaiaToolMaterials.ZIRCON, props.attributes(createAttributes(GaiaToolMaterials.ZIRCON, 3, -2.3F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals lightning damage to those hit
    //TODO: [FUTURE] Can be used to unlock Unknown Goldstone Structure?
}
