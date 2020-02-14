package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.ShallowArenthisEntity;
import androsa.gaiadimension.model.ShallowArenthisModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShallowArenthisRenderer<T extends ShallowArenthisEntity, M extends ShallowArenthisModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "shallowarenthis.png");

    public ShallowArenthisRenderer(EntityRendererManager manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected void setupTransforms(T entityLiving, MatrixStack matrixStack, float rotationPitch, float rotationYaw, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, entityLiving.prevArenthisPitch, entityLiving.arenthisPitch);
        float f1 = MathHelper.lerp(partialTicks, entityLiving.prevArenthisYaw, entityLiving.arenthisYaw);
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - rotationYaw));
        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(f));
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(f1));
        matrixStack.translate(0.0F, -1.2F, 0.0F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(T livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return textureLoc;
    }
}
