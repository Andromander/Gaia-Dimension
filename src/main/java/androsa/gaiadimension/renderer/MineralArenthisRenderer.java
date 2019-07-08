package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.MineralArenthisEntity;
import androsa.gaiadimension.model.MineralArenthisModel;
import androsa.gaiadimension.renderer.layer.MineralArenthisPartsLayer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MineralArenthisRenderer extends MobRenderer<MineralArenthisEntity, MineralArenthisModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "mineralarenthis.png");

    public MineralArenthisRenderer(EntityRendererManager manager, MineralArenthisModel model, float shadowSize) {
        super(manager, model, shadowSize);
        this.addLayer(new MineralArenthisPartsLayer(this));
    }

    @Override
    protected void applyRotations(MineralArenthisEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
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
    @Override
    protected float handleRotationFloat(MineralArenthisEntity livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }

    @Override
    protected ResourceLocation getEntityTexture(MineralArenthisEntity entity) {
        return textureLoc;
    }
}
