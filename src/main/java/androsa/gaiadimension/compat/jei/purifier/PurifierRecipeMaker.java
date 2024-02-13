package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class PurifierRecipeMaker {

    private final ClientLevel level;
    private final RecipeManager recipeManager;

    public PurifierRecipeMaker() {
        this.level = Minecraft.getInstance().level;
        this.recipeManager = this.level.getRecipeManager();
    }

    public List<RecipeHolder<PurifierRecipe>> getPurifierRecipes() {
        return recipeManager.getAllRecipesFor(ModRecipes.PURIFYING.get())
                .stream()
                .filter(this::isRecipeValid)
                .toList();
    }

    private boolean isRecipeValid(RecipeHolder<PurifierRecipe> holder) {
        PurifierRecipe recipe = holder.value();
        if (recipe.isSpecial()) {
            return true;
        }
        RegistryAccess access = this.level.registryAccess();
        ItemStack recipeOutput = recipe.getResultItem(access);
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

        for(Ingredient ingredient : ingredientList) {
            ItemStack[] input = ingredient.getItems();
            if (input == null) {
                return -1;
            } else {
                inputCount++;
            }
        }

        return inputCount;
    }
}
