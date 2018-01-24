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
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class GDFluidBlock extends BlockFluidClassic implements SuperRegistry {

    private final String name;

    public GDFluidBlock(CreativeTabs tab, Fluid fluid, Material material, String name) {
        super(fluid, material);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
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
