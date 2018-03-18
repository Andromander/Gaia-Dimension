package androsa.gaiadimension.block;

import androsa.gaiadimension.block.enums.GaiaLeavesVariant;
import androsa.gaiadimension.block.enums.SpecialGaiaLeavesVariant;
import androsa.gaiadimension.registry.*;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

import static androsa.gaiadimension.block.enums.SpecialGaiaLeavesVariant.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDSpecialLeaves extends BlockLeaves implements ModelRegisterCallback {

    public static final PropertyEnum<SpecialGaiaLeavesVariant> VARIANT = PropertyEnum.create("variant", SpecialGaiaLeavesVariant.class);

    public GDSpecialLeaves() {
        this.setHardness(0.3F);
        this.setSoundType(SoundType.GLASS);
        this.setLightOpacity(1);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true).withProperty(VARIANT, FOSSILIZED));
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.OAK;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal()));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random par2Random, int par3) {
        switch (state.getValue(VARIANT)) {
            case HEATED:
                return GDItems.hotDust;
            case CRUSTY:
                return Items.GUNPOWDER;
            case CORRUPTED:
                return GDItems.goldstoneDust;
            case FOSSILIZED:
            default:
                return GDItems.fineDust;
        }
    }

    @Override
    public int getSaplingDropChance(IBlockState state) {
        return 20;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE, VARIANT);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        SpecialGaiaLeavesVariant variant = SpecialGaiaLeavesVariant.values()[meta & 0b11];
        return this.getDefaultState().withProperty(VARIANT, variant).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(VARIANT).ordinal();

        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY)) {
            i |= 8;
        }
        return i;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (world.getBlockState(pos) == state) {
            switch (state.getValue(VARIANT)) {
                case HEATED:
                    return 5;
                case FOSSILIZED:
                case CORRUPTED:
                case CRUSTY:
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return Blocks.LEAVES.getBlockLayer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return Blocks.LEAVES.shouldSideBeRendered(state, world, pos, side);
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
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(CHECK_DECAY, DECAYABLE).build());
        ModelUtils.registerToStateSingleVariant(this, VARIANT);
    }
}
