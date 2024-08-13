package androsa.gaiadimension.model;
// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings


import androsa.gaiadimension.entity.OpaliteContruct;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class OpaliteConstructModel<T extends OpaliteContruct> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public OpaliteConstructModel(ModelPart root) {
		this.root = root;

		this.head = root.getChild("head");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	public static LayerDefinition makeBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(31, 14).addBox(-3.0F, -6.0F, -2.5F, 6.0F, 6.0F, 5.0F),
				PartPose.offset(0.0F, 7.0F, 0.0F));
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-5.0F, -5.5F, -4.0F, 10.0F, 11.0F, 8.0F),
				PartPose.offset(0.0F, 12.5F, 0.0F));
		body.addOrReplaceChild("shell", CubeListBuilder.create()
						.texOffs(0, 19)
						.addBox(-5.5F, -0.5F, 0.0F, 11.0F, 11.0F, 4.0F),
				PartPose.offset(0.0F, -5.5F, 1.0F));
		root.addOrReplaceChild("left_arm", CubeListBuilder.create()
						.texOffs(0, 34)
						.addBox(-4.0F, 0.0F, -2.5F, 4.0F, 11.0F, 5.0F),
				PartPose.offset(-5.0F, 8.0F, 0.0F));
		root.addOrReplaceChild("right_arm", CubeListBuilder.create()
						.texOffs(25, 29)
						.addBox(0.0F, 0.0F, -2.5F, 4.0F, 11.0F, 5.0F),
				PartPose.offset(5.0F, 8.0F, 0.0F));
		root.addOrReplaceChild("left_leg", CubeListBuilder.create()
						.texOffs(37, 39)
						.addBox(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 6.0F),
				PartPose.offset(-2.5F, 18.0F, 0.0F));
		root.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(36, 0)
						.addBox(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 6.0F),
				PartPose.offset(2.5F, 18.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);

		this.right_arm.zRot = 0.0F;
		this.left_arm.zRot = 0.0F;
		this.right_arm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F;
		this.left_arm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F;

		if (entity.isConstructing()) {
			this.right_arm.xRot = -2.0F;
			this.left_arm.xRot = -2.0F;
		} else {
			this.right_arm.xRot = 0.0F;
			this.left_arm.xRot = 0.0F;
			this.right_arm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
			this.left_arm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
			this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
			this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
		}

		this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
	}
}