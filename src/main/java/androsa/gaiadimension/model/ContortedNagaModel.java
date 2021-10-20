package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ContortedNagaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelContortedNaga - Androsa
 * Created using Tabula 7.0.0
 */
public class ContortedNagaModel<T extends ContortedNagaEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart lowerJaw;
    public ModelPart upperArmL;
    public ModelPart tendril1;
    public ModelPart tendril2;
    public ModelPart tendril3;
    public ModelPart miniTendril1;
    public ModelPart miniTendril2;
    public ModelPart miniTendril3;
    public ModelPart tailTop;
    public ModelPart tailBottom;

    public ContortedNagaModel(ModelPart root) {
        this.root = root;
        ModelPart torso = root.getChild("torso");
        this.head = torso.getChild("neck").getChild("head");
        this.lowerJaw = head.getChild("jaw");
        this.upperArmL = torso.getChild("upper_arm");
        this.tendril1 = torso.getChild("tendril_segment_1");
        this.tendril2 = tendril1.getChild("tendril_segment_2");
        this.tendril3 = tendril2.getChild("tendril_segment_3");
        this.miniTendril1 = torso.getChild("mini_tendril_segment_1");
        this.miniTendril2 = tendril1.getChild("mini_tendril_segment_2");
        this.miniTendril3 = tendril2.getChild("mini_tendril_segment_3");
        this.tailTop = torso.getChild("chest").getChild("lower_body").getChild("tail_top");
        this.tailBottom = tailTop.getChild("tail_bottom");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-9.5F, 0.0F, 0.0F, 19, 10, 8),
                PartPose.offsetAndRotation(0.0F, -8.0F, -8.0F, 0.3665191429188092F, 0.0F, 0.0F));

        PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(104, 8)
                        .addBox(-3.0F, -5.0F, -5.0F, 6, 5, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 5.3F, 0.2617993877991494F, 0.0F, 0.0F));
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(22, 36)
                        .addBox(-3.5F, -4.0F, -7.1F, 7, 7, 7),
                PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.3141592653589793F, 0.0F, 0.0F));
        head.addOrReplaceChild("brow_left", CubeListBuilder.create()
                        .texOffs(114, 18)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 2, 5),
                PartPose.offsetAndRotation(3.9F, -2.4F, -6.0F, 0.17453292519943295F, 0.0F, 0.0F));
        head.addOrReplaceChild("brow_right", CubeListBuilder.create()
                        .texOffs(38, 22)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 2, 5),
                PartPose.offsetAndRotation(-3.9F, -2.4F, -6.0F, 0.17453292519943295F, 0.0F, 0.0F));
        head.addOrReplaceChild("snout", CubeListBuilder.create()
                        .texOffs(70, 44)
                        .addBox(-2.5F, -3.0F, -6.0F, 5, 3, 6),
                PartPose.offset(0.0F, 0.0F, -7.0F));
        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(50, 37)
                        .addBox(-3.0F, 0.0F, -7.0F, 6, 3, 7),
                PartPose.offset(0.0F, 0.0F, -7.0F));
        jaw.addOrReplaceChild("tooth_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(2.7F, 0.6F, -5.5F, 0.0F, 0.0F, 0.2792526803190927F));
        jaw.addOrReplaceChild("tooth_right", CubeListBuilder.create()
                        .texOffs(4, 0)
                        .addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1),
                PartPose.offsetAndRotation(-2.7F, 0.6F, -4.5F, 0.0F, 0.0F, -0.12217304763960307F));

        PartDefinition upperarm = torso.addOrReplaceChild("upper_arm", CubeListBuilder.create()
                        .texOffs(88, 0)
                        .addBox(0.0F, -2.5F, -2.5F, 14, 4, 4),
                PartPose.offsetAndRotation(7.1F, 2.5F, 5.7F, 0.01641330483365508F, 0.3141592653589793F, 0.7853981633974483F));
        PartDefinition lowerarm = upperarm.addOrReplaceChild("lower_arm", CubeListBuilder.create()
                        .texOffs(70, 32)
                        .addBox(0.0F, -5.0F, -2.9F, 14, 6, 6),
                PartPose.offsetAndRotation(10.4F, 0.0F, 0.0F, 0.0F, 0.4553564018453205F, 0.8112290363269642F));
        lowerarm.addOrReplaceChild("finger_left", CubeListBuilder.create()
                        .texOffs(33, 18)
                        .addBox(0.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(13.6F, -0.5F, -2.0F, 0.0F, -0.47123889803846897F, 0.0F));
        lowerarm.addOrReplaceChild("finger_right", CubeListBuilder.create()
                        .texOffs(64, 19)
                        .addBox(0.0F, -1.0F, -1.0F, 6, 2, 2),
                PartPose.offsetAndRotation(13.6F, -3.5F, -2.0F, 0.0F, -0.47123889803846897F, 0.0F));
        lowerarm.addOrReplaceChild("decal_large", CubeListBuilder.create()
                        .texOffs(72, 19)
                        .addBox(0.0F, 0.0F, 0.0F, 1, 1, 8),
                PartPose.offsetAndRotation(0.5F, -4.8F, 0.0F, 0.24434609527920614F, -0.9948376736367678F, 0.0F));
        lowerarm.addOrReplaceChild("decal_small", CubeListBuilder.create()
                        .texOffs(0, 29)
                        .addBox(0.0F, 0.0F, 0.0F, 1, 1, 7),
                PartPose.offsetAndRotation(2.3F, -5.0F, 0.0F, 0.9075712110370513F, -0.8726646259971648F, 0.0F));

        PartDefinition tendril1 = torso.addOrReplaceChild("tendril_segment_1", CubeListBuilder.create()
                        .texOffs(88, 8)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
                PartPose.offsetAndRotation(-9.0F, 1.0F, 2.0F, -0.2565634000431664F, 0.0F, 0.19198621771937624F));
        PartDefinition tendril2 = tendril1.addOrReplaceChild("tendril_segment_2", CubeListBuilder.create()
                        .texOffs(110, 32)
                        .addBox(-1.5F, 0.0F, 0.0F, 3, 11, 3),
                PartPose.offsetAndRotation(0.3F, 11.5F, -1.9F, 0.9075712110370513F, 0.0F, -0.3141592653589793F));
        tendril2.addOrReplaceChild("tendril_segment_3", CubeListBuilder.create()
                        .texOffs(14, 36)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2),
                PartPose.offsetAndRotation(0.0F, 9.5F, 1.0F, -0.7155849933176751F, 0.0F, 0.3141592653589793F));

        PartDefinition minitendril1 = torso.addOrReplaceChild("mini_tendril_segment_1", CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(-3.0F, 0.0F, -1.3F, 3, 8, 3),
                PartPose.offsetAndRotation(-7.0F, 2.9F, 5.5F, -0.13962634015954636F, 0.0F, 0.9075712110370513F));
        PartDefinition minitendril2 = minitendril1.addOrReplaceChild("mini_tendril_segment_2", CubeListBuilder.create()
                        .texOffs(0, 37)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2),
                PartPose.offsetAndRotation(-1.6F, 7.0F, 0.2F, 0.0F, 0.0F, -0.6108652381980153F));
        minitendril2.addOrReplaceChild("mini_tendril_segment_3", CubeListBuilder.create()
                        .texOffs(46, 0)
                        .addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1),
                PartPose.offsetAndRotation(0.0F, 6.7F, 0.0F, 0.0F, 0.0F, 0.45378560551852565F));

        PartDefinition chest = torso.addOrReplaceChild("chest", CubeListBuilder.create()
                        .texOffs(54, 0)
                        .addBox(-5.5F, 0.0F, 0.0F, 11, 13, 6),
                PartPose.offsetAndRotation(0.0F, 9.0F, 1.9F, -0.17453292519943295F, 0.0F, 0.0F));
        PartDefinition lowerbody = chest.addOrReplaceChild("lower_body", CubeListBuilder.create()
                        .texOffs(12, 18)
                        .addBox(-4.0F, 0.0F, 0.0F, 8, 13, 5),
                PartPose.offsetAndRotation(0.0F, 11.0F, 1.1F, -0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition toptail = lowerbody.addOrReplaceChild("tail_top", CubeListBuilder.create()
                        .texOffs(38, 19)
                        .addBox(-3.5F, 0.0F, 0.0F, 7, 5, 12),
                PartPose.offsetAndRotation(0.0F, 8.8F, 0.4F, 0.15707963267948966F, 0.0F, 0.017453292519943295F));
        toptail.addOrReplaceChild("tail_bottom", CubeListBuilder.create()
                        .texOffs(94, 18)
                        .addBox(-2.5F, -4.0F, 0.0F, 5, 4, 10),
                PartPose.offset(0.0F, 5.0F, 12.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI) + -0.3141592653589793F;

        this.lowerJaw.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.1F + 0.15F;

        this.upperArmL.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.upperArmL.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F - 0.3141592653589793F;

        this.tendril1.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F - 0.2565634000431664F;
        this.tendril2.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F + 0.9075712110370513F;
        this.tendril3.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F - 0.7155849933176751F;

        this.miniTendril1.zRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F + 0.9075712110370513F;
        this.miniTendril2.zRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F - 0.6108652381980153F;
        this.miniTendril3.zRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F + 0.45378560551852565F;

        this.tailTop.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.tailBottom.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
