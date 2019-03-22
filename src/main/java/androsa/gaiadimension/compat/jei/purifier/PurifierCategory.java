package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.compat.jei.GDRecipeCategoryUid;
import androsa.gaiadimension.registry.GDBlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PurifierCategory extends PurifierRecipeCategory<PurifyingRecipe> {

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    private final ResourceLocation backgroundimage = new ResourceLocation(GaiaDimension.MODID, "textures/gui/jei/recipe2output.png");

    public PurifierCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(backgroundimage, 0, 0, 75, 55)
                .setTextureSize(76, 56).addPadding(0, 0, 0, 10)
                .build();
        icon = guiHelper.createDrawableIngredient(new ItemStack(GDBlocks.purifier_idle));
        localizedName = I18n.format("gui.gaiadimension.category.purifying");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getModName() {
        return GaiaDimension.NAME;
    }

    @Override
    public String getUid() {
        return GDRecipeCategoryUid.PURIFY;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, PurifyingRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 29, 2);
        guiItemStacks.init(outputSlot1, false, 14, 36);
        guiItemStacks.init(outputSlot2, false, 44, 36);

        guiItemStacks.set(ingredients);
    }
}
