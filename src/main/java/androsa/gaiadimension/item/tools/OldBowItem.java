package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.item.HoningEquipment;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class OldBowItem extends BowItem implements HoningEquipment {

    public OldBowItem(Properties props) {
        super(props);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == ModItems.agate_arrow.get();
    }
}
