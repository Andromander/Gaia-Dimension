package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MalachiteDrone;
import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.model.renderstate.MalachiteDroneRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MalachiteDroneGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MalachiteDroneRenderer<T extends MalachiteDrone, M extends MalachiteDroneModel> extends MobRenderer<T, MalachiteDroneRenderState, M> {

    public MalachiteDroneRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteDroneGlowLayer<>(this));
    }

    @Override
    public MalachiteDroneRenderState createRenderState() {
        return new MalachiteDroneRenderState();
    }

    @Override
    public void extractRenderState(T entity, MalachiteDroneRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isFollowing = entity.getOwnerReference() != null;
    }

    @Override
    public ResourceLocation getTextureLocation(MalachiteDroneRenderState entity) {
        return entity.isFollowing ? ModEntitiesRendering.makeTexture("malachite_drone", "follow") : ModEntitiesRendering.makeTexture("malachite_drone", "normal");
    }
}
