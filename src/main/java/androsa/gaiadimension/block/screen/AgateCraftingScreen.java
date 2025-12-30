package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.NullMarked;

//TODO: Recipe Book?
@NullMarked
public class AgateCraftingScreen extends AbstractContainerScreen<AgateCraftingTableMenu> {
    private static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/crafting_table.png");

    public AgateCraftingScreen(AgateCraftingTableMenu container, Inventory player, Component textComponent) {
        super(container, player, textComponent);
    }

    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int x, int y) {
        stack.drawString(font, title, 28, 6, 0xF0F0F0);
        stack.drawString(font, playerInventoryTitle, 8, (imageHeight - 96 + 3), 0xF0F0F0);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTickTime, int x, int y) {
        int left = leftPos;
        int top = topPos;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, left, top, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}
