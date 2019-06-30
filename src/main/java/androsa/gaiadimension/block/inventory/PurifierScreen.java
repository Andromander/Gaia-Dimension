package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.PurifierContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PurifierScreen extends ContainerScreen<PurifierContainer> implements IHasContainer<PurifierContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/purifier.png");

    public PurifierScreen(PurifierContainer purifier, PlayerInventory invPlayer, ITextComponent component) {
        super(purifier, invPlayer, component);

        xSize = 176;
        ySize = 207;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        font.drawString(title.getFormattedText(), (float)(xSize / 2 - font.getStringWidth(title.getFormattedText()) / 2), 6, 0x000000);
        font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(ySize - 96 + 2), 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        blit(k, l, 0, 0, xSize, ySize);
        int i1;

        if (container.isBurning()) {
            i1 = container.getTimeLeftScaled();
            blit(k + 77, l + 62 + 12 - i1, 176, 21 - i1, 22, i1 + 2);
        }

        i1 = container.getTimeLeft();
        blit(k + 64, l + 81, 176, 22, 47, i1 + 1);
    }
}
