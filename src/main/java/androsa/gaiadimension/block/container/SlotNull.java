package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import androsa.gaiadimension.block.tileentity.TileEntityRestructurer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotNull extends Slot {

    public SlotNull(IInventory iInventoryIn, int index, int xPosition, int yPosition) {
        super(iInventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileEntityPurifier.getThirdFuelBurnTime(stack) > 0;
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }
}
