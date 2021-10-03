package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class GaiaDukeSwordItem extends SwordItem {

    public GaiaDukeSwordItem(Properties props) {
        super(GaiaToolMaterials.LARVIKITE, 3, -3.0F, props);
    }

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return new TranslatableComponent(super.getName(stack).getString(), ChatFormatting.BLUE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslatableComponent(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals extra damage to Gaian mobs
    //TODO: [FUTURE] Can be used to unlock Unknown Stage 4 Final Boss Structure?
}
