package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.values.GaiaArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MalachiteGuardArmorItem extends BasicGaiaArmorItem {

    public MalachiteGuardArmorItem(ArmorItem.Type slot, Properties props) {
        super(GaiaArmorMaterials.MALACHITE, slot, props);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("malachite_armor.tooltip"));
    }
}
