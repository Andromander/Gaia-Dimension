package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.List;

public class GaiaDuchessSwordItem extends SwordItem {

    public GaiaDuchessSwordItem(Properties props) {
        super(GaiaToolMaterials.BIXBITE, props.attributes(createAttributes(GaiaToolMaterials.BIXBITE, 3, -2.2F)));
    }

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.RED);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Make this sword shoot projectiles, but lowers durability by 2
    //TODO: [FUTURE] Can be used to unlock Unknown Stage 2 Final Boss Structure?
}
