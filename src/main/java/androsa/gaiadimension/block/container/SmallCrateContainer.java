package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.container.slots.SmallCrateSlot;
import androsa.gaiadimension.registry.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SmallCrateContainer extends Container {

    private final IInventory smallCrate;

    public SmallCrateContainer(int id, PlayerInventory inventory) {
        this(id, inventory, new Inventory(27));
    }

    public SmallCrateContainer(int id, PlayerInventory playerInventoryIn, IInventory inventory) {
        super(ModContainers.SMALL_CRATE, id);
        assertInventorySize(inventory, 27);
        smallCrate = inventory;
        inventory.openInventory(playerInventoryIn.player);

        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new SmallCrateSlot(inventory, l + k * 9, 8 + l * 18, 18 + k * 18));
            }
        }

        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(playerInventoryIn, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }

        for (int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(playerInventoryIn, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.smallCrate.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.smallCrate.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.smallCrate.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.smallCrate.getSizeInventory(), false)) {
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
        this.smallCrate.closeInventory(playerIn);
    }
}
