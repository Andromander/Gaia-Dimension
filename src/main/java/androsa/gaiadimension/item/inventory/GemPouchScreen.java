package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.GaiaDimensionMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GemPouchScreen extends AbstractContainerScreen<GemPouchContainer> {

    private static final ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/gem_pouch.png");

    public GemPouchScreen(GemPouchContainer pouch, Inventory player, Component textComponent) {
        super(pouch, player, textComponent);
        imageHeight = 182;
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
        stack.drawString(font, title, 48, 6, 0xFFFFFF);
        stack.drawString(font, playerInventoryTitle, 8, this.imageHeight - 93 + 2, 0xFFFFFF);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureLoc);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        stack.blit(textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
