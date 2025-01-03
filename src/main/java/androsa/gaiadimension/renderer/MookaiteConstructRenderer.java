package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MookaiteConstruct;
import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.model.renderstate.MookaiteConstructRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MookaiteConstructPartLayer;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class MookaiteConstructRenderer<T extends MookaiteConstruct, M extends MookaiteConstructModel> extends BasicEntityRenderer<T, MookaiteConstructRenderState, M> {

    public MookaiteConstructRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, new MookaiteConstructRenderState(), "mookaite_construct", shadow);
        this.addLayer(new MookaiteConstructPartLayer<>(this));
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
        state.partList = ImmutableList.of(
                state.rightHorn, state.leftHorn,
                state.rightEye, state.leftEye,
                state.rightShoulder, state.leftShoulder,
                state.rightArmBrace, state.leftArmBrace,
                state.rightLegBrace, state.leftLegBrace);
    }
}
