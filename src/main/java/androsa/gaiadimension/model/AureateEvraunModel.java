package androsa.gaiadimension.model;
// Made with Blockbench 4.7.1
// Exported for Minecraft version 1.17 or later with Mojang mappings


import androsa.gaiadimension.entity.AureateEvraunEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class AureateEvraunModel<T extends AureateEvraunEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart upperLegL;
	private final ModelPart upperLegR;
	private final ModelPart upperArm1;
	private final ModelPart upperArm2;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;

	public AureateEvraunModel(ModelPart root) {
		this.root = root;
		ModelPart body = root.getChild("body");
		this.head = body.getChild("upperBody").getChild("neck").getChild("head");
		this.tail1 = body.getChild("tail1");
		this.tail2 = tail1.getChild("tail2");
		this.tail3 = tail2.getChild("tail3");
		this.tail4 = tail3.getChild("tail4");
		this.upperLegL = root.getChild("upperLegL");
		this.upperLegR = root.getChild("upperLegR");
		this.upperArm1 = root.getChild("upperArm1");
		this.upperArm2 = root.getChild("upperArm2");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	public static LayerDefinition makeBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition upperLegL = root.addOrReplaceChild("upperLegL", CubeListBuilder.create()
						.texOffs(65, 58)
						.addBox(-3.0F, -3.0F, -13.0F, 6.0F, 6.0F, 13.0F),
				PartPose.offsetAndRotation(7.5F, -5.0F, 7.0F, 1.0908F, 0.0F, 0.0F));
		PartDefinition midLegL = upperLegL.addOrReplaceChild("midLegL", CubeListBuilder.create()
						.texOffs(30, 71)
						.addBox(-2.9F, 0.0F, -10.0F, 6.0F, 5.0F, 10.0F),
				PartPose.offsetAndRotation(0.0F, -3.0F, -13.0F, 1.1781F, 0.0F, 0.0F));
		PartDefinition lowerLegL = midLegL.addOrReplaceChild("lowerLegL", CubeListBuilder.create()
						.texOffs(51, 77)
						.addBox(-2.5F, -4.0F, -11.0F, 5.0F, 4.0F, 11.0F),
				PartPose.offsetAndRotation(0.0F, 4.5F, -10.0F, -0.6981F, 0.0F, 0.0F));
		PartDefinition footL = lowerLegL.addOrReplaceChild("footL", CubeListBuilder.create()
						.texOffs(100, 99)
						.addBox(-2.5F, -5.0F, -3.0F, 5.0F, 5.0F, 3.0F),
				PartPose.offset(0.0F, 0.0F, -11.0F));
		footL.addOrReplaceChild("toeLL", CubeListBuilder.create()
						.texOffs(70, 38)
						.addBox(-1.0F, -8.0F, -1.6F, 2.0F, 8.0F, 2.0F),
				PartPose.offsetAndRotation(1.5F, -4.5F, -1.5F, 0.0F, 0.0F, 0.1745F));
		footL.addOrReplaceChild("toeLR", CubeListBuilder.create()
						.texOffs(29, 54)
						.addBox(-1.0F, -8.0F, -1.6F, 2.0F, 8.0F, 2.0F),
				PartPose.offsetAndRotation(-1.5F, -4.5F, -1.5F, 0.0F, 0.0F, -0.1745F));

		PartDefinition upperLegR = root.addOrReplaceChild("upperLegR", CubeListBuilder.create()
						.texOffs(61, 19)
						.addBox(-3.0F, -3.0F, -13.0F, 6.0F, 6.0F, 13.0F),
				PartPose.offsetAndRotation(-7.5F, -5.0F, 7.0F, 1.0908F, 0.0F, 0.0F));
		PartDefinition midLegR = upperLegR.addOrReplaceChild("midLegR", CubeListBuilder.create()
						.texOffs(70, 38)
						.addBox(-3.1F, 0.0F, -10.0F, 6.0F, 5.0F, 10.0F),
				PartPose.offsetAndRotation(0.0F, -3.0F, -13.0F, 1.1781F, 0.0F, 0.0F));
		PartDefinition lowerLegR = midLegR.addOrReplaceChild("lowerLegR", CubeListBuilder.create()
						.texOffs(0, 75)
						.addBox(-2.5F, -4.0F, -11.0F, 5.0F, 4.0F, 11.0F),
				PartPose.offsetAndRotation(0.0F, 4.5F, -10.0F, -0.6981F, 0.0F, 0.0F));
		PartDefinition footR = lowerLegR.addOrReplaceChild("footR", CubeListBuilder.create()
						.texOffs(90, 63)
						.addBox(-2.5F, -5.0F, -3.0F, 5.0F, 5.0F, 3.0F),
				PartPose.offset(0.0F, 0.0F, -11.0F));
		footR.addOrReplaceChild("toeRL", CubeListBuilder.create()
						.texOffs(0, 54)
						.addBox(-1.0F, -8.0F, -1.6F, 2.0F, 8.0F, 2.0F),
				PartPose.offsetAndRotation(-1.5F, -4.5F, -1.5F, 0.0F, 0.0F, -0.1745F));
		footR.addOrReplaceChild("toeRR", CubeListBuilder.create()
						.texOffs(35, 31)
						.addBox(-1.0F, -8.0F, -1.6F, 2.0F, 8.0F, 2.0F),
				PartPose.offsetAndRotation(1.5F, -4.5F, -1.5F, 0.0F, 0.0F, 0.1745F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-8.0F, -5.0F, -9.5F, 16.0F, 10.0F, 21.0F),
				PartPose.offset(0.0F, -3.5F, 0.0F));
		PartDefinition upperBody = body.addOrReplaceChild("upperBody", CubeListBuilder.create()
				.texOffs(42, 46)
				.addBox(-5.0F, -17.0F, 0.0F, 10.0F, 17.0F, 8.0F),
				PartPose.offsetAndRotation(0.0F, 5.0F, -9.5F, 0.6109F, 0.0F, 0.0F));
		PartDefinition neck = upperBody.addOrReplaceChild("neck", CubeListBuilder.create()
				.texOffs(83, 77)
				.addBox(-3.0F, -16.0F, 0.0F, 6.0F, 16.0F, 6.0F),
				PartPose.offsetAndRotation(0.0F, -17.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 54)
				.addBox(-4.5F, -9.5F, -11.0F, 9.0F, 10.0F, 11.0F),
				PartPose.offset(0.0F, -13.5F, 5.0F));
		head.addOrReplaceChild("snout", CubeListBuilder.create()
						.texOffs(82, 0)
						.addBox(-3.5F, -2.0F, -9.0F, 7.0F, 4.0F, 9.0F),
				PartPose.offset(0.0F, -3.5F, -11.0F));
		head.addOrReplaceChild("jaw", CubeListBuilder.create()
						.texOffs(90, 53)
						.addBox(-2.5F, 0.0F, -8.0F, 5.0F, 2.0F, 8.0F),
				PartPose.offset(0.0F, -1.5F, -11.0F));
		head.addOrReplaceChild("browL", CubeListBuilder.create()
						.texOffs(86, 99)
						.addBox(-1.0F, -3.0F, 0.5F, 3.0F, 3.0F, 8.0F),
				PartPose.offsetAndRotation(4.0F, -5.0F, -10.5F, 0.0873F, 0.1745F, 0.0F));
		head.addOrReplaceChild("browR", CubeListBuilder.create()
						.texOffs(92, 30)
						.addBox(-2.0F, -3.0F, 0.5F, 3.0F, 3.0F, 8.0F),
				PartPose.offsetAndRotation(-4.0F, -5.0F, -10.5F, 0.0873F, -0.1745F, 0.0F));
		head.addOrReplaceChild("frillL", CubeListBuilder.create()
						.texOffs(20, 90)
						.addBox(0.0F, -8.0F, 0.0F, 10.0F, 24.0F, 0.0F),
				PartPose.offsetAndRotation(2.5F, -7.0F, -2.0F, 0.0F, -0.3927F, 0.0F));
		head.addOrReplaceChild("frillR", CubeListBuilder.create()
						.texOffs(0, 90)
						.addBox(-10.0F, -8.0F, 0.0F, 10.0F, 24.0F, 0.0F),
				PartPose.offsetAndRotation(-2.5F, -7.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create()
						.texOffs(0, 31)
						.addBox(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 15.0F),
				PartPose.offsetAndRotation(0.0F, -4.0F, 11.5F, -0.0873F, 0.0F, 0.0F));
		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create()
						.texOffs(53, 0)
						.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 13.0F),
				PartPose.offsetAndRotation(0.0F, 0.5F, 14.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create()
						.texOffs(35, 31)
						.addBox(-3.0F, 0.0F, 0.0F, 6.0F, 4.0F, 11.0F),
				PartPose.offsetAndRotation(0.0F, 0.5F, 12.0F, 0.1309F, 0.0F, 0.0F));
		tail3.addOrReplaceChild("tail4", CubeListBuilder.create()
						.texOffs(86, 13)
						.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 9.0F),
				PartPose.offsetAndRotation(0.0F, 0.5F, 10.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition upperArm1 = root.addOrReplaceChild("upperArm1", CubeListBuilder.create()
						.texOffs(40, 92)
						.addBox(-4.0F, 0.0F, -2.5F, 4.0F, 14.0F, 5.0F),
				PartPose.offsetAndRotation(-4.5F, -11.0F, -12.5F, -0.1309F, 0.0F, 0.0F));
		PartDefinition lowerArm1 = upperArm1.addOrReplaceChild("lowerArm1", CubeListBuilder.create()
						.texOffs(72, 95)
						.addBox(-1.35F, 0.0F, -4.0F, 3.0F, 15.0F, 4.0F),
				PartPose.offsetAndRotation(-2.5F, 14.0F, 2.5F, -0.5236F, 0.0F, 0.0F));
		lowerArm1.addOrReplaceChild("fin1", CubeListBuilder.create()
						.texOffs(53, 0)
						.addBox(-6.0F, -7.0F, 0.0F, 6.0F, 12.0F, 0.0F),
				PartPose.offsetAndRotation(-1.0F, 8.0F, -1.0F, 0.0F, 0.5672F, 0.0F));
		lowerArm1.addOrReplaceChild("hand1", CubeListBuilder.create()
						.texOffs(0, 75)
						.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, 1.1345F, 0.0F, 0.0F));

		PartDefinition upperArm2 = root.addOrReplaceChild("upperArm2", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(0.0F, 0.0F, -2.5F, 4.0F, 14.0F, 5.0F),
				PartPose.offsetAndRotation(4.5F, -11.0F, -12.5F, -0.1309F, 0.0F, 0.0F));
		PartDefinition lowerArm2 = upperArm2.addOrReplaceChild("lowerArm2", CubeListBuilder.create()
						.texOffs(58, 92)
						.addBox(-1.65F, 0.0F, -4.0F, 3.0F, 15.0F, 4.0F),
				PartPose.offsetAndRotation(2.5F, 14.0F, 2.5F, -0.5236F, 0.0F, 0.0F));
		lowerArm2.addOrReplaceChild("fin2", CubeListBuilder.create()
						.texOffs(0, 31)
						.addBox(0.0F, -7.0F, 0.0F, 6.0F, 12.0F, 0.0F),
				PartPose.offsetAndRotation(1.0F, 8.0F, -1.0F, 0.0F, -0.5672F, 0.0F));
		lowerArm2.addOrReplaceChild("hand2", CubeListBuilder.create()
						.texOffs(52, 71)
						.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, 1.1345F, 0.0F, 0.0F));

		return LayerDefinition.create(mesh, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);

		this.upperArm1.zRot = 0.0F;
		this.upperArm1.xRot = -0.1309F;
		this.upperArm1.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
		this.upperArm1.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.upperArm1.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount - 0.1309F;

		this.upperArm2.zRot = 0.0F;
		this.upperArm2.xRot = -0.1309F;
		this.upperArm2.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
		this.upperArm2.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.upperArm2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount - 0.1309F;

		this.upperLegL.xRot = Mth.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount + 1.0908F;
		this.upperLegR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount + 1.0908F;

		this.tail1.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail1.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.tail2.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail2.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.tail3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail3.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.tail4.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail4.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
	}
}