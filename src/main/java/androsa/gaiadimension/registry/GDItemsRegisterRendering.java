package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = GaiaDimension.MODID, value = Side.CLIENT)
public class GDItemsRegisterRendering {
    @SubscribeEvent
    public static void onModelRegistryReady(ModelRegistryEvent event) {
        for (ModelRegisterCallback b : GDBlocksRegister.getBlockModels()) b.registerModel();

        for (ModelRegisterCallback i : GDItemsRegister.ItemRegistryHelper.getItemModels()) i.registerModel();
    }
}
