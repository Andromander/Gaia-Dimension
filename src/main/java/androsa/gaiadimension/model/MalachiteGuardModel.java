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
    private boolean reset;

    public MalachiteGuardModel(float scale) {
        this.texWidth = 128;
        this.texHeight = 64;
        //head
        this.head = new ModelRenderer(this, 17, 9);
        this.head.setPos(0.0F, -20.0F, 0.0F);
        this.head.addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7, scale);
        //helmet
        this.helmet = new ModelRenderer(this, 20, 23);
        this.helmet.setPos(0.0F, 0.0F, 0.0F);
        this.helmet.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        //right helmet blade
        this.bladeR = new ModelRenderer(this, 8, 35);
        this.bladeR.setPos(-4.1F, -6.0F, 0.0F);
        this.bladeR.addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, scale);
        this.setRotateAngle(bladeR, -1.0471975511965976F, 0.0F, 0.0F);
        //left helmet blade
        this.bladeL = new ModelRenderer(this, 0, 35);
        this.bladeL.setPos(4.1F, -6.0F, 0.0F);
        this.bladeL.addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, scale);
        this.setRotateAngle(bladeL, -1.0471975511965976F, 0.0F, 0.0F);
        //neck
        this.neck = new ModelRenderer(this, 65, 12);
        this.neck.setPos(0.0F, -4.0F, 0.0F);
        this.neck.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, scale);
        //torso
        this.torso = new ModelRenderer(this, 88, 0);
        this.torso.setPos(0.0F, -16.0F, 0.0F);
        this.torso.addBox(-6.0F, 0.0F, -2.5F, 12, 6, 5, scale);
        //right shoulder
        this.shoulderR = new ModelRenderer(this, 70, 0);
        this.shoulderR.setPos(-6.0F, -14.5F, 0.0F);
        this.shoulderR.addBox(-5.0F, -2.0F, -2.0F, 5, 2, 4, scale);
        //right upper arm
        this.upperArmR = new ModelRenderer(this, 99, 11);
        this.upperArmR.setPos(-2.5F, 0.0F, 0.0F);
        this.upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        //right lower arm
        this.lowerArmR = new ModelRenderer(this, 107, 11);
        this.lowerArmR.setPos(0.0F, 8.0F, 0.0F);
        this.lowerArmR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        //left shoulder
        this.shoulderL = new ModelRenderer(this, 66, 6);
        this.shoulderL.setPos(6.0F, -14.5F, 0.0F);
        this.shoulderL.addBox(0.0F, -2.0F, -2.0F, 5, 2, 4, scale);
        //left upper arm
        this.upperArmL = new ModelRenderer(this, 0, 13);
        this.upperArmL.setPos(2.5F, 0.0F, 0.0F);
        this.upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        //left lower arm
        this.lowerArmL = new ModelRenderer(this, 4, 19);
        this.lowerArmL.setPos(0.0F, 8.0F, 0.0F);
        this.lowerArmL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        //waist
        this.waist = new ModelRenderer(this, 58, 0);
        this.waist.setPos(0.0F, -10.0F, 0.0F);
        this.waist.addBox(-2.0F, 0.0F, -1.0F, 4, 5, 2, scale);
        //hips
        this.hips = new ModelRenderer(this, 24, 0);
        this.hips.setPos(0.0F, -5.0F, -2.5F);
        this.hips.addBox(-6.0F, 0.0F, 0.0F, 12, 4, 5, scale);
        //left leg
        this.legR = new ModelRenderer(this, 0, 0);
        this.legR.setPos(-4.5F, -1.0F, 0.0F);
        this.legR.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, scale);
        //right foot
        this.footR = new ModelRenderer(this, 45, 9);
        this.footR.setPos(0.0F, 10.0F, 0.0F);
        this.footR.addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, scale);
        //left leg
        this.legL = new ModelRenderer(this, 12, 0);
        this.legL.setPos(4.5F, -1.0F, 0.0F);
        this.legL.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, scale);
        //left foot
        this.footL = new ModelRenderer(this, 79, 11);
        this.footL.setPos(0.0F, 10.0F, 0.0F);
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
    public Iterable<ModelRenderer> parts() {
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
    public void renderToBuffer(MatrixStack stack, IVertexBuilder builder, int light, int overlay, float red, float green, float blue, float scale) {
        stack.pushPose();
        stack.translate(0.0F, offset, 0.0F);
        super.renderToBuffer(stack, builder, light, overlay, red, green, blue, scale);
        stack.popPose();
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
        if (entity.getPhase() == 0) {
            if (reset) reset = false;
            this.offset = 0.1F;

            setRotateAngle(upperArmL, -1.3F, -0.1F, 0.0F);
            setRotateAngle(upperArmR, -1.3F, 0.1F, 0.0F);
            setRotateAngle(legL, -0.5F, -0.7F, 0.0F);
            setRotateAngle(legR, -0.5F, 0.7F, 0.0F);
            setRotateAngle(footL, 0.5F, 0.0F, 0.0F);
            setRotateAngle(footR, 0.5F, 0.0F, 0.0F);
            setRotateAngle(lowerArmL, -1.7F, 0.6F, 0.0F);
            setRotateAngle(lowerArmR, -1.7F, -0.6F, 0.0F);
        } else if (entity.isCharging()) {
            if (reset) reset = false;
            this.offset = 0.5F;

            setRotateAngle(head, 0.8F, 0.0F, 0.0F);
            setRotateAngle(torso, 0.2F, 0.0F, 0.0F);
            setRotateAngle(upperArmL, -0.8F, -0.4F, 0.0F);
            setRotateAngle(upperArmR, -0.8F, 0.4F, 0.0F);
            setRotateAngle(lowerArmL, -1.8F, 0.0F, 0.0F);
            setRotateAngle(lowerArmR, -1.8F, 0.0F, 0.0F);
            setRotateAngle(legL, -1.1F, -0.3F, 0.0F);
            setRotateAngle(legR, -1.1F, 0.3F, 0.0F);
            setRotateAngle(footL, 1.6F, 0.0F, 0.0F);
            setRotateAngle(footR, 1.6F, 0.0F, 0.0F);
        } else if (entity.isCharged()) {
            if (reset) reset = false;
            this.offset = 0.1F;

            setRotateAngle(head, -0.8F, 0.0F, 0.0F);
            setRotateAngle(torso, -0.1F, 0.0F, 0.0F);
            setRotateAngle(upperArmL, -3.0F, 0.0F, 0.7F);
            setRotateAngle(upperArmR, -3.0F, 0.0F, -0.7F);
            setRotateAngle(lowerArmL, 0.0F, 0.0F, 0.0F);
            setRotateAngle(lowerArmR, 0.0F, 0.0F, 0.0F);
            setRotateAngle(legL, 0.0F, 0.0F, -0.3F);
            setRotateAngle(legR, 0.0F, 0.0F, 0.3F);
            setRotateAngle(footL, 0.0F, 0.0F, 0.0F);
            setRotateAngle(footR, 0.0F, 0.0F, 0.0F);

        } else {
            this.offset = 0.0F;

            if (!reset) {
                this.resetAngles(head, torso, upperArmL, upperArmR, legL, legR, footL, footR, lowerArmL, lowerArmR);
                this.reset = true;
            }

            //Do the angles thing
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

            this.legR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
            this.legL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

            this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
            this.head.xRot = headPitch / (180F / (float) Math.PI);
        }
    }

    private void resetAngles(ModelRenderer... renderers) {
        for (ModelRenderer part : renderers) {
            this.setRotateAngle(part, 0.0F, 0.0F, 0.0F);
        }
    }
}
