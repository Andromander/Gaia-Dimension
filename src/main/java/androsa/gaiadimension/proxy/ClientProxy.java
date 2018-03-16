package androsa.gaiadimension.proxy;

import androsa.gaiadimension.entity.GDHowliteWolf;
import androsa.gaiadimension.entity.GDRockyLuggeroth;
import androsa.gaiadimension.entity.GDSpellElement;
import androsa.gaiadimension.entity.boss.GDBlueHowliteWolf;
import androsa.gaiadimension.entity.boss.GDMalachiteGuard;
import androsa.gaiadimension.model.*;
import androsa.gaiadimension.renderer.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    @Override
    public void doPreLoadRegistration() {
        RenderingRegistry.registerEntityRenderingHandler(GDHowliteWolf.class, m -> new EntityRenderHowliteWolf(m, new ModelHowliteWolf(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(GDSpellElement.class, m -> new EntityRenderSpellElement(m, new ModelSpellElement(), 0.0F));
        RenderingRegistry.registerEntityRenderingHandler(GDRockyLuggeroth.class, m -> new EntityRenderRockyLuggeroth(m, new ModelRockyLuggeroth(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(GDBlueHowliteWolf.class, m -> new EntityRenderBlueHowliteWolf(m, new ModelBlueHowliteWolf(), 1.2F));
        RenderingRegistry.registerEntityRenderingHandler(GDMalachiteGuard.class, m -> new EntityRenderMalachiteGuard(m, new ModelMalachiteGuard(), 1.0F));
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
