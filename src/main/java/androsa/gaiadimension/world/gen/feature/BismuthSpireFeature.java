package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BismuthSpireFeature<T extends FeatureHeightConfig> extends Feature<T> {

    public BismuthSpireFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, T config) {
        for (int cx = 0; cx < 3; cx++) {
            for (int cz = 0; cz < 3; cz++) {
                BlockPos pos = position.add(cx - 1, 0, cz - 1);

                if (worldIn.isBlockLoaded(pos)) {
                    Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();

                    if (blockBelow != ModBlocks.murky_grass) {
                        return false;
                    }
                }
            }
        }

        int height = config.startHeight + rand.nextInt(4);
        int heightA = height - rand.nextInt(4) - 2;
        int heightD = height / 2  - (rand.nextInt(5) - 2);
        int heightE = rand.nextInt(3);

        for (int i = 0; i < height; i++) {
            if (i < heightE) {
                for (int ex = -2; ex <= 2; ex++) {
                    for (int ez = -2; ez <= 2; ez++) {
                        if (Math.abs(ex) != 2 || Math.abs(ez) != 2)
                        setBismuthType(worldIn, rand, position.add(ex, i, ez));
                    }
                }
            } else if (i < heightD) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        setBismuthType(worldIn, rand, position.add(dx, i, dz));
                    }
                }
            } else if (i < heightA) {
                for (int ax = -1; ax <= 1; ax++) {
                    for (int az = -1; az <= 1; az++) {
                        if (Math.abs(ax) != 1 || Math.abs(az) != 1) {
                            setBismuthType(worldIn, rand, position.add(ax, i, az));
                        }
                    }
                }
            } else {
                setBismuthType(worldIn, rand, position.up(i));
            }
        }
        return true;
    }

    private void setBismuthType(IWorldGenerationReader world, Random random, BlockPos pos) {
        BlockState state;

        if (random.nextInt(30) == 0) {
            state = ModBlocks.bismuth_block.getDefaultState();
        } else if (random.nextInt(15) == 0) {
            state = ModBlocks.active_rock.getDefaultState();
        } else {
            state = ModBlocks.impure_rock.getDefaultState();
        }
        world.setBlockState(pos, state, 2);
    }
}

