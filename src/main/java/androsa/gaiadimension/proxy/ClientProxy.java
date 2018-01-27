package androsa.gaiadimension.proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    @Override
    public void doPreLoadRegistration() {
        ;
    }
    @Override
    public void doOnLoadRegistration() {
        ;
    }
    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().world;
    }
}
