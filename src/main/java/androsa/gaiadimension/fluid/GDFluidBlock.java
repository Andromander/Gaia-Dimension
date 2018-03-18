package androsa.gaiadimension.fluid;

import androsa.gaiadimension.registry.MeshDefinitionFix;
import androsa.gaiadimension.registry.SuperRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class GDFluidBlock extends BlockFluidClassic implements SuperRegistry {

    private final String name;

    public GDFluidBlock(CreativeTabs tab, Fluid fluid, Material material, String name) {
        super(fluid, material);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
    }

    @Override
    public int getLightValue(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos)
    {
        if (maxScaledLight == 0)
        {
            return super.getLightValue(state, world, pos);
        }
        int data = quantaPerBlock - state.getValue(LEVEL) - 1;
        return (int) (data / quantaPerBlockFloat * maxScaledLight);
    }

    protected int getDepth(IBlockState state) {
        return state.getMaterial() == this.blockMaterial ? ((Integer)state.getValue(LEVEL)).intValue() : -1;
    }

    protected int getRenderedDepth(IBlockState state)
    {
        int i = this.getDepth(state);
        return i >= 8 ? 0 : i;
    }

    @SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        int i = source.getCombinedLight(pos, 0);
        int j = source.getCombinedLight(pos.up(), 0);
        int k = i & 255;
        int l = j & 255;
        int i1 = i >> 16 & 255;
        int j1 = j >> 16 & 255;
        return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
    }

    public String getModelDir() {
        return "fluids";
    }

    @Override
    public void registerBlock(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(this);
    }

    @Override
    public void registerItem(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel(ModelRegistryEvent e) {
        final Item item = Item.getItemFromBlock(this);
        ModelBakery.registerItemVariants(item);
        String domain = getRegistryName() == null ? "minecraft" : getRegistryName().getResourceDomain();
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(domain + ":blocks/fluids", getFluid().getName());
        ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return modelResourceLocation;
            }
        });
    }
}
