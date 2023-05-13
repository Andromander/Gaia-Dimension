package androsa.gaiadimension.block.screen;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
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
public class GaiaStoneFurnaceScreen extends AbstractContainerScreen<GaiaStoneFurnaceMenu> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/gaia_stone_furnace.png");

    public GaiaStoneFurnaceScreen(GaiaStoneFurnaceMenu container, Inventory playerInventory, Component textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureLoc);
        int i = this.leftPos;
        int j = this.topPos;
        blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if ((this.menu).isBurning()) {
            int k = (this.menu).getBurnLeftScaled();
            blit(stack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = (this.menu).getCookProgressionScaled();
        blit(stack, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseZ, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseZ, partialTicks);
        this.renderTooltip(stack, mouseX, mouseZ);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        String s = this.title.getString();
        this.font.draw(stack, s, (float)(this.imageWidth / 2 - this.font.width(s) / 2), 6.0F, 0xD0D0D0);
        this.font.draw(stack, this.playerInventoryTitle, 8.0F, (float)(this.imageHeight - 96 + 2), 0xD0D0D0);
    }
}
