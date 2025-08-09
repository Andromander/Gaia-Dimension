package androsa.gaiadimension.block;

import androsa.gaiadimension.block.menu.AugmenterMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AugmenterBlock extends Block {

    public static final MapCodec<AugmenterBlock> CODEC = simpleCodec(AugmenterBlock::new);
    private static final VoxelShape BASE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 5.0D, 15.0D);
    private static final VoxelShape TOP_LEFT_GEM_SHAPE = Block.box(1.0D, 5.0D, 1.0D, 4.0D, 8.0D, 4.0D);
    private static final VoxelShape TOP_RIGHT_GEM_SHAPE = Block.box(1.0D, 5.0D, 12.0D, 4.0D, 8.0D, 15.0D);
    private static final VoxelShape BOTTOM_LEFT_GEM_SHAPE = Block.box(12.0D, 5.0D, 1.0D, 15.0D, 8.0D, 4.0D);
    private static final VoxelShape BOTTOM_RIGHT_GEM_SHAPE = Block.box(12.0D, 5.0D, 12.0D, 15.0D, 8.0D, 15.0D);
    private static final VoxelShape PLATE_SHAPE = Block.box(4.0D, 5.0D, 4.0D, 12.0D, 6.0D, 12.0D);
    private static final VoxelShape SHAPE = Shapes.or(BASE_SHAPE, TOP_LEFT_GEM_SHAPE, TOP_RIGHT_GEM_SHAPE, BOTTOM_LEFT_GEM_SHAPE, BOTTOM_RIGHT_GEM_SHAPE, PLATE_SHAPE);

    public AugmenterBlock(Properties props) {
        super(props);
    }

    public MapCodec<AugmenterBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((id, inv, player) -> new AugmenterMenu(id, inv, ContainerLevelAccess.create(level, pos)), Component.translatable("gaiadimension.container.augmenter"));
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}
