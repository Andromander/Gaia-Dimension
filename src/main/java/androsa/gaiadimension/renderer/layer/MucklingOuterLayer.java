package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.renderer.MucklingRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
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
    public void submit(PoseStack stack, SubmitNodeCollector source, int light, SlimeRenderState state, float yrot, float xrot) {
        boolean flag = state.appearsGlowing() && state.isInvisible;
        int overlay = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
        if (!state.isInvisible || flag) {
            if (flag) {
                source.order(1)
                        .submitModel(
                                this.model,
                                state,
                                stack,
                                RenderType.outline(MucklingRenderer.LOCATION),
                                light,
                                overlay,
                                -1,
                                null,
                                state.outlineColor,
                                null);
            } else {
                source.order(1)
                        .submitModel(
                                this.model,
                                state,
                                stack,
                                RenderType.entityTranslucent(MucklingRenderer.LOCATION),
                                light,
                                overlay,
                                -1,
                                null,
                                state.outlineColor,
                                null);
            }
        }
    }
}
