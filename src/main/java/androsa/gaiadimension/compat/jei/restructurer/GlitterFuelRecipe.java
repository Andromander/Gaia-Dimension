package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimension;
import com.google.common.base.Preconditions;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GlitterFuelRecipe implements IRecipeWrapper {
    private final List<List<ItemStack>> inputs;
    private final String smeltCountString;
    private final IDrawableAnimated flame;
    private final ResourceLocation firetex = new ResourceLocation(GaiaDimension.MODID, "textures/gui/jei/glittering.png");

    public GlitterFuelRecipe(IGuiHelper guiHelper, Collection<ItemStack> input, int burnTime) {
        Preconditions.checkArgument(burnTime > 0, "burn time must be greater than 0");
        List<ItemStack> inputList = new ArrayList<>(input);
        this.inputs = Collections.singletonList(inputList);

        if (burnTime == 200) {
            this.smeltCountString = I18n.format("gui.gaiadimension.category.fuel.smeltAverage.singleAverage");
        } else {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumFractionDigits(2);
            String smeltCount = numberInstance.format(burnTime / 200f);
            this.smeltCountString = I18n.format("gui.gaiadimension.category.fuel.smeltAverage", smeltCount);
        }

        this.flame = guiHelper.drawableBuilder(firetex, 0, 0, 14, 14)
                .setTextureSize(14, 14)
                .buildAnimated(burnTime, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, inputs);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        flame.draw(minecraft, 1, 0);
        minecraft.fontRenderer.drawString(smeltCountString, 24, 13, 0x808080);
    }
}
