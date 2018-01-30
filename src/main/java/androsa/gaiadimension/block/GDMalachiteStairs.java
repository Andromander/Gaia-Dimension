package androsa.gaiadimension.block;

import androsa.gaiadimension.block.enums.MalachiteStairsVariant;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDMalachiteStairs extends BlockStairs implements ModelRegisterCallback {

    public static final PropertyEnum<MalachiteStairsVariant> VARIANT = PropertyEnum.create("variant", MalachiteStairsVariant.class);

    public GDMalachiteStairs(IBlockState state) {
        super(state);
        this.setHardness(20);
        this.setResistance(100);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, MalachiteStairsVariant.BRICKS));
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, HALF, SHAPE, VARIANT);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return super.getMetaFromState(state) + (state.getValue(VARIANT) == MalachiteStairsVariant.PILLAR ? 8 : 0);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta & 0b0111).withProperty(VARIANT, (meta & 0b1000) == 8 ? MalachiteStairsVariant.PILLAR : MalachiteStairsVariant.BRICKS);
    }

    @Override
    public void getSubBlocks(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> par3List) {
        par3List.add(new ItemStack(this, 1, 0));
        par3List.add(new ItemStack(this, 1, 8));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT) == MalachiteStairsVariant.PILLAR ? 8 : 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtils.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.SOUTH));
    }
}
