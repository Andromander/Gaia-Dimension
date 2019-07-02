package androsa.gaiadimension.item.inventory;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GemPouchScreen extends ContainerScreen<GemPouchContainer> implements IHasContainer<GemPouchContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/gem_pouch.png");

    public GemPouchScreen(GemPouchContainer pouch, PlayerInventory player, ITextComponent textComponent) {
        super(pouch, player, textComponent);
        ySize = 182;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(title.getFormattedText(), 48, 6, 0xFFFFFF);
        this.font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, this.ySize - 93 + 2, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(textureLoc);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
    }
}
