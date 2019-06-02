package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDAuraShoot extends GDBlock implements IPlantable {

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 5);
    public static final PropertyBool IS_TOP = PropertyBool.create("is_top");
    private static final AxisAlignedBB SHOOT_AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

    public GDAuraShoot() {
        super(Material.GLASS, MapColor.BLUE, SoundType.GLASS, "pickaxe", 1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(IS_TOP, true));
        this.setTickRandomly(true);
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SHOOT_AABB;
    }

    @Override
    @Deprecated
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state.withProperty(IS_TOP, block != this);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        BlockPos blockpos = pos.up();

        if (worldIn.isAirBlock(blockpos)) {
            int i;
            i = 1;

            while (worldIn.getBlockState(pos.down(i)).getBlock() == this) {
                ++i;
            }

            if (i < 15) {
                int j = state.getValue(AGE);

                if (ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
                    if (j == 5) {
                        worldIn.setBlockState(blockpos, this.getDefaultState());
                        IBlockState iblockstate = state.withProperty(AGE, 0);
                        worldIn.setBlockState(pos, iblockstate, 4);
                        iblockstate.neighborChanged(worldIn, blockpos, this, pos);
                    } else {
                        worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
                    }
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();

        return block.canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this) || block == this;

    }

    public boolean canBlockStay(World worldIn, BlockPos pos) {
        return this.canPlaceBlockAt(worldIn, pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        this.checkForDrop(worldIn, pos, state);
    }

    public final void checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, meta >> 1).withProperty(IS_TOP, (meta & 1) != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(AGE) << 1) | (state.getValue(IS_TOP) ? 1 : 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE, IS_TOP);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(AGE).build());
        ModelUtils.registerToState(this, 0, getDefaultState());
    }
}
