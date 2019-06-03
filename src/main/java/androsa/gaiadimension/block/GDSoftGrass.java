package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GDSoftGrass extends GDGaiaGrass {

    public GDSoftGrass() {
        super(MapColor.CYAN, () -> GDBlocks.soft_grass, () -> GDBlocks.light_soil);
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true) {
                if (j >= i / 16) {
                    if (worldIn.isAirBlock(blockpos1)) {
                        if (rand.nextInt(8) == 0) {
                            worldIn.getBiome(blockpos1).plantFlower(worldIn, rand, blockpos1);
                        } else {
                            IBlockState iblockstate = GDBlocks.crystal_growth_aura.getDefaultState();

                            if (((BlockBush)GDBlocks.crystal_growth_aura).canBlockStay(worldIn, blockpos1, iblockstate)) {
                                worldIn.setBlockState(blockpos1, iblockstate, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != GDBlocks.soft_grass || worldIn.getBlockState(blockpos1).isNormalCube()) {
                    break;
                }

                ++j;
            }
        }
    }
}
