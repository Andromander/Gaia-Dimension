package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.RuggedLurmorusEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelRuggedLurmorus - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class RuggedLurmorusModel<T extends RuggedLurmorusEntity> extends SegmentedModel<T> {
    public ModelRenderer body;
    public ModelRenderer shoulderL;
    public ModelRenderer shoulderR;
    public ModelRenderer upperLegL;
    public ModelRenderer upperLegR;
    public ModelRenderer tailBase;
    public ModelRenderer neckLow;
    public ModelRenderer neckMid;
    public ModelRenderer neckHigh;
    public ModelRenderer neckSailL;
    public ModelRenderer head;
    public ModelRenderer neckSailU;
    public ModelRenderer noseSail;
    public ModelRenderer upperArmL;
    public ModelRenderer lowerArmL;
    public ModelRenderer hoofL;
    public ModelRenderer upperArmR;
    public ModelRenderer lowerArmR;
    public ModelRenderer hoofR;
    public ModelRenderer lowerLegL;
    public ModelRenderer hoofLL;
    public ModelRenderer lowerLegR;
    public ModelRenderer hoofRR;
    public ModelRenderer tailpart1;
    public ModelRenderer tailpart2;
    public ModelRenderer tailpart3;

    public RuggedLurmorusModel() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.head = new ModelRenderer(this, 0, 32);
        this.head.setPos(0.0F, -8.6F, 0.7F);
        this.head.addBox(-2.5F, -4.0F, -6.0F, 5, 5, 6, 0.0F);
        this.setRotateAngle(head, -0.1832595714594046F, 0.0F, 0.0F);
        this.neckSailU = new ModelRenderer(this, 104, 30);
        this.neckSailU.setPos(0.0F, -14.0F, 0.0F);
        this.neckSailU.addBox(-0.5F, 0.0F, 0.0F, 1, 15, 6, 0.0F);
        this.tailBase = new ModelRenderer(this, 82, 4);
        this.tailBase.setPos(0.0F, 0.9F, 12.0F);
        this.tailBase.addBox(-4.0F, 0.0F, 0.0F, 8, 7, 10, 0.0F);
        this.setRotateAngle(tailBase, -0.2617993877991494F, 0.0F, 0.0F);
        this.tailpart1 = new ModelRenderer(this, 65, 41);
        this.tailpart1.setPos(0.0F, 0.2F, 9.0F);
        this.tailpart1.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 9, 0.0F);
        this.setRotateAngle(tailpart1, 0.12217304763960307F, 0.0F, 0.0F);
        this.noseSail = new ModelRenderer(this, 22, 32);
        this.noseSail.setPos(0.0F, -5.4F, -8.0F);
        this.noseSail.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 7, 0.0F);
        this.hoofRR = new ModelRenderer(this, 44, 46);
        this.hoofRR.setPos(-1.5F, 10.1F, 1.8F);
        this.hoofRR.addBox(-2.0F, 0.0F, -4.0F, 4, 2, 4, 0.0F);
        this.upperArmR = new ModelRenderer(this, 56, 38);
        this.upperArmR.setPos(-1.8F, 7.0F, -2.7F);
        this.upperArmR.addBox(-2.0F, 0.0F, 0.0F, 4, 7, 5, 0.0F);
        this.lowerArmR = new ModelRenderer(this, 0, 43);
        this.lowerArmR.setPos(0.0F, 6.5F, 0.1F);
        this.lowerArmR.addBox(-2.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.hoofR = new ModelRenderer(this, 40, 14);
        this.hoofR.setPos(0.0F, 7.5F, 3.4F);
        this.hoofR.addBox(-2.5F, 0.0F, -5.0F, 5, 3, 5, 0.0F);
        this.neckMid = new ModelRenderer(this, 90, 21);
        this.neckMid.setPos(0.0F, -7.7F, -0.8F);
        this.neckMid.addBox(-2.5F, -10.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(neckMid, 0.091106186954104F, 0.0F, 0.0F);
        this.tailpart2 = new ModelRenderer(this, 87, 50);
        this.tailpart2.setPos(0.0F, 0.2F, 8.0F);
        this.tailpart2.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8, 0.0F);
        this.setRotateAngle(tailpart2, 0.18203784098300857F, 0.0F, 0.0F);
        this.upperLegR = new ModelRenderer(this, 76, 0);
        this.upperLegR.setPos(-4.0F, 4.0F, 9.0F);
        this.upperLegR.addBox(-3.0F, 0.0F, -2.5F, 3, 9, 5, 0.0F);
        this.neckSailL = new ModelRenderer(this, 113, 16);
        this.neckSailL.setPos(0.0F, -10.0F, 2.0F);
        this.neckSailL.addBox(-0.5F, 0.0F, 0.0F, 1, 14, 5, 0.0F);
        this.lowerArmL = new ModelRenderer(this, 86, 36);
        this.lowerArmL.setPos(0.0F, 6.5F, 0.1F);
        this.lowerArmL.addBox(-2.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.hoofL = new ModelRenderer(this, 0, 14);
        this.hoofL.setPos(0.0F, 7.5F, 3.4F);
        this.hoofL.addBox(-2.5F, 0.0F, -5.0F, 5, 3, 5, 0.0F);
        this.neckLow = new ModelRenderer(this, 62, 21);
        this.neckLow.setPos(0.0F, 5.0F, -9.0F);
        this.neckLow.addBox(-3.5F, -8.0F, -3.5F, 7, 10, 7, 0.0F);
        this.hoofLL = new ModelRenderer(this, 60, 14);
        this.hoofLL.setPos(1.5F, 10.1F, 1.8F);
        this.hoofLL.addBox(-2.0F, 0.0F, -4.0F, 4, 2, 4, 0.0F);
        this.shoulderL = new ModelRenderer(this, 0, 0);
        this.shoulderL.setPos(3.5F, 0.0F, -4.0F);
        this.shoulderL.addBox(0.0F, 0.0F, -3.0F, 4, 8, 6, 0.0F);
        this.neckHigh = new ModelRenderer(this, 108, 0);
        this.neckHigh.setPos(0.0F, -9.5F, 0.0F);
        this.neckHigh.addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(neckHigh, 0.1832595714594046F, 0.0F, 0.0F);
        this.upperArmL = new ModelRenderer(this, 38, 32);
        this.upperArmL.setPos(1.8F, 7.0F, -2.7F);
        this.upperArmL.addBox(-2.0F, 0.0F, 0.0F, 4, 7, 5, 0.0F);
        this.shoulderR = new ModelRenderer(this, 40, 0);
        this.shoulderR.setPos(-3.5F, 0.0F, -4.0F);
        this.shoulderR.addBox(-4.0F, 0.0F, -3.0F, 4, 8, 6, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, -1.0F, 2.0F);
        this.body.addBox(-4.5F, 0.0F, -11.0F, 9, 10, 22, 0.0F);
        this.setRotateAngle(body, -0.08726646259971647F, 0.0F, 0.0F);
        this.tailpart3 = new ModelRenderer(this, 52, 50);
        this.tailpart3.setPos(0.0F, 0.2F, 7.0F);
        this.tailpart3.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 8, 0.0F);
        this.setRotateAngle(tailpart3, 0.136659280431156F, 0.0F, 0.0F);
        this.lowerLegL = new ModelRenderer(this, 16, 44);
        this.lowerLegL.setPos(-0.2F, 8.0F, 0.0F);
        this.lowerLegL.addBox(0.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F);
        this.lowerLegR = new ModelRenderer(this, 30, 44);
        this.lowerLegR.setPos(0.2F, 8.0F, 0.0F);
        this.lowerLegR.addBox(-3.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F);
        this.upperLegL = new ModelRenderer(this, 60, 0);
        this.upperLegL.setPos(4.0F, 4.0F, 9.0F);
        this.upperLegL.addBox(0.0F, 0.0F, -2.5F, 3, 9, 5, 0.0F);
        this.neckHigh.addChild(this.head);
        this.neckHigh.addChild(this.neckSailU);
        this.tailBase.addChild(this.tailpart1);
        this.head.addChild(this.noseSail);
        this.lowerLegR.addChild(this.hoofRR);
        this.shoulderR.addChild(this.upperArmR);
        this.upperArmR.addChild(this.lowerArmR);
        this.lowerArmR.addChild(this.hoofR);
        this.neckLow.addChild(this.neckMid);
        this.tailpart1.addChild(this.tailpart2);
        this.neckMid.addChild(this.neckSailL);
        this.upperArmL.addChild(this.lowerArmL);
        this.lowerArmL.addChild(this.hoofL);
        this.body.addChild(this.neckLow);
        this.lowerLegL.addChild(this.hoofLL);
        this.neckMid.addChild(this.neckHigh);
        this.shoulderL.addChild(this.upperArmL);
        this.tailpart2.addChild(this.tailpart3);
        this.upperLegL.addChild(this.lowerLegL);
        this.upperLegR.addChild(this.lowerLegR);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                this.tailBase,
                this.upperLegR,
                this.shoulderL,
                this.shoulderR,
                this.body,
                this.upperLegL
        );
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.upperArmL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.upperArmR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.upperLegL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.upperLegR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;

        //hehe, wag that tail
        this.tailBase.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + -0.2617993877991494F;
        this.tailBase.yRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.2617993877991494F;
        this.tailpart1.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.12217304763960307F;
        this.tailpart1.yRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 0.12217304763960307F;
        this.tailpart2.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.18203784098300857F;
        this.tailpart2.yRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 0.18203784098300857F;
        this.tailpart3.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.136659280431156F;
        this.tailpart3.yRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 0.136659280431156F;
    }
}
