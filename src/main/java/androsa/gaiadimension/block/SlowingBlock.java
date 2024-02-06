package androsa.gaiadimension.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SlowingBlock extends Block {

    public static final MapCodec<? extends SlowingBlock> CODEC = simpleCodec(SlowingBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public SlowingBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    @Deprecated
    public VoxelShape getBlockSupportShape(BlockState p_60581_, BlockGetter getter, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    @Deprecated
    public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType path) {
        return false;
    }
}
