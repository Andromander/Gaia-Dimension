package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemAxe;

public class GDCarnelianAxe extends ItemAxe implements ModelRegisterCallback {

    public GDCarnelianAxe(ToolMaterial material) {
        super(material, 6F, -3.2F);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
