package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.tileentity.TileEntityLargeCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLargeCrate extends Container {

    private TileEntityLargeCrate largeCrate;

    public ContainerLargeCrate(InventoryPlayer playerInventoryIn, TileEntityLargeCrate crate) {
        largeCrate = crate;

        for (int rowCount = 0; rowCount < 6; ++rowCount) {
            for (int colCount = 0; colCount < 9; ++colCount) {
                this.addSlotToContainer(new SlotLargeCrate(crate, colCount + rowCount * 9, 8 + colCount * 18, 18 + rowCount * 18));
            }
        }

        for (int rowCount = 0; rowCount < 3; ++rowCount) {
            for (int colCount = 0; colCount < 9; ++colCount) {
                this.addSlotToContainer(new Slot(playerInventoryIn, colCount + rowCount * 9 + 9, 8 + colCount * 18, 140 + rowCount * 18));
            }
        }

        for (int colCount = 0; colCount < 9; ++colCount) {
            this.addSlotToContainer(new Slot(playerInventoryIn, colCount, 8 + colCount * 18, 198));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.largeCrate.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.largeCrate.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.largeCrate.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.largeCrate.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        this.largeCrate.closeInventory(playerIn);
    }
}
