package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemSpade;

public class GDIxioliteShovel extends ItemSpade implements ModelRegisterCallback {

    public GDIxioliteShovel(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
