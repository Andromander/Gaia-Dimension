package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class OldBowItem extends BowItem {

    public OldBowItem() {
        super(new Properties().durability(425).tab(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == ModItems.agate_arrow.get();
    }
}
