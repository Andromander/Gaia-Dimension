package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemAxe;

public class GDGenericAxe extends ItemAxe implements ModelRegisterCallback {

    public GDGenericAxe(ToolMaterial material, float damage, float speed) {
        super(material, damage, speed);

        setCreativeTab(GDTabs.tabTool);
        setMaxStackSize(1);
    }
}
