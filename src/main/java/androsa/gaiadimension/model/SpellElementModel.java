package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.SpellElementEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelSpellElement - Androsa
 * Created using Tabula 7.0.0
 */
public class SpellElementModel<T extends SpellElementEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart scytheL;
    public ModelPart scytheR;

    public SpellElementModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.scytheL = root.getChild("scythe_left");
        this.scytheR = root.getChild("scythe_right");
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
                        .addBox(-2.5F, -5.0F, -1.5F, 5, 5, 3),
                PartPose.ZERO);
        head.addOrReplaceChild("chin", CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 2, 1),
                PartPose.offset(0.0F, 0.0F, -1.5F));
        PartDefinition lefthorn = head.addOrReplaceChild("lower_horn_left", CubeListBuilder.create()
                        .texOffs(16, 5)
                        .addBox(0.0F, 0.0F, -0.5F, 3, 4, 1),
                PartPose.offsetAndRotation(0.5F, -4.6F, 0.0F, 0.0F, 0.0F, -0.4553564018453205F));
        lefthorn.addOrReplaceChild("upper_horn_left", CubeListBuilder.create()
                        .texOffs(0, 8)
                        .addBox(-2.0F, 0.0F, -0.5F, 2, 4, 1),
                PartPose.offsetAndRotation(1.6F, 1.5F, 0.1F, 0.0F, 0.0F, -2.367539130330308F));
        PartDefinition righthorn = head.addOrReplaceChild("lower_horn_right", CubeListBuilder.create()
                        .texOffs(52, 3)
                        .addBox(-3.0F, 0.0F, -0.5F, 3, 4, 1),
                PartPose.offsetAndRotation(-0.5F, -4.6F, 0.0F, 0.0F, 0.0F, 0.4553564018453205F));
        righthorn.addOrReplaceChild("upper_horn_right", CubeListBuilder.create()
                        .texOffs(46, 5)
                        .addBox(0.0F, 0.0F, -0.5F, 2, 4, 1),
                PartPose.offsetAndRotation(-1.6F, 1.5F, 0.1F, 0.0F, 0.0F, 2.367539130330308F));

        PartDefinition upperbody = root.addOrReplaceChild("upper_body", CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 4, 1),
                PartPose.offset(0.0F, 8.4F, 0.0F));
        upperbody.addOrReplaceChild("lower_body", CubeListBuilder.create()
                        .texOffs(6, 10)
                        .addBox(-4.0F, -5.0F, 0.0F, 8, 5, 1),
                PartPose.ZERO);
        upperbody.addOrReplaceChild("core", CubeListBuilder.create()
                        .texOffs(50, 8)
                        .addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2),
                PartPose.offset(0.0F, -1.5F, -0.1F));

        PartDefinition leftscythe = root.addOrReplaceChild("scythe_left", CubeListBuilder.create()
                        .texOffs(36, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 3, 11, 2),
                PartPose.offsetAndRotation(8.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.08726646259971647F));
        leftscythe.addOrReplaceChild("elbow_left", CubeListBuilder.create()
                        .texOffs(50, 12)
                        .addBox(0.0F, -2.0F, -1.0F, 1, 2, 2),
                PartPose.offset(2.0F, 0.0F, 0.0F));
        leftscythe.addOrReplaceChild("claw_left", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(0.0F, 0.0F, -0.5F, 2, 4, 1),
                PartPose.offsetAndRotation(0.7F, 10.3F, 0.0F, 0.0F, 0.0F, 0.36425021489121656F));

        PartDefinition rightscythe = root.addOrReplaceChild("scythe_right", CubeListBuilder.create()
                        .texOffs(26, 0)
                        .addBox(-3.0F, 0.0F, -1.0F, 3, 11, 2),
                PartPose.offsetAndRotation(-8.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.08726646259971647F));
        rightscythe.addOrReplaceChild("elbow_right", CubeListBuilder.create()
                        .texOffs(46, 10)
                        .addBox(0.0F, -2.0F, -1.0F, 1, 2, 2),
                PartPose.offset(-3.0F, 0.0F, 0.0F));
        rightscythe.addOrReplaceChild("claw_right", CubeListBuilder.create()
                        .texOffs(57, 11)
                        .addBox(-2.0F, 0.0F, -0.5F, 2, 4, 1),
                PartPose.offsetAndRotation(-0.7F, 10.3F, 0.0F, 0.0F, 0.0F, -0.36425021489121656F));

        PartDefinition tip = root.addOrReplaceChild("tip_main", CubeListBuilder.create()
                        .texOffs(46, 0)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 4, 1),
                PartPose.offset(0.0F, 16.0F, 0.0F));
        tip.addOrReplaceChild("tip_left", CubeListBuilder.create()
                        .texOffs(56, 8)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 1, 1),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363323129985824F));
        tip.addOrReplaceChild("tip_right", CubeListBuilder.create()
                        .texOffs(6, 8)
                        .addBox(-2.0F, 0.0F, 0.0F, 2, 1, 1),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363323129985824F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.scytheR.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F;
        this.scytheL.zRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.scytheR.zRot += Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.08726646259971647F;
        this.scytheL.zRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.08726646259971647F;
    }
}
