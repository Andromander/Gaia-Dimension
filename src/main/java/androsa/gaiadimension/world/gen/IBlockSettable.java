package androsa.gaiadimension.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockSettable {
    void setBlockAndNotify(World world, BlockPos pos, IBlockState state);
}
