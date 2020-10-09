package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.RestructurerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RestructurerScreen extends ContainerScreen<RestructurerContainer> implements IHasContainer<RestructurerContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/glitter_furnace.png");

    public RestructurerScreen(RestructurerContainer container, PlayerInventory invPlayer, ITextComponent textComponent) {
        super(container, invPlayer, textComponent);

        xSize = 175;
        ySize = 196;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        font.func_243248_b(stack, title, (float)(xSize / 2 - font.getStringWidth(title.getString()) / 2), 6.0F, 0xF0F0F0);
        font.func_243248_b(stack, playerInventory.getDisplayName(), 8.0F, (float)(ySize -96 + 2), 0xF0F0F0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        blit(stack, k, l, 0, 0, xSize, ySize);
        int i1;

        if (container.isBurning()) {
            i1 = container.getTimeLeft();
            blit(stack, k + 68, l + 19 + 12 - i1, 176, 14 - i1, 38, i1 + 2);
        }

        i1 = container.getTimeLeftScaled();
        blit(stack, k + 80, l + 51, 176, 16, 16, i1);
    }
}
