package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.compat.jei.GDRecipeCategoryUid;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class GlitterFuelCategory extends RestructurerRecipeCategory<GlitterFuelRecipe> {

    private final IDrawableStatic background;
    private final IDrawableStatic flame;
    private final String localizedName;
    private final ResourceLocation backgroundimage = new ResourceLocation("jei:textures/gui/gui_vanilla.png");
    private final ResourceLocation flameimage = new ResourceLocation(GaiaDimension.MODID, "textures/gui/jei/glittering.png");

    public GlitterFuelCategory(IGuiHelper guiHelper) {
        //super(guiHelper);
        background = guiHelper.drawableBuilder(backgroundimage, 0, 134, 18, 34)
                .addPadding(0, 0, 0, 95)
                .build();
        flame = guiHelper.drawableBuilder(flameimage, 0, 0, 14, 14)
                .setTextureSize(14, 14)
                .build();

        localizedName = I18n.format("gui.gaiadimension.category.glitterFuel");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public String getUid() {
        return GDRecipeCategoryUid.GOLD;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getModName() {
        return GaiaDimension.NAME;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return flame;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, GlitterFuelRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(glitterFuelSlot, true, 0, 16);
        guiItemStacks.set(ingredients);
    }
}
