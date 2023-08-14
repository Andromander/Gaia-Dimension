package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.registry.registration.ModRecipes;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.library.plugins.vanilla.crafting.CategoryRecipeValidator;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class PurifierRecipeMaker {

    private final IIngredientManager ingredientManager;
    private final RecipeManager recipeManager;

    public PurifierRecipeMaker(IIngredientManager ingredientManager) {
        this.recipeManager = Minecraft.getInstance().level.getRecipeManager();
        this.ingredientManager = ingredientManager;
    }

    public List<PurifierRecipe> getPurifierRecipes(IRecipeCategory<PurifierRecipe> category) {
        CategoryRecipeValidator<PurifierRecipe> validator = new CategoryRecipeValidator<>(category, ingredientManager, 1);
        return recipeManager.getAllRecipesFor(ModRecipes.PURIFYING.get())
                .stream()
                .filter(r -> validator.isRecipeValid(r) && validator.isRecipeHandled(r))
                .toList();
    }

//    public static List<PurifierRecipe> getPurifierRecipes() {
//        ClientLevel world = Minecraft.getInstance().level;
//        RecipeManager recipeManager = world.getRecipeManager();
//        List<PurifierRecipe> recipeList = new ArrayList<>();
//
//        for (Recipe<Container> iInventoryIRecipe : JEICompat.getRecipes(recipeManager, ModRecipes.PURIFYING)) {
//            PurifierRecipe recipe = (PurifierRecipe) iInventoryIRecipe;
//            if (isRecipeValid(recipe)) {
//                recipeList.add(recipe);
//            }
//        }
//
//        return recipeList;
//    }
//
//    private static boolean isRecipeValid(PurifierRecipe recipe) {
//        ItemStack recipeOutput = recipe.getResultItem();
//        if (recipeOutput != null && !recipeOutput.isEmpty()) {
//            List<Ingredient> ingredients = recipe.getIngredients();
//            if (ingredients == null) {
//                GaiaDimensionMod.LOGGER.error("Recipe has no input Ingredients.");
//                return false;
//            } else {
//                int inputCount = getInputCount(ingredients);
//                if (inputCount == -1) {
//                    return false;
//                } else {
//                    if (inputCount > 1) {
//                        GaiaDimensionMod.LOGGER.error("Recipe has too many inputs.");
//                        return false;
//                    } else if (inputCount == 0) {
//                        GaiaDimensionMod.LOGGER.error("Recipe has no inputs.");
//                        return false;
//                    } else {
//                        return true;
//                    }
//                }
//            }
//        } else {
//            GaiaDimensionMod.LOGGER.error("Recipe has no output. {}", recipeOutput);
//            return false;
//        }
//    }
//
//    private static int getInputCount(List<Ingredient> ingredientList) {
//        int inputCount = 0;
//
//        for(Iterator<Ingredient> iterator = ingredientList.iterator(); iterator.hasNext(); ++inputCount) {
//            Ingredient ingredient = iterator.next();
//            ItemStack[] input = ingredient.getItems();
//            if (input == null) {
//                return -1;
//            }
//        }
//
//        return inputCount;
//    }
}
