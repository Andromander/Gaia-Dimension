package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.ShallowArenthisEntity;
import androsa.gaiadimension.model.ShallowArenthisModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShallowArenthisRenderer extends MobRenderer<ShallowArenthisEntity, ShallowArenthisModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "shallowarenthis.png");

    public ShallowArenthisRenderer(EntityRendererManager manager, ShallowArenthisModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected void applyRotations(ShallowArenthisEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = entityLiving.prevArenthisPitch + (entityLiving.arenthisPitch - entityLiving.prevArenthisPitch) * partialTicks;
        float f1 = entityLiving.prevArenthisYaw + (entityLiving.arenthisYaw - entityLiving.prevArenthisYaw) * partialTicks;
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(ShallowArenthisEntity livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }

    @Override
    protected ResourceLocation getEntityTexture(ShallowArenthisEntity entity) {
        return textureLoc;
    }
}
