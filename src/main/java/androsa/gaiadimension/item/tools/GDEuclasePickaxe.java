package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemPickaxe;

public class GDEuclasePickaxe extends ItemPickaxe implements ModelRegisterCallback {

    public GDEuclasePickaxe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
