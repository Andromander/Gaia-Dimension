package androsa.gaiadimension.block.blocksore;

import androsa.gaiadimension.block.enums.OpalOreVariant;
import androsa.gaiadimension.registry.GDItems;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDOreOpal extends Block implements ModelRegisterCallback {

    public static final PropertyEnum<OpalOreVariant> VARIANT = PropertyEnum.create("variant", OpalOreVariant.class);

    public GDOreOpal() {
        super(Material.ROCK);

        this.setHardness(2.0F);
        this.setResistance(15.0F);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, OpalOreVariant.RED));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int j) {
        switch (state.getValue(VARIANT)) {
            default:
            case RED:
                return GDItems.red_opal;
            case BLUE:
                return GDItems.blue_opal;
            case GREEN:
                return GDItems.green_opal;
            case WHITE:
                return GDItems.white_opal;
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return super.quantityDropped(state, fortune, random);
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
        return getDefaultState().withProperty(VARIANT, OpalOreVariant.values()[meta]);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i = 0; i < OpalOreVariant.values().length; i++)
            items.add(new ItemStack(this, 1, i));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtils.registerToStateSingleVariant(this, VARIANT);
    }
}
