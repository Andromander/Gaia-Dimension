package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import androsa.gaiadimension.registry.ModTileEntities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GaiaStoneFurnaceTileEntity extends AbstractFurnaceTileEntity {

    public GaiaStoneFurnaceTileEntity() {
        super(ModTileEntities.GAIA_STONE_FURNACE.get(), IRecipeType.SMELTING);
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("gaiadimension.container.gaia_stone_furnace");
    }

    protected Container createMenu(int id, PlayerInventory inventory) {
        return new GaiaStoneFurnaceContainer(id, inventory, this, this.dataAccess);
    }
}