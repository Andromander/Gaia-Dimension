package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.registry.GaiaTags;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SmallCrateSlot extends Slot {

    public SmallCrateSlot(IInventory inventory, int slotIndexIn, int xPosition, int yPosition) {
        super(inventory, slotIndexIn, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack) {
        return GaiaTags.Items.CRUDE_STORAGE_BLACKLIST.contains(stack.getItem());
    }
}
