package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.GDRecipeCategoryUid;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class GlitterFuelCategory extends RestructurerRecipeCategory<GlitterFuelRecipe> {

    private final IDrawableStatic background;
    private final IDrawableStatic flame;
    private final String localizedName;
    private final ResourceLocation backgroundimage = new ResourceLocation("jei:textures/gui/gui_vanilla.png");
    private final ResourceLocation flameimage = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/jei/glittering.png");

    public GlitterFuelCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(backgroundimage, 0, 134, 18, 34)
                .addPadding(0, 0, 0, 95)
                .build();
        flame = guiHelper.drawableBuilder(flameimage, 0, 0, 14, 14)
                .setTextureSize(14, 14)
                .build();

        localizedName = I18n.format("gui.gaiadimension.category.glitter_fuel");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public ResourceLocation getUid() {
        return GDRecipeCategoryUid.GOLD;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public Class<? extends GlitterFuelRecipe> getRecipeClass() {
        return GlitterFuelRecipe.class;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return flame;
    }

    @Override
    public void setIngredients(GlitterFuelRecipe glitterFuelRecipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, glitterFuelRecipe.getInputs());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, GlitterFuelRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(glitterFuelSlot, true, 0, 16);
        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(GlitterFuelRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
        IDrawableAnimated flame = recipe.getFlame();
        flame.draw(stack, 1, 0);
        Minecraft minecraft = Minecraft.getInstance();
        String smeltCountString = recipe.getGlitterCountString();
        minecraft.fontRenderer.drawString(stack, smeltCountString, 24.0F, 13.0F, -8355712);
    }
}