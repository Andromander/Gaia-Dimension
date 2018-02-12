package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public class GDGroundGem extends Item implements ModelRegisterCallback {

    public GDGroundGem() {
        this.setCreativeTab(GDTabs.tabItem);
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(ItemStack par1ItemStack) {
        return TextFormatting.GRAY + super.getItemStackDisplayName(par1ItemStack);
    }
}
