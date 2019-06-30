package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class MurkyGrassBlock extends AbstractGaiaGrassBlock {

    public MurkyGrassBlock() {
        super(MaterialColor.GRAY, ModBlocks.murky_grass, ModBlocks.boggy_soil);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {

    }
}
