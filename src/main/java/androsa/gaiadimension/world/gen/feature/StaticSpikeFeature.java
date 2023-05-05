package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.feature.config.FeatureHeightConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StaticSpikeFeature<T extends FeatureHeightConfig> extends Feature<T> {

    private final BlockState block = ModBlocks.charged_mineral.get().defaultBlockState();

    public StaticSpikeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel worldIn, RandomSource rand, BlockPos position, T config) {
        int height = config.startHeight + rand.nextInt(4);
        boolean flag = true;

        for (int cx = 0; cx < 3; cx++) {
            for (int cz = 0; cz < 3; cz++) {
                BlockPos pos1 = position.offset(cx - 1, 0, cz - 1);

                if (worldIn.hasChunkAt(pos1)) {
                    Block blockBelow = worldIn.getBlockState(pos1.below()).getBlock();

                    if (blockBelow != ModBlocks.wasteland_stone.get() && blockBelow != ModBlocks.static_stone.get()) {
                        flag = false;
                    }
                }
            }
        }

        if (!flag) {
            return false;
        } else {
            for (int i = 0; i < height; i++) {
                worldIn.setBlock(position.above(i), this.block, 2);

                if (i < height / 2) {
                    worldIn.setBlock(position.offset(0, i, -1), this.block, 2);
                    worldIn.setBlock(position.offset(0, i, 1), this.block, 2);
                    worldIn.setBlock(position.offset(-1, i, 0), this.block, 2);
                    worldIn.setBlock(position.offset(1, i, 0), this.block, 2);

                    if (i < height / 4) {
                        worldIn.setBlock(position.offset(1, i, -1), this.block, 2);
                        worldIn.setBlock(position.offset(1, i, 1), this.block, 2);
                        worldIn.setBlock(position.offset(-1, i, -1), this.block, 2);
                        worldIn.setBlock(position.offset(-1, i, 1), this.block, 2);
                    }
                }
            }
            return true;
        }
    }
}
