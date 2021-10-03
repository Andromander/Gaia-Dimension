package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.GaiaTags;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GemPouchInventory implements Container, MenuProvider {

    private final ItemStack invItem;
    private NonNullList<ItemStack> inventory;

    public GemPouchInventory(ItemStack stack) {
        this.invItem = stack;
        this.inventory = NonNullList.withSize(20, ItemStack.EMPTY);
        if (!stack.hasTag())
            stack.setTag(new CompoundTag());
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
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void startOpen(Player player) { }

    @Override
    public void stopOpen(Player player) { }

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
    public Component getDisplayName() {
        return new TranslatableComponent("gaiadimension.container.gemstone_pouch");
    }

    public void readFromNBT(CompoundTag tag) {
        inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, inventory);
    }

    public void writeToNBT(CompoundTag tag) {
        ContainerHelper.saveAllItems(tag, inventory, true);
    }

    @Override
    public AbstractContainerMenu createMenu(int menuId, Inventory playerInv, Player player) {
        return new GemPouchContainer(menuId, playerInv, this);
    }
}
