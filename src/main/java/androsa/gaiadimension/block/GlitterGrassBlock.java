package androsa.gaiadimension.block;

import androsa.gaiadimension.biomes.MutantAgateWildwoodBiome;
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

public class GlitterGrassBlock extends AbstractGaiaGrassBlock {

    public GlitterGrassBlock() {
        super(MaterialColor.PINK, ModBlocks.heavy_soil);
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.up();
        BlockState normalGrowth = ModBlocks.crystal_growth.getDefaultState();
        BlockState mutantGrowth = ModBlocks.crystal_growth_mutant.getDefaultState();

        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while(true) {
                if (j >= i / 16) {
                    BlockState blockstate2 = worldIn.getBlockState(blockpos1);
                    if (blockstate2.getBlock() == normalGrowth.getBlock() && rand.nextInt(10) == 0) {
                        ((IGrowable)normalGrowth.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
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
                        blockstate1 = normalGrowth;
                    }

                    if (blockstate1.isValidPosition(worldIn, blockpos1)) {
                        if (worldIn.getBiome(pos) instanceof MutantAgateWildwoodBiome) {
                            worldIn.setBlockState(blockpos1, mutantGrowth, 3);
                        } else {
                            worldIn.setBlockState(blockpos1, normalGrowth, 3);
                        }
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
