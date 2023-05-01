package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.GaiaArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class GaiaDukeArmorItem extends BasicGaiaArmorItem {

    public GaiaDukeArmorItem(ArmorItem.Type slot, Properties props) {
        super(GaiaArmorMaterials.LARVIKITE, slot, props);
    }

    //TODO: If I can, allows for temporary Elytra flight. Otherwise, teleports the attacker randomly

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return Component.translatable(super.getName(stack).getString(), ChatFormatting.BLUE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("larvikite_armor.tooltip"));
    }
}
