package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.RockyLuggeroth;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelRockyLuggeroth - Androsa
 * Created using Tabula 7.0.0
 */
public class RockyLuggerothModel<T extends RockyLuggeroth> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart foot1;
    public ModelPart foot2;
    public ModelPart foot3;
    public ModelPart foot4;

    public RockyLuggerothModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.foot1 = root.getChild("foot_front_left");
        this.foot2 = root.getChild("foot_front_right");
        this.foot3 = root.getChild("foot_back_left");
        this.foot4 = root.getChild("foot_back_right");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-2.5F, -2.0F, -5.0F, 5, 4, 5),
                PartPose.offset(0.0F, 18.8F, -8.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, 0.0F, -5.0F, 8, 3, 16),
                PartPose.offset(0.0F, 16.0F, -3.0F));
        PartDefinition shell = body.addOrReplaceChild("shell", CubeListBuilder.create()
                        .texOffs(46, 9)
                        .addBox(-5.0F, -18.0F, -10.0F, 10, 18, 20),
                PartPose.offset(0.0F, 0.0F, 3.0F));
        shell.addOrReplaceChild("ridge", CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-6.0F, 0.0F, -11.0F, 12, 1, 22),
                PartPose.ZERO);
        root.addOrReplaceChild("foot_front_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, 0.0F, 0.0F, 3, 6, 3),
                PartPose.offset(-2.5F, 18.0F, -6.0F));
        root.addOrReplaceChild("foot_front_right", CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 6, 3),
                PartPose.offset(2.5F, 18.0F, -6.0F));
        root.addOrReplaceChild("foot_back_left", CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-3.0F, 0.0F, 0.0F, 3, 6, 3),
                PartPose.offset(-2.5F, 18.0F, 6.0F));
        root.addOrReplaceChild("foot_back_right", CubeListBuilder.create()
                        .texOffs(76, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 3, 6, 3),
                PartPose.offset(2.5F, 18.0F, 6.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.foot2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.foot3.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.foot4.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
