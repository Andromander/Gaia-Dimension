package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class MurkyGrassBlock extends AbstractGaiaGrassBlock {

    public MurkyGrassBlock(Properties props) {
        super(ModBlocks.boggy_soil.get().defaultBlockState(), props);
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
        return false;
    }
}
