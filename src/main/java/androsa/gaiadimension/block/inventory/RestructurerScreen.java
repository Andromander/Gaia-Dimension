package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.RestructurerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
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

        imageWidth = 175;
        imageHeight = 196;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        font.draw(stack, title, (float)(imageWidth / 2 - font.width(title.getString()) / 2), 6.0F, 0xF0F0F0);
        font.draw(stack, inventory.getDisplayName(), 8.0F, (float)(imageHeight -96 + 2), 0xF0F0F0);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(textureLoc);
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        blit(stack, k, l, 0, 0, imageWidth, imageHeight);
        int i1;

        if (menu.isBurning()) {
            i1 = menu.getTimeLeft();
            blit(stack, k + 68, l + 19 + 12 - i1, 176, 14 - i1, 38, i1 + 2);
        }

        i1 = menu.getTimeLeftScaled();
        blit(stack, k + 80, l + 51, 176, 16, 16, i1);
    }
}
