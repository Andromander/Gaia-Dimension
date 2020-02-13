package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class GaiaBlobFeature<T extends BlockBlobConfig> extends Feature<T> {

    public GaiaBlobFeature(Function<Dynamic<?>, T> config) {
        super(config);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, T config) {
        while(true) {
            label50: {
                if (pos.getY() > 3) {
                    if (worldIn.isAirBlock(pos.down())) {
                        break label50;
                    }

                    Block block = worldIn.getBlockState(pos.down()).getBlock();
                    if (!(block instanceof AbstractGaiaGrassBlock) && !isDirt(block) && !isStone(block)) {
                        break label50;
                    }
                }

                if (pos.getY() <= 3) {
                    return false;
                }

                int rad = config.startRadius;

                for(int i = 0; rad >= 0 && i < 3; ++i) {
                    int x = rad + rand.nextInt(2);
                    int y = rad + rand.nextInt(2);
                    int z = rad + rand.nextInt(2);
                    float f = (float)(x + y + z) * 0.333F + 0.5F;

                    for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-x, -y, -z), pos.add(x, y, z))) {
                        if (blockpos.distanceSq(pos) <= (double)(f * f)) {
                            worldIn.setBlockState(blockpos, config.state, 4);
                        }
                    }

                    pos = pos.add(-(rad + 1) + rand.nextInt(2 + rad * 2), 0 - rand.nextInt(2), -(rad + 1) + rand.nextInt(2 + rad * 2));
                }

                return true;
            }

            pos = pos.down();
        }
    }
}
