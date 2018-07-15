package androsa.gaiadimension.block;

import androsa.gaiadimension.block.enums.SaplingVariant;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.world.gen.*;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDAgateSapling extends BlockBush implements IGrowable, ModelRegisterCallback {
    public static final PropertyEnum<SaplingVariant> TREE_TYPE = PropertyEnum.create("tree_type", SaplingVariant.class);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public GDAgateSapling() {
        this.setCreativeTab(GDTabs.tabBlock);
        this.setSoundType(SoundType.GLASS);
        this.setDefaultState(blockState.getBaseState().withProperty(TREE_TYPE, SaplingVariant.PINK_AGATE));
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        WorldGenerator treeGenerator;

        switch (state.getValue(TREE_TYPE)) {
            case PINK_AGATE:
                treeGenerator = new GDGenPinkAgateTree(true);
                break;
            case BLUE_AGATE:
                treeGenerator = new GDGenBlueAgateTree(true);
                break;
            case GREEN_AGATE:
                treeGenerator = new GDGenGreenAgateTree(true);
                break;
            case PURPLE_AGATE:
                treeGenerator = new GDGenPurpleAgateTree(true);
                break;
            case CORRUPTED:
                treeGenerator = new GDGenGoldstoneCorruptTree(true);
                break;
            case CRUSTY:
                treeGenerator = new GDGenBurntAgateTree(true);
                break;
            case HEATED:
                treeGenerator = new GDGenFieryAgateTree(true);
                break;
            case FOSSILIZED:
            default:
                treeGenerator = new GDGenFossilizedTree(true);
                break;
        }

        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

        if (!treeGenerator.generate(world, rand, pos)) {
            world.setBlockState(pos, state, 4);
        }
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TREE_TYPE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TREE_TYPE).ordinal();
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TREE_TYPE, SaplingVariant.values()[meta % SaplingVariant.values().length]);
    }

    @Override
    public void getSubBlocks(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> par3List) {
        par3List.add(new ItemStack(this, 1, 0));
        par3List.add(new ItemStack(this, 1, 1));
        par3List.add(new ItemStack(this, 1, 2));
        par3List.add(new ItemStack(this, 1, 3));
        par3List.add(new ItemStack(this, 1, 4));
        par3List.add(new ItemStack(this, 1, 5));
        par3List.add(new ItemStack(this, 1, 6));
        par3List.add(new ItemStack(this, 1, 7));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, world.getBlockState(pos).getValue(TREE_TYPE).ordinal());
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        for (int i = 0; i < SaplingVariant.values().length; i++) {
            String variant = "inventory_" + SaplingVariant.values()[i].getName();
            ModelResourceLocation mrl = new ModelResourceLocation(getRegistryName(), variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, mrl);
        }
    }

}
