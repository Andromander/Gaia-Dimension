package androsa.gaiadimension.item.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GemPouchContainer extends Container {

    private final IInventory inventory;

    public GemPouchContainer(int id, PlayerInventory invPlayer, IInventory inventory) {
        super(ModContainers.GEM_POUCH, id);
        this.inventory = inventory;
        //Pouch
        for (int row = 0; row < 4; ++row) {
            for (int column = 0; column < 5; ++column) {
                this.addSlot(new GemPouchSlot(inventory, column + row * 5, 43 + column * 18, 18 + row * 18));
            }
        }

        //Player Inventory
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(invPlayer, k1 + i1 * 9 + 9, 8 + k1 * 18, 100 + i1 * 18));
            }
        }

        //Player Hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(invPlayer, k, 8 + k * 18, 158));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.inventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
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
        this.inventory.closeInventory(playerIn);
    }
}
