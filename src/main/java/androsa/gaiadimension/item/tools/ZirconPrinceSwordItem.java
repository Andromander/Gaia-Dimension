package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ZirconPrinceSwordItem extends SwordItem {

    public ZirconPrinceSwordItem(Properties props) {
        super(GaiaToolMaterials.ZIRCON, 3, -2.3F, props);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslatableComponent(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals lightning damage to those hit
    //TODO: [FUTURE] Can be used to unlock Unknown Goldstone Structure?
}
