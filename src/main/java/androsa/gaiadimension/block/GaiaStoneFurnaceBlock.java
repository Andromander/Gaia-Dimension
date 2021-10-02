package androsa.gaiadimension.block;

import androsa.gaiadimension.block.blockentity.GaiaStoneFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GaiaStoneFurnaceBlock extends FurnaceBlock {

    public GaiaStoneFurnaceBlock(Properties props) {
        super(props);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GaiaStoneFurnaceBlockEntity(pos, state);
    }

    @Override
    protected void openContainer(Level world, BlockPos pos, Player player) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof GaiaStoneFurnaceBlockEntity) {
            player.openMenu((MenuProvider)tileentity);
        }
    }
}
