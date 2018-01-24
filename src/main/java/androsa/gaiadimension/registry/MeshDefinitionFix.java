package androsa.gaiadimension.registry;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface MeshDefinitionFix extends ItemMeshDefinition {
    ModelResourceLocation getLocation(ItemStack stack);

    static ItemMeshDefinition create(MeshDefinitionFix lambda) {
        return lambda;
    }

    @Override
    default ModelResourceLocation getModelLocation(ItemStack stack) {
        return getLocation(stack);
    }
}
