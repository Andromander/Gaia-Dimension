package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.LargeCrateContainer;
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
public class LargeCrateScreen extends ContainerScreen<LargeCrateContainer> implements IHasContainer<LargeCrateContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/large_crate.png");

    public LargeCrateScreen(LargeCrateContainer container, PlayerInventory playerInventoryIn, ITextComponent component) {
        super(container, playerInventoryIn, component);
        imageWidth = 184;
        imageHeight = 222;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, title, 8, 6, 0xBEBEBE);
        this.font.draw(stack, inventory.getDisplayName(), 8, this.imageHeight - 96 + 2, 0xBEBEBE);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(textureLoc);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
