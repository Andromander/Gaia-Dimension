package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.LesserSpitfireEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelLesserSpitfire - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class LesserSpitfireModel<T extends LesserSpitfireEntity> extends SegmentedModel<T> {
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

    public LesserSpitfireModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.capeM = new ModelRenderer(this, 22, 29);
        this.capeM.setRotationPoint(0.0F, 8.5F, 2.0F);
        this.capeM.addCuboid(-2.0F, 0.0F, 0.0F, 4, 1, 14, 0.0F);
        this.setRotateAngle(capeM, -0.7853981633974483F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 48, 0);
        this.legL.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.legL.addCuboid(-1.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.capeR = new ModelRenderer(this, 0, 41);
        this.capeR.setRotationPoint(-2.0F, 8.5F, 2.0F);
        this.capeR.addCuboid(-2.0F, 0.0F, 0.0F, 4, 1, 14, 0.0F);
        this.setRotateAngle(capeR, -0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.armR = new ModelRenderer(this, 16, 14);
        this.armR.setRotationPoint(-2.5F, 0.5F, 0.0F);
        this.armR.addCuboid(-2.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.legR = new ModelRenderer(this, 0, 14);
        this.legR.setRotationPoint(-1.5F, 10.0F, 0.0F);
        this.legR.addCuboid(-1.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.earR = new ModelRenderer(this, 24, 15);
        this.earR.setRotationPoint(-3.9F, -1.9F, 0.0F);
        this.earR.addCuboid(0.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
        this.setRotateAngle(earR, 2.356194490192345F, -0.3665191429188092F, 0.0F);
        this.headpart = new ModelRenderer(this, 32, 17);
        this.headpart.setRotationPoint(0.0F, -5.0F, 1.9F);
        this.headpart.addCuboid(-2.5F, -2.5F, 0.0F, 5, 5, 7, 0.0F);
        this.setRotateAngle(headpart, 0.40980330836826856F, 0.0F, 0.017453292519943295F);
        this.capeL = new ModelRenderer(this, 0, 26);
        this.capeL.setRotationPoint(2.0F, 8.5F, 2.0F);
        this.capeL.addCuboid(-2.0F, 0.0F, 0.0F, 4, 1, 14, 0.0F);
        this.setRotateAngle(capeL, -0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head.addCuboid(-3.5F, -7.0F, -3.5F, 7, 7, 7, 0.0F);
        this.armL = new ModelRenderer(this, 8, 14);
        this.armL.setRotationPoint(2.5F, 0.5F, 0.0F);
        this.armL.addCuboid(0.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.neck = new ModelRenderer(this, 21, 0);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addCuboid(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
        this.earL = new ModelRenderer(this, 53, 13);
        this.earL.setRotationPoint(3.5F, -1.9F, 0.0F);
        this.earL.addCuboid(-0.5F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
        this.setRotateAngle(earL, 2.356194490192345F, 0.3665191429188092F, 0.0F);
        this.body = new ModelRenderer(this, 28, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addCuboid(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.body.addChild(this.capeM);
        this.body.addChild(this.capeR);
        this.head.addChild(this.earR);
        this.head.addChild(this.headpart);
        this.body.addChild(this.capeL);
        this.head.addChild(this.earL);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.legL,
                this.armR,
                this.legR,
                this.head,
                this.armL,
                this.neck,
                this.body
        );
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
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
