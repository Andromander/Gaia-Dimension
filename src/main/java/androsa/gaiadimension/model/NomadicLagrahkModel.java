package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.NomadicLagrahkEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelNomadicLagrahk - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NomadicLagrahkModel<T extends NomadicLagrahkEntity> extends SegmentedModel<T> {
    public ModelRenderer upperLegL;
    public ModelRenderer lowerLegL;
    public ModelRenderer footL;
    public ModelRenderer toeL;
    public ModelRenderer clawsL;
    public ModelRenderer upperLegR;
    public ModelRenderer lowerLegR;
    public ModelRenderer footR;
    public ModelRenderer toeR;
    public ModelRenderer clawsR;
    public ModelRenderer lowerBody;
    public ModelRenderer midBody;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer faceL;
    public ModelRenderer browR;
    public ModelRenderer jaw;
    public ModelRenderer faceplateMid;
    public ModelRenderer faceplateL;
    public ModelRenderer faceplateR;
    public ModelRenderer teeth;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer upperArm1;
    public ModelRenderer lowerArm1;
    public ModelRenderer hand1;
    public ModelRenderer claws1;
    public ModelRenderer upperArm2;
    public ModelRenderer lowerArm2;
    public ModelRenderer hand2;
    public ModelRenderer claws2;

    public NomadicLagrahkModel() {
        this.textureWidth = 140;
        this.textureHeight = 140;
        this.snout = new ModelRenderer(this, 0, 59);
        this.snout.setRotationPoint(0.0F, -7.0F, -8.5F);
        this.snout.addCuboid(-4.5F, 0.0F, -16.0F, 9, 5, 16, 0.0F);
        this.tail4 = new ModelRenderer(this, 113, 42);
        this.tail4.setRotationPoint(0.0F, 1.0F, 9.0F);
        this.tail4.addCuboid(-2.0F, 0.0F, 0.0F, 4, 2, 9, 0.0F);
        this.setRotateAngle(tail4, 0.22759093446006054F, 0.0F, 0.0F);
        this.faceL = new ModelRenderer(this, 28, 22);
        this.faceL.setRotationPoint(2.0F, -7.4F, -9.9F);
        this.faceL.addCuboid(0.0F, 0.0F, 0.0F, 4, 4, 9, 0.0F);
        this.setRotateAngle(faceL, 0.1832595714594046F, 0.22689280275926282F, 0.0F);
        this.hand2 = new ModelRenderer(this, 98, 78);
        this.hand2.setRotationPoint(0.0F, 10.5F, 0.0F);
        this.hand2.addCuboid(-3.0F, 0.0F, -3.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(hand2, 1.3089969389957472F, 0.0F, 0.0F);
        this.faceplateL = new ModelRenderer(this, 30, 77);
        this.faceplateL.setRotationPoint(1.0F, -8.5F, -17.0F);
        this.faceplateL.addCuboid(-2.5F, 0.0F, 0.0F, 5, 3, 20, 0.0F);
        this.setRotateAngle(faceplateL, 0.22689280275926282F, 0.40142572795869574F, -0.08726646259971647F);
        this.upperArm1 = new ModelRenderer(this, 120, 61);
        this.upperArm1.setRotationPoint(-6.5F, -20.0F, -2.0F);
        this.upperArm1.addCuboid(-2.0F, 0.0F, -3.0F, 4, 10, 5, 0.0F);
        this.setRotateAngle(upperArm1, -0.3141592653589793F, 0.0F, 0.0F);
        this.browR = new ModelRenderer(this, 34, 60);
        this.browR.setRotationPoint(-1.7F, -7.4F, -9.9F);
        this.browR.addCuboid(-4.0F, 0.0F, 0.0F, 4, 4, 9, 0.0F);
        this.setRotateAngle(browR, 0.1832595714594046F, -0.22689280275926282F, 0.0F);
        this.faceplateR = new ModelRenderer(this, 0, 80);
        this.faceplateR.setRotationPoint(-1.0F, -8.5F, -17.0F);
        this.faceplateR.addCuboid(-2.5F, 0.0F, 0.0F, 5, 3, 20, 0.0F);
        this.setRotateAngle(faceplateR, 0.22689280275926282F, -0.40142572795869574F, 0.08726646259971647F);
        this.lowerLegL = new ModelRenderer(this, 38, 0);
        this.lowerLegL.setRotationPoint(0.1F, 2.1F, -12.7F);
        this.lowerLegL.addCuboid(-3.0F, -3.0F, -13.0F, 6, 6, 16, 0.0F);
        this.setRotateAngle(lowerLegL, 0.8726646259971648F, 0.0F, 0.0F);
        this.lowerLegR = new ModelRenderer(this, 0, 19);
        this.lowerLegR.setRotationPoint(-0.1F, 2.1F, -12.7F);
        this.lowerLegR.addCuboid(-3.0F, -3.0F, -13.0F, 6, 6, 16, 0.0F);
        this.setRotateAngle(lowerLegR, 0.8726646259971648F, 0.0F, 0.0F);
        this.lowerBody = new ModelRenderer(this, 72, 19);
        this.lowerBody.setRotationPoint(0.0F, -8.0F, 7.3F);
        this.lowerBody.addCuboid(-10.0F, 0.0F, -10.0F, 20, 13, 10, 0.0F);
        this.setRotateAngle(lowerBody, 0.17453292519943295F, 0.0F, 0.0F);
        this.footL = new ModelRenderer(this, 25, 0);
        this.footL.setRotationPoint(0.0F, 2.3F, -12.3F);
        this.footL.addCuboid(-3.5F, -8.0F, -3.0F, 7, 9, 3, 0.0F);
        this.setRotateAngle(footL, -0.3490658503988659F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 65, 92);
        this.tail1.setRotationPoint(0.0F, 0.0F, 7.8F);
        this.tail1.addCuboid(-5.0F, 0.0F, 0.0F, 10, 8, 15, 0.0F);
        this.setRotateAngle(tail1, -0.7285004297824331F, 0.0F, 0.0F);
        this.toeR = new ModelRenderer(this, 82, 0);
        this.toeR.setRotationPoint(2.6F, -1.0F, -2.1F);
        this.toeR.addCuboid(0.0F, -1.0F, 0.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(toeR, 0.0F, 0.36425021489121656F, 0.0F);
        this.head = new ModelRenderer(this, 79, 42);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addCuboid(-5.5F, -10.0F, -12.0F, 11, 12, 12, 0.0F);
        this.setRotateAngle(head, -0.5235987755982988F, 0.0F, 0.0F);
        this.faceplateMid = new ModelRenderer(this, 63, 66);
        this.faceplateMid.setRotationPoint(0.0F, -9.3F, -17.0F);
        this.faceplateMid.addCuboid(-3.0F, 0.0F, 0.0F, 6, 3, 23, 0.0F);
        this.setRotateAngle(faceplateMid, 0.2617993877991494F, 0.0F, 0.0F);
        this.jaw = new ModelRenderer(this, 50, 62);
        this.jaw.setRotationPoint(0.0F, -1.8F, -11.0F);
        this.jaw.addCuboid(-3.5F, 0.0F, -11.0F, 7, 4, 11, 0.0F);
        this.toeL = new ModelRenderer(this, 0, 0);
        this.toeL.setRotationPoint(-2.6F, -1.0F, -2.1F);
        this.toeL.addCuboid(-3.0F, -1.0F, 0.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(toeL, 0.0F, -0.36425021489121656F, 0.0F);
        this.clawsL = new ModelRenderer(this, 66, 0);
        this.clawsL.setRotationPoint(0.0F, -7.6F, -2.7F);
        this.clawsL.addCuboid(-3.0F, -3.0F, 0.0F, 6, 3, 2, 0.0F);
        this.upperArm2 = new ModelRenderer(this, 30, 80);
        this.upperArm2.setRotationPoint(6.5F, -20.0F, -2.0F);
        this.upperArm2.addCuboid(-2.0F, 0.0F, -3.0F, 4, 10, 5, 0.0F);
        this.setRotateAngle(upperArm2, -0.3141592653589793F, 0.0F, 0.0F);
        this.tail3 = new ModelRenderer(this, 100, 92);
        this.tail3.setRotationPoint(0.0F, 1.0F, 11.0F);
        this.tail3.addCuboid(-3.0F, 0.0F, 0.0F, 6, 4, 11, 0.0F);
        this.setRotateAngle(tail3, 0.27314402793711257F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 0, 103);
        this.tail2.setRotationPoint(0.0F, 1.1F, 13.0F);
        this.tail2.addCuboid(-4.0F, 0.0F, 0.0F, 8, 6, 13, 0.0F);
        this.setRotateAngle(tail2, 0.31869712141416456F, 0.0F, 0.0F);
        this.lowerArm2 = new ModelRenderer(this, 45, 100);
        this.lowerArm2.setRotationPoint(0.1F, 6.5F, -1.5F);
        this.lowerArm2.addCuboid(-2.0F, 0.0F, 0.0F, 4, 11, 5, 0.0F);
        this.setRotateAngle(lowerArm2, -0.7853981633974483F, 0.0F, 0.0F);
        this.claws1 = new ModelRenderer(this, 80, 8);
        this.claws1.setRotationPoint(-2.5F, 6.0F, -0.8F);
        this.claws1.addCuboid(0.0F, 0.0F, -2.0F, 5, 3, 2, 0.0F);
        this.claws2 = new ModelRenderer(this, 66, 10);
        this.claws2.setRotationPoint(-2.5F, 6.0F, -0.8F);
        this.claws2.addCuboid(0.0F, 0.0F, -2.0F, 5, 3, 2, 0.0F);
        this.upperLegL = new ModelRenderer(this, 0, 0);
        this.upperLegL.setRotationPoint(6.5F, -1.0F, 0.0F);
        this.upperLegL.addCuboid(-3.0F, -2.0F, -13.0F, 6, 6, 13, 0.0F);
        this.setRotateAngle(upperLegL, 1.0471975511965976F, -0.22689280275926282F, 0.0F);
        this.midBody = new ModelRenderer(this, 37, 35);
        this.midBody.setRotationPoint(0.0F, 1.0F, -7.6F);
        this.midBody.addCuboid(-7.0F, -18.0F, 0.0F, 14, 18, 7, 0.0F);
        this.setRotateAngle(midBody, 0.091106186954104F, 0.0F, 0.0F);
        this.lowerArm1 = new ModelRenderer(this, 0, 80);
        this.lowerArm1.setRotationPoint(-0.1F, 6.5F, -1.5F);
        this.lowerArm1.addCuboid(-2.0F, 0.0F, 0.0F, 4, 11, 5, 0.0F);
        this.setRotateAngle(lowerArm1, -0.7853981633974483F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 41);
        this.neck.setRotationPoint(0.0F, -27.4F, 0.0F);
        this.neck.addCuboid(-4.0F, -1.0F, -6.0F, 8, 12, 6, 0.0F);
        this.setRotateAngle(neck, 0.5235987755982988F, 0.0F, 0.0F);
        this.hand1 = new ModelRenderer(this, 60, 77);
        this.hand1.setRotationPoint(0.0F, 10.5F, 0.0F);
        this.hand1.addCuboid(-3.0F, 0.0F, -3.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(hand1, 1.3089969389957472F, 0.0F, 0.0F);
        this.footR = new ModelRenderer(this, 107, 0);
        this.footR.setRotationPoint(0.0F, 2.3F, -12.3F);
        this.footR.addCuboid(-3.5F, -8.0F, -3.0F, 7, 9, 3, 0.0F);
        this.setRotateAngle(footR, -0.3141592653589793F, 0.0F, 0.0F);
        this.clawsR = new ModelRenderer(this, 66, 5);
        this.clawsR.setRotationPoint(0.0F, -7.6F, -2.7F);
        this.clawsR.addCuboid(-3.0F, -3.0F, 0.0F, 6, 3, 2, 0.0F);
        this.upperLegR = new ModelRenderer(this, 82, 0);
        this.upperLegR.setRotationPoint(-6.5F, -1.0F, 0.0F);
        this.upperLegR.addCuboid(-3.0F, -2.0F, -13.0F, 6, 6, 13, 0.0F);
        this.setRotateAngle(upperLegR, 1.0471975511965976F, 0.22689280275926282F, 0.0F);
        this.teeth = new ModelRenderer(this, 98, 66);
        this.teeth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.teeth.addCuboid(-3.0F, -2.0F, -10.0F, 6, 2, 10, 0.0F);
        this.head.addChild(this.snout);
        this.tail3.addChild(this.tail4);
        this.head.addChild(this.faceL);
        this.lowerArm2.addChild(this.hand2);
        this.head.addChild(this.faceplateL);
        this.head.addChild(this.browR);
        this.head.addChild(this.faceplateR);
        this.upperLegL.addChild(this.lowerLegL);
        this.upperLegR.addChild(this.lowerLegR);
        this.lowerLegL.addChild(this.footL);
        this.footR.addChild(this.toeR);
        this.neck.addChild(this.head);
        this.head.addChild(this.faceplateMid);
        this.head.addChild(this.jaw);
        this.footL.addChild(this.toeL);
        this.footL.addChild(this.clawsL);
        this.tail2.addChild(this.tail3);
        this.tail1.addChild(this.tail2);
        this.upperArm2.addChild(this.lowerArm2);
        this.hand1.addChild(this.claws1);
        this.hand2.addChild(this.claws2);
        this.lowerBody.addChild(this.midBody);
        this.upperArm1.addChild(this.lowerArm1);
        this.midBody.addChild(this.neck);
        this.lowerArm1.addChild(this.hand1);
        this.lowerLegR.addChild(this.footR);
        this.footR.addChild(this.clawsR);
        this.jaw.addChild(this.teeth);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.upperArm1,
                this.lowerBody,
                this.tail1,
                this.upperArm2,
                this.upperLegL,
                this.upperLegR
        );
    }

//    @Override
//    public void render(NomadicLagrahkEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.upperArm1.render(f5);
//        this.lowerBody.render(f5);
//        this.tail1.render(f5);
//        this.upperArm2.render(f5);
//        this.upperLegL.render(f5);
//        this.upperLegR.render(f5);
//    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setAngles(T entity, float ageInTicks, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI) - 0.5235987755982988F;

        this.upperArm1.rotateAngleZ = 0.0F;
        this.upperArm1.rotateAngleX = -0.33161255787892263F;
        this.upperArm1.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArm1.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount + -0.33161255787892263F;

        this.upperArm2.rotateAngleZ = 0.0F;
        this.upperArm2.rotateAngleX = -0.33161255787892263F;
        this.upperArm2.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArm2.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount + -0.33161255787892263F;

        this.upperLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount + 1.0471975511965976F;
        this.upperLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount + 1.0471975511965976F;

        this.tail1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail1.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail2.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail3.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail4.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail4.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }
}
