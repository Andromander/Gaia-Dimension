package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.GaiaRecipeTypes;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

public class GlitterFuelCategory extends RestructurerRecipeCategory<GlitterFuelRecipe> {

    private final IDrawableStatic background;
    private final IDrawableStatic staticFlame;
    private final Component localizedName;
    private final LoadingCache<Integer, IDrawableAnimated> cache;
    private static final ResourceLocation BACKGROUND = new ResourceLocation("jei:textures/jei/gui/gui_vanilla.png");
    private static final ResourceLocation FLAME = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/jei/glittering.png");

    public GlitterFuelCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(BACKGROUND, 0, 134, 18, 34)
                .addPadding(0, 0, 0, 96)
                .build();
        staticFlame = guiHelper.drawableBuilder(FLAME, 0, 0, 14, 14)
                .setTextureSize(14, 14)
                .build();
        localizedName = Component.translatable("gui.gaiadimension.category.glitter_fuel");

        this.cache = CacheBuilder.newBuilder()
                .maximumSize(25)
                .build(new CacheLoader<>() {
                    @Override
                    public IDrawableAnimated load(Integer time) {
                        return guiHelper.drawableBuilder(FLAME, 0, 0, 14, 14)
                                .setTextureSize(14, 14)
                                .buildAnimated(time, IDrawableAnimated.StartDirection.TOP, true);
                    }
                });
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public RecipeType<GlitterFuelRecipe> getRecipeType() {
        return GaiaRecipeTypes.GOLD;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return staticFlame;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder layout, GlitterFuelRecipe recipe, IFocusGroup focus) {
        layout.addSlot(RecipeIngredientRole.INPUT, 1, 17).addItemStacks(recipe.getInputs());
    }

    @Override
    public void draw(GlitterFuelRecipe recipe, IRecipeSlotsView view, GuiGraphics graphics, double mouseX, double mouseY) {
        int burnTime = recipe.getBurnTime();
        IDrawableAnimated flame = cache.getUnchecked(burnTime);
        flame.draw(graphics, 1, 0);
        Minecraft minecraft = Minecraft.getInstance();
        Component smeltCountString = createFuelCountText(burnTime);
        graphics.drawString(minecraft.font, smeltCountString, 24, 13, -8355712, false);
    }

    private static Component createFuelCountText(int burnTime) {
        if (burnTime == 200) {
            return Component.translatable("gui.gaiadimension.category.fuel.single_average");
        } else {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumFractionDigits(2);
            String smeltCount = numberInstance.format(burnTime / 200.0F);
            return Component.translatable("gui.gaiadimension.category.fuel.smelt_average", smeltCount);
        }
    }
}