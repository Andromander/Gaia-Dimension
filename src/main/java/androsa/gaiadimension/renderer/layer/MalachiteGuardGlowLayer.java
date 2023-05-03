package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

//TODO: texture location
public class MalachiteGuardGlowLayer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends RenderLayer<T, M> {
    private static final RenderType textureLoc = RenderType.eyes(new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "malachiteguard_glow.png"));

    public MalachiteGuardGlowLayer(RenderLayerParent<T, M> render) {
        super(render);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        VertexConsumer builder = buffer.getBuffer(textureLoc);
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
