package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.ContainerGlitterFurnace;
import androsa.gaiadimension.block.tileentity.TileEntityGlitterFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGlitterFurnace extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/glitter_furnace.png");
    private TileEntityGlitterFurnace tileGlitterFurnace;

    public GuiGlitterFurnace(InventoryPlayer invPlayer, TileEntityGlitterFurnace glitterFurnace) {
        super(new ContainerGlitterFurnace(invPlayer, glitterFurnace));
        tileGlitterFurnace = glitterFurnace;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = tileGlitterFurnace.hasCustomName() ? tileGlitterFurnace.getName() : I18n.format(tileGlitterFurnace.getName(), new Object[0]);
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x000000);
        fontRenderer.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize -96 + 2, 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;

        if (tileGlitterFurnace.isGlittering()) {
            i1 = tileGlitterFurnace.getGlitterTimeRemainingScaled(12);
            drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = tileGlitterFurnace.getGlitterProgressScaled(24);
        drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}
