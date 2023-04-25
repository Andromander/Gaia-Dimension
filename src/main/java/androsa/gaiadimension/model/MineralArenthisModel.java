package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MineralArenthisEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import java.util.Arrays;

/**
 * ModelMineralArenthis - Androsa
 * Created using Tabula 7.0.0
 */
public class MineralArenthisModel<T extends MineralArenthisEntity> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart body;
    public ModelPart tail;
    public ModelPart tailfin;
    public ModelPart[] tentacles = new ModelPart[10];

    public MineralArenthisModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("cap_top").getChild("cap_bottom").getChild("head").getChild("body");
        this.tail = body.getChild("tail");
        this.tailfin = tail.getChild("tail_fin");
        Arrays.setAll(this.tentacles, (num) -> root.getChild(getTentacleName(num)));
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition topcap = root.addOrReplaceChild("cap_top", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8.0F, 0.0F, -8.0F, 16, 7, 16),
                PartPose.offset(0.0F, -5.0F, 0.0F));
        PartDefinition bottomcap = topcap.addOrReplaceChild("cap_bottom", CubeListBuilder.create()
                        .texOffs(44, 3)
                        .addBox(-10.0F, 0.0F, -10.0F, 20, 6, 20),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        PartDefinition head = bottomcap.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(-3.5F, 0.0F, -3.5F, 7, 4, 7),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(104, 0)
                        .addBox(-2.5F, 0.0F, -2.0F, 5, 5, 4),
                PartPose.offset(0.0F, 4.0F, 0.0F));
        body.addOrReplaceChild("fin_left", CubeListBuilder.create()
                        .texOffs(-4, 41)
                        .addBox(0.0F, 0.0F, -2.0F, 10, 0, 4),
                PartPose.offsetAndRotation(2.2F, 0.5F, 0.1F, 0.0F, 0.0F, 1.2217304763960306F));
        body.addOrReplaceChild("fin_right", CubeListBuilder.create()
                        .texOffs(-4, 36)
                        .addBox(-10.0F, 0.0F, -2.0F, 10, 0, 4),
                PartPose.offsetAndRotation(-2.2F, 0.5F, 0.1F, 0.0F, 0.0F, -1.2217304763960306F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, 0.0F, -1.0F, 4, 7, 2),
                PartPose.offset(0.0F, 5.0F, 0.0F));
        tail.addOrReplaceChild("tail_fin", CubeListBuilder.create()
                        .texOffs(86, 30)
                        .addBox(-5.5F, 0.0F, 0.0F, 11, 6, 0),
                PartPose.offset(0.0F, 7.0F, 0.0F));

        CubeListBuilder tentaclecube = CubeListBuilder.create()
                .texOffs(28, 23).addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        for (int j = 0; j < 10; ++j) {
            double d0 = (double)j * Math.PI * 2.0D / 10.0D;
            float f = (float)Math.cos(d0) * 8.0F;
            float f1 = (float)Math.sin(d0) * 8.0F;
            d0 = (double)j * Math.PI * -2.0D / 10.0D + (Math.PI / 2D);
            root.addOrReplaceChild(getTentacleName(j), tentaclecube, PartPose.offsetAndRotation(f, 5.0F, f1, 0.0F, (float)d0, 0.0F));
        }

        return LayerDefinition.create(mesh, 128, 80);
    }

    public static String getTentacleName(int num) {
        return "tentacle_" + num;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;
        this.tail.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;
        this.tailfin.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;

        for (ModelPart modelrenderer : this.tentacles) {
            modelrenderer.xRot = ageInTicks;
        }
    }
}
