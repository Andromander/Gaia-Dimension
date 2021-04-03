package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.GaiaArmorMaterials;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class GaiaChampArmorItem extends BasicGaiaArmorItem {

    public GaiaChampArmorItem(EquipmentSlotType slot) {
        super(GaiaArmorMaterials.GAIA_CHAMP, slot);
    }

    //TODO: Half damage from Corrupt and Non-Gaian mobs

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("champion_armor.tooltip"));
    }
}
