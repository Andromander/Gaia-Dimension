package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

//FIXME
public class MalachiteGuardGlowLayer<M extends MalachiteGuardModel> extends RenderLayer<MalachiteGuardRenderState, M> {

    public MalachiteGuardGlowLayer(RenderLayerParent<MalachiteGuardRenderState, M> render) {
        super(render);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, MalachiteGuardRenderState entity, float yrot, float xrot) {
        VertexConsumer builder = buffer.getBuffer(validate());
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY);
    }

    private RenderType validate() {
        return RenderType.eyes(ModEntitiesRendering.makeTexture("malachite_guard", "glow"));
    }
}
