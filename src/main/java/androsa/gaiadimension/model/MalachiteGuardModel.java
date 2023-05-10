package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

/**
 * ModelMalachiteGuard - Androsa
 * Created using Tabula 7.0.0
 */
public class MalachiteGuardModel<T extends MalachiteGuardEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart torso;
    public ModelPart upperArmL;
    public ModelPart lowerArmL;
    public ModelPart upperArmR;
    public ModelPart lowerArmR;
    public ModelPart legL;
    public ModelPart legR;
    public ModelPart footL;
    public ModelPart footR;

    private float offset;
    private boolean reset;

    public MalachiteGuardModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.torso = root.getChild("torso");
        this.upperArmL = root.getChild("shoulder_left").getChild("upper_arm_left");
        this.lowerArmL = upperArmL.getChild("lower_arm_left");
        this.upperArmR = root.getChild("shoulder_right").getChild("upper_arm_right");
        this.lowerArmR = upperArmR.getChild("lower_arm_right");
        this.legL = root.getChild("leg_left");
        this.footL = legL.getChild("boot_left");
        this.legR = root.getChild("leg_right");
        this.footR = legR.getChild("boot_right");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer(CubeDeformation scale) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(17, 9)
                        .addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7, scale),
                PartPose.offset(0.0F, -20.0F, 0.0F));
        PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create()
                        .texOffs(20, 23)
                        .addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale),
                PartPose.ZERO);
        helmet.addOrReplaceChild("blade_left", CubeListBuilder.create()
                        .texOffs(0, 35)
                        .addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, scale),
                PartPose.offsetAndRotation(4.1F, -6.0F, 0.0F, -1.0471975511965976F, 0.0F, 0.0F));
        helmet.addOrReplaceChild("blade_right", CubeListBuilder.create()
                        .texOffs(8, 35)
                        .addBox(0.0F, -7.0F, 0.0F, 0, 7, 4, scale),
                PartPose.offsetAndRotation(-4.1F, -6.0F, 0.0F, -1.0471975511965976F, 0.0F, 0.0F));

        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(88, 0)
                        .addBox(-6.0F, 0.0F, -2.5F, 12, 6, 5, scale),
                PartPose.offset(0.0F, -16.0F, 0.0F));
        torso.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(65, 12)
                        .addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, scale),
                PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition leftshoulder = root.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                        .texOffs(66, 6)
                        .addBox(0.0F, -2.0F, -2.0F, 5, 2, 4, scale),
                PartPose.offset(6.0F, -14.5F, 0.0F));
        PartDefinition leftupperarm = leftshoulder.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale),
                PartPose.offset(2.5F, 0.0F, 0.0F));
        leftupperarm.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(4, 19)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition rightshoulder = root.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                        .texOffs(70, 0)
                        .addBox(-5.0F, -2.0F, -2.0F, 5, 2, 4, scale),
                PartPose.offset(-6.0F, -14.5F, 0.0F));
        PartDefinition rightupperarm = rightshoulder.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(99, 11)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale),
                PartPose.offset(-2.5F, 0.0F, 0.0F));
        rightupperarm.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(107, 11)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        root.addOrReplaceChild("waist", CubeListBuilder.create()
                        .texOffs(58, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 4, 5, 2, scale),
                PartPose.offset(0.0F, -10.0F, 0.0F));
        root.addOrReplaceChild("hips", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-6.0F, 0.0F, -2.5F, 12, 4, 5, scale),
                PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition leftleg = root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(12, 0)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, scale),
                PartPose.offset(4.5F, -1.0F, 0.0F));
        leftleg.addOrReplaceChild("boot_left", CubeListBuilder.create()
                        .texOffs(79, 11)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, scale),
                PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition rightleg = root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, scale),
                PartPose.offset(-4.5F, -1.0F, 0.0F));
        rightleg.addOrReplaceChild("boot_right", CubeListBuilder.create()
                        .texOffs(45, 9)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 15, 5, scale),
                PartPose.offset(0.0F, 10.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer builder, int light, int overlay, float red, float green, float blue, float scale) {
        stack.pushPose();
        stack.translate(0.0F, offset, 0.0F);
        super.renderToBuffer(stack, builder, light, overlay, red, green, blue, scale);
        stack.popPose();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
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
        } else if (entity.getChargePhase() == 1 || entity.getStompPhase() == 2) {
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
        } else if (entity.getChargePhase() == 2) {
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
        } else if (entity.getStompPhase() == 1) {
            if (reset) reset = false;

            setRotateAngle(head, -0.3F, 0.0F, 0.0F);
            setRotateAngle(upperArmL, -2.0F, -0.7F, 0.0F);
            setRotateAngle(upperArmR, -2.0F, 0.7F, 0.0F);
            setRotateAngle(lowerArmL, -0.8F, 0.3F, 0.0F);
            setRotateAngle(lowerArmR, -0.8F, -0.3F, 0.0F);
            setRotateAngle(legL, -2.0F, -0.5F, 0.0F);
            setRotateAngle(footL, 2.0F, 0.0F, 0.0F);
            setRotateAngle(legR, -0.2F, 0.5F, 0.0F);
            setRotateAngle(footR, 0.2F, 0.0F, 0.0F);
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
            this.upperArmR.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.upperArmL.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.upperArmR.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
            this.upperArmL.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
            this.upperArmL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
            this.upperArmR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

            this.legR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
            this.legL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

            this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
            this.head.xRot = headPitch / (180F / (float) Math.PI);
        }
    }

    private void resetAngles(ModelPart... renderers) {
        for (ModelPart part : renderers) {
            this.setRotateAngle(part, 0.0F, 0.0F, 0.0F);
        }
    }
}
