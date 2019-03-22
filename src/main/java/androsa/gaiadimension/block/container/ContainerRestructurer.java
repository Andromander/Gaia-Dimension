package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.tileentity.TileEntityRestructurer;
import androsa.gaiadimension.recipe.RestructurerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerRestructurer extends Container {

    private TileEntityRestructurer tileRestructurer;
    private int lastCookTime;
    private int lastCookTime2;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerRestructurer(InventoryPlayer invPlayer, TileEntityRestructurer restructurer) {
        tileRestructurer = restructurer;
        addSlotToContainer(new Slot(restructurer, 0, 80, 34));       //Input
        addSlotToContainer(new SlotGold(restructurer, 1, 51, 17));   //Fuel 1
        addSlotToContainer(new SlotShine(restructurer, 2, 109, 17)); //Fuel 2
        addSlotToContainer(new SlotGlitter(invPlayer.player, restructurer, 3, 70, 80)); //Output 1
        addSlotToContainer(new SlotGlitter(invPlayer.player, restructurer, 4, 90, 80)); //Output 2
        int i;

        //Player Inventory
        for (i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 115 + i * 18));
        //Player Hotbar
        for (i = 0; i < 9; ++i)
            addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 172));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, tileRestructurer.changeTime);
        listener.sendWindowProperty(this, 1, tileRestructurer.restructuringTime);
        listener.sendWindowProperty(this, 2, tileRestructurer.currentRestructuringTime);
        listener.sendWindowProperty(this, 3, tileRestructurer.changeTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener icrafting : listeners) {
            if (lastCookTime != tileRestructurer.changeTime)
                icrafting.sendWindowProperty(this, 0, tileRestructurer.changeTime);
            if (lastBurnTime != tileRestructurer.restructuringTime)
                icrafting.sendWindowProperty(this, 1, tileRestructurer.restructuringTime);
            if (lastItemBurnTime != tileRestructurer.currentRestructuringTime)
                icrafting.sendWindowProperty(this, 2, tileRestructurer.currentRestructuringTime);
            if (lastCookTime2 != tileRestructurer.changeTime)
                icrafting.sendWindowProperty(this, 3, tileRestructurer.changeTime);
        }

        lastCookTime = tileRestructurer.changeTime;
        lastCookTime2 = tileRestructurer.changeTime;
        lastBurnTime = tileRestructurer.restructuringTime;
        lastItemBurnTime = tileRestructurer.currentRestructuringTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int index) {
        if (id == 0)
            tileRestructurer.changeTime = index;

        if (id == 1)
            tileRestructurer.restructuringTime = index;

        if (id == 2)
            tileRestructurer.currentRestructuringTime = index;

        if (id == 3)
            tileRestructurer.changeTime = index;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileRestructurer.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            if (index == 3 || index == 4) {
                if (!mergeItemStack(slotStack, 5, 39, true))
                    return ItemStack.EMPTY;

                slot.onSlotChange(slotStack, itemstack);
            } else if (index != 2 && index != 1 && index != 0) {
                if (RestructurerRecipes.instance().getRefactoringResult(slotStack) != null) {
                    if (!mergeItemStack(slotStack, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (TileEntityRestructurer.isItemFuel(slotStack)) {
                    if (!mergeItemStack(slotStack, 1, 3, false))
                        return ItemStack.EMPTY;
                } else if (index >= 5 && index < 30) {
                    if (!mergeItemStack(slotStack, 30, 39, false))
                        return ItemStack.EMPTY;
                } else if (index >= 31 && index < 40 && !mergeItemStack(slotStack, 5, 30, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(slotStack, 5, 39, false))
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
