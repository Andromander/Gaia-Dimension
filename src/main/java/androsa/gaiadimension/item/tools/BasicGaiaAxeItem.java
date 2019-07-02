package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.ModItemGroups;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class BasicGaiaAxeItem extends AxeItem {

    public BasicGaiaAxeItem(IItemTier material) {
        super(material, 6F, -3.2F, new Properties().maxStackSize(1).defaultMaxDamage(material.getMaxUses()).group(ModItemGroups.GAIA_TOOLS));
    }
}
