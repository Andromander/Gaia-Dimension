package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.LesserShockshooter;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelLesserShockshooter - Androsa
 * Created using Tabula 7.0.0
 */
public class LesserShockshooterModel<T extends LesserShockshooter> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart armL;
    public ModelPart armR;
    public ModelPart legL;
    public ModelPart legR;

    public LesserShockshooterModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.armL = root.getChild("arm_left");
        this.armR = root.getChild("arm_right");
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

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("headpart", CubeListBuilder.create()
                        .texOffs(18, 19)
                        .addBox(-2.5F, -2.0F, 0.0F, 5, 2, 10),
                PartPose.offsetAndRotation(0.0F, -4.3F, -3.0F, 1.3089969389957472F, 0.0F, 0.017453292519943295F));
        head.addOrReplaceChild("ear_left", CubeListBuilder.create()
                        .texOffs(62, 15)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 8, 3),
                PartPose.offsetAndRotation(3.5F, -1.9F, 0.0F, 2.356194490192345F, 0.3665191429188092F, 0.0F));
        head.addOrReplaceChild("ear_right", CubeListBuilder.create()
                        .texOffs(70, 15)
                        .addBox(0.0F, 0.0F, 0.0F, 1, 8, 3),
                PartPose.offsetAndRotation(-3.9F, -1.9F, 0.0F, 2.356194490192345F, -0.3665191429188092F, 0.0F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(-2.0F, -2.0F, -1.5F, 4, 2, 3),
                PartPose.ZERO);

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(28, 0)
                        .addBox(-3.5F, 0.0F, -2.5F, 7, 12, 5),
                PartPose.ZERO);
        body.addOrReplaceChild("cape_left", CubeListBuilder.create()
                        .texOffs(34, 30)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 1, 14),
                PartPose.offsetAndRotation(2.0F, 1.5F, 2.0F, 0.7853981633974483F, 0.7853981633974483F, 0.0F));
        body.addOrReplaceChild("cape_mid", CubeListBuilder.create()
                        .texOffs(0, 31)
                        .addBox(-3.5F, 0.0F, 0.0F, 7, 1, 17),
                PartPose.offsetAndRotation(0.0F, 1.5F, 2.5F, -1.3089969389957472F, 0.0F, 0.0F));
        body.addOrReplaceChild("cape_right", CubeListBuilder.create()
                        .texOffs(34, 45)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 1, 14),
                PartPose.offsetAndRotation(-2.0F, 1.5F, 2.0F, 0.7853981633974483F, -0.7853981633974483F, 0.0F));

        root.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(14, 14)
                        .addBox(0.0F, 0.0F, -2.0F, 3, 11, 4),
                PartPose.offset(3.5F, 0.5F, 0.0F));
        root.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(48, 15)
                        .addBox(-3.0F, 0.0F, -2.0F, 3, 11, 4),
                PartPose.offset(-3.5F, 0.5F, 0.0F));
        root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3),
                PartPose.offset(2.0F, 12.0F, 0.0F));
        root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3),
                PartPose.offset(-2.0F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 80, 64);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        float f = Mth.sin(this.attackTime * (float) Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float) Math.PI);
        this.armR.zRot = 0.0F;
        this.armL.zRot = 0.0F;
        this.armR.yRot = -(0.1F - f * 0.6F);
        this.armL.yRot = 0.1F - f * 0.6F;
        this.armR.xRot = 0.0F;
        this.armL.xRot = 0.0F;
        this.armR.xRot -= f * 1.2F - f1 * 0.4F;
        this.armL.xRot -= f * 1.2F - f1 * 0.4F;
        this.armR.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armL.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armR.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.armR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;

        this.legR.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.legL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
    }
}
