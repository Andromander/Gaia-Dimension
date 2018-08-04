package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class GDChalcedonyAxe extends ItemAxe implements ModelRegisterCallback {

    public GDChalcedonyAxe(Item.ToolMaterial material) {
        super(material, 5F, -3.2F);
        this.setCreativeTab(GDTabs.tabTool);
        this.setMaxStackSize(1);
    }
}
