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
        addSlotToContainer(new Slot(purifier, 0, 80, 17));       //Input
        addSlotToContainer(new SlotGold(purifier, 1, 59, 63));   //Fuel 1
        addSlotToContainer(new SlotShine(purifier, 2, 101, 63)); //Fuel 2
        addSlotToContainer(new SlotNull(purifier, 3, 80, 35));   //Fuel 3
        addSlotToContainer(new SlotPurify(invPlayer.player, purifier, 4, 46, 91));  //Output 1
        addSlotToContainer(new SlotPurify(invPlayer.player, purifier, 5, 114, 91)); //Output 2
        int i;

        //Player Inventory
        for (i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 124 + i * 18));
        //Player Hotbar
        for (i = 0; i < 9; ++i)
            addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 182));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, tilePurifier.clearTime);
        listener.sendWindowProperty(this, 1, tilePurifier.purifyingTime);
        listener.sendWindowProperty(this, 2, tilePurifier.currentPurifyingTime);
        listener.sendWindowProperty(this, 3, tilePurifier.clearTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener icrafting : listeners) {
            if (lastCookTime != tilePurifier.clearTime)
                icrafting.sendWindowProperty(this, 0, tilePurifier.clearTime);
            if (lastBurnTime != tilePurifier.purifyingTime)
                icrafting.sendWindowProperty(this, 1, tilePurifier.purifyingTime);
            if (lastItemBurnTime != tilePurifier.currentPurifyingTime)
                icrafting.sendWindowProperty(this, 2, tilePurifier.currentPurifyingTime);
            if (lastCookTime2 != tilePurifier.clearTime)
                icrafting.sendWindowProperty(this, 3, tilePurifier.clearTime);
        }

        lastCookTime = tilePurifier.clearTime;
        lastCookTime2 = tilePurifier.clearTime;
        lastBurnTime = tilePurifier.purifyingTime;
        lastItemBurnTime = tilePurifier.currentPurifyingTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int index) {
        if (id == 0)
            tilePurifier.clearTime = index;

        if (id == 1)
            tilePurifier.purifyingTime = index;

        if (id == 2)
            tilePurifier.currentPurifyingTime = index;

        if (id == 3)
            tilePurifier.clearTime = index;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tilePurifier.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            if (index == 4 || index == 5) {
                if (!mergeItemStack(slotStack, 3, 39, true))
                    return ItemStack.EMPTY;

                slot.onSlotChange(slotStack, itemstack);
            } else if (index != 3 && index != 2 && index != 1 && index != 0) {
                if (PurifierRecipes.instance().getPurifyingResult(slotStack) != null) {
                    if (!mergeItemStack(slotStack, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (TileEntityPurifier.isItemFuel(slotStack)) {
                    if (!mergeItemStack(slotStack, 1, 4, false))
                        return ItemStack.EMPTY;
                } else if (index >= 4 && index < 30) {
                    if (!mergeItemStack(slotStack, 30, 39, false))
                        return ItemStack.EMPTY;
                } else if (index >= 30 && index < 39 && !mergeItemStack(slotStack, 3, 30, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(slotStack, 4, 39, false))
                return ItemStack.EMPTY;

            if (slotStack.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (slotStack.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, slotStack);
        }

        return itemstack;
    }
}

