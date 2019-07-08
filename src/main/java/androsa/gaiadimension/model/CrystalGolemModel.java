package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.CrystalGolemEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelCrystalGolem - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CrystalGolemModel extends EntityModel<CrystalGolemEntity> {
    public RendererModel face;
    public RendererModel neck;
    public RendererModel faceL;
    public RendererModel faceR;
    public RendererModel faceT;
    public RendererModel faceB;
    public RendererModel torso;
    public RendererModel stomach;
    public RendererModel shoulderL;
    public RendererModel shoulderR;
    public RendererModel backpart1;
    public RendererModel backpart2;
    public RendererModel backpart3;
    public RendererModel backpart4;
    public RendererModel upperArmL;
    public RendererModel lowerArmL;
    public RendererModel handL;
    public RendererModel thumbL;
    public RendererModel upperArmR;
    public RendererModel lowerArmR;
    public RendererModel handR;
    public RendererModel thumbR;
    public RendererModel legL;
    public RendererModel footL;
    public RendererModel legR;
    public RendererModel footR;

    public CrystalGolemModel() {
        this.textureWidth = 138;
        this.textureHeight = 64;
        this.face = new RendererModel(this, 0, 0);
        this.face.setRotationPoint(0.0F, -9.0F, -1.0F);
        this.face.addBox(-3.0F, -7.0F, -5.0F, 6, 8, 5, 0.0F);
        this.lowerArmL = new RendererModel(this, 114, 19);
        this.lowerArmL.setRotationPoint(1.0F, 9.6F, -0.1F);
        this.lowerArmL.addBox(-2.5F, 0.0F, -2.5F, 5, 11, 5, 0.0F);
        this.setRotateAngle(lowerArmL, 0.0F, 0.7853981633974483F, 0.17453292519943295F);
        this.handR = new RendererModel(this, 83, 28);
        this.handR.setRotationPoint(-2.0F, 11.0F, 0.1F);
        this.handR.addBox(0.0F, 0.0F, -2.5F, 2, 4, 5, 0.0F);
        this.setRotateAngle(handR, 0.0F, 0.0F, -0.3490658503988659F);
        this.footL = new RendererModel(this, 0, 30);
        this.footL.setRotationPoint(0.0F, 14.0F, -1.0F);
        this.footL.addBox(-2.0F, 0.0F, -4.0F, 4, 3, 4, 0.0F);
        this.backpart3 = new RendererModel(this, 0, 13);
        this.backpart3.setRotationPoint(-2.5F, 5.0F, 2.0F);
        this.backpart3.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 6, 0.0F);
        this.setRotateAngle(backpart3, 0.5235987755982988F, -0.17453292519943295F, 0.0F);
        this.legR = new RendererModel(this, 11, 34);
        this.legR.setRotationPoint(-2.9F, 8.0F, 0.4F);
        this.legR.addBox(-2.5F, 0.0F, -2.6F, 5, 16, 5, 0.0F);
        this.backpart4 = new RendererModel(this, 101, 16);
        this.backpart4.setRotationPoint(2.5F, 5.0F, 2.0F);
        this.backpart4.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 6, 0.0F);
        this.setRotateAngle(backpart4, 0.5235987755982988F, 0.17453292519943295F, 0.0F);
        this.legL = new RendererModel(this, 97, 30);
        this.legL.setRotationPoint(2.9F, 8.0F, 0.4F);
        this.legL.addBox(-2.5F, 0.0F, -2.6F, 5, 16, 5, 0.0F);
        this.thumbL = new RendererModel(this, 49, 0);
        this.thumbL.setRotationPoint(-1.5F, 11.0F, 0.0F);
        this.thumbL.addBox(-1.0F, 0.0F, -2.0F, 2, 3, 2, 0.0F);
        this.torso = new RendererModel(this, 94, 2);
        this.torso.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.torso.addBox(-7.0F, 0.0F, -3.0F, 14, 8, 6, 0.0F);
        this.stomach = new RendererModel(this, 63, 11);
        this.stomach.setRotationPoint(0.0F, -1.5F, 0.0F);
        this.stomach.addBox(-5.5F, 0.0F, -2.1F, 11, 10, 5, 0.0F);
        this.neck = new RendererModel(this, 22, 0);
        this.neck.setRotationPoint(0.0F, -12.5F, -1.0F);
        this.neck.addBox(-2.5F, 0.0F, -4.0F, 5, 5, 4, 0.0F);
        this.setRotateAngle(neck, 0.7285004297824331F, 0.0F, 0.0F);
        this.backpart2 = new RendererModel(this, 85, 16);
        this.backpart2.setRotationPoint(4.0F, 0.0F, 1.2F);
        this.backpart2.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 10, 0.0F);
        this.setRotateAngle(backpart2, 0.6981317007977318F, 0.3490658503988659F, 0.0F);
        this.faceB = new RendererModel(this, 88, 0);
        this.faceB.setRotationPoint(0.0F, -6.6F, -0.2F);
        this.faceB.addBox(-2.5F, 0.0F, 0.0F, 5, 7, 1, 0.0F);
        this.shoulderL = new RendererModel(this, 15, 9);
        this.shoulderL.setRotationPoint(-6.3F, -0.2F, 0.0F);
        this.shoulderL.addBox(-4.0F, 0.0F, -3.5F, 4, 3, 7, 0.0F);
        this.setRotateAngle(shoulderL, 0.0F, 0.0F, -0.20943951023931953F);
        this.backpart1 = new RendererModel(this, 42, 16);
        this.backpart1.setRotationPoint(-4.0F, 0.0F, 1.2F);
        this.backpart1.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 10, 0.0F);
        this.setRotateAngle(backpart1, 0.6981317007977318F, -0.3490658503988659F, 0.0F);
        this.lowerArmR = new RendererModel(this, 42, 28);
        this.lowerArmR.setRotationPoint(-1.0F, 9.6F, -0.1F);
        this.lowerArmR.addBox(-2.5F, 0.0F, -2.5F, 5, 11, 5, 0.0F);
        this.setRotateAngle(lowerArmR, 0.0F, -0.7853981633974483F, -0.17453292519943295F);
        this.faceR = new RendererModel(this, 54, 0);
        this.faceR.setRotationPoint(1.8F, -6.5F, -2.4F);
        this.faceR.addBox(0.0F, 0.0F, -2.5F, 2, 7, 5, 0.0F);
        this.setRotateAngle(faceR, 0.0F, -0.06981317007977318F, 0.0F);
        this.handL = new RendererModel(this, 33, 23);
        this.handL.setRotationPoint(2.0F, 11.0F, 0.1F);
        this.handL.addBox(-2.0F, 0.0F, -2.5F, 2, 4, 5, 0.0F);
        this.setRotateAngle(handL, 0.0F, 0.0F, 0.3490658503988659F);
        this.upperArmR = new RendererModel(this, 63, 26);
        this.upperArmR.setRotationPoint(-6.5F, -7.5F, 0.0F);
        this.upperArmR.addBox(-3.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(upperArmR, 0.0F, 0, 0.17453292519943295F);
        this.footR = new RendererModel(this, 117, 35);
        this.footR.setRotationPoint(0.0F, 14.0F, -1.0F);
        this.footR.addBox(-2.0F, 0.0F, -4.0F, 4, 3, 4, 0.0F);
        this.faceL = new RendererModel(this, 40, 0);
        this.faceL.setRotationPoint(-1.8F, -6.5F, -2.4F);
        this.faceL.addBox(-2.0F, 0.0F, -2.5F, 2, 7, 5, 0.0F);
        this.setRotateAngle(faceL, 0.0F, 0.0698131700797732F, 0.0F);
        this.upperArmL = new RendererModel(this, 13, 19);
        this.upperArmL.setRotationPoint(6.5F, -7.5F, 0.0F);
        this.upperArmL.addBox(-1.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(upperArmL, 0.0F, 0, -0.17453292519943295F);
        this.faceT = new RendererModel(this, 68, 0);
        this.faceT.setRotationPoint(0.0F, -7.8F, -4.7F);
        this.faceT.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(faceT, -0.06981317007977318F, 0.0F, 0.0F);
        this.shoulderR = new RendererModel(this, 30, 12);
        this.shoulderR.setRotationPoint(6.3F, -0.2F, 0.0F);
        this.shoulderR.addBox(0.0F, 0.0F, -3.5F, 4, 3, 7, 0.0F);
        this.setRotateAngle(shoulderR, 0.0F, 0.0F, 0.20943951023931953F);
        this.thumbR = new RendererModel(this, 63, 0);
        this.thumbR.setRotationPoint(1.5F, 11.0F, 0.0F);
        this.thumbR.addBox(-1.0F, 0.0F, -2.0F, 2, 3, 2, 0.0F);
        this.upperArmL.addChild(this.lowerArmL);
        this.lowerArmR.addChild(this.handR);
        this.legL.addChild(this.footL);
        this.torso.addChild(this.backpart3);
        this.torso.addChild(this.backpart4);
        this.lowerArmL.addChild(this.thumbL);
        this.torso.addChild(this.backpart2);
        this.face.addChild(this.faceB);
        this.torso.addChild(this.shoulderL);
        this.torso.addChild(this.backpart1);
        this.upperArmR.addChild(this.lowerArmR);
        this.face.addChild(this.faceR);
        this.lowerArmL.addChild(this.handL);
        this.legR.addChild(this.footR);
        this.face.addChild(this.faceL);
        this.face.addChild(this.faceT);
        this.torso.addChild(this.shoulderR);
        this.lowerArmR.addChild(this.thumbR);
    }

    @Override
    public void render(CrystalGolemEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.face.render(f5);
        this.legR.render(f5);
        this.legL.render(f5);
        this.torso.render(f5);
        this.stomach.render(f5);
        this.neck.render(f5);
        this.upperArmR.render(f5);
        this.upperArmL.render(f5);
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
    public void setRotationAngles(CrystalGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.face.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.face.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.upperArmR.rotateAngleZ = 0.0F;
        this.upperArmL.rotateAngleZ = 0.0F;
        this.upperArmR.rotateAngleX = 0.0F;
        this.upperArmL.rotateAngleX = 0.0F;
        this.upperArmR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.2F;
        this.upperArmL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.2F;
        this.upperArmR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.upperArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
    }
}
