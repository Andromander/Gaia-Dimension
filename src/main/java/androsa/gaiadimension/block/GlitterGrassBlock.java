package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.bootstrap.GaiaFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class GlitterGrassBlock extends AbstractGaiaGrassBlock {

    public GlitterGrassBlock(Properties props) {
        super(props, ModBlocks.heavy_soil.get());
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        Optional<Holder.Reference<Biome>> wildwood = worldIn.registryAccess().registryOrThrow(Registries.BIOME).getHolder(GaiaBiomes.mutant_agate_wildwood);
        Optional<Holder.Reference<PlacedFeature>> optionalD = worldIn.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(GaiaFeatures.Placed.CRYSTAL_GROWTH_02);
        Optional<Holder.Reference<PlacedFeature>> optionalM = worldIn.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(GaiaFeatures.Placed.CRYSTAL_GROWTH_MUTANT);

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
                Holder<PlacedFeature> feature;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    feature = ((RandomPatchConfiguration)list.get(0).config()).feature();
                } else {
                    if (wildwood.isEmpty()) {
                        continue;
                    } else {
                        if (worldIn.getBiome(blockpos1).equals(wildwood.get())) {
                            if (optionalM.isEmpty()) {
                                continue;
                            }

                            feature = optionalM.get();
                        } else {
                            if (optionalD.isEmpty()) {
                                continue;
                            }

                            feature = optionalD.get();
                        }
                    }
                }

                feature.value().place(worldIn, worldIn.getChunkSource().getGenerator(), rand, blockpos1);
            }
        }
    }
}
