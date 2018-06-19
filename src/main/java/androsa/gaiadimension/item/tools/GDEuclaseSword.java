package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemSword;

public class GDEuclaseSword extends ItemSword implements ModelRegisterCallback {

    public GDEuclaseSword(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
