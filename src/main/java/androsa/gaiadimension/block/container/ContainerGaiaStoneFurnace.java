package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.tileentity.TileEntityGaiaStoneFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerGaiaStoneFurnace extends Container {

    private TileEntityGaiaStoneFurnace tileFurnace;
    private int lastCookTime;
    private int lastCookTime2;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerGaiaStoneFurnace(InventoryPlayer invPlayer, TileEntityGaiaStoneFurnace gaiaStoneFurnace) {
        this.tileFurnace = gaiaStoneFurnace;
        this.addSlotToContainer(new Slot(gaiaStoneFurnace, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(gaiaStoneFurnace, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(invPlayer.player, gaiaStoneFurnace, 2, 116, 35));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(invPlayer, k, 8 + k * 18, 142));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, tileFurnace.cookTime);
        listener.sendWindowProperty(this, 1, tileFurnace.furnaceTimer);
        listener.sendWindowProperty(this, 2, tileFurnace.currentItemSmeltTime);
        listener.sendWindowProperty(this, 3, tileFurnace.cookTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < listeners.size(); ++i) {
            IContainerListener icrafting = listeners.get(i);

            if (lastCookTime != tileFurnace.cookTime)
                icrafting.sendWindowProperty(this, 0, tileFurnace.cookTime);
            if (lastBurnTime != tileFurnace.furnaceTimer)
                icrafting.sendWindowProperty(this, 1, tileFurnace.furnaceTimer);
            if (lastItemBurnTime != tileFurnace.currentItemSmeltTime)
                icrafting.sendWindowProperty(this, 2, tileFurnace.currentItemSmeltTime);
            if (lastCookTime2 != tileFurnace.cookTime)
                icrafting.sendWindowProperty(this, 3, tileFurnace.cookTime);
        }

        lastCookTime = tileFurnace.cookTime;
        lastCookTime2 = tileFurnace.cookTime;
        lastBurnTime = tileFurnace.furnaceTimer;
        lastItemBurnTime = tileFurnace.currentItemSmeltTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        if (id == 0)
            tileFurnace.cookTime = data;
        if (id == 1)
            tileFurnace.furnaceTimer = data;
        if (id == 2)
            tileFurnace.currentItemSmeltTime = data;
        if (id == 3)
            tileFurnace.cookTime = data;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileFurnace.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
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
                if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty()) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (TileEntityGaiaStoneFurnace.isItemFuel(itemstack1)) {
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
}
