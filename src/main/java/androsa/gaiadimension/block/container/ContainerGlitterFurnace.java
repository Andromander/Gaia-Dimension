package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.tileentity.TileEntityGlitterFurnace;
import androsa.gaiadimension.recipe.GlitterFurnaceRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerGlitterFurnace extends Container {

    private TileEntityGlitterFurnace tileGlitterFurnace;
    private int lastCookTime;
    private int lastCookTime2;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerGlitterFurnace(InventoryPlayer invPlayer, TileEntityGlitterFurnace glitterFurnace) {
        tileGlitterFurnace = glitterFurnace;
        addSlotToContainer(new Slot(glitterFurnace, 0, 56, 17));
        addSlotToContainer(new Slot(glitterFurnace, 1, 56, 53));
        addSlotToContainer(new SlotGlitter(invPlayer.player, glitterFurnace, 2, 116, 35));
        addSlotToContainer(new SlotGlitter(invPlayer.player, glitterFurnace, 3, 136, 35));
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
        listener.sendWindowProperty(this, 0, tileGlitterFurnace.glitterFormTime);
        listener.sendWindowProperty(this, 1, tileGlitterFurnace.glitterTimer);
        listener.sendWindowProperty(this, 2, tileGlitterFurnace.currentItemRefactorTime);
        listener.sendWindowProperty(this, 3, tileGlitterFurnace.glitterFormTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < listeners.size(); ++i) {
            IContainerListener icrafting = listeners.get(i);

            if (lastCookTime != tileGlitterFurnace.glitterFormTime)
                icrafting.sendWindowProperty(this, 0, tileGlitterFurnace.glitterFormTime);

            if (lastBurnTime != tileGlitterFurnace.glitterTimer)
                icrafting.sendWindowProperty(this, 1, tileGlitterFurnace.glitterTimer);

            if (lastItemBurnTime != tileGlitterFurnace.currentItemRefactorTime)
                icrafting.sendWindowProperty(this, 2, tileGlitterFurnace.currentItemRefactorTime);
            if (lastCookTime2 != tileGlitterFurnace.glitterFormTime)
                icrafting.sendWindowProperty(this, 3, tileGlitterFurnace.glitterFormTime);
        }

        lastCookTime = tileGlitterFurnace.glitterFormTime;
        lastCookTime2 = tileGlitterFurnace.glitterFormTime;
        lastBurnTime = tileGlitterFurnace.glitterTimer;
        lastItemBurnTime = tileGlitterFurnace.currentItemRefactorTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0)
            tileGlitterFurnace.glitterFormTime = par2;

        if (par1 == 1)
            tileGlitterFurnace.glitterTimer = par2;

        if (par1 == 2)
            tileGlitterFurnace.currentItemRefactorTime = par2;

        if (par1 == 3)
            tileGlitterFurnace.glitterFormTime = par2;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileGlitterFurnace.isUsableByPlayer(player);
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
                if (GlitterFurnaceRecipes.instance().getRefactoringResult(itemstack1) != null) {
                    if (!mergeItemStack(itemstack1, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (TileEntityGlitterFurnace.isItemFuel(itemstack1)) {
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
