package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.feature.config.TwoBlockStateConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BalancingRockFeature<T extends TwoBlockStateConfig> extends Feature<T> {

    public BalancingRockFeature(Codec<T> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        BlockState first = context.config().first;
        BlockState second = context.config().second;
        boolean rotate = context.random().nextBoolean();

        for (int x = -4; x <= 4; x++) {
            for (int y = 0; y <= 11; y++) {
                for (int z = -4; z <= 4; z++) {

                    if (Math.abs(x) <= 1 && Math.abs(z) <= 1 && y == 0) {
                        if (level.getBlockState(origin.offset(x, y, z).below()) != ModBlocks.gilded_grass.get().defaultBlockState()) {
                            return false;
                        }
                    }

                    if (!(level.getBlockState(origin.offset(x, y, z)).isAir())) {
                        return false;
                    }
                }
            }
        }

        return switch (context.random().nextInt(7)) {
            case 0 -> rockQuad(level, origin, 3, 6, first, second, rotate); //square
            case 1 -> rockQuad(level, origin, 3, 10, first, second, rotate); //rectangle
            case 2 -> rockQuad(level, origin, 5, 6, first, second, rotate); //oblong
            case 3 -> rockArray(level, origin, 8, 8, first, second, rotate, Arrays.DIAMOND);
            case 4 -> rockArray(level, origin, 8, 8, first, second, rotate, Arrays.CIRCLE);
            case 5 -> rockArray(level, origin, 8, 6, first, second, rotate, Arrays.UP_ARROW);
            case 6 -> rockArray(level, origin, 8, 6, first, second, rotate, Arrays.DOWN_ARROW);
            default -> false;
        };
    }

    private void smallRock(WorldGenLevel level, BlockPos pos, BlockState first) {
        for (int width = -1; width <= 1; width ++) {
            for (int length = -1; length <= 1; length++) {
                if (length == 0 && width == 0) {
                    level.setBlock(setPos(pos, length, 2, width, false), first, 2);
                }

                if (Math.abs(length) != 1 || Math.abs(width) != 1) {
                    level.setBlock(setPos(pos, length, 1, width, false), first, 2);
                }

                level.setBlock(setPos(pos, length, 0, width, false), first, 2);
            }
        }
    }

    private boolean rockQuad(WorldGenLevel level, BlockPos pos, int radius, int rockheight, BlockState first, BlockState second, boolean rotate) {
        int totalheight = rockheight + 3;

        smallRock(level, pos, first);

        for (int width = -1; width <= 1; width ++) {
            for (int length = -radius; length <= radius; length++) {
                for (int height = 3; height <= totalheight; height++) {
                    if (Math.abs(length) > radius - 2 || (height < 5 || height > totalheight - 2)) {
                        level.setBlock(setPos(pos, length, height, width, rotate), second, 2);
                    }
                }
            }
        }
        return true;
    }

    private boolean rockArray(WorldGenLevel level, BlockPos pos, int x, int y, BlockState first, BlockState second, boolean rotate, byte[] array) {
        smallRock(level, pos, first);
        pos = rotate ? pos.offset(0, 3, -(x / 2)) : pos.offset(-(x / 2), 3, 0);

        for (int width = -1; width <= 1; width++) {
            for (int length = 0; length <= x; length++) {
                for (int height = 0; height <= y; height++) {
                    if (array[(y - height) * (x + 1) + length] == 1) {
                        level.setBlock(setPos(pos, length, height, width, rotate), second, 2);
                    }
                }
            }
        }

        return true;
    }

    private BlockPos setPos(BlockPos pos, int length, int height, int width, boolean rotate) {
        return rotate ? pos.offset(width, height, length) : pos.offset(length, height, width);
    }

    private static class Arrays {

        public static final byte[] DIAMOND = new byte[] {
                0, 0, 0, 0, 1, 0, 0, 0, 0,
                0, 0, 0, 1, 1, 1, 0, 0, 0,
                0, 0, 1, 1, 0, 1, 1, 0, 0,
                0, 1, 1, 0, 0, 0, 1, 1, 0,
                1, 1, 0, 0, 0, 0, 0, 1, 1,
                0, 1, 1, 0, 0, 0, 1, 1, 0,
                0, 0, 1, 1, 0, 1, 1, 0, 0,
                0, 0, 0, 1, 1, 1, 0, 0, 0,
                0, 0, 0, 0, 1, 0, 0, 0, 0
        };

        public static final byte[] CIRCLE = new byte[] {
                0, 0, 1, 1, 1, 1, 1, 0, 0,
                0, 1, 1, 1, 1, 1, 1, 1, 0,
                1, 1, 1, 0, 0, 0, 1, 1, 1,
                1, 1, 0, 0, 0, 0, 0, 1, 1,
                1, 1, 0, 0, 0, 0, 0, 1, 1,
                1, 1, 0, 0, 0, 0, 0, 1, 1,
                1, 1, 1, 0, 0, 0, 1, 1, 1,
                0, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 0, 1, 1, 1, 1, 1, 0, 0
        };

        public static final byte[] UP_ARROW = new byte[] {
                0, 0, 0, 0, 1, 0, 0, 0, 0,
                0, 0, 0, 1, 1, 1, 0, 0, 0,
                0, 0, 1, 1, 0, 1, 1, 0, 0,
                0, 1, 1, 0, 0, 0, 1, 1, 0,
                1, 1, 0, 0, 0, 0, 0, 1, 1,
                0, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 0, 1, 1, 1, 1, 1, 0, 0
        };

        public static final byte[] DOWN_ARROW = new byte[] {
                0, 0, 0, 0, 1, 0, 0, 0, 0,
                0, 0, 0, 1, 1, 1, 0, 0, 0,
                0, 0, 1, 1, 0, 1, 1, 0, 0,
                0, 1, 1, 0, 0, 0, 1, 1, 0,
                1, 1, 0, 0, 0, 0, 0, 1, 1,
                0, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 0, 1, 1, 1, 1, 1, 0, 0
        };
    }
}
