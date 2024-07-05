package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class SpinelPrincessArmorItem extends BasicGaiaArmorItem {

    public SpinelPrincessArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.SPINEL, slot, props.durability(slot.getDurability(394)));
    }

    //TODO: Reduces damage from fire damage and sources

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("spinel_armor.tooltip"));
    }
}

