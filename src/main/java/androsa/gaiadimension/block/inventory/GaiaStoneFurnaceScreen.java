package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
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
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(textureLoc);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if ((this.menu).isBurning()) {
            int k = (this.menu).getBurnLeftScaled();
            this.blit(stack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = (this.menu).getCookProgressionScaled();
        this.blit(stack, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseZ, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseZ, partialTicks);
        this.renderTooltip(stack, mouseX, mouseZ);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        String s = this.title.getString();
        this.font.draw(stack, s, (float)(this.imageWidth / 2 - this.font.width(s) / 2), 6.0F, 0xD0D0D0);
        this.font.draw(stack, this.inventory.getDisplayName().getString(), 8.0F, (float)(this.imageHeight - 96 + 2), 0xD0D0D0);
    }
}
