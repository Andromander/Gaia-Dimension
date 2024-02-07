package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GaiaStoneFurnaceScreen extends AbstractContainerScreen<GaiaStoneFurnaceMenu> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/gaia_stone_furnace.png");

    public GaiaStoneFurnaceScreen(GaiaStoneFurnaceMenu container, Inventory playerInventory, Component textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = this.topPos;
        stack.blit(textureLoc, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if ((this.menu).isBurning()) {
            int k = (this.menu).getBurnLeftScaled();
            stack.blit(textureLoc, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = (this.menu).getCookProgressionScaled();
        stack.blit(textureLoc, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseZ, float partialTicks) {
        this.renderBackground(stack, mouseX, mouseZ, partialTicks);
        super.render(stack, mouseX, mouseZ, partialTicks);
        this.renderTooltip(stack, mouseX, mouseZ);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
        String s = this.title.getString();
        stack.drawString(font, s, (this.imageWidth / 2 - this.font.width(s) / 2), 6, 0xD0D0D0);
        stack.drawString(font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 0xD0D0D0);
    }
}
