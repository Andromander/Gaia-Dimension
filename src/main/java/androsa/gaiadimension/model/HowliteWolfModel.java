package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.HowliteWolf;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelSlime - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class HowliteWolfModel<T extends HowliteWolf> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg3;
    public ModelPart leg4;

    public HowliteWolfModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg_front_left");
        this.leg2 = root.getChild("leg_front_right");
        this.leg3 = root.getChild("leg_back_left");
        this.leg4 = root.getChild("leg_back_right");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(28, 0)
                        .addBox(-2.5F, -3.0F, -2.0F, 5, 6, 4),
                PartPose.offset(0.0F, 13.5F, -7.0F));
        head.addOrReplaceChild("ear_left", CubeListBuilder.create()
                        .texOffs(46, 0)
                        .addBox(-1.0F, -4.0F, 0.0F, 2, 4, 1),
                PartPose.offsetAndRotation(-2.0F, -2.5F, 0.0F, 0.0F, 0.0F, -0.136659280431156F));
        head.addOrReplaceChild("ear_right", CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(-1.0F, -4.0F, 0.0F, 2, 4, 1),
                PartPose.offsetAndRotation(2.0F, -2.5F, 0.0F, 0.0F, 0.0F, 0.136659280431156F));
        head.addOrReplaceChild("nose", CubeListBuilder.create()
                        .texOffs(46, 5)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 2, 4),
                PartPose.offset(-1.5F, 0.8F, -5.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -2.0F, -3.0F, 5, 12, 5),
                PartPose.offsetAndRotation(0.5F, 14.0F, -3.0F, 1.5707963267948966F, 0.0F, 0.0F));
        root.addOrReplaceChild("leg_front_left", CubeListBuilder.create()
                        .texOffs(20, 10)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 8, 2),
                PartPose.offset(-2.5F, 16.0F, 7.0F));
        root.addOrReplaceChild("leg_back_left", CubeListBuilder.create()
                        .texOffs(36, 10)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 8, 2),
                PartPose.offset(-2.5F, 16.0F, -4.0F));
        root.addOrReplaceChild("leg_front_right", CubeListBuilder.create()
                        .texOffs(28, 10)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 8, 2),
                PartPose.offset(0.5F, 16.0F, 7.0F));
        root.addOrReplaceChild("leg_back_right", CubeListBuilder.create()
                        .texOffs(20, 0)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 8, 2),
                PartPose.offset(0.5F, 16.0F, -4.0F));

        PartDefinition tail1 = root.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                        .texOffs(44, 11)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 7, 2),
                PartPose.offsetAndRotation(-1.0F, 13.0F, 6.5F, 1.3062044121925562F, 0.0F, 0.0F));
        tail1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                        .texOffs(52, 11)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 7, 2),
                PartPose.offsetAndRotation(0.0F, 7.0F, -1.0F, 0.6818213656395611F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.leg4.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.leg3.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
