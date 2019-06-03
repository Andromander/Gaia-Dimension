package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class GDGaiaSlab extends BlockSlab implements ModelRegisterCallback {

    private static final PropertyEnum<GaiaSlab> VARIANT = PropertyEnum.create("variant", GaiaSlab.class);
    private final boolean isDouble;
    private final MapColor mapColor;
    private final Supplier<Item> slabDrop;

    public GDGaiaSlab(boolean isDouble, Material material, MapColor color, SoundType sound, String tool, int level, Supplier<Item> drop) {
        super(material, color);

        this.setSoundType(sound);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setLightOpacity(isDouble ? 255 : 0);
        this.setHarvestLevel(tool, level);
        this.isDouble = isDouble;
        this.mapColor = color;
        this.slabDrop = drop;

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, GaiaSlab.NORMAL);
        if (!this.isDouble())
            state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        this.setDefaultState(state);
    }

    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return mapColor;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return GaiaSlab.NORMAL;
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public int quantityDropped(Random random) {
        return isDouble ? 2 : 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int j) {
        if (slabDrop != null) {
            return slabDrop.get();
        } else {
            return Item.getItemFromBlock(this);
        }
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

    public enum GaiaSlab implements IStringSerializable {
        NORMAL;

        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
