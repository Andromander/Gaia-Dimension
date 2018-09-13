package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.GDCrateLarge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLargeCrate extends Slot {

    public SlotLargeCrate(IInventory inventory, int slotIndexIn, int xPosition, int yPosition) {
        super(inventory, slotIndexIn, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack) {
        return !(Block.getBlockFromItem(stack.getItem()) instanceof BlockShulkerBox
                || Block.getBlockFromItem(stack.getItem()) instanceof GDCrateLarge);
    }
}
