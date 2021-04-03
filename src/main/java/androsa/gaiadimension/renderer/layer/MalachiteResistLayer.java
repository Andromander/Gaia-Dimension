package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class MalachiteResistLayer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends LayerRenderer<T,M> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachiteguard_resist.png");

    public MalachiteResistLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.displayResistLayer()) {
            float ticks = (float)entity.tickCount + partialTicks;
            EntityModel<T> model = this.getEnergySwirlModel();
            model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.getParentModel().copyPropertiesTo(model);
            IVertexBuilder builder = buffer.getBuffer(RenderType.energySwirl(this.getEnergySwirlTexture(), this.getEnergySwirlX(ticks), ticks * 0.01F));
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
        return new MalachiteGuardModel<>(0.25F);
    }
}
