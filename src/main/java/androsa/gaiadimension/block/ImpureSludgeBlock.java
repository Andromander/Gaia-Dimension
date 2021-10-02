package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ImpureSludgeBlock extends Block {

    private static final VoxelShape SLUDGE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public ImpureSludgeBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SLUDGE_SHAPE;
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.4D, 0.1D, 0.4D));
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType path) {
        return false;
    }
}
