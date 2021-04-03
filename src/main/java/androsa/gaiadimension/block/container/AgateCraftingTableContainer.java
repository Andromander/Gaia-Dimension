package androsa.gaiadimension.block.container;

import androsa.gaiadimension.registry.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

public class AgateCraftingTableContainer extends RecipeBookContainer<CraftingInventory> {

    private final CraftingInventory invCrafting = new CraftingInventory(this, 3, 3);
    private final CraftResultInventory invResult = new CraftResultInventory();
    private final IWorldPosCallable worldPos;
    private final PlayerEntity player;

    public AgateCraftingTableContainer(int id, PlayerInventory player) {
        this(id, player, IWorldPosCallable.NULL);
    }

    public AgateCraftingTableContainer(int id, PlayerInventory player, IWorldPosCallable world) {
        super(ModContainers.AGATE_CRAFTING_TABLE.get(), id);

        this.worldPos = world;
        this.player = player.player;
        this.addSlot(new CraftingResultSlot(player.player, this.invCrafting, this.invResult, 0, 124, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.invCrafting, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(player, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(player, l, 8 + l * 18, 142));
        }
    }

    @Override
    public void slotsChanged(IInventory inventoryIn) {
        this.worldPos.execute((world, pos) -> updateSlots(this.containerId, world, this.player, this.invCrafting, this.invResult));
    }

    protected static void updateSlots(int id, World world, PlayerEntity playerentity, CraftingInventory craft, CraftResultInventory result) {
        if (!world.isClientSide()) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)playerentity;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(IRecipeType.CRAFTING, craft, world);
            if (optional.isPresent()) {
                ICraftingRecipe icraftingrecipe = optional.get();
                if (result.setRecipeUsed(world, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.assemble(craft);
                }
            }

            result.setItem(0, itemstack);
            serverplayerentity.connection.send(new SSetSlotPacket(id, 0, itemstack));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(RecipeItemHelper helper) {
        this.invCrafting.fillStackedContents(helper);
    }

    @Override
    public void clearCraftingContent() {
        this.invCrafting.clearContent();
        this.invResult.clearContent();
    }

    @Override
    public boolean recipeMatches(IRecipe<? super CraftingInventory> recipeIn) {
        return recipeIn.matches(this.invCrafting, this.player.level);
    }

    @Override
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.worldPos.execute((world, pos) -> this.clearContainer(playerIn, world, this.invCrafting));
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                this.worldPos.execute((world, pos) -> itemstack1.getItem().onCraftedBy(itemstack1, world, playerIn));
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= 10 && index < 37) {
                if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 37 && index < 46) {
                if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
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

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.drop(itemstack2, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn) {
        return slotIn.container != this.invResult && super.canTakeItemForPickAll(stack, slotIn);
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return this.invCrafting.getWidth();
    }

    @Override
    public int getGridHeight() {
        return this.invCrafting.getHeight();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSize() {
        return 10;
    }

    @Override
    public RecipeBookCategory getRecipeBookType() {
        return RecipeBookCategory.CRAFTING;
    }
}
