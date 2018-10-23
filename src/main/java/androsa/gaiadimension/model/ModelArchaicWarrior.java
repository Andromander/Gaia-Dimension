package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * ModelArchaicWarrior - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelArchaicWarrior extends ModelBase {
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedHead;
    public ModelRenderer shoulderPlateL;
    public ModelRenderer shoulderPlateR;
    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedLeftLegwear;

    public ModelArchaicWarrior() {
        this.textureWidth = 70;
        this.textureHeight = 50;
        this.bipedRightArmwear = new ModelRenderer(this, 8, 30);
        this.bipedRightArmwear.mirror = true;
        this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArmwear.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.5F);
        this.setRotateAngle(bipedRightArmwear, 0.0F, 0.0F, 0.10000736613927509F);
        this.shoulderPlateR = new ModelRenderer(this, 52, 28);
        this.shoulderPlateR.setRotationPoint(-4.5F, -1.5F, 0.0F);
        this.shoulderPlateR.addBox(-4.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 0);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.1F);
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.bipedLeftLegwear = new ModelRenderer(this, 24, 32);
        this.bipedLeftLegwear.setRotationPoint(2.0F, 12.0F, 0.1F);
        this.bipedLeftLegwear.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.5F);
        this.bipedRightArm = new ModelRenderer(this, 0, 0);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.bipedLeftArmwear = new ModelRenderer(this, 8, 0);
        this.bipedLeftArmwear.mirror = true;
        this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.5F);
        this.setRotateAngle(bipedLeftArmwear, 0.0F, 0.0F, -0.10000736613927509F);
        this.bipedLeftArm = new ModelRenderer(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.bipedHead = new ModelRenderer(this, 24, 16);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.shoulderPlateL = new ModelRenderer(this, 52, 14);
        this.shoulderPlateL.setRotationPoint(4.5F, -1.5F, 0.0F);
        this.shoulderPlateL.addBox(0.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.bipedRightLegwear = new ModelRenderer(this, 0, 30);
        this.bipedRightLegwear.setRotationPoint(-2.0F, 12.0F, 0.1F);
        this.bipedRightLegwear.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.5F);
        this.bipedRightLeg = new ModelRenderer(this, 16, 30);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.1F);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 24, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.bipedBody = new ModelRenderer(this, 0, 14);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedRightArmwear.render(f5);
        this.shoulderPlateR.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedLeftLegwear.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArmwear.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedHead.render(f5);
        this.shoulderPlateL.render(f5);
        this.bipedRightLegwear.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedHeadwear.render(f5);
        this.bipedBody.render(f5);
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
        this.bipedHead.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.bipedHead.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.bipedHeadwear.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.bipedHeadwear.rotateAngleX = headPitch / (180F / (float) Math.PI);

        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
        this.bipedRightArm.rotateAngleX = 0.0F;
        this.bipedLeftArm.rotateAngleX = 0.0F;
        this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightArmwear.rotateAngleZ = 0.0F;
        this.bipedLeftArmwear.rotateAngleZ = 0.0F;
        this.bipedRightArmwear.rotateAngleY = -(0.1F - f * 0.6F);
        this.bipedLeftArmwear.rotateAngleY = 0.1F - f * 0.6F;
        this.bipedRightArmwear.rotateAngleX = 0.0F;
        this.bipedLeftArmwear.rotateAngleX = 0.0F;
        this.bipedRightArmwear.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArmwear.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArmwear.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArmwear.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArmwear.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArmwear.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArmwear.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedRightArmwear.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightLegwear.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedLeftLegwear.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
    }
}
