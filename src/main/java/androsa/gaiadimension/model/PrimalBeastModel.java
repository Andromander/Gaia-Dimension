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
        this.texWidth = 96;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 34, 16);
        this.head.setPos(0.0F, -3.0F, -3.0F);
        this.head.addBox(-4.0F, -7.8F, -6.0F, 8, 9, 6, 0.0F);
        this.bodyTop = new ModelRenderer(this, 0, 0);
        this.bodyTop.setPos(0.0F, -5.0F, -3.5F);
        this.bodyTop.addBox(-6.5F, 0.0F, -3.5F, 13, 11, 7, 0.0F);
        this.setRotateAngle(bodyTop, 0.4886921905584123F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 35, 38);
        this.legL.setPos(3.1F, 8.0F, 3.5F);
        this.legL.addBox(-3.0F, 0.0F, -3.0F, 6, 13, 6, 0.0F);
        this.forearmR = new ModelRenderer(this, 15, 31);
        this.forearmR.setPos(-2.4F, 11.5F, 0.4F);
        this.forearmR.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(forearmR, -0.6108652381980153F, 0.0F, 0.0F);
        this.tailBottom = new ModelRenderer(this, 0, 40);
        this.tailBottom.setPos(0.0F, 3.6F, 9.4F);
        this.tailBottom.addBox(-1.5F, -2.0F, 0.0F, 3, 2, 9, 0.0F);
        this.setRotateAngle(tailBottom, 0.33161255787892263F, 0.0F, 0.0F);
        this.armR = new ModelRenderer(this, 0, 18);
        this.armR.setPos(-6.5F, 0.9F, 0.0F);
        this.armR.addBox(-5.0F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(armR, -0.33161255787892263F, 0.0F, 0.0F);
        this.tailtop = new ModelRenderer(this, 49, 47);
        this.tailtop.setPos(0.0F, 4.0F, 7.0F);
        this.tailtop.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(tailtop, -0.593411945678072F, 0.0F, 0.0F);
        this.fingersL = new ModelRenderer(this, 78, 0);
        this.fingersL.setPos(0.5F, 12.0F, 0.0F);
        this.fingersL.addBox(0.0F, 0.0F, -2.4F, 2, 5, 5, 0.0F);
        this.setRotateAngle(fingersL, 0.0F, 0.0F, 0.3141592653589793F);
        this.armL = new ModelRenderer(this, 73, 11);
        this.armL.setPos(6.5F, 0.9F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(armL, -0.33161255787892263F, 0.0F, 0.0F);
        this.bodyLower = new ModelRenderer(this, 40, 0);
        this.bodyLower.setPos(0.0F, 3.0F, -2.2F);
        this.bodyLower.addBox(-6.0F, 0.0F, 0.0F, 12, 9, 7, 0.0F);
        this.legR = new ModelRenderer(this, 71, 38);
        this.legR.setPos(-2.9F, 8.0F, 3.5F);
        this.legR.addBox(-3.0F, 0.0F, -3.0F, 6, 13, 6, 0.0F);
        this.forearmL = new ModelRenderer(this, 57, 26);
        this.forearmL.setPos(2.4F, 11.5F, 0.4F);
        this.forearmL.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(forearmL, -0.6108652381980153F, 0.0F, 0.0F);
        this.fingersR = new ModelRenderer(this, 20, 18);
        this.fingersR.setPos(-2.5F, 12.0F, 0.0F);
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
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.bodyTop, this.bodyLower);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.armR.zRot = 0.0F;
        this.armL.zRot = 0.0F;
        this.armR.xRot = -0.33161255787892263F;
        this.armL.xRot = -0.33161255787892263F;
        this.armR.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.armL.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.15F;
        this.armR.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        this.armL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount + -0.33161255787892263F;
        this.armR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount + -0.33161255787892263F;

        this.fingersL.zRot = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 0.15F + 0.3F;
        this.fingersR.zRot = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * -0.15F + -0.3F;

        this.legL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.legR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.tailtop.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tailtop.yRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.tailBottom.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.tailBottom.yRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
