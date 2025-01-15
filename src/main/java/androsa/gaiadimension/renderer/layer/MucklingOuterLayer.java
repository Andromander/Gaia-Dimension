package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.renderer.MucklingRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;

public class MucklingOuterLayer extends RenderLayer<SlimeRenderState, SlimeModel> {
    private final SlimeModel model;

    public MucklingOuterLayer(RenderLayerParent<SlimeRenderState, SlimeModel> state, EntityModelSet set) {
        super(state);
        this.model = new SlimeModel(set.bakeLayer(ModelLayers.SLIME_OUTER));
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource source, int light, SlimeRenderState state, float yrot, float xrot) {
        boolean flag = state.appearsGlowing && state.isInvisible;
        if (!state.isInvisible || flag) {
            VertexConsumer vertexconsumer;
            if (flag) {
                vertexconsumer = source.getBuffer(RenderType.outline(MucklingRenderer.LOCATION));
            } else {
                vertexconsumer = source.getBuffer(RenderType.entityTranslucent(MucklingRenderer.LOCATION));
            }

            this.model.setupAnim(state);
            this.model.renderToBuffer(stack, vertexconsumer, light, LivingEntityRenderer.getOverlayCoords(state, 0.0F));
        }
    }
}
