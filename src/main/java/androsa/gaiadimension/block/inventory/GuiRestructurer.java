package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.ContainerRestructurer;
import androsa.gaiadimension.block.tileentity.TileEntityRestructurer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRestructurer extends GuiContainer {

    //TODO: A lot of sizing might be needed
    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/glitter_furnace.png");
    private TileEntityRestructurer tileGlitterFurnace;

    public GuiRestructurer(InventoryPlayer invPlayer, TileEntityRestructurer glitterFurnace) {
        super(new ContainerRestructurer(invPlayer, glitterFurnace));
        tileGlitterFurnace = glitterFurnace;

        xSize = 175;
        ySize = 196;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = tileGlitterFurnace.hasCustomName() ? tileGlitterFurnace.getName() : I18n.format(tileGlitterFurnace.getName());
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0xF0F0F0);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize -96 + 2, 0xF0F0F0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;

        if (tileGlitterFurnace.isBurning()) {
            i1 = tileGlitterFurnace.getChangeTimeRemainingScaled(12);
            drawTexturedModalRect(k + 68, l + 19 + 12 - i1, 176, 14 - i1, 38, i1 + 2);
        }

        i1 = tileGlitterFurnace.getChangeProgressScaled(24);
        drawTexturedModalRect(k + 80, l + 51, 176, 16, 16, i1);
    }
}
