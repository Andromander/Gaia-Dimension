package androsa.gaiadimension.block.menu;

import androsa.gaiadimension.block.menu.slots.LargeCrateSlot;
import androsa.gaiadimension.registry.registration.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LargeCrateMenu extends AbstractContainerMenu {

    private final Container largeCrate;

    public LargeCrateMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(54));
    }

    public LargeCrateMenu(int id, Inventory playerInventoryIn, Container inventory) {
        super(ModMenus.LARGE_CRATE.get(), id);
        checkContainerSize(inventory, 27);
        largeCrate = inventory;
        inventory.startOpen(playerInventoryIn.player);

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
    public boolean stillValid(Player playerIn) {
        return this.largeCrate.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.largeCrate.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.largeCrate.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.largeCrate.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        this.largeCrate.stopOpen(playerIn);
    }
}
