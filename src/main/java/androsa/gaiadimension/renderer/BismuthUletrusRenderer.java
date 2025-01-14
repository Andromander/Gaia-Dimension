package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.BismuthUletrus;
import androsa.gaiadimension.model.BismuthUletrusModel;
import androsa.gaiadimension.model.renderstate.BismuthUletrusRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BismuthUletrusRenderer extends MobRenderer<BismuthUletrus, BismuthUletrusRenderState, BismuthUletrusModel> {

    public BismuthUletrusRenderer(EntityRendererProvider.Context context, BismuthUletrusModel model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public BismuthUletrusRenderState createRenderState() {
        return new BismuthUletrusRenderState();
    }

    @Override
    public void extractRenderState(BismuthUletrus entity, BismuthUletrusRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isResting = entity.getResting();
    }

    @Override
    public ResourceLocation getTextureLocation(BismuthUletrusRenderState entity) {
        return ModEntitiesRendering.makeTexture("bismuth_uletrus");
    }
}
