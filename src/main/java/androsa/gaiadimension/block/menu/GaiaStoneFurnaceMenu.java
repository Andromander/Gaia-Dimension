package androsa.gaiadimension.block.menu;

import androsa.gaiadimension.block.menu.slots.GaiaFurnaceSlot;
import androsa.gaiadimension.registry.registration.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

public class GaiaStoneFurnaceMenu extends AbstractContainerMenu {

    private final Container tileFurnace;
    private final ContainerData slotData;
    private final Level world;
    private final RecipeType<SmeltingRecipe> recipeType = RecipeType.SMELTING;

    public GaiaStoneFurnaceMenu(int id, Inventory playerinv) {
        this(id, playerinv, new SimpleContainer(3), new SimpleContainerData(4));
    }

    public GaiaStoneFurnaceMenu(int id, Inventory playerinv, Container inventory, ContainerData array) {
        super(ModMenus.GAIA_STONE_FURNACE.get(), id);
        checkContainerSize(inventory, 3);
        checkContainerDataCount(array, 4);
        this.tileFurnace = inventory;
        this.slotData = array;
        this.world = playerinv.player.level();
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

        this.addDataSlots(array);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return tileFurnace.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.isRecipePresent(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private boolean isRecipePresent(ItemStack stack) {
        return this.world.getRecipeManager().getRecipeFor(this.recipeType, new SimpleContainer(stack), this.world).isPresent();
    }

    public static boolean isFuel(ItemStack stack) {
        return AbstractFurnaceBlockEntity.isFuel(stack);
    }

    public int getCookProgressionScaled() {
        int i = this.slotData.get(2);
        int j = this.slotData.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public int getBurnLeftScaled() {
        int i = this.slotData.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.slotData.get(0) * 13 / i;
    }

    public boolean isBurning() {
        return this.slotData.get(0) > 0;
    }
}
