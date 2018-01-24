package androsa.gaiadimension.registry;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDFluidsRegister {

    @SideOnly(Side.CLIENT)
    public static void registerFluidModel(BlockFluidBase fluid) {
        final Item item = Item.getItemFromBlock(fluid);
        net.minecraft.client.renderer.block.model.ModelBakery.registerItemVariants(item);
        String domain = fluid.getRegistryName() == null ? "minecraft" : fluid.getRegistryName().getResourceDomain();
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(new ResourceLocation(domain, "blocks/fluids"), fluid.getFluid().getName());
        ModelLoader.setCustomMeshDefinition(item, androsa.gaiadimension.registry.MeshDefinitionFix.create(stack -> modelResourceLocation));
        ModelLoader.setCustomStateMapper(fluid, new net.minecraft.client.renderer.block.statemap.StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return modelResourceLocation;
            }
        });
    }

            public static Fluid createFluid(String modid, String name, String textureName, boolean hasFlowIcon, boolean hasBucket) {
                ResourceLocation still = new ResourceLocation(modid, textureName + "_still");
                ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(modid, textureName + "_flow") : still;
                Fluid fluid = new Fluid(name, still, flowing);
                if (!FluidRegistry.registerFluid(fluid))
                    fluid = FluidRegistry.getFluid(name);
                if (hasBucket)
                    FluidRegistry.addBucketForFluid(fluid);
                return fluid;
            }
}
