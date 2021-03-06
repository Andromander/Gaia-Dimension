package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class AuraShootBlock extends Block implements IPlantable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final BooleanProperty IS_TOP = BooleanProperty.create("is_top");
    private static final VoxelShape SHOOT_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_1 = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_2 = Block.box(6.0D, 10.0D, 6.0D, 10.0D, 13.0D, 10.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_3 = Block.box(7.0D, 13.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE = VoxelShapes.or(TOP_SHOOT_SHAPE_1, TOP_SHOOT_SHAPE_2, TOP_SHOOT_SHAPE_3);

    public AuraShootBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(IS_TOP, true));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        if (state.getValue(IS_TOP)) {
            return TOP_SHOOT_SHAPE;
        }
        return SHOOT_SHAPE;
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
        }

        if (facing != Direction.UP) {
            return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            Block block = facingState.getBlock();
            return stateIn.setValue(IS_TOP, block != this);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Block block = context.getLevel().getBlockState(context.getClickedPos().above()).getBlock();
        return this.defaultBlockState().setValue(IS_TOP, block != this);
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.below());
        if (soil.canSustainPlant(worldIn, pos.below(), Direction.UP, this))
            return true;
        Block block = worldIn.getBlockState(pos.below()).getBlock();
        return block == this ||
                block instanceof AbstractGaiaGrassBlock ||
                block instanceof GaiaSoilBlock;
    }

    @Override
    public PlantType getPlantType(IBlockReader reader, BlockPos pos) {
        return PlantType.PLAINS;
    }

    @Override
    public BlockState getPlant(IBlockReader reader, BlockPos pos) {
        return this.defaultBlockState();
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.canSurvive(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.isEmptyBlock(pos.above())) {
            int i;
            i = 1;

            while (worldIn.getBlockState(pos.below(i)).getBlock() == this) {
                ++i;
            }

            if (i < 15) {
                int j = state.getValue(AGE);

                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    if (j == 5) {
                        worldIn.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                        worldIn.setBlock(pos, state.setValue(AGE, 0).setValue(IS_TOP, false), 3);
                    } else {
                        worldIn.setBlock(pos, state.setValue(AGE, j + 1), 3);
                    }
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, IS_TOP);
    }
}
