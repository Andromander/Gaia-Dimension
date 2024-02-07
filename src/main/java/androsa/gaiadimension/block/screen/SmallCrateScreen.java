package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.SmallCrateContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SmallCrateScreen extends AbstractContainerScreen<SmallCrateContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/small_crate.png");

    public SmallCrateScreen(SmallCrateContainer container, Inventory playerInv, Component textComponent) {
        super(container, playerInv, textComponent);
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
        stack.drawString(font, title, 8, 6, 0xBEBEBE);
        stack.drawString(font, playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 0xBEBEBE);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        stack.blit(textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
