package androsa.gaiadimension.block.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class AgateCraftingTableContainer extends WorkbenchContainer {

    public AgateCraftingTableContainer(int id, PlayerInventory player, IWorldPosCallable world) {
        super(id, player, world);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }
}
