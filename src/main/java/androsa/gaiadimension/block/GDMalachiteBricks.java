package androsa.gaiadimension.block;

import androsa.gaiadimension.block.enums.MalachiteBricksVariant;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDMalachiteBricks extends Block implements ModelRegisterCallback {

    public static final PropertyEnum<MalachiteBricksVariant> VARIANT = PropertyEnum.create("variant", MalachiteBricksVariant.class);

    public GDMalachiteBricks() {
        super(Material.ROCK);

        this.setHardness(20);
        this.setResistance(100);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, MalachiteBricksVariant.NORMAL));
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
        return getDefaultState().withProperty(VARIANT, MalachiteBricksVariant.values()[meta]);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i = 0; i < MalachiteBricksVariant.values().length; i++)
            items.add(new ItemStack(this, 1, i));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtils.registerToStateSingleVariant(this, VARIANT);
    }
}
