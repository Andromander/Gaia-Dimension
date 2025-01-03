package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.model.renderstate.MookaiteConstructRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class MookaiteConstructPartLayer<M extends MookaiteConstructModel> extends RenderLayer<MookaiteConstructRenderState, M> {

    public MookaiteConstructPartLayer(RenderLayerParent<MookaiteConstructRenderState, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int light, MookaiteConstructRenderState entity, float netHeadYaw, float headPitch) {
        for (MookaitePartType part : entity.partList) {
            if (part.isPresent()) {
                String color = part.getSerializedName();
                ResourceLocation location = ModEntitiesRendering.makeTextureNoPrefix("mookaite_construct", part.name() + "/" + color);
                if (location != null && !entity.isInvisible) {
                    VertexConsumer vertex = buffer.getBuffer(RenderType.entityCutout(location));
                    this.getParentModel().renderToBuffer(stack, vertex, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
                }
            }
        }
    }
}
