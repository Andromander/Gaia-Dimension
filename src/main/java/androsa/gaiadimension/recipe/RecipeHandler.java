package androsa.gaiadimension.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeHandler {

    //===GLITTER FURNACE===//
    public static void addGlitterRefactoring(Block input, ItemStack output1, ItemStack output2, float xp) {
        RestructurerRecipes.instance().glittering(input, output1, output2, xp);
    }

    public static void addGlitterRefactoring(Item input, ItemStack output1, ItemStack output2, float xp) {
        RestructurerRecipes.instance().glittering(input, output1, output2, xp);
    }

    public static void addGlitterRefactoring(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        RestructurerRecipes.instance().glittering(input, output1, output2, xp);
    }

    //===PURIFIER===//
    public static void addPurifying(Block input, ItemStack output1, ItemStack output2, float xp) {
        PurifierRecipes.instance().purifying(input, output1, output2, xp);
    }

    public static void addPurifying(Item input, ItemStack output1, ItemStack output2, float xp) {
        PurifierRecipes.instance().purifying(input, output1, output2, xp);
    }

    public static void addPurifying(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        PurifierRecipes.instance().purifying(input, output1, output2, xp);
    }
}
