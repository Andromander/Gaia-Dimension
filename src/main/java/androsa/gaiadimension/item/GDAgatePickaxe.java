package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemPickaxe;

public class GDAgatePickaxe extends ItemPickaxe implements ModelRegisterCallback {

    public GDAgatePickaxe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
