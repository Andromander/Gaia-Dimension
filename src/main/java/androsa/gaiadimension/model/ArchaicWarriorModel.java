package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ArchaicWarriorEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelArchaicWarrior - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ArchaicWarriorModel<T extends ArchaicWarriorEntity> extends SegmentedModel<T> {
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

    public ArchaicWarriorModel() {
        this.texWidth = 70;
        this.texHeight = 50;
        this.bipedRightArmwear = new ModelRenderer(this, 8, 30);
        this.bipedRightArmwear.mirror = true;
        this.bipedRightArmwear.setPos(-5.0F, 2.0F, 0.0F);
        this.bipedRightArmwear.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.5F);
        this.setRotateAngle(bipedRightArmwear, 0.0F, 0.0F, 0.10000736613927509F);
        this.shoulderPlateR = new ModelRenderer(this, 52, 28);
        this.shoulderPlateR.setPos(-4.5F, -1.5F, 0.0F);
        this.shoulderPlateR.addBox(-4.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 0);
        this.bipedLeftLeg.setPos(2.0F, 12.0F, 0.1F);
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.bipedLeftLegwear = new ModelRenderer(this, 24, 32);
        this.bipedLeftLegwear.setPos(2.0F, 12.0F, 0.1F);
        this.bipedLeftLegwear.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.5F);
        this.bipedRightArm = new ModelRenderer(this, 0, 0);
        this.bipedRightArm.setPos(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.bipedLeftArmwear = new ModelRenderer(this, 8, 0);
        this.bipedLeftArmwear.mirror = true;
        this.bipedLeftArmwear.setPos(5.0F, 2.0F, 0.0F);
        this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.5F);
        this.setRotateAngle(bipedLeftArmwear, 0.0F, 0.0F, -0.10000736613927509F);
        this.bipedLeftArm = new ModelRenderer(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setPos(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.bipedHead = new ModelRenderer(this, 24, 16);
        this.bipedHead.setPos(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.shoulderPlateL = new ModelRenderer(this, 52, 14);
        this.shoulderPlateL.setPos(4.5F, -1.5F, 0.0F);
        this.shoulderPlateL.addBox(0.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.bipedRightLegwear = new ModelRenderer(this, 0, 30);
        this.bipedRightLegwear.setPos(-2.0F, 12.0F, 0.1F);
        this.bipedRightLegwear.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.5F);
        this.bipedRightLeg = new ModelRenderer(this, 16, 30);
        this.bipedRightLeg.setPos(-2.0F, 12.0F, 0.1F);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 24, 0);
        this.bipedHeadwear.setPos(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.bipedBody = new ModelRenderer(this, 0, 14);
        this.bipedBody.setPos(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                this.bipedRightArmwear,
                this.shoulderPlateR,
                this.bipedLeftLeg,
                this.bipedLeftLegwear,
                this.bipedRightArm,
                this.bipedLeftArmwear,
                this.bipedLeftArm,
                this.bipedHead,
                this.shoulderPlateL,
                this.bipedRightLegwear,
                this.bipedRightLeg,
                this.bipedHeadwear,
                this.bipedBody
        );
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.bipedHead.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.bipedHead.xRot = headPitch / (180F / (float) Math.PI);

        this.bipedHeadwear.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.bipedHeadwear.xRot = headPitch / (180F / (float) Math.PI);

        float f = MathHelper.sin(this.attackTime * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
        this.bipedRightArm.zRot = 0.0F;
        this.bipedLeftArm.zRot = 0.0F;
        this.bipedRightArm.yRot = -(0.1F - f * 0.6F);
        this.bipedLeftArm.yRot = 0.1F - f * 0.6F;
        this.bipedRightArm.xRot = 0.0F;
        this.bipedLeftArm.xRot = 0.0F;
        this.bipedRightArm.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArm.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedRightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightArmwear.zRot = 0.0F;
        this.bipedLeftArmwear.zRot = 0.0F;
        this.bipedRightArmwear.yRot = -(0.1F - f * 0.6F);
        this.bipedLeftArmwear.yRot = 0.1F - f * 0.6F;
        this.bipedRightArmwear.xRot = 0.0F;
        this.bipedLeftArmwear.xRot = 0.0F;
        this.bipedRightArmwear.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArmwear.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArmwear.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArmwear.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArmwear.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArmwear.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArmwear.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedRightArmwear.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightLegwear.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedLeftLegwear.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
    }
}
