package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.AgateGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelAgateGolem - Androsa
 * Created using Tabula 7.0.0
 */
public class AgateGolemModel<T extends AgateGolemEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart face;
    public ModelPart upperArmL;
    public ModelPart upperLegL;
    public ModelPart upperArmR;
    public ModelPart upperLegR;

    public AgateGolemModel(ModelPart root) {
        this.root = root;
        this.face = root.getChild("face");
        this.upperArmL = root.getChild("upper_arm_left");
        this.upperArmR = root.getChild("upper_arm_right");
        this.upperLegL = root.getChild("upper_leg_left");
        this.upperLegR = root.getChild("upper_leg_right");
    }

    @Override
    public ModelPart root() {
        return root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition face = root.addOrReplaceChild("face", CubeListBuilder.create()
                        .texOffs(44, 0)
                        .addBox(-3.0F, -7.0F, -5.0F, 6, 8, 5),
                PartPose.offset(0.0F, -8.5F, 0.0F));
        face.addOrReplaceChild("head_top", CubeListBuilder.create()
                        .texOffs(120, 0)
                        .addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2),
                PartPose.offsetAndRotation(0.0F, -6.7F, -5.0F, -0.16388641676226753F, 0.17453292519943295F, 0.7853981633974483F));
        face.addOrReplaceChild("face_part_upper_left", CubeListBuilder.create()
                        .texOffs(102, 0)
                        .addBox(-2.5F, -7.0F, 0.0F, 6, 7, 3),
                PartPose.offsetAndRotation(2.0F, -5.2F, -4.5F, -0.2617993877991494F, 0.0F, 0.6981317007977318F));
        face.addOrReplaceChild("face_part_upper_right", CubeListBuilder.create()
                        .texOffs(117, 7)
                        .addBox(-3.5F, -7.0F, 0.0F, 6, 7, 3),
                PartPose.offsetAndRotation(-2.0F, -5.2F, -4.5F, -0.2617993877991494F, 0.0F, -0.6981317007977318F));
        face.addOrReplaceChild("face_part_lower_left", CubeListBuilder.create()
                        .texOffs(82, 0)
                        .addBox(-2.0F, 0.0F, 0.0F, 3, 6, 2),
                PartPose.offsetAndRotation(2.5F, -0.5F, -2.4F, 0.33161255787892263F, 0.0F, -0.8726646259971648F));
        face.addOrReplaceChild("face_part_lower_right", CubeListBuilder.create()
                        .texOffs(92, 0)
                        .addBox(-1.0F, 0.0F, 0.0F, 3, 6, 2),
                PartPose.offsetAndRotation(-2.5F, -0.5F, -2.4F, 0.33161255787892263F, 0.0F, 0.8726646259971648F));
        face.addOrReplaceChild("chin", CubeListBuilder.create()
                        .texOffs(15, 0)
                        .addBox(-2.0F, -2.0F, 0.0F, 4, 4, 1),
                PartPose.offsetAndRotation(0.0F, 1.0F, -4.9F, 0.0F, 0.0F, 0.7853981633974483F));
        face.addOrReplaceChild("chin_part_left", CubeListBuilder.create()
                        .texOffs(80, 8)
                        .addBox(-3.0F, 0.0F, 0.0F, 3, 8, 2),
                PartPose.offsetAndRotation(4.6F, -4.2F, -4.2F, 0.0F, 0.0F, 0.22689280275926282F));
        face.addOrReplaceChild("chin_part_right", CubeListBuilder.create()
                        .texOffs(90, 8)
                        .addBox(-3.0F, 0.0F, 0.0F, 3, 8, 2),
                PartPose.offsetAndRotation(-1.6F, -5.0F, -4.2F, 0.0F, 0.0F, -0.22689280275926282F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(66, 0)
                        .addBox(-2.0F, 0.0F, -4.0F, 4, 5, 4),
                PartPose.offsetAndRotation(0.0F, -12.5F, 0.0F, 0.7285004297824331F, 0.0F, 0.0F));

        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(38, 13)
                        .addBox(-6.5F, 0.0F, 0.0F, 13, 8, 6),
                PartPose.offset(0.0F, -9.0F, 0.0F));
        torso.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(0.0F, -3.0F, -3.0F, 9, 6, 6),
                PartPose.offsetAndRotation(2.5F, 0.0F, 3.0F, -0.7155849933176751F, 0.0F, 0.2792526803190927F));
        torso.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                        .texOffs(70, 21)
                        .addBox(-9.0F, -3.0F, -3.0F, 9, 6, 6),
                PartPose.offsetAndRotation(-2.5F, 0.0F, 3.0F, -0.7155849933176751F, 0.0F, -0.2792526803190927F));

        PartDefinition upperarmL = root.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(121, 17)
                        .addBox(-1.0F, 0.0F, -1.5F, 3, 12, 3),
                PartPose.offsetAndRotation(8.0F, -6.0F, 3.0F, 0.0F, 0.0F, -0.3490658503988659F));
        PartDefinition lowerarmL = upperarmL.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(26, 24)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4),
                PartPose.offsetAndRotation(0.6F, 10.5F, 0.2F, -0.45378560551852565F, 0.0F, 0.3490658503988659F));
        lowerarmL.addOrReplaceChild("arm_piece_front_left", CubeListBuilder.create()
                        .texOffs(42, 27)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 13, 2),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.08726646259971647F, 0.0F, 0.0F));
        lowerarmL.addOrReplaceChild("arm_piece_left_left", CubeListBuilder.create()
                        .texOffs(100, 26)
                        .addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4),
                PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.08726646259971647F));
        lowerarmL.addOrReplaceChild("arm_piece_right_left", CubeListBuilder.create()
                        .texOffs(54, 27)
                        .addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.08726646259971647F));
        lowerarmL.addOrReplaceChild("arm_piece_back_left", CubeListBuilder.create()
                        .texOffs(0, 28)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 13, 2),
                PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, 0.08726646259971647F, 0.0F, 0.0F));
        lowerarmL.addOrReplaceChild("fist_left", CubeListBuilder.create()
                        .texOffs(24, 16)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 3, 3),
                PartPose.offsetAndRotation(0.0F, 11.0F, 1.0F, 0.7853981633974483F, 1.3089969389957472F, 1.5707963267948966F));

        PartDefinition upperarmR = root.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(12, 28)
                        .addBox(-2.0F, 0.0F, -1.5F, 3, 12, 3),
                PartPose.offsetAndRotation(-8.0F, -6.0F, 3.0F, 0.0F, 0.0F, 0.3490658503988659F));
        PartDefinition lowerarmR = upperarmR.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(112, 32)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4),
                PartPose.offsetAndRotation(-0.6F, 10.5F, 0.2F, -0.45378560551852565F, 0.0F, -0.3490658503988659F));
        lowerarmR.addOrReplaceChild("arm_piece_front_right", CubeListBuilder.create()
                        .texOffs(78, 33)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 13, 2),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.08726646259971647F, 0.0F, 0.0F));
        lowerarmR.addOrReplaceChild("arm_piece_left_right", CubeListBuilder.create()
                        .texOffs(66, 33)
                        .addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4),
                PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.08726646259971647F));
        lowerarmR.addOrReplaceChild("arm_piece_right_right", CubeListBuilder.create()
                        .texOffs(90, 39)
                        .addBox(-2.0F, 0.0F, -2.0F, 2, 13, 4),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.08726646259971647F));
        lowerarmR.addOrReplaceChild("arm_piece_back_right", CubeListBuilder.create()
                        .texOffs(22, 41)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 13, 2),
                PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, 0.08726646259971647F, 0.0F, 0.0F));
        lowerarmR.addOrReplaceChild("fist_right", CubeListBuilder.create()
                        .texOffs(34, 42)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 3, 3),
                PartPose.offsetAndRotation(0.0F, 11.0F, 1.0F, 0.7853981633974483F, 1.3089969389957472F, 1.5707963267948966F));

        root.addOrReplaceChild("stomach", CubeListBuilder.create()
                        .texOffs(95, 13)
                        .addBox(-4.0F, 0.0F, 0.0F, 8, 8, 5),
                PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition leftleg = root.addOrReplaceChild("upper_leg_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5),
                PartPose.offsetAndRotation(-3.0F, 6.3F, 1.9F, -0.3490658503988659F, 0.4363323129985824F, 0.0F));
        leftleg.addOrReplaceChild("lower_leg_left", CubeListBuilder.create()
                        .texOffs(20, 0)
                        .addBox(-3.0F, 0.0F, -3.0F, 6, 10, 6),
                PartPose.offsetAndRotation(0.0F, 8.2F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition rightleg = root.addOrReplaceChild("upper_leg_right", CubeListBuilder.create()
                        .texOffs(0, 43)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5),
                PartPose.offsetAndRotation(3.0F, 6.3F, 1.9F, -0.3490658503988659F, -0.4363323129985824F, 0.0F));
        rightleg.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(40, 44)
                        .addBox(-3.0F, 0.0F, -3.0F, 6, 10, 6),
                PartPose.offsetAndRotation(0.0F, 8.2F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 138, 64);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.face.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.face.xRot = headPitch / (180F / (float) Math.PI);

        this.upperArmR.zRot = 0.0F;
        this.upperArmL.zRot = 0.0F;
        this.upperArmR.xRot = 0.0F;
        this.upperArmL.xRot = 0.0F;
        this.upperArmR.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.3490658503988659F;
        this.upperArmL.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.3490658503988659F;
        this.upperArmR.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.upperArmR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.upperLegL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount - 0.3490658503988659F;
        this.upperLegR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount - 0.3490658503988659F;
    }
}
