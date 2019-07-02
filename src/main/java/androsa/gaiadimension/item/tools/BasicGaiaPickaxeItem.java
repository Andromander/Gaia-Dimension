package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

public class BasicGaiaPickaxeItem extends PickaxeItem {

    public BasicGaiaPickaxeItem(IItemTier material) {
        super(material, 1, -2.8F, new Properties().maxStackSize(1).defaultMaxDamage(material.getMaxUses()).group(ModItemGroups.GAIA_TOOLS));
    }
}
