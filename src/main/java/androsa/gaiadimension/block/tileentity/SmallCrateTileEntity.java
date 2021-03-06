package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.SmallCrateBlock;
import androsa.gaiadimension.block.container.SmallCrateContainer;
import androsa.gaiadimension.registry.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

public class SmallCrateTileEntity extends LockableLootTileEntity implements ISidedInventory {
    private static final int[] SLOTS = IntStream.range(0, 27).toArray();
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    public SmallCrateTileEntity() {
        super(ModTileEntities.SMALL_CRATE.get());
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public void startOpen(PlayerEntity player) {
    }

    @Override
    public void stopOpen(PlayerEntity player) {
    }

    @Override
    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("gaiadimension.container.small_crate");
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.loadFromNbt(compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        return this.saveToNbt(compound);
    }

    public void loadFromNbt(CompoundNBT compound) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(compound) && compound.contains("Items", 9)) {
            ItemStackHelper.loadAllItems(compound, this.items);
        }
    }

    public CompoundNBT saveToNbt(CompoundNBT compound) {
        if (!this.trySaveLootTable(compound)) {
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
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, Direction direction) {
        return !(Block.byItem(itemStackIn.getItem()) instanceof SmallCrateBlock);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory) {
        return new SmallCrateContainer(id, playerInventory, this);
    }

    @Override
    protected IItemHandler createUnSidedHandler() {
        return new SidedInvWrapper(this, Direction.UP);
    }
}
