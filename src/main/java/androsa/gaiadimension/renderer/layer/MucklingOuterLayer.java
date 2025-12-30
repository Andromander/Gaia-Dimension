package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.renderer.MucklingRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import org.jspecify.annotations.NullMarked;

@NullMarked
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
                                RenderTypes.outline(MucklingRenderer.LOCATION),
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
                                RenderTypes.entityTranslucent(MucklingRenderer.LOCATION),
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
