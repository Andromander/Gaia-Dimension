package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.JEICompat;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.registry.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestructurerRecipeMaker {

    private RestructurerRecipeMaker() {
    }

    public static List<RestructurerRecipe> getRestructurerRecipes() {
        ClientLevel world = Minecraft.getInstance().level;
        RecipeManager recipeManager = world.getRecipeManager();
        List<RestructurerRecipe> recipeList = new ArrayList<>();

        for (Recipe<Container> iInventoryIRecipe : JEICompat.getRecipes(recipeManager, ModRecipes.RESTRUCTURING)) {
            RestructurerRecipe recipe = (RestructurerRecipe) iInventoryIRecipe;
            if (isRecipeValid(recipe)) {
                recipeList.add(recipe);
            }
        }

        return recipeList;
    }

    private static boolean isRecipeValid(RestructurerRecipe recipe) {
        ItemStack recipeOutput = recipe.getResultItem();
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
            GaiaDimensionMod.LOGGER.error("Recipe has no output. {}", recipeOutput);
            return false;
        }
    }

    private static int getInputCount(List<Ingredient> ingredientList) {
        int inputCount = 0;

        for(Iterator<Ingredient> iterator = ingredientList.iterator(); iterator.hasNext(); ++inputCount) {
            Ingredient ingredient = iterator.next();
            ItemStack[] input = ingredient.getItems();
            if (input == null) {
                return -1;
            }
        }

        return inputCount;
    }
}
