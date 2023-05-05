package androsa.gaiadimension.world.gen.structure.processor;

import androsa.gaiadimension.registry.ModStructures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public class BlockDegradeProcessor extends StructureProcessor {

    public static final BlockDegradeProcessor AMETHYST_DECAY = new BlockDegradeProcessor(MiniTowerType.AMETHYST, 0.55F);
    public static final BlockDegradeProcessor COPAL_DECAY = new BlockDegradeProcessor(MiniTowerType.COPAL, 0.55F);
    public static final BlockDegradeProcessor JADE_DECAY = new BlockDegradeProcessor(MiniTowerType.JADE, 0.55F);
    public static final BlockDegradeProcessor JET_DECAY = new BlockDegradeProcessor(MiniTowerType.JET, 0.55F);

    public static final Codec<BlockDegradeProcessor> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    MiniTowerType.CODEC.fieldOf("towertype").orElse(MiniTowerType.AMETHYST).forGetter((obj) -> obj.towertype),
                    Codec.FLOAT.fieldOf("integrity").orElse(1.0F).forGetter((obj) -> obj.integrity)
            ).apply(instance, BlockDegradeProcessor::new));

    private final MiniTowerType towertype;
    private final float integrity;
    private static final Random random = new Random();

    public BlockDegradeProcessor(MiniTowerType type, float integrity) {
        this.towertype = type;
        this.integrity = integrity;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructures.BLOCK_DEGRADE.get();
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos pos, BlockPos tpos, StructureTemplate.StructureBlockInfo oldInfo, StructureTemplate.StructureBlockInfo newInfo, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        BlockState state = newInfo.state;

        if (state == towertype.getBrick())
            return random.nextFloat() > integrity ? newInfo : new StructureTemplate.StructureBlockInfo(newInfo.pos, towertype.getBrickDecay(random), null);
        if (state == towertype.getSlab())
            return random.nextFloat() > integrity ? newInfo : new StructureTemplate.StructureBlockInfo(newInfo.pos, translateState(state, towertype.getSlabDecay(random).getBlock(), SlabBlock.TYPE, SlabBlock.WATERLOGGED), null);
        if (state == towertype.getStairs())
            return random.nextFloat() > integrity ? newInfo : new StructureTemplate.StructureBlockInfo(newInfo.pos, translateState(state, towertype.getStairsDecay(random).getBlock(), StairBlock.FACING, StairBlock.HALF, StairBlock.SHAPE, StairBlock.WATERLOGGED), null);

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
