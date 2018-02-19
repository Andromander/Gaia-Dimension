package androsa.gaiadimension.world;

import androsa.gaiadimension.block.GDCrystalBloom;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenCrystalBloom extends WorldGenerator {
    private final IBlockState tallGrassState;

    public GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant var1) {
        this.tallGrassState = GDBlocks.crystalBloom.getDefaultState().withProperty(GDCrystalBloom.VARIANT, var1);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && GDBlocks.crystalBloom.canPlaceBlockAt(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, this.tallGrassState, 2);
            }
        }
        return true;
    }
}
