package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class MookaiteConstructPartLayer<T extends MookaiteConstructEntity, M extends MookaiteConstructModel<T>> extends RenderLayer<T, M> {

    public MookaiteConstructPartLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        for (MookaiteConstructEntity.MookaitePart part : MookaiteConstructEntity.PARTS) {
            if (entity.isPresent(part)) {
                String color = MookaiteConstructEntity.INT_TO_COLOR.getOrDefault(entity.getPart(part), MookaiteConstructEntity.INT_TO_COLOR.get(1));
                ResourceLocation location = ModEntitiesRendering.makeTextureNoPrefix(entity, part.name() + "/" + color);
                if (location != null && !entity.isInvisible()) {
                    VertexConsumer vertex = buffer.getBuffer(RenderType.entityCutout(location));
                    this.getParentModel().renderToBuffer(stack, vertex, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }
    }
}
