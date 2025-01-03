package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.data.GuardPhase;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MalachiteResistLayer<M extends MalachiteGuardModel> extends RenderLayer<MalachiteGuardRenderState,M> {

    private final MalachiteGuardModel model;

    public MalachiteResistLayer(RenderLayerParent<MalachiteGuardRenderState, M> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new MalachiteGuardModel(set.bakeLayer(ModEntitiesRendering.MALACHITE_GUARD_RESIST));
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, MalachiteGuardRenderState entity, float netHeadYaw, float headPitch) {
        if (entity.phase == GuardPhase.RESIST) {
            float ticks = entity.ageInTicks;
            MalachiteGuardModel model = this.getEnergySwirlModel();
            VertexConsumer builder = buffer.getBuffer(RenderType.energySwirl(this.getEnergySwirlTexture(), this.getEnergySwirlX(ticks), ticks * 0.01F));
            model.setupAnim(entity);
            model.renderToBuffer(matrix, builder, light, OverlayTexture.NO_OVERLAY, -8355712);
        }
    }

    protected float getEnergySwirlX(float ticks) {
        return ticks * 0.005F;
    }

    protected ResourceLocation getEnergySwirlTexture() {
        return ModEntitiesRendering.makeTexture("malachite_guard", "resist");
    }

    protected MalachiteGuardModel getEnergySwirlModel() {
        return model;
    }
}
