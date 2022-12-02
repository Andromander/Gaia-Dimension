package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Random;

public class MenhirFeature extends Feature<BlockStateConfiguration> {

    public MenhirFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        return place(context.level(), context.random(), context.origin(), context.config().state);
    }

    public boolean place(WorldGenLevel level, Random random, BlockPos origin, BlockState state) {
        boolean place = true;
        for (int x = 0; x <= 1; x++) {
            for (int z = 0; z <= 1; z++) {
                if (!(level.getBlockState(origin.offset(x, -1, z)).isFaceSturdy(EmptyBlockGetter.INSTANCE, origin.offset(x, 0, z), Direction.DOWN) && level.isEmptyBlock(origin))) {
                    place = false;
                }
            }
        }

        if (place) {
            int height = random.nextInt(16) + 8;

            for (int x = 0; x <= 1; x++) {
                for (int z = 0; z <= 1; z++) {
                    for (int y = 0; y <= height; y++) {
                        level.setBlock(origin.offset(x, y, z), state, 2);
                    }
                }
            }
        }

        return true;
    }
}
