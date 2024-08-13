package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.boss.MalachiteGuard;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MalachiteGuardGlowLayer<T extends MalachiteGuard, M extends MalachiteGuardModel<T>> extends RenderLayer<T, M> {

    public MalachiteGuardGlowLayer(RenderLayerParent<T, M> render) {
        super(render);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        VertexConsumer builder = buffer.getBuffer(validate(entity));
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY);
    }

    private RenderType validate(T entity) {
        return RenderType.eyes(ModEntitiesRendering.makeTexture(entity, "glow"));
    }
}
