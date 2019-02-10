package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelAgateGolem - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelAgateGolem extends ModelBase {
    public ModelRenderer upperLegL;
    public ModelRenderer lowerLegL;
    public ModelRenderer face;
    public ModelRenderer neck;
    public ModelRenderer facepartLowL;
    public ModelRenderer facepartLowR;
    public ModelRenderer facepartUpL;
    public ModelRenderer facepartUpR;
    public ModelRenderer chinpartL;
    public ModelRenderer chinpartR;
    public ModelRenderer chin;
    public ModelRenderer headtop;
    public ModelRenderer torso;
    public ModelRenderer stomach;
    public ModelRenderer shoulderL;
    public ModelRenderer shoulderR;
    public ModelRenderer upperArmL;
    public ModelRenderer lowerArmL;
    public ModelRenderer lowarmLeftL;
    public ModelRenderer lowarmTopL;
    public ModelRenderer lowarmRightL;
    public ModelRenderer lowarmTopL_1;
    public ModelRenderer fistL;
    public ModelRenderer upperArmR;
    public ModelRenderer lowerArmR;
    public ModelRenderer lowarmLeftR;
    public ModelRenderer lowarmTopR;
    public ModelRenderer lowarmRightR;
    public ModelRenderer lowarmTopR_1;
    public ModelRenderer fistR;
    public ModelRenderer upperLegR;
    public ModelRenderer lowerLegL_1;

    public ModelAgateGolem() {
        this.textureWidth = 138;
        this.textureHeight = 64;
        this.shoulderR = new ModelRenderer(this, 70, 21);
        this.shoulderR.setRotationPoint(-2.5F, 0.0F, 3.0F);
        this.shoulderR.addBox(-9.0F, -3.0F, -3.0F, 9, 6, 6, 0.0F);
        this.setRotateAngle(shoulderR, -0.7155849933176751F, 0.0F, -0.2792526803190927F);
        this.facepartUpR = new ModelRenderer(this, 117, 7);
        this.facepartUpR.setRotationPoint(-2.0F, -5.2F, -4.5F);
        this.facepartUpR.addBox(-3.5F, -7.0F, 0.0F, 6, 7, 3, 0.0F);
        this.setRotateAngle(facepartUpR, -0.2617993877991494F, 0.0F, -0.6981317007977318F);
        this.stomach = new ModelRenderer(this, 95, 13);
        this.stomach.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.stomach.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 5, 0.0F);
        this.upperArmL = new ModelRenderer(this, 121, 17);
        this.upperArmL.setRotationPoint(8.0F, -6.0F, 3.0F);
        this.upperArmL.addBox(-1.0F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.setRotateAngle(upperArmL, 0.0F, 0.0F, -0.3490658503988659F);
        this.lowerLegL = new ModelRenderer(this, 20, 0);
        this.lowerLegL.setRotationPoint(0.0F, 8.2F, 0.0F);
        this.lowerLegL.addBox(-3.0F, 0.0F, -3.0F, 6, 10, 6, 0.0F);
        this.setRotateAngle(lowerLegL, 0.3490658503988659F, 0.0F, 0.0F);
        this.headtop = new ModelRenderer(this, 120, 0);
        this.headtop.setRotationPoint(0.0F, -6.7F, -5.0F);
        this.headtop.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
        this.setRotateAngle(headtop, -0.16388641676226753F, 0.17453292519943295F, 0.7853981633974483F);
        this.facepartLowR = new ModelRenderer(this, 92, 0);
        this.facepartLowR.setRotationPoint(-2.5F, -0.5F, -2.4F);
        this.facepartLowR.addBox(-1.0F, 0.0F, 0.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(facepartLowR, 0.33161255787892263F, 0.0F, 0.8726646259971648F);
        this.chin = new ModelRenderer(this, 15, 0);
        this.chin.setRotationPoint(0.0F, 1.0F, -4.9F);
        this.chin.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.setRotateAngle(chin, 0.0F, 0.0F, 0.7853981633974483F);
        this.lowerArmL = new ModelRenderer(this, 26, 24);
        this.lowerArmL.setRotationPoint(0.6F, 10.5F, 0.2F);
        this.lowerArmL.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.setRotateAngle(lowerArmL, -0.45378560551852565F, 0.0F, 0.3490658503988659F);
        this.upperLegR = new ModelRenderer(this, 0, 43);
        this.upperLegR.setRotationPoint(3.0F, 6.3F, 1.9F);
        this.upperLegR.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
        this.setRotateAngle(upperLegR, -0.3490658503988659F, -0.4363323129985824F, 0.0F);
        this.shoulderL = new ModelRenderer(this, 0, 16);
        this.shoulderL.setRotationPoint(2.5F, 0.0F, 3.0F);
        this.shoulderL.addBox(0.0F, -3.0F, -3.0F, 9, 6, 6, 0.0F);
        this.setRotateAngle(shoulderL, -0.7155849933176751F, 0.0F, 0.2792526803190927F);
        this.facepartLowL = new ModelRenderer(this, 82, 0);
        this.facepartLowL.setRotationPoint(2.5F, -0.5F, -2.4F);
        this.facepartLowL.addBox(-2.0F, 0.0F, 0.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(facepartLowL, 0.33161255787892263F, 0.0F, -0.8726646259971648F);
        this.facepartUpL = new ModelRenderer(this, 102, 0);
        this.facepartUpL.setRotationPoint(2.0F, -5.2F, -4.5F);
        this.facepartUpL.addBox(-2.5F, -7.0F, 0.0F, 6, 7, 3, 0.0F);
        this.setRotateAngle(facepartUpL, -0.2617993877991494F, 0.0F, 0.6981317007977318F);
        this.lowarmTopL_1 = new ModelRenderer(this, 0, 28);
        this.lowarmTopL_1.setRotationPoint(-2.0F, 1.0F, 0.0F);
        this.lowarmTopL_1.addBox(0.0F, 0.0F, 0.0F, 4, 13, 2, 0.0F);
        this.setRotateAngle(lowarmTopL_1, 0.08726646259971647F, 0.0F, 0.0F);
        this.lowarmLeftL = new ModelRenderer(this, 100, 26);
        this.lowarmLeftL.setRotationPoint(2.0F, 1.0F, 0.0F);
        this.lowarmLeftL.addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4, 0.0F);
        this.setRotateAngle(lowarmLeftL, 0.0F, 0.0F, -0.08726646259971647F);
        this.fistR = new ModelRenderer(this, 34, 42);
        this.fistR.setRotationPoint(0.0F, 11.0F, 1.0F);
        this.fistR.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(fistR, 0.7853981633974483F, 1.3089969389957472F, 1.5707963267948966F);
        this.lowarmLeftR = new ModelRenderer(this, 66, 33);
        this.lowarmLeftR.setRotationPoint(2.0F, 1.0F, 0.0F);
        this.lowarmLeftR.addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4, 0.0F);
        this.setRotateAngle(lowarmLeftR, 0.0F, 0.0F, -0.08726646259971647F);
        this.lowarmRightR = new ModelRenderer(this, 90, 39);
        this.lowarmRightR.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.lowarmRightR.addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4, 0.0F);
        this.setRotateAngle(lowarmRightR, 0.0F, 0.0F, 0.08726646259971647F);
        this.fistL = new ModelRenderer(this, 24, 16);
        this.fistL.setRotationPoint(0.0F, 11.0F, 1.0F);
        this.fistL.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(fistL, 0.7853981633974483F, 1.3089969389957472F, 1.5707963267948966F);
        this.lowarmTopR_1 = new ModelRenderer(this, 22, 41);
        this.lowarmTopR_1.setRotationPoint(-2.0F, 1.0F, 0.0F);
        this.lowarmTopR_1.addBox(0.0F, 0.0F, 0.0F, 4, 13, 2, 0.0F);
        this.setRotateAngle(lowarmTopR_1, 0.08726646259971647F, 0.0F, 0.0F);
        this.upperLegL = new ModelRenderer(this, 0, 0);
        this.upperLegL.setRotationPoint(-3.0F, 6.3F, 1.9F);
        this.upperLegL.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
        this.setRotateAngle(upperLegL, -0.3490658503988659F, 0.4363323129985824F, 0.0F);
        this.lowarmRightL = new ModelRenderer(this, 54, 27);
        this.lowarmRightL.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.lowarmRightL.addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4, 0.0F);
        this.setRotateAngle(lowarmRightL, 0.0F, 0.0F, 0.08726646259971647F);
        this.lowarmTopL = new ModelRenderer(this, 42, 27);
        this.lowarmTopL.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.lowarmTopL.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 2, 0.0F);
        this.setRotateAngle(lowarmTopL, -0.08726646259971647F, 0.0F, 0.0F);
        this.lowerArmR = new ModelRenderer(this, 112, 32);
        this.lowerArmR.setRotationPoint(-0.6F, 10.5F, 0.2F);
        this.lowerArmR.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.setRotateAngle(lowerArmR, -0.45378560551852565F, 0.0F, -0.3490658503988659F);
        this.neck = new ModelRenderer(this, 66, 0);
        this.neck.setRotationPoint(0.0F, -12.5F, 0.0F);
        this.neck.addBox(-2.0F, 0.0F, -4.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(neck, 0.7285004297824331F, 0.0F, 0.0F);
        this.chinpartL = new ModelRenderer(this, 80, 8);
        this.chinpartL.setRotationPoint(4.6F, -4.2F, -4.2F);
        this.chinpartL.addBox(-3.0F, 0.0F, 0.0F, 3, 8, 2, 0.0F);
        this.setRotateAngle(chinpartL, 0.0F, 0.0F, 0.22689280275926282F);
        this.chinpartR = new ModelRenderer(this, 90, 8);
        this.chinpartR.setRotationPoint(-1.6F, -5.0F, -4.2F);
        this.chinpartR.addBox(-3.0F, 0.0F, 0.0F, 3, 8, 2, 0.0F);
        this.setRotateAngle(chinpartR, 0.0F, 0.0F, -0.22689280275926282F);
        this.upperArmR = new ModelRenderer(this, 12, 28);
        this.upperArmR.setRotationPoint(-8.0F, -6.0F, 3.0F);
        this.upperArmR.addBox(-2.0F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.setRotateAngle(upperArmR, 0.0F, 0.0F, 0.3490658503988659F);
        this.lowerLegL_1 = new ModelRenderer(this, 40, 44);
        this.lowerLegL_1.setRotationPoint(0.0F, 8.2F, 0.0F);
        this.lowerLegL_1.addBox(-3.0F, 0.0F, -3.0F, 6, 10, 6, 0.0F);
        this.setRotateAngle(lowerLegL_1, 0.3490658503988659F, 0.0F, 0.0F);
        this.lowarmTopR = new ModelRenderer(this, 78, 33);
        this.lowarmTopR.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.lowarmTopR.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 2, 0.0F);
        this.setRotateAngle(lowarmTopR, -0.08726646259971647F, 0.0F, 0.0F);
        this.face = new ModelRenderer(this, 44, 0);
        this.face.setRotationPoint(0.0F, -8.5F, 0.0F);
        this.face.addBox(-3.0F, -7.0F, -5.0F, 6, 8, 5, 0.0F);
        this.torso = new ModelRenderer(this, 38, 13);
        this.torso.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.torso.addBox(-6.5F, 0.0F, 0.0F, 13, 8, 6, 0.0F);
        this.torso.addChild(this.shoulderR);
        this.face.addChild(this.facepartUpR);
        this.upperLegL.addChild(this.lowerLegL);
        this.face.addChild(this.headtop);
        this.face.addChild(this.facepartLowR);
        this.face.addChild(this.chin);
        this.upperArmL.addChild(this.lowerArmL);
        this.torso.addChild(this.shoulderL);
        this.face.addChild(this.facepartLowL);
        this.face.addChild(this.facepartUpL);
        this.lowerArmL.addChild(this.lowarmTopL_1);
        this.lowerArmL.addChild(this.lowarmLeftL);
        this.lowerArmR.addChild(this.fistR);
        this.lowerArmR.addChild(this.lowarmLeftR);
        this.lowerArmR.addChild(this.lowarmRightR);
        this.lowerArmL.addChild(this.fistL);
        this.lowerArmR.addChild(this.lowarmTopR_1);
        this.lowerArmL.addChild(this.lowarmRightL);
        this.lowerArmL.addChild(this.lowarmTopL);
        this.upperArmR.addChild(this.lowerArmR);
        this.face.addChild(this.chinpartL);
        this.face.addChild(this.chinpartR);
        this.upperLegR.addChild(this.lowerLegL_1);
        this.lowerArmR.addChild(this.lowarmTopR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.stomach.render(f5);
        this.upperArmL.render(f5);
        this.upperLegR.render(f5);
        this.upperLegL.render(f5);
        this.neck.render(f5);
        this.upperArmR.render(f5);
        this.face.render(f5);
        this.torso.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity par7Entity) {
        this.face.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.face.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.upperArmR.rotateAngleZ = 0.0F;
        this.upperArmL.rotateAngleZ = 0.0F;
        this.upperArmR.rotateAngleX = 0.0F;
        this.upperArmL.rotateAngleX = 0.0F;
        this.upperArmR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.3490658503988659F;
        this.upperArmL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.3490658503988659F;
        this.upperArmR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.upperArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.upperLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + -0.3490658503988659F;
        this.upperLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + -0.3490658503988659F;
    }
}
