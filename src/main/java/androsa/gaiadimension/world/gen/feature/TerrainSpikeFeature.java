package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Random;

public class TerrainSpikeFeature<T extends BlockStateConfiguration> extends Feature<T> {

    public TerrainSpikeFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config().state);
    }

    public boolean place(WorldGenLevel world, Random random, BlockPos position, BlockState state) {
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {

                if (random.nextBoolean()) {
                    BlockPos pos = position.offset(x, 0, z);
                    boolean flag = true;

                    for (int y = -2; y < 2 && flag; y++) {
                        pos = pos.above(y);
                        if (world.getBlockState(pos.below()) == state) {
                            for (int height = 1; height < random.nextInt(8) + 2; height++) {
                                world.setBlock(pos.above(height), state, 2);
                            }
                            flag = false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
