package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class FrailGlitterBlobFeature extends Feature<NoFeatureConfig> {

    public FrailGlitterBlobFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!worldIn.isAirBlock(pos)) {
            return false;
        } else if (worldIn.getBlockState(pos.up()).getBlock() != ModBlocks.gaia_stone && worldIn.getBlockState(pos.down()).getBlock() != ModBlocks.gaia_stone) {
            return false;
        } else {
            if (pos.getY() > worldIn.getSeaLevel() || pos.getY() < 15)
                return false;

            worldIn.setBlockState(pos, ModBlocks.frail_glitter_block.getDefaultState(), 2);

            for(int i = 0; i < 1500; ++i) {
                BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
                if (worldIn.getBlockState(blockpos).isAir(worldIn, blockpos)) {
                    int j = 0;

                    for(Direction direction : Direction.values()) {
                        if (worldIn.getBlockState(blockpos.offset(direction)).getBlock() == ModBlocks.frail_glitter_block) {
                            ++j;
                        }

                        if (j > 1) {
                            break;
                        }
                    }

                    if (j == 1) {
                        worldIn.setBlockState(blockpos, ModBlocks.frail_glitter_block.getDefaultState(), 2);
                    }
                }
            }

            return true;
        }
    }
}
