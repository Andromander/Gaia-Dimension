package androsa.gaiadimension.model;
// Made with Blockbench 4.12.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import androsa.gaiadimension.entity.MossAgateMonitor;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MossAgateMonitorModel<T extends MossAgateMonitor> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart body;
	private final ModelPart neck_base;
	private final ModelPart neck_top;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart snout;
	private final ModelPart front_arm_left;
	private final ModelPart front_foot_left;
	private final ModelPart front_left_toe1;
	private final ModelPart front_left_toe2;
	private final ModelPart front_left_toe3;
	private final ModelPart front_arm_right;
	private final ModelPart front_foot_right;
	private final ModelPart front_right_toe1;
	private final ModelPart front_right_toe2;
	private final ModelPart front_right_toe3;
	private final ModelPart back_leg_left;
	private final ModelPart back_foot_left;
	private final ModelPart back_left_toe1;
	private final ModelPart back_left_toe2;
	private final ModelPart back_left_toe3;
	private final ModelPart back_leg_right;
	private final ModelPart back_foot_right;
	private final ModelPart back_right_toe1;
	private final ModelPart back_right_toe2;
	private final ModelPart back_right_toe3;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;

	public MossAgateMonitorModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
		this.neck_base = this.body.getChild("neck_base");
		this.neck_top = this.neck_base.getChild("neck_top");
		this.head = this.neck_top.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.snout = this.head.getChild("snout");
		this.front_arm_left = this.body.getChild("front_arm_left");
		this.front_foot_left = this.front_arm_left.getChild("front_foot_left");
		this.front_left_toe1 = this.front_foot_left.getChild("front_left_toe1");
		this.front_left_toe2 = this.front_foot_left.getChild("front_left_toe2");
		this.front_left_toe3 = this.front_foot_left.getChild("front_left_toe3");
		this.front_arm_right = this.body.getChild("front_arm_right");
		this.front_foot_right = this.front_arm_right.getChild("front_foot_right");
		this.front_right_toe1 = this.front_foot_right.getChild("front_right_toe1");
		this.front_right_toe2 = this.front_foot_right.getChild("front_right_toe2");
		this.front_right_toe3 = this.front_foot_right.getChild("front_right_toe3");
		this.back_leg_left = this.body.getChild("back_leg_left");
		this.back_foot_left = this.back_leg_left.getChild("back_foot_left");
		this.back_left_toe1 = this.back_foot_left.getChild("back_left_toe1");
		this.back_left_toe2 = this.back_foot_left.getChild("back_left_toe2");
		this.back_left_toe3 = this.back_foot_left.getChild("back_left_toe3");
		this.back_leg_right = this.body.getChild("back_leg_right");
		this.back_foot_right = this.back_leg_right.getChild("back_foot_right");
		this.back_right_toe1 = this.back_foot_right.getChild("back_right_toe1");
		this.back_right_toe2 = this.back_foot_right.getChild("back_right_toe2");
		this.back_right_toe3 = this.back_foot_right.getChild("back_right_toe3");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	public static LayerDefinition makeBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.75F, -7.0F, 7.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition neck_base = body.addOrReplaceChild("neck_base", CubeListBuilder.create().texOffs(22, 27).addBox(-2.5F, -4.0F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -6.25F, 0.3927F, 0.0F, 0.0F));

		PartDefinition neck_top = neck_base.addOrReplaceChild("neck_top", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -2.0F, -0.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition neck_top_r1 = neck_top.addOrReplaceChild("neck_top_r1", CubeListBuilder.create().texOffs(40, 6).addBox(-2.0F, -7.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition head = neck_top.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 47).addBox(-2.0F, -3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -6.0F, -2.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(38, 42).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(-0.5F, 0.0F, -2.0F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(24, 42).addBox(-2.0F, -3.0F, -6.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_arm_left = body.addOrReplaceChild("front_arm_left", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -4.0F, -5.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition front_foot_left = front_arm_left.addOrReplaceChild("front_foot_left", CubeListBuilder.create().texOffs(40, 27).addBox(-1.0F, 0.0F, -2.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -1.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition front_left_toe1 = front_foot_left.addOrReplaceChild("front_left_toe1", CubeListBuilder.create().texOffs(42, 23).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 5.25F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition front_left_toe2 = front_foot_left.addOrReplaceChild("front_left_toe2", CubeListBuilder.create().texOffs(24, 48).addBox(0.0F, 4.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -0.25F));

		PartDefinition front_left_toe3 = front_foot_left.addOrReplaceChild("front_left_toe3", CubeListBuilder.create().texOffs(0, 50).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.25F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition front_arm_right = body.addOrReplaceChild("front_arm_right", CubeListBuilder.create().texOffs(18, 36).addBox(-6.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0F, -5.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition front_foot_right = front_arm_right.addOrReplaceChild("front_foot_right", CubeListBuilder.create().texOffs(0, 42).addBox(-2.0F, 0.0F, -2.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition front_right_toe1 = front_foot_right.addOrReplaceChild("front_right_toe1", CubeListBuilder.create().texOffs(8, 50).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.25F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition front_right_toe2 = front_foot_right.addOrReplaceChild("front_right_toe2", CubeListBuilder.create().texOffs(16, 50).addBox(-1.0F, 4.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -0.25F));

		PartDefinition front_right_toe3 = front_foot_right.addOrReplaceChild("front_right_toe3", CubeListBuilder.create().texOffs(50, 23).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 5.25F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition back_leg_left = body.addOrReplaceChild("back_leg_left", CubeListBuilder.create().texOffs(36, 36).addBox(-0.1152F, -1.5F, -1.5793F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -4.0F, 5.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition back_foot_left = back_leg_left.addOrReplaceChild("back_foot_left", CubeListBuilder.create().texOffs(12, 42).addBox(-1.0F, 0.0F, -2.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3848F, -1.0F, -0.0793F, 0.0F, -0.3927F, 0.0F));

		PartDefinition back_left_toe1 = back_foot_left.addOrReplaceChild("back_left_toe1", CubeListBuilder.create().texOffs(50, 47).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 5.25F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition back_left_toe2 = back_foot_left.addOrReplaceChild("back_left_toe2", CubeListBuilder.create().texOffs(50, 51).addBox(0.0F, 4.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -0.25F));

		PartDefinition back_left_toe3 = back_foot_left.addOrReplaceChild("back_left_toe3", CubeListBuilder.create().texOffs(52, 6).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.25F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition back_leg_right = body.addOrReplaceChild("back_leg_right", CubeListBuilder.create().texOffs(40, 0).addBox(-4.8848F, -1.5F, -1.5793F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0F, 5.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition back_foot_right = back_leg_right.addOrReplaceChild("back_foot_right", CubeListBuilder.create().texOffs(42, 15).addBox(-2.0F, 0.0F, -2.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3848F, -1.0F, -0.0793F, 0.0F, 0.3927F, 0.0F));

		PartDefinition back_right_toe1 = back_foot_right.addOrReplaceChild("back_right_toe1", CubeListBuilder.create().texOffs(52, 10).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.25F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition back_right_toe2 = back_foot_right.addOrReplaceChild("back_right_toe2", CubeListBuilder.create().texOffs(24, 52).addBox(-1.0F, 4.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -0.25F));

		PartDefinition back_right_toe3 = back_foot_right.addOrReplaceChild("back_right_toe3", CubeListBuilder.create().texOffs(52, 27).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 5.25F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 18).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 6.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(22, 18).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, 6.75F, 0.0873F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, 0.0F, -0.25F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, 6.75F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);

		if (entity.isAngry()) {
			this.jaw.xRot = 0.3F;
		} else {
			this.jaw.xRot = 0.0F;
		}

		this.front_arm_left.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + 0.3927F;
		this.front_arm_left.zRot = Mth.cos(limbSwing * 0.6662F) * 0.25F * limbSwingAmount;
		this.front_arm_right.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount - 0.3927F;
		this.front_arm_right.zRot = Mth.cos(limbSwing * 0.6662F) * 0.25F * limbSwingAmount;

		this.back_leg_left.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount - 0.3927F;
		this.back_leg_left.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.25F * limbSwingAmount;
		this.back_leg_right.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + 0.3927F;
		this.back_leg_right.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.25F * limbSwingAmount;

		this.tail1.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail1.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.tail2.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail2.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.tail3.yRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
		this.tail3.yRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
	}
}