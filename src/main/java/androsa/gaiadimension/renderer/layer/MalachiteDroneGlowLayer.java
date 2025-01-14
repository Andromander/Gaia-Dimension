package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.model.renderstate.MalachiteDroneRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

//FIXME
public class MalachiteDroneGlowLayer<S extends MalachiteDroneRenderState, M extends MalachiteDroneModel> extends RenderLayer<S, M> {
    private static RenderType NORMAL;
    private static RenderType FOLLOW;

    public MalachiteDroneGlowLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, S entity, float yrot, float xrot) {
        this.validate();
        VertexConsumer builder = entity.isFollowing ? buffer.getBuffer(FOLLOW) : buffer.getBuffer(NORMAL);
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY);
    }

    private void validate() {
        if (NORMAL == null) {
            NORMAL = RenderType.eyes(ModEntitiesRendering.makeTexture("malachite_drone", "normal_glow"));
        }
        if (FOLLOW == null) {
            FOLLOW = RenderType.eyes(ModEntitiesRendering.makeTexture("malachite_drone", "follow_glow"));
        }
    }
}