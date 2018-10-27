package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPrimalBeast - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelPrimalBeast extends ModelBase {
    public ModelRenderer bodyTop;
    public ModelRenderer bodyLower;
    public ModelRenderer head;
    public ModelRenderer armL;
    public ModelRenderer armR;
    public ModelRenderer forearmL;
    public ModelRenderer fingersL;
    public ModelRenderer forearmR;
    public ModelRenderer fingersR;
    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer tailtop;
    public ModelRenderer tailBottom;

    public ModelPrimalBeast() {
        this.textureWidth = 96;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 34, 16);
        this.head.setRotationPoint(0.0F, -3.0F, -3.0F);
        this.head.addBox(-4.0F, -7.8F, -6.0F, 8, 9, 6, 0.0F);
        this.bodyTop = new ModelRenderer(this, 0, 0);
        this.bodyTop.setRotationPoint(0.0F, -5.0F, -3.5F);
        this.bodyTop.addBox(-6.5F, 0.0F, -3.5F, 13, 11, 7, 0.0F);
        this.setRotateAngle(bodyTop, 0.4886921905584123F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 35, 38);
        this.legL.setRotationPoint(3.1F, 8.0F, 3.5F);
        this.legL.addBox(-3.0F, 0.0F, -3.0F, 6, 13, 6, 0.0F);
        this.forearmR = new ModelRenderer(this, 15, 31);
        this.forearmR.setRotationPoint(-2.4F, 11.5F, 0.4F);
        this.forearmR.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(forearmR, -0.6108652381980153F, 0.0F, 0.0F);
        this.tailBottom = new ModelRenderer(this, 0, 40);
        this.tailBottom.setRotationPoint(0.0F, 3.6F, 9.4F);
        this.tailBottom.addBox(-1.5F, -2.0F, 0.0F, 3, 2, 9, 0.0F);
        this.setRotateAngle(tailBottom, 0.33161255787892263F, 0.0F, 0.0F);
        this.armR = new ModelRenderer(this, 0, 18);
        this.armR.setRotationPoint(-6.5F, 0.9F, 0.0F);
        this.armR.addBox(-5.0F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(armR, -0.33161255787892263F, 0.0F, 0.0F);
        this.tailtop = new ModelRenderer(this, 49, 47);
        this.tailtop.setRotationPoint(0.0F, 4.0F, 7.0F);
        this.tailtop.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(tailtop, -0.593411945678072F, 0.0F, 0.0F);
        this.fingersL = new ModelRenderer(this, 78, 0);
        this.fingersL.setRotationPoint(0.5F, 12.0F, 0.0F);
        this.fingersL.addBox(0.0F, 0.0F, -2.4F, 2, 5, 5, 0.0F);
        this.setRotateAngle(fingersL, 0.0F, 0.0F, 0.3141592653589793F);
        this.armL = new ModelRenderer(this, 73, 11);
        this.armL.setRotationPoint(6.5F, 0.9F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(armL, -0.33161255787892263F, 0.0F, 0.0F);
        this.bodyLower = new ModelRenderer(this, 40, 0);
        this.bodyLower.setRotationPoint(0.0F, 3.0F, -2.2F);
        this.bodyLower.addBox(-6.0F, 0.0F, 0.0F, 12, 9, 7, 0.0F);
        this.legR = new ModelRenderer(this, 71, 38);
        this.legR.setRotationPoint(-2.9F, 8.0F, 3.5F);
        this.legR.addBox(-3.0F, 0.0F, -3.0F, 6, 13, 6, 0.0F);
        this.forearmL = new ModelRenderer(this, 57, 26);
        this.forearmL.setRotationPoint(2.4F, 11.5F, 0.4F);
        this.forearmL.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(forearmL, -0.6108652381980153F, 0.0F, 0.0F);
        this.fingersR = new ModelRenderer(this, 20, 18);
        this.fingersR.setRotationPoint(-2.5F, 12.0F, 0.0F);
        this.fingersR.addBox(0.0F, 0.0F, -2.4F, 2, 5, 5, 0.0F);
        this.setRotateAngle(fingersR, 0.0F, 0.0F, -0.3141592653589793F);
        this.bodyLower.addChild(this.legL);
        this.armR.addChild(this.forearmR);
        this.tailtop.addChild(this.tailBottom);
        this.bodyTop.addChild(this.armR);
        this.bodyLower.addChild(this.tailtop);
        this.forearmL.addChild(this.fingersL);
        this.bodyTop.addChild(this.armL);
        this.bodyLower.addChild(this.legR);
        this.armL.addChild(this.forearmL);
        this.forearmR.addChild(this.fingersR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
        this.bodyTop.render(f5);
        this.bodyLower.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
