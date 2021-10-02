package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.PurifierMenu;
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
public class PurifierScreen extends AbstractContainerScreen<PurifierMenu> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/purifier.png");

    public PurifierScreen(PurifierMenu purifier, Inventory invPlayer, Component component) {
        super(purifier, invPlayer, component);

        imageWidth = 176;
        imageHeight = 207;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        font.draw(stack, title, (float)(imageWidth / 2 - font.width(title.getString()) / 2), 6, 0xF0F0F0);
        font.draw(stack, playerInventoryTitle, 8.0F, (float)(imageHeight - 96 + 2), 0xF0F0F0);
    }

    @Override
    protected void renderBg(PoseStack stack, float par1, int par2, int par3) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureLoc);
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        blit(stack, k, l, 0, 0, imageWidth, imageHeight);
        int i1;

        if (menu.isBurning()) {
            i1 = menu.getTimeLeftScaled();
            blit(stack, k + 64, l + 81, 176, 22, 47, i1 + 1);

        }

        i1 = menu.getTimeLeft();
        blit(stack, k + 77, l + 62 + 12 - i1, 176, 21 - i1, 22, i1 + 2);
    }
}
