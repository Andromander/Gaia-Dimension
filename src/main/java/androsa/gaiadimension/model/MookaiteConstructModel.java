package androsa.gaiadimension.model;
// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings

import androsa.gaiadimension.entity.MookaiteConstruct;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class MookaiteConstructModel<T extends MookaiteConstruct> extends HierarchicalModel<T> {
	private final ModelPart root;

	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart right_arm_upper;
	private final ModelPart left_arm_upper;
	private final ModelPart right_leg_upper;
	private final ModelPart left_leg_upper;
	//visible parts
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart right_eye;
	private final ModelPart left_eye;
	private final ModelPart right_shoulder;
	private final ModelPart left_shoulder;
	private final ModelPart right_arm_brace;
	private final ModelPart left_arm_brace;
	private final ModelPart right_leg_brace;
	private final ModelPart left_leg_brace;

	public MookaiteConstructModel(ModelPart root) {
		this.root = root;

		ModelPart body = root.getChild("body");
		this.torso = body.getChild("torso");
		this.head = torso.getChild("neck").getChild("head");
		this.right_arm_upper = torso.getChild("right_arm_upper");
		this.left_arm_upper = torso.getChild("left_arm_upper");
		this.right_leg_upper = root.getChild("right_leg_upper");
		this.left_leg_upper = root.getChild("left_leg_upper");
		//visible parts
		this.right_horn = this.head.getChild("right_ear").getChild("right_horn");
		this.left_horn = this.head.getChild("left_ear").getChild("left_horn");
		this.right_eye = this.head.getChild("right_eye");
		this.left_eye = this.head.getChild("left_eye");
		this.right_shoulder = right_arm_upper.getChild("right_shoulder");
		this.left_shoulder = left_arm_upper.getChild("left_shoulder");
		this.right_arm_brace = right_arm_upper.getChild("right_arm_lower").getChild("right_arm_brace");
		this.left_arm_brace = left_arm_upper.getChild("left_arm_lower").getChild("left_arm_brace");
		this.right_leg_brace = right_leg_upper.getChild("right_leg_lower").getChild("right_leg_brace");
		this.left_leg_brace = left_leg_upper.getChild("left_leg_lower").getChild("left_leg_brace");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	public static LayerDefinition makeBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -10.5F, 0.0F));
		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-10.0F, -7.0F, -4.8042F, 20.0F, 7.0F, 8.0F),
				PartPose.offset(0.0F, 3.5F, 0.0F));
		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create()
						.texOffs(73, 76)
						.addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F),
				PartPose.offsetAndRotation(0.0F, -6.0F, -3.5F, 0.7418F, 0.0F, 0.0F));
		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(40, 66)
						.addBox(-3.0F, -6.4183F, -2.9959F, 6.0F, 7.0F, 3.0F),
				PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7418F, 0.0F, 0.0F));
		head.addOrReplaceChild("right_eye", CubeListBuilder.create()
						.texOffs(0, 54)
						.addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F),
				PartPose.offset(-2.5F, -2.9183F, -2.9959F));
		head.addOrReplaceChild("left_eye", CubeListBuilder.create()
						.texOffs(40, 33)
						.addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F),
				PartPose.offset(2.5F, -2.9183F, -2.9959F));
		head.addOrReplaceChild("chin", CubeListBuilder.create()
						.texOffs(27, 15)
						.addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 1.0F),
				PartPose.offset(0.0F, 0.5817F, -1.9959F));
		head.addOrReplaceChild("sail", CubeListBuilder.create()
						.texOffs(53, 71)
						.addBox(-0.5F, -8.0F, 0.0F, 1.0F, 8.0F, 5.0F),
				PartPose.offset(0.0F, -2.4183F, -2.4959F));
		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create()
						.texOffs(28, 76)
						.addBox(-3.0F, -6.0F, -0.5F, 3.0F, 6.0F, 2.0F),
				PartPose.offset(-2.0F, -2.4183F, -0.9959F));
		right_ear.addOrReplaceChild("right_horn", CubeListBuilder.create()
						.texOffs(65, 76)
						.addBox(-2.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F),
				PartPose.offset(-3.0F, -2.0F, 0.5F));
		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create()
						.texOffs(28, 47)
						.addBox(0.0F, -6.0F, -0.5F, 3.0F, 6.0F, 2.0F),
				PartPose.offset(2.0F, -2.4183F, -0.9959F));
		left_ear.addOrReplaceChild("left_horn", CubeListBuilder.create()
						.texOffs(38, 76)
						.addBox(0.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F),
				PartPose.offset(3.0F, -2.0F, 0.5F));
		torso.addOrReplaceChild("right_fin", CubeListBuilder.create()
						.texOffs(17, 47)
						.addBox(-0.5F, -9.0F, 0.0F, 1.0F, 9.0F, 9.0F),
				PartPose.offset(-4.5F, -2.0F, 0.0F));
		torso.addOrReplaceChild("left_fin", CubeListBuilder.create()
						.texOffs(38, 38)
						.addBox(-0.5F, -9.0F, 0.0F, 1.0F, 9.0F, 9.0F),
				PartPose.offset(4.5F, -2.0F, 0.0F));
		body.addOrReplaceChild("lower_torso", CubeListBuilder.create()
						.texOffs(20, 65)
						.addBox(-3.0F, 0.0F, -2.3042F, 6.0F, 7.0F, 4.0F),
				PartPose.offset(0.0F, 3.5F, -0.5F));
		body.addOrReplaceChild("torso_shell_1", CubeListBuilder.create()
						.texOffs(37, 58)
						.addBox(-4.0F, -1.0F, -2.8042F, 8.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, 5.5F, -1.0F));
		body.addOrReplaceChild("torso_shell_2", CubeListBuilder.create()
						.texOffs(52, 50)
						.addBox(-4.0F, -1.0F, -4.8042F, 8.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, 8.5F, 1.0F));
		body.addOrReplaceChild("torso_shell_3", CubeListBuilder.create()
						.texOffs(48, 0)
						.addBox(-4.0F, -1.0F, -4.8042F, 8.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, 11.5F, 1.0F));
		body.addOrReplaceChild("waist", CubeListBuilder.create()
						.texOffs(72, 27)
						.addBox(-2.0F, 0.0F, -3.0F, 4.0F, 3.0F, 4.0F),
				PartPose.offset(0.0F, 12.5F, 0.5F));
		body.addOrReplaceChild("hip", CubeListBuilder.create()
						.texOffs(0, 15)
						.addBox(-5.0F, -2.0F, -3.5F, 10.0F, 4.0F, 7.0F),
				PartPose.offset(0.0F, 17.5F, -0.5F));

		PartDefinition right_arm_upper = torso.addOrReplaceChild("right_arm_upper", CubeListBuilder.create()
						.texOffs(20, 76)
						.addBox(-2.0F, 0.1958F, -1.0F, 2.0F, 10.0F, 2.0F),
				PartPose.offsetAndRotation(-10.0F, -5.0F, -1.0F, 0.2182F, 0.0F, 0.0F));
		right_arm_upper.addOrReplaceChild("right_shoulder", CubeListBuilder.create()
						.texOffs(27, 19)
						.addBox(-7.0F, -3.4021F, -3.3941F, 7.0F, 7.0F, 7.0F),
				PartPose.offsetAndRotation(1.0F, -1.5F, 0.5F, -0.2182F, 0.0F, 0.0F));
		PartDefinition right_arm_lower = right_arm_upper.addOrReplaceChild("right_arm_lower", CubeListBuilder.create()
						.texOffs(76, 0)
						.addBox(-0.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F),
				PartPose.offsetAndRotation(-1.0F, 10.0F, 1.0F, -1.5708F, 1.0036F, -1.5708F));
		right_arm_lower.addOrReplaceChild("right_arm_brace", CubeListBuilder.create()
						.texOffs(51, 10)
						.addBox(-3.0F, -5.0F, -2.5F, 6.0F, 10.0F, 5.0F),
				PartPose.offset(1.0F, 6.5F, 0.0F));
		right_arm_lower.addOrReplaceChild("right_finger_1", CubeListBuilder.create()
						.texOffs(21, 26)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(-0.5F, 11.5F, -1.0F));
		right_arm_lower.addOrReplaceChild("right_finger_2", CubeListBuilder.create()
						.texOffs(0, 26)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(1.0F, 11.5F, -1.0F));
		right_arm_lower.addOrReplaceChild("right_finger_3", CubeListBuilder.create()
						.texOffs(3, 18)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(2.5F, 11.5F, -1.0F));
		right_arm_lower.addOrReplaceChild("right_thumb", CubeListBuilder.create()
						.texOffs(27, 18)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F),
				PartPose.offset(2.5F, 11.5F, 1.5F));

		PartDefinition left_arm_upper = torso.addOrReplaceChild("left_arm_upper", CubeListBuilder.create()
						.texOffs(12, 75)
						.addBox(0.0F, 0.1958F, -1.0F, 2.0F, 10.0F, 2.0F),
				PartPose.offsetAndRotation(10.0F, -5.0F, -1.0F, 0.2182F, 0.0F, 0.0F));
		left_arm_upper.addOrReplaceChild("left_shoulder", CubeListBuilder.create()
						.texOffs(0, 26)
						.addBox(0.0F, -3.4021F, -3.3941F, 7.0F, 7.0F, 7.0F),
				PartPose.offsetAndRotation(-1.0F, -1.5F, 0.5F, -0.2182F, 0.0F, 0.0F));
		PartDefinition left_arm_lower = left_arm_upper.addOrReplaceChild("left_arm_lower", CubeListBuilder.create()
						.texOffs(0, 75)
						.addBox(-2.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F),
				PartPose.offsetAndRotation(1.0F, 10.0F, 1.0F, -1.5708F, -1.0105F, 1.5708F));
		left_arm_lower.addOrReplaceChild("left_arm_brace", CubeListBuilder.create()
						.texOffs(50, 28)
						.addBox(-3.0F, -5.0F, -2.5F, 6.0F, 10.0F, 5.0F),
				PartPose.offset(-1.0F, 6.5F, 0.0F));
		left_arm_lower.addOrReplaceChild("left_finger_1", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(0.5F, 11.5F, -1.0F));
		left_arm_lower.addOrReplaceChild("left_finger_2", CubeListBuilder.create()
						.texOffs(3, 3)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(-1.0F, 11.5F, -1.0F));
		left_arm_lower.addOrReplaceChild("left_finger_3", CubeListBuilder.create()
						.texOffs(0, 15)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(-2.5F, 11.5F, -1.0F));
		left_arm_lower.addOrReplaceChild("left_thumb", CubeListBuilder.create()
				.texOffs(4, 0)
				.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F),
				PartPose.offset(-2.5F, 11.5F, 1.5F));

		PartDefinition right_leg_upper = root.addOrReplaceChild("right_leg_upper", CubeListBuilder.create()
				.texOffs(75, 58)
				.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F), PartPose.offset(-4.0F, 9.0F, -0.5F));
		PartDefinition right_leg_lower = right_leg_upper.addOrReplaceChild("right_leg_lower", CubeListBuilder.create()
						.texOffs(60, 61)
						.addBox(-2.5F, 0.0F, -1.0F, 5.0F, 10.0F, 5.0F),
				PartPose.offset(0.0F, 5.0F, -1.5F));
		right_leg_lower.addOrReplaceChild("right_leg_brace", CubeListBuilder.create()
						.texOffs(0, 40)
						.addBox(-3.0F, -3.5F, -3.5F, 6.0F, 7.0F, 7.0F),
				PartPose.offset(0.0F, 5.0F, 1.5F));
		right_leg_lower.addOrReplaceChild("right_foot", CubeListBuilder.create()
						.texOffs(65, 17)
						.addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 8.0F),
				PartPose.offset(0.0F, 9.5F, 1.5F));

		PartDefinition left_leg_upper = root.addOrReplaceChild("left_leg_upper", CubeListBuilder.create()
						.texOffs(74, 45)
						.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F),
				PartPose.offset(4.0F, 9.0F, -0.5F));
		PartDefinition left_leg_lower = left_leg_upper.addOrReplaceChild("left_leg_lower", CubeListBuilder.create()
						.texOffs(0, 60)
						.addBox(-2.5F, 0.0F, -1.0F, 5.0F, 10.0F, 5.0F),
				PartPose.offset(0.0F, 5.0F, -1.5F));
		left_leg_lower.addOrReplaceChild("left_leg_brace", CubeListBuilder.create()
						.texOffs(21, 33)
						.addBox(-3.0F, -3.5F, -3.5F, 6.0F, 7.0F, 7.0F),
				PartPose.offset(0.0F, 5.0F, 1.5F));
		left_leg_lower.addOrReplaceChild("left_foot", CubeListBuilder.create()
						.texOffs(64, 35)
						.addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 8.0F),
				PartPose.offset(0.0F, 9.5F, 1.5F));

		return LayerDefinition.create(mesh, 96, 96);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.torso.yRot = netHeadYaw / (270F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI) - 0.7418F;

		this.right_arm_upper.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount + 0.2182F;
		this.left_arm_upper.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount + 0.2182F;
		this.right_leg_upper.xRot = Mth.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount;
		this.left_leg_upper.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		this.right_horn.visible = entity.getPart(MookaiteConstruct.RIGHT_HORN).isPresent();
		this.left_horn.visible = entity.getPart(MookaiteConstruct.LEFT_HORN).isPresent();
		this.left_eye.visible = entity.getPart(MookaiteConstruct.LEFT_EYE).isPresent();
		this.right_eye.visible = entity.getPart(MookaiteConstruct.RIGHT_EYE).isPresent();
		this.right_shoulder.visible = entity.getPart(MookaiteConstruct.RIGHT_SHOULDER).isPresent();
		this.left_shoulder.visible = entity.getPart(MookaiteConstruct.LEFT_SHOULDER).isPresent();
		this.right_arm_brace.visible = entity.getPart(MookaiteConstruct.RIGHT_ARM).isPresent();
		this.left_arm_brace.visible = entity.getPart(MookaiteConstruct.LEFT_ARM).isPresent();
		this.right_leg_brace.visible = entity.getPart(MookaiteConstruct.RIGHT_LEG).isPresent();
		this.left_leg_brace.visible = entity.getPart(MookaiteConstruct.LEFT_LEG).isPresent();
	}
}