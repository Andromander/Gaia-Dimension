package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class BasicGaiaAxeItem extends AxeItem {

    public BasicGaiaAxeItem(IItemTier material) {
        super(material, 6F, -3.2F, new Properties().tab(GaiaItemGroups.GAIA_TOOLS));
    }
}
