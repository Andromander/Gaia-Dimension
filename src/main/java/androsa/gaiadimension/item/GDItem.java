package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public class GDItem extends Item implements ModelRegisterCallback {

    public GDItem() {
        this.setCreativeTab(GDTabs.tabItem);
    }
}
