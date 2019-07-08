package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MutantGrowthExtractorEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelGrowthExtractor - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class GrowthExtractorModel extends EntityModel<MutantGrowthExtractorEntity> {
    public RendererModel head;
    public RendererModel neck;
    public RendererModel torso;
    public RendererModel stomach;
    public RendererModel legR;
    public RendererModel legL;
    public RendererModel uparmUpperR;
    public RendererModel uparmUpperR_1;
    public RendererModel uparmUpperR_2;
    public RendererModel uparmUpperR_3;
    public RendererModel nozzleUp;
    public RendererModel eyeL;
    public RendererModel eyeR;
    public RendererModel nozzleLow;
    public RendererModel sapper;
    public RendererModel footR;
    public RendererModel footL;
    public RendererModel lowarmUpperR;
    public RendererModel lowarmUpperR_1;
    public RendererModel lowarmUpperR_2;
    public RendererModel lowarmUpperR_3;

    public GrowthExtractorModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.lowarmUpperR_1 = new RendererModel(this, 28, 38);
        this.lowarmUpperR_1.setRotationPoint(6.5F, 0.0F, 1.5F);
        this.lowarmUpperR_1.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(lowarmUpperR_1, -1.5707963267948966F, 0.0F, 0.0F);
        this.torso = new RendererModel(this, 36, 0);
        this.torso.setRotationPoint(0.0F, 13.0F, 5.6F);
        this.torso.addBox(-3.5F, -9.0F, -5.0F, 7, 9, 5, 0.0F);
        this.setRotateAngle(torso, 0.2617993877991494F, 0.0F, 0.0F);
        this.eyeR = new RendererModel(this, 10, 29);
        this.eyeR.setRotationPoint(-1.2F, -2.1F, -4.0F);
        this.eyeR.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        this.nozzleLow = new RendererModel(this, 20, 29);
        this.nozzleLow.setRotationPoint(0.0F, -0.5F, -3.2F);
        this.nozzleLow.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(nozzleLow, -0.6108652381980153F, 0.0F, 0.0F);
        this.legR = new RendererModel(this, 32, 14);
        this.legR.setRotationPoint(-3.4F, 18.0F, 2.0F);
        this.legR.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(legR, 0.2617993877991494F, 0.4363323129985824F, 0.0F);
        this.uparmUpperR = new RendererModel(this, 0, 23);
        this.uparmUpperR.setRotationPoint(-3.0F, 7.5F, 2.1F);
        this.uparmUpperR.addBox(-5.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(uparmUpperR, 0.0F, 0.0F, -0.08726646259971647F);
        this.uparmUpperR_2 = new RendererModel(this, 29, 26);
        this.uparmUpperR_2.setRotationPoint(-2.5F, 12.5F, 3.2F);
        this.uparmUpperR_2.addBox(-5.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(uparmUpperR_2, 0.0F, 0.0F, -0.2617993877991494F);
        this.neck = new RendererModel(this, 20, 0);
        this.neck.setRotationPoint(0.0F, 3.0F, -1.1F);
        this.neck.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(neck, -0.8196066167365371F, 0.0F, 0.0F);
        this.nozzleUp = new RendererModel(this, 26, 10);
        this.nozzleUp.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.nozzleUp.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(nozzleUp, 0.5235987755982988F, 0.0F, 0.0F);
        this.stomach = new RendererModel(this, 0, 10);
        this.stomach.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.stomach.addBox(-5.0F, 0.0F, 0.0F, 10, 7, 6, 0.0F);
        this.legL = new RendererModel(this, 44, 17);
        this.legL.setRotationPoint(3.4F, 18.0F, 2.0F);
        this.legL.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(legL, 0.2617993877991494F, -0.4363323129985824F, 0.0F);
        this.footR = new RendererModel(this, 40, 32);
        this.footR.setRotationPoint(0.0F, -1.3F, -5.0F);
        this.footR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(footR, -0.2617993877991494F, 0.0F, 0.0F);
        this.footL = new RendererModel(this, 0, 35);
        this.footL.setRotationPoint(0.0F, -1.3F, -5.0F);
        this.footL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(footL, -0.2617993877991494F, 0.0F, 0.0F);
        this.uparmUpperR_1 = new RendererModel(this, 16, 23);
        this.uparmUpperR_1.setRotationPoint(3.0F, 7.5F, 2.1F);
        this.uparmUpperR_1.addBox(0.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(uparmUpperR_1, 0.0F, 0.0F, 0.08726646259971647F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.head.addBox(-2.5F, -4.0F, -5.0F, 5, 5, 5, 0.0F);
        this.lowarmUpperR_2 = new RendererModel(this, 40, 42);
        this.lowarmUpperR_2.setRotationPoint(-6.5F, 0.0F, 1.5F);
        this.lowarmUpperR_2.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(lowarmUpperR_2, -1.5707963267948966F, 0.0F, 0.0F);
        this.eyeL = new RendererModel(this, 0, 29);
        this.eyeL.setRotationPoint(1.2F, -2.1F, -4.0F);
        this.eyeL.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        this.lowarmUpperR_3 = new RendererModel(this, 0, 45);
        this.lowarmUpperR_3.setRotationPoint(6.5F, 0.0F, 1.5F);
        this.lowarmUpperR_3.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(lowarmUpperR_3, -1.5707963267948966F, 0.0F, 0.0F);
        this.uparmUpperR_3 = new RendererModel(this, 45, 26);
        this.uparmUpperR_3.setRotationPoint(2.5F, 12.5F, 3.2F);
        this.uparmUpperR_3.addBox(0.0F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(uparmUpperR_3, 0.0F, 0.0F, 0.2617993877991494F);
        this.lowarmUpperR = new RendererModel(this, 16, 38);
        this.lowarmUpperR.setRotationPoint(-6.5F, 0.0F, 1.5F);
        this.lowarmUpperR.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(lowarmUpperR, -1.5707963267948966F, 0.0F, 0.0F);
        this.sapper = new RendererModel(this, 24, 32);
        this.sapper.setRotationPoint(0.0F, 3.6F, 0.0F);
        this.sapper.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.uparmUpperR_1.addChild(this.lowarmUpperR_1);
        this.head.addChild(this.eyeR);
        this.nozzleUp.addChild(this.nozzleLow);
        this.head.addChild(this.nozzleUp);
        this.legR.addChild(this.footR);
        this.legL.addChild(this.footL);
        this.uparmUpperR_2.addChild(this.lowarmUpperR_2);
        this.head.addChild(this.eyeL);
        this.uparmUpperR_3.addChild(this.lowarmUpperR_3);
        this.uparmUpperR.addChild(this.lowarmUpperR);
        this.nozzleLow.addChild(this.sapper);
    }

    @Override
    public void render(MutantGrowthExtractorEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.torso.render(f5);
        this.legR.render(f5);
        this.uparmUpperR.render(f5);
        this.uparmUpperR_2.render(f5);
        this.neck.render(f5);
        this.stomach.render(f5);
        this.legL.render(f5);
        this.uparmUpperR_1.render(f5);
        this.head.render(f5);
        this.uparmUpperR_3.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(MutantGrowthExtractorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.2617993877991494F;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.2617993877991494F;

        this.uparmUpperR.rotateAngleY = MathHelper.sin(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.uparmUpperR_1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.uparmUpperR_2.rotateAngleY = MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.uparmUpperR_3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
