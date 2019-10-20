package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.container.slots.GaiaFurnaceSlot;
import androsa.gaiadimension.registry.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GaiaStoneFurnaceContainer extends Container {

    private final IInventory tileFurnace;
    private final IIntArray slotsArray;
    private final World world;
    private final IRecipeType<FurnaceRecipe> recipeType = IRecipeType.SMELTING;

    public GaiaStoneFurnaceContainer(int id, PlayerInventory playerinv) {
        this(id, playerinv, new Inventory(3), new IntArray(4));
    }

    public GaiaStoneFurnaceContainer(int id, PlayerInventory playerinv, IInventory inventory, IIntArray array) {
        super(ModContainers.GAIA_STONE_FURNACE.get(), id);
        assertInventorySize(inventory, 3);
        assertIntArraySize(array, 4);
        this.tileFurnace = inventory;
        this.slotsArray = array;
        this.world = playerinv.player.world;
        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new GaiaFurnaceSlot(inventory, 1, 56, 53));
        this.addSlot(new FurnaceResultSlot(playerinv.player, inventory, 2, 116, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerinv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerinv, k, 8 + k * 18, 142));
        }

        this.trackIntArray(array);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return tileFurnace.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.isRecipePresent(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private boolean isRecipePresent(ItemStack stack) {
        return this.world.getRecipeManager().getRecipe(this.recipeType, new Inventory(stack), this.world).isPresent();
    }

    public static boolean isFuel(ItemStack stack) {
        return AbstractFurnaceTileEntity.isFuel(stack);
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        int i = this.slotsArray.get(2);
        int j = this.slotsArray.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int i = this.slotsArray.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.slotsArray.get(0) * 13 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.slotsArray.get(0) > 0;
    }
}
