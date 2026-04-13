package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class GemPouchScreen extends AbstractContainerScreen<GemPouchContainer> {

    private static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/gem_pouch.png");

    public GemPouchScreen(GemPouchContainer pouch, Inventory player, Component textComponent) {
        super(pouch, player, textComponent, 176, 182);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor stack, int mouseX, int mouseY) {
        stack.text(font, title, 48, 6, 0xFFFFFFFF, false);
        stack.text(font, playerInventoryTitle, 8, this.imageHeight - 93 + 2, 0xFFFFFFFF, false);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor stack, int mouseX, int mouseY, float partialTicks) {
        super.extractBackground(stack, mouseX, mouseY, partialTicks);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }
}
