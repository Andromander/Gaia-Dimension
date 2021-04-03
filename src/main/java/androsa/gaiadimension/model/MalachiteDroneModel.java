package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MalachiteDroneEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * MalachiteDroneModel - Androsa
 * Created using Tabula 7.0.0
 */
public class MalachiteDroneModel<T extends MalachiteDroneEntity> extends SegmentedModel<T> {
    public ModelRenderer top;
    public ModelRenderer head;
    public ModelRenderer upperArmL;
    public ModelRenderer upperArmR;
    public ModelRenderer upperLegL;
    public ModelRenderer upperLegR;
    public ModelRenderer topCap;
    public ModelRenderer lowerArmL;
    public ModelRenderer clawLT;
    public ModelRenderer clawLB;
    public ModelRenderer lowerArmL_1;
    public ModelRenderer clawRT;
    public ModelRenderer clawRB;
    public ModelRenderer lowerLegL;
    public ModelRenderer footL;
    public ModelRenderer lowerLegR;
    public ModelRenderer footR;

    public MalachiteDroneModel() {
        this.texWidth = 86;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 0, 24);
        this.head.setPos(0.0F, 0.0F, -3.5F);
        this.head.addBox(-3.5F, 0.0F, 0.0F, 7, 3, 11, 0.0F);
        this.clawLB = new ModelRenderer(this, 0, 13);
        this.clawLB.setPos(0.5F, 10.5F, 1.0F);
        this.clawLB.addBox(0.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(clawLB, 0.0F, 0.0F, 0.5235987755982988F);
        this.lowerArmL_1 = new ModelRenderer(this, 72, 13);
        this.lowerArmL_1.setPos(-0.1F, 10.5F, 0.5F);
        this.lowerArmL_1.addBox(-2.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(lowerArmL_1, -0.6981317007977318F, 0.0F, 0.0F);
        this.clawRT = new ModelRenderer(this, 4, 13);
        this.clawRT.setPos(-0.5F, 10.5F, -1.0F);
        this.clawRT.addBox(-1.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(clawRT, 0.0F, 0.0F, -0.5235987755982988F);
        this.footR = new ModelRenderer(this, 69, 26);
        this.footR.setPos(-1.1F, 13.0F, 2.0F);
        this.footR.addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(footR, 0.17453292519943295F, 0.0F, 0.0F);
        this.clawLT = new ModelRenderer(this, 81, 12);
        this.clawLT.setPos(0.5F, 10.5F, -1.0F);
        this.clawLT.addBox(0.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(clawLT, 0.0F, 0.0F, 0.5235987755982988F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setPos(0.0F, 0.0F, 0.0F);
        this.top.addBox(-9.0F, -6.0F, -9.0F, 18, 6, 18, 0.0F);
        this.lowerArmL = new ModelRenderer(this, 74, 0);
        this.lowerArmL.setPos(0.1F, 10.5F, 0.5F);
        this.lowerArmL.addBox(0.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(lowerArmL, -0.6981317007977318F, 0.0F, 0.0F);
        this.footL = new ModelRenderer(this, 25, 24);
        this.footL.setPos(1.1F, 13.0F, 2.0F);
        this.footL.addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(footL, 0.17453292519943295F, 0.0F, 0.0F);
        this.lowerLegR = new ModelRenderer(this, 8, 38);
        this.lowerLegR.setPos(0.0F, 8.9F, -0.2F);
        this.lowerLegR.addBox(-2.1F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(lowerLegR, -0.5235987755982988F, 0.0F, 0.0F);
        this.topCap = new ModelRenderer(this, 36, 24);
        this.topCap.setPos(0.0F, -6.0F, 0.0F);
        this.topCap.addBox(-5.5F, -3.0F, -5.5F, 11, 3, 11, 0.0F);
        this.upperArmR = new ModelRenderer(this, 8, 0);
        this.upperArmR.setPos(-4.0F, 0.0F, -1.5F);
        this.upperArmR.addBox(-2.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.lowerLegL = new ModelRenderer(this, 0, 38);
        this.lowerLegL.setPos(0.0F, 8.9F, -0.2F);
        this.lowerLegL.addBox(0.1F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(lowerLegL, -0.5235987755982988F, 0.0F, 0.0F);
        this.upperArmL = new ModelRenderer(this, 0, 0);
        this.upperArmL.setPos(4.0F, 0.0F, -1.5F);
        this.upperArmL.addBox(0.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.upperLegR = new ModelRenderer(this, 64, 0);
        this.upperLegR.setPos(-2.0F, 1.0F, 3.5F);
        this.upperLegR.addBox(-2.0F, 0.0F, -1.5F, 2, 10, 3, 0.0F);
        this.setRotateAngle(upperLegR, 0.3490658503988659F, 0.0F, 0.0F);
        this.upperLegL = new ModelRenderer(this, 54, 0);
        this.upperLegL.setPos(2.0F, 1.0F, 3.5F);
        this.upperLegL.addBox(0.0F, 0.0F, -1.5F, 2, 10, 3, 0.0F);
        this.setRotateAngle(upperLegL, 0.3490658503988659F, 0.0F, 0.0F);
        this.clawRB = new ModelRenderer(this, 8, 13);
        this.clawRB.setPos(-0.5F, 10.5F, 1.0F);
        this.clawRB.addBox(-1.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(clawRB, 0.0F, 0.0F, -0.5235987755982988F);
        this.lowerArmL.addChild(this.clawLB);
        this.upperArmR.addChild(this.lowerArmL_1);
        this.lowerArmL_1.addChild(this.clawRT);
        this.lowerLegR.addChild(this.footR);
        this.lowerArmL.addChild(this.clawLT);
        this.upperArmL.addChild(this.lowerArmL);
        this.lowerLegL.addChild(this.footL);
        this.upperLegR.addChild(this.lowerLegR);
        this.top.addChild(this.topCap);
        this.upperLegL.addChild(this.lowerLegL);
        this.lowerArmL_1.addChild(this.clawRB);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                this.head,
                this.top,
                this.upperArmR,
                this.upperArmL,
                this.upperLegR,
                this.upperLegL
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
        this.upperArmR.zRot = 0.0F;
        this.upperArmL.zRot = 0.0F;
        this.upperArmR.xRot = 0.0F;
        this.upperArmL.xRot = 0.0F;
        this.upperArmR.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.upperArmL.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.upperArmR.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.upperArmR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.upperLegR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.3490658503988659F;
        this.upperLegL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.3490658503988659F;
    }
}
