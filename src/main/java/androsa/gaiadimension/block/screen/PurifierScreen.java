package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.PurifierMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PurifierScreen extends AbstractContainerScreen<PurifierMenu> {

    private static final ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/purifier.png");

    public PurifierScreen(PurifierMenu purifier, Inventory invPlayer, Component component) {
        super(purifier, invPlayer, component);

        imageWidth = 176;
        imageHeight = 207;
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
        stack.drawString(font, title, (imageWidth / 2 - font.width(title.getString()) / 2), 6, 0xF0F0F0);
        stack.drawString(font, playerInventoryTitle, 8, (imageHeight - 96 + 2), 0xF0F0F0);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float par1, int par2, int par3) {
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        stack.blit(textureLoc, k, l, 0, 0, imageWidth, imageHeight);
        int i1;

        if (menu.isBurning()) {
            i1 = menu.getTimeLeftScaled();
            stack.blit(textureLoc, k + 64, l + 81, 176, 22, 47, i1 + 1);

        }

        i1 = menu.getTimeLeft();
        stack.blit(textureLoc, k + 77, l + 61 + 12 - i1, 176, 20 - i1, 22, i1 + 2);
    }
}
