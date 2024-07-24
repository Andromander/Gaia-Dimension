package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class CurtainBlock extends Block {

    public static final EnumProperty<Direction.Axis> FACING = BlockStateProperties.HORIZONTAL_AXIS;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final EnumProperty<CurtainSide> SIDE = EnumProperty.create("side", CurtainSide.class);

    protected static final VoxelShape X_AABB = Block.box(0.0, 0.0, 7.5, 16.0, 16.0, 8.5);
    protected static final VoxelShape Z_AABB = Block.box(7.5, 0.0, 0.0, 8.5, 16.0, 16.0);

    public CurtainBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.Axis.X)
                .setValue(OPEN, false)
                .setValue(POWERED, false)
                .setValue(HALF, DoubleBlockHalf.UPPER)
                .setValue(SIDE, CurtainSide.SINGLE));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return state.getValue(FACING) == Direction.Axis.X ? X_AABB : Z_AABB;
    }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? Shapes.empty() : super.getCollisionShape(state, getter, pos, context);
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbor, LevelAccessor accessor, BlockPos pos, BlockPos nPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.UPPER != (direction == Direction.DOWN)) {
            //Half is upper, direction is up, and cannot survive
            return state.getValue(HALF) == DoubleBlockHalf.UPPER && direction == Direction.UP && !state.canSurvive(accessor, pos)
                    ? Blocks.AIR.defaultBlockState()
                    : defineHalf(state, neighbor, direction);
        } else {
            return neighbor.getBlock() instanceof CurtainBlock && neighbor.getValue(HALF) != half
                    ? neighbor.setValue(HALF, half)
                    : Blocks.AIR.defaultBlockState();
        }
    }

    private BlockState defineHalf(BlockState state, BlockState neighbor, Direction direction) {
        CurtainSide side = state.getValue(SIDE);
        boolean open = state.getValue(OPEN);
        boolean powered = false;

        if (side != CurtainSide.SINGLE) {
            //Only check if there's a non-curtain, we don't want to connect if we're not Single
            if (!(neighbor.getBlock() instanceof CurtainBlock)) {
                if (side == CurtainSide.LEFT) {
                    if ((direction == Direction.EAST && state.getValue(FACING) == Direction.Axis.X) || (direction == Direction.SOUTH && state.getValue(FACING) == Direction.Axis.Z)) {
                        side = CurtainSide.SINGLE;
                        open = false;
                    } else {
                        return state;
                    }
                } else if (side == CurtainSide.RIGHT) {
                    if ((direction == Direction.WEST && state.getValue(FACING) == Direction.Axis.X) || (direction == Direction.NORTH && state.getValue(FACING) == Direction.Axis.Z)) {
                        side = CurtainSide.SINGLE;
                        open = false;
                    } else {
                        return state;
                    }
                } else {
                    return state;
                }
            } else {
                return state;
            }
        }
        if (neighbor.getBlock() instanceof CurtainBlock) {
            if (state.getValue(FACING) == neighbor.getValue(FACING)) {
                if (neighbor.getValue(SIDE) == CurtainSide.RIGHT) {
                    if (direction == Direction.EAST || direction == Direction.SOUTH) {
                        side = CurtainSide.LEFT;
                        powered = neighbor.getValue(POWERED);
                    }
                }
                if (neighbor.getValue(SIDE) == CurtainSide.LEFT) {
                    if (direction == Direction.WEST || direction == Direction.NORTH) {
                        side = CurtainSide.RIGHT;
                        powered = neighbor.getValue(POWERED);
                    }
                }
            }
        }

        //Open state is set to closed to make sure the states don't get all wacky
        return state.setValue(SIDE, side).setValue(POWERED, powered).setValue(OPEN, open);
    }

    @Override
    @Deprecated
    public void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackpos) {
        if (explosion.getBlockInteraction() == Explosion.BlockInteraction.TRIGGER_BLOCK
                && state.getValue(HALF) == DoubleBlockHalf.UPPER
                && !level.isClientSide()
                && !state.getValue(POWERED)) {
            this.setOpen(null, level, state, pos, !state.getValue(OPEN));
        }

        super.onExplosionHit(state, level, pos, explosion, stackpos);
    }

    public void setOpen(@Nullable Entity entity, Level level, BlockState state, BlockPos pos, boolean closed) {
        if (state.is(this) && state.getValue(OPEN) != closed) {
            level.setBlock(pos, state.setValue(OPEN, closed), 10);
            this.playSound(entity, level, pos);
            level.gameEvent(entity, closed ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
    }

    private void playSound(@Nullable Entity entity, Level level, BlockPos pos) {
        level.playSound(entity, pos, SoundEvents.AMETHYST_BLOCK_STEP, SoundSource.BLOCKS, 0.8F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.playSound(entity, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && (player.isCreative() || !player.hasCorrectToolForDrops(state))) {
            DoubleBlockHalf half = state.getValue(HALF);
            if (half == DoubleBlockHalf.LOWER) {
                BlockPos blockpos = pos.above();
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER) {
                    BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                    level.setBlock(blockpos, blockstate1, 35);
                    level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, PathComputationType computation) {
        return switch(computation) {
            case LAND, AIR -> state.getValue(OPEN);
            case WATER -> false;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        if (level.getBlockState(blockpos.below()).canBeReplaced(context)) {
            boolean flag = level.hasNeighborSignal(blockpos) || level.hasNeighborSignal(blockpos.below());
            Direction.Axis axis = context.getHorizontalDirection().getAxis() == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            CurtainSide half = switch (axis) {
                case X -> {
                    BlockState west = level.getBlockState(blockpos.west());
                    BlockState east = level.getBlockState(blockpos.east());
                    if (west.is(this) && west.getValue(FACING) == Direction.Axis.X && west.getValue(SIDE) == CurtainSide.SINGLE) yield CurtainSide.RIGHT;
                    if (east.is(this) && east.getValue(FACING) == Direction.Axis.X && east.getValue(SIDE) == CurtainSide.SINGLE) yield CurtainSide.LEFT;
                    yield CurtainSide.SINGLE;
                }
                case Z -> {
                    BlockState north = level.getBlockState(blockpos.north());
                    BlockState south = level.getBlockState(blockpos.south());
                    if (north.is(this) && north.getValue(FACING) == Direction.Axis.Z && north.getValue(SIDE) == CurtainSide.SINGLE) yield CurtainSide.RIGHT;
                    if (south.is(this) && south.getValue(FACING) == Direction.Axis.Z && south.getValue(SIDE) == CurtainSide.SINGLE) yield CurtainSide.LEFT;
                    yield CurtainSide.SINGLE;
                }
                default -> CurtainSide.SINGLE;
            };

            return this.defaultBlockState()
                    .setValue(FACING, axis)
                    .setValue(POWERED, flag)
                    .setValue(OPEN, flag)
                    .setValue(HALF, DoubleBlockHalf.UPPER)
                    .setValue(SIDE, half);
        } else {
            return null;
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        level.setBlock(pos.below(), state.setValue(HALF, DoubleBlockHalf.LOWER), 3);
    }

    @Override
    @Deprecated
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        this.openBlock(level, pos, player);
        if (state.getValue(SIDE) != CurtainSide.SINGLE) {
            if (state.getValue(SIDE) == CurtainSide.LEFT) {
                if (state.getValue(FACING) == Direction.Axis.X && level.getBlockState(pos.east()).is(this)) {
                    this.openBlock(level, pos.east(), player);
                }
                if (state.getValue(FACING) == Direction.Axis.Z && level.getBlockState(pos.south()).is(this)) {
                    this.openBlock(level, pos.south(), player);
                }
            }
            if (state.getValue(SIDE) == CurtainSide.RIGHT) {
                if (state.getValue(FACING) == Direction.Axis.X && level.getBlockState(pos.west()).is(this)) {
                    this.openBlock(level, pos.west(), player);
                }
                if (state.getValue(FACING) == Direction.Axis.Z && level.getBlockState(pos.north()).is(this)) {
                    this.openBlock(level, pos.north(), player);
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    private void openBlock(Level level, BlockPos pos, Player player) {
        BlockState state = level.getBlockState(pos);
        state = state.cycle(OPEN);
        level.setBlock(pos, state, 10);
        this.playSound(player, level, pos);
        level.gameEvent(player, state.getValue(OPEN) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    @Deprecated
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos nPos, boolean moved) {
        //This or the neighbour has a signal
        Direction dir = state.getValue(HALF) == DoubleBlockHalf.UPPER ? Direction.DOWN : Direction.UP;
        boolean flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.relative(dir));
        if (!this.defaultBlockState().is(block) && flag != state.getValue(POWERED)) {
            if (flag != state.getValue(OPEN)) {
                this.playSound(null, level, pos);
                level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }

            level.setBlock(pos, state.setValue(POWERED, flag).setValue(OPEN, flag), 2);
        }
        if (state.getValue(SIDE) != CurtainSide.SINGLE) {
            if (state.getValue(SIDE) == CurtainSide.LEFT) {
                if (state.getValue(FACING) == Direction.Axis.X && level.getBlockState(pos.east()).is(this)) {
                    this.changeNeighbor(level, state, pos, pos.east(), dir);
                }
                if (state.getValue(FACING) == Direction.Axis.Z && level.getBlockState(pos.south()).is(this)) {
                    this.changeNeighbor(level, state, pos, pos.south(), dir);
                }
            }
            if (state.getValue(SIDE) == CurtainSide.RIGHT) {
                if (state.getValue(FACING) == Direction.Axis.X && level.getBlockState(pos.west()).is(this)) {
                    this.changeNeighbor(level, state, pos, pos.west(), dir);
                }
                if (state.getValue(FACING) == Direction.Axis.Z && level.getBlockState(pos.north()).is(this)) {
                    this.changeNeighbor(level, state, pos, pos.north(), dir);
                }
            }
        }
    }

    private void changeNeighbor(Level level, BlockState state, BlockPos pos, BlockPos opposite, Direction dir) {
        BlockState neighbor = level.getBlockState(opposite);
        boolean flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(opposite.relative(dir));
        if (neighbor.getValue(POWERED) != state.getValue(POWERED)) {
            if (flag != neighbor.getValue(OPEN)) {
                this.playSound(null, level, opposite);
                level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, opposite);
            }

            level.setBlock(opposite, neighbor.setValue(POWERED, flag).setValue(OPEN, flag), 2);
        }
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = reader.getBlockState(blockpos);
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? blockstate.isFaceSturdy(reader, blockpos, Direction.DOWN) : blockstate.is(this);
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(FACING)) {
                case Z -> state.setValue(FACING, Direction.Axis.X);
                case X -> state.setValue(FACING, Direction.Axis.Z);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    @Deprecated
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(defaultBlockState().getValue(HALF) == DoubleBlockHalf.UPPER ? 0 : 1).getY(), pos.getZ());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, POWERED, SIDE);
    }

    public enum CurtainSide implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right");

        private final String name;

        CurtainSide(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.getSerializedName();
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
