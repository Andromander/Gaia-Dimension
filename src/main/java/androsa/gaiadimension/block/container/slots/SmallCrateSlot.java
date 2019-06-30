package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.SmallCrateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SmallCrateSlot extends Slot {

    public SmallCrateSlot(IInventory inventory, int slotIndexIn, int xPosition, int yPosition) {
        super(inventory, slotIndexIn, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack) {
        return !(Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock
                || Block.getBlockFromItem(stack.getItem()) instanceof SmallCrateBlock
                || Block.getBlockFromItem(stack.getItem()) instanceof LargeCrateBlock);
    }
}
