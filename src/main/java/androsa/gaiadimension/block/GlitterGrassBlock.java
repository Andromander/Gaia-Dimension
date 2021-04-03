package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GlitterGrassBlock extends AbstractGaiaGrassBlock {

    public GlitterGrassBlock(Properties props) {
        super(props, ModBlocks.heavy_soil.get());
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState normalGrowth = ModBlocks.crystal_growth.get().defaultBlockState();
        BlockState mutantGrowth = ModBlocks.crystal_growth_mutant.get().defaultBlockState();

        label48:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1)) {
                    continue label48;
                }
            }

            BlockState blockstate2 = worldIn.getBlockState(blockpos1);
            if (blockstate2.isAir()) {
                BlockState blockstate1;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    ConfiguredFeature<?, ?> configuredfeature = list.get(0);
                    FlowersFeature flowersfeature = (FlowersFeature)configuredfeature.feature;
                    blockstate1 = flowersfeature.getRandomFlower(rand, blockpos1, configuredfeature.config());
                } else {
                    blockstate1 = worldIn.getBiomeName(blockpos1) == Optional.of(ModBiomes.mutant_agate_wildwood) ? mutantGrowth : normalGrowth;
                }

                if (blockstate1.canSurvive(worldIn, blockpos1)) {
                    worldIn.setBlockAndUpdate(blockpos1, blockstate1);
                }
            }
        }
    }
}
