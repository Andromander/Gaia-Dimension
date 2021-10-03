package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.GaiaArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class CorruptWarriorArmorItem extends BasicGaiaArmorItem {

    public CorruptWarriorArmorItem(EquipmentSlot slot, Properties props) {
        super(GaiaArmorMaterials.CORRUPT, slot, props);
    }

    //TODO: Half damage from normal targets, but extra damage from bosses
    //This set is identical to Gaia Champion, but needs serious repercussions when worn

    @Override
    @Nonnull
    public Component getName(ItemStack stack) {
        return new TranslatableComponent(super.getName(stack).getString(), ChatFormatting.DARK_PURPLE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslatableComponent("corrupt_armor.tooltip"));
    }
}