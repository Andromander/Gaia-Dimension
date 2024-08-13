package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.CavernTick;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * ModelCavernTick - Androsa
 * Created using Tabula 7.0.0
 */
public class CavernTickModel<T extends CavernTick> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart head;
    public ModelPart spike1;
    public ModelPart spike2;
    public ModelPart spike3;

    public CavernTickModel(ModelPart root) {
        this.root = root;
        ModelPart body = root.getChild("body");
        this.head = body.getChild("head");
        this.spike1 = body.getChild("spike_left");
        this.spike2 = body.getChild("spike_mid");
        this.spike3 = body.getChild("spike_right");
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
                        .addBox(-2.5F, 0.0F, 0.0F, 5, 4, 7),
                PartPose.offset(0.0F, 20.0F, -3.0F));
        body.addOrReplaceChild("spike_left", CubeListBuilder.create()
                        .texOffs(14, 11)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5),
                PartPose.offsetAndRotation(-1.5F, 0.5F, 2.0F, 0.8726646259971648F, -0.5235987755982988F, -0.6108652381980153F));
        body.addOrReplaceChild("spike_mid", CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5),
                PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.7853981633974483F, 0.0F, 0.0F));
        body.addOrReplaceChild("spike_right", CubeListBuilder.create()
                        .texOffs(0, 17)
                        .addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5),
                PartPose.offsetAndRotation(1.5F, 0.5F, 2.0F, 0.8726646259971648F, 0.5235987755982988F, 0.6108652381980153F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(17, 0)
                        .addBox(-2.0F, -2.0F, -2.0F, 4, 3, 2),
                PartPose.offset(0.0F, 3.0F, 0.0F));
        head.addOrReplaceChild("horn", CubeListBuilder.create()
                        .texOffs(23, 8)
                        .addBox(-0.5F, 0.0F, -3.0F, 1, 1, 3),
                PartPose.offsetAndRotation(0.0F, -1.5F, -1.0F, -0.7853981633974483F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 32, 24);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.spike1.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;
        this.spike2.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;
        this.spike3.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;

        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
    }
}
