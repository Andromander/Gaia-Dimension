package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class GDSugiliteSword extends ItemSword implements ModelRegisterCallback {

    public GDSugiliteSword(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
    }
}
