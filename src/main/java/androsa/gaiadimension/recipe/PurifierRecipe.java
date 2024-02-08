package androsa.gaiadimension.recipe;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class PurifierRecipe implements Recipe<Container> {
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final ItemStack byproduct;
    protected final float experience;
    protected final int cookTime;

    public PurifierRecipe(String groupIn, Ingredient ingredientIn, ItemStack resultIn, ItemStack byproductIn, float experienceIn, int cookTimeIn) {
        this.group = groupIn;
        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.byproduct = byproductIn;
        this.experience = experienceIn;
        this.cookTime = cookTimeIn;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.purifier.get());
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(Container inv, RegistryAccess access) {
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
    public ItemStack getResultItem(RegistryAccess access) {
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
        return ModRecipes.PURIFYING.get();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PURIFYING_SERIALIZER.get();
    }

    public interface EntityFactory<T extends PurifierRecipe> {
        T create(String group, Ingredient ingredientIn, ItemStack outputIn, ItemStack byproductIn, float experienceIn, int timeIn);
    }
}
