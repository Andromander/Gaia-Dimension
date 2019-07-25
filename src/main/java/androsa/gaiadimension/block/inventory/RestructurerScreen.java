package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.RestructurerContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RestructurerScreen extends ContainerScreen<RestructurerContainer> implements IHasContainer<RestructurerContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/glitter_furnace.png");

    public RestructurerScreen(RestructurerContainer container, PlayerInventory invPlayer, ITextComponent textComponent) {
        super(container, invPlayer, textComponent);

        xSize = 175;
        ySize = 196;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        font.drawString(title.getFormattedText(), (float)(xSize / 2 - font.getStringWidth(title.getFormattedText()) / 2), 6.0F, 0xF0F0F0);
        font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(ySize -96 + 2), 0xF0F0F0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        blit(k, l, 0, 0, xSize, ySize);
        int i1;

        if (container.isBurning()) {
            i1 = container.getTimeLeft();
            blit(k + 68, l + 19 + 12 - i1, 176, 14 - i1, 38, i1 + 2);
        }

        i1 = container.getTimeLeftScaled();
        blit(k + 80, l + 51, 176, 16, 16, i1);
    }
}
