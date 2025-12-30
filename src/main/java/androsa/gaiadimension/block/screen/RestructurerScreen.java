package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.RestructurerMenu;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

//TODO: recipe book?
public class RestructurerScreen extends AbstractContainerScreen<RestructurerMenu> {

    private static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/glitter_furnace.png");
    private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
            new RecipeBookComponent.TabInfo(ModItems.stibnite.get(), ModRecipes.RESTRUCTURING_CATEGORY.get()));

    public RestructurerScreen(RestructurerMenu container, Inventory invPlayer, Component textComponent) {
        super(container, invPlayer, textComponent);

        imageWidth = 175;
        imageHeight = 196;
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
        stack.drawString(font, title, (imageWidth / 2 - font.width(title.getString()) / 2), 6, 0xF0F0F0);
        stack.drawString(font, playerInventoryTitle, 8, (imageHeight -96 + 2), 0xF0F0F0);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, k, l, 0, 0, imageWidth, imageHeight, 256, 256);
        int i1;

        if (menu.isBurning()) {
            i1 = menu.getTimeLeft();
            stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, k + 68, l + 19 + 12 - i1, 176, 14 - i1, 38, i1 + 2, 256, 256);
        }

        i1 = menu.getTimeLeftScaled();
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, k + 80, l + 51, 176, 16, 16, i1, 256, 256);
    }
}
