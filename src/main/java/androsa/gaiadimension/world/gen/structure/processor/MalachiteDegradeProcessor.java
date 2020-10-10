package androsa.gaiadimension.world.gen.structure.processor;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nullable;
import java.util.Random;

public class MalachiteDegradeProcessor extends StructureProcessor {

    public static final Codec<MalachiteDegradeProcessor> CODEC = Codec.FLOAT.fieldOf("integrity").orElse(1.0F).xmap(MalachiteDegradeProcessor::new, (obj) -> obj.integrity).codec();

    private final float integrity;
    private static final Random random = new Random();
    private static final Block BRICKS = ModBlocks.malachite_bricks;
    private static final Block SLAB = ModBlocks.malachite_brick_slab;
    private static final Block STAIRS = ModBlocks.malachite_brick_stairs;
    private static final ImmutableList<Block> BRICK_DECAY = ImmutableList.of(ModBlocks.malachite_cracked_bricks, ModBlocks.malachite_crusted_bricks);
    private static final ImmutableList<SlabBlock> SLAB_DECAY = ImmutableList.of(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab);
    private static final ImmutableList<StairsBlock> STAIRS_DECAY = ImmutableList.of(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_crusted_brick_stairs);

    public MalachiteDegradeProcessor(float integrity) {
        this.integrity = integrity;
    }

    @Override
    protected IStructureProcessorType getType() {
        return ModWorldgen.MALACHITE_DEGRADE;
    }

    @Nullable
    @Override
    public Template.BlockInfo process(IWorldReader world, BlockPos pos, BlockPos tpos, Template.BlockInfo oldInfo, Template.BlockInfo newInfo, PlacementSettings settings, @Nullable Template template) {
        BlockState state = newInfo.state;
        Block block = state.getBlock();

        if (block == BRICKS)
            return random.nextFloat() > integrity ? newInfo : new Template.BlockInfo(newInfo.pos, BRICK_DECAY.get(random.nextInt(BRICK_DECAY.size())).getDefaultState(), null);
        if (block == SLAB)
            return random.nextFloat() > integrity ? newInfo : new Template.BlockInfo(newInfo.pos, translateState(state, SLAB_DECAY.get(random.nextInt(SLAB_DECAY.size())), SlabBlock.TYPE, SlabBlock.WATERLOGGED), null);
        if (block == STAIRS)
            return random.nextFloat() > integrity ? newInfo : new Template.BlockInfo(newInfo.pos, translateState(state, STAIRS_DECAY.get(random.nextInt(STAIRS_DECAY.size())), StairsBlock.FACING, StairsBlock.HALF, StairsBlock.SHAPE, StairsBlock.WATERLOGGED), null);

        return newInfo;
    }

    protected static BlockState translateState(BlockState stateIn, Block blockOut, Property<?>... properties) {
        BlockState stateOut = blockOut.getDefaultState();
        for (Property<?> property : properties)
            stateOut = copyValue(stateIn, stateOut, property);
        return stateOut;
    }

    private static <T extends Comparable<T>> BlockState copyValue(BlockState from, BlockState to, Property<T> property) {
        return to.with(property, from.get(property));
    }
}
