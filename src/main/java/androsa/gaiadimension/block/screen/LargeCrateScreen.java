package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.LargeCrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LargeCrateScreen extends AbstractContainerScreen<LargeCrateMenu> {

    private static final ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/large_crate.png");

    public LargeCrateScreen(LargeCrateMenu container, Inventory playerInventoryIn, Component component) {
        super(container, playerInventoryIn, component);
        imageWidth = 184;
        imageHeight = 222;
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
        stack.drawString(font, title, 8, 6, 0xBEBEBE);
        stack.drawString(font, playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0xBEBEBE);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        stack.blit(textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
