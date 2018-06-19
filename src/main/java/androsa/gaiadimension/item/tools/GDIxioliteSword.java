package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemSword;

public class GDIxioliteSword extends ItemSword implements ModelRegisterCallback {

    public GDIxioliteSword(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
