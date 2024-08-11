package androsa.gaiadimension.block.blockentity;

import androsa.gaiadimension.block.RestructurerBlock;
import androsa.gaiadimension.block.menu.RestructurerMenu;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.registry.registration.*;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class RestructurerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {

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
    private int burnDuration;
    private int cookTime;
    private int cookTimeTotal;
    protected final ContainerData slotsArray = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> burnTime;
                case 1 -> burnDuration;
                case 2 -> cookTime;
                case 3 -> cookTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    burnTime = value;
                case 1:
                    burnDuration = value;
                case 2:
                    cookTime = value;
                case 3:
                    cookTimeTotal = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipeMap = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends RestructurerRecipe> cache;

    public RestructurerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESTRUCTURER.get(), pos, state);
        this.cache = RecipeManager.createCheck(ModRecipes.RESTRUCTURING.get());
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("gaiadimension.container.restructurer", 0);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new RestructurerMenu(id, inventory, this, slotsArray);
    }

    public static int getFuelBurnTime(Item item) {
        var fuel = item.builtInRegistryHolder().getData(ModDataMaps.GLITTERING_FUEL);
        return fuel != null ? fuel : 0;
    }

    public static int getSecondFuelBurnTime(Item item) {
        var fuel = item.builtInRegistryHolder().getData(ModDataMaps.SHINING_FUEL);
        return fuel != null ? fuel : 0;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.loadAdditional(compound, provider);
        this.restructurerItemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.restructurerItemStacks, provider);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.burnDuration = this.getItemBurnTime(this.restructurerItemStacks.get(1), this.restructurerItemStacks.get(2));
        int i = compound.getShort("burnDurationSize");

        for (int j = 0; j < i; j++) {
            ResourceLocation resourcelocation = ResourceLocation.parse(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.recipeMap.put(resourcelocation, k);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.saveAdditional(compound, provider);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", (short)this.cookTimeTotal);
        ContainerHelper.saveAllItems(compound, this.restructurerItemStacks, provider);
        compound.putShort("burnDurationSize", (short)this.recipeMap.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeMap.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RestructurerBlockEntity entity) {
        boolean burning = entity.isBurning();
        boolean burn = false;

        if (entity.isBurning()) {
            --entity.burnTime;
        }

        ItemStack input = entity.restructurerItemStacks.get(0);
        ItemStack goldStack = entity.restructurerItemStacks.get(1);
        ItemStack essenceStack = entity.restructurerItemStacks.get(2);

        if (entity.isBurning() || !goldStack.isEmpty() && !essenceStack.isEmpty()) {
            RecipeHolder<? extends RestructurerRecipe> recipeHolder;

            if (!entity.restructurerItemStacks.get(0).isEmpty()) {
                recipeHolder = entity.cache.getRecipeFor(new SingleRecipeInput(input), level).orElse(null);
            } else {
                recipeHolder = null;
            }

            if (!entity.isBurning() && entity.canChange(level.registryAccess(), recipeHolder, entity.restructurerItemStacks, entity.getMaxStackSize())) {
                entity.burnTime = entity.getItemBurnTime(goldStack, essenceStack);
                entity.burnDuration = entity.burnTime;

                if (entity.isBurning()) {
                    burn = true;

                    if (goldStack.hasCraftingRemainingItem()) {
                        entity.restructurerItemStacks.set(1, goldStack.getCraftingRemainingItem());
                    } else if (!goldStack.isEmpty()) {
                        goldStack.shrink(1);
                        if (goldStack.isEmpty()) {
                            entity.restructurerItemStacks.set(1, goldStack.getCraftingRemainingItem());
                        }
                    }

                    if (essenceStack.hasCraftingRemainingItem()) {
                        entity.restructurerItemStacks.set(2, essenceStack.getCraftingRemainingItem());
                    } else if (!essenceStack.isEmpty()) {
                        essenceStack.shrink(1);
                        if (essenceStack.isEmpty()) {
                            entity.restructurerItemStacks.set(2, essenceStack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (entity.isBurning() && entity.canChange(level.registryAccess(), recipeHolder, entity.restructurerItemStacks, entity.getMaxStackSize())) {
                ++entity.cookTime;

                if (entity.cookTime == entity.cookTimeTotal) {
                    entity.cookTime = 0;
                    entity.cookTimeTotal = cookingTime(level, new SingleRecipeInput(entity.getItem(0)));
                    if (entity.changeItem(level.registryAccess(), recipeHolder, entity.restructurerItemStacks, entity.getMaxStackSize())) {
                        entity.setRecipeUsed(recipeHolder);
                    }
                    burn = true;
                }
            } else {
                entity.cookTime = 0;
            }
        } else if (!entity.isBurning() && entity.cookTime > 0) {
            entity.cookTime = Mth.clamp(entity.cookTime - 2, 0, entity.cookTimeTotal);
        }

        if (burning != entity.isBurning()) {
            burn = true;
            level.setBlockAndUpdate(pos, state.setValue(RestructurerBlock.LIT, entity.isBurning()));
        }

        if (burn) {
            setChanged(level, pos, state);
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canChange(RegistryAccess access, RecipeHolder<? extends RestructurerRecipe> recipe, NonNullList<ItemStack> stacks, int stacksize) {
        if (!stacks.get(0).isEmpty() && recipe != null) {
            ItemStack slot1 = recipe.value().getResultItem(access);
            ItemStack slot2 = recipe.value().getByproduct();

            if (slot1.isEmpty() && slot2.isEmpty() || slot1.isEmpty()) {
                return false;
            } else {
                ItemStack output = stacks.get(3), byproduct = stacks.get(4);

                if (output.isEmpty() && byproduct.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(output, slot1) || !ItemStack.isSameItem(byproduct, slot2)) {
                    return false;
                } else if ((output.getCount() + slot1.getCount() <= stacksize && output.getCount() + slot1.getCount() <= output.getMaxStackSize()) &&
                        byproduct.getCount() + slot2.getCount() <= stacksize && byproduct.getCount() + slot2.getCount() <= byproduct.getMaxStackSize()) {
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
    private boolean changeItem(RegistryAccess access, RecipeHolder<? extends RestructurerRecipe> recipe, NonNullList<ItemStack> stacks, int stacksize) {
        if (recipe != null && canChange(access, recipe, stacks, stacksize)) {
            ItemStack input = stacks.get(0);
            ItemStack slot1 = recipe.value().getResultItem(access);
            ItemStack slot2 = recipe.value().getByproduct();
            ItemStack output = stacks.get(3);
            ItemStack byproduct = stacks.get(4);

            if (output.isEmpty())
                stacks.set(3, slot1.copy());
            else if (output.getItem() == slot1.getItem())
                output.grow(slot1.getCount());
            if (!slot2.isEmpty()) {
                if (byproduct.isEmpty())
                    stacks.set(4, slot2.copy());
                else if (byproduct.getItem() == slot2.getItem())
                    byproduct.grow(slot2.getCount());
            }

            input.shrink(1);
            return true;
        }
        return false;
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
            return (getFuelBurnTime(itemGlitter) + getSecondFuelBurnTime(itemShine)) / 2;
        }
    }

    private static int cookingTime(Level level, SingleRecipeInput container) {
        return level.getRecipeManager().getRecipeFor(ModRecipes.RESTRUCTURING.get(), container, level).map(recipe -> recipe.value().getCookTime()).orElse(200);
    }

    public static boolean isItemFuel(ItemStack stack) {
        Item item = stack.getItem();
        return getFuelBurnTime(item) > 0 || getSecondFuelBurnTime(item) > 0;
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
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1 || index == 2) {
            Item item = stack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    public int getContainerSize() {
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
    public ItemStack getItem(int index) {
        return this.restructurerItemStacks.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.restructurerItemStacks, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.restructurerItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = this.restructurerItemStacks.get(index);
        boolean burning = !stack.isEmpty() && ItemStack.isSameItemSameComponents(stack, itemstack);
        this.restructurerItemStacks.set(index, stack);

        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (index == 0 && !burning) {
            this.cookTimeTotal = cookingTime(this.level, new SingleRecipeInput(itemstack));
            this.cookTime = 0;
            this.setChanged();
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.restructurerItemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.restructurerItemStacks = stacks;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.level.getBlockEntity(this.worldPosition) == this &&
                player.distanceToSqr(
                        (double) this.worldPosition.getX() + 0.5D,
                        (double) this.worldPosition.getY() + 0.5D,
                        (double) this.worldPosition.getZ() + 0.5D
                ) <= 64.0D;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return index != 3 && index != 4 && (index == 0 || isItemFuel(stack));
    }

    @Override
    public void clearContent() {
        this.restructurerItemStacks.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            this.recipeMap.addTo(recipe.id(), 1);
        }
    }

    @Override
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void awardUsedRecipes(Player player, List<ItemStack> stacks) { }

    public void awardRecipe(ServerPlayer player) {
        List<RecipeHolder<?>> list = unlockRecipe(player.serverLevel(), player.position());
        player.awardRecipes(list);

        for (RecipeHolder<?> holder : list) {
            if (holder != null) {
                player.triggerRecipeCrafted(holder, this.restructurerItemStacks);
            }
        }

        this.recipeMap.clear();
    }

    public List<RecipeHolder<?>> unlockRecipe(ServerLevel level, Vec3 position) {
        List<RecipeHolder<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipeMap.object2IntEntrySet()) {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                grantExperience(level, position, entry.getIntValue(), ((RestructurerRecipe)recipe.value()).getExperience());
            });
        }

        return list;
    }

    private static void grantExperience(ServerLevel level, Vec3 position, int amount, float multiplier) {
        int i = Mth.floor((float)amount * multiplier);
        float f = Mth.frac((float)amount * multiplier);
        if (f != 0.0F && Math.random() < f) {
            i++;
        }

        ExperienceOrb.award(level, position, i);
    }

    @Override
    public void fillStackedContents(StackedContents helper) {
        for(ItemStack itemstack : this.restructurerItemStacks) {
            helper.accountStack(itemstack);
        }
    }
}
