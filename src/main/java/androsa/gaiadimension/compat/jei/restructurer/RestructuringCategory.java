package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.GDRecipeCategoryUid;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestructuringCategory extends RestructurerRecipeCategory<RestructurerRecipe> {

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    private final ResourceLocation backgroundimage = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/jei/recipe2output.png");

    public RestructuringCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(backgroundimage, 0, 0, 75, 55)
                .setTextureSize(76, 56).addPadding(0, 0, 0, 10)
                .build();
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.restructurer.get()));
        localizedName = I18n.format("gui.gaiadimension.category.restructuring");
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
    public ResourceLocation getUid() {
        return GDRecipeCategoryUid.RESTRUCTURE;
    }

    @Override
    public Class<? extends RestructurerRecipe> getRecipeClass() {
        return RestructurerRecipe.class;
    }

    @Override
    public void setIngredients(RestructurerRecipe recipe, IIngredients ingredients) {
        List<ItemStack> output = new ArrayList<>();
        Collections.addAll(output, recipe.getRecipeOutput(), recipe.getByproduct());

        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutputs(VanillaTypes.ITEM, output);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RestructurerRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 29, 2);
        guiItemStacks.init(outputSlot1, false, 14, 36);
        guiItemStacks.init(outputSlot2, false, 44, 36);

        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(RestructurerRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
        float experience = recipe.getExperience();
        if (experience > 0.0F) {
            String experienceString = I18n.format("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringWidth(experienceString);
            fontRenderer.drawString(stack, experienceString, (float)(this.background.getWidth() - stringWidth), 0.0F, -8355712);
        }
    }
}
