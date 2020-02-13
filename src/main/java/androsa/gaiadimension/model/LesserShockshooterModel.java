package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.LesserShockshooterEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelLesserShockshooter - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class LesserShockshooterModel<T extends LesserShockshooterEntity> extends SegmentedModel<T> {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer neck;
    public ModelRenderer armL;
    public ModelRenderer armR;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer headpart;
    public ModelRenderer capeL;
    public ModelRenderer capeM;
    public ModelRenderer capeR;

    public LesserShockshooterModel() {
        this.textureWidth = 80;
        this.textureHeight = 64;
        this.legL = new ModelRenderer(this, 52, 0);
        this.legL.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.legL.addCuboid(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.armL = new ModelRenderer(this, 14, 14);
        this.armL.setRotationPoint(3.5F, 0.5F, 0.0F);
        this.armL.addCuboid(0.0F, 0.0F, -2.0F, 3, 11, 4, 0.0F);
        this.neck = new ModelRenderer(this, 0, 14);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addCuboid(-2.0F, -2.0F, -1.5F, 4, 2, 3, 0.0F);
        this.headpart = new ModelRenderer(this, 18, 19);
        this.headpart.setRotationPoint(0.0F, -4.3F, -3.0F);
        this.headpart.addCuboid(-2.5F, -2.0F, 0.0F, 5, 2, 10, 0.0F);
        this.setRotateAngle(headpart, 1.3089969389957472F, 0.0F, 0.017453292519943295F);
        this.armR = new ModelRenderer(this, 48, 15);
        this.armR.setRotationPoint(-3.5F, 0.5F, 0.0F);
        this.armR.addCuboid(-3.0F, 0.0F, -2.0F, 3, 11, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head.addCuboid(-3.5F, -7.0F, -3.5F, 7, 7, 7, 0.0F);
        this.capeM = new ModelRenderer(this, 0, 31);
        this.capeM.setRotationPoint(0.0F, 1.5F, 2.5F);
        this.capeM.addCuboid(-3.5F, 0.0F, 0.0F, 7, 1, 17, 0.0F);
        this.setRotateAngle(capeM, -1.3089969389957472F, 0.0F, 0.0F);
        this.legR = new ModelRenderer(this, 64, 0);
        this.legR.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.legR.addCuboid(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.earL = new ModelRenderer(this, 62, 15);
        this.earL.setRotationPoint(3.5F, -1.9F, 0.0F);
        this.earL.addCuboid(-0.5F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
        this.setRotateAngle(earL, 2.356194490192345F, 0.3665191429188092F, 0.0F);
        this.capeL = new ModelRenderer(this, 34, 30);
        this.capeL.setRotationPoint(2.0F, 1.5F, 2.0F);
        this.capeL.addCuboid(-2.0F, 0.0F, 0.0F, 4, 1, 14, 0.0F);
        this.setRotateAngle(capeL, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.earR = new ModelRenderer(this, 70, 15);
        this.earR.setRotationPoint(-3.9F, -1.9F, 0.0F);
        this.earR.addCuboid(0.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
        this.setRotateAngle(earR, 2.356194490192345F, -0.3665191429188092F, 0.0F);
        this.capeR = new ModelRenderer(this, 34, 45);
        this.capeR.setRotationPoint(-2.0F, 1.5F, 2.0F);
        this.capeR.addCuboid(-2.0F, 0.0F, 0.0F, 4, 1, 14, 0.0F);
        this.setRotateAngle(capeR, 0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.body = new ModelRenderer(this, 28, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addCuboid(-3.5F, 0.0F, -2.5F, 7, 12, 5, 0.0F);
        this.head.addChild(this.headpart);
        this.body.addChild(this.capeM);
        this.head.addChild(this.earL);
        this.body.addChild(this.capeL);
        this.head.addChild(this.earR);
        this.body.addChild(this.capeR);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.legL,
                this.armL,
                this.neck,
                this.armR,
                this.head,
                this.legR,
                this.body
        );
    }

//    @Override
//    public void render(LesserShockshooterEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.legL.render(f5);
//        this.armL.render(f5);
//        this.neck.render(f5);
//        this.armR.render(f5);
//        this.head.render(f5);
//        this.legR.render(f5);
//        this.body.render(f5);
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
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
        this.armR.rotateAngleZ = 0.0F;
        this.armL.rotateAngleZ = 0.0F;
        this.armR.rotateAngleY = -(0.1F - f * 0.6F);
        this.armL.rotateAngleY = 0.1F - f * 0.6F;
        this.armR.rotateAngleX = 0.0F;
        this.armL.rotateAngleX = 0.0F;
        this.armR.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.armL.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.armR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.armR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
    }
}
