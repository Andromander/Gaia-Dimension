package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class InventoryGemPouch implements IInventory {

    private final ItemStack invItem;
    private NonNullList<ItemStack> inventory;
    private String name;

    public InventoryGemPouch(ItemStack stack, String name) {
        this.invItem = stack;
        this.name = name;
        this.inventory = NonNullList.withSize(20, ItemStack.EMPTY);
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        this.readFromNBT(stack.getTagCompound());
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
                stack = stack.splitStack(count);
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
        this.writeToNBT(this.invItem.getTagCompound());
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) { }

    @Override
    public void closeInventory(EntityPlayer player) { }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        Item item = stack.getItem();
        return item != GDItems.gemstone_pouch || item != Item.getItemFromBlock(GDBlocks.crude_storage_crate) || item != Item.getItemFromBlock(GDBlocks.mega_storage_crate);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) { }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSizeInventory(); i++) {
            this.setInventorySlotContents(1, ItemStack.EMPTY);
        }
    }

    @Override
    public String getName() {
        return hasCustomName() ? name : "container.gaiadimension.gemstone_pouch";
    }

    @Override
    public boolean hasCustomName() {
        return name != null && name.length() > 0;
    }

    @Override
    public ITextComponent getDisplayName() {
        return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName());
    }

    public void readFromNBT(NBTTagCompound tag) {
        inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, inventory);
    }

    public void writeToNBT(NBTTagCompound tag) {
        ItemStackHelper.saveAllItems(tag, inventory, true);
    }
}
