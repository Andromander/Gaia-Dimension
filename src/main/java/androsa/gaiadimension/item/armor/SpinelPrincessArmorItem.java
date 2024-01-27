package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.values.GaiaArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpinelPrincessArmorItem extends BasicGaiaArmorItem {

    public SpinelPrincessArmorItem(ArmorItem.Type slot, Properties props) {
        super(GaiaArmorMaterials.SPINEL, slot, props);
    }

    //TODO: Reduces damage from fire damage and sources

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("spinel_armor.tooltip"));
    }
}

