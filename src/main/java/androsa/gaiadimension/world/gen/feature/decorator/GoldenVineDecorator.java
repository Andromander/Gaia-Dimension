package androsa.gaiadimension.world.gen.feature.decorator;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class GoldenVineDecorator extends TreeDecorator {

    public static final Codec<GoldenVineDecorator> CODEC = Codec.unit(() -> GoldenVineDecorator.INSTANCE);
    public static final GoldenVineDecorator INSTANCE = new GoldenVineDecorator();

    @Override
    protected TreeDecoratorType<?> type() {
        return ModWorldgen.GOLDEN_VINE_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> statepos, Random random, List<BlockPos> trunk, List<BlockPos> leaves) {
        leaves.forEach((pos) -> {
            if (random.nextInt(4) == 0) {
                BlockPos west = pos.west();
                if (Feature.isAir(level, west)) {
                    addHangingVine(level, west, VineBlock.EAST, statepos);
                }
            }

            if (random.nextInt(4) == 0) {
                BlockPos east = pos.east();
                if (Feature.isAir(level, east)) {
                    addHangingVine(level, east, VineBlock.WEST, statepos);
                }
            }

            if (random.nextInt(4) == 0) {
                BlockPos north = pos.north();
                if (Feature.isAir(level, north)) {
                    addHangingVine(level, north, VineBlock.SOUTH, statepos);
                }

            }
            if (random.nextInt(4) == 0) {
                BlockPos south = pos.south();
                if (Feature.isAir(level, south)) {
                    addHangingVine(level, south, VineBlock.NORTH, statepos);
                }
            }
        });
    }

    private static void addHangingVine(LevelSimulatedReader reader, BlockPos pos, BooleanProperty property, BiConsumer<BlockPos, BlockState> statepos) {
        addVine(statepos, pos, property);
        int y = 4;

        for (BlockPos hangpos = pos.below(); Feature.isAir(reader, hangpos) && y > 0; y--) {
            addVine(statepos, hangpos, property);
            hangpos = hangpos.below();
        }
    }

    private static void addVine(BiConsumer<BlockPos, BlockState> statepos, BlockPos pos, BooleanProperty property) {
        statepos.accept(pos, ModBlocks.golden_vine.get().defaultBlockState().setValue(property, true));
    }
}
