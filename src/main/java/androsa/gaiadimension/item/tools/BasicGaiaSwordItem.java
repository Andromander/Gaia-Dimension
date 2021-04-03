package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class BasicGaiaSwordItem extends SwordItem {

    public BasicGaiaSwordItem(IItemTier material) {
        super(material, 3, -2.4F, new Properties().tab(GaiaItemGroups.GAIA_TOOLS));
    }
}
