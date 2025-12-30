package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MookaiteConstruct;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.model.renderstate.MookaiteConstructRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MookaiteConstructPartLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

import java.util.Map;

@NullMarked
public class MookaiteConstructRenderer<T extends MookaiteConstruct, M extends MookaiteConstructModel> extends MobRenderer<T, MookaiteConstructRenderState, M> {

    public MookaiteConstructRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new MookaiteConstructPartLayer<>(this));
    }

    @Override
    public MookaiteConstructRenderState createRenderState() {
        return new MookaiteConstructRenderState();
    }

    @Override
    public void extractRenderState(T entity, MookaiteConstructRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.rightHorn = entity.getPart(MookaiteConstruct.RIGHT_HORN_TYPE);
        state.leftHorn = entity.getPart(MookaiteConstruct.LEFT_HORN_TYPE);
        state.rightEye = entity.getPart(MookaiteConstruct.RIGHT_EYE_TYPE);
        state.leftEye = entity.getPart(MookaiteConstruct.LEFT_EYE_TYPE);
        state.rightShoulder = entity.getPart(MookaiteConstruct.LEFT_SHOULDER_TYPE);
        state.leftShoulder = entity.getPart(MookaiteConstruct.LEFT_SHOULDER_TYPE);
        state.rightArmBrace = entity.getPart(MookaiteConstruct.RIGHT_ARM_BRACE_TYPE);
        state.leftArmBrace = entity.getPart(MookaiteConstruct.LEFT_ARM_BRACE_TYPE);
        state.rightLegBrace = entity.getPart(MookaiteConstruct.RIGHT_LEG_BRACE_TYPE);
        state.leftLegBrace = entity.getPart(MookaiteConstruct.LEFT_LEG_BRACE_TYPE);
        state.partMap = Map.of(
                MookaiteConstruct.RIGHT_HORN,state.rightHorn,
                MookaiteConstruct.LEFT_HORN,state.leftHorn,
                MookaiteConstruct.RIGHT_EYE,state.rightEye,
                MookaiteConstruct.LEFT_EYE,state.leftEye,
                MookaiteConstruct.RIGHT_SHOULDER,state.rightShoulder,
                MookaiteConstruct.LEFT_SHOULDER,state.leftShoulder,
                MookaiteConstruct.RIGHT_ARM,state.rightArmBrace,
                MookaiteConstruct.LEFT_ARM,state.leftArmBrace,
                MookaiteConstruct.RIGHT_LEG,state.rightLegBrace,
                MookaiteConstruct.LEFT_LEG, state.leftLegBrace);
    }

    @Override
    public Identifier getTextureLocation(MookaiteConstructRenderState entity) {
        return ModEntitiesRendering.makeTexture("mookaite_construct/mookaite_construct");
    }
}
