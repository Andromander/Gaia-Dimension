package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.configurations.GaiaFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GlitterGrassBlock extends AbstractGaiaGrassBlock {

    public GlitterGrassBlock(Properties props) {
        super(props, ModBlocks.heavy_soil.get());
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();

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
                PlacedFeature feature;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    feature = ((RandomPatchConfiguration)list.get(0).config()).feature().value();
                } else {
                    feature = worldIn.getBiome(blockpos1).unwrapKey().equals(Optional.of(ModBiomes.mutant_agate_wildwood)) ? GaiaFeatures.Placed.CRYSTAL_GROWTH_MUTANT.value() : GaiaFeatures.Placed.CRYSTAL_GROWTH_02.value();
                }

                feature.place(worldIn, worldIn.getChunkSource().getGenerator(), rand, blockpos1);
            }
        }
    }
}
