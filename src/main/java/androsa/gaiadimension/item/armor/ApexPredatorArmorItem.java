package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.bootstrap.GaiaArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class ApexPredatorArmorItem extends BasicGaiaArmorItem {

    public ApexPredatorArmorItem(ArmorType slot, Properties props) {
        super(GaiaArmorMaterials.TIGER_EYE, slot, props.durability(slot.getDurability(315)));
    }

    //TODO: Deal melee damage to attacker

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("tigereye_armor.tooltip"));
    }
}
