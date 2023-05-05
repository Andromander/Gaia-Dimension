package androsa.gaiadimension.world.gen.feature.decorator;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class GoldenVineDecorator extends TreeDecorator {

    public static final Codec<GoldenVineDecorator> CODEC = Codec.unit(() -> GoldenVineDecorator.INSTANCE);
    public static final GoldenVineDecorator INSTANCE = new GoldenVineDecorator();

    @Override
    protected TreeDecoratorType<?> type() {
        return ModWorldgen.GOLDEN_VINE_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        this.place(context, context.random(), context.leaves());
    }

    public void place(Context context, RandomSource random, List<BlockPos> leaves) {
        leaves.forEach((pos) -> {
            if (random.nextInt(4) == 0) {
                BlockPos west = pos.west();
                if (context.isAir(west)) {
                    addHangingVine(west, VineBlock.EAST, context);
                }
            }

            if (random.nextInt(4) == 0) {
                BlockPos east = pos.east();
                if (context.isAir(east)) {
                    addHangingVine(east, VineBlock.WEST, context);
                }
            }

            if (random.nextInt(4) == 0) {
                BlockPos north = pos.north();
                if (context.isAir(north)) {
                    addHangingVine(north, VineBlock.SOUTH, context);
                }

            }
            if (random.nextInt(4) == 0) {
                BlockPos south = pos.south();
                if (context.isAir(south)) {
                    addHangingVine(south, VineBlock.NORTH, context);
                }
            }
        });
    }

    private static void addHangingVine(BlockPos pos, BooleanProperty property, Context context) {
        addVine(context, pos, property);
        int y = 4;

        for (BlockPos hangpos = pos.below(); context.isAir(hangpos) && y > 0; y--) {
            addVine(context, hangpos, property);
            hangpos = hangpos.below();
        }
    }

    private static void addVine(Context context, BlockPos pos, BooleanProperty property) {
        context.setBlock(pos, ModBlocks.golden_vine.get().defaultBlockState().setValue(property, true));
    }
}
