package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
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

public class MalachiteResistLayer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends RenderLayer<T,M> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachiteguard_resist.png");
    private final EntityModel<T> model;

    public MalachiteResistLayer(RenderLayerParent<T, M> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new MalachiteGuardModel<>(set.bakeLayer(ModEntitiesRendering.MALACHITE_GUARD_RESIST));
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getPhase() == 2) {
            float ticks = (float)entity.tickCount + partialTicks;
            EntityModel<T> model = this.getEnergySwirlModel();
            model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.getParentModel().copyPropertiesTo(model);
            VertexConsumer builder = buffer.getBuffer(RenderType.energySwirl(this.getEnergySwirlTexture(), this.getEnergySwirlX(ticks), ticks * 0.01F));
            model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            model.renderToBuffer(matrix, builder, light, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
        }
    }

    protected float getEnergySwirlX(float ticks) {
        return ticks * 0.005F;
    }

    protected ResourceLocation getEnergySwirlTexture() {
        return textureLoc;
    }

    protected EntityModel<T> getEnergySwirlModel() {
        return model;
    }
}
