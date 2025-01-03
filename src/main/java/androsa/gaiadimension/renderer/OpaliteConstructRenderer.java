package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.OpaliteContruct;
import androsa.gaiadimension.model.OpaliteConstructModel;
import androsa.gaiadimension.model.renderstate.OpaliteConstructRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class OpaliteConstructRenderer extends BasicEntityRenderer<OpaliteContruct, OpaliteConstructRenderState, OpaliteConstructModel> {

    public OpaliteConstructRenderer(EntityRendererProvider.Context context, OpaliteConstructModel model, float shadow) {
        super(context, model, new OpaliteConstructRenderState(), "opalite_construct", shadow);
    }

    @Override
    public void extractRenderState(OpaliteContruct entity, OpaliteConstructRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isConstructing = entity.isConstructing();
    }
}
