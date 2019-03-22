package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.recipe.RestructurerRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestructurerRecipeMaker {

    private RestructurerRecipeMaker() {
    }

    public static List<RestructurerRecipe> getRestructurerRecipes(IJeiHelpers helpers) {
        IStackHelper stackHelper = helpers.getStackHelper();
        RestructurerRecipes recipes = RestructurerRecipes.instance();
        Map<ItemStack, ItemStack[]> restructrureMap = recipes.getGlitteringList();

        List<RestructurerRecipe> recipeList = new ArrayList<>();

        for (Map.Entry<ItemStack, ItemStack[]> entry : restructrureMap.entrySet()) {
            ItemStack input = entry.getKey();
            ItemStack output1 = entry.getValue()[0];
            ItemStack output2 = entry.getValue()[1];

            float exp = recipes.getExperience(output1);

            List<ItemStack> inputs = stackHelper.getSubtypes(input);
            RestructurerRecipe recipe = new RestructurerRecipe(inputs, output1, output2, exp);
            recipeList.add(recipe);
        }

        return recipeList;
    }
}
