package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;

import java.util.List;
import java.util.Random;

public class SoftGrassBlock extends AbstractGaiaGrassBlock {

    public SoftGrassBlock() {
        super(MaterialColor.CYAN, ModBlocks.light_soil);
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.up();
        BlockState growth = ModBlocks.crystal_growth.getDefaultState();

        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while(true) {
                if (j >= i / 16) {
                    BlockState blockstate2 = worldIn.getBlockState(blockpos1);
                    if (blockstate2.getBlock() == growth.getBlock() && rand.nextInt(10) == 0) {
                        ((IGrowable)growth.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
                    }

                    if (!blockstate2.isAir()) {
                        break;
                    }

                    BlockState blockstate1;
                    if (rand.nextInt(8) == 0) {
                        List<ConfiguredFeature<?>> list = worldIn.getBiome(blockpos1).getFlowers();
                        if (list.isEmpty()) {
                            break;
                        }

                        blockstate1 = ((FlowersFeature)((DecoratedFeatureConfig)(list.get(0)).config).feature.feature).getRandomFlower(rand, blockpos1);
                    } else {
                        blockstate1 = growth;
                    }

                    if (blockstate1.isValidPosition(worldIn, blockpos1)) {
                        worldIn.setBlockState(blockpos1, growth, 3);
                    }
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos1.down()).getBlock() != this || isOpaque(worldIn.getBlockState(blockpos1).getCollisionShape(worldIn, blockpos1))) {
                    break;
                }

                ++j;
            }
        }
    }
}
