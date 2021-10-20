package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.RuggedLurmorusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelRuggedLurmorus - Androsa
 * Created using Tabula 7.0.0
 */
public class RuggedLurmorusModel<T extends RuggedLurmorusEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart shoulderL;
    public ModelPart shoulderR;
    public ModelPart upperLegL;
    public ModelPart upperLegR;
    public ModelPart tailBase;
    public ModelPart tailpart1;
    public ModelPart tailpart2;
    public ModelPart tailpart3;

    public RuggedLurmorusModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("body").getChild("neck_lower").getChild("neck_middle").getChild("neck_upper").getChild("head");
        this.shoulderL = root.getChild("shoulder_left");
        this.shoulderR = root.getChild("shoulder_right");
        this.upperLegL = root.getChild("upper_leg_back_left");
        this.upperLegR = root.getChild("upper_leg_back_right");
        this.tailBase = root.getChild("tail_base");
        this.tailpart1 = root.getChild("tail_segment_1");
        this.tailpart2 = root.getChild("tail_segment_2");
        this.tailpart3 = root.getChild("tail_segment_3");
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
                        .addBox(-4.5F, 0.0F, -11.0F, 9, 10, 22),
                PartPose.offsetAndRotation(0.0F, -1.0F, 2.0F, -0.08726646259971647F, 0.0F, 0.0F));
        PartDefinition lowerneck = body.addOrReplaceChild("neck_lower", CubeListBuilder.create()
                        .texOffs(62, 21)
                        .addBox(-3.5F, -8.0F, -3.5F, 7, 10, 7),
                PartPose.offset(0.0F, 5.0F, -9.0F));
        PartDefinition midneck = lowerneck.addOrReplaceChild("neck_middle", CubeListBuilder.create()
                        .texOffs(90, 21)
                        .addBox(-2.5F, -10.0F, -2.5F, 5, 10, 5),
                PartPose.offsetAndRotation(0.0F, -7.7F, -0.8F, 0.091106186954104F, 0.0F, 0.0F));
        midneck.addOrReplaceChild("neck_sail_lower", CubeListBuilder.create()
                        .texOffs(113, 16)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 14, 5),
                PartPose.offset(0.0F, -10.0F, 2.0F));
        PartDefinition upperneck = midneck.addOrReplaceChild("neck_upper", CubeListBuilder.create()
                        .texOffs(108, 0)
                        .addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4),
                PartPose.offsetAndRotation(0.0F, -9.5F, 0.0F, 0.1832595714594046F, 0.0F, 0.0F));
        upperneck.addOrReplaceChild("neck_sail_upper", CubeListBuilder.create()
                        .texOffs(104, 30)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 15, 6),
                PartPose.offset(0.0F, -14.0F, 0.0F));
        PartDefinition head = upperneck.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 32)
                        .addBox(-2.5F, -4.0F, -6.0F, 5, 5, 6),
                PartPose.offsetAndRotation(0.0F, -8.6F, 0.7F, -0.1832595714594046F, 0.0F, 0.0F));
        head.addOrReplaceChild("nose_sail", CubeListBuilder.create()
                        .texOffs(22, 32)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 5, 7),
                PartPose.offset(0.0F, -5.4F, -8.0F));

        PartDefinition leftshoulder = root.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, -3.0F, 4, 8, 6),
                PartPose.offset(3.5F, 0.0F, -4.0F));
        PartDefinition frontleftupperleg = leftshoulder.addOrReplaceChild("upper_leg_front_left", CubeListBuilder.create()
                        .texOffs(38, 32)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 7, 5),
                PartPose.offset(1.8F, 7.0F, -2.7F));
        PartDefinition frontleftlowerleg = frontleftupperleg.addOrReplaceChild("lower_leg_front_left", CubeListBuilder.create()
                        .texOffs(86, 36)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 10, 4),
                PartPose.offset(0.0F, 6.5F, 0.1F));
        frontleftlowerleg.addOrReplaceChild("hoof_front_left", CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(-2.5F, 0.0F, -5.0F, 5, 3, 5),
                PartPose.offset(0.0F, 7.5F, 3.4F));

        PartDefinition rightshoulder = root.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(-4.0F, 0.0F, -3.0F, 4, 8, 6),
                PartPose.offset(-3.5F, 0.0F, -4.0F));
        PartDefinition frontrightupperleg = rightshoulder.addOrReplaceChild("upper_leg_front_right", CubeListBuilder.create()
                        .texOffs(56, 38)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 7, 5),
                PartPose.offset(-1.8F, 7.0F, -2.7F));
        PartDefinition frontrightlowerleg = frontrightupperleg.addOrReplaceChild("lower_leg_front_right", CubeListBuilder.create()
                        .texOffs(0, 43)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 10, 4),
                PartPose.offset(0.0F, 6.5F, 0.1F));
        frontrightlowerleg.addOrReplaceChild("hoof_front_right", CubeListBuilder.create()
                        .texOffs(40, 14)
                        .addBox(-2.5F, 0.0F, -5.0F, 5, 3, 5),
                PartPose.offset(0.0F, 7.5F, 3.4F));

        PartDefinition backleftupperleg = root.addOrReplaceChild("upper_leg_back_left", CubeListBuilder.create()
                        .texOffs(60, 0)
                        .addBox(0.0F, 0.0F, -2.5F, 3, 9, 5),
                PartPose.offset(4.0F, 4.0F, 9.0F));
        PartDefinition backleftlowerleg = backleftupperleg.addOrReplaceChild("lower_leg_back_left", CubeListBuilder.create()
                        .texOffs(16, 44)
                        .addBox(0.0F, 0.0F, -2.0F, 3, 12, 4),
                PartPose.offset(-0.2F, 8.0F, 0.0F));
        backleftlowerleg.addOrReplaceChild("hoof_back_left", CubeListBuilder.create()
                        .texOffs(60, 14)
                        .addBox(-2.0F, 0.0F, -4.0F, 4, 2, 4),
                PartPose.offset(1.5F, 10.1F, 1.8F));

        PartDefinition backrightupperleg = root.addOrReplaceChild("upper_leg_back_right", CubeListBuilder.create()
                        .texOffs(76, 0)
                        .addBox(-3.0F, 0.0F, -2.5F, 3, 9, 5),
                PartPose.offset(-4.0F, 4.0F, 9.0F));
        PartDefinition backrightlowerleg = backrightupperleg.addOrReplaceChild("lower_leg_back_right", CubeListBuilder.create()
                        .texOffs(30, 44)
                        .addBox(-3.0F, 0.0F, -2.0F, 3, 12, 4),
                PartPose.offset(0.2F, 8.0F, 0.0F));
        backrightlowerleg.addOrReplaceChild("hoof_back_right", CubeListBuilder.create()
                        .texOffs(44, 46)
                        .addBox(-2.0F, 0.0F, -4.0F, 4, 2, 4),
                PartPose.offset(-1.5F, 10.1F, 1.8F));

        PartDefinition tailbase = root.addOrReplaceChild("tail_base", CubeListBuilder.create()
                        .texOffs(82, 4)
                        .addBox(-4.0F, 0.0F, 0.0F, 8, 7, 10),
                PartPose.offsetAndRotation(0.0F, 0.9F, 12.0F, -0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition tail1 = tailbase.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(65, 41)
                        .addBox(-3.0F, 0.0F, 0.0F, 6, 6, 9),
                PartPose.offsetAndRotation(0.0F, 0.2F, 9.0F, 0.12217304763960307F, 0.0F, 0.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(87, 50)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8),
                PartPose.offsetAndRotation(0.0F, 0.2F, 8.0F, 0.18203784098300857F, 0.0F, 0.0F));
        tail2.addOrReplaceChild("tail_segment_3", CubeListBuilder.create()
                        .texOffs(52, 50)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 4, 8),
                PartPose.offsetAndRotation(0.0F, 0.2F, 7.0F, 0.136659280431156F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.shoulderL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.shoulderR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.upperLegL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.upperLegR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;

        //hehe, wag that tail
        this.tailBase.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F - 0.2617993877991494F;
        this.tailBase.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.2617993877991494F;
        this.tailpart1.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.12217304763960307F;
        this.tailpart1.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F + 0.12217304763960307F;
        this.tailpart2.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.18203784098300857F;
        this.tailpart2.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F + 0.18203784098300857F;
        this.tailpart3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.136659280431156F;
        this.tailpart3.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F + 0.136659280431156F;
    }
}
