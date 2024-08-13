package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.BismuthUletrus;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelBismuthUletrus - Androsa
 * Created using Tabula 7.0.0
 */
public class BismuthUletrusModel<T extends BismuthUletrus> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart upperArmL;
    public ModelPart lowerArmL;
    public ModelPart frontPawL;
    public ModelPart upperArmR;
    public ModelPart lowerArmR;
    public ModelPart frontPawR;
    public ModelPart upperLegL;
    public ModelPart lowerLegL;
    public ModelPart backPawL;
    public ModelPart upperLegR;
    public ModelPart lowerLegR;
    public ModelPart backPawR;
    public ModelPart tail1;
    public ModelPart tail2;
    public ModelPart tail3;
    public ModelPart tail4;

    public BismuthUletrusModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("body").getChild("neck").getChild("head");
        this.upperArmL = root.getChild("upper_arm_left");
        this.lowerArmL = upperArmL.getChild("lower_arm_left");
        this.frontPawL = lowerArmL.getChild("front_paw_left");
        this.upperArmR = root.getChild("upper_arm_right");
        this.lowerArmR = upperArmR.getChild("lower_arm_right");
        this.frontPawR = lowerArmR.getChild("front_paw_right");
        this.upperLegL = root.getChild("upper_leg_left");
        this.lowerLegL = upperLegL.getChild("lower_leg_left");
        this.backPawL = lowerLegL.getChild("back_paw_left");
        this.upperLegR = root.getChild("upper_leg_right");
        this.lowerLegR = upperLegR.getChild("lower_leg_right");
        this.backPawR = lowerLegR.getChild("back_paw_right");
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

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-9.0F, 0.0F, 0.0F, 18, 14, 31),
                PartPose.offset(0.0F, -2.0F, -13.0F));
        body.addOrReplaceChild("sail", CubeListBuilder.create()
                        .texOffs(91, 43)
                        .addBox(-0.5F, -18.0F, 0.0F, 1, 18, 32),
                PartPose.rotation(-0.17453292519943295F, 0.0F, 0.0F));
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(0, 45)
                        .addBox(-5.0F, 0.0F, -10.0F, 10, 11, 10),
                PartPose.offsetAndRotation(0.0F, 1.5F, 5.5F, -0.4363323129985824F, 0.0F, 0.0F));
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(40, 53)
                        .addBox(-5.5F, -6.0F, -12.0F, 11, 12, 12),
                PartPose.offsetAndRotation(0.0F, 4.5F, -6.0F, 0.4363323129985824F, 0.0F, 0.0F));
        head.addOrReplaceChild("snout", CubeListBuilder.create()
                        .texOffs(0, 66)
                        .addBox(-4.5F, 0.0F, -14.0F, 9, 5, 14),
                PartPose.offset(0.0F, -3.0F, -8.5F));
        head.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(125, 43)
                        .addBox(-3.5F, 0.0F, -10.0F, 7, 4, 10),
                PartPose.offset(0.0F, 2.0F, -11.0F));
        head.addOrReplaceChild("brow_left", CubeListBuilder.create()
                        .texOffs(28, 77)
                        .addBox(-2.5F, -2.0F, 0.0F, 5, 3, 18),
                PartPose.offsetAndRotation(4.0F, -4.0F, -13.0F, 0.3665191429188092F, 0.17453292519943295F, 0.6981317007977318F));
        head.addOrReplaceChild("brow_right", CubeListBuilder.create()
                        .texOffs(56, 80)
                        .addBox(-2.5F, -2.0F, 0.0F, 5, 3, 18),
                PartPose.offsetAndRotation(-4.0F, -4.0F, -13.0F, 0.3665191429188092F, -0.17453292519943295F, -0.6981317007977318F));
        head.addOrReplaceChild("horn", CubeListBuilder.create()
                        .texOffs(146, 18)
                        .addBox(-1.5F, -12.0F, -1.5F, 3, 13, 3),
                PartPose.offsetAndRotation(0.0F, -5.0F, -11.5F, 0.7853981633974483F, 0.0F, 0.0F));

        PartDefinition leftupperarm = root.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(118, 0)
                        .addBox(-2.0F, 0.0F, -3.0F, 7, 12, 8),
                PartPose.offsetAndRotation(8.0F, 3.0F, -10.0F, 0.33161255787892263F, 0.0F, 0.0F));
        PartDefinition leftlowerarm = leftupperarm.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(20, 98)
                        .addBox(0.0F, 0.0F, -2.0F, 7, 12, 6),
                PartPose.offsetAndRotation(-1.9F, 9.2F, 1.9F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition frontleftpaw = leftlowerarm.addOrReplaceChild("front_paw_left", CubeListBuilder.create()
                        .texOffs(48, 101)
                        .addBox(-4.5F, 0.0F, -3.0F, 9, 9, 4),
                PartPose.offsetAndRotation(3.5F, 10.9F, 5.5F, -1.1344640137963142F, 0.0F, 0.0F));
        frontleftpaw.addOrReplaceChild("front_claws_left", CubeListBuilder.create()
                        .texOffs(67, 23)
                        .addBox(-3.0F, 0.0F, -3.0F, 8, 3, 2),
                PartPose.offset(-1.0F, 9.0F, 1.7F));

        PartDefinition rightupperarm = root.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-5.0F, 0.0F, -3.0F, 7, 12, 8),
                PartPose.offsetAndRotation(-8.0F, 3.0F, -10.0F, 0.33161255787892263F, 0.0F, 0.0F));
        PartDefinition rightlowerarm = rightupperarm.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(0, 85)
                        .addBox(-7.0F, 0.0F, -2.0F, 7, 12, 6),
                PartPose.offsetAndRotation(1.9F, 9.2F, 1.9F, -0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition frontrightpaw = rightlowerarm.addOrReplaceChild("front_paw_right", CubeListBuilder.create()
                        .texOffs(118, 93)
                        .addBox(-4.5F, 0.0F, -3.0F, 9, 9, 4),
                PartPose.offsetAndRotation(-3.5F, 10.9F, 5.5F, -1.1344640137963142F, 0.0F, 0.0F));
        frontrightpaw.addOrReplaceChild("front_claws_right", CubeListBuilder.create()
                        .texOffs(102, 0)
                        .addBox(-3.0F, 0.0F, -3.0F, 8, 3, 2),
                PartPose.offset(-1.0F, 9.0F, 1.7F));

        PartDefinition leftupperleg = root.addOrReplaceChild("upper_leg_left", CubeListBuilder.create()
                        .texOffs(85, 43)
                        .addBox(-3.0F, -2.0F, -13.0F, 6, 9, 13),
                PartPose.offsetAndRotation(8.0F, 1.5F, 15.0F, 1.0471975511965976F, 0.0F, 0.0F));
        PartDefinition leftlowerleg = leftupperleg.addOrReplaceChild("lower_leg_left", CubeListBuilder.create()
                        .texOffs(87, 112)
                        .addBox(-3.0F, -3.0F, -10.0F, 6, 7, 13),
                PartPose.offsetAndRotation(0.1F, 2.1F, -12.7F, 0.8726646259971648F, 0.0F, 0.0F));
        PartDefinition backleftpaw = leftlowerleg.addOrReplaceChild("back_paw_left", CubeListBuilder.create()
                        .texOffs(0, 113)
                        .addBox(-3.5F, -8.0F, -3.0F, 7, 10, 4),
                PartPose.offsetAndRotation(0.0F, 2.5F, -9.5F, -0.3490658503988659F, 0.0F, 0.0F));
        backleftpaw.addOrReplaceChild("back_claws_left", CubeListBuilder.create()
                        .texOffs(89, 23)
                        .addBox(-3.0F, -3.0F, 0.0F, 6, 3, 3),
                PartPose.offset(0.0F, -7.6F, -2.7F));

        PartDefinition rightupperleg = root.addOrReplaceChild("upper_leg_right", CubeListBuilder.create()
                        .texOffs(104, 21)
                        .addBox(-3.0F, -2.0F, -13.0F, 6, 9, 13),
                PartPose.offsetAndRotation(-8.0F, 1.5F, 15.0F, 1.0471975511965976F, 0.0F, 0.0F));
        PartDefinition rightlowerleg = rightupperleg.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(62, 102)
                        .addBox(-3.0F, -3.0F, -10.0F, 6, 7, 13),
                PartPose.offsetAndRotation(0.1F, 2.1F, -12.7F, 0.8726646259971648F, 0.0F, 0.0F));
        PartDefinition backrightpaw = rightlowerleg.addOrReplaceChild("back_paw_right", CubeListBuilder.create()
                        .texOffs(131, 106)
                        .addBox(-3.5F, -8.0F, -3.0F, 7, 10, 4),
                PartPose.offsetAndRotation(0.0F, 2.5F, -9.5F, -0.3490658503988659F, 0.0F, 0.0F));
        backrightpaw.addOrReplaceChild("back_claws_right", CubeListBuilder.create()
                        .texOffs(140, 0)
                        .addBox(-3.0F, -3.0F, 0.0F, 6, 3, 3),
                PartPose.offset(0.0F, -7.6F, -2.7F));

        PartDefinition tail1 = root.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(67, 0)
                        .addBox(-5.0F, 0.0F, 0.0F, 10, 8, 15),
                PartPose.offsetAndRotation(0.0F, -1.5F, 16.0F, -0.7285004297824331F, -0.014660765716752367F, 0.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(89, 93)
                        .addBox(-4.0F, 0.0F, 0.0F, 8, 6, 13),
                PartPose.offsetAndRotation(0.0F, 1.1F, 13.0F, 0.31869712141416456F, -0.014660765716752367F, 0.0F));
        PartDefinition tail3 = tail2.addOrReplaceChild("tail_segment_3", CubeListBuilder.create()
                        .texOffs(125, 57)
                        .addBox(-3.0F, 0.0F, 0.0F, 6, 4, 11),
                PartPose.offsetAndRotation(0.0F, 1.0F, 11.0F, 0.27314402793711257F, -0.014660765716752367F, 0.0F));
        tail3.addOrReplaceChild("tail_segment_4", CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 2, 9),
                PartPose.offsetAndRotation(0.0F, 1.0F, 9.0F, 0.22759093446006054F, -0.014660765716752367F, 0.0F));

        return LayerDefinition.create(mesh, 160, 160);
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
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI) + 0.4363323129985824F;

        if (entity.getResting()) {
            this.setRotateAngle(upperArmL, 1.117010721276371F, 0.4363323129985824F, 0.0F);
            this.setRotateAngle(upperArmR, 1.117010721276371F, -0.4363323129985824F, 0.0F);
            this.setRotateAngle(lowerArmL, -1.7453292519943295F, 0.0F, 0.0F);
            this.setRotateAngle(lowerArmR, -1.7453292519943295F, 0.0F, 0.0F);
            this.setRotateAngle(frontPawL, -0.9599310885968813F, 0.0F, 0.0F);
            this.setRotateAngle(frontPawR, -0.9599310885968813F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegL, 0.2792526803190927F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegR, 0.2792526803190927F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegL, 1.6755160819145563F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegR, 1.6755160819145563F, 0.0F, 0.0F);
            this.setRotateAngle(backPawL, -0.3665191429188092F, 0.0F, 0.0F);
            this.setRotateAngle(backPawR, -0.3665191429188092F, 0.0F, 0.0F);
            this.setRotateAngle(tail1, -0.6044075199656364F, -0.31869712141416456F, 0.20943951023931953F);
            this.setRotateAngle(tail2, 0.13334315485236678F, -0.31869712141416456F, -0.14817845349431857F);
            this.setRotateAngle(tail3, 0.27314402793711257F, -0.6631701824345141F, -0.29646144764240534F);
            this.setRotateAngle(tail4, -0.222346085731804F, -0.6261125014792135F, 0.0F);

//            this.body.offsetY = 0.4F;
//            this.upperArmL.offsetY = 0.4F;
//            this.upperArmR.offsetY = 0.4F;
//            this.upperLegL.offsetY = 0.4F;
//            this.upperLegR.offsetY = 0.4F;
//            this.tail1.offsetY = 0.4F;
//            this.body.setPos(0.0F, 4.5F, -13.0F);
//            this.upperArmL.setPos(8.0F, 9.5F, -10.0F);
//            this.upperArmR.setPos(-8.0F, 9.5F, -10.0F);
//            this.upperLegL.setPos(8.0F, 8.0F, 15.0F);
//            this.upperLegR.setPos(-8.0F, 8.0F, 15.0F);
//            this.tail1.setPos(0.0F, 6.0F, 16.0F);
            this.root.y = 6.5F;
        } else {
            this.setRotateAngle(upperArmL, 0.33161255787892263F, 0.0F, 0.0F);
            this.setRotateAngle(upperArmR, 0.33161255787892263F, 0.0F, 0.0F);
            this.setRotateAngle(lowerArmL, -0.7853981633974483F, 0.0F, 0.0F);
            this.setRotateAngle(lowerArmR, -0.7853981633974483F, 0.0F, 0.0F);
            this.setRotateAngle(frontPawL, -1.1344640137963142F, 0.0F, 0.0F);
            this.setRotateAngle(frontPawR, -1.1344640137963142F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegL, 1.0471975511965976F, 0.0F, 0.0F);
            this.setRotateAngle(upperLegR, 1.0471975511965976F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegL, 0.8726646259971648F, 0.0F, 0.0F);
            this.setRotateAngle(lowerLegR, 0.8726646259971648F, 0.0F, 0.0F);
            this.setRotateAngle(backPawL, -0.3490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(backPawR, -0.3490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(tail1, -0.7285004297824331F, -0.014660765716752367F, 0.0F);
            this.setRotateAngle(tail2, 0.31869712141416456F, -0.014660765716752367F, 0.0F);
            this.setRotateAngle(tail3, 0.27314402793711257F, -0.014660765716752367F, 0.0F);
            this.setRotateAngle(tail4, 0.22759093446006054F, -0.014660765716752367F, 0.0F);

//            this.body.setPos(0.0F, -2.0F, -13.0F);
//            this.upperArmL.setPos(8.0F, 3.0F, -10.0F);
//            this.upperArmR.setPos(-8.0F, 3.0F, -10.0F);
//            this.upperLegL.setPos(8.0F, 1.5F, 15.0F);
//            this.upperLegR.setPos(-8.0F, 1.5F, 15.0F);
//            this.tail1.setPos(0.0F, -1.5F, 16.0F);
            this.root.y = 0.0F;

            this.upperArmL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.33161255787892263F;
            this.upperArmR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.33161255787892263F;

            this.upperLegL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 1.0471975511965976F;
            this.upperLegR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 1.0471975511965976F;

            this.tail1.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 0.014660765716752367F;
            this.tail1.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.014660765716752367F;
            this.tail2.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 0.014660765716752367F;
            this.tail2.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.014660765716752367F;
            this.tail3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 0.014660765716752367F;
            this.tail3.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.014660765716752367F;
            this.tail4.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 0.014660765716752367F;
            this.tail4.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.014660765716752367F;
        }
    }
}
