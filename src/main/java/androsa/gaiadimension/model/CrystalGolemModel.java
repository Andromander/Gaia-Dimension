package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.CrystalGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelCrystalGolem - Androsa
 * Created using Tabula 7.0.0
 */
public class CrystalGolemModel<T extends CrystalGolemEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart face;
    public ModelPart upperArmL;
    public ModelPart upperArmR;
    public ModelPart legL;
    public ModelPart legR;

    public CrystalGolemModel(ModelPart root) {
        this.root = root;
        this.face = root.getChild("face");
        this.upperArmL = root.getChild("upper_arm_left");
        this.upperArmR = root.getChild("upper_arm_right");
        this.legL = root.getChild("leg_left");
        this.legR = root.getChild("leg_right");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition face = root.addOrReplaceChild("face", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -7.0F, -5.0F, 6, 8, 5),
                PartPose.offset(0.0F, -9.0F, -1.0F));
        face.addOrReplaceChild("face_top", CubeListBuilder.create()
                        .texOffs(68, 0)
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 2, 5),
                PartPose.offsetAndRotation(0.0F, -7.8F, -4.7F, -0.06981317007977318F, 0.0F, 0.0F));
        face.addOrReplaceChild("face_left", CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(-2.0F, 0.0F, -2.5F, 2, 7, 5),
                PartPose.offsetAndRotation(-1.8F, -6.5F, -2.4F, 0.0F, 0.0698131700797732F, 0.0F));
        face.addOrReplaceChild("face_right", CubeListBuilder.create()
                        .texOffs(54, 0)
                        .addBox(0.0F, 0.0F, -2.5F, 2, 7, 5),
                PartPose.offsetAndRotation(1.8F, -6.5F, -2.4F, 0.0F, -0.06981317007977318F, 0.0F));
        face.addOrReplaceChild("face_bottom", CubeListBuilder.create()
                        .texOffs(88, 0)
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 7, 1),
                PartPose.offset(0.0F, -6.6F, -0.2F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(22, 0)
                        .addBox(-2.5F, 0.0F, -4.0F, 5, 5, 4),
                PartPose.offsetAndRotation(0.0F, -12.5F, -1.0F, 0.7285004297824331F, 0.0F, 0.0F));

        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(94, 2)
                        .addBox(-7.0F, 0.0F, -3.0F, 14, 8, 6),
                PartPose.offset(0.0F, -9.0F, 0.0F));
        torso.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                        .texOffs(15, 9)
                        .addBox(-4.0F, 0.0F, -3.5F, 4, 3, 7),
                PartPose.offsetAndRotation(-6.3F, -0.2F, 0.0F, 0.0F, 0.0F, -0.20943951023931953F));
        torso.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                        .texOffs(30, 12)
                        .addBox(0.0F, 0.0F, -3.5F, 4, 3, 7),
                PartPose.offsetAndRotation(6.3F, -0.2F, 0.0F, 0.0F, 0.0F, 0.20943951023931953F));
        torso.addOrReplaceChild("back_spike_1", CubeListBuilder.create()
                        .texOffs(42, 16)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 2, 10),
                PartPose.offsetAndRotation(-4.0F, 0.0F, 1.2F, 0.6981317007977318F, -0.3490658503988659F, 0.0F));
        torso.addOrReplaceChild("back_spike_2", CubeListBuilder.create()
                        .texOffs(85, 16)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 2, 10),
                PartPose.offsetAndRotation(4.0F, 0.0F, 1.2F, 0.6981317007977318F, 0.3490658503988659F, 0.0F));
        torso.addOrReplaceChild("back_spike_3", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 2, 6),
                PartPose.offsetAndRotation(-2.5F, 5.0F, 2.0F, 0.5235987755982988F, -0.17453292519943295F, 0.0F));
        torso.addOrReplaceChild("back_spike_4", CubeListBuilder.create()
                        .texOffs(101, 16)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 2, 6),
                PartPose.offsetAndRotation(2.5F, 5.0F, 2.0F, 0.5235987755982988F, 0.17453292519943295F, 0.0F));

        PartDefinition leftupperarm = root.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(13, 19)
                        .addBox(-1.5F, 0.0F, -2.5F, 5, 10, 5),
                PartPose.offsetAndRotation(6.5F, -7.5F, 0.0F, 0.0F, 0, -0.17453292519943295F));
        PartDefinition leftlowerarm = leftupperarm.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(114, 19)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 11, 5),
                PartPose.offsetAndRotation(1.0F, 9.6F, -0.1F, 0.0F, 0.7853981633974483F, 0.17453292519943295F));
        leftlowerarm.addOrReplaceChild("fingers_left", CubeListBuilder.create()
                        .texOffs(33, 23)
                        .addBox(-2.0F, 0.0F, -2.5F, 2, 4, 5),
                PartPose.offsetAndRotation(2.0F, 11.0F, 0.1F, 0.0F, 0.0F, 0.3490658503988659F));
        leftlowerarm.addOrReplaceChild("thumb_left", CubeListBuilder.create()
                        .texOffs(49, 0)
                        .addBox(-1.0F, 0.0F, -2.0F, 2, 3, 2),
                PartPose.offset(-1.5F, 11.0F, 0.0F));

        PartDefinition rightupperarm = root.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(63, 26)
                        .addBox(-3.5F, 0.0F, -2.5F, 5, 10, 5),
                PartPose.offsetAndRotation(-6.5F, -7.5F, 0.0F, 0.0F, 0, 0.17453292519943295F));
        PartDefinition rightlowerarm = rightupperarm.addOrReplaceChild("lower_arm_right", CubeListBuilder.create()
                        .texOffs(42, 28)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 11, 5),
                PartPose.offsetAndRotation(-1.0F, 9.6F, -0.1F, 0.0F, -0.7853981633974483F, -0.17453292519943295F));
        rightlowerarm.addOrReplaceChild("fingers_right", CubeListBuilder.create()
                        .texOffs(83, 28)
                        .addBox(0.0F, 0.0F, -2.5F, 2, 4, 5),
                PartPose.offsetAndRotation(-2.0F, 11.0F, 0.1F, 0.0F, 0.0F, -0.3490658503988659F));
        rightlowerarm.addOrReplaceChild("thumb_right", CubeListBuilder.create()
                        .texOffs(63, 0)
                        .addBox(-1.0F, 0.0F, -2.0F, 2, 3, 2),
                PartPose.offset(1.5F, 11.0F, 0.0F));

        root.addOrReplaceChild("stomach", CubeListBuilder.create()
                        .texOffs(63, 11)
                        .addBox(-5.5F, 0.0F, -2.1F, 11, 10, 5),
                PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition leftleg = root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(97, 30)
                        .addBox(-2.5F, 0.0F, -2.6F, 5, 16, 5),
                PartPose.offset(2.9F, 8.0F, 0.4F));
        leftleg.addOrReplaceChild("foot_left", CubeListBuilder.create()
                        .texOffs(0, 30)
                        .addBox(-2.0F, 0.0F, -4.0F, 4, 3, 4),
                PartPose.offset(0.0F, 14.0F, -1.0F));

        PartDefinition rightleg = root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(11, 34)
                        .addBox(-2.5F, 0.0F, -2.6F, 5, 16, 5),
                PartPose.offset(-2.9F, 8.0F, 0.4F));
        rightleg.addOrReplaceChild("foot_right", CubeListBuilder.create()
                        .texOffs(117, 35)
                        .addBox(-2.0F, 0.0F, -4.0F, 4, 3, 4),
                PartPose.offset(0.0F, 14.0F, -1.0F));

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
        this.upperArmR.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.2F;
        this.upperArmL.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.2F;
        this.upperArmR.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.upperArmL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.upperArmR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.legL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.legR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
    }
}
