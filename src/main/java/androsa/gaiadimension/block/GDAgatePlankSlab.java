package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import androsa.gaiadimension.registry.ModelUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDAgatePlankSlab extends BlockSlab implements ModelRegisterCallback {

    private static final PropertyEnum<AgatePlankSlab> VARIANT = PropertyEnum.create("variant", AgatePlankSlab.class);

    private final boolean isDouble;

    public GDAgatePlankSlab(boolean isDouble) {
        super(Material.WOOD);

        this.setSoundType(SoundType.STONE);
        this.isDouble = isDouble;
        this.setCreativeTab(GDTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(2.0F);
        this.setLightOpacity(isDouble ? 255 : 0);
        this.setHarvestLevel("axe", 0);

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, AgatePlankSlab.NORMAL);

        if (!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);

        this.setDefaultState(state);
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return AgatePlankSlab.NORMAL;
    }

    @Override
    public boolean isDouble() {
        return isDouble;
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

    public enum AgatePlankSlab implements IStringSerializable {
        NORMAL;

        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}

