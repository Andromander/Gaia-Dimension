package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.BismuthUletrus;
import androsa.gaiadimension.model.BismuthUletrusModel;
import androsa.gaiadimension.model.renderstate.BismuthUletrusRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BismuthUletrusRenderer extends BasicEntityRenderer<BismuthUletrus, BismuthUletrusRenderState, BismuthUletrusModel> {

    public BismuthUletrusRenderer(EntityRendererProvider.Context context, BismuthUletrusModel model, float shadow) {
        super(context, model, new BismuthUletrusRenderState(), "bismuth_uletrus", shadow);
    }

    @Override
    public void extractRenderState(BismuthUletrus entity, BismuthUletrusRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isResting = entity.getResting();
    }
}
