package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemAxe;

public class GDBenitoiteAxe extends ItemAxe implements ModelRegisterCallback {

    public GDBenitoiteAxe(ToolMaterial material) {
        super(material, 5F, -3.2F);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
