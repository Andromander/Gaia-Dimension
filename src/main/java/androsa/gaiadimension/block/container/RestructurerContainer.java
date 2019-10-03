package androsa.gaiadimension.block.container;

import androsa.gaiadimension.block.container.slots.GlitterOutputSlot;
import androsa.gaiadimension.block.container.slots.GoldSlot;
import androsa.gaiadimension.block.container.slots.ShineSlot;
import androsa.gaiadimension.block.tileentity.RestructurerTileEntity;
import androsa.gaiadimension.recipe.RestructurerRecipe;
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

public class RestructurerContainer extends Container {

    private final IInventory tileRestructurer;
    private final IIntArray slotsArray;
    private final World world;
    private final IRecipeType<RestructurerRecipe> recipeType = ModRecipes.RESTRUCTURING;

    public RestructurerContainer(int id, PlayerInventory playerinv) {
        this(id, playerinv, new Inventory(5), new IntArray(4));
    }

    public RestructurerContainer(int id, PlayerInventory invPlayer, IInventory restructurer, IIntArray slots) {
        super(ModContainers.RESTRUCTURER.get(), id);
        assertInventorySize(restructurer, 5);
        assertIntArraySize(slots, 4);
        tileRestructurer = restructurer;
        slotsArray = slots;
        world = invPlayer.player.world;
        addSlot(new Slot(restructurer, 0, 80, 34));       //Input
        addSlot(new GoldSlot(restructurer, 1, 51, 17));   //Fuel 1
        addSlot(new ShineSlot(restructurer, 2, 109, 17)); //Fuel 2
        addSlot(new GlitterOutputSlot(invPlayer.player, restructurer, 3, 70, 80)); //Output 1
        addSlot(new GlitterOutputSlot(invPlayer.player, restructurer, 4, 90, 80)); //Output 2

        //Player Inventory
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlot(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 115 + i * 18));
        //Player Hotbar
        for (int i = 0; i < 9; ++i)
            addSlot(new Slot(invPlayer, i, 8 + i * 18, 172));

        this.trackIntArray(slots);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return tileRestructurer.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
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
                if (isRecipePresent(slotStack)) {
                    if (!mergeItemStack(slotStack, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (RestructurerTileEntity.isItemFuel(slotStack)) {
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

    private boolean isRecipePresent(ItemStack stack) {
        return this.world.getRecipeManager().getRecipe(this.recipeType, new Inventory(stack), this.world).isPresent();
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
