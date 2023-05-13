package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GemPouchSlot extends Slot {

    public GemPouchSlot(Container inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(GaiaTags.Items.GEM_POUCH_ITEMS);
    }
}
