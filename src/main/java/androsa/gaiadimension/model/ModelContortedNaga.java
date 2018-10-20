package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelContortedNaga - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelContortedNaga extends ModelBase {
    public ModelRenderer bodyTop;
    public ModelRenderer bodyTorso;
    public ModelRenderer upperArmL;
    public ModelRenderer tendril1;
    public ModelRenderer neck;
    public ModelRenderer miniTendril1;
    public ModelRenderer bodyLower;
    public ModelRenderer tailTop;
    public ModelRenderer tailBottom;
    public ModelRenderer lowerArmL;
    public ModelRenderer finger1;
    public ModelRenderer finger2;
    public ModelRenderer decal1;
    public ModelRenderer decal2;
    public ModelRenderer tendril2;
    public ModelRenderer tendril3;
    public ModelRenderer head;
    public ModelRenderer lowerJaw;
    public ModelRenderer upperJaw;
    public ModelRenderer browL;
    public ModelRenderer browR;
    public ModelRenderer tooth1;
    public ModelRenderer tooth2;
    public ModelRenderer miniTendril2;
    public ModelRenderer miniTendril3;

    public ModelContortedNaga() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.decal2 = new ModelRenderer(this, 0, 29);
        this.decal2.setRotationPoint(2.3F, -5.0F, 0.0F);
        this.decal2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7, 0.0F);
        this.setRotateAngle(decal2, 0.9075712110370513F, -0.8726646259971648F, 0.0F);
        this.bodyTorso = new ModelRenderer(this, 54, 0);
        this.bodyTorso.setRotationPoint(0.0F, 9.0F, 1.9F);
        this.bodyTorso.addBox(-5.5F, 0.0F, 0.0F, 11, 13, 6, 0.0F);
        this.setRotateAngle(bodyTorso, -0.17453292519943295F, 0.0F, 0.0F);
        this.miniTendril1 = new ModelRenderer(this, 0, 18);
        this.miniTendril1.setRotationPoint(-7.0F, 2.9F, 5.5F);
        this.miniTendril1.addBox(-3.0F, 0.0F, -1.3F, 3, 8, 3, 0.0F);
        this.setRotateAngle(miniTendril1, -0.13962634015954636F, 0.0F, 0.9075712110370513F);
        this.tendril2 = new ModelRenderer(this, 110, 32);
        this.tendril2.setRotationPoint(0.3F, 11.5F, -1.9F);
        this.tendril2.addBox(-1.5F, 0.0F, 0.0F, 3, 11, 3, 0.0F);
        this.setRotateAngle(tendril2, 0.9075712110370513F, 0.0F, -0.3141592653589793F);
        this.bodyLower = new ModelRenderer(this, 12, 18);
        this.bodyLower.setRotationPoint(0.0F, 11.0F, 1.1F);
        this.bodyLower.addBox(-4.0F, 0.0F, 0.0F, 8, 13, 5, 0.0F);
        this.setRotateAngle(bodyLower, -0.3490658503988659F, 0.0F, 0.0F);
        this.tailBottom = new ModelRenderer(this, 94, 18);
        this.tailBottom.setRotationPoint(0.0F, 5.0F, 12.0F);
        this.tailBottom.addBox(-2.5F, -4.0F, 0.0F, 5, 4, 10, 0.0F);
        this.browL = new ModelRenderer(this, 114, 18);
        this.browL.setRotationPoint(3.9F, -2.4F, -6.0F);
        this.browL.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 5, 0.0F);
        this.setRotateAngle(browL, 0.17453292519943295F, 0.0F, 0.0F);
        this.miniTendril2 = new ModelRenderer(this, 0, 37);
        this.miniTendril2.setRotationPoint(-1.6F, 7.0F, 0.2F);
        this.miniTendril2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(miniTendril2, 0.0F, 0.0F, -0.6108652381980153F);
        this.miniTendril3 = new ModelRenderer(this, 46, 0);
        this.miniTendril3.setRotationPoint(0.0F, 6.7F, 0.0F);
        this.miniTendril3.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(miniTendril3, 0.0F, 0.0F, 0.45378560551852565F);
        this.lowerArmL = new ModelRenderer(this, 70, 32);
        this.lowerArmL.setRotationPoint(10.4F, 0.0F, 0.0F);
        this.lowerArmL.addBox(0.0F, -5.0F, -2.9F, 14, 6, 6, 0.0F);
        this.setRotateAngle(lowerArmL, 0.0F, 0.4553564018453205F, 0.8112290363269642F);
        this.decal1 = new ModelRenderer(this, 72, 19);
        this.decal1.setRotationPoint(0.5F, -4.8F, 0.0F);
        this.decal1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(decal1, 0.24434609527920614F, -0.9948376736367678F, 0.0F);
        this.browR = new ModelRenderer(this, 38, 22);
        this.browR.setRotationPoint(-3.9F, -2.4F, -6.0F);
        this.browR.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 5, 0.0F);
        this.setRotateAngle(browR, 0.17453292519943295F, 0.0F, 0.0F);
        this.bodyTop = new ModelRenderer(this, 0, 0);
        this.bodyTop.setRotationPoint(0.0F, -8.0F, -8.0F);
        this.bodyTop.addBox(-9.5F, 0.0F, 0.0F, 19, 10, 8, 0.0F);
        this.setRotateAngle(bodyTop, 0.3665191429188092F, 0.0F, 0.0F);
        this.finger2 = new ModelRenderer(this, 64, 19);
        this.finger2.setRotationPoint(13.6F, -3.5F, -2.0F);
        this.finger2.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(finger2, 0.0F, -0.47123889803846897F, 0.0F);
        this.head = new ModelRenderer(this, 22, 36);
        this.head.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.head.addBox(-3.5F, -4.0F, -7.1F, 7, 7, 7, 0.0F);
        this.setRotateAngle(head, -0.3141592653589793F, 0.0F, 0.0F);
        this.finger1 = new ModelRenderer(this, 33, 18);
        this.finger1.setRotationPoint(13.6F, -0.5F, -2.0F);
        this.finger1.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(finger1, 0.0F, -0.47123889803846897F, 0.0F);
        this.tendril3 = new ModelRenderer(this, 14, 36);
        this.tendril3.setRotationPoint(0.0F, 9.5F, 1.0F);
        this.tendril3.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tendril3, -0.7155849933176751F, 0.0F, 0.3141592653589793F);
        this.lowerJaw = new ModelRenderer(this, 50, 37);
        this.lowerJaw.setRotationPoint(0.0F, 0.0F, -7.0F);
        this.lowerJaw.addBox(-3.0F, 0.0F, -7.0F, 6, 3, 7, 0.0F);
        this.upperJaw = new ModelRenderer(this, 70, 44);
        this.upperJaw.setRotationPoint(0.0F, 0.0F, -7.0F);
        this.upperJaw.addBox(-2.5F, -3.0F, -6.0F, 5, 3, 6, 0.0F);
        this.tendril1 = new ModelRenderer(this, 88, 8);
        this.tendril1.setRotationPoint(-9.0F, 1.0F, 2.0F);
        this.tendril1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(tendril1, -0.2565634000431664F, 0.0F, 0.19198621771937624F);
        this.tailTop = new ModelRenderer(this, 38, 19);
        this.tailTop.setRotationPoint(0.0F, 8.8F, 0.4F);
        this.tailTop.addBox(-3.5F, 0.0F, 0.0F, 7, 5, 12, 0.0F);
        this.setRotateAngle(tailTop, 0.15707963267948966F, 0.0F, 0.017453292519943295F);
        this.tooth1 = new ModelRenderer(this, 0, 0);
        this.tooth1.setRotationPoint(2.7F, 0.6F, -5.5F);
        this.tooth1.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tooth1, 0.0F, 0.0F, 0.2792526803190927F);
        this.neck = new ModelRenderer(this, 104, 8);
        this.neck.setRotationPoint(0.0F, 0.0F, 5.3F);
        this.neck.addBox(-3.0F, -5.0F, -5.0F, 6, 5, 5, 0.0F);
        this.setRotateAngle(neck, 0.2617993877991494F, 0.0F, 0.0F);
        this.upperArmL = new ModelRenderer(this, 88, 0);
        this.upperArmL.setRotationPoint(7.1F, 2.5F, 5.7F);
        this.upperArmL.addBox(0.0F, -2.5F, -2.5F, 14, 4, 4, 0.0F);
        this.setRotateAngle(upperArmL, 0.01641330483365508F, 0.3141592653589793F, 0.7853981633974483F);
        this.tooth2 = new ModelRenderer(this, 4, 0);
        this.tooth2.setRotationPoint(-2.7F, 0.6F, -4.5F);
        this.tooth2.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(tooth2, 0.0F, 0.0F, -0.12217304763960307F);
        this.lowerArmL.addChild(this.decal2);
        this.bodyTop.addChild(this.bodyTorso);
        this.bodyTop.addChild(this.miniTendril1);
        this.tendril1.addChild(this.tendril2);
        this.bodyTorso.addChild(this.bodyLower);
        this.tailTop.addChild(this.tailBottom);
        this.head.addChild(this.browL);
        this.miniTendril1.addChild(this.miniTendril2);
        this.miniTendril2.addChild(this.miniTendril3);
        this.upperArmL.addChild(this.lowerArmL);
        this.lowerArmL.addChild(this.decal1);
        this.head.addChild(this.browR);
        this.lowerArmL.addChild(this.finger2);
        this.neck.addChild(this.head);
        this.lowerArmL.addChild(this.finger1);
        this.tendril2.addChild(this.tendril3);
        this.head.addChild(this.lowerJaw);
        this.head.addChild(this.upperJaw);
        this.bodyTop.addChild(this.tendril1);
        this.bodyLower.addChild(this.tailTop);
        this.lowerJaw.addChild(this.tooth1);
        this.bodyTop.addChild(this.neck);
        this.bodyTop.addChild(this.upperArmL);
        this.lowerJaw.addChild(this.tooth2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bodyTop.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity par7Entity) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI) + -0.3141592653589793F;

        this.upperArmL.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.upperArmL.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F - 0.3141592653589793F;


        this.tendril1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tendril1.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.2F + 0.2565634000431664F;
        this.tendril2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tendril2.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.25F - 0.9075712110370513F;
        this.tendril3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tendril3.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.3F + 0.7155849933176751F;


        this.miniTendril1.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.miniTendril1.rotateAngleZ -= MathHelper.sin(ageInTicks * 0.067F) * 0.1F - 0.9075712110370513F;
        this.miniTendril2.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.miniTendril2.rotateAngleZ -= MathHelper.sin(ageInTicks * 0.067F) * 0.15F + 0.6108652381980153F;
        this.miniTendril3.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.miniTendril3.rotateAngleZ -= MathHelper.sin(ageInTicks * 0.067F) * 0.2F - 0.45378560551852565F;

    }
}
