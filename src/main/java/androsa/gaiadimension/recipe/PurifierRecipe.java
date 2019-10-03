package androsa.gaiadimension.recipe;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PurifierRecipe implements IRecipe<IInventory> {
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final ItemStack byproduct;
    protected final float experience;
    protected final int cookTime;

    public PurifierRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, ItemStack byproductIn, float experienceIn, int cookTimeIn) {
        this.id = idIn;
        this.group = groupIn;
        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.byproduct = byproductIn;
        this.experience = experienceIn;
        this.cookTime = cookTimeIn;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.purifier.get());
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public float getExperience() {
        return this.experience;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public ItemStack[] getRecipeOutputs() {
        return new ItemStack[]{this.result, this.byproduct};
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    @Override
    public String getGroup() {
        return this.group;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.PURIFYING;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.PURIFYING_SERIALIZER.get();
    }

    /*private static final PurifierRecipes purifyingBase = new PurifierRecipes();
    private final Map<ItemStack, ItemStack[]> purifyingList = new HashMap<>();
    private final Map<ItemStack, Float> experienceList = new HashMap<>();

    public static PurifierRecipes instance() {
        return purifyingBase;
    }

    private PurifierRecipes() {}

    public void purifying(Block input, ItemStack output1, ItemStack output2, float xp) {
        purifying(Item.getItemFromBlock(input), output1, output2, xp);
    }

    public void purifying(Item input, ItemStack output1, ItemStack output2, float xp) {
        purifying(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), output1, output2, xp);
    }

    public void purifying(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        purifyingList.put(input, new ItemStack[]{ output1, output2 });
        experienceList.put(output1, xp);
    }

    public ItemStack[] getPurifyingResult(ItemStack stack) {
        for (Map.Entry<ItemStack, ItemStack[]> entry : purifyingList.entrySet()) {
            if (areStacksEqual(stack, entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }

    private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE|| par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
    }

    public Map<ItemStack, ItemStack[]> getPurifyingList() {
        return purifyingList;
    }

    public float getExperience(ItemStack stack) {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Map.Entry<ItemStack, Float> entry : experienceList.entrySet()) {
            if (areStacksEqual(stack, entry.getKey())) {
                return entry.getValue();
            }
        }

        return 0.0F;
    }*/
}
