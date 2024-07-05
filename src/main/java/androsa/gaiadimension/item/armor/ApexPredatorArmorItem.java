package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.registration.ModArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ApexPredatorArmorItem extends BasicGaiaArmorItem {

    public ApexPredatorArmorItem(ArmorItem.Type slot, Properties props) {
        super(ModArmorMaterials.TIGER_EYE, slot, props.durability(slot.getDurability(315)));
    }

    //TODO: Deal melee damage to attacker

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("tigereye_armor.tooltip"));
    }
}
