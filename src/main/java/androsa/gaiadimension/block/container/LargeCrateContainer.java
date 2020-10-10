package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.container.slots.LargeCrateSlot;
import androsa.gaiadimension.registry.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class LargeCrateContainer extends Container {

    private final IInventory largeCrate;

    public LargeCrateContainer(int id, PlayerInventory inventory) {
        this(id, inventory, new Inventory(54));
    }

    public LargeCrateContainer(int id, PlayerInventory playerInventoryIn, IInventory inventory) {
        super(ModContainers.LARGE_CRATE, id);
        assertInventorySize(inventory, 27);
        largeCrate = inventory;
        inventory.openInventory(playerInventoryIn.player);

        for (int rowCount = 0; rowCount < 6; ++rowCount) {
            for (int colCount = 0; colCount < 9; ++colCount) {
                this.addSlot(new LargeCrateSlot(inventory, colCount + rowCount * 9, 8 + colCount * 18, 18 + rowCount * 18));
            }
        }

        for (int rowCount = 0; rowCount < 3; ++rowCount) {
            for (int colCount = 0; colCount < 9; ++colCount) {
                this.addSlot(new Slot(playerInventoryIn, colCount + rowCount * 9 + 9, 8 + colCount * 18, 140 + rowCount * 18));
            }
        }

        for (int colCount = 0; colCount < 9; ++colCount) {
            this.addSlot(new Slot(playerInventoryIn, colCount, 8 + colCount * 18, 198));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.largeCrate.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
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
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.largeCrate.closeInventory(playerIn);
    }
}
