package androsa.gaiadimension.compat.jei;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.purifier.NullFuelRecipe;
import androsa.gaiadimension.compat.jei.restructurer.GlitterFuelRecipe;
import androsa.gaiadimension.compat.jei.restructurer.ShineFuelRecipe;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import mezz.jei.api.recipe.RecipeType;

public class GaiaRecipeTypes {
    //Restructuring Recipes
    public static final RecipeType<RestructurerRecipe> RESTRUCTURE = RecipeType.create(GaiaDimensionMod.MODID, "restructuring", RestructurerRecipe.class);
    //Purifying Recipes
    public static final RecipeType<PurifierRecipe> PURIFY = RecipeType.create(GaiaDimensionMod.MODID, "purifying", PurifierRecipe.class);
    //Gold Slot
    public static final RecipeType<GlitterFuelRecipe> GOLD = RecipeType.create(GaiaDimensionMod.MODID, "gold_fuel", GlitterFuelRecipe.class);
    //Shine Slot
    public static final RecipeType<ShineFuelRecipe> SHINE = RecipeType.create(GaiaDimensionMod.MODID, "shine_fuel", ShineFuelRecipe.class);
    //Null Slot
    public static final RecipeType<NullFuelRecipe> NULLING = RecipeType.create(GaiaDimensionMod.MODID, "nulling_fuel", NullFuelRecipe.class);

    private GaiaRecipeTypes() { }
}
