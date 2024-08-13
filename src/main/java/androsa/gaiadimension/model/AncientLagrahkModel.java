package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.AncientLagrahk;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelAncientLagrahk - Androsa
 * Created using Tabula 7.0.0
 */
public class AncientLagrahkModel<T extends AncientLagrahk> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart upperArm1;
    public ModelPart upperArm2;
    public ModelPart upperArm3;
    public ModelPart upperArm4;
    public ModelPart upperLegL;
    public ModelPart upperLegR;
    public ModelPart tail1;
    public ModelPart tail2;
    public ModelPart tail3;
    public ModelPart tail4;

    public AncientLagrahkModel(ModelPart root) {
        this.root = root;

        this.head = root.getChild("lower_body").getChild("mid_body").getChild("neck").getChild("head");
        this.upperArm1 = root.getChild("upper_arm_top_left");
        this.upperArm2 = root.getChild("upper_arm_top_right");
        this.upperArm3 = root.getChild("upper_arm_bottom_left");
        this.upperArm4 = root.getChild("upper_arm_bottom_right");
        this.upperLegL = root.getChild("upper_leg_left");
        this.upperLegR = root.getChild("upper_leg_right");
        this.tail1 = root.getChild("tail_segment_1");
        this.tail2 = tail1.getChild("tail_segment_2");
        this.tail3 = tail2.getChild("tail_segment_3");
        this.tail4 = tail3.getChild("tail_segment_4");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition lowerbody = root.addOrReplaceChild("lower_body", CubeListBuilder.create()
                        .texOffs(72, 19)
                        .addBox(-10.0F, 0.0F, -10.0F, 20, 13, 10),
                PartPose.offsetAndRotation(0.0F, -8.0F, 7.3F, 0.17453292519943295F, 0.0F, 0.0F));
        PartDefinition midbody = lowerbody.addOrReplaceChild("mid_body", CubeListBuilder.create()
                        .texOffs(37, 35)
                        .addBox(-7.0F, -18.0F, 0.0F, 14, 18, 7),
                PartPose.offsetAndRotation(0.0F, 1.0F, -7.6F, 0.091106186954104F, 0.0F, 0.0F));
        PartDefinition neck = midbody.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-4.0F, -1.0F, -6.0F, 8, 12, 6),
                PartPose.offsetAndRotation(0.0F, -27.4F, 0.0F, 0.5235987755982988F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(79, 42)
                        .addBox(-5.5F, -10.0F, -12.0F, 11, 12, 12),
                PartPose.rotation(-0.5235987755982988F, 0.0F, 0.0F));
        head.addOrReplaceChild("snout", CubeListBuilder.create()
                        .texOffs(0, 59)
                        .addBox(-4.5F, 0.0F, -16.0F, 9, 5, 16),
                PartPose.offsetAndRotation(0.0F, -8.5F, -10.0F, 0.18203784098300857F, 0.0F, 0.0F));
        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(50, 62)
                        .addBox(-3.5F, 0.0F, -11.0F, 7, 4, 11),
                PartPose.offset(0.0F, -1.8F, -11.0F));
        jaw.addOrReplaceChild("teeth", CubeListBuilder.create()
                        .texOffs(40, 77)
                        .addBox(-3.0F, -2.0F, -10.0F, 6, 2, 10),
                PartPose.ZERO);
        head.addOrReplaceChild("brow_left", CubeListBuilder.create()
                        .texOffs(28, 22)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 4, 9),
                PartPose.offsetAndRotation(2.0F, -7.4F, -9.9F, 0.1832595714594046F, 0.22689280275926282F, 0.0F));
        head.addOrReplaceChild("brow_right", CubeListBuilder.create()
                        .texOffs(34, 60)
                        .addBox(-4.0F, 0.0F, 0.0F, 4, 4, 9),
                PartPose.offsetAndRotation(-1.7F, -7.4F, -9.9F, 0.1832595714594046F, -0.22689280275926282F, 0.0F));
        PartDefinition lowerhornleft = head.addOrReplaceChild("horn_base_left", CubeListBuilder.create()
                        .texOffs(73, 66)
                        .addBox(-2.0F, -2.0F, 0.0F, 5, 5, 13),
                PartPose.offsetAndRotation(3.2F, -7.3F, -9.7F, 1.2304571226560024F, -0.18203784098300857F, 0.9773843811168246F));
        PartDefinition midhornleft = lowerhornleft.addOrReplaceChild("horn_body_left", CubeListBuilder.create()
                        .texOffs(0, 80)
                        .addBox(-1.5F, -1.5F, 0.0F, 4, 4, 10),
                PartPose.offsetAndRotation(0.0F, 0.0F, 10.4F, 1.5707963267948966F, 0.0F, -0.7853981633974483F));
        midhornleft.addOrReplaceChild("horn_tip_left", CubeListBuilder.create()
                        .texOffs(113, 42)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 2, 9),
                PartPose.offsetAndRotation(0.5F, 0.0F, 9.9F, -1.5707963267948966F, 0.0F, 0.0F));
        PartDefinition lowerhornright = head.addOrReplaceChild("horn_base_right", CubeListBuilder.create()
                        .texOffs(96, 71)
                        .addBox(-2.0F, -2.0F, 0.0F, 5, 5, 13),
                PartPose.offsetAndRotation(-4.6F, -7.3F, -9.1F, 1.2304571226560024F, 0.18203784098300857F, -0.9773843811168246F));
        PartDefinition midhornright = lowerhornright.addOrReplaceChild("horn_body_right", CubeListBuilder.create()
                        .texOffs(18, 84)
                        .addBox(-1.5F, -1.5F, 0.0F, 4, 4, 10),
                PartPose.offsetAndRotation(0.0F, -0.3F, 10.4F, 1.5707963267948966F, 0.0F, 0.7853981633974483F));
        midhornright.addOrReplaceChild("horn_tip_right", CubeListBuilder.create()
                        .texOffs(116, 57)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 2, 9),
                PartPose.offsetAndRotation(0.5F, 0.0F, 9.9F, -1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition topleftupperarm = root.addOrReplaceChild("upper_arm_top_left", CubeListBuilder.create()
                        .texOffs(125, 12)
                        .addBox(-1.5F, 0.0F, -3.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-4.5F, -20.0F, -2.0F, -0.45378560551852565F, 0.0F, 0.0F));
        PartDefinition topleftlowerarm = topleftupperarm.addOrReplaceChild("lower_arm_top_left", CubeListBuilder.create()
                        .texOffs(0, 19)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-0.1F, 5.9F, -0.1F, -1.5707963267948966F, 0.0F, 0.0F));
        PartDefinition toplefthand = topleftlowerarm.addOrReplaceChild("hand_top_left", CubeListBuilder.create()
                        .texOffs(66, 8)
                        .addBox(-2.0F, 0.0F, -3.0F, 4, 4, 3),
                PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 1.5707963267948966F, 0.0F, 0.0F));
        toplefthand.addOrReplaceChild("claw_left_top_left", CubeListBuilder.create()
                        .texOffs(41, 8)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(1.0F, 3.1F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));
        toplefthand.addOrReplaceChild("claw_right_top_left", CubeListBuilder.create()
                        .texOffs(0, 5)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(-1.0F, 3.0F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition toprightupperarm = root.addOrReplaceChild("upper_arm_top_right", CubeListBuilder.create()
                        .texOffs(54, 22)
                        .addBox(-1.5F, 0.0F, -3.0F, 3, 9, 3),
                PartPose.offsetAndRotation(4.5F, -20.0F, -2.0F, -0.45378560551852565F, 0.0F, 0.0F));
        PartDefinition toprightlowerarm = toprightupperarm.addOrReplaceChild("lower_arm_top_right", CubeListBuilder.create()
                        .texOffs(79, 42)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-0.1F, 5.9F, -0.1F, -1.5707963267948966F, 0.0F, 0.0F));
        PartDefinition toprighthand = toprightlowerarm.addOrReplaceChild("hand_top_right", CubeListBuilder.create()
                        .texOffs(66, 22)
                        .addBox(-2.0F, 0.0F, -3.0F, 4, 4, 3),
                PartPose.offsetAndRotation(0.0F, 8.5F, -0.1F, 1.5707963267948966F, 0.0F, 0.0F));
        toprighthand.addOrReplaceChild("claw_left_top_right", CubeListBuilder.create()
                        .texOffs(22, 41)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(1.0F, 3.1F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));
        toprighthand.addOrReplaceChild("claw_right_top_right", CubeListBuilder.create()
                        .texOffs(128, 38)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(-1.0F, 3.0F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition downleftupperarm = root.addOrReplaceChild("upper_arm_bottom_left", CubeListBuilder.create()
                        .texOffs(0, 59)
                        .addBox(-1.5F, 0.0F, -3.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-4.5F, -10.0F, 1.0F, -0.7285004297824331F, 0.0F, 0.0F));
        PartDefinition downleftlowerarm = downleftupperarm.addOrReplaceChild("lower_arm_bottom_left", CubeListBuilder.create()
                        .texOffs(96, 66)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-0.1F, 5.9F, -0.1F, -1.5707963267948966F, 0.0F, 0.0F));
        PartDefinition downlefthand = downleftlowerarm.addOrReplaceChild("hand_bottom_left", CubeListBuilder.create()
                        .texOffs(119, 68)
                        .addBox(-2.0F, 0.0F, -3.0F, 4, 4, 3),
                PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 1.5707963267948966F, 0.0F, 0.0F));
        downlefthand.addOrReplaceChild("claw_left_bottom_left", CubeListBuilder.create()
                        .texOffs(129, 53)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(1.0F, 3.1F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));
        downlefthand.addOrReplaceChild("claw_right_bottom_left", CubeListBuilder.create()
                        .texOffs(126, 44)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(-1.0F, 3.0F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition downrightupperarm = root.addOrReplaceChild("upper_arm_bottom_right", CubeListBuilder.create()
                        .texOffs(123, 89)
                        .addBox(-1.5F, 0.0F, -3.0F, 3, 9, 3),
                PartPose.offsetAndRotation(4.5F, -10.0F, 1.0F, -0.7285004297824331F, 0.0F, 0.0F));
        PartDefinition downrightlowerarm = downrightupperarm.addOrReplaceChild("lower_arm_bottom_right", CubeListBuilder.create()
                        .texOffs(43, 95)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 9, 3),
                PartPose.offsetAndRotation(-0.1F, 5.9F, -0.1F, -1.5707963267948966F, 0.0F, 0.0F));
        PartDefinition downrighthand = downrightlowerarm.addOrReplaceChild("hand_bottom_right", CubeListBuilder.create()
                        .texOffs(119, 75)
                        .addBox(-2.0F, 0.0F, -3.0F, 4, 4, 3),
                PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 1.5707963267948966F, 0.0F, 0.0F));
        downrighthand.addOrReplaceChild("claw_left_bottom_right", CubeListBuilder.create()
                        .texOffs(51, 60)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(1.0F, 3.1F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));
        downrighthand.addOrReplaceChild("claw_right_bottom_right", CubeListBuilder.create()
                        .texOffs(129, 59)
                        .addBox(-0.5F, -2.0F, -4.0F, 1, 2, 4),
                PartPose.offsetAndRotation(-1.0F, 3.0F, -0.9F, 1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition leftupperleg = root.addOrReplaceChild("upper_leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -2.0F, -13.0F, 6, 6, 13),
                PartPose.offsetAndRotation(6.5F, -1.0F, 0.0F, 1.0471975511965976F, -0.22689280275926282F, 0.0F));
        PartDefinition leftlowerleg = leftupperleg.addOrReplaceChild("lower_leg_left", CubeListBuilder.create()
                        .texOffs(38, 0)
                        .addBox(-3.0F, -3.0F, -13.0F, 6, 6, 16),
                PartPose.offsetAndRotation(0.1F, 2.1F, -12.7F, 0.8726646259971648F, 0.0F, 0.0F));
        PartDefinition leftfoot = leftlowerleg.addOrReplaceChild("foot_left", CubeListBuilder.create()
                        .texOffs(25, 0)
                        .addBox(-3.5F, -8.0F, -3.0F, 7, 9, 3),
                PartPose.offsetAndRotation(0.0F, 2.3F, -12.3F, -0.3490658503988659F, 0.0F, 0.0F));
        leftfoot.addOrReplaceChild("toe_left", CubeListBuilder.create()
                        .texOffs(74, 0)
                        .addBox(-3.0F, -1.0F, 0.0F, 3, 2, 2),
                PartPose.offsetAndRotation(-2.6F, -1.0F, -2.1F, 0.0F, -0.36425021489121656F, 0.0F));
        leftfoot.addOrReplaceChild("claws_left", CubeListBuilder.create()
                        .texOffs(84, 0)
                        .addBox(-3.0F, -3.0F, 0.0F, 6, 3, 2),
                PartPose.offset(0.0F, -7.6F, -2.7F));

        PartDefinition rightupperleg = root.addOrReplaceChild("upper_leg_right", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -2.0F, -13.0F, 6, 6, 13),
                PartPose.offsetAndRotation(-6.5F, -1.0F, 0.0F, 1.0471975511965976F, 0.22689280275926282F, 0.0F));
        PartDefinition rightlowerleg = rightupperleg.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(0, 19)
                        .addBox(-3.0F, -3.0F, -13.0F, 6, 6, 16),
                PartPose.offsetAndRotation(-0.1F, 2.1F, -12.7F, 0.8726646259971648F, 0.0F, 0.0F));
        PartDefinition rightfoot = rightlowerleg.addOrReplaceChild("foot_right", CubeListBuilder.create()
                        .texOffs(112, 0)
                        .addBox(-3.5F, -8.0F, -3.0F, 7, 9, 3),
                PartPose.offsetAndRotation(0.0F, 2.3F, -12.3F, -0.3141592653589793F, 0.0F, 0.0F));
        rightfoot.addOrReplaceChild("toe_right", CubeListBuilder.create()
                        .texOffs(72, 4)
                        .addBox(0.0F, -1.0F, 0.0F, 3, 2, 2),
                PartPose.offsetAndRotation(2.6F, -1.0F, -2.1F, 0.0F, 0.36425021489121656F, 0.0F));
        rightfoot.addOrReplaceChild("claws_right", CubeListBuilder.create()
                        .texOffs(82, 5)
                        .addBox(-3.0F, -3.0F, 0.0F, 6, 3, 2),
                PartPose.offset(0.0F, -7.6F, -2.7F));

        PartDefinition tail1 = root.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(57, 84)
                        .addBox(-5.0F, 0.0F, 0.0F, 10, 8, 15),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.8F, -0.7285004297824331F, 0.0F, 0.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(94, 94)
                        .addBox(-4.0F, 0.0F, 0.0F, 8, 6, 13),
                PartPose.offsetAndRotation(0.0F, 1.1F, 13.0F, 0.31869712141416456F, 0.0F, 0.0F));
        PartDefinition tail3 = tail2.addOrReplaceChild("tail_segment_3", CubeListBuilder.create()
                        .texOffs(0, 98)
                        .addBox(-3.0F, 0.0F, 0.0F, 6, 4, 11),
                PartPose.offsetAndRotation(0.0F, 1.0F, 11.0F, 0.27314402793711257F, 0.0F, 0.0F));
        tail3.addOrReplaceChild("tail_segment_4", CubeListBuilder.create()
                        .texOffs(23, 98)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 2, 9),
                PartPose.offsetAndRotation(0.0F, 1.0F, 9.0F, 0.22759093446006054F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 140, 140);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI) - 0.5235987755982988F;

        this.upperArm1.zRot = 0.0F;
        this.upperArm1.xRot = -0.33161255787892263F;
        this.upperArm1.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArm1.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArm1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount - 0.33161255787892263F;

        this.upperArm2.zRot = 0.0F;
        this.upperArm2.xRot = -0.33161255787892263F;
        this.upperArm2.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArm2.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArm2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount - 0.33161255787892263F;

        this.upperArm3.zRot = 0.0F;
        this.upperArm3.xRot = -0.33161255787892263F;
        this.upperArm3.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArm3.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArm3.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount - 0.33161255787892263F;

        this.upperArm4.zRot = 0.0F;
        this.upperArm4.xRot = -0.33161255787892263F;
        this.upperArm4.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArm4.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArm4.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount - 0.33161255787892263F;

        this.upperLegL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount + 1.0471975511965976F;
        this.upperLegR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount + 1.0471975511965976F;

        this.tail1.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail1.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail2.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail2.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail3.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail4.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tail4.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
    }
}
