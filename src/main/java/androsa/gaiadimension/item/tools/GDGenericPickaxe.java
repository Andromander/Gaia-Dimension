package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemPickaxe;

public class GDGenericPickaxe extends ItemPickaxe implements ModelRegisterCallback {

    public GDGenericPickaxe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
