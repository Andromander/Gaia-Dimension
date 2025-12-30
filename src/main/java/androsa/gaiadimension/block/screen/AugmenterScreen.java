package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.AugmenterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class AugmenterScreen extends ItemCombinerScreen<AugmenterMenu> {

    private static final Identifier LOCATION = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/gui/augmenter.png");

    public AugmenterScreen(AugmenterMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, LOCATION);
    }

    protected void renderLabels(GuiGraphics gui, int x, int y) {
        gui.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xFFBBBBBB, false);
        gui.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0xFFBBBBBB, false);
    }

    @Override
    protected void renderErrorIcon(GuiGraphics p_281990_, int p_266822_, int p_267045_) {
        //nah lol
    }
}
