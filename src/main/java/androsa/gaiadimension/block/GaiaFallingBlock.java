package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class GaiaFallingBlock extends FallingBlock {

    private int dust;

    public GaiaFallingBlock(Properties props, int dustColor) {
        super(props);

        dust = dustColor;
    }

    @Override
    public int getDustColor(BlockState state, IBlockReader reader, BlockPos pos) {
        return dust;
    }
}
