package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.PurifierContainer;
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
public class PurifierScreen extends ContainerScreen<PurifierContainer> implements IHasContainer<PurifierContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/purifier.png");

    public PurifierScreen(PurifierContainer purifier, PlayerInventory invPlayer, ITextComponent component) {
        super(purifier, invPlayer, component);

        xSize = 176;
        ySize = 207;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        font.func_243246_a(stack, title, (float)(xSize / 2 - font.getStringWidth(title.getString()) / 2), 6, 0xF0F0F0);
        font.func_243246_a(stack, playerInventory.getDisplayName(), 8.0F, (float)(ySize - 96 + 2), 0xF0F0F0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float par1, int par2, int par3) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        blit(stack, k, l, 0, 0, xSize, ySize);
        int i1;

        if (container.isBurning()) {
            i1 = container.getTimeLeftScaled();
            blit(stack, k + 64, l + 81, 176, 22, 47, i1 + 1);

        }

        i1 = container.getTimeLeft();
        blit(stack, k + 77, l + 62 + 12 - i1, 176, 21 - i1, 22, i1 + 2);
    }
}
