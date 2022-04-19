package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.registry.GaiaTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LargeCrateSlot extends Slot {

    public LargeCrateSlot(Container inventory, int slotIndexIn, int xPosition, int yPosition) {
        super(inventory, slotIndexIn, xPosition, yPosition);
    }

    public boolean mayPlace(ItemStack stack) {
        return !stack.is(GaiaTags.Items.MEGA_STORAGE_BLACKLIST);
    }
}
