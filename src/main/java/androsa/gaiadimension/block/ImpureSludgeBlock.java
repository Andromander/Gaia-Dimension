package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ImpureSludgeBlock extends Block {

    private static final VoxelShape SLUDGE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public ImpureSludgeBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SLUDGE_SHAPE;
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.4D, 0.1D, 0.4D));
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType path) {
        return false;
    }
}
