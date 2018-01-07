package androsa.gaiadimension.block;

import androsa.gaiadimension.block.enums.GaiaLogVariant;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
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

public class GDGaiaLog extends BlockLog implements ModelRegisterCallback {

    public static final PropertyEnum<GaiaLogVariant> VARIANT = PropertyEnum.create("variant", GaiaLogVariant.class);

    public GDGaiaLog() {
        this.setHardness(1.5F);
        this.setResistance(2.0F);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setDefaultState(this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, GaiaLogVariant.values()[meta & 3]);

        switch (meta & 0b1100) {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(VARIANT).ordinal();

        switch (state.getValue(LOG_AXIS)) {
            case X:
                i |= 4;
                break;
            case Y:
                i |= 0;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }
        return i;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(state.getBlock(), 1, state.getValue(VARIANT).ordinal());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtils.registerToStateSingleVariant(this, VARIANT);
    }
}
