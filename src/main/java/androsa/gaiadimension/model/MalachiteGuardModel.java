package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelMalachiteGuard - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class MalachiteGuardModel<T extends MalachiteGuardEntity> extends SegmentedModel<T> {
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer hips;
    public ModelRenderer waist;
    public ModelRenderer shoulderR;
    public ModelRenderer torso;
    public ModelRenderer shoulderL;
    public ModelRenderer head;
    public ModelRenderer footR;
    public ModelRenderer footL;
    public ModelRenderer upperArmR;
    public ModelRenderer lowerArmR;
    public ModelRenderer neck;
    public ModelRenderer upperArmL;
    public ModelRenderer lowerArmL;
    public ModelRenderer helmet;
    public ModelRenderer bladeR;
    public ModelRenderer bladeL;

    private float offset;

    public MalachiteGuardModel(float scale) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        //head
        this.head = new ModelRenderer(this, 17, 9);
        this.head.setRotationPoint(0.0F, -20.0F, 0.0F);
        this.head.addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7, scale);
        //helmet
        this.helmet = new ModelRenderer(this, 20, 23);
        this.helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmet.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        //right helmet blade
        this.bladeR = new ModelRenderer(this, 8, 35);
        this.bladeR.setRotationPoint(-4.1F, -6.0F, 0.0F);
        this.bladeR.addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, scale);
        this.setRotateAngle(bladeR, -1.0471975511965976F, 0.0F, 0.0F);
        //left helmet blade
        this.bladeL = new ModelRenderer(this, 0, 35);
        this.bladeL.setRotationPoint(4.1F, -6.0F, 0.0F);
        this.bladeL.addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, scale);
        this.setRotateAngle(bladeL, -1.0471975511965976F, 0.0F, 0.0F);
        //neck
        this.neck = new ModelRenderer(this, 65, 12);
        this.neck.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.neck.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, scale);
        //torso
        this.torso = new ModelRenderer(this, 88, 0);
        this.torso.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.torso.addBox(-6.0F, 0.0F, -2.5F, 12, 6, 5, scale);
        //right shoulder
        this.shoulderR = new ModelRenderer(this, 70, 0);
        this.shoulderR.setRotationPoint(-6.0F, -14.5F, 0.0F);
        this.shoulderR.addBox(-5.0F, -2.0F, -2.0F, 5, 2, 4, scale);
        //right upper arm
        this.upperArmR = new ModelRenderer(this, 99, 11);
        this.upperArmR.setRotationPoint(-2.5F, 0.0F, 0.0F);
        this.upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        //right lower arm
        this.lowerArmR = new ModelRenderer(this, 107, 11);
        this.lowerArmR.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.lowerArmR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        //left shoulder
        this.shoulderL = new ModelRenderer(this, 66, 6);
        this.shoulderL.setRotationPoint(6.0F, -14.5F, 0.0F);
        this.shoulderL.addBox(0.0F, -2.0F, -2.0F, 5, 2, 4, scale);
        //left upper arm
        this.upperArmL = new ModelRenderer(this, 0, 13);
        this.upperArmL.setRotationPoint(2.5F, 0.0F, 0.0F);
        this.upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        //left lower arm
        this.lowerArmL = new ModelRenderer(this, 4, 19);
        this.lowerArmL.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.lowerArmL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        //waist
        this.waist = new ModelRenderer(this, 58, 0);
        this.waist.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.waist.addBox(-2.0F, 0.0F, -1.0F, 4, 5, 2, scale);
        //hips
        this.hips = new ModelRenderer(this, 24, 0);
        this.hips.setRotationPoint(0.0F, -5.0F, -2.5F);
        this.hips.addBox(-6.0F, 0.0F, 0.0F, 12, 4, 5, scale);
        //left leg
        this.legR = new ModelRenderer(this, 0, 0);
        this.legR.setRotationPoint(-4.5F, -1.0F, 0.0F);
        this.legR.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, scale);
        //right foot
        this.footR = new ModelRenderer(this, 45, 9);
        this.footR.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.footR.addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, scale);
        //left leg
        this.legL = new ModelRenderer(this, 12, 0);
        this.legL.setRotationPoint(4.5F, -1.0F, 0.0F);
        this.legL.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, scale);
        //left foot
        this.footL = new ModelRenderer(this, 79, 11);
        this.footL.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.footL.addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, scale);

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
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.head,
                this.waist,
                this.shoulderR,
                this.hips,
                this.legR,
                this.legL,
                this.torso,
                this.shoulderL
        );
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder builder, int light, int overlay, float red, float green, float blue, float scale) {
        stack.push();
        stack.translate(0.0F, offset, 0.0F);
        super.render(stack, builder, light, overlay, red, green, blue, scale);
        stack.pop();
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
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getPhase() == 0) {
            this.offset = 0.1F;

            this.upperArmL.rotateAngleX = -1.3F;
            this.upperArmR.rotateAngleX = -1.3F;
            this.upperArmL.rotateAngleY = -0.1F;
            this.upperArmR.rotateAngleY = 0.1F;

            this.legL.rotateAngleX = -0.5F;
            this.legR.rotateAngleX = -0.5F;
            this.legL.rotateAngleY = -0.7F;
            this.legR.rotateAngleY = 0.7F;

            this.footL.rotateAngleX = 0.5F;
            this.footR.rotateAngleX = 0.5F;

            this.lowerArmL.rotateAngleX = -1.7F;
            this.lowerArmR.rotateAngleX = -1.7F;
            this.lowerArmL.rotateAngleY = 0.6F;
            this.lowerArmR.rotateAngleY = -0.6F;
        } else {
            this.offset = 0.0F;

            //Reset angles
            this.upperArmL.rotateAngleX = 0F;
            this.upperArmR.rotateAngleX = 0F;
            this.upperArmL.rotateAngleY = 0F;
            this.upperArmR.rotateAngleY = 0F;

            this.legL.rotateAngleX = 0F;
            this.legR.rotateAngleX = 0F;
            this.legL.rotateAngleY = 0F;
            this.legR.rotateAngleY = 0F;

            this.footL.rotateAngleX = 0F;
            this.footR.rotateAngleX = 0F;

            this.lowerArmL.rotateAngleX = 0F;
            this.lowerArmR.rotateAngleX = 0F;
            this.lowerArmL.rotateAngleY = 0F;
            this.lowerArmR.rotateAngleY = 0F;

            //Do the angles thing
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

        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
    }
}
