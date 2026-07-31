package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.bootstrap.GaiaFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class GlitterGrassBlock extends AbstractGaiaGrassBlock {

    public GlitterGrassBlock(Properties props) {
        super(ModBlocks.heavy_soil.get().defaultBlockState(), props);
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        Optional<Holder.Reference<Biome>> wildwood = worldIn.registryAccess().lookupOrThrow(Registries.BIOME).get(GaiaBiomes.mutant_agate_wildwood);
        Optional<Holder.Reference<PlacedFeature>> optionalD = worldIn.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(GaiaFeatures.Placed.CRYSTAL_GROWTH_BONEMEAL);
        Optional<Holder.Reference<PlacedFeature>> optionalM = worldIn.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(GaiaFeatures.Placed.CRYSTAL_GROWTH_MUTANT_BONEMEAL);

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
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).value().getGenerationSettings().getBoneMealFeatures();
                    if (!list.isEmpty()) {
                        ConfiguredFeature<?, ?> config = Util.getRandom(list, rand);
                        config.place(worldIn, worldIn.getChunkSource().getGenerator(), rand, blockpos1);
                    }
                } else {
                    if (wildwood.isPresent()) {
                        if (worldIn.getBiome(blockpos1).equals(wildwood.get())) {
                            if (optionalM.isPresent()) {
                                optionalM.get().value().place(worldIn, worldIn.getChunkSource().getGenerator(), rand, blockpos1);
                            }
                        } else {
                            if (optionalD.isPresent()) {
                                optionalD.get().value().place(worldIn, worldIn.getChunkSource().getGenerator(), rand, blockpos1);
                            }
                        }
                    }
                }
            }
        }
    }
}
