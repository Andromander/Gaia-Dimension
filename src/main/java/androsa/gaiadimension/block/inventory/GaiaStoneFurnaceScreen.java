package androsa.gaiadimension.block.inventory;

import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GaiaStoneFurnaceScreen extends AbstractFurnaceScreen<GaiaStoneFurnaceContainer> {

    private static final ResourceLocation textureLoc = new ResourceLocation("gaiadimension:textures/gui/gaia_stone_furnace.png");

    public GaiaStoneFurnaceScreen(GaiaStoneFurnaceContainer container, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(container, new FurnaceRecipeGui(), playerInventory, textComponent, textureLoc);
    }
}
