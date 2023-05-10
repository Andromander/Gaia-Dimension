package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.MineralArenthisEntity;
import androsa.gaiadimension.model.MineralArenthisModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MineralArenthisPartsLayer<T extends MineralArenthisEntity, M extends MineralArenthisModel<T>> extends RenderLayer<T, M> {
    private static RenderType GLOW;

    public MineralArenthisPartsLayer(RenderLayerParent<T, M> arentisRenderIn) {
        super(arentisRenderIn);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        VertexConsumer builder = buffer.getBuffer(validate(entity));
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private RenderType validate(T entity) {
        if (GLOW == null) {
            GLOW = RenderType.eyes(ModEntitiesRendering.makeTexture(entity, "glow"));
        }
        return GLOW;
    }
}
