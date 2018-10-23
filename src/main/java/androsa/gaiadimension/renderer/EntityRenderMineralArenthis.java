package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDMineralArenthis;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderMineralArenthis extends RenderLiving<GDMineralArenthis> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "mineralarenthis.png");

    public EntityRenderMineralArenthis(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
        //this.addLayer(new LayerMineralArenthisParts(this)); Comment out until it works
    }

    @Override
    protected void applyRotations(GDMineralArenthis entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = entityLiving.prevArenthisPitch + (entityLiving.arenthisPitch - entityLiving.prevArenthisPitch) * partialTicks;
        float f1 = entityLiving.prevArenthisYaw + (entityLiving.arenthisYaw - entityLiving.prevArenthisYaw) * partialTicks;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(GDMineralArenthis livingBase, float partialTicks) {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }

    @Override
    protected ResourceLocation getEntityTexture(GDMineralArenthis par1Entity) {
        return textureLoc;
    }
}
