package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
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

    public AuraShootBlock() {
        super(Properties.create(Material.GLASS, MaterialColor.BLUE).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(1).tickRandomly());
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(IS_TOP, true));
    }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHOOT_SHAPE;
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
                        worldIn.setBlockState(pos, state.with(AGE, 0), 4);
                    } else {
                        worldIn.setBlockState(pos, state.with(AGE, j + 1), 4);
                    }
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    @Deprecated
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState ground = worldIn.getBlockState(pos.down());
        Block block = ground.getBlock();

        return ground.canSustainPlant(worldIn, pos.down(), Direction.UP, this) || block == this;

    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, IS_TOP);
    }
}
