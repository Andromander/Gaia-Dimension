package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MossAgateMonitor;
import androsa.gaiadimension.model.MossAgateMonitorModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MossAgateMonitorRenderer<T extends MossAgateMonitor, M extends MossAgateMonitorModel<T>> extends MobRenderer<T, M> {

    public MossAgateMonitorRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return ModEntitiesRendering.makeTexture("moss_agate_monitor");
    }
}
