package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public class GDCrystalBloom extends BlockBush implements ModelRegisterCallback {

    protected static final AxisAlignedBB PLANT_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public static final PropertyEnum<CrystalBloomVariant> VARIANT = PropertyEnum.create("variant", CrystalBloomVariant.class);

    public GDCrystalBloom() {
        super(Material.PLANTS);

        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, CrystalBloomVariant.values()[meta]);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i = 0; i < CrystalBloomVariant.values().length; i++)
            items.add(new ItemStack(this, 1, i));
    }

    @Override
    public int damageDropped(IBlockState state) {
        CrystalBloomVariant leafType = state.getValue(VARIANT);
        return leafType.ordinal();
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal());
    }

    public boolean canPlaceBlockAt(IBlockState state) {
        return state.getBlock() == GDBlocks.glitterGrass || state.getBlock() == GDBlocks.heavySoil;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        for (int i = 0; i < CrystalBloomVariant.values().length; i++) {
            String variant = "inventory_" + CrystalBloomVariant.values()[i].getName();
            ModelResourceLocation mrl = new ModelResourceLocation(getRegistryName(), variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, mrl);
        }
    }

    public enum CrystalBloomVariant implements IStringSerializable {
        THISCUS,
        OUZIUM,
        VARLOOM,
        CORRUPT_VARLOOM,
        MISSINGNO;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
