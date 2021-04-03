package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.GaiaTags;
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
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return inventory.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack stack = getItem(index);
        if(!stack.isEmpty()) {
            if(stack.getCount() > count) {
                stack = stack.split(count);
                this.setChanged();
            } else {
                this.setItem(index, ItemStack.EMPTY);
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = this.getItem(index);
        this.setItem(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        inventory.set(index, stack);

        if (!stack.isEmpty() && stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        this.setChanged();
    }

    @Override
    public int getMaxStackSize() {
        return 16;
    }

    @Override
    public void setChanged() {
        for (int i = 0; i < getContainerSize(); i++) {
            if (!getItem(i).isEmpty() && getItem(i).getCount() == 0) {
                this.inventory.set(i, ItemStack.EMPTY);
            }
        }
        this.writeToNBT(this.invItem.getTag());
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    @Override
    public void startOpen(PlayerEntity player) { }

    @Override
    public void stopOpen(PlayerEntity player) { }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        Item item = stack.getItem();
        return GaiaTags.Items.GEM_POUCH_ITEMS.contains(item);
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < getContainerSize(); i++) {
            this.setItem(1, ItemStack.EMPTY);
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gaiadimension.container.gemstone_pouch");
    }

    public void readFromNBT(CompoundNBT tag) {
        inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
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
