package androsa.gaiadimension.recipe;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

import java.util.List;

public class RecipeHandler {

    private static final List<IFuelHandler> glitterFuelHandlers = Lists.newArrayList();

    public static void addGlitterRefactoring(Block input, ItemStack output1, ItemStack output2, float xp) {
        GlitterFurnaceRecipes.instance().glittering(input, output1, output2, xp);
    }

    public static void addGlitterRefactoring(Item input, ItemStack output1, ItemStack output2, float xp) {
        GlitterFurnaceRecipes.instance().glittering(input, output1, output2, xp);
    }

    public static void addGlitterRefactoring(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        GlitterFurnaceRecipes.instance().glittering(input, output1, output2, xp);
    }
}
