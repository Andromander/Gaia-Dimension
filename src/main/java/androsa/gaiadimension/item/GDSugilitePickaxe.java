package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemPickaxe;

public class GDSugilitePickaxe extends ItemPickaxe implements ModelRegisterCallback {

    public GDSugilitePickaxe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
