package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class GildedGrassBlock extends AbstractGaiaGrassBlock {

    //TODO: Enable bonemealing
    public GildedGrassBlock(Properties props) {
        super(ModBlocks.aurum_soil.get().defaultBlockState(), props);
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

    }
}
