package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.registration.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class BismuthGeyserFeature<T extends NoneFeatureConfiguration> extends Feature<T> {

    private static final Supplier<BlockState> GRASS = () -> ModBlocks.murky_grass.get().defaultBlockState();

    public BismuthGeyserFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.origin());
    }

    public boolean place(WorldGenLevel worldIn, BlockPos position) {
        for (int cx = 0; cx < 5; cx++) {
            for (int cz = 0; cz < 5; cz++) {
                BlockPos pos = position.offset(cx - 2, 0, cz - 2);

                if (worldIn.hasChunkAt(pos)) {
                    Block blockBelow = worldIn.getBlockState(pos.below()).getBlock();

                    if (blockBelow != ModBlocks.murky_grass.get()) {
                        return false;
                    }
                }
            }
        }

        for (int bx = -2; bx <= 2; bx++) {
            for (int bz = -2; bz <= 2; bz++) {
                if (Math.abs(bx) != 2 || Math.abs(bz) != 2) {
                    if (bx == 0 && bz == 0) {
                        worldIn.setBlock(position.offset(bx, 0, bz), ModBlocks.geyser_block.get().defaultBlockState(), 2);
                    } else {
                        if (bx < 2 && bz < 2 && bx > -2 && bz > -2) {
                            worldIn.setBlock(position.offset(bx, 0, bz), ModBlocks.boggy_soil.get().defaultBlockState(), 2);
                        } else {
                            worldIn.setBlock(position.offset(bx, 0, bz), GRASS.get(), 2);
                        }
                    }
                }
            }
        }
        for (int tx = -1; tx <= 1; tx++) {
            for (int tz = -1; tz <= 1; tz++) {
                if (!(tx == 0 && tz == 0)) {
                    worldIn.setBlock(position.offset(tx, 1, tz), GRASS.get(), 2);
                }
            }
        }
        return true;
    }
}
