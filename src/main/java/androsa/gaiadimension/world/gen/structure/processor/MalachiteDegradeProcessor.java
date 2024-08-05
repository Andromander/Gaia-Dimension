package androsa.gaiadimension.world.gen.structure.processor;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModStructures;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class MalachiteDegradeProcessor extends StructureProcessor {

    public static final MapCodec<MalachiteDegradeProcessor> CODEC = Codec.FLOAT.fieldOf("integrity").orElse(1.0F).xmap(MalachiteDegradeProcessor::new, (obj) -> obj.integrity);

    private final float integrity;
    private static final Random random = new Random();
    private static final Block BRICKS = ModBlocks.malachite_bricks.get();
    private static final Block SLAB = ModBlocks.malachite_brick_slab.get();
    private static final Block STAIRS = ModBlocks.malachite_brick_stairs.get();
    private static final ImmutableList<Supplier<Block>> BRICK_DECAY = ImmutableList.of(ModBlocks.malachite_cracked_bricks, ModBlocks.malachite_crusted_bricks);
    private static final ImmutableList<Supplier<SlabBlock>> SLAB_DECAY = ImmutableList.of(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab);
    private static final ImmutableList<Supplier<StairBlock>> STAIRS_DECAY = ImmutableList.of(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_crusted_brick_stairs);

    public MalachiteDegradeProcessor(float integrity) {
        this.integrity = integrity;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructures.MALACHITE_DEGRADE.get();
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos pos, BlockPos tpos, StructureTemplate.StructureBlockInfo oldInfo, StructureTemplate.StructureBlockInfo newInfo, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        BlockState state = newInfo.state();
        Block block = state.getBlock();

        if (block == BRICKS)
            return random.nextFloat() > integrity ? newInfo : new StructureTemplate.StructureBlockInfo(newInfo.pos(), BRICK_DECAY.get(random.nextInt(BRICK_DECAY.size())).get().defaultBlockState(), null);
        if (block == SLAB)
            return random.nextFloat() > integrity ? newInfo : new StructureTemplate.StructureBlockInfo(newInfo.pos(), translateState(state, SLAB_DECAY.get(random.nextInt(SLAB_DECAY.size())).get(), SlabBlock.TYPE, SlabBlock.WATERLOGGED), null);
        if (block == STAIRS)
            return random.nextFloat() > integrity ? newInfo : new StructureTemplate.StructureBlockInfo(newInfo.pos(), translateState(state, STAIRS_DECAY.get(random.nextInt(STAIRS_DECAY.size())).get(), StairBlock.FACING, StairBlock.HALF, StairBlock.SHAPE, StairBlock.WATERLOGGED), null);

        return newInfo;
    }

    protected static BlockState translateState(BlockState stateIn, Block blockOut, Property<?>... properties) {
        BlockState stateOut = blockOut.defaultBlockState();
        for (Property<?> property : properties)
            stateOut = copyValue(stateIn, stateOut, property);
        return stateOut;
    }

    private static <T extends Comparable<T>> BlockState copyValue(BlockState from, BlockState to, Property<T> property) {
        return to.setValue(property, from.getValue(property));
    }
}
