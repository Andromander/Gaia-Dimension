package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.compat.jei.GaiaRecipeTypes;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.registry.registration.ModBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class RestructuringCategory extends RestructurerRecipeCategory<RecipeHolder<RestructurerRecipe>> {

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/jei/recipe2output.png");

    public RestructuringCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(BACKGROUND, 0, 0, 75, 55)
                .setTextureSize(76, 56)
                .addPadding(0, 0, 0, 10)
                .build();
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.restructurer.get()));
        localizedName = Component.translatable("gui.gaiadimension.category.restructuring");
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
    public RecipeType<RecipeHolder<RestructurerRecipe>> getRecipeType() {
        return GaiaRecipeTypes.RESTRUCTURE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder layout, RecipeHolder<RestructurerRecipe> recipe, IFocusGroup focuses) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) {
            throw new NullPointerException("level must not be null.");
        }
        RegistryAccess registry = level.registryAccess();

        layout.addSlot(RecipeIngredientRole.INPUT, 30, 3).addIngredients(recipe.value().getIngredients().get(0));
        layout.addSlot(RecipeIngredientRole.OUTPUT, 15, 37).addItemStack(recipe.value().getResultItem(registry));
        layout.addSlot(RecipeIngredientRole.OUTPUT, 45, 37).addItemStack(recipe.value().getByproduct());
    }

    @Override
    public void draw(RecipeHolder<RestructurerRecipe> recipe, IRecipeSlotsView view, GuiGraphics graphics, double mouseX, double mouseY) {
        float experience = recipe.value().getExperience();
        if (experience > 0.0F) {
            Component experienceString = Component.translatable("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(experienceString);
            graphics.drawString(fontRenderer, experienceString, (this.background.getWidth() - stringWidth), 0, -8355712, false);
        }
    }

    @Override
    public boolean isHandled(RecipeHolder<RestructurerRecipe> recipe) {
        return !recipe.value().isSpecial();
    }
}
