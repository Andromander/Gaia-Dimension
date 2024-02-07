package androsa.gaiadimension.block.menu;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModMenus;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class AgateCraftingTableMenu extends RecipeBookMenu<CraftingContainer> {

    private final CraftingContainer invCrafting = new TransientCraftingContainer(this, 3, 3);
    private final ResultContainer invResult = new ResultContainer();
    private final ContainerLevelAccess worldPos;
    private final Player player;

    public AgateCraftingTableMenu(int id, Inventory player) {
        this(id, player, ContainerLevelAccess.NULL);
    }

    public AgateCraftingTableMenu(int id, Inventory player, ContainerLevelAccess world) {
        super(ModMenus.AGATE_CRAFTING_TABLE.get(), id);

        this.worldPos = world;
        this.player = player.player;
        this.addSlot(new ResultSlot(player.player, this.invCrafting, this.invResult, 0, 124, 35));

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
    public void slotsChanged(Container inventoryIn) {
        this.worldPos.execute((world, pos) -> updateSlots(this, world, this.player, this.invCrafting, this.invResult));
    }

    protected static void updateSlots(AbstractContainerMenu menu, Level world, Player playerentity, CraftingContainer craft, ResultContainer result) {
        if (!world.isClientSide()) {
            ServerPlayer serverplayerentity = (ServerPlayer)playerentity;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<RecipeHolder<CraftingRecipe>> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craft, world);
            if (optional.isPresent()) {
                RecipeHolder<CraftingRecipe> icraftingrecipe = optional.get();
                CraftingRecipe crafting = icraftingrecipe.value();
                if (result.setRecipeUsed(world, serverplayerentity, icraftingrecipe)) {
                    ItemStack assembled = crafting.assemble(craft, world.registryAccess());
                    if (assembled.isItemEnabled(world.enabledFeatures())) {
                        itemstack = assembled;
                    }
                }
            }

            result.setItem(0, itemstack);
            menu.setRemoteSlot(0, itemstack);
            serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, itemstack));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents helper) {
        this.invCrafting.fillStackedContents(helper);
    }

    @Override
    public void clearCraftingContent() {
        this.invCrafting.clearContent();
        this.invResult.clearContent();
    }

    @Override
    public boolean recipeMatches(RecipeHolder<? extends Recipe<CraftingContainer>> recipeIn) {
        return recipeIn.value().matches(this.invCrafting, this.player.level());
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        this.worldPos.execute((world, pos) -> this.clearContainer(playerIn, this.invCrafting));
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(this.worldPos, playerIn, ModBlocks.agate_crafting_table.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
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

            slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn) {
        return slotIn.container != this.invResult && super.canTakeItemForPickAll(stack, slotIn);
    }

    @Override
    public boolean shouldMoveToInventory(int id) {
        return id != this.getResultSlotIndex();
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
    public int getSize() {
        return 10;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }
}
