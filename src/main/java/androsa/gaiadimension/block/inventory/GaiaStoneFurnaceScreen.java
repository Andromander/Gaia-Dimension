package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GaiaStoneFurnaceScreen extends ContainerScreen<GaiaStoneFurnaceContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/gaia_stone_furnace.png");

    public GaiaStoneFurnaceScreen(GaiaStoneFurnaceContainer container, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(textureLoc);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(stack, i, j, 0, 0, this.xSize, this.ySize);
        if ((this.container).isBurning()) {
            int k = (this.container).getBurnLeftScaled();
            this.blit(stack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = (this.container).getCookProgressionScaled();
        this.blit(stack, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseZ, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseZ, partialTicks);
        this.renderHoveredTooltip(stack, mouseX, mouseZ);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        String s = this.title.getString();
        this.font.drawString(stack, s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 0xD0D0D0);
        this.font.drawString(stack, this.playerInventory.getDisplayName().getString(), 8.0F, (float)(this.ySize - 96 + 2), 0xD0D0D0);
    }


}
