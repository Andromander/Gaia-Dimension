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

public class RestructurerRecipe implements IRecipe<IInventory> {
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final ItemStack byproduct;
    protected final float experience;
    protected final int cookTime;

    public RestructurerRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, ItemStack byproductIn, float experienceIn, int cookTimeIn) {
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
        return new ItemStack(ModBlocks.restructurer);
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
        return ModRecipes.RESTRUCTURING;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.RESTRUCTURING_SERIALIZER;
    }

    /*private static final RestructurerRecipe glitteringBase = new RestructurerRecipe();
    private final Map<ItemStack, ItemStack[]> glitteringList = new HashMap<>();
    private final Map<ItemStack, Float> experienceList = new HashMap<>();

    public static RestructurerRecipe instance() {
        return glitteringBase;
    }

    private RestructurerRecipe() {}

    public void glittering(Block input, ItemStack output1, ItemStack output2, float xp) {
        glittering(Item.getItemFromBlock(input), output1, output2, xp);
    }

    public void glittering(Item input, ItemStack output1, ItemStack output2, float xp) {
        glittering(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), output1, output2, xp);
    }

    public void glittering(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        glitteringList.put(input, new ItemStack[]{ output1, output2 });
        experienceList.put(output1, xp);
    }

    public ItemStack[] getRefactoringResult(ItemStack stack) {
        for (Map.Entry<ItemStack, ItemStack[]> entry : glitteringList.entrySet()) {
            if (areStacksEqual(stack, entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }

    private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE|| par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
    }

    public Map<ItemStack, ItemStack[]> getGlitteringList() {
        return glitteringList;
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
