package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GaiaFallingBlock extends FallingBlock {

    private final int dust;

    public GaiaFallingBlock(Properties props, int dustColor) {
        super(props);

        dust = dustColor;
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
        return dust;
    }
}
