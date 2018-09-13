package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.ContainerLargeCrate;
import androsa.gaiadimension.block.tileentity.TileEntityLargeCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLargeCrate extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/large_crate.png");
    private TileEntityLargeCrate largeCrate;

    public GuiLargeCrate(InventoryPlayer playerInventoryIn, TileEntityLargeCrate crate) {
        super(new ContainerLargeCrate(playerInventoryIn, crate));
        largeCrate = crate;
        xSize = 184;
        ySize = 222;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = largeCrate.hasCustomName() ? largeCrate.getName() : I18n.format(largeCrate.getName());
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
