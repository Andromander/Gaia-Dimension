package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ArchaicWarriorEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

/**
 * ModelArchaicWarrior - Androsa
 * Created using Tabula 7.0.0
 */
public class ArchaicWarriorModel<T extends ArchaicWarriorEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart bipedRightArm;
    public ModelPart bipedRightLeg;
    public ModelPart bipedLeftLeg;
    public ModelPart bipedHeadwear;
    public ModelPart bipedLeftArm;
    public ModelPart bipedHead;
    public ModelPart bipedLeftArmwear;
    public ModelPart bipedRightArmwear;
    public ModelPart bipedRightLegwear;
    public ModelPart bipedLeftLegwear;

    public ArchaicWarriorModel(ModelPart root) {
        this.root = root;
        this.bipedHead = root.getChild("head");
        this.bipedHeadwear = root.getChild("headwear");
        this.bipedLeftArm = root.getChild("arm_left");
        this.bipedLeftArmwear = root.getChild("armwear_left");
        this.bipedRightArm = root.getChild("arm_right");
        this.bipedRightArmwear = root.getChild("armwear_right");
        this.bipedLeftLeg = root.getChild("leg_left");
        this.bipedLeftLegwear = root.getChild("legwear_left");
        this.bipedRightLeg = root.getChild("leg_right");
        this.bipedRightLegwear = root.getChild("legwear_right");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(24, 16)
                        .addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8),
                PartPose.ZERO);
        root.addOrReplaceChild("headwear", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, new CubeDeformation(0.5F)),
                PartPose.ZERO);
        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4),
                PartPose.ZERO);
        root.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                        .texOffs(52, 14)
                        .addBox(0.0F, 0.0F, -2.0F, 4, 2, 4),
                PartPose.offset(4.5F, -1.5F, 0.0F));
        root.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(56, 0)
                        .addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2)
                        .mirror(),
                PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.10000736613927509F));
        root.addOrReplaceChild("armwear_left", CubeListBuilder.create()
                        .texOffs(8, 0)
                        .addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, new CubeDeformation(0.5F))
                        .mirror(),
                PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.10000736613927509F));
        root.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                        .texOffs(52, 28)
                        .addBox(-4.0F, 0.0F, -2.0F, 4, 2, 4),
                PartPose.offset(-4.5F, -1.5F, 0.0F));
        root.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2),
                PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.10000736613927509F));
        root.addOrReplaceChild("armwear_right", CubeListBuilder.create()
                        .texOffs(8, 30)
                        .addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, new CubeDeformation(0.5F))
                        .mirror(),
                PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.10000736613927509F));
        root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2),
                PartPose.offset(2.0F, 12.0F, 0.1F));
        root.addOrReplaceChild("legwear_left", CubeListBuilder.create()
                        .texOffs(24, 32)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, new CubeDeformation(0.5F)),
                PartPose.offset(2.0F, 12.0F, 0.1F));
        root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(16, 30)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2),
                PartPose.offset(-2.0F, 12.0F, 0.1F));
        root.addOrReplaceChild("legwear_right", CubeListBuilder.create()
                        .texOffs(0, 30)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, new CubeDeformation(0.5F)),
                PartPose.offset(-2.0F, 12.0F, 0.1F));

        return LayerDefinition.create(mesh, 70, 50);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.bipedHead.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.bipedHead.xRot = headPitch / (180F / (float) Math.PI);

        this.bipedHeadwear.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.bipedHeadwear.xRot = headPitch / (180F / (float) Math.PI);

        float f = Mth.sin(this.attackTime * (float)Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
        this.bipedRightArm.zRot = 0.0F;
        this.bipedLeftArm.zRot = 0.0F;
        this.bipedRightArm.yRot = -(0.1F - f * 0.6F);
        this.bipedLeftArm.yRot = 0.1F - f * 0.6F;
        this.bipedRightArm.xRot = 0.0F;
        this.bipedLeftArm.xRot = 0.0F;
        this.bipedRightArm.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArm.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedRightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightArmwear.zRot = 0.0F;
        this.bipedLeftArmwear.zRot = 0.0F;
        this.bipedRightArmwear.yRot = -(0.1F - f * 0.6F);
        this.bipedLeftArmwear.yRot = 0.1F - f * 0.6F;
        this.bipedRightArmwear.xRot = 0.0F;
        this.bipedLeftArmwear.xRot = 0.0F;
        this.bipedRightArmwear.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArmwear.xRot -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArmwear.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArmwear.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArmwear.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArmwear.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArmwear.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedRightArmwear.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.bipedRightLegwear.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.bipedLeftLegwear.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
    }
}
