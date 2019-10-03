package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class StaticSpikeFeature extends Feature<NoFeatureConfig> {

    private final BlockState block = ModBlocks.charged_mineral.get().getDefaultState();
    private final int startHeight;

    public StaticSpikeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, int startHeightIn) {
        super(configIn);
        this.startHeight = startHeightIn;
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos position, NoFeatureConfig config) {
        int height = startHeight + rand.nextInt(4);
        boolean flag = true;

        for (int cx = 0; cx < 3; cx++) {
            for (int cz = 0; cz < 3; cz++) {
                BlockPos pos1 = position.add(cx - 1, 0, cz - 1);

                if (worldIn.isBlockLoaded(pos1)) {
                    Block blockBelow = worldIn.getBlockState(pos1.down()).getBlock();

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
