package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.SmallCrateContainer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class SmallCrateScreen extends AbstractContainerScreen<SmallCrateContainer> {

    private static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/small_crate.png");

    public SmallCrateScreen(SmallCrateContainer container, Inventory playerInv, Component textComponent) {
        super(container, playerInv, textComponent);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor stack, int mouseX, int mouseY) {
        stack.text(font, title, 8, 6, 0xFFBEBEBE, false);
        stack.text(font, playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 0xFFBEBEBE, false);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor stack, int mouseX, int mouseY, float partialTicks) {
        super.extractBackground(stack, mouseX, mouseY, partialTicks);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }
}
