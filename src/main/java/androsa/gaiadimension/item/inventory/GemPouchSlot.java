package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.GaiaTags;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GemPouchSlot extends Slot {

    public GemPouchSlot(IInventory inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return GaiaTags.Items.GEM_POUCH_ITEMS.contains(stack.getItem());
    }
}
