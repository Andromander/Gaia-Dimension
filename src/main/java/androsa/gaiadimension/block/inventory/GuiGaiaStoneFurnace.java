package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.ContainerGaiaStoneFurnace;
import androsa.gaiadimension.block.tileentity.TileEntityGaiaStoneFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGaiaStoneFurnace extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/gaia_stone_furnace.png");
    private TileEntityGaiaStoneFurnace tileFurnace;

    public GuiGaiaStoneFurnace(InventoryPlayer invPlayer, TileEntityGaiaStoneFurnace gaiaStoneFurnace) {
        super(new ContainerGaiaStoneFurnace(invPlayer, gaiaStoneFurnace));
        tileFurnace = gaiaStoneFurnace;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = tileFurnace.hasCustomName() ? tileFurnace.getName() : I18n.format(tileFurnace.getName());
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0xFFFFFF);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;

        if (tileFurnace.isBurning()) {
            i1 = tileFurnace.getSmeltTimeRemainingScaled(12);
            drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = tileFurnace.getSmeltProgressScaled(24);
        drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}
