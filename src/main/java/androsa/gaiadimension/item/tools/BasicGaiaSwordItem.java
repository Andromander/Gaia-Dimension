package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class BasicGaiaSwordItem extends SwordItem {

    public BasicGaiaSwordItem(IItemTier material) {
        super(material, 3, -2.4F, new Properties().maxStackSize(1).defaultMaxDamage(material.getMaxUses()).group(ModItemGroups.GAIA_TOOLS));
    }
}
