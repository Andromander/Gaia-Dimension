package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
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
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor stack, int x, int y) {
        stack.text(font, title, 28, 6, 0xFFF0F0F0, false);
        stack.text(font, playerInventoryTitle, 8, (imageHeight - 96 + 3), 0xFFF0F0F0, false);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor stack, int x, int y, float partialTickTime) {
        super.extractBackground(stack, x, y, partialTickTime);
        int left = leftPos;
        int top = topPos;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, left, top, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}
