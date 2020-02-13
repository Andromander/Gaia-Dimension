package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.PrimalBeastEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelPrimalBeast - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class PrimalBeastModel<T extends PrimalBeastEntity> extends SegmentedModel<T> {
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

    public PrimalBeastModel() {
        this.textureWidth = 96;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 34, 16);
        this.head.setRotationPoint(0.0F, -3.0F, -3.0F);
        this.head.addCuboid(-4.0F, -7.8F, -6.0F, 8, 9, 6, 0.0F);
        this.bodyTop = new ModelRenderer(this, 0, 0);
        this.bodyTop.setRotationPoint(0.0F, -5.0F, -3.5F);
        this.bodyTop.addCuboid(-6.5F, 0.0F, -3.5F, 13, 11, 7, 0.0F);
        this.setRotateAngle(bodyTop, 0.4886921905584123F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 35, 38);
        this.legL.setRotationPoint(3.1F, 8.0F, 3.5F);
        this.legL.addCuboid(-3.0F, 0.0F, -3.0F, 6, 13, 6, 0.0F);
        this.forearmR = new ModelRenderer(this, 15, 31);
        this.forearmR.setRotationPoint(-2.4F, 11.5F, 0.4F);
        this.forearmR.addCuboid(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(forearmR, -0.6108652381980153F, 0.0F, 0.0F);
        this.tailBottom = new ModelRenderer(this, 0, 40);
        this.tailBottom.setRotationPoint(0.0F, 3.6F, 9.4F);
        this.tailBottom.addCuboid(-1.5F, -2.0F, 0.0F, 3, 2, 9, 0.0F);
        this.setRotateAngle(tailBottom, 0.33161255787892263F, 0.0F, 0.0F);
        this.armR = new ModelRenderer(this, 0, 18);
        this.armR.setRotationPoint(-6.5F, 0.9F, 0.0F);
        this.armR.addCuboid(-5.0F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(armR, -0.33161255787892263F, 0.0F, 0.0F);
        this.tailtop = new ModelRenderer(this, 49, 47);
        this.tailtop.setRotationPoint(0.0F, 4.0F, 7.0F);
        this.tailtop.addCuboid(-2.5F, 0.0F, 0.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(tailtop, -0.593411945678072F, 0.0F, 0.0F);
        this.fingersL = new ModelRenderer(this, 78, 0);
        this.fingersL.setRotationPoint(0.5F, 12.0F, 0.0F);
        this.fingersL.addCuboid(0.0F, 0.0F, -2.4F, 2, 5, 5, 0.0F);
        this.setRotateAngle(fingersL, 0.0F, 0.0F, 0.3141592653589793F);
        this.armL = new ModelRenderer(this, 73, 11);
        this.armL.setRotationPoint(6.5F, 0.9F, 0.0F);
        this.armL.addCuboid(0.0F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(armL, -0.33161255787892263F, 0.0F, 0.0F);
        this.bodyLower = new ModelRenderer(this, 40, 0);
        this.bodyLower.setRotationPoint(0.0F, 3.0F, -2.2F);
        this.bodyLower.addCuboid(-6.0F, 0.0F, 0.0F, 12, 9, 7, 0.0F);
        this.legR = new ModelRenderer(this, 71, 38);
        this.legR.setRotationPoint(-2.9F, 8.0F, 3.5F);
        this.legR.addCuboid(-3.0F, 0.0F, -3.0F, 6, 13, 6, 0.0F);
        this.forearmL = new ModelRenderer(this, 57, 26);
        this.forearmL.setRotationPoint(2.4F, 11.5F, 0.4F);
        this.forearmL.addCuboid(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(forearmL, -0.6108652381980153F, 0.0F, 0.0F);
        this.fingersR = new ModelRenderer(this, 20, 18);
        this.fingersR.setRotationPoint(-2.5F, 12.0F, 0.0F);
        this.fingersR.addCuboid(0.0F, 0.0F, -2.4F, 2, 5, 5, 0.0F);
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
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.head, this.bodyTop, this.bodyLower);
    }

//    @Override
//    public void render(PrimalBeastEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.head.render(f5);
//        this.bodyTop.render(f5);
//        this.bodyLower.render(f5);
//    }

    @Override
    public void setAngles(T entity, float ageInTicks, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.armR.rotateAngleZ = 0.0F;
        this.armL.rotateAngleZ = 0.0F;
        this.armR.rotateAngleX = -0.33161255787892263F;
        this.armL.rotateAngleX = -0.33161255787892263F;
        this.armR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.armL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.armR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        this.armL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount + -0.33161255787892263F;
        this.armR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount + -0.33161255787892263F;

        this.fingersL.rotateAngleZ = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F + 0.3F;
        this.fingersR.rotateAngleZ = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * -0.15F + -0.3F;

        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.tailtop.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tailtop.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.tailBottom.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tailBottom.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
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
