package androsa.gaiadimension.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;

/**
 * ModelLesserSpitfire - Androsa
 * Created using Tabula 7.0.0
 */
public class LesserSpitfireModel extends EntityModel<HumanoidRenderState> {
    public ModelPart head;
    public ModelPart armL;
    public ModelPart armR;
    public ModelPart legL;
    public ModelPart legR;

    public LesserSpitfireModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.armL = root.getChild("arm_left");
        this.armR = root.getChild("arm_right");
        this.legL = root.getChild("leg_left");
        this.legR = root.getChild("leg_right");
    }

    public static LayerDefinition makeBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("headpart", CubeListBuilder.create()
                        .texOffs(32, 17)
                        .addBox(-2.5F, -2.5F, 0.0F, 5, 5, 7),
                PartPose.offsetAndRotation(0.0F, -5.0F, 1.9F, 0.40980330836826856F, 0.0F, 0.017453292519943295F));
        head.addOrReplaceChild("ear_left", CubeListBuilder.create()
                        .texOffs(53, 13)
                        .addBox(-0.5F, 0.0F, 0.0F, 1, 8, 3),
                PartPose.offsetAndRotation(3.5F, -1.9F, 0.0F, 2.356194490192345F, 0.3665191429188092F, 0.0F));
        head.addOrReplaceChild("ear_right", CubeListBuilder.create()
                        .texOffs(24, 15)
                        .addBox(0.0F, 0.0F, 0.0F, 1, 8, 3),
                PartPose.offsetAndRotation(-3.9F, -1.9F, 0.0F, 2.356194490192345F, -0.3665191429188092F, 0.0F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2),
                PartPose.ZERO);

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(28, 0)
                        .addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5),
                PartPose.ZERO);
        body.addOrReplaceChild("cape_left", CubeListBuilder.create()
                        .texOffs(0, 26)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 1, 14),
                PartPose.offsetAndRotation(2.0F, 8.5F, 2.0F, -0.7853981633974483F, 0.7853981633974483F, 0.0F));
        body.addOrReplaceChild("cape_mid", CubeListBuilder.create()
                        .texOffs(22, 29)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 1, 14),
                PartPose.offsetAndRotation(0.0F, 8.5F, 2.0F, -0.7853981633974483F, 0.0F, 0.0F));
        body.addOrReplaceChild("cape_right", CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-2.0F, 0.0F, 0.0F, 4, 1, 14),
                PartPose.offsetAndRotation(-2.0F, 8.5F, 2.0F, -0.7853981633974483F, -0.7853981633974483F, 0.0F));

        root.addOrReplaceChild("arm_left", CubeListBuilder.create()
                        .texOffs(8, 14)
                        .addBox(0.0F, 0.0F, -1.0F, 2, 10, 2),
                PartPose.offset(2.5F, 0.5F, 0.0F));
        root.addOrReplaceChild("arm_right", CubeListBuilder.create()
                        .texOffs(16, 14)
                        .addBox(-2.0F, 0.0F, -1.0F, 2, 10, 2),
                PartPose.offset(-2.5F, 0.5F, 0.0F));
        root.addOrReplaceChild("leg_left", CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 14, 2),
                PartPose.offset(1.5F, 10.0F, 0.0F));
        root.addOrReplaceChild("leg_right", CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(-1.0F, 0.0F, -1.0F, 2, 14, 2),
                PartPose.offset(-1.5F, 10.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void setupAnim(HumanoidRenderState state) {
        this.head.yRot = state.yRot / (180F / (float) Math.PI);
        this.head.xRot = state.xRot / (180F / (float) Math.PI);

        float f = Mth.sin(state.attackTime * (float) Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - state.attackTime) * (1.0F - state.attackTime)) * (float) Math.PI);
        this.armR.zRot = 0.0F;
        this.armL.zRot = 0.0F;
        this.armR.yRot = -(0.1F - f * 0.6F);
        this.armL.yRot = 0.1F - f * 0.6F;
        this.armR.xRot = 0.0F;
        this.armL.xRot = 0.0F;
        this.armR.xRot -= f * 1.2F - f1 * 0.4F;
        this.armL.xRot -= f * 1.2F - f1 * 0.4F;
        this.armR.zRot += Mth.cos(state.ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armL.zRot -= Mth.cos(state.ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armR.xRot += Mth.sin(state.ageInTicks * 0.067F) * 0.05F;
        this.armL.xRot -= Mth.sin(state.ageInTicks * 0.067F) * 0.05F;
        this.armL.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.0F * state.walkAnimationSpeed;
        this.armR.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.0F * state.walkAnimationSpeed;

        this.legR.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.0F * state.walkAnimationSpeed;
        this.legL.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.0F * state.walkAnimationSpeed;
    }
}
