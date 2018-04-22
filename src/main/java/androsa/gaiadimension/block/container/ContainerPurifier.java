package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import androsa.gaiadimension.recipe.PurifierRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerPurifier extends Container {

    private TileEntityPurifier tilePurifier;
    private int lastCookTime;
    private int lastCookTime2;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerPurifier(InventoryPlayer invPlayer, TileEntityPurifier purifier) {
        tilePurifier = purifier;
        addSlotToContainer(new Slot(purifier, 0, 56, 17));
        addSlotToContainer(new Slot(purifier, 1, 56, 53));
        addSlotToContainer(new SlotPurify(invPlayer.player, purifier, 2, 116, 35));
        addSlotToContainer(new SlotPurify(invPlayer.player, purifier, 3, 136, 35));
        int i;

        for (i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (i = 0; i < 9; ++i)
            addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, tilePurifier.purifyClearTime);
        listener.sendWindowProperty(this, 1, tilePurifier.purifyTimer);
        listener.sendWindowProperty(this, 2, tilePurifier.currentItemPurifyTime);
        listener.sendWindowProperty(this, 3, tilePurifier.purifyClearTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < listeners.size(); ++i) {
            IContainerListener icrafting = listeners.get(i);

            if (lastCookTime != tilePurifier.purifyClearTime)
                icrafting.sendWindowProperty(this, 0, tilePurifier.purifyClearTime);

            if (lastBurnTime != tilePurifier.purifyTimer)
                icrafting.sendWindowProperty(this, 1, tilePurifier.purifyTimer);

            if (lastItemBurnTime != tilePurifier.currentItemPurifyTime)
                icrafting.sendWindowProperty(this, 2, tilePurifier.currentItemPurifyTime);
            if (lastCookTime2 != tilePurifier.purifyClearTime)
                icrafting.sendWindowProperty(this, 3, tilePurifier.purifyClearTime);
        }

        lastCookTime = tilePurifier.purifyClearTime;
        lastCookTime2 = tilePurifier.purifyClearTime;
        lastBurnTime = tilePurifier.purifyTimer;
        lastItemBurnTime = tilePurifier.currentItemPurifyTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0)
            tilePurifier.purifyClearTime = par2;

        if (par1 == 1)
            tilePurifier.purifyTimer = par2;

        if (par1 == 2)
            tilePurifier.currentItemPurifyTime = par2;

        if (par1 == 3)
            tilePurifier.purifyClearTime = par2;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tilePurifier.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!mergeItemStack(itemstack1, 3, 39, true))
                    return ItemStack.EMPTY;

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 1 && par2 != 0) {
                if (PurifierRecipes.instance().getPurifyingResult(itemstack1) != null) {
                    if (!mergeItemStack(itemstack1, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (TileEntityPurifier.isItemFuel(itemstack1)) {
                    if (!mergeItemStack(itemstack1, 1, 2, false))
                        return ItemStack.EMPTY;
                } else if (par2 >= 3 && par2 < 30) {
                    if (!mergeItemStack(itemstack1, 30, 39, false))
                        return ItemStack.EMPTY;
                } else if (par2 >= 30 && par2 < 39 && !mergeItemStack(itemstack1, 3, 30, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(itemstack1, 3, 39, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}

