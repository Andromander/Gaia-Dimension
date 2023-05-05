package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FrailGlitterBlobFeature<T extends NoneFeatureConfiguration> extends Feature<T> {

    public FrailGlitterBlobFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin());
    }

    public boolean place(WorldGenLevel worldIn, RandomSource rand, BlockPos pos) {
        if (!worldIn.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState above = worldIn.getBlockState(pos.above());
            BlockState below = worldIn.getBlockState(pos.below());
            if (!above.is(ModBlocks.gaia_stone.get()) && !below.is(ModBlocks.gaia_stone.get())) {
                return false;
            } else {
                worldIn.setBlock(pos, ModBlocks.frail_glitter_block.get().defaultBlockState(), 2);

                for(int i = 0; i < 1500; ++i) {
                    BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
                    if (worldIn.getBlockState(blockpos).isAir()) {
                        int j = 0;

                        for(Direction direction : Direction.values()) {
                            if (worldIn.getBlockState(blockpos.relative(direction)).getBlock() == ModBlocks.frail_glitter_block.get()) {
                                ++j;
                            }

                            if (j > 1) {
                                break;
                            }
                        }

                        if (j == 1) {
                            worldIn.setBlock(blockpos, ModBlocks.frail_glitter_block.get().defaultBlockState(), 2);
                        }
                    }
                }

                return true;
            }
        }
    }
}
