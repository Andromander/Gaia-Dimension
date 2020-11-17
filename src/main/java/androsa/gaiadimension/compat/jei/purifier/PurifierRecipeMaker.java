package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.JEICompat;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.registry.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;

import java.util.*;

public class PurifierRecipeMaker {

    private PurifierRecipeMaker() {
    }

    public static List<PurifierRecipe> getPurifierRecipes() {
        ClientWorld world = Minecraft.getInstance().world;
        RecipeManager recipeManager = world.getRecipeManager();
        List<PurifierRecipe> recipeList = new ArrayList<>();
        Iterator iterator = JEICompat.getRecipes(recipeManager, ModRecipes.PURIFYING).iterator();

        while(iterator.hasNext()) {
            PurifierRecipe recipe = (PurifierRecipe)iterator.next();
            if (isRecipeValid(recipe)) {
                recipeList.add(recipe);
            }
        }

        return recipeList;
    }

    private static boolean isRecipeValid(PurifierRecipe recipe) {
        ItemStack recipeOutput = recipe.getRecipeOutput();
        if (recipeOutput != null && !recipeOutput.isEmpty()) {
            List<Ingredient> ingredients = recipe.getIngredients();
            if (ingredients == null) {
                GaiaDimensionMod.LOGGER.error("Recipe has no input Ingredients.");
                return false;
            } else {
                int inputCount = getInputCount(ingredients);
                if (inputCount == -1) {
                    return false;
                } else {
                    if (inputCount > 1) {
                        GaiaDimensionMod.LOGGER.error("Recipe has too many inputs.");
                        return false;
                    } else if (inputCount == 0) {
                        GaiaDimensionMod.LOGGER.error("Recipe has no inputs.");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else {
            GaiaDimensionMod.LOGGER.error("Recipe has no output. {}");
            return false;
        }
    }

    private static int getInputCount(List<Ingredient> ingredientList) {
        int inputCount = 0;

        for(Iterator iterator = ingredientList.iterator(); iterator.hasNext(); ++inputCount) {
            Ingredient ingredient = (Ingredient)iterator.next();
            ItemStack[] input = ingredient.getMatchingStacks();
            if (input == null) {
                return -1;
            }
        }

        return inputCount;
    }
}
