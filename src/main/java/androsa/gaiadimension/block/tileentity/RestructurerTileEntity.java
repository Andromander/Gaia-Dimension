package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.RestructurerBlock;
import androsa.gaiadimension.block.container.RestructurerContainer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import androsa.gaiadimension.registry.ModRecipes;
import androsa.gaiadimension.registry.ModTileEntities;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class RestructurerTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 3, 1, 2, 4 };
    private static final int[] slotsSides = new int [] { 1, 2 };
    /**
     * 0 = Input
     * 1 = Gold Slot
     * 2 = Pink Essence Slot
     * 3 = Output Slot
     * 4 = Byproduct Slot
     */
    private NonNullList<ItemStack> restructurerItemStacks = NonNullList.withSize(5, ItemStack.EMPTY);
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

    public RestructurerTileEntity() {
        super(ModTileEntities.RESTRUCTURER.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("gaiadimension.container.restructurer", 0);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory inventory) {
        return new RestructurerContainer(id, inventory, this, slotsArray);
    }

    /** Burn Times for the first slot */
    public static Map<Item, Integer> getFuelBurnTime() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemToMap(map, Items.GOLD_NUGGET, 20);
        addItemToMap(map, Items.GOLD_INGOT, 200);
        addItemToMap(map, Items.GOLDEN_AXE, 150);
        addItemToMap(map, Items.GOLDEN_HOE, 150);
        addItemToMap(map, Items.GOLDEN_PICKAXE, 150);
        addItemToMap(map, Items.GOLDEN_SHOVEL, 150);
        addItemToMap(map, Items.GOLDEN_SWORD, 150);
        addItemToMap(map, Items.GOLDEN_HELMET, 500);
        addItemToMap(map, Items.GOLDEN_CHESTPLATE, 500);
        addItemToMap(map, Items.GOLDEN_LEGGINGS, 500);
        addItemToMap(map, Items.GOLDEN_BOOTS, 500);
        addItemToMap(map, Items.GOLDEN_HORSE_ARMOR, 1000);
        addItemToMap(map, Blocks.GOLD_BLOCK, 2000);
        addItemToMap(map, Blocks.GOLD_ORE, 150);
        addItemToMap(map, ModItems.pyrite, 500);
        addItemToMap(map, ModBlocks.pyrite_block, 5000);
        addItemToMap(map, ModItems.sweet_muckball, 250);
        addItemToMap(map, ModBlocks.frail_glitter_block, 1000);
        addItemToMap(map, ModBlocks.thick_glitter_block, 2000);
        addItemToMap(map, ModBlocks.gummy_glitter_block, 4000);
        addItemToMap(map, Items.BLAZE_POWDER, 1200);
        addItemToMap(map, Items.BLAZE_ROD, 2400);
        return map;
    }

    /** Burn times for the second slot */
    public static Map<Item, Integer> getSecondFuelBurnTime() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemToMap(map, ModItems.pink_essence, 100);
        addItemToMap(map, ModItems.pink_goo, 900);
        addItemToMap(map, ModBlocks.pink_sludge_block, 8100);
        addItemToMap(map, ModItems.aura_residue, 200);
        addItemToMap(map, ModItems.aura_cluster, 1800);
        addItemToMap(map, ModBlocks.aura_block, 16200);
        return map;
    }

    private static void addItemToMap(Map<Item, Integer> map, IItemProvider provider, int ticks) {
        map.put(provider.asItem(), ticks);
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.restructurerItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.restructurerItemStacks);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = this.getItemBurnTime(this.restructurerItemStacks.get(1), this.restructurerItemStacks.get(2));
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
        compound.putInt("CookTimeTotal", (short)this.cookTimeTotal);
        ItemStackHelper.saveAllItems(compound, this.restructurerItemStacks);
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
            ItemStack goldStack = this.restructurerItemStacks.get(1);
            ItemStack essenceStack = this.restructurerItemStacks.get(2);

            if (this.isBurning() || !goldStack.isEmpty() && !essenceStack.isEmpty() && !this.restructurerItemStacks.get(0).isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(ModRecipes.RESTRUCTURING, this, this.world).orElse(null);
                if (!this.isBurning() && this.canChange(irecipe)) {
                    this.burnTime = getItemBurnTime(goldStack, essenceStack);
                    this.recipesUsed = this.burnTime;

                    if (this.isBurning()) {
                        burn = true;

                        if (!goldStack.isEmpty()) {
                            Item item = goldStack.getItem();
                            goldStack.shrink(1);

                            if (goldStack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(goldStack);
                                this.restructurerItemStacks.set(1, item1);
                            }
                        }

                        if (!essenceStack.isEmpty()) {
                            Item item = essenceStack.getItem();
                            essenceStack.shrink(1);

                            if (essenceStack.isEmpty()) {
                                ItemStack item2 = item.getContainerItem(essenceStack);
                                this.restructurerItemStacks.set(2, item2);
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
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(RestructurerBlock.LIT, this.isBurning()), 3);
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
        if (!this.restructurerItemStacks.get(0).isEmpty() && recipe != null) {
            ItemStack slot1 = ((RestructurerRecipe)recipe).getRecipeOutput();
            ItemStack slot2 = ((RestructurerRecipe)recipe).getByproduct();

            if (slot1.isEmpty() && slot2.isEmpty() || slot1.isEmpty()) {
                return false;
            } else {
                ItemStack output = restructurerItemStacks.get(3), byproduct = restructurerItemStacks.get(4);

                if (output.isEmpty() && byproduct.isEmpty()) {
                    return true;
                } else if (!output.isItemEqual(slot1) || !byproduct.isItemEqual(slot2)) {
                    return false;
                } else if ((output.getCount() + slot1.getCount() <= this.getInventoryStackLimit() && output.getCount() + slot1.getCount() <= output.getMaxStackSize()) &&
                        byproduct.getCount() + slot2.getCount() <= this.getInventoryStackLimit() && byproduct.getCount() + slot2.getCount() <= byproduct.getMaxStackSize()) {
                    return true;
                } else {
                    return output.getCount() + slot1.getCount() <= output.getMaxStackSize() && byproduct.getCount() + slot2.getCount() <= byproduct.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    private void changeItem(IRecipe<?> recipe) {
        if (recipe != null && canChange(recipe)) {
            ItemStack input = this.restructurerItemStacks.get(0);
            ItemStack slot1 = ((RestructurerRecipe)recipe).getRecipeOutput();
            ItemStack slot2 = ((RestructurerRecipe)recipe).getByproduct();
            ItemStack output = this.restructurerItemStacks.get(3);
            ItemStack byproduct = this.restructurerItemStacks.get(4);

            if (output.isEmpty())
                restructurerItemStacks.set(3, slot1.copy());
            else if (output.getItem() == slot1.getItem())
                output.grow(slot1.getCount());
            if (!slot2.isEmpty()) {
                if (byproduct.isEmpty())
                    restructurerItemStacks.set(4, slot2.copy());
                else if (byproduct.getItem() == slot2.getItem())
                    byproduct.grow(slot2.getCount());
            }

            if (!this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            if (input.getCount() <= 0)
                restructurerItemStacks.set(0, ItemStack.EMPTY);

            input.shrink(1);
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public int getItemBurnTime(ItemStack stack1, ItemStack stack2) {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return 0;
        } else {
            Item itemGlitter = stack1.getItem();
            Item itemShine = stack2.getItem();
            return (getFuelBurnTime().get(itemGlitter) + getSecondFuelBurnTime().get(itemShine)) / 2;
        }
    }

    private int cookingTime() {
        return this.world.getRecipeManager().getRecipe(ModRecipes.RESTRUCTURING, this, this.world).map(RestructurerRecipe::getCookTime).orElse(200);
    }

    public static boolean isItemFuel(ItemStack stack) {
        Item item = stack.getItem();
        return getFuelBurnTime().get(item) != null || getSecondFuelBurnTime().get(item) != null;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return slotsBottom;
        } else {
            return side == Direction.UP ? slotsTop : slotsSides;
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1 || index == 2) {
            Item item = stack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.restructurerItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.restructurerItemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.restructurerItemStacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.restructurerItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.restructurerItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.restructurerItemStacks.get(index);
        boolean burning = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.restructurerItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !burning) {
            this.cookTimeTotal = this.cookingTime();
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
        return index != 3 && index != 4 && (index == 0 || isItemFuel(stack));
    }

    @Override
    public void clear() {
        this.restructurerItemStacks.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null) {
            this.recipeMap.compute(recipe.getId(), (location, integer) -> 1 + (integer == null ? 0 : integer));
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
                grantExperience(player, entry.getValue(), ((RestructurerRecipe)recipe).getExperience());
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
            player.world.addEntity(new ExperienceOrbEntity(player.world, player.getPosX(), player.getPosY() + 0.5D, player.getPosZ() + 0.5D, j));
        }
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for(ItemStack itemstack : this.restructurerItemStacks) {
            helper.accountStack(itemstack);
        }
    }

    private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    @Nullable
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
