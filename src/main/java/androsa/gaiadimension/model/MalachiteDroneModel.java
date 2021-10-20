package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MalachiteDroneEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * MalachiteDroneModel - Androsa
 * Created using Tabula 7.0.0
 */
public class MalachiteDroneModel<T extends MalachiteDroneEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart upperArmL;
    public ModelPart upperArmR;
    public ModelPart upperLegL;
    public ModelPart upperLegR;

    public MalachiteDroneModel(ModelPart root) {
        this.root = root;
        this.upperArmL = root.getChild("upper_arm_left");
        this.upperArmR = root.getChild("upper_arm_right");
        this.upperLegL = root.getChild("upper_leg_left");
        this.upperLegR = root.getChild("upper_leg_right");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 24)
                        .addBox(-3.5F, 0.0F, 0.0F, 7, 3, 11),
                PartPose.offset(0.0F, 0.0F, -3.5F));

        PartDefinition cap = root.addOrReplaceChild("cap_bottom", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-9.0F, -6.0F, -9.0F, 18, 6, 18),
                PartPose.ZERO);
        cap.addOrReplaceChild("cap_top", CubeListBuilder.create()
                        .texOffs(36, 24)
                        .addBox(-5.5F, -3.0F, -5.5F, 11, 3, 11),
                PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition leftupperarm = root.addOrReplaceChild("upper_arm_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 11, 2),
                PartPose.offset(4.0F, 0.0F, -1.5F));
        PartDefinition leftlowerarm = leftupperarm.addOrReplaceChild("lower_arm_left", CubeListBuilder.create()
                        .texOffs(74, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 11, 2),
                PartPose.offsetAndRotation(0.1F, 10.5F, 0.5F, -0.6981317007977318F, 0.0F, 0.0F));
        leftlowerarm.addOrReplaceChild("claw_front_left", CubeListBuilder.create()
                        .texOffs(81, 12)
                        .addBox(0.0F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(0.5F, 10.5F, -1.0F, 0.0F, 0.0F, 0.5235987755982988F));
        leftlowerarm.addOrReplaceChild("claw_back_left", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(0.0F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(0.5F, 10.5F, 1.0F, 0.0F, 0.0F, 0.5235987755982988F));

        PartDefinition rightupperarm = root.addOrReplaceChild("upper_arm_right", CubeListBuilder.create()
                        .texOffs(8, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 11, 2),
                PartPose.offset(-4.0F, 0.0F, -1.5F));
        PartDefinition rightlowerarm = rightupperarm.addOrReplaceChild("", CubeListBuilder.create()
                        .texOffs(72, 13)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 11, 2),
                PartPose.offsetAndRotation(-0.1F, 10.5F, 0.5F, -0.6981317007977318F, 0.0F, 0.0F));
        rightlowerarm.addOrReplaceChild("claw_front_right", CubeListBuilder.create()
                        .texOffs(4, 13)
                        .addBox(-1.0F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-0.5F, 10.5F, -1.0F, 0.0F, 0.0F, -0.5235987755982988F));
        rightlowerarm.addOrReplaceChild("claw_back_right", CubeListBuilder.create()
                        .texOffs(8, 13)
                        .addBox(-1.0F, 0.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-0.5F, 10.5F, 1.0F, 0.0F, 0.0F, -0.5235987755982988F));

        PartDefinition leftupperleg = root.addOrReplaceChild("upper_leg_left", CubeListBuilder.create()
                        .texOffs(54, 0)
                        .addBox(0.0F, 0.0F, -1.5F, 2, 10, 3),
                PartPose.offsetAndRotation(2.0F, 1.0F, 3.5F, 0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition leftlowerleg = leftupperleg.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(0, 38)
                        .addBox(0.1F, 0.0F, 0.0F, 2, 14, 2),
                PartPose.offsetAndRotation(0.0F, 8.9F, -0.2F, -0.5235987755982988F, 0.0F, 0.0F));
        leftlowerleg.addOrReplaceChild("foot_left", CubeListBuilder.create()
                        .texOffs(25, 24)
                        .addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4),
                PartPose.offsetAndRotation(1.1F, 13.0F, 2.0F, 0.17453292519943295F, 0.0F, 0.0F));

        PartDefinition rightupperleg = root.addOrReplaceChild("upper_leg_right", CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-2.0F, 0.0F, -1.5F, 2, 10, 3),
                PartPose.offsetAndRotation(-2.0F, 1.0F, 3.5F, 0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition rightlowerleg = rightupperleg.addOrReplaceChild("lower_leg_right", CubeListBuilder.create()
                        .texOffs(8, 38)
                        .addBox(-2.1F, 0.0F, 0.0F, 2, 14, 2),
                PartPose.offsetAndRotation(0.0F, 8.9F, -0.2F, -0.5235987755982988F, 0.0F, 0.0F));
        rightlowerleg.addOrReplaceChild("foot_right", CubeListBuilder.create()
                        .texOffs(69, 26)
                        .addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4),
                PartPose.offsetAndRotation(-1.1F, 13.0F, 2.0F, 0.17453292519943295F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 86, 64);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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

        this.upperLegR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.3490658503988659F;
        this.upperLegL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.3490658503988659F;
    }
}
