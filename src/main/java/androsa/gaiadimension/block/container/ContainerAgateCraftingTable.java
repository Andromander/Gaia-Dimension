package androsa.gaiadimension.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerAgateCraftingTable extends ContainerWorkbench {

    public ContainerAgateCraftingTable(InventoryPlayer player, World world, BlockPos pos) {
        super(player, world, pos);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
