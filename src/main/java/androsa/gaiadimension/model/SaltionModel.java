package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.Saltion;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelSaltion - Androsa
 * Created using Tabula 7.0.0
 */
public class SaltionModel<T extends Saltion> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart armL;
    public ModelPart armR;
    public ModelPart legL1;
    public ModelPart legL2;
    public ModelPart legL3;
    public ModelPart legR1;
    public ModelPart legR2;
    public ModelPart legR3;
    public ModelPart tail1;
    public ModelPart tail2;
    public ModelPart tail3;
    public ModelPart bulb;

    public SaltionModel(ModelPart root) {
        this.root = root;
        ModelPart body = root.getChild("body");
        this.head = body.getChild("head");
        this.armL = body.getChild("arm_left");
        this.armR = body.getChild("arm_right");
        this.legL1 = body.getChild("leg_front_left");
        this.legL2 = body.getChild("leg_mid_left");
        this.legL3 = body.getChild("leg_back_left");
        this.legR1 = body.getChild("leg_front_right");
        this.legR2 = body.getChild("leg_mid_right");
        this.legR3 = body.getChild("leg_back_right");
        this.tail1 = body.getChild("tail_segment_1");
        this.tail2 = tail1.getChild("tail_segment_2");
        this.tail3 = tail2.getChild("tail_segment_3");
        this.bulb = tail3.getChild("bulb");
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
                        .addBox(-2.5F, -1.5F, 0.0F, 5, 3, 10),
                PartPose.offset(0.0F, 21.0F, -4.0F));
        body.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(41, 12)
                        .addBox(-2.0F, -1.5F, -4.0F, 4, 3, 3),
                PartPose.offset(0.0F, 0.3F, 1.1F));

        body.addOrReplaceChild("leg_front_left", CubeListBuilder.create()
                        .texOffs(20, 0)
                        .addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(-2.0F, 0.6F, 2.8F, 0.0F, 0.13962634015954636F, -0.3490658503988659F));
        body.addOrReplaceChild("leg_mid_left", CubeListBuilder.create()
                        .texOffs(36, 0)
                        .addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(-2.0F, 0.6F, 5.8F, 0.0F, 0.17453292519943295F, -0.3490658503988659F));
        body.addOrReplaceChild("leg_back_left", CubeListBuilder.create()
                        .texOffs(20, 4)
                        .addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(-2.0F, 0.6F, 8.8F, 0.0F, 0.20943951023931953F, -0.3490658503988659F));
        body.addOrReplaceChild("leg_front_right", CubeListBuilder.create()
                        .texOffs(36, 4)
                        .addBox(0.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(2.0F, 0.6F, 2.8F, 0.0F, -0.13962634015954636F, 0.3490658503988659F));
        body.addOrReplaceChild("leg_mid_right", CubeListBuilder.create()
                        .texOffs(30, 8)
                        .addBox(0.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(2.0F, 0.6F, 5.8F, 0.0F, -0.17453292519943295F, 0.3490658503988659F));
        body.addOrReplaceChild("leg_back_right", CubeListBuilder.create()
                        .texOffs(46, 8)
                        .addBox(0.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(2.0F, 0.6F, 8.8F, 0.0F, -0.20943951023931953F, 0.3490658503988659F));

        PartDefinition leftarm = body.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(14, 14)
                        .addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5),
                PartPose.offsetAndRotation(2.0F, 1.0F, 0.8F, 0.0F, -0.8377580409572781F, 0.0F));
        PartDefinition leftpincer = leftarm.addOrReplaceChild("pincer_left", CubeListBuilder.create()
                        .texOffs(0, 27)
                        .addBox(-8.0F, -1.0F, -2.0F, 8, 2, 3),
                PartPose.offsetAndRotation(1.0F, -0.1F, -5.2F, 0.0F, -0.3490658503988659F, 0.0F));
        leftpincer.addOrReplaceChild("claw_left", CubeListBuilder.create()
                        .texOffs(47, 18)
                        .addBox(-4.0F, -0.5F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(-2.5F, 0.0F, 0.0F, 0.0F, 0.2617993877991494F, 0.0F));

        PartDefinition rightarm = body.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5),
                PartPose.offsetAndRotation(-2.0F, 1.0F, 0.8F, 0.0F, 0.8377580409572781F, 0.0F));
        PartDefinition rightpincer = rightarm.addOrReplaceChild("pincer_right", CubeListBuilder.create()
                        .texOffs(25, 25)
                        .addBox(0.0F, -1.0F, -2.0F, 8, 2, 3),
                PartPose.offsetAndRotation(-1.0F, -0.1F, -5.2F, 0.0F, 0.3490658503988659F, 0.0F));
        rightpincer.addOrReplaceChild("claw_right", CubeListBuilder.create()
                        .texOffs(50, 2)
                        .addBox(0.0F, -0.5F, -1.0F, 4, 1, 2),
                PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, 0.0F, -0.2617993877991494F, 0.0F));

        PartDefinition tail1 = body.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(25, 12)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 2, 5),
                PartPose.offsetAndRotation(0.0F, -1.5F, 8.6F, 0.7853981633974483F, 0.0F, 0.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(36, 18)
                        .addBox(-1.5F, 0.0F, -0.8F, 3, 2, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 3.9F, 0.9560913642424937F, 0.0F, 0.0F));
        PartDefinition tail3 = tail2.addOrReplaceChild("tail_segment_3", CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-1.5F, 0.0F, -0.6F, 3, 2, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 3.1F, 0.8651597102135892F, 0.0F, 0.0F));
        PartDefinition bulb = tail3.addOrReplaceChild("bulb", CubeListBuilder.create()
                        .texOffs(16, 21)
                        .addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3),
                PartPose.offset(0.0F, 1.1F, 4.0F));
        bulb.addOrReplaceChild("stinger", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 1, 3),
                PartPose.offsetAndRotation(0.0F, -0.8F, 2.0F, 1.3089969389957472F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 40);
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);

        this.armR.yRot = Mth.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount + 0.8377580409572781F;
        this.armL.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount - 0.8377580409572781F ;

        this.tail1.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;
        this.tail2.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.9560913642424937F;
        this.tail3.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.8651597102135892F;
        this.bulb.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;

        float f1 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f2 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float f4 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

        this.legL1.zRot = -0.3490658503988659F;
        this.legL1.yRot = 0.13962634015954636F;
        this.legL1.yRot += f1;
        this.legL1.zRot += f4;

        this.legR1.zRot = 0.3490658503988659F;
        this.legR1.yRot = -0.13962634015954636F;
        this.legR1.yRot -= f1;
        this.legR1.zRot -= f4;

        this.legL2.zRot = -0.3490658503988659F;
        this.legL2.yRot = 0.17453292519943295F;
        this.legL2.yRot += f2;
        this.legL2.zRot += f5;

        this.legR2.zRot = 0.3490658503988659F;
        this.legR2.yRot = -0.17453292519943295F;
        this.legR2.yRot -= f2;
        this.legR2.zRot -= f5;

        this.legL3.zRot = -0.3490658503988659F;
        this.legL3.yRot = 0.20943951023931953F;
        this.legL3.yRot += f3;
        this.legL3.zRot += f6;

        this.legR3.zRot = 0.3490658503988659F;
        this.legR3.yRot = -0.20943951023931953F;
        this.legR3.yRot -= f3;
        this.legR3.zRot -= f6;
    }
}
