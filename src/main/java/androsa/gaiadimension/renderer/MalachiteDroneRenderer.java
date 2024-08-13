package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MalachiteDrone;
import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MalachiteDroneGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MalachiteDroneRenderer<T extends MalachiteDrone, M extends MalachiteDroneModel<T>> extends MobRenderer<T, M> {

    public MalachiteDroneRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteDroneGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getOwnerUniqueId() != null ? ModEntitiesRendering.makeTexture(entity, "follow") : ModEntitiesRendering.makeTexture(entity, "normal");
    }
}
