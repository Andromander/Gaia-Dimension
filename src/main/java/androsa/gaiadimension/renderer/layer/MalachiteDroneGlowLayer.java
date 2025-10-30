package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.model.renderstate.MalachiteDroneRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MalachiteDroneGlowLayer<S extends MalachiteDroneRenderState, M extends MalachiteDroneModel> extends RenderLayer<S, M> {

    private final RenderType normal = RenderType.eyes(ModEntitiesRendering.makeTexture("malachite_drone", "normal_glow"));
    private final RenderType follower = RenderType.eyes(ModEntitiesRendering.makeTexture("malachite_drone", "follow_glow"));

    public MalachiteDroneGlowLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack matrixStack, SubmitNodeCollector buffer, int i, S entity, float yrot, float xrot) {
        buffer.order(1)
                .submitModel(
                        this.getParentModel(),
                        entity,
                        matrixStack,
                        renderType(entity),
                        i,
                        OverlayTexture.NO_OVERLAY,
                        15728640,
                        null,
                        entity.outlineColor,
                        null
                );
    }

    private RenderType renderType(S entity) {
        if (entity.isFollowing) {
            return follower;
        } else {
            return normal;
        }
    }
}