package androsa.gaiadimension.block.container;

import androsa.gaiadimension.registry.ModContainers;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;

public class GaiaStoneFurnaceContainer extends AbstractFurnaceContainer {

    public GaiaStoneFurnaceContainer(int id, PlayerInventory playerinv) {
        super(ModContainers.GAIA_STONE_FURNACE.get(), IRecipeType.SMELTING, id, playerinv);
    }

    public GaiaStoneFurnaceContainer(int id, PlayerInventory playerinv, IInventory inventory, IIntArray array) {
        super(ModContainers.GAIA_STONE_FURNACE.get(), IRecipeType.SMELTING, id, playerinv, inventory, array);
    }
}
