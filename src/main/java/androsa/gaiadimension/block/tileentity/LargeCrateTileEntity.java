package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.container.LargeCrateContainer;
import androsa.gaiadimension.registry.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import java.util.stream.IntStream;

public class LargeCrateTileEntity extends LockableLootTileEntity implements ISidedInventory {
    private static final int[] SLOTS = IntStream.range(0, 54).toArray();
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);

    public LargeCrateTileEntity() {
        super(ModTileEntities.LARGE_CRATE.get());
    }

    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public void openInventory(PlayerEntity player) {
    }

    @Override
    public void closeInventory(PlayerEntity player) {
    }

    @Override
    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("gaiadimension.container.large_crate");
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.loadFromNbt(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        return this.saveToNbt(compound);
    }

    public void loadFromNbt(CompoundNBT compound) {
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound) && compound.contains("Items", 9)) {
            ItemStackHelper.loadAllItems(compound, this.items);
        }
    }

    public CompoundNBT saveToNbt(CompoundNBT compound) {
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items, false);
        }

        return compound;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
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
    public int[] getSlotsForFace(Direction side) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return !(Block.getBlockFromItem(itemStackIn.getItem()) instanceof LargeCrateBlock);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory) {
        return new LargeCrateContainer(id, playerInventory, this);
    }

    @Override
    protected IItemHandler createUnSidedHandler() {
        return new SidedInvWrapper(this, Direction.UP);
    }
}
