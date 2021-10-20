package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.model.MalachiteDroneModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MalachiteDroneGlowLayer<T extends MalachiteDroneEntity, M extends MalachiteDroneModel<T>> extends RenderLayer<T, M> {
    private static final RenderType normalLoc = RenderType.eyes(new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_normal_glow.png"));
    private static final RenderType followLoc = RenderType.eyes(new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_follow_glow.png"));

    public MalachiteDroneGlowLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        VertexConsumer builder = entity.getOwnerUniqueId() != null ? buffer.getBuffer(followLoc) : buffer.getBuffer(normalLoc);
        this.getParentModel().renderToBuffer(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}