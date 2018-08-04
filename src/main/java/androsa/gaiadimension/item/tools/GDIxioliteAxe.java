package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class GDIxioliteAxe extends ItemAxe implements ModelRegisterCallback {

    public GDIxioliteAxe(Item.ToolMaterial material) {
        super(material, 6F, -3.2F); //TODO: Change damage
        this.setCreativeTab(GDTabs.tabTool);
        this.setMaxStackSize(1);
    }
}