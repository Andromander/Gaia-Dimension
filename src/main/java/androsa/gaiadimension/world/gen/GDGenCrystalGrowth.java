package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class GDGenCrystalGrowth extends WorldGenerator {
    private final IBlockState tallGrassState;

    public GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant var1) {
        this.tallGrassState = GDBlocks.crystalGrowth.getDefaultState().withProperty(GDCrystalGrowth.VARIANT, var1);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && GDBlocks.crystalGrowth.canPlaceBlockAt(worldIn, blockpos)) {
                    worldIn.setBlockState(blockpos, this.tallGrassState, 2);
            }
        }
        return true;
    }
}
