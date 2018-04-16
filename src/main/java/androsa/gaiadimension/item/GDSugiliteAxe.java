package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class GDSugiliteAxe extends ItemAxe implements ModelRegisterCallback {

    public GDSugiliteAxe(Item.ToolMaterial material) {
        super(material, 6F, -3.2F);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
