package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.GDCrateLarge;
import androsa.gaiadimension.block.container.ContainerLargeCrate;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

public class TileEntityLargeCrate extends TileEntityLockableLoot implements ISidedInventory {
    private static final int[] SLOTS = new int[54];
    private NonNullList<ItemStack> items;
    private boolean hasBeenCleared;
    private boolean destroyedByCreativePlayer;

    public TileEntityLargeCrate() {
        this.items = NonNullList.withSize(54, ItemStack.EMPTY);
    }

    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerLargeCrate(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return "gaiadimension:large_crate";
    }

    @Override
    public String getName() {
        return hasCustomName() ? customName : "container.gaiadimension.large_crate";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.loadFromNbt(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return this.saveToNbt(compound);
    }

    public void loadFromNbt(NBTTagCompound compound) {
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound) && compound.hasKey("Items", 9)) {
            ItemStackHelper.loadAllItems(compound, this.items);
        }

        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound saveToNbt(NBTTagCompound compound) {
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items, false);
        }

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.customName);
        }

        if (!compound.hasKey("Lock") && this.isLocked()) {
            this.getLockCode().toNBT(compound);
        }

        return compound;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return !(Block.getBlockFromItem(itemStackIn.getItem()) instanceof GDCrateLarge);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public void clear() {
        this.hasBeenCleared = true;
        super.clear();
    }

    public boolean isCleared() {
        return this.hasBeenCleared;
    }

    public boolean isDestroyedByCreativePlayer() {
        return this.destroyedByCreativePlayer;
    }

    public void setDestroyedByCreativePlayer(boolean flag) {
        this.destroyedByCreativePlayer = flag;
    }

    public boolean shouldDrop() {
        return !this.isDestroyedByCreativePlayer() || !this.isEmpty() || this.hasCustomName() || this.lootTable != null;
    }

    static {
        for (int i = 0; i < SLOTS.length; SLOTS[i] = i++) {
            ;
        }
    }

    @Override
    protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
        return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.UP);
    }
}
