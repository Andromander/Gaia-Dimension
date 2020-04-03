package androsa.gaiadimension.world.gen.structure.processor;

import androsa.gaiadimension.registry.ModWorldgen;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDegradeProcessor extends StructureProcessor {

    public static final BlockDegradeProcessor AMETHYST_DECAY = new BlockDegradeProcessor(MiniTowerType.AMETHYST, 0.55F);
    public static final BlockDegradeProcessor COPAL_DECAY = new BlockDegradeProcessor(MiniTowerType.COPAL, 0.55F);
    public static final BlockDegradeProcessor JADE_DECAY = new BlockDegradeProcessor(MiniTowerType.JADE, 0.55F);
    public static final BlockDegradeProcessor JET_DECAY = new BlockDegradeProcessor(MiniTowerType.JET, 0.55F);

    private final MiniTowerType towertype;
    private final float integrity;
    private static final Random random = new Random();

    public BlockDegradeProcessor(MiniTowerType type, float integrity) {
        this.towertype = type;
        this.integrity = integrity;
    }

    public BlockDegradeProcessor(Dynamic<?> dyn) {
        this(
                MiniTowerType.getType(dyn.get("towertype").asString(MiniTowerType.AMETHYST.getName())),
                dyn.get("integrity").asFloat(1.0F)
        );
    }

    @Override
    protected IStructureProcessorType getType() {
        return ModWorldgen.BLOCK_DEGRADE;
    }

    @Override
    protected <T> Dynamic<T> serialize0(DynamicOps<T> dynOps) {
        return new Dynamic<>(dynOps, dynOps.createMap(ImmutableMap.of(
                dynOps.createString("towertype"), dynOps.createString(this.towertype.getName()),
                dynOps.createString("integrity"), dynOps.createFloat(this.integrity))));
    }

    @Nullable
    @Override
    public Template.BlockInfo process(IWorldReader world, BlockPos pos, Template.BlockInfo oldInfo, Template.BlockInfo newInfo, PlacementSettings settings, @Nullable Template template) {
        BlockState state = newInfo.state;

        if (state == towertype.getBrick())
            return random.nextFloat() > integrity ? newInfo : new Template.BlockInfo(newInfo.pos, towertype.getBrickDecay(random), null);
        if (state == towertype.getSlab())
            return random.nextFloat() > integrity ? newInfo : new Template.BlockInfo(newInfo.pos, translateState(state, towertype.getSlabDecay(random).getBlock(), SlabBlock.TYPE, SlabBlock.WATERLOGGED), null);
        if (state == towertype.getStairs())
            return random.nextFloat() > integrity ? newInfo : new Template.BlockInfo(newInfo.pos, translateState(state, towertype.getStairsDecay(random).getBlock(), StairsBlock.FACING, StairsBlock.HALF, StairsBlock.SHAPE, StairsBlock.WATERLOGGED), null);

        return newInfo;
    }

    protected static BlockState translateState(BlockState stateIn, Block blockOut, IProperty<?>... properties) {
        BlockState stateOut = blockOut.getDefaultState();
        for (IProperty<?> property : properties)
            stateOut = copyValue(stateIn, stateOut, property);
        return stateOut;
    }

    private static <T extends Comparable<T>> BlockState copyValue(BlockState from, BlockState to, IProperty<T> property) {
        return to.with(property, from.get(property));
    }
}
