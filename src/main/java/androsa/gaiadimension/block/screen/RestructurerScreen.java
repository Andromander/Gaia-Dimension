package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.RestructurerMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RestructurerScreen extends AbstractContainerScreen<RestructurerMenu> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/glitter_furnace.png");

    public RestructurerScreen(RestructurerMenu container, Inventory invPlayer, Component textComponent) {
        super(container, invPlayer, textComponent);

        imageWidth = 175;
        imageHeight = 196;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        font.draw(stack, title, (float)(imageWidth / 2 - font.width(title.getString()) / 2), 6.0F, 0xF0F0F0);
        font.draw(stack, playerInventoryTitle, 8.0F, (float)(imageHeight -96 + 2), 0xF0F0F0);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureLoc);
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
