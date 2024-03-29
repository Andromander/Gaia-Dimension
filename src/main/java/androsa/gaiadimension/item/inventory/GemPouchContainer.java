package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.registration.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GemPouchContainer extends AbstractContainerMenu {

    private final Container inventory;

    public GemPouchContainer(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(20));
    }

    public GemPouchContainer(int id, Inventory invPlayer, Container inventory) {
        super(ModMenus.GEMSTONE_POUCH.get(), id);
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
    public boolean stillValid(Player playerIn) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.inventory.getContainerSize(), false)) {
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
        this.inventory.stopOpen(playerIn);
    }
}
