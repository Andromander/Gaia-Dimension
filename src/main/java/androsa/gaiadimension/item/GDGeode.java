package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.Item;

public class GDGeode extends Item implements ModelRegisterCallback {

    public GDGeode() {
        super();

        this.setCreativeTab(GDTabs.tabItem);
    }
}
