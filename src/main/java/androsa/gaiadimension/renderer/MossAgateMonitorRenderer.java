package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MossAgateMonitor;
import androsa.gaiadimension.model.MossAgateMonitorModel;
import androsa.gaiadimension.model.renderstate.MossAgateMonitorRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class MossAgateMonitorRenderer<T extends MossAgateMonitor, M extends MossAgateMonitorModel> extends MobRenderer<T, MossAgateMonitorRenderState, M> {

    public MossAgateMonitorRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public MossAgateMonitorRenderState createRenderState() {
        return new MossAgateMonitorRenderState();
    }

    @Override
    public void extractRenderState(T entity, MossAgateMonitorRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isAngry = entity.isAngry();
    }

    @Override
    public Identifier getTextureLocation(MossAgateMonitorRenderState entity) {
        return ModEntitiesRendering.makeTexture("moss_agate_monitor");
    }
}
