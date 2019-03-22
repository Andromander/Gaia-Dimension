package androsa.gaiadimension.item.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiGemPouch extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/gem_pouch.png");
    private final InventoryGemPouch gemPouch;

    public GuiGemPouch(ContainerGemPouch pouch) {
        super(pouch);
        gemPouch = pouch.getItemInventory();
        ySize = 182;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = gemPouch.hasCustomName() ? gemPouch.getName() : I18n.format(gemPouch.getName());
        this.fontRenderer.drawString(name, 48, 6, 0xFFFFFF);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 93 + 2, 0xFFFFFF);
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
