package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class StaticSpikeFeature<T extends FeatureHeightConfig> extends Feature<T> {

    private final BlockState block = ModBlocks.charged_mineral.getDefaultState();

    public StaticSpikeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, T config) {
        int height = config.startHeight + rand.nextInt(4);
        boolean flag = true;

        for (int cx = 0; cx < 3; cx++) {
            for (int cz = 0; cz < 3; cz++) {
                BlockPos pos1 = position.add(cx - 1, 0, cz - 1);

                if (worldIn.isBlockLoaded(pos1)) {
                    Block blockBelow = worldIn.getBlockState(pos1.down()).getBlock();

                    if (blockBelow != ModBlocks.wasteland_stone && blockBelow != ModBlocks.static_stone) {
                        flag = false;
                    }
                }
            }
        }

        if (!flag) {
            return false;
        } else {
            for (int i = 0; i < height; i++) {
                worldIn.setBlockState(position.up(i), this.block, 2);

                if (i < height / 2) {
                    worldIn.setBlockState(position.add(0, i, -1), this.block, 2);
                    worldIn.setBlockState(position.add(0, i, 1), this.block, 2);
                    worldIn.setBlockState(position.add(-1, i, 0), this.block, 2);
                    worldIn.setBlockState(position.add(1, i, 0), this.block, 2);

                    if (i < height / 4) {
                        worldIn.setBlockState(position.add(1, i, -1), this.block, 2);
                        worldIn.setBlockState(position.add(1, i, 1), this.block, 2);
                        worldIn.setBlockState(position.add(-1, i, -1), this.block, 2);
                        worldIn.setBlockState(position.add(-1, i, 1), this.block, 2);
                    }
                }
            }
            return true;
        }
    }
}
