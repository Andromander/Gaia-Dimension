package androsa.gaiadimension.recipe;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class RestructurerRecipe implements Recipe<SingleRecipeInput> {
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final ItemStack byproduct;
    protected final float experience;
    protected final int cookTime;

    public RestructurerRecipe(String groupIn, Ingredient ingredientIn, ItemStack resultIn, ItemStack byproductIn, float experienceIn, int cookTimeIn) {
        this.group = groupIn;
        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.byproduct = byproductIn;
        this.experience = experienceIn;
        this.cookTime = cookTimeIn;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.restructurer.get());
    }

    @Override
    public boolean matches(SingleRecipeInput inv, Level worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput inv, HolderLookup.Provider access) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
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
    public ItemStack getResultItem(HolderLookup.Provider access) {
        return this.result;
    }

    public ItemStack getByproduct() {
        return this.byproduct;
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
    public RecipeType<?> getType() {
        return ModRecipes.RESTRUCTURING.get();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.RESTRUCTURING_SERIALIZER.get();
    }

    public interface EntityFactory<T extends RestructurerRecipe> {
        T create(String group, Ingredient ingredientIn, ItemStack outputIn, ItemStack byproductIn, float experienceIn, int timeIn);
    }
}
