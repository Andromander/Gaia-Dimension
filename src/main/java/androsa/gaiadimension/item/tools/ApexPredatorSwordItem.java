package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ApexPredatorSwordItem extends SwordItem {

    public ApexPredatorSwordItem(Properties props) {
        super(GaiaToolMaterials.TIGER_EYE, 3, -3.2F, props);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deal Bleeding damage/effect
    //TODO: [FUTURE] Can be used to unlock Unknown Spinel Princess Structure?
}

