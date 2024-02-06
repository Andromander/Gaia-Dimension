package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class GaiaSaplingBlock extends SaplingBlock {

    public GaiaSaplingBlock(TreeGrower tree, Properties props) {
        super(tree, props);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getBlock() instanceof AbstractGaiaGrassBlock;
    }
}
