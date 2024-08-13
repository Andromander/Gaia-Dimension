package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.MalachiteDrone;
import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MalachiteDroneGlowLayer<T extends MalachiteDrone, M extends MalachiteDroneModel<T>> extends RenderLayer<T, M> {
    private static RenderType NORMAL;
    private static RenderType FOLLOW;

    public MalachiteDroneGlowLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        this.validate(entity);
        VertexConsumer builder = entity.getOwnerUniqueId() != null ? buffer.getBuffer(FOLLOW) : buffer.getBuffer(NORMAL);
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY);
    }

    private void validate(T entity) {
        if (NORMAL == null) {
            NORMAL = RenderType.eyes(ModEntitiesRendering.makeTexture(entity, "normal_glow"));
        }
        if (FOLLOW == null) {
            FOLLOW = RenderType.eyes(ModEntitiesRendering.makeTexture(entity, "follow_glow"));
        }
    }
}