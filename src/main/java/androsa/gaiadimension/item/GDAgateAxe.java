package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class GDAgateAxe extends ItemAxe implements ModelRegisterCallback {

    public GDAgateAxe(Item.ToolMaterial material) {
        super(material, 5F, -3.2F);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
