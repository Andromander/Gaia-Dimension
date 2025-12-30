package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.OpaliteContruct;
import androsa.gaiadimension.model.OpaliteConstructModel;
import androsa.gaiadimension.model.renderstate.OpaliteConstructRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class OpaliteConstructRenderer extends MobRenderer<OpaliteContruct, OpaliteConstructRenderState, OpaliteConstructModel> {

    public OpaliteConstructRenderer(EntityRendererProvider.Context context, OpaliteConstructModel model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public OpaliteConstructRenderState createRenderState() {
        return new OpaliteConstructRenderState();
    }

    @Override
    public void extractRenderState(OpaliteContruct entity, OpaliteConstructRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isConstructing = entity.isConstructing();
    }

    @Override
    public Identifier getTextureLocation(OpaliteConstructRenderState entity) {
        return ModEntitiesRendering.makeTexture("opalite_construct");
    }
}
