package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.PurifierMenu;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

//TODO: recipe book?
public class PurifierScreen extends AbstractContainerScreen<PurifierMenu> {

    private static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/purifier.png");
    private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
            new RecipeBookComponent.TabInfo(ModItems.goldstone.get(), ModRecipes.PURIFYING_CATEGORY.get()));

    public PurifierScreen(PurifierMenu purifier, Inventory invPlayer, Component component) {
        super(purifier, invPlayer, component, 176, 207);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor stack, int mouseX, int mouseY) {
        stack.text(font, title, (imageWidth / 2 - font.width(title.getString()) / 2), 6, 0xFFF0F0F0, false);
        stack.text(font, playerInventoryTitle, 8, (imageHeight - 96 + 2), 0xFFF0F0F0, false);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor stack, int x, int z, float partialticks) {
        super.extractBackground(stack, x, z, partialticks);
        int k = leftPos;
        int l = topPos;
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, k, l, 0, 0, imageWidth, imageHeight, 256, 256);
        int i1;

        if (menu.isBurning()) {
            i1 = menu.getTimeLeftScaled();
            stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, k + 64, l + 81, 176, 22, 47, i1 + 1, 256, 256);

        }

        i1 = menu.getTimeLeft();
        stack.blit(RenderPipelines.GUI_TEXTURED, textureLoc, k + 77, l + 61 + 12 - i1, 176, 20 - i1, 22, i1 + 2, 256, 256);
    }
}
