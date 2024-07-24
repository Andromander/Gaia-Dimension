package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SombreCactiBlock extends Block {

    public static final MapCodec<? extends SombreCactiBlock> CODEC = simpleCodec(SombreCactiBlock::new);
    public static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F);

    public SombreCactiBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return reader.getBlockState(pos.below()).is(ModBlocks.golden_sand.get());
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.hurt(level.damageSources().cactus(), 1.0F);
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, PathComputationType path) {
        return false;
    }
}
