package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelMalachiteGuard - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class MalachiteGuardModel extends EntityModel<MalachiteGuardEntity> {
    public RendererModel legR;
    public RendererModel legL;
    public RendererModel hips;
    public RendererModel waist;
    public RendererModel shoulderR;
    public RendererModel torso;
    public RendererModel shoulderL;
    public RendererModel head;
    public RendererModel footR;
    public RendererModel footL;
    public RendererModel upperArmR;
    public RendererModel lowerArmR;
    public RendererModel neck;
    public RendererModel upperArmL;
    public RendererModel lowerArmL;
    public RendererModel helmet;
    public RendererModel bladeR;
    public RendererModel bladeL;

    public MalachiteGuardModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        //head
        this.head = new RendererModel(this, 17, 9);
        this.head.setRotationPoint(0.0F, -20.0F, 0.0F);
        this.head.addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7, 0.0F);
        //helmet
        this.helmet = new RendererModel(this, 20, 23);
        this.helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmet.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        //right helmet blade
        this.bladeR = new RendererModel(this, 8, 35);
        this.bladeR.setRotationPoint(-4.1F, -6.0F, 0.0F);
        this.bladeR.addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, 0.0F);
        this.setRotateAngle(bladeR, -1.0471975511965976F, 0.0F, 0.0F);
        //left helmet blade
        this.bladeL = new RendererModel(this, 0, 35);
        this.bladeL.setRotationPoint(4.1F, -6.0F, 0.0F);
        this.bladeL.addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, 0.0F);
        this.setRotateAngle(bladeL, -1.0471975511965976F, 0.0F, 0.0F);
        //neck
        this.neck = new RendererModel(this, 65, 12);
        this.neck.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.neck.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        //torso
        this.torso = new RendererModel(this, 88, 0);
        this.torso.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.torso.addBox(-6.0F, 0.0F, -2.5F, 12, 6, 5, 0.0F);
        //right shoulder
        this.shoulderR = new RendererModel(this, 70, 0);
        this.shoulderR.setRotationPoint(-6.0F, -14.5F, 0.0F);
        this.shoulderR.addBox(-5.0F, -2.0F, -2.0F, 5, 2, 4, 0.0F);
        //right upper arm
        this.upperArmR = new RendererModel(this, 99, 11);
        this.upperArmR.setRotationPoint(-2.5F, 0.0F, 0.0F);
        this.upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        //right lower arm
        this.lowerArmR = new RendererModel(this, 107, 11);
        this.lowerArmR.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.lowerArmR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        //left shoulder
        this.shoulderL = new RendererModel(this, 66, 6);
        this.shoulderL.setRotationPoint(6.0F, -14.5F, 0.0F);
        this.shoulderL.addBox(0.0F, -2.0F, -2.0F, 5, 2, 4, 0.0F);
        //left upper arm
        this.upperArmL = new RendererModel(this, 0, 13);
        this.upperArmL.setRotationPoint(2.5F, 0.0F, 0.0F);
        this.upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        //left lower arm
        this.lowerArmL = new RendererModel(this, 4, 19);
        this.lowerArmL.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.lowerArmL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        //waist
        this.waist = new RendererModel(this, 58, 0);
        this.waist.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.waist.addBox(-2.0F, 0.0F, -1.0F, 4, 5, 2, 0.0F);
        //hips
        this.hips = new RendererModel(this, 24, 0);
        this.hips.setRotationPoint(0.0F, -5.0F, -2.5F);
        this.hips.addBox(-6.0F, 0.0F, 0.0F, 12, 4, 5, 0.0F);
        //left leg
        this.legR = new RendererModel(this, 0, 0);
        this.legR.setRotationPoint(-4.5F, -1.0F, 0.0F);
        this.legR.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        //right foot
        this.footR = new RendererModel(this, 45, 9);
        this.footR.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.footR.addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, 0.0F);
        //left leg
        this.legL = new RendererModel(this, 12, 0);
        this.legL.setRotationPoint(4.5F, -1.0F, 0.0F);
        this.legL.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        //left foot
        this.footL = new RendererModel(this, 79, 11);
        this.footL.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.footL.addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, 0.0F);

        //kiddiewinkles
        this.head.addChild(this.helmet);
        this.upperArmL.addChild(this.lowerArmL);
        this.helmet.addChild(this.bladeL);
        this.shoulderR.addChild(this.upperArmR);
        this.shoulderL.addChild(this.upperArmL);
        this.torso.addChild(this.neck);
        this.legR.addChild(this.footR);
        this.helmet.addChild(this.bladeR);
        this.legL.addChild(this.footL);
        this.upperArmR.addChild(this.lowerArmR);
    }

    @Override
    public void render(MalachiteGuardEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.waist.render(f5);
        this.shoulderR.render(f5);
        this.hips.render(f5);
        this.legR.render(f5);
        this.legL.render(f5);
        this.torso.render(f5);
        this.shoulderL.render(f5);
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
    public void setRotationAngles(MalachiteGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.upperArmR.rotateAngleZ = 0.0F;
        this.upperArmL.rotateAngleZ = 0.0F;
        this.upperArmR.rotateAngleX = 0.0F;
        this.upperArmL.rotateAngleX = 0.0F;
        this.upperArmR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.upperArmL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.upperArmR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.upperArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
    }
}
