package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AgateCraftingScreen extends AbstractContainerScreen<AgateCraftingTableMenu> implements RecipeUpdateListener {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/crafting_table.png");

    public AgateCraftingScreen(AgateCraftingTableMenu container, Inventory player, Component textComponent) {
        super(container, player, textComponent);
    }

    protected void init() {
        super.init();
    }

    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int x, int y) {
        font.draw(stack, title, 28.0F, 6.0F, 0xF0F0F0);
        font.draw(stack, playerInventoryTitle, 8.0F, (float)(imageHeight - 96 + 3), 0xF0F0F0);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTickTime, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureLoc);
        int left = leftPos;
        int top = topPos;
        blit(stack, left, top, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void recipesUpdated() { }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return null;
    }
}
