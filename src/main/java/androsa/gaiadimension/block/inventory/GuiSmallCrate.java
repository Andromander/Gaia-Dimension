package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.ContainerSmallCrate;
import androsa.gaiadimension.block.tileentity.TileEntitySmallCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSmallCrate extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/small_crate.png");
    private TileEntitySmallCrate smallCrate;

    public GuiSmallCrate(InventoryPlayer playerInventoryIn, TileEntitySmallCrate crate) {
        super(new ContainerSmallCrate(playerInventoryIn, crate));
        smallCrate = crate;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = smallCrate.hasCustomName() ? smallCrate.getName() : I18n.format(smallCrate.getName());
        this.fontRenderer.drawString(name, 8, 6, 0xBEBEBE);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 0xBEBEBE);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(textureLoc);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
