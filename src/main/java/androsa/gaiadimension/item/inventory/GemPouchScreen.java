package androsa.gaiadimension.item.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GemPouchScreen extends ContainerScreen<GemPouchContainer> implements IHasContainer<GemPouchContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/gem_pouch.png");

    public GemPouchScreen(GemPouchContainer pouch, PlayerInventory player, ITextComponent textComponent) {
        super(pouch, player, textComponent);
        imageHeight = 182;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, title, 48, 6, 0xFFFFFF);
        this.font.draw(stack, inventory.getDisplayName(), 8.0F, this.imageHeight - 93 + 2, 0xFFFFFF);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(textureLoc);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
