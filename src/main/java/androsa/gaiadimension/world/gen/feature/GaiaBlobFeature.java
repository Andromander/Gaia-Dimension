package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class GaiaBlobFeature<T extends BlockStateFeatureConfig> extends Feature<T> {

    public GaiaBlobFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, T config) {
        while(true) {
            label50: {
                if (pos.getY() > 3) {
                    if (worldIn.isEmptyBlock(pos.below())) {
                        break label50;
                    }

                    Block block = worldIn.getBlockState(pos.below()).getBlock();
                    if (!(block instanceof AbstractGaiaGrassBlock) && !isDirt(block) && !isStone(block)) {
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
