package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.FeatureHeightConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BismuthSpireFeature<T extends FeatureHeightConfig> extends Feature<T> {

    public BismuthSpireFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel worldIn, Random rand, BlockPos position, T config) {
        for (int cx = 0; cx < 3; cx++) {
            for (int cz = 0; cz < 3; cz++) {
                BlockPos pos = position.offset(cx - 1, 0, cz - 1);

                if (worldIn.hasChunkAt(pos)) {
                    Block blockBelow = worldIn.getBlockState(pos.below()).getBlock();

                    if (blockBelow != ModBlocks.murky_grass.get()) {
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
                        setBismuthType(worldIn, rand, position.offset(ex, i, ez));
                    }
                }
            } else if (i < heightD) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        setBismuthType(worldIn, rand, position.offset(dx, i, dz));
                    }
                }
            } else if (i < heightA) {
                for (int ax = -1; ax <= 1; ax++) {
                    for (int az = -1; az <= 1; az++) {
                        if (Math.abs(ax) != 1 || Math.abs(az) != 1) {
                            setBismuthType(worldIn, rand, position.offset(ax, i, az));
                        }
                    }
                }
            } else {
                setBismuthType(worldIn, rand, position.above(i));
            }
        }
        return true;
    }

    private void setBismuthType(WorldGenLevel world, Random random, BlockPos pos) {
        BlockState state;

        if (random.nextInt(30) == 0) {
            state = ModBlocks.bismuth_block.get().defaultBlockState();
        } else if (random.nextInt(15) == 0) {
            state = ModBlocks.active_rock.get().defaultBlockState();
        } else {
            state = ModBlocks.impure_rock.get().defaultBlockState();
        }
        world.setBlock(pos, state, 2);
    }
}

