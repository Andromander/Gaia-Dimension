package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class OldBowItem extends BowItem {

    public OldBowItem(Properties props) {
        super(props);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == ModItems.agate_arrow.get();
    }
}
