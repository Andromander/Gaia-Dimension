package androsa.gaiadimension.registry;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.registries.IRegistryDelegate;

import java.util.Map;

public class ModelUtils {
    private static final Map<IRegistryDelegate<Block>, IStateMapper> stateMappers = ReflectionHelper.getPrivateValue(ModelLoader.class, null, "customStateMappers");
    private static final IStateMapper defaultStateMapper = new DefaultStateMapper();

    public static void registerToState(Block b, int itemMeta, IBlockState state) {
        ModelResourceLocation mrl = stateMappers.getOrDefault(state.getBlock().delegate, defaultStateMapper)
                .putStateModelLocations(state.getBlock())
                .get(state);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), itemMeta, mrl);
    }
}
