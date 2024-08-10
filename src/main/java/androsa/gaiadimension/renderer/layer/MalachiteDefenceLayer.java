package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.data.GuardPhase;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MalachiteDefenceLayer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends RenderLayer<T,M> {

    private final EntityModel<T> model;

    public MalachiteDefenceLayer(RenderLayerParent<T, M> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new MalachiteGuardModel<>(set.bakeLayer(ModEntitiesRendering.MALACHITE_GUARD_DEFENCE));
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getPhase() == GuardPhase.DEFENCE) {
            float ticks = (float)entity.tickCount + partialTicks;
            EntityModel<T> model = this.getEnergySwirlModel();
            model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.getParentModel().copyPropertiesTo(model);
            VertexConsumer builder = buffer.getBuffer(RenderType.energySwirl(this.getEnergySwirlTexture(entity), this.getEnergySwirlX(ticks), ticks * 0.01F));
            model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            model.renderToBuffer(matrix, builder, light, OverlayTexture.NO_OVERLAY, -8355712);
        }
    }

    protected float getEnergySwirlX(float ticks) {
        return ticks * 0.01F;
    }

    protected ResourceLocation getEnergySwirlTexture(T entity) {
        return ModEntitiesRendering.makeTexture(entity, "defence");
    }

    protected EntityModel<T> getEnergySwirlModel() {
        return model;
    }
}
