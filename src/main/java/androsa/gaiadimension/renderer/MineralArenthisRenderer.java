package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MineralArenthisEntity;
import androsa.gaiadimension.model.MineralArenthisModel;
import androsa.gaiadimension.renderer.layer.MineralArenthisPartsLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;

public class MineralArenthisRenderer<T extends MineralArenthisEntity, M extends MineralArenthisModel<T>> extends BasicEntityRenderer<T, M> {

    public MineralArenthisRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        this.addLayer(new MineralArenthisPartsLayer<>(this));
    }

    @Override
    protected void setupRotations(T entityLiving, PoseStack matrixStack, float rotationPitch, float rotationYaw, float partialTicks) {
        float f = Mth.lerp(partialTicks, entityLiving.prevArenthisPitch, entityLiving.arenthisPitch);
        float f1 = Mth.lerp(partialTicks, entityLiving.prevArenthisYaw, entityLiving.arenthisYaw);
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - rotationYaw));
        matrixStack.mulPose(Axis.XP.rotationDegrees(f));
        matrixStack.mulPose(Axis.YP.rotationDegrees(f1));
        matrixStack.translate(0.0F, -1.2F, 0.0F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    protected float getBob(T livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }
}
