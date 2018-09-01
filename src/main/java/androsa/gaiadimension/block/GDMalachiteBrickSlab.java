package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDMalachiteBrickSlab extends BlockSlab implements ModelRegisterCallback {

    private static final PropertyEnum<MalachiteSlabVariant> VARIANT = PropertyEnum.create("variant", MalachiteSlabVariant.class);

    private final boolean isDouble;

    public GDMalachiteBrickSlab(boolean isDouble) {
        super(Material.ROCK);

        this.isDouble = isDouble;
        this.setCreativeTab(GDTabs.tabBlock);
        this.setHardness(20F);
        this.setResistance(100F);
        this.setLightOpacity(isDouble ? 255 : 0);

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, MalachiteSlabVariant.NORMAL);

        if (!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);

        this.setDefaultState(state);
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return MalachiteSlabVariant.NORMAL;
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(GDBlocks.malachite_brick_slab);
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, VARIANT, HALF);
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.isDouble() ? this.getDefaultState() : this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF).ordinal();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        if (this.isDouble())
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).ignore(HALF).build());
        else {
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).build());
            ModelUtils.registerToState(this, 0, getDefaultState());
        }
    }

    public enum MalachiteSlabVariant implements IStringSerializable {
        NORMAL;

        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
