package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.boss.BlueHowliteWolf;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelBlueHowliteWolf - Androsa
 * Created using Tabula 7.0.0
 */
public class BlueHowliteWolfModel<T extends BlueHowliteWolf> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart frontUpperLeg1;
    public ModelPart frontUpperLeg2;
    public ModelPart backUpperLeg1;
    public ModelPart backUpperLeg2;

    public BlueHowliteWolfModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.frontUpperLeg1 = root.getChild("front_upper_leg_right");
        this.frontUpperLeg2 = root.getChild("front_upper_leg_left");
        this.backUpperLeg1 = root.getChild("back_upper_leg_right");
        this.backUpperLeg2 = root.getChild("back_upper_leg_left");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(14, 9)
                        .addBox(-2.5F, -6.0F, -3.0F, 5, 6, 6),
                PartPose.offset(0F, -6.0F, -9.0F));
        head.addOrReplaceChild("ear_left", CubeListBuilder.create()
                        .texOffs(122, 56)
                        .addBox(1.5F, -10.0F, 0.0F, 2, 7, 1),
                PartPose.ZERO);
        head.addOrReplaceChild("ear_right", CubeListBuilder.create()
                        .texOffs(0, 56)
                        .addBox(-3.5F, -10.0F, 0.0F, 2, 7, 1),
                PartPose.ZERO);
        head.addOrReplaceChild("snout", CubeListBuilder.create()
                        .texOffs(0, 9)
                        .addBox(-1.5F, -3.5F, -6.0F, 3, 3, 4),
                PartPose.rotation(0.17627825445142728F, 0.0F, 0.0F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(74, 33)
                        .addBox(0.0F, 0.0F, 0.0F, 7, 10, 6),
                PartPose.offset(-3.5F, -7.8F, -11.0F));
        root.addOrReplaceChild("chest_mane", CubeListBuilder.create()
                        .texOffs(6, 46)
                        .addBox(0.0F, 0.0F, 0.0F, 12, 8, 10),
                PartPose.offset(-6.0F, 0.0F, -13.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 25)
                        .addBox(0.0F, 0.0F, 0.0F, 8, 6, 15),
                PartPose.offsetAndRotation(-4.0F, 0.7F, -3.1F, -0.27314402793711257F, 0.0F, 0.0F));

        PartDefinition frontupperleftleg = root.addOrReplaceChild("front_upper_leg_left", CubeListBuilder.create()
                        .texOffs(82, 49)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 10, 5),
                PartPose.offset(5.0F, 2.0F, -11.0F));
        PartDefinition frontlowerleftleg = frontupperleftleg.addOrReplaceChild("front_lower_leg_left", CubeListBuilder.create()
                        .texOffs(110, 51)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 10, 3),
                PartPose.offset(-0.1F, 9.3F, 1.8F));
        frontlowerleftleg.addOrReplaceChild("front_paw_left", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 6, 3, 6),
                PartPose.offset(-1.5F, 9.7F, -2.8F));

        PartDefinition frontupperrightleg = root.addOrReplaceChild("front_upper_leg_right", CubeListBuilder.create()
                        .texOffs(66, 49)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 10, 5),
                PartPose.offset(-8.0F, 2.0F, -11.0F));
        PartDefinition frontlowerrightleg = frontupperrightleg.addOrReplaceChild("front_lower_leg_right", CubeListBuilder.create()
                        .texOffs(98, 51)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 10, 3),
                PartPose.offset(0.1F, 9.3F, 1.8F));
        frontlowerrightleg.addOrReplaceChild("front_paw_right", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 6, 3, 6),
                PartPose.offset(-1.5F, 9.7F, -2.8F));

        PartDefinition backupperleftleg = root.addOrReplaceChild("back_upper_leg_left", CubeListBuilder.create()
                        .texOffs(116, 38)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 7, 4),
                PartPose.offsetAndRotation(3.0F, 6.3F, 7.5F, -0.7285004297824331F, 0.0F, 0.0F));
        PartDefinition backlowerleftleg = backupperleftleg.addOrReplaceChild("back_lower_leg_left", CubeListBuilder.create()
                        .texOffs(108, 36)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 11, 2),
                PartPose.offsetAndRotation(-0.1F, 6.5F, 2.3F, 1.06011298766135573F, 0.0F, 0.0F));
        backlowerleftleg.addOrReplaceChild("back_paw_left", CubeListBuilder.create()
                        .texOffs(50, 58)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 2, 4),
                PartPose.offsetAndRotation(-1.0F, 9.5F, -1.5F, -0.3285004297824331F, 0.0F, 0.0F));

        PartDefinition backupperrightleg = root.addOrReplaceChild("back_upper_leg_right", CubeListBuilder.create()
                        .texOffs(116, 27)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 7, 4),
                PartPose.offsetAndRotation(-5.2F, 6.3F, 7.5F, -0.7285004297824331F, 0.0F, 0.0F));
        PartDefinition backlowerrightleg = backupperrightleg.addOrReplaceChild("back_lower_leg_right", CubeListBuilder.create()
                        .texOffs(100, 36)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 11, 2),
                PartPose.offsetAndRotation(0.1F, 6.5F, 2.3F, 1.06011298766135573F, 0.0F, 0.0F));
        backlowerrightleg.addOrReplaceChild("back_paw_right", CubeListBuilder.create()
                        .texOffs(50, 52)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 2, 4),
                PartPose.offsetAndRotation(-1.0F, 9.5F, -1.5F, -0.3285004297824331F, 0.0F, 0.0F));

        root.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 3, 12),
                PartPose.offsetAndRotation(-2.0F, 4.7F, 11.3F, -0.7234389749516497F, 0.0F, 0.0F));
        root.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(48, 15)
                        .addBox(0.0F, 0.0F, 0.0F, 4, 3, 12),
                PartPose.offsetAndRotation(-2.0F, 12.3F, 16.7F, 0.6108652381980153F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.frontUpperLeg1.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.frontUpperLeg2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.backUpperLeg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount - 0.7285004297824331F;
        this.backUpperLeg2.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount - 0.7285004297824331F;
    }
}
