package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MineralArenthisModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MineralArenthisPartsLayer<M extends MineralArenthisModel> extends RenderLayer<SquidRenderState, M> {
    private static RenderType GLOW;

    public MineralArenthisPartsLayer(RenderLayerParent<SquidRenderState, M> arentisRenderIn) {
        super(arentisRenderIn);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, SquidRenderState entity, float v, float v1) {
        VertexConsumer builder = buffer.getBuffer(validate());
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY);
    }

    private RenderType validate() {
        if (GLOW == null) {
            GLOW = RenderType.eyes(ModEntitiesRendering.makeTexture("mineral_arenthis", "glow"));
        }
        return GLOW;
    }
}
