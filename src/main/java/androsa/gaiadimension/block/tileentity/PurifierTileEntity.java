package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.PurifierBlock;
import androsa.gaiadimension.block.container.PurifierContainer;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.registry.*;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class PurifierTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 4, 1, 2, 3, 5 };
    private static final int[] slotsSides = new int [] { 1, 2, 3 };
    /**
     * 0 = Input
     * 1 = Gold Slot
     * 2 = Pink Essence Slot
     * 3 = Bismuth Slot
     * 4 = Output Slot
     * 5 = Byproduct Slot
     */
    private NonNullList<ItemStack> purifyingItemStacks = NonNullList.withSize(6, ItemStack.EMPTY);
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;
    protected final IIntArray slotsArray = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return burnTime;
                case 1:
                    return recipesUsed;
                case 2:
                    return cookTime;
                case 3:
                    return cookTimeTotal;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    burnTime = value;
                case 1:
                    recipesUsed = value;
                case 2:
                    cookTime = value;
                case 3:
                    cookTimeTotal = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };
    private final Map<ResourceLocation, Integer> recipeMap = Maps.newHashMap();

    public PurifierTileEntity() {
        super(ModTileEntities.PURIFIER.get());
    }

    @Override
    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("gaiadimension.container.purifier", 0);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory inventory) {
        return new PurifierContainer(id, inventory, this, slotsArray);
    }

    /** Burn times for the third slot*/
    public static Map<Item, Integer> getThirdFuelBurnTime() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemToMap(map, ModItems.bismuth_residue.get(), 200);
        addItemToMap(map, ModItems.bismuth_crystal.get(), 1800);
        addItemToMap(map, ModBlocks.bismuth_block.get(), 16200);
        addItemToMap(map, ModItems.black_residue.get(), 100);
        addItemToMap(map, ModItems.tektite.get(), 900);
        addItemToMap(map, ModBlocks.tektite_block.get(), 8100);
        return map;
    }

    private static void addItemToMap(Map<Item, Integer> map, IItemProvider provider, int ticks) {
        map.put(provider.asItem(), ticks);
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.purifyingItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.purifyingItemStacks);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = getItemBurnTime(this.purifyingItemStacks.get(1), this.purifyingItemStacks.get(2), this.purifyingItemStacks.get(3));
        int i = compound.getShort("RecipesUsedSize");

        for (int j = 0; j < i; j++) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.recipeMap.put(resourcelocation, k);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        ItemStackHelper.saveAllItems(compound, this.purifyingItemStacks);
        compound.putShort("RecipesUsedSize", (short)this.recipeMap.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeMap.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return compound;
    }

    @Override
    public void tick() {
        boolean burning = this.isBurning();
        boolean burn = false;

        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack goldStack = this.purifyingItemStacks.get(1);
            ItemStack essenceStack = this.purifyingItemStacks.get(2);
            ItemStack bismuthStack = this.purifyingItemStacks.get(3);

            if (this.isBurning() || !goldStack.isEmpty() && !essenceStack.isEmpty() && !bismuthStack.isEmpty() && !this.purifyingItemStacks.get(0).isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(ModRecipes.PURIFYING, this, this.world).orElse(null);
                if (!this.isBurning() && this.canChange(irecipe)) {
                    this.burnTime = getItemBurnTime(goldStack, essenceStack, bismuthStack);
                    this.recipesUsed = this.burnTime;

                    if (this.isBurning()) {
                        burn = true;

                        if (!goldStack.isEmpty()) {
                            Item item = goldStack.getItem();
                            goldStack.shrink(1);

                            if (goldStack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(goldStack);
                                this.purifyingItemStacks.set(1, item1);
                            }
                        }

                        if (!essenceStack.isEmpty()) {
                            Item item = essenceStack.getItem();
                            essenceStack.shrink(1);

                            if (essenceStack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(essenceStack);
                                this.purifyingItemStacks.set(2, item1);
                            }
                        }

                        if (!bismuthStack.isEmpty()) {
                            Item item = bismuthStack.getItem();
                            bismuthStack.shrink(1);

                            if (bismuthStack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(bismuthStack);
                                this.purifyingItemStacks.set(3, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canChange(irecipe)) {
                    ++this.cookTime;

                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = cookingTime();
                        this.changeItem(irecipe);
                        burn = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }

            if (burning != this.isBurning()) {
                burn = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(PurifierBlock.LIT, this.isBurning()), 3);
            }
        }

        if (burn) {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canChange(IRecipe<?> recipe) {
        if (!this.purifyingItemStacks.get(0).isEmpty() && recipe != null) {
            ItemStack[] itemstack = ((PurifierRecipe)recipe).getRecipeOutputs();

            if(itemstack[0].isEmpty() && itemstack[1].isEmpty() || itemstack[0].isEmpty()) {
                return false;
            } else {
                ItemStack output = purifyingItemStacks.get(4), byproduct = purifyingItemStacks.get(5);

                if(output.isEmpty() && byproduct.isEmpty()) {
                    return true;
                } else if (!output.isItemEqual(itemstack[0]) || !byproduct.isItemEqual(itemstack[1])) {
                    return false;
                } else if ((output.getCount() + itemstack[0].getCount() <= this.getInventoryStackLimit() && output.getCount() + itemstack[0].getCount() <= output.getMaxStackSize()) &&
                        byproduct.getCount() + itemstack[1].getCount() <= this.getInventoryStackLimit() && byproduct.getCount() + itemstack[1].getCount() <= byproduct.getMaxStackSize()) {
                    return true;
                } else {
                    return output.getCount() + itemstack[0].getCount() <= output.getMaxStackSize() && byproduct.getCount() + itemstack[1].getCount() <= byproduct.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void changeItem(IRecipe<?> recipe) {
        if (recipe != null && canChange(recipe)) {
            ItemStack input = this.purifyingItemStacks.get(0);
            ItemStack[] itemstack = ((PurifierRecipe)recipe).getRecipeOutputs();
            ItemStack output = this.purifyingItemStacks.get(3);
            ItemStack byproduct = this.purifyingItemStacks.get(4);

            if (output.isEmpty())
                purifyingItemStacks.set(4, itemstack[0].copy());
            else if (output.getItem() == itemstack[0].getItem())
                output.grow(itemstack[0].getCount());
            if (!itemstack[1].isEmpty()) {
                if (byproduct.isEmpty())
                    purifyingItemStacks.set(5, itemstack[1].copy());
                else if (byproduct.getItem() == itemstack[1].getItem())
                    byproduct.grow(itemstack[1].getCount());
            }

            if (!this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            if (input.getCount() <= 0)
                purifyingItemStacks.set(0, ItemStack.EMPTY);

            input.shrink(1);
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public int getItemBurnTime(ItemStack stack1, ItemStack stack2, ItemStack stack3) {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return 0;
        } else {
            Item itemGlitter = stack1.getItem();
            Item itemShine = stack2.getItem();
            Item itemNull = stack3.getItem();
            return (RestructurerTileEntity.getFuelBurnTime().get(itemGlitter) + RestructurerTileEntity.getSecondFuelBurnTime().get(itemShine) + getThirdFuelBurnTime().get(itemNull)) / 3;
        }
    }

    private int cookingTime() {
        return this.world.getRecipeManager().getRecipe(ModRecipes.PURIFYING, this, this.world).map(PurifierRecipe::getCookTime).orElse(200);
    }

    public static boolean isItemFuel(ItemStack stack) {
        Item item = stack.getItem();
        return RestructurerTileEntity.getFuelBurnTime().get(item) > 0 || RestructurerTileEntity.getSecondFuelBurnTime().get(item) > 0 || getThirdFuelBurnTime().get(item) > 0;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return slotsBottom;
        } else {
            return side == Direction.UP ? slotsTop : slotsSides;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1 || index == 2 || index == 3) {
            Item item = stack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.purifyingItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.purifyingItemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.purifyingItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.purifyingItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.purifyingItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.purifyingItemStacks.get(index);
        boolean burning = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.purifyingItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !burning) {
            this.cookTimeTotal = 200;
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index != 4 && index != 5 && (index == 0 || isItemFuel(stack));
    }

    @Override
    public void clear() {
        this.purifyingItemStacks.clear();
    }

    @Override
    public void setRecipeUsed(IRecipe<?> recipe) {
        if (recipe != null) {
            this.recipeMap.compute(recipe.getId(), (location, integer) -> 1 + (location == null ? 0 : integer));
        }
    }

    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void onCrafting(PlayerEntity player) { }

    public void unlockRecipe(PlayerEntity player) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for(Map.Entry<ResourceLocation, Integer> entry : this.recipeMap.entrySet()) {
            player.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                grantExperience(player, entry.getValue(), ((PurifierRecipe)recipe).getExperience());
            });
        }

        player.unlockRecipes(list);
        this.recipeMap.clear();
    }

    private static void grantExperience(PlayerEntity player, int amount, float multiplier) {
        if (multiplier == 0.0F) {
            amount = 0;
        } else if (multiplier < 1.0F) {
            int i = MathHelper.floor((float)amount * multiplier);
            if (i < MathHelper.ceil((float)amount * multiplier) && Math.random() < (double)((float)amount * multiplier - (float)i)) {
                ++i;
            }

            amount = i;
        }

        while(amount > 0) {
            int j = ExperienceOrbEntity.getXPSplit(amount);
            amount -= j;
            player.world.addEntity(new ExperienceOrbEntity(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, j));
        }
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for(ItemStack itemstack : this.purifyingItemStacks) {
            helper.accountStack(itemstack);
        }
    }

    private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == Direction.DOWN)
                return handlers[0].cast();
            else if (facing == Direction.UP)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void remove() {
        super.remove();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }
}
