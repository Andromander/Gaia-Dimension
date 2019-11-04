package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.block.tileentity.RestructurerTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GoldSlot extends Slot {

    public GoldSlot(IInventory iInventoryIn, int index, int xPosition, int yPosition) {
        super(iInventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return RestructurerTileEntity.getFuelBurnTime().getOrDefault(stack.getItem(), 0) > 0;
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }
}
