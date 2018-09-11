package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDAgatePlankStairs extends BlockStairs implements ModelRegisterCallback {

    public GDAgatePlankStairs(IBlockState state) {
        super(state);

        this.setSoundType(SoundType.STONE);
        this.setHardness(1.5F);
        this.setResistance(2.0F);
        this.setCreativeTab(GDTabs.tabBlock);

        this.useNeighborBrightness = true;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, HALF, SHAPE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return super.getMetaFromState(state);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta & 0b0111);
    }

    @Override
    public void getSubBlocks(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> par3List) {
        par3List.add(new ItemStack(this, 1, 0));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtils.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.EAST));
    }
}