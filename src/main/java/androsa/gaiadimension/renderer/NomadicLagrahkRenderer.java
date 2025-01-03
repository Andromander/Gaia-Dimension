package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.NomadicLagrahk;
import androsa.gaiadimension.model.NomadicLagrahkModel;
import androsa.gaiadimension.model.renderstate.NomadicLagrahkRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NomadicLagrahkRenderer<T extends NomadicLagrahk, M extends NomadicLagrahkModel> extends MobRenderer<T, NomadicLagrahkRenderState, M> {

    public NomadicLagrahkRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public NomadicLagrahkRenderState createRenderState() {
        return new NomadicLagrahkRenderState();
    }

    @Override
    public void extractRenderState(T entity, NomadicLagrahkRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.variant = entity.getEntityVariant();
    }

    @Override
    public ResourceLocation getTextureLocation(NomadicLagrahkRenderState entity) {
        return ModEntitiesRendering.makeTexture("nomadic_lagrahk", entity.variant.getSerializedName());
    }
}
