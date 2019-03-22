package androsa.gaiadimension.item.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGemPouch extends Container {

    private final InventoryGemPouch inventory;

    public ContainerGemPouch(InventoryPlayer invPlayer, InventoryGemPouch invGemPouch) {
        this.inventory = invGemPouch;

        //Pouch
        for (int row = 0; row < 4; ++row) {
            for (int column = 0; column < 5; ++column) {
                this.addSlotToContainer(new SlotGemPouch(invGemPouch, column + row * 5, 43 + column * 18, 18 + row * 18));
            }
        }

        //Player Inventory
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlotToContainer(new Slot(invPlayer, k1 + i1 * 9 + 9, 8 + k1 * 18, 100 + i1 * 18));
            }
        }

        //Player Hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(invPlayer, k, 8 + k * 18, 158));
        }
    }

    public InventoryGemPouch getItemInventory() {
        return this.inventory;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
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
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }
}
