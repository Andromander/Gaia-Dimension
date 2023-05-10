package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MookaiteConstructPartLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MookaiteConstructRenderer<T extends MookaiteConstructEntity, M extends MookaiteConstructModel<T>> extends BasicEntityRenderer<T, M> {

    public MookaiteConstructRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new MookaiteConstructPartLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return ModEntitiesRendering.makeTexture(entity, "");
    }
}
