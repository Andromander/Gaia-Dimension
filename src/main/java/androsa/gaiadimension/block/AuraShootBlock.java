package androsa.gaiadimension.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class AuraShootBlock extends Block {

    public static final MapCodec<? extends AuraShootBlock> CODEC = simpleCodec(AuraShootBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final BooleanProperty IS_TOP = BooleanProperty.create("is_top");
    private static final VoxelShape SHOOT_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_1 = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_2 = Block.box(6.0D, 10.0D, 6.0D, 10.0D, 13.0D, 10.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_3 = Block.box(7.0D, 13.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE = Shapes.or(TOP_SHOOT_SHAPE_1, TOP_SHOOT_SHAPE_2, TOP_SHOOT_SHAPE_3);

    public AuraShootBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(IS_TOP, true));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        if (state.getValue(IS_TOP)) {
            return TOP_SHOOT_SHAPE;
        }
        return SHOOT_SHAPE;
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState stateIn, LevelReader reader, ScheduledTickAccess ticker, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
        if (!stateIn.canSurvive(reader, currentPos)) {
            ticker.scheduleTick(currentPos, this, 1);
        }

        if (facing != Direction.UP) {
            return super.updateShape(stateIn, reader, ticker, currentPos, facing, facingPos, facingState, random);
        } else {
            Block block = facingState.getBlock();
            return stateIn.setValue(IS_TOP, block != this);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Block block = context.getLevel().getBlockState(context.getClickedPos().above()).getBlock();
        return this.defaultBlockState().setValue(IS_TOP, block != this);
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.below());
        if (soil.canSustainPlant(worldIn, pos.below(), Direction.UP, state).isTrue())
            return true;
        Block block = worldIn.getBlockState(pos.below()).getBlock();
        return block == this ||
                block instanceof AbstractGaiaGrassBlock ||
                block instanceof GaiaSoilBlock;
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (!state.canSurvive(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (worldIn.isEmptyBlock(pos.above())) {
            int i = 1;

            while (worldIn.getBlockState(pos.below(i)).is(this)) {
                ++i;
            }

            if (i < 15) {
                int j = state.getValue(AGE);

                if (CommonHooks.canCropGrow(worldIn, pos, state, true)) {
                    if (j == 5) {
                        worldIn.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                        CommonHooks.fireCropGrowPost(worldIn, pos, state);
                        worldIn.setBlock(pos, state.setValue(AGE, 0).setValue(IS_TOP, false), 3);
                    } else {
                        worldIn.setBlock(pos, state.setValue(AGE, j + 1), 3);
                    }
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, IS_TOP);
    }
}
