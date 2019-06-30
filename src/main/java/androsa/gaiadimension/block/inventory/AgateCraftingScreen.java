package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.container.AgateCraftingTableContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AgateCraftingScreen extends CraftingScreen {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODID, "textures/gui/crafting_table.png");

    public AgateCraftingScreen(AgateCraftingTableContainer container, PlayerInventory player, ITextComponent textComponent) {
        super(container, player, textComponent);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTickTime, int x, int y) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int left = guiLeft;
        int top = (guiTop - ySize) / 2;
        blit(left, top, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        font.drawString(I18n.format(title.getFormattedText()), 28.0F, 6.0F, 0x000000);
        font.drawString(I18n.format(playerInventory.getDisplayName().getFormattedText()), 8.0F, (float)(ySize - 96 + 3), 0x000000);
    }
}
