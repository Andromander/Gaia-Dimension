package androsa.gaiadimension.block.inventory;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiAgateCraftingTable extends GuiCrafting {
    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/crafting_table.png");

    public GuiAgateCraftingTable(InventoryPlayer player, World world, BlockPos pos) {
        super(player, world, pos);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTickTime, int x, int y) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(textureLoc);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.crafting").getFormattedText()), 29, 7, getColour(0, 0, 0));
        fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), 8, ySize - 96 + 3, getColour(0, 0, 0));
    }

    public static final int getColour(int R, int G, int B) {
        return new Color(R, G, B).getRGB() & 0x00ffffff;
    }
}
