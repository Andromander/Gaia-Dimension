package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDAgateLeaves extends BlockLeaves implements ModelRegisterCallback {

    private final Supplier<Item> itemSupplier;

    public GDAgateLeaves(Supplier<Item> block) {
        this.setHardness(0.3F);
        this.setSoundType(SoundType.GLASS);
        this.setLightOpacity(1);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));

        itemSupplier = block;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int par3) {
        return itemSupplier.get();
    }

    @Override
    public int getSaplingDropChance(IBlockState state) {
        return 20;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY)) {
            i |= 8;
        }
        return i;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return Blocks.LEAVES.getRenderLayer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Deprecated
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return Blocks.LEAVES.shouldSideBeRendered(state, world, pos, side);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(CHECK_DECAY, DECAYABLE).build());
        ModelUtils.registerToState(this, 0, getDefaultState());
    }
}
