package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.registry.GaiaTags;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class LargeCrateSlot extends Slot {

    public LargeCrateSlot(IInventory inventory, int slotIndexIn, int xPosition, int yPosition) {
        super(inventory, slotIndexIn, xPosition, yPosition);
    }

    public boolean mayPlace(ItemStack stack) {
        return GaiaTags.Items.MEGA_STORAGE_BLACKLIST.contains(stack.getItem());
    }
}
