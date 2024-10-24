package androsa.gaiadimension.block.blockentity;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.menu.LargeCrateMenu;
import androsa.gaiadimension.registry.registration.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.stream.IntStream;

public class LargeCrateBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private static final int[] SLOTS = IntStream.range(0, 54).toArray();
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);

    public LargeCrateBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LARGE_CRATE.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public void startOpen(Player player) {
    }

    @Override
    public void stopOpen(Player player) {
    }

    @Override
    public Component getDefaultName() {
        return Component.translatable("gaiadimension.container.large_crate");
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.loadAdditional(compound, provider);
        this.loadFromNbt(compound, provider);
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.saveAdditional(compound, provider);
        this.saveToNbt(compound, provider);
    }

    public void loadFromNbt(CompoundTag compound, HolderLookup.Provider provider) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(compound) && compound.contains("Items", 9)) {
            ContainerHelper.loadAllItems(compound, this.items, provider);
        }
    }

    public CompoundTag saveToNbt(CompoundTag compound, HolderLookup.Provider provider) {
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.items, false, provider);
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
        return !(Block.byItem(itemStackIn.getItem()) instanceof LargeCrateBlock);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return new LargeCrateMenu(id, playerInventory, this);
    }
}
