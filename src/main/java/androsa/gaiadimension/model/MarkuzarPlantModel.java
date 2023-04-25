package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MarkuzarPlantEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelMarkuzarPlant - Androsa
 * Created using Tabula 7.0.0
 */
public class MarkuzarPlantModel<T extends MarkuzarPlantEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart stalkLow;
    public ModelPart stalkMid;
    public ModelPart stalkHigh;
    public ModelPart leafR;
    public ModelPart leafL;

    public MarkuzarPlantModel(ModelPart root) {
        this.root = root;
        this.stalkLow = root.getChild("stalk_lower");
        this.stalkMid = stalkLow.getChild("stalk_middle");
        this.leafL = stalkMid.getChild("leaf_left");
        this.leafR = stalkMid.getChild("leaf_right");
        this.stalkHigh = stalkMid.getChild("stalk_upper");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition lowerstalk = root.addOrReplaceChild("stalk_lower", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -12.0F, -3.5F, 7, 12, 7),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition midstalk = lowerstalk.addOrReplaceChild("stalk_middle", CubeListBuilder.create()
                        .texOffs(28, 0)
                        .addBox(-2.5F, -10.0F, -2.5F, 5, 10, 5),
                PartPose.offset(0.0F, -12.0F, 0.0F));
        midstalk.addOrReplaceChild("leaf_left", CubeListBuilder.create()
                        .texOffs(-7, 40)
                        .addBox(0.0F, 0.0F, -3.5F, 10, 0, 7),
                PartPose.offsetAndRotation(2.5F, -6.0F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));
        midstalk.addOrReplaceChild("leaf_right", CubeListBuilder.create()
                        .texOffs(-7, 48)
                        .addBox(-10.0F, 0.0F, -3.5F, 10, 0, 7),
                PartPose.offsetAndRotation(-2.5F, -6.0F, 0.0F, 0.3490658503988659F, 0.0F, 0.0F));
        PartDefinition upperstalk = midstalk.addOrReplaceChild("stalk_upper", CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(-1.5F, -8.0F, -1.5F, 3, 8, 3),
                PartPose.offset(0.0F, -10.0F, 0.0F));
        PartDefinition bulb = upperstalk.addOrReplaceChild("bulb", CubeListBuilder.create()
                        .texOffs(44, 11)
                        .addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4),
                PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 1.1344640137963142F, 0.0F, 0.0F));
        bulb.addOrReplaceChild("decor", CubeListBuilder.create()
                        .texOffs(-13, 27)
                        .addBox(-6.5F, 0.0F, -6.5F, 13, 0, 13),
                PartPose.offset(0.0F, -0.4F, 0.0F));
        bulb.addOrReplaceChild("jewel", CubeListBuilder.create()
                        .texOffs(26, 35)
                        .addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2),
                PartPose.offset(0.0F, -2.0F, 0.0F));
        bulb.addOrReplaceChild("petal_1", CubeListBuilder.create()
                        .texOffs(0, 19)
                        .addBox(0.0F, 0.0F, -1.5F, 9, 1, 3),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7853981633974483F, 0.0F));
        bulb.addOrReplaceChild("petal_2", CubeListBuilder.create()
                        .texOffs(24, 19)
                        .addBox(0.0F, 0.0F, -1.5F, 9, 1, 3),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.7853981633974483F, 0.0F));
        bulb.addOrReplaceChild("petal_3", CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(0.0F, 0.0F, -1.5F, 9, 1, 3),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 2.356194490192345F, 0.0F));
        bulb.addOrReplaceChild("petal_4", CubeListBuilder.create()
                        .texOffs(24, 23)
                        .addBox(0.0F, 0.0F, -1.5F, 9, 1, 3),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -2.356194490192345F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        //Oh dear god, this is unsettling to watch
        this.stalkLow.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.stalkLow.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.stalkMid.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.stalkMid.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.stalkHigh.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.stalkHigh.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        this.leafL.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.leafL.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.leafR.zRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.leafR.zRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
    }
}
