package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.recipe.PurifierRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PurifierRecipeMaker {

    private PurifierRecipeMaker() {
    }

    public static List<PurifyingRecipe> getPurifierRecipes(IJeiHelpers helpers) {
        IStackHelper stackHelper = helpers.getStackHelper();
        PurifierRecipes recipes = PurifierRecipes.instance();
        Map<ItemStack, ItemStack[]> purifyMap = recipes.getPurifyingList();

        List<PurifyingRecipe> recipeList = new ArrayList<>();

        for (Map.Entry<ItemStack, ItemStack[]> entry : purifyMap.entrySet()) {
            ItemStack input = entry.getKey();
            ItemStack output1 = entry.getValue()[0];
            ItemStack output2 = entry.getValue()[1];

            float exp = recipes.getExperience(output1);

            List<ItemStack> inputs = stackHelper.getSubtypes(input);
            PurifyingRecipe recipe = new PurifyingRecipe(inputs, output1, output2, exp);
            recipeList.add(recipe);
        }

        return recipeList;
    }
}
