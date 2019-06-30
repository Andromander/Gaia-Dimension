package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.container.slots.GoldSlot;
import androsa.gaiadimension.block.container.slots.NullSlot;
import androsa.gaiadimension.block.container.slots.PurifyOutputSlot;
import androsa.gaiadimension.block.container.slots.ShineSlot;
import androsa.gaiadimension.block.tileentity.PurifierTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PurifierContainer extends Container {

    private final IInventory tilePurifier;
    private final IIntArray slotsArray;
    private final World world;
    private final IRecipeType<?> recipeType = ModRecipeTypes.PURIFYING;

    public PurifierContainer(int id, PlayerInventory invPlayer, IInventory purifier, IIntArray slots) {
        super(ModContainers.PURIFIER, id);
        assertInventorySize(purifier, 6);
        assertIntArraySize(slots, 4);
        tilePurifier = purifier;
        slotsArray = slots;
        world = invPlayer.player.world;
        addSlot(new Slot(purifier, 0, 80, 17));       //Input
        addSlot(new GoldSlot(purifier, 1, 59, 63));   //Fuel 1
        addSlot(new ShineSlot(purifier, 2, 101, 63)); //Fuel 2
        addSlot(new NullSlot(purifier, 3, 80, 35));   //Fuel 3
        addSlot(new PurifyOutputSlot(invPlayer.player, purifier, 4, 46, 91));  //Output 1
        addSlot(new PurifyOutputSlot(invPlayer.player, purifier, 5, 114, 91)); //Output 2

        //Player Inventory
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlot(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 124 + i * 18));
        //Player Hotbar
        for (int i = 0; i < 9; ++i)
            addSlot(new Slot(invPlayer, i, 8 + i * 18, 182));

        this.func_216961_a(slots);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return tilePurifier.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            if (index == 4 || index == 5) {
                if (!mergeItemStack(slotStack, 6, 39, true))
                    return ItemStack.EMPTY;

                slot.onSlotChange(slotStack, itemstack);
            } else if (index != 3 && index != 2 && index != 1 && index != 0) {
                if (isRecipePresent(slotStack)) {
                    if (!mergeItemStack(slotStack, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (PurifierTileEntity.isItemFuel(slotStack)) {
                    if (!mergeItemStack(slotStack, 1, 4, false))
                        return ItemStack.EMPTY;
                } else if (index >= 6 && index < 30) {
                    if (!mergeItemStack(slotStack, 30, 39, false))
                        return ItemStack.EMPTY;
                } else if (index >= 31 && index < 40 && !mergeItemStack(slotStack, 6, 30, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(slotStack, 6, 39, false))
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

    private boolean isRecipePresent(ItemStack stack) {
        return this.world.getRecipeManager().getRecipe((IRecipeType)this.recipeType, new Inventory(stack), this.world).isPresent();
    }

    @OnlyIn(Dist.CLIENT)
    public int getTimeLeftScaled() {
        int time = this.slotsArray.get(2);
        int timeTotal = this.slotsArray.get(3);
        return timeTotal != 0 && time != 0 ? time * 24 / timeTotal : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getTimeLeft() {
        int recipes = this.slotsArray.get(1);
        if (recipes == 0) {
            recipes = 200;
        }

        return this.slotsArray.get(0) * 13 / recipes;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.slotsArray.get(0) > 0;
    }
}

