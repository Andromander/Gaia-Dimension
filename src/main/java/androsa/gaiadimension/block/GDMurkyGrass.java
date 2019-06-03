package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GDMurkyGrass extends GDGaiaGrass {

    public GDMurkyGrass() {
        super(MapColor.GRAY, () -> GDBlocks.murky_grass, () -> GDBlocks.boggy_soil);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {

    }
}
