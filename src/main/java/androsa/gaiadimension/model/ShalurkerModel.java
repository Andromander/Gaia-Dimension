package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ShalurkerEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelShalurker - Androsa
 * Created using Tabula 7.0.0
 */
public class ShalurkerModel<T extends ShalurkerEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart armL;
    public ModelPart armR;
    public ModelPart legL;
    public ModelPart legR;

    public ShalurkerModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.armL = root.getChild("arm_left");
        this.armR = root.getChild("arm_right");
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
                        .texOffs(20, 9)
                        .addBox(-3.0F, -6.0F, -6.5F, 6, 6, 6),
                PartPose.offset(0.0F, 0.0F, -1.5F));
        head.addOrReplaceChild("spike_left", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, -3.0F, 0.0F, 1, 3, 1),
                PartPose.offsetAndRotation(-1.5F, -6.0F, -4.5F, -0.3490658503988659F, 0.0F, 0.0F));
        head.addOrReplaceChild("spike_right", CubeListBuilder.create()
                        .texOffs(15, 0)
                        .addBox(-0.5F, -3.0F, 0.0F, 1, 3, 1),
                PartPose.offsetAndRotation(1.5F, -6.0F, -4.5F, -0.3490658503988659F, 0.0F, 0.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 15, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.6108652381980153F, 0.0F, 0.0F));
        root.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 2, 10, 2),
                PartPose.offsetAndRotation(2.0F, 1.0F, -2.0F, -0.2617993877991494F, 0.0F, 0.0F));
        root.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 10, 2),
                PartPose.offsetAndRotation(-2.0F, 1.0F, -2.0F, -0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition leftleg = root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(30, 0)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 6, 3),
                PartPose.offsetAndRotation(2.5F, 8.5F, 3.0F, -0.6108652381980153F, 0.0F, 0.0F));
        leftleg.addOrReplaceChild("foreleg_left", CubeListBuilder.create()
                        .texOffs(44, 12)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 11, 2),
                PartPose.offsetAndRotation(-0.1F, 5.0F, 0.1F, 0.6108652381980153F, 0.0F, 0.0F));

        PartDefinition rightleg = root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(20, 0)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 6, 3),
                PartPose.offsetAndRotation(-2.5F, 8.5F, 3.0F, -0.6108652381980153F, 0.0F, 0.0F));
        rightleg.addOrReplaceChild("foreleg_right", CubeListBuilder.create()
                        .texOffs(54, 10)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 11, 2),
                PartPose.offsetAndRotation(0.1F, 5.0F, 0.1F, 0.6108652381980153F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.armR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount - 0.2617993877991494F;
        this.armL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount - 0.2617993877991494F;

        this.legL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount - 0.6108652381980153F;
        this.legR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount - 0.6108652381980153F;
    }
}
