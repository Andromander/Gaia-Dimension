package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ShallowArenthis;
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
 * ModelShallowArenthis - Androsa
 * Created using Tabula 7.0.0
 */
public class ShallowArenthisModel<T extends ShallowArenthis> extends HierarchicalModel<T> {
    public ModelPart root;
    public ModelPart[] tentacles = new ModelPart[7];
    public ModelPart body;
    public ModelPart tail;

    public ShallowArenthisModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("cap").getChild("head").getChild("body");
        this.tail = body.getChild("tail");
        Arrays.setAll(this.tentacles, (num) -> root.getChild(getTentacleName(num)));
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition cap = root.addOrReplaceChild("cap", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-5.0F, 0.0F, -5.0F, 10, 5, 10),
                PartPose.ZERO);
        PartDefinition head = cap.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(30, 0)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 3, 5),
                PartPose.offset(0.0F, 5.0F, 0.0F));
        PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(48, 9)
                        .addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3),
                PartPose.offset(0.0F, 3.0F, 0.0F));
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                        .texOffs(0, 15)
                        .addBox(-3.5F, 0.0F, 0.0F, 7, 4, 0),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        for (int j = 0; j < 7; ++j) {
            double d0 = (double)j * Math.PI * 2.0D / 7.0D;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            d0 = (double)j * Math.PI * -2.0D / 7.0D + (Math.PI / 2D);

            CubeListBuilder tentaclecube = CubeListBuilder.create()
                    .texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 1, 8, 1);
            root.addOrReplaceChild(getTentacleName(j), tentaclecube, PartPose.offsetAndRotation(f, 5.0F, f1, 0.0F, (float)d0, 0.0F));
        }

       return LayerDefinition.create(mesh, 64, 32);
    }

    public static String getTentacleName(int num) {
        return "tentacle_" + num;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;
        this.tail.xRot = Mth.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;

        for (ModelPart modelrenderer : this.tentacles) {
            modelrenderer.xRot = ageInTicks;
        }
    }
}
