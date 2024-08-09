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

public class CorruptWarriorSwordItem extends SwordItem {

    public CorruptWarriorSwordItem(Properties props) {
        super(GaiaToolMaterials.CORRUPT, props.attributes(createAttributes(GaiaToolMaterials.CORRUPT, 3, -3.5F)));
    }

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString()).withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deal extra damage to Gaian Mobs, but half damage to bosses. May inflict Corrupt Mania
    //TODO: [FUTURE] Can be used to unlock Unknown Stage 1 Final Boss Structure?
}
