package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class AuraShootBlock extends Block implements IPlantable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
    public static final BooleanProperty IS_TOP = BooleanProperty.create("is_top");
    private static final VoxelShape SHOOT_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_1 = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_2 = Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 13.0D, 10.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE_3 = Block.makeCuboidShape(7.0D, 13.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape TOP_SHOOT_SHAPE = VoxelShapes.or(TOP_SHOOT_SHAPE_1, TOP_SHOOT_SHAPE_2, TOP_SHOOT_SHAPE_3);

    public AuraShootBlock() {
        super(Properties.create(Material.GLASS, MaterialColor.BLUE).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(1).tickRandomly());
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(IS_TOP, true));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        if (state.get(IS_TOP)) {
            return TOP_SHOOT_SHAPE;
        }
        return SHOOT_SHAPE;
    }

    @Override
    @Deprecated
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        if (facing != Direction.UP) {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            Block block = facingState.getBlock();
            return stateIn.with(IS_TOP, block != this);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Block block = context.getWorld().getBlockState(context.getPos().up()).getBlock();
        return this.getDefaultState().with(IS_TOP, block != this);
    }

    @Override
    @Deprecated
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.down());
        if (soil.canSustainPlant(worldIn, pos.down(), Direction.UP, this))
            return true;
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return block == this ||
                block instanceof AbstractGaiaGrassBlock ||
                block instanceof GaiaSoilBlock;
    }

    @Override
    public PlantType getPlantType(IBlockReader reader, BlockPos pos) {
        return PlantType.Plains;
    }

    @Override
    public BlockState getPlant(IBlockReader reader, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    @Deprecated
    public void tick(BlockState state, World worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        BlockPos blockpos = pos.up();

        if (worldIn.isAirBlock(blockpos)) {
            int i;
            i = 1;

            while (worldIn.getBlockState(pos.down(i)).getBlock() == this) {
                ++i;
            }

            if (i < 15) {
                int j = state.get(AGE);

                if (ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
                    if (j == 5) {
                        worldIn.setBlockState(blockpos, this.getDefaultState());
                        worldIn.setBlockState(pos, state.with(AGE, 0).with(IS_TOP, false), 3);
                    } else {
                        worldIn.setBlockState(pos, state.with(AGE, j + 1), 3);
                    }
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, IS_TOP);
    }
}
