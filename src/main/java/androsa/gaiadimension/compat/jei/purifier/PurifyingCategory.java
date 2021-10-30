package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.GDRecipeCategoryUid;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurifyingCategory extends PurifierRecipeCategory<PurifierRecipe> {

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final ResourceLocation backgroundimage = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/jei/recipe2output.png");

    public PurifyingCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(backgroundimage, 0, 0, 75, 55)
                .setTextureSize(76, 56).addPadding(0, 0, 0, 10)
                .build();
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.purifier.get()));
        localizedName = new TranslatableComponent("gui.gaiadimension.category.purifying");
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
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public ResourceLocation getUid() {
        return GDRecipeCategoryUid.PURIFY;
    }

    @Override
    public Class<? extends PurifierRecipe> getRecipeClass() {
        return PurifierRecipe.class;
    }

    @Override
    public void setIngredients(PurifierRecipe recipe, IIngredients ingredients) {
        List<ItemStack> output = new ArrayList<>();
        Collections.addAll(output, recipe.getResultItem(), recipe.getByproduct());

        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutputs(VanillaTypes.ITEM, output);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, PurifierRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 29, 2);
        guiItemStacks.init(outputSlot1, false, 14, 36);
        guiItemStacks.init(outputSlot2, false, 44, 36);

        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(PurifierRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
        float experience = recipe.getExperience();
        if (experience > 0.0F) {
            Component experienceString = new TranslatableComponent("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(experienceString);
            fontRenderer.draw(stack, experienceString, (float)(this.background.getWidth() - stringWidth), 0.0F, -8355712);
        }
    }
}
