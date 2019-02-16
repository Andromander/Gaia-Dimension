package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.tileentity.TileEntityRestructurer;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@MethodsReturnNonnullByDefault
public class GDRestructurer extends Block implements ModelRegisterCallback, ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private final boolean isRestructuring;
    private static boolean keepInventory;

    public GDRestructurer(boolean flag) {
        super(Material.ROCK);
        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 1);
        this.isRestructuring = flag;

        if (!isRestructuring) {
            this.setCreativeTab(GDTabs.tabBlock);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(GDBlocks.restructurer_idle);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState block = worldIn.getBlockState(pos.north());
            IBlockState block1 = worldIn.getBlockState(pos.south());
            IBlockState block2 = worldIn.getBlockState(pos.west());
            IBlockState block3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
                enumfacing = EnumFacing.SOUTH;
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
                enumfacing = EnumFacing.NORTH;
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
                enumfacing = EnumFacing.EAST;
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
                enumfacing = EnumFacing.WEST;

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote)
            FMLNetworkHandler.openGui(player, GaiaDimension.instance, CommonProxy.GuiID.GLITTER_FURNACE.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    public static void updateGlitterBlockState(boolean flag, World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        TileEntity tileentity = world.getTileEntity(pos);
        keepInventory = true;

        if (flag)
            world.setBlockState(pos, GDBlocks.restructurer_lit.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        else
            world.setBlockState(pos, GDBlocks.restructurer_idle.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int index) {
        return new TileEntityRestructurer();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {

        world.setBlockState(pos, state.withProperty(FACING, living.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName())
            ((TileEntityRestructurer) world.getTileEntity(pos)).setCustomInventoryName(stack.getDisplayName());
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntityRestructurer tileentityglitter = (TileEntityRestructurer) world.getTileEntity(pos);

            if (tileentityglitter != null) {

                InventoryHelper.dropInventoryItems(world, pos, tileentityglitter);

                world.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if (isRestructuring){
            EnumFacing enumfacing = state.getValue(FACING);
            double d0 = pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            switch (enumfacing) {
                case WEST:
                    world.spawnParticle(EnumParticleTypes.END_ROD, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    world.spawnParticle(EnumParticleTypes.END_ROD, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    world.spawnParticle(EnumParticleTypes.END_ROD, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    world.spawnParticle(EnumParticleTypes.END_ROD, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
                default:
                    break;
            }
        }
    }

    @Override
    @Deprecated
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
        return Container.calcRedstone(world.getTileEntity(pos));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(GDBlocks.restructurer_idle);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
            enumfacing = EnumFacing.NORTH;

        return getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this).add(FACING).build();
    }
}
