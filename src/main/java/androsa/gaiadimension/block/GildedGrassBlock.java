package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class GildedGrassBlock extends AbstractGaiaGrassBlock {

    public GildedGrassBlock(Properties props) {
        super(props, ModBlocks.aurum_soil.get());
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter reader, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {

    }
}
