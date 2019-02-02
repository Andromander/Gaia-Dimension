package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.ContainerPurifier;
import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPurifier extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/purifier.png");
    private TileEntityPurifier tilePurifier;

    public GuiPurifier(InventoryPlayer invPlayer, TileEntityPurifier purifier) {
        super(new ContainerPurifier(invPlayer, purifier));
        tilePurifier = purifier;

        xSize = 176;
        ySize = 207;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = tilePurifier.hasCustomName() ? tilePurifier.getName() : I18n.format(tilePurifier.getName());
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x000000);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize -96 + 2, 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;

        if (tilePurifier.isBurning()) {
            i1 = tilePurifier.getClearTimeRemainingScaled(22);
            drawTexturedModalRect(k + 77, l + 62 + 12 - i1, 176, 21 - i1, 22, i1 + 2);
        }

        i1 = tilePurifier.getClearProgressScaled(25);
        drawTexturedModalRect(k + 64, l + 81, 176, 22, 47, i1 + 1);
    }
}
