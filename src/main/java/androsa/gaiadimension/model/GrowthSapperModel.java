package androsa.gaiadimension.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

/**
 * ModelGrowthSapper - Androsa
 * Created using Tabula 7.0.0
 */
public class GrowthSapperModel<T extends Mob> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg3;
    public ModelPart leg4;
    public ModelPart leg5;
    public ModelPart leg6;

    public GrowthSapperModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg_front_left");
        this.leg2 = root.getChild("leg_mid_left");
        this.leg3 = root.getChild("leg_back_left");
        this.leg4 = root.getChild("leg_front_right");
        this.leg5 = root.getChild("leg_mid_right");
        this.leg6 = root.getChild("leg_back_right");
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
                        .addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5),
                PartPose.offset(0.0F, 15.0F, -6.0F));
        head.addOrReplaceChild("nozzle", CubeListBuilder.create()
                        .texOffs(41, 16)
                        .addBox(-0.5F, 0.0F, -5.0F, 1, 1, 5),
                PartPose.offsetAndRotation(0.0F, 1.0F, -5.0F, 0.27314402793711257F, 0.0F, 0.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(8, 0)
                        .addBox(-3.5F, -9.0F, 0.0F, 7, 9, 12),
                PartPose.offset(0.0F, 18.0F, -6.0F));
        root.addOrReplaceChild("leg_front_left", CubeListBuilder.create()
                        .texOffs(0, 10)
                        .addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2),
                PartPose.offset(-1.4F, 18.0F, -5.5F));
        root.addOrReplaceChild("leg_mid_left", CubeListBuilder.create()
                        .texOffs(34, 0)
                        .addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2),
                PartPose.offset(-1.4F, 18.0F, 0.0F));
        root.addOrReplaceChild("leg_back_left", CubeListBuilder.create()
                        .texOffs(42, 0)
                        .addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2),
                PartPose.offset(-1.4F, 18.0F, 3.5F));
        root.addOrReplaceChild("leg_front_right", CubeListBuilder.create()
                        .texOffs(50, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 6, 2),
                PartPose.offset(1.4F, 18.0F, -5.5F));
        root.addOrReplaceChild("leg_mid_right", CubeListBuilder.create()
                        .texOffs(46, 8)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 6, 2),
                PartPose.offset(1.4F, 18.0F, 0.0F));
        root.addOrReplaceChild("leg_back_right", CubeListBuilder.create()
                        .texOffs(54, 8)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 6, 2),
                PartPose.offset(1.4F, 18.0F, 3.5F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.leg4.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.leg3.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.leg5.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.leg6.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
