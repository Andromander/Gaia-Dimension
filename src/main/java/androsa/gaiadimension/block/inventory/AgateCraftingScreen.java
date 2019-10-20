package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.container.AgateCraftingTableContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//FIXME: GUI is not using custom texture. Investigate.
@OnlyIn(Dist.CLIENT)
public class AgateCraftingScreen extends ContainerScreen<AgateCraftingTableContainer> implements IRecipeShownListener {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/crafting_table.png");

    public AgateCraftingScreen(AgateCraftingTableContainer container, PlayerInventory player, ITextComponent textComponent) {
        super(container, player, textComponent);
    }

    protected void init() {
        super.init();
    }

    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        this.renderBackground();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        this.renderHoveredToolTip(p_render_1_, p_render_2_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        font.drawString(I18n.format(title.getFormattedText()), 28.0F, 6.0F, 0x000000);
        font.drawString(I18n.format(playerInventory.getDisplayName().getFormattedText()), 8.0F, (float)(ySize - 96 + 3), 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTickTime, int x, int y) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(textureLoc);
        int left = guiLeft;
        int top = (guiTop - ySize) / 2;
        blit(left, top, 0, 0, xSize, ySize);
    }

    @Override
    public void recipesUpdated() { }

    @Override
    public RecipeBookGui getRecipeGui() {
        return null;
    }
}
