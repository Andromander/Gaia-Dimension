package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class GaiaStoneFurnaceScreen extends AbstractContainerScreen<GaiaStoneFurnaceMenu> {

    private static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/gaia_stone_furnace.png");

    public GaiaStoneFurnaceScreen(GaiaStoneFurnaceMenu container, Inventory playerInventory, Component textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor stack, int mouseX, int mouseY, float partialTicks) {
        super.extractBackground(stack, mouseX, mouseY, partialTicks);
        int i = this.leftPos;
        int j = this.topPos;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        if ((this.menu).isBurning()) {
            int k = (this.menu).getBurnLeftScaled();
            stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1, 256, 256);
        }

        int l = (this.menu).getCookProgressionScaled();
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, i + 79, j + 34, 176, 14, l + 1, 16, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor stack, int mouseX, int mouseY) {
        String s = this.title.getString();
        stack.text(font, s, (this.imageWidth / 2 - this.font.width(s) / 2), 6, 0xFFD0D0D0, false);
        stack.text(font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 0xFFD0D0D0, false);
    }
}
