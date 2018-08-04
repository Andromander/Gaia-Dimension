package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemSword;

public class GDGenericSword extends ItemSword implements ModelRegisterCallback {

    public GDGenericSword(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
        this.setMaxStackSize(1);
    }
}
