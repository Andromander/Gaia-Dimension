package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MutantGrowthExtractorEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelGrowthExtractor - Androsa
 * Created using Tabula 7.0.0
 */
public class GrowthExtractorModel<T extends MutantGrowthExtractorEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart upperArmTL;
    public ModelPart upperArmTR;
    public ModelPart upperArmBL;
    public ModelPart upperArmBR;
    public ModelPart legL;
    public ModelPart legR;

    public GrowthExtractorModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.upperArmTL = root.getChild("upper_arm_top_left");
        this.upperArmTR = root.getChild("upper_arm_top_right");
        this.upperArmBL = root.getChild("upper_arm_bottom_left");
        this.upperArmBR = root.getChild("upper_arm_bottom_right");
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
                        .addBox(-2.5F, -4.0F, -5.0F, 5, 5, 5),
                PartPose.offset(0.0F, 5.0F, 0.0F));
        head.addOrReplaceChild("eye_left", CubeListBuilder.create()
                        .texOffs(0, 29)
                        .addBox(0.0F, -1.5F, -1.5F, 2, 3, 3),
                PartPose.offset(1.2F, -2.1F, -4.0F));
        head.addOrReplaceChild("eye_right", CubeListBuilder.create()
                        .texOffs(10, 29)
                        .addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3),
                PartPose.offset(-1.2F, -2.1F, -4.0F));
        PartDefinition topnozzle = head.addOrReplaceChild("upper_nozzle", CubeListBuilder.create()
                        .texOffs(26, 10)
                        .addBox(-1.0F, -1.0F, -4.0F, 2, 2, 4),
                PartPose.offsetAndRotation(0.0F, 0.0F, -4.5F, 0.5235987755982988F, 0.0F, 0.0F));
        PartDefinition lownozzle = topnozzle.addOrReplaceChild("lower_nozzle", CubeListBuilder.create()
                        .texOffs(20, 29)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2),
                PartPose.offsetAndRotation(0.0F, -0.5F, -3.2F, -0.6108652381980153F, 0.0F, 0.0F));
        lownozzle.addOrReplaceChild("suction", CubeListBuilder.create()
                        .texOffs(24, 32)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4),
                PartPose.offset(0.0F, 3.6F, 0.0F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(20, 0)
                        .addBox(-2.0F, -1.5F, 0.0F, 4, 3, 4),
                PartPose.offsetAndRotation(0.0F, 3.0F, -1.1F, -0.8196066167365371F, 0.0F, 0.0F));
        root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(36, 0)
                        .addBox(-3.5F, -9.0F, -5.0F, 7, 9, 5),
                PartPose.offsetAndRotation(0.0F, 13.0F, 5.6F, 0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition topleftupperarm = root.addOrReplaceChild("upper_arm_top_left", CubeListBuilder.create()
                        .texOffs(29, 26)
                        .addBox(-5.0F, -1.5F, -1.5F, 5, 3, 3),
                PartPose.offsetAndRotation(-2.5F, 12.5F, 3.2F, 0.0F, 0.0F, -0.2617993877991494F));
        topleftupperarm.addOrReplaceChild("lower_arm_top_left", CubeListBuilder.create()
                        .texOffs(40, 42)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3),
                PartPose.offsetAndRotation(-6.5F, 0.0F, 1.5F, -1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition toprightupperarm = root.addOrReplaceChild("upper_arm_top_right", CubeListBuilder.create()
                        .texOffs(45, 26)
                        .addBox(0.0F, -1.5F, -1.5F, 5, 3, 3),
                PartPose.offsetAndRotation(2.5F, 12.5F, 3.2F, 0.0F, 0.0F, 0.2617993877991494F));
        toprightupperarm.addOrReplaceChild("lower_arm_top_right", CubeListBuilder.create()
                        .texOffs(0, 45)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3),
                PartPose.offsetAndRotation(6.5F, 0.0F, 1.5F, -1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition bottomleftupperarm = root.addOrReplaceChild("upper_arm_bottom_left", CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(-5.0F, -1.5F, -1.5F, 5, 3, 3),
                PartPose.offsetAndRotation(-3.0F, 7.5F, 2.1F, 0.0F, 0.0F, -0.08726646259971647F));
        bottomleftupperarm.addOrReplaceChild("lower_arm_bottom_left", CubeListBuilder.create()
                        .texOffs(16, 38)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3),
                PartPose.offsetAndRotation(-6.5F, 0.0F, 1.5F, -1.5707963267948966F, 0.0F, 0.0F));

        PartDefinition bottomrightupperarm = root.addOrReplaceChild("upper_arm_bottom_right", CubeListBuilder.create()
                        .texOffs(16, 23)
                        .addBox(0.0F, -1.5F, -1.5F, 5, 3, 3),
                PartPose.offsetAndRotation(3.0F, 7.5F, 2.1F, 0.0F, 0.0F, 0.08726646259971647F));
        bottomrightupperarm.addOrReplaceChild("lower_arm_bottom_right", CubeListBuilder.create()
                        .texOffs(28, 38)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3),
                PartPose.offsetAndRotation(6.5F, 0.0F, 1.5F, -1.5707963267948966F, 0.0F, 0.0F));

        root.addOrReplaceChild("stomach", CubeListBuilder.create()
                        .texOffs(0, 10)
                        .addBox(-5.0F, 0.0F, 0.0F, 10, 7, 6),
                PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition leftleg = root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(44, 17)
                        .addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6),
                PartPose.offsetAndRotation(3.4F, 18.0F, 2.0F, 0.2617993877991494F, -0.4363323129985824F, 0.0F));
        leftleg.addOrReplaceChild("foot_left", CubeListBuilder.create()
                        .texOffs(0, 35)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4),
                PartPose.offsetAndRotation(0.0F, -1.3F, -5.0F, -0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition rightleg = root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(32, 14)
                        .addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6),
                PartPose.offsetAndRotation(-3.4F, 18.0F, 2.0F, 0.2617993877991494F, 0.4363323129985824F, 0.0F));
        rightleg.addOrReplaceChild("foot_right", CubeListBuilder.create()
                        .texOffs(40, 32)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4),
                PartPose.offsetAndRotation(0.0F, -1.3F, -5.0F, -0.2617993877991494F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.legL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.2617993877991494F;
        this.legR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.2617993877991494F;

        this.upperArmTR.yRot = Mth.sin(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.upperArmTL.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.upperArmBL.yRot = Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.upperArmBR.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
