package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ZirconPrinceArmorItem extends BasicGaiaArmorItem {

    public ZirconPrinceArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.ZIRCON, slot, props.durability(slot.getDurability(472)));
    }

    //TODO: Deals lightning damage to attackers

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("zircon_armor.tooltip"));
    }
}
