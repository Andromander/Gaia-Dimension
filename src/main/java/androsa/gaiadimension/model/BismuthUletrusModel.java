package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.BismuthUletrusEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelBismuthUletrus - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class BismuthUletrusModel extends EntityModel<BismuthUletrusEntity> {
    public RendererModel body;
    public RendererModel tail1;
    public RendererModel upperArmR;
    public RendererModel upperArmL;
    public RendererModel upperLegR;
    public RendererModel upperLegL;
    public RendererModel neck;
    public RendererModel sail;
    public RendererModel head;
    public RendererModel upperJaw;
    public RendererModel lowerJaw;
    public RendererModel headPart1;
    public RendererModel headPart2;
    public RendererModel horn;
    public RendererModel tail2;
    public RendererModel tail3;
    public RendererModel tail4;
    public RendererModel lowerArmR;
    public RendererModel pawR;
    public RendererModel clawsR;
    public RendererModel lowerArmL;
    public RendererModel pawL;
    public RendererModel clawsL;
    public RendererModel lowerLegR;
    public RendererModel backPawR;
    public RendererModel backClawL;
    public RendererModel lowerLegL;
    public RendererModel backPawL;
    public RendererModel backClawL_1;

    public BismuthUletrusModel() {
        this.textureWidth = 160;
        this.textureHeight = 160;
        this.tail1 = new RendererModel(this, 67, 0);
        this.tail1.setRotationPoint(0.0F, -1.5F, 16.0F);
        this.tail1.addBox(-5.0F, 0.0F, 0.0F, 10, 8, 15, 0.0F);
        this.setRotateAngle(tail1, -0.7285004297824331F, -0.014660765716752367F, 0.0F);
        this.upperLegR = new RendererModel(this, 104, 21);
        this.upperLegR.setRotationPoint(-8.0F, 1.5F, 15.0F);
        this.upperLegR.addBox(-3.0F, -2.0F, -13.0F, 6, 9, 13, 0.0F);
        this.setRotateAngle(upperLegR, 1.0471975511965976F, 0.0F, 0.0F);
        this.upperArmR = new RendererModel(this, 0, 0);
        this.upperArmR.setRotationPoint(-8.0F, 3.0F, -10.0F);
        this.upperArmR.addBox(-5.0F, 0.0F, -3.0F, 7, 12, 8, 0.0F);
        this.setRotateAngle(upperArmR, 0.33161255787892263F, 0.0F, 0.0F);
        this.upperLegL = new RendererModel(this, 85, 43);
        this.upperLegL.setRotationPoint(8.0F, 1.5F, 15.0F);
        this.upperLegL.addBox(-3.0F, -2.0F, -13.0F, 6, 9, 13, 0.0F);
        this.setRotateAngle(upperLegL, 1.0471975511965976F, 0.0F, 0.0F);
        this.lowerArmL = new RendererModel(this, 20, 98);
        this.lowerArmL.setRotationPoint(-1.9F, 9.2F, 1.9F);
        this.lowerArmL.addBox(0.0F, 0.0F, -2.0F, 7, 12, 6, 0.0F);
        this.setRotateAngle(lowerArmL, -0.7853981633974483F, 0.0F, 0.0F);
        this.backClawL_1 = new RendererModel(this, 89, 23);
        this.backClawL_1.setRotationPoint(0.0F, -7.6F, -2.7F);
        this.backClawL_1.addBox(-3.0F, -3.0F, 0.0F, 6, 3, 3, 0.0F);
        this.tail2 = new RendererModel(this, 89, 93);
        this.tail2.setRotationPoint(0.0F, 1.1F, 13.0F);
        this.tail2.addBox(-4.0F, 0.0F, 0.0F, 8, 6, 13, 0.0F);
        this.setRotateAngle(tail2, 0.31869712141416456F, -0.014660765716752367F, 0.0F);
        this.pawL = new RendererModel(this, 48, 101);
        this.pawL.setRotationPoint(3.5F, 10.9F, 5.5F);
        this.pawL.addBox(-4.5F, 0.0F, -3.0F, 9, 9, 4, 0.0F);
        this.setRotateAngle(pawL, -1.1344640137963142F, 0.0F, 0.0F);
        this.lowerJaw = new RendererModel(this, 125, 43);
        this.lowerJaw.setRotationPoint(0.0F, 2.0F, -11.0F);
        this.lowerJaw.addBox(-3.5F, 0.0F, -10.0F, 7, 4, 10, 0.0F);
        this.lowerLegR = new RendererModel(this, 62, 102);
        this.lowerLegR.setRotationPoint(0.1F, 2.1F, -12.7F);
        this.lowerLegR.addBox(-3.0F, -3.0F, -10.0F, 6, 7, 13, 0.0F);
        this.setRotateAngle(lowerLegR, 0.8726646259971648F, 0.0F, 0.0F);
        this.upperJaw = new RendererModel(this, 0, 66);
        this.upperJaw.setRotationPoint(0.0F, -3.0F, -8.5F);
        this.upperJaw.addBox(-4.5F, 0.0F, -14.0F, 9, 5, 14, 0.0F);
        this.head = new RendererModel(this, 40, 53);
        this.head.setRotationPoint(0.0F, 4.5F, -6.0F);
        this.head.addBox(-5.5F, -6.0F, -12.0F, 11, 12, 12, 0.0F);
        this.setRotateAngle(head, 0.4363323129985824F, 0.0F, 0.0F);
        this.headPart1 = new RendererModel(this, 28, 77);
        this.headPart1.setRotationPoint(4.0F, -4.0F, -13.0F);
        this.headPart1.addBox(-2.5F, -2.0F, 0.0F, 5, 3, 18, 0.0F);
        this.setRotateAngle(headPart1, 0.3665191429188092F, 0.17453292519943295F, 0.6981317007977318F);
        this.backClawL = new RendererModel(this, 140, 0);
        this.backClawL.setRotationPoint(0.0F, -7.6F, -2.7F);
        this.backClawL.addBox(-3.0F, -3.0F, 0.0F, 6, 3, 3, 0.0F);
        this.backPawR = new RendererModel(this, 131, 106);
        this.backPawR.setRotationPoint(0.0F, 2.5F, -9.5F);
        this.backPawR.addBox(-3.5F, -8.0F, -3.0F, 7, 10, 4, 0.0F);
        this.setRotateAngle(backPawR, -0.3490658503988659F, 0.0F, 0.0F);
        this.backPawL = new RendererModel(this, 0, 113);
        this.backPawL.setRotationPoint(0.0F, 2.5F, -9.5F);
        this.backPawL.addBox(-3.5F, -8.0F, -3.0F, 7, 10, 4, 0.0F);
        this.setRotateAngle(backPawL, -0.3490658503988659F, 0.0F, 0.0F);
        this.lowerLegL = new RendererModel(this, 87, 112);
        this.lowerLegL.setRotationPoint(0.1F, 2.1F, -12.7F);
        this.lowerLegL.addBox(-3.0F, -3.0F, -10.0F, 6, 7, 13, 0.0F);
        this.setRotateAngle(lowerLegL, 0.8726646259971648F, 0.0F, 0.0F);
        this.body = new RendererModel(this, 0, 0);
        this.body.setRotationPoint(0.0F, -2.0F, -13.0F);
        this.body.addBox(-9.0F, 0.0F, 0.0F, 18, 14, 31, 0.0F);
        this.upperArmL = new RendererModel(this, 118, 0);
        this.upperArmL.setRotationPoint(8.0F, 3.0F, -10.0F);
        this.upperArmL.addBox(-2.0F, 0.0F, -3.0F, 7, 12, 8, 0.0F);
        this.setRotateAngle(upperArmL, 0.33161255787892263F, 0.0F, 0.0F);
        this.horn = new RendererModel(this, 146, 18);
        this.horn.setRotationPoint(0.0F, -5.0F, -11.5F);
        this.horn.addBox(-1.5F, -12.0F, -1.5F, 3, 13, 3, 0.0F);
        this.setRotateAngle(horn, 0.7853981633974483F, 0.0F, 0.0F);
        this.tail4 = new RendererModel(this, 0, 20);
        this.tail4.setRotationPoint(0.0F, 1.0F, 9.0F);
        this.tail4.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 9, 0.0F);
        this.setRotateAngle(tail4, 0.22759093446006054F, -0.014660765716752367F, 0.0F);
        this.pawR = new RendererModel(this, 118, 93);
        this.pawR.setRotationPoint(-3.5F, 10.9F, 5.5F);
        this.pawR.addBox(-4.5F, 0.0F, -3.0F, 9, 9, 4, 0.0F);
        this.setRotateAngle(pawR, -1.1344640137963142F, 0.0F, 0.0F);
        this.headPart2 = new RendererModel(this, 56, 80);
        this.headPart2.setRotationPoint(-4.0F, -4.0F, -13.0F);
        this.headPart2.addBox(-2.5F, -2.0F, 0.0F, 5, 3, 18, 0.0F);
        this.setRotateAngle(headPart2, 0.3665191429188092F, -0.17453292519943295F, -0.6981317007977318F);
        this.clawsR = new RendererModel(this, 102, 0);
        this.clawsR.setRotationPoint(-1.0F, 9.0F, 1.7F);
        this.clawsR.addBox(-3.0F, 0.0F, -3.0F, 8, 3, 2, 0.0F);
        this.lowerArmR = new RendererModel(this, 0, 85);
        this.lowerArmR.setRotationPoint(1.9F, 9.2F, 1.9F);
        this.lowerArmR.addBox(-7.0F, 0.0F, -2.0F, 7, 12, 6, 0.0F);
        this.setRotateAngle(lowerArmR, -0.7853981633974483F, 0.0F, 0.0F);
        this.sail = new RendererModel(this, 91, 43);
        this.sail.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sail.addBox(-0.5F, -18.0F, 0.0F, 1, 18, 32, 0.0F);
        this.setRotateAngle(sail, -0.17453292519943295F, 0.0F, 0.0F);
        this.tail3 = new RendererModel(this, 125, 57);
        this.tail3.setRotationPoint(0.0F, 1.0F, 11.0F);
        this.tail3.addBox(-3.0F, 0.0F, 0.0F, 6, 4, 11, 0.0F);
        this.setRotateAngle(tail3, 0.27314402793711257F, -0.014660765716752367F, 0.0F);
        this.neck = new RendererModel(this, 0, 45);
        this.neck.setRotationPoint(0.0F, 1.5F, 5.5F);
        this.neck.addBox(-5.0F, 0.0F, -10.0F, 10, 11, 10, 0.0F);
        this.setRotateAngle(neck, -0.4363323129985824F, 0.0F, 0.0F);
        this.clawsL = new RendererModel(this, 67, 23);
        this.clawsL.setRotationPoint(-1.0F, 9.0F, 1.7F);
        this.clawsL.addBox(-3.0F, 0.0F, -3.0F, 8, 3, 2, 0.0F);
        this.upperArmL.addChild(this.lowerArmL);
        this.backPawL.addChild(this.backClawL_1);
        this.tail1.addChild(this.tail2);
        this.lowerArmL.addChild(this.pawL);
        this.head.addChild(this.lowerJaw);
        this.upperLegR.addChild(this.lowerLegR);
        this.head.addChild(this.upperJaw);
        this.neck.addChild(this.head);
        this.head.addChild(this.headPart1);
        this.backPawR.addChild(this.backClawL);
        this.lowerLegR.addChild(this.backPawR);
        this.lowerLegL.addChild(this.backPawL);
        this.upperLegL.addChild(this.lowerLegL);
        this.head.addChild(this.horn);
        this.tail3.addChild(this.tail4);
        this.lowerArmR.addChild(this.pawR);
        this.head.addChild(this.headPart2);
        this.pawR.addChild(this.clawsR);
        this.upperArmR.addChild(this.lowerArmR);
        this.body.addChild(this.sail);
        this.tail2.addChild(this.tail3);
        this.body.addChild(this.neck);
        this.pawL.addChild(this.clawsL);
    }

    @Override
    public void render(BismuthUletrusEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tail1.render(f5);
        this.upperLegR.render(f5);
        this.upperArmR.render(f5);
        this.upperLegL.render(f5);
        this.body.render(f5);
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
    public void setRotationAngles(BismuthUletrusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI) + 0.4363323129985824F;

        if (entity.getResting()) {
            this.setRotateAngle(upperArmL, 1.117010721276371F, 0.4363323129985824F, 0.0F);
            this.setRotateAngle(upperArmR, 1.117010721276371F, -0.4363323129985824F, 0.0F);
            this.setRotateAngle(lowerArmL, -1.7453292519943295F, 0.0F, 0.0F);
            this.setRotateAngle(lowerArmR, -1.7453292519943295F, 0.0F, 0.0F);
            this.setRotateAngle(pawL, -0.9599310885968813F, 0.0F, 0.0F);
            this.setRotateAngle(pawR, -0.9599310885968813F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegL, 0.2792526803190927F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegR, 0.2792526803190927F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegL, 1.6755160819145563F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegR, 1.6755160819145563F, 0.0F, 0.0F);
            this.setRotateAngle(backPawL, -0.3665191429188092F, 0.0F, 0.0F);
            this.setRotateAngle(backPawR, -0.3665191429188092F, 0.0F, 0.0F);
            this.setRotateAngle(tail1, -0.6044075199656364F, -0.31869712141416456F, 0.20943951023931953F);
            this.setRotateAngle(tail2, 0.13334315485236678F, -0.31869712141416456F, -0.14817845349431857F);
            this.setRotateAngle(tail3, 0.27314402793711257F, -0.6631701824345141F, -0.29646144764240534F);
            this.setRotateAngle(tail4, -0.222346085731804F, -0.6261125014792135F, 0.0F);

            this.body.offsetY = 0.4F;
            this.upperArmL.offsetY = 0.4F;
            this.upperArmR.offsetY = 0.4F;
            this.upperLegL.offsetY = 0.4F;
            this.upperLegR.offsetY = 0.4F;
            this.tail1.offsetY = 0.4F;
        } else {
            this.setRotateAngle(upperArmL, 0.33161255787892263F, 0.0F, 0.0F);
            this.setRotateAngle(upperArmR, 0.33161255787892263F, 0.0F, 0.0F);
            this.setRotateAngle(lowerArmL, -0.7853981633974483F, 0.0F, 0.0F);
            this.setRotateAngle(lowerArmR, -0.7853981633974483F, 0.0F, 0.0F);
            this.setRotateAngle(pawL, -1.1344640137963142F, 0.0F, 0.0F);
            this.setRotateAngle(pawR, -1.1344640137963142F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegL, 1.0471975511965976F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegR, 1.0471975511965976F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegL, 0.8726646259971648F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegR, 0.8726646259971648F, 0.0F, 0.0F);
            this.setRotateAngle(backPawL, -0.3490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(backPawR, -0.3490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(tail1, -0.7285004297824331F, -0.014660765716752367F, 0.0F);
            this.setRotateAngle(tail2, 0.31869712141416456F, -0.014660765716752367F, 0.0F);
            this.setRotateAngle(tail3, 0.27314402793711257F, -0.014660765716752367F, 0.0F);
            this.setRotateAngle(tail4, 0.22759093446006054F, -0.014660765716752367F, 0.0F);

            this.body.offsetY = 0.0F;
            this.upperArmL.offsetY = 0.0F;
            this.upperArmR.offsetY = 0.0F;
            this.upperLegL.offsetY = 0.0F;
            this.upperLegR.offsetY = 0.0F;
            this.tail1.offsetY = 0.0F;

            this.upperArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.33161255787892263F;
            this.upperArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.33161255787892263F;

            this.upperLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 1.0471975511965976F;
            this.upperLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 1.0471975511965976F;

            this.tail1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + -0.014660765716752367F;
            this.tail1.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.014660765716752367F;
            this.tail2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + -0.014660765716752367F;
            this.tail2.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.014660765716752367F;
            this.tail3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + -0.014660765716752367F;
            this.tail3.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.014660765716752367F;
            this.tail4.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + -0.014660765716752367F;
            this.tail4.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.014660765716752367F;
        }

    }
}
