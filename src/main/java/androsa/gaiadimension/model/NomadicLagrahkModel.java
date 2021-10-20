package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.NomadicLagrahkEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelNomadicLagrahk - Androsa
 * Created using Tabula 7.0.0
 */
public class NomadicLagrahkModel<T extends NomadicLagrahkEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart upperArmL;
    public ModelPart upperArmR;
    public ModelPart upperLegL;
    public ModelPart upperLegR;
    public ModelPart tail1;
    public ModelPart tail2;
    public ModelPart tail3;
    public ModelPart tail4;

    public NomadicLagrahkModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("lower_body").getChild("mid_body").getChild("neck").getChild("head");
        this.upperArmL = root.getChild("upper_arm_left");
        this.upperArmR = root.getChild("upper_arm_right");
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
        head.addOrReplaceChild("brow_left", CubeListBuilder.create()
                        .texOffs(28, 22)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 4, 9),
                PartPose.offsetAndRotation(2.0F, -7.4F, -9.9F, 0.1832595714594046F, 0.22689280275926282F, 0.0F));
        head.addOrReplaceChild("brow_right", CubeListBuilder.create()
                        .texOffs(34, 60)
                        .addBox(-4.0F, 0.0F, 0.0F, 4, 4, 9),
                PartPose.offsetAndRotation(-1.7F, -7.4F, -9.9F, 0.1832595714594046F, -0.22689280275926282F, 0.0F));
        head.addOrReplaceChild("faceplate_left", CubeListBuilder.create()
                        .texOffs(30, 77)
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 3, 20),
                PartPose.offsetAndRotation(1.0F, -8.5F, -17.0F, 0.22689280275926282F, 0.40142572795869574F, -0.08726646259971647F));
        head.addOrReplaceChild("faceplate_mid", CubeListBuilder.create()
                        .texOffs(63, 66)
                        .addBox(-3.0F, 0.0F, 0.0F, 6, 3, 23),
                PartPose.offsetAndRotation(0.0F, -9.3F, -17.0F, 0.2617993877991494F, 0.0F, 0.0F));
        head.addOrReplaceChild("faceplate_right", CubeListBuilder.create()
                        .texOffs(0, 80)
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 3, 20),
                PartPose.offsetAndRotation(-1.0F, -8.5F, -17.0F, 0.22689280275926282F, -0.40142572795869574F, 0.08726646259971647F));
        head.addOrReplaceChild("snout", CubeListBuilder.create()
                        .texOffs(0, 59)
                        .addBox(-4.5F, 0.0F, -16.0F, 9, 5, 16),
                PartPose.offset(0.0F, -7.0F, -8.5F));
        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(50, 62)
                        .addBox(-3.5F, 0.0F, -11.0F, 7, 4, 11),
                PartPose.offset(0.0F, -1.8F, -11.0F));
        jaw.addOrReplaceChild("teeth", CubeListBuilder.create()
                        .texOffs(98, 66)
                        .addBox(-3.0F, -2.0F, -10.0F, 6, 2, 10),
                PartPose.ZERO);

        PartDefinition leftupperarm = root.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(120, 61)
                        .addBox(-2.0F, 0.0F, -3.0F, 4, 10, 5),
                PartPose.offsetAndRotation(-6.5F, -20.0F, -2.0F, -0.3141592653589793F, 0.0F, 0.0F));
        PartDefinition leftlowerarm = leftupperarm.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(0, 80)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 11, 5),
                PartPose.offsetAndRotation(-0.1F, 6.5F, -1.5F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition lefthand = leftlowerarm.addOrReplaceChild("hand_left", CubeListBuilder.create()
                        .texOffs(60, 77)
                        .addBox(-3.0F, 0.0F, -3.0F, 6, 6, 4),
                PartPose.offsetAndRotation(0.0F, 10.5F, 0.0F, 1.3089969389957472F, 0.0F, 0.0F));
        lefthand.addOrReplaceChild("claws_left", CubeListBuilder.create()
                        .texOffs(80, 8)
                        .addBox(0.0F, 0.0F, -2.0F, 5, 3, 2),
                PartPose.offset(-2.5F, 6.0F, -0.8F));

        PartDefinition rightupperarm = root.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(30, 80)
                        .addBox(-2.0F, 0.0F, -3.0F, 4, 10, 5),
                PartPose.offsetAndRotation(6.5F, -20.0F, -2.0F, -0.3141592653589793F, 0.0F, 0.0F));
        PartDefinition rightlowerarm = rightupperarm.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(45, 100)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 11, 5),
                PartPose.offsetAndRotation(0.1F, 6.5F, -1.5F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition righthand = rightlowerarm.addOrReplaceChild("hand_right", CubeListBuilder.create()
                        .texOffs(98, 78)
                        .addBox(-3.0F, 0.0F, -3.0F, 6, 6, 4),
                PartPose.offsetAndRotation(0.0F, 10.5F, 0.0F, 1.3089969389957472F, 0.0F, 0.0F));
        righthand.addOrReplaceChild("claws_right", CubeListBuilder.create()
                        .texOffs(66, 10)
                        .addBox(0.0F, 0.0F, -2.0F, 5, 3, 2),
                PartPose.offset(-2.5F, 6.0F, -0.8F));

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
        leftfoot.addOrReplaceChild("talons_left", CubeListBuilder.create()
                        .texOffs(66, 0)
                        .addBox(-3.0F, -3.0F, 0.0F, 6, 3, 2),
                PartPose.offset(0.0F, -7.6F, -2.7F));
        leftfoot.addOrReplaceChild("toe_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -1.0F, 0.0F, 3, 2, 2),
                PartPose.offsetAndRotation(-2.6F, -1.0F, -2.1F, 0.0F, -0.36425021489121656F, 0.0F));

        PartDefinition rightupperleg = root.addOrReplaceChild("upper_leg_right", CubeListBuilder.create()
                        .texOffs(82, 0)
                        .addBox(-3.0F, -2.0F, -13.0F, 6, 6, 13),
                PartPose.offsetAndRotation(-6.5F, -1.0F, 0.0F, 1.0471975511965976F, 0.22689280275926282F, 0.0F));
        PartDefinition rightlowerleg = rightupperleg.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(0, 19)
                        .addBox(-3.0F, -3.0F, -13.0F, 6, 6, 16),
                PartPose.offsetAndRotation(-0.1F, 2.1F, -12.7F, 0.8726646259971648F, 0.0F, 0.0F));
        PartDefinition rightfoot = rightlowerleg.addOrReplaceChild("foot_right", CubeListBuilder.create()
                        .texOffs(107, 0)
                        .addBox(-3.5F, -8.0F, -3.0F, 7, 9, 3),
                PartPose.offsetAndRotation(0.0F, 2.3F, -12.3F, -0.3141592653589793F, 0.0F, 0.0F));
        rightfoot.addOrReplaceChild("talons_right", CubeListBuilder.create()
                        .texOffs(66, 5)
                        .addBox(-3.0F, -3.0F, 0.0F, 6, 3, 2),
                PartPose.offset(0.0F, -7.6F, -2.7F));
        rightfoot.addOrReplaceChild("toe_right", CubeListBuilder.create()
                        .texOffs(82, 0)
                        .addBox(0.0F, -1.0F, 0.0F, 3, 2, 2),
                PartPose.offsetAndRotation(2.6F, -1.0F, -2.1F, 0.0F, 0.36425021489121656F, 0.0F));

        PartDefinition tail1 = root.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(65, 92)
                        .addBox(-5.0F, 0.0F, 0.0F, 10, 8, 15),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.8F, -0.7285004297824331F, 0.0F, 0.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(0, 103)
                        .addBox(-4.0F, 0.0F, 0.0F, 8, 6, 13),
                PartPose.offsetAndRotation(0.0F, 1.1F, 13.0F, 0.31869712141416456F, 0.0F, 0.0F));
        PartDefinition tail3 = tail2.addOrReplaceChild("tail_segment_3", CubeListBuilder.create()
                        .texOffs(100, 92)
                        .addBox(-3.0F, 0.0F, 0.0F, 6, 4, 11),
                PartPose.offsetAndRotation(0.0F, 1.0F, 11.0F, 0.27314402793711257F, 0.0F, 0.0F));
        tail3.addOrReplaceChild("tail_segment_4", CubeListBuilder.create()
                        .texOffs(113, 42)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 2, 9),
                PartPose.offsetAndRotation(0.0F, 1.0F, 9.0F, 0.22759093446006054F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 140, 140);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI) - 0.5235987755982988F;

        this.upperArmL.zRot = 0.0F;
        this.upperArmL.xRot = -0.33161255787892263F;
        this.upperArmL.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArmL.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount - 0.33161255787892263F;

        this.upperArmR.zRot = 0.0F;
        this.upperArmR.xRot = -0.33161255787892263F;
        this.upperArmR.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.upperArmR.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmR.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount - 0.33161255787892263F;

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
