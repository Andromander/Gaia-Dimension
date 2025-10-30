package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.MookaiteConstruct;
import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.model.renderstate.MookaiteConstructRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MookaiteConstructPartLayer<M extends MookaiteConstructModel> extends RenderLayer<MookaiteConstructRenderState, M> {

    public MookaiteConstructPartLayer(RenderLayerParent<MookaiteConstructRenderState, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack stack, SubmitNodeCollector buffer, int light, MookaiteConstructRenderState state, float netHeadYaw, float headPitch) {
        for (Map.Entry<MookaiteConstruct.MookaitePart, MookaitePartType> part : state.partMap.entrySet()) {
            if (part.getValue().isPresent()) {
                String color = part.getValue().getSerializedName();
                ResourceLocation location = ModEntitiesRendering.makeTextureNoPrefix("mookaite_construct", part.getKey().name() + "/" + color);
                if (location != null && !state.isInvisible) {
                    buffer.submitModel(
                            this.getParentModel(),
                            state,
                            stack,
                            RenderType.entityCutout(location),
                            light,
                            LivingEntityRenderer.getOverlayCoords(state, 0.0F),
                            state.outlineColor,
                            null);
                }
            }
        }
    }
}
