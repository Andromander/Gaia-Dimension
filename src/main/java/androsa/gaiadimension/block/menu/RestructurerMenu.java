package androsa.gaiadimension.block.menu;

import androsa.gaiadimension.block.menu.slots.GlitterOutputSlot;
import androsa.gaiadimension.block.menu.slots.GoldSlot;
import androsa.gaiadimension.block.menu.slots.ShineSlot;
import androsa.gaiadimension.block.blockentity.RestructurerBlockEntity;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.registry.registration.ModMenus;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RestructurerMenu extends AbstractContainerMenu {

    private final Container tileRestructurer;
    private final ContainerData slotsArray;
    private final Level world;
    private final RecipeType<RestructurerRecipe> recipeType = ModRecipes.RESTRUCTURING.get();

    public RestructurerMenu(int id, Inventory playerinv) {
        this(id, playerinv, new SimpleContainer(5), new SimpleContainerData(4));
    }

    public RestructurerMenu(int id, Inventory invPlayer, Container restructurer, ContainerData slots) {
        super(ModMenus.RESTRUCTURER.get(), id);
        checkContainerSize(restructurer, 5);
        checkContainerDataCount(slots, 4);
        tileRestructurer = restructurer;
        slotsArray = slots;
        world = invPlayer.player.level();
        addSlot(new Slot(restructurer, 0, 80, 34)); //Input
        addSlot(new GoldSlot(restructurer, 1, 51, 17));    //Fuel 1
        addSlot(new ShineSlot(restructurer, 2, 109, 17));  //Fuel 2
        addSlot(new GlitterOutputSlot(invPlayer.player, restructurer, 3, 70, 80)); //Output 1
        addSlot(new GlitterOutputSlot(invPlayer.player, restructurer, 4, 90, 80)); //Output 2

        //Player Inventory
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlot(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 115 + i * 18));
        //Player Hotbar
        for (int i = 0; i < 9; ++i)
            addSlot(new Slot(invPlayer, i, 8 + i * 18, 172));

        this.addDataSlots(slots);
    }

    @Override
    public boolean stillValid(Player player) {
        return tileRestructurer.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();

            if (index == 3 || index == 4) {
                if (!moveItemStackTo(slotStack, 5, 39, true))
                    return ItemStack.EMPTY;

                slot.onQuickCraft(slotStack, itemstack);
            } else if (index != 2 && index != 1 && index != 0) {
                if (isRecipePresent(slotStack)) {
                    if (!moveItemStackTo(slotStack, 0, 1, false))
                        return ItemStack.EMPTY;
                } else if (RestructurerBlockEntity.isItemFuel(slotStack)) {
                    if (!moveItemStackTo(slotStack, 1, 3, false))
                        return ItemStack.EMPTY;
                } else if (index >= 5 && index < 30) {
                    if (!moveItemStackTo(slotStack, 30, 39, false))
                        return ItemStack.EMPTY;
                } else if (index >= 31 && index < 40 && !moveItemStackTo(slotStack, 5, 30, false))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(slotStack, 5, 39, false))
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
        return this.world.getRecipeManager().getRecipeFor(this.recipeType, new SimpleContainer(stack), this.world).isPresent();
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
