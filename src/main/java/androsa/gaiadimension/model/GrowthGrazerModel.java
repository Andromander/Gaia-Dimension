package androsa.gaiadimension.model;
// Made with Blockbench 4.7.1
// Exported for Minecraft version 1.17 or later with Mojang mappings

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class GrowthGrazerModel<T extends Mob> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;

	public GrowthGrazerModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
		this.leg4 = root.getChild("leg4");
		this.leg5 = root.getChild("leg5");
		this.leg6 = root.getChild("leg6");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	public static LayerDefinition makeeBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-2.5F, -2.5F, -5.0F, 5.0F, 5.0F, 5.0F),
				PartPose.offset(0.0F, 15.0F, -6.0F));
		head.addOrReplaceChild("left_pincer", CubeListBuilder.create()
						.texOffs(47, 21)
						.addBox(-0.5F, -1.5F, -3.0F, 1.0F, 2.0F, 3.0F),
				PartPose.offsetAndRotation(1.0F, 1.5F, -4.0F, 0.6109F, 0.0F, 0.0F));
		head.addOrReplaceChild("right_pincer", CubeListBuilder.create()
						.texOffs(38, 21)
						.addBox(-2.5F, -1.5F, -3.0F, 1.0F, 2.0F, 3.0F),
				PartPose.offsetAndRotation(1.0F, 1.5F, -4.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(8, 0)
						.addBox(-3.5F, -9.0F, 0.0F, 7.0F, 9.0F, 12.0F),
				PartPose.offset(0.0F, 18.0F, -6.0F));
		body.addOrReplaceChild("left_frill", CubeListBuilder.create()
						.texOffs(0, 22)
						.addBox(0.0F, -8.0F, -9.0F, 0.0F, 8.0F, 18.0F),
				PartPose.offsetAndRotation(-2.5F, -7.0F, 7.0F, 0.0F, 0.0F, -0.7854F));
		body.addOrReplaceChild("mid_frill", CubeListBuilder.create()
						.texOffs(0, 13)
						.addBox(0.0F, -8.0F, -9.0F, 0.0F, 8.0F, 18.0F),
				PartPose.offset(0.0F, -7.0F, 7.0F));
		body.addOrReplaceChild("right_frill", CubeListBuilder.create()
						.texOffs(0, 4)
						.addBox(0.0F, -8.0F, -9.0F, 0.0F, 8.0F, 18.0F),
				PartPose.offsetAndRotation(2.5F, -7.0F, 7.0F, 0.0F, 0.0F, 0.7854F));

		root.addOrReplaceChild("leg1", CubeListBuilder.create()
						.texOffs(0, 10).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F),
				PartPose.offset(-1.4F, 18.0F, -5.5F));
		root.addOrReplaceChild("leg2", CubeListBuilder.create()
						.texOffs(34, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F),
				PartPose.offset(-1.4F, 18.0F, 0.0F));
		root.addOrReplaceChild("leg3", CubeListBuilder.create()
						.texOffs(42, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F),
				PartPose.offset(-1.4F, 18.0F, 3.5F));
		root.addOrReplaceChild("leg4", CubeListBuilder.create()
						.texOffs(50, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F),
				PartPose.offset(1.4F, 18.0F, -5.5F));
		root.addOrReplaceChild("leg5", CubeListBuilder.create()
						.texOffs(46, 8).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F),
				PartPose.offset(1.4F, 18.0F, 0.0F));
		root.addOrReplaceChild("leg6", CubeListBuilder.create()
						.texOffs(54, 8).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F),
				PartPose.offset(1.4F, 18.0F, 3.5F));

		return LayerDefinition.create(mesh, 64, 64);
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