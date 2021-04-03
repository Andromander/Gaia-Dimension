package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

public class BasicGaiaShovelItem extends ShovelItem {

    public BasicGaiaShovelItem(IItemTier material) {
        super(material, 1.5F, -3.0F, new Properties().tab(GaiaItemGroups.GAIA_TOOLS));
    }
}
