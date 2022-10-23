package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DoubleCrystalGrowthBlock extends DoublePlantBlock {

    public DoubleCrystalGrowthBlock(Properties props) {
        super(props);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getBlock() instanceof AbstractGaiaGrassBlock || state.getBlock() instanceof GaiaSoilBlock;
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.NONE; //nah, don't like it
    }
}
