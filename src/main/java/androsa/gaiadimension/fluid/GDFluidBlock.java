package androsa.gaiadimension.fluid;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class GDFluidBlock extends BlockFluidClassic implements ModelRegisterCallback {

    public GDFluidBlock(Fluid fluid, Material material) {
        super(fluid, material);
        setLightOpacity(3);
        setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    public int getLightValue(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        if (maxScaledLight == 0) {
            return super.getLightValue(state, world, pos);
        }
        int data = quantaPerBlock - state.getValue(LEVEL) - 1;
        return (int) (data / quantaPerBlockFloat * maxScaledLight);
    }

    @Override
    @Deprecated
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        int i = source.getCombinedLight(pos, 0);
        int j = source.getCombinedLight(pos.up(), 0);
        int k = i & 255;
        int l = j & 255;
        int i1 = i >> 16 & 255;
        int j1 = j >> 16 & 255;
        return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
    }

    @Override
    public BlockFluidBase setQuantaPerBlock(int quantaPerBlock) {
        if (quantaPerBlock > 16 || quantaPerBlock < 1) quantaPerBlock = 8;
        this.quantaPerBlock = quantaPerBlock;
        this.quantaPerBlockFloat = quantaPerBlock;
        return this;
    }

    @Override
    public void registerModel() {
        final Item item = Item.getItemFromBlock(this);
        ModelBakery.registerItemVariants(item);
        String domain = getRegistryName() == null ? "minecraft" : getRegistryName().getNamespace();
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(domain + ":fluids", getFluid().getName());
        ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return modelResourceLocation;
            }
        });
    }
}
