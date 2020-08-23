package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ImpureSludgeBlock extends BasicGaiaBlock {

    private static final VoxelShape SLUDGE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public ImpureSludgeBlock() {
        super(Material.EARTH, MaterialColor.YELLOW_TERRACOTTA, SoundType.GROUND, 0.6F, 0.0F);
    }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SLUDGE_SHAPE;
    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.setMotion(entity.getMotion().mul(0.4D, 0.1D, 0.4D));
    }

    @Override
    @Deprecated
    public boolean allowsMovement(BlockState state, IBlockReader reader, BlockPos pos, PathType path) {
        return false;
    }

    @Override
    @Deprecated
    public boolean canEntitySpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == ModEntities.BISMUTH_ULETRUS.get();
    }
}
