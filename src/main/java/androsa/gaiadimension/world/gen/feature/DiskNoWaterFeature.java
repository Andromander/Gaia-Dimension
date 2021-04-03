package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;

import java.util.Random;

public class DiskNoWaterFeature<T extends SphereReplaceConfig> extends Feature<T> {

    public DiskNoWaterFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, T config) {
        int i = 0;
        int j = (config.radius.sample(rand) - 2) + 2;

        for(int k = pos.getX() - j; k <= pos.getX() + j; ++k) {
            for(int l = pos.getZ() - j; l <= pos.getZ() + j; ++l) {
                int i1 = k - pos.getX();
                int j1 = l - pos.getZ();
                if (i1 * i1 + j1 * j1 <= j * j) {
                    for(int k1 = pos.getY() - config.halfHeight; k1 <= pos.getY() + config.halfHeight; ++k1) {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        BlockState blockstate = worldIn.getBlockState(blockpos);

                        for(BlockState blockstate1 : config.targets) {
                            if (blockstate1.getBlock() == blockstate.getBlock()) {
                                worldIn.setBlock(blockpos, config.state, 2);
                                ++i;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return i > 0;
    }
}

