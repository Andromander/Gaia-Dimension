package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.registry.GaiaTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SmallCrateSlot extends Slot {

    public SmallCrateSlot(Container inventory, int slotIndexIn, int xPosition, int yPosition) {
        super(inventory, slotIndexIn, xPosition, yPosition);
    }

    public boolean mayPlace(ItemStack stack) {
        return !GaiaTags.Items.CRUDE_STORAGE_BLACKLIST.contains(stack.getItem());
    }
}
