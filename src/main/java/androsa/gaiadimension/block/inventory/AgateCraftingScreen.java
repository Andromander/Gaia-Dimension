package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.container.AgateCraftingTableContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AgateCraftingScreen extends ContainerScreen<AgateCraftingTableContainer> implements IRecipeShownListener {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/crafting_table.png");

    public AgateCraftingScreen(AgateCraftingTableContainer container, PlayerInventory player, ITextComponent textComponent) {
        super(container, player, textComponent);
    }

    protected void init() {
        super.init();
    }

    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int x, int y) {
        font.func_243246_a(stack, title, 28.0F, 6.0F, 0x000000);
        font.func_243246_a(stack, playerInventory.getDisplayName(), 8.0F, (float)(ySize - 96 + 3), 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTickTime, int x, int y) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int left = guiLeft;
        int top = guiTop;
        blit(stack, left, top, 0, 0, xSize, ySize);
    }

    @Override
    public void recipesUpdated() { }

    @Override
    public RecipeBookGui getRecipeGui() {
        return null;
    }
}
