package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.bootstrap.GaiaArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class GaiaChampArmorItem extends BasicGaiaArmorItem {

    public GaiaChampArmorItem(ArmorType slot, Properties props) {
        super(GaiaArmorMaterials.GAIA_CHAMP, slot, props);
    }

    //TODO: Half damage from Corrupt and Non-Gaian mobs

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable("champion_armor.tooltip"));
    }
}
