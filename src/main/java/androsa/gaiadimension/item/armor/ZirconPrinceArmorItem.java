package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.bootstrap.GaiaArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;

public class ZirconPrinceArmorItem extends BasicGaiaArmorItem {

    public ZirconPrinceArmorItem(ArmorType slot, Properties props) {
        super(GaiaArmorMaterials.ZIRCON, slot, props);
    }

    //TODO: Deals lightning damage to attackers

    @Override
    @Deprecated
    public void appendHoverText(ItemStack stack, TooltipContext world, TooltipDisplay display, Consumer<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, display, tooltips, flags);
        tooltips.accept(Component.translatable("zircon_armor.tooltip"));
    }
}
