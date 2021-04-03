package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.container.slots.GoldSlot;
import androsa.gaiadimension.block.container.slots.NullSlot;
import androsa.gaiadimension.block.container.slots.PurifyOutputSlot;
import androsa.gaiadimension.block.container.slots.ShineSlot;
import androsa.gaiadimension.block.tileentity.PurifierTileEntity;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.registry.ModContainers;
import androsa.gaiadimension.registry.ModRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PurifierContainer extends Container {

    private final IInventory tilePurifier;
    private final IIntArray slotsArray;
    private final World world;
    private final IRecipeType<PurifierRecipe> recipeType = ModRecipes.PURIFYING;

    public PurifierContainer(int id, PlayerInventory playerinv) {
        this(id, playerinv, new Inventory(6), new IntArray(4));
    }

    public PurifierContainer(int id, PlayerInventory invPlayer, IInventory purifier, IIntArray slots) {
        super(ModContainers.PURIFIER.get(), id);
        checkContainerSize(purifier, 6);
        checkContainerDataCount(slots, 4);
        tilePurifier = purifier;
        slotsArray = slots;
        world = invPlayer.player.level;
        addSlot(new Slot(purifier, 0, 80, 17)); //Input
        addSlot(new GoldSlot(purifier, 1, 59, 63));    //Fuel 1
        addSlot(new ShineSlot(purifier, 2, 101, 63));  //Fuel 2
        addSlot(new NullSlot(purifier, 3, 80, 35));    //Fuel 3
        addSlot(new PurifyOutputSlot(invPlayer.player, purifier, 4, 46, 91));  //Output 1
        addSlot(new PurifyOutputSlot(invPlayer.player, purifier, 5, 114, 91)); //Output 2

        //Player Inventory
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlot(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 124 + i * 18));
        //Player Hotbar
        for (int i = 0; i < 9; ++i)
            addSlot(new Slot(invPlayer, i, 8 + i * 18, 182));

        this.addDataSlots(slots);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return tilePurifier.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();

            if (index == 4 || index == 5) {
                if (!moveItemStackTo(slotStack, 6, 39, true))
                    return ItemStack.EMPTY;

                slot.onQuickCraft(slotStack, itemstack);
            } else if (index != 3 && index != 2 && index != 1 && index != 0) {
                if (isRecipePresent(slotStack)) {
                    if (!moveItemStackTo(slotStack, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (PurifierTileEntity.isItemFuel(slotStack)) {
                    if (!moveItemStackTo(slotStack, 1, 4, false))
                        return ItemStack.EMPTY;
                } else if (index >= 6 && index < 30) {
                    if (!moveItemStackTo(slotStack, 30, 39, false))
                        return ItemStack.EMPTY;
                } else if (index >= 31 && index < 40 && !moveItemStackTo(slotStack, 6, 30, false))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(slotStack, 6, 39, false))
                return ItemStack.EMPTY;

            if (slotStack.isEmpty())
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();

            if (slotStack.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, slotStack);
        }

        return itemstack;
    }

    private boolean isRecipePresent(ItemStack stack) {
        return this.world.getRecipeManager().getRecipeFor(this.recipeType, new Inventory(stack), this.world).isPresent();
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

