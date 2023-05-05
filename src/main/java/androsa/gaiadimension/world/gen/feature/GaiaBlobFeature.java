package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class GaiaBlobFeature<T extends BlockStateConfiguration> extends Feature<T> {

    public GaiaBlobFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel worldIn, RandomSource rand, BlockPos pos, T config) {
        while(true) {
            label50: {
                if (pos.getY() > 3) {
                    if (worldIn.isEmptyBlock(pos.below())) {
                        break label50;
                    }

                    BlockState block = worldIn.getBlockState(pos.below());
                    if (!(block.getBlock() instanceof AbstractGaiaGrassBlock) && !isDirt(block) && !isStone(block)) {
                        break label50;
                    }
                }

                if (pos.getY() <= 3) {
                    return false;
                }

                for(int i = 0; i < 3; ++i) {
                    int x = rand.nextInt(2);
                    int y = rand.nextInt(2);
                    int z = rand.nextInt(2);
                    float f = (float)(x + y + z) * 0.333F + 0.5F;

                    for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-x, -y, -z), pos.offset(x, y, z))) {
                        if (blockpos.distSqr(pos) <= (double)(f * f)) {
                            worldIn.setBlock(blockpos, config.state, 4);
                        }
                    }

                    pos = pos.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
                }

                return true;
            }

            pos = pos.below();
        }
    }
}
