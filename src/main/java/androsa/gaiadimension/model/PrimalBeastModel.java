package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.PrimalBeastEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelPrimalBeast - Androsa
 * Created using Tabula 7.0.0
 */
public class PrimalBeastModel<T extends PrimalBeastEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart armL;
    public ModelPart armR;
    public ModelPart fingersL;
    public ModelPart fingersR;
    public ModelPart legL;
    public ModelPart legR;
    public ModelPart tailtop;
    public ModelPart tailBottom;

    public PrimalBeastModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        ModelPart torso = root.getChild("torso");
        this.armL = torso.getChild("arm_left");
        this.fingersL = armL.getChild("forearm_left").getChild("fingers_left");
        this.armR = torso.getChild("arm_right");
        this.fingersR = armR.getChild("forearm_right").getChild("fingers_right");
        ModelPart stomach = root.getChild("stomach");
        this.legL = stomach.getChild("leg_left");
        this.legR = stomach.getChild("leg_right");
        this.tailtop = stomach.getChild("tail_segment_top");
        this.tailBottom = stomach.getChild("tail_segment_bottom");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(34, 16)
                        .addBox(-4.0F, -7.8F, -6.0F, 8, 9, 6),
                PartPose.offset(0.0F, -3.0F, -3.0F));

        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-6.5F, 0.0F, -3.5F, 13, 11, 7),
                PartPose.offsetAndRotation(0.0F, -5.0F, -3.5F, 0.4886921905584123F, 0.0F, 0.0F));
        PartDefinition leftarm = torso.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(73, 11)
                        .addBox(0.0F, 0.0F, -2.5F, 5, 13, 5),
                PartPose.offsetAndRotation(6.5F, 0.9F, 0.0F, -0.33161255787892263F, 0.0F, 0.0F));
        PartDefinition leftforearm = leftarm.addOrReplaceChild("forearm_left", CubeListBuilder.create()
                        .texOffs(57, 26)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5),
                PartPose.offsetAndRotation(2.4F, 11.5F, 0.4F, -0.6108652381980153F, 0.0F, 0.0F));
        leftforearm.addOrReplaceChild("fingers_left", CubeListBuilder.create()
                        .texOffs(78, 0)
                        .addBox(0.0F, 0.0F, -2.4F, 2, 5, 5),
                PartPose.offsetAndRotation(0.5F, 12.0F, 0.0F, 0.0F, 0.0F, 0.3141592653589793F));
        PartDefinition rightarm = torso.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(-5.0F, 0.0F, -2.5F, 5, 13, 5),
                PartPose.offsetAndRotation(-6.5F, 0.9F, 0.0F, -0.33161255787892263F, 0.0F, 0.0F));
        PartDefinition rightforearm = rightarm.addOrReplaceChild("forearm_right", CubeListBuilder.create()
                        .texOffs(15, 31)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5),
                PartPose.offsetAndRotation(-2.4F, 11.5F, 0.4F, -0.6108652381980153F, 0.0F, 0.0F));
        rightforearm.addOrReplaceChild("fingers_right", CubeListBuilder.create()
                        .texOffs(20, 18)
                        .addBox(0.0F, 0.0F, -2.4F, 2, 5, 5),
                PartPose.offsetAndRotation(-2.5F, 12.0F, 0.0F, 0.0F, 0.0F, -0.3141592653589793F));

        PartDefinition stomach = root.addOrReplaceChild("stomach", CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(-6.0F, 0.0F, 0.0F, 12, 9, 7),
                PartPose.offset(0.0F, 3.0F, -2.2F));
        stomach.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(35, 38)
                        .addBox(-3.0F, 0.0F, -3.0F, 6, 13, 6),
                PartPose.offset(3.1F, 8.0F, 3.5F));
        stomach.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(71, 38)
                        .addBox(-3.0F, 0.0F, -3.0F, 6, 13, 6),
                PartPose.offset(-2.9F, 8.0F, 3.5F));
        PartDefinition tail = stomach.addOrReplaceChild("tail_segment_top", CubeListBuilder.create()
                        .texOffs(49, 47)
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 4, 10),
                PartPose.offsetAndRotation(0.0F, 4.0F, 7.0F, -0.593411945678072F, 0.0F, 0.0F));
        tail.addOrReplaceChild("tail_segment_bottom", CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(-1.5F, -2.0F, 0.0F, 3, 2, 9),
                PartPose.offsetAndRotation(0.0F, 3.6F, 9.4F, 0.33161255787892263F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 96, 64);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.armR.zRot = 0.0F;
        this.armL.zRot = 0.0F;
        this.armR.xRot = -0.33161255787892263F;
        this.armL.xRot = -0.33161255787892263F;
        this.armR.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.armL.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.armR.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        this.armL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount - 0.33161255787892263F;
        this.armR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount - 0.33161255787892263F;

        this.fingersL.zRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F + 0.3F;
        this.fingersR.zRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * -0.15F - 0.3F;

        this.legL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.legR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.tailtop.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tailtop.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.tailBottom.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tailBottom.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
    }
}
