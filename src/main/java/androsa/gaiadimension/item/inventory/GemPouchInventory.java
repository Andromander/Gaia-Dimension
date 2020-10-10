package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GemPouchInventory implements IInventory, INamedContainerProvider {

    private final ItemStack invItem;
    private NonNullList<ItemStack> inventory;

    public GemPouchInventory(ItemStack stack) {
        this.invItem = stack;
        this.inventory = NonNullList.withSize(20, ItemStack.EMPTY);
        if (!stack.hasTag())
            stack.setTag(new CompoundNBT());
        this.readFromNBT(stack.getTag());
    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if(!stack.isEmpty()) {
            if(stack.getCount() > count) {
                stack = stack.split(count);
                this.markDirty();
            } else {
                this.setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory.set(index, stack);

        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 16;
    }

    @Override
    public void markDirty() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (!getStackInSlot(i).isEmpty() && getStackInSlot(i).getCount() == 0) {
                this.inventory.set(i, ItemStack.EMPTY);
            }
        }
        this.writeToNBT(this.invItem.getTag());
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void openInventory(PlayerEntity player) { }

    @Override
    public void closeInventory(PlayerEntity player) { }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        Item item = stack.getItem();
        return item != ModItems.gemstone_pouch || Block.getBlockFromItem(item) != ModBlocks.crude_storage_crate.get() || Block.getBlockFromItem(item) != ModBlocks.mega_storage_crate.get();
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSizeInventory(); i++) {
            this.setInventorySlotContents(1, ItemStack.EMPTY);
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gaiadimension.container.gemstone_pouch");
    }

    public void readFromNBT(CompoundNBT tag) {
        inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, inventory);
    }

    public void writeToNBT(CompoundNBT tag) {
        ItemStackHelper.saveAllItems(tag, inventory, true);
    }

    @Override
    public Container createMenu(int menuId, PlayerInventory playerInv, PlayerEntity player) {
        return new GemPouchContainer(menuId, playerInv, this);
    }
}
