package androsa.gaiadimension.compat.jei.purifier;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurifyingRecipe implements IRecipeWrapper {

    private final List<List<ItemStack>> input;
    private final List<ItemStack> outputs;
    private final String experienceString;

    public PurifyingRecipe(List<ItemStack> input, ItemStack output1, ItemStack output2, float exp) {
        this.input = Collections.singletonList(input);

        this.outputs = new ArrayList<>();
        Collections.addAll(outputs, output1, output2);

        if (exp > 0) {
            experienceString = I18n.format("gui.jei.category.smelting.experience", exp);
        } else {
            experienceString = null;
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, input);
        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }

    public List<List<ItemStack>> getInputs() {
        return input;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        if (experienceString != null) {
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringWidth(experienceString);
            fontRenderer.drawString(experienceString, recipeWidth - stringWidth, 5, 0x808080);
        }
    }
}
