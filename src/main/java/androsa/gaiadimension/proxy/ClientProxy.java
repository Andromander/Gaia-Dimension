package androsa.gaiadimension.proxy;

import androsa.gaiadimension.entity.GDHowliteWolf;
import androsa.gaiadimension.entity.boss.GDBlueHowliteWolf;
import androsa.gaiadimension.model.ModelBlueHowliteWolf;
import androsa.gaiadimension.model.ModelHowliteWolf;
import androsa.gaiadimension.renderer.EntityRenderBlueHowliteWolf;
import androsa.gaiadimension.renderer.EntityRenderHowliteWolf;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    @Override
    public void doPreLoadRegistration() {
        RenderingRegistry.registerEntityRenderingHandler(GDHowliteWolf.class, m -> new EntityRenderHowliteWolf(m, new ModelHowliteWolf(), 0.6F));

        RenderingRegistry.registerEntityRenderingHandler(GDBlueHowliteWolf.class, m -> new EntityRenderBlueHowliteWolf(m, new ModelBlueHowliteWolf(), 1.2F));
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
