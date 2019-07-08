package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.RuggedLurmorusEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelRuggedLurmorus - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class RuggedLurmorusModel extends EntityModel<RuggedLurmorusEntity> {
    public RendererModel body;
    public RendererModel shoulderL;
    public RendererModel shoulderR;
    public RendererModel upperLegL;
    public RendererModel upperLegR;
    public RendererModel tailBase;
    public RendererModel neckLow;
    public RendererModel neckMid;
    public RendererModel neckHigh;
    public RendererModel neckSailL;
    public RendererModel head;
    public RendererModel neckSailU;
    public RendererModel noseSail;
    public RendererModel upperArmL;
    public RendererModel lowerArmL;
    public RendererModel hoofL;
    public RendererModel upperArmR;
    public RendererModel lowerArmR;
    public RendererModel hoofR;
    public RendererModel lowerLegL;
    public RendererModel hoofLL;
    public RendererModel lowerLegR;
    public RendererModel hoofRR;
    public RendererModel tailpart1;
    public RendererModel tailpart2;
    public RendererModel tailpart3;

    public RuggedLurmorusModel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.head = new RendererModel(this, 0, 32);
        this.head.setRotationPoint(0.0F, -8.6F, 0.7F);
        this.head.addBox(-2.5F, -4.0F, -6.0F, 5, 5, 6, 0.0F);
        this.setRotateAngle(head, -0.1832595714594046F, 0.0F, 0.0F);
        this.neckSailU = new RendererModel(this, 104, 30);
        this.neckSailU.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.neckSailU.addBox(-0.5F, 0.0F, 0.0F, 1, 15, 6, 0.0F);
        this.tailBase = new RendererModel(this, 82, 4);
        this.tailBase.setRotationPoint(0.0F, 0.9F, 12.0F);
        this.tailBase.addBox(-4.0F, 0.0F, 0.0F, 8, 7, 10, 0.0F);
        this.setRotateAngle(tailBase, -0.2617993877991494F, 0.0F, 0.0F);
        this.tailpart1 = new RendererModel(this, 65, 41);
        this.tailpart1.setRotationPoint(0.0F, 0.2F, 9.0F);
        this.tailpart1.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 9, 0.0F);
        this.setRotateAngle(tailpart1, 0.12217304763960307F, 0.0F, 0.0F);
        this.noseSail = new RendererModel(this, 22, 32);
        this.noseSail.setRotationPoint(0.0F, -5.4F, -8.0F);
        this.noseSail.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 7, 0.0F);
        this.hoofRR = new RendererModel(this, 44, 46);
        this.hoofRR.setRotationPoint(-1.5F, 10.1F, 1.8F);
        this.hoofRR.addBox(-2.0F, 0.0F, -4.0F, 4, 2, 4, 0.0F);
        this.upperArmR = new RendererModel(this, 56, 38);
        this.upperArmR.setRotationPoint(-1.8F, 7.0F, -2.7F);
        this.upperArmR.addBox(-2.0F, 0.0F, 0.0F, 4, 7, 5, 0.0F);
        this.lowerArmR = new RendererModel(this, 0, 43);
        this.lowerArmR.setRotationPoint(0.0F, 6.5F, 0.1F);
        this.lowerArmR.addBox(-2.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.hoofR = new RendererModel(this, 40, 14);
        this.hoofR.setRotationPoint(0.0F, 7.5F, 3.4F);
        this.hoofR.addBox(-2.5F, 0.0F, -5.0F, 5, 3, 5, 0.0F);
        this.neckMid = new RendererModel(this, 90, 21);
        this.neckMid.setRotationPoint(0.0F, -7.7F, -0.8F);
        this.neckMid.addBox(-2.5F, -10.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(neckMid, 0.091106186954104F, 0.0F, 0.0F);
        this.tailpart2 = new RendererModel(this, 87, 50);
        this.tailpart2.setRotationPoint(0.0F, 0.2F, 8.0F);
        this.tailpart2.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8, 0.0F);
        this.setRotateAngle(tailpart2, 0.18203784098300857F, 0.0F, 0.0F);
        this.upperLegR = new RendererModel(this, 76, 0);
        this.upperLegR.setRotationPoint(-4.0F, 4.0F, 9.0F);
        this.upperLegR.addBox(-3.0F, 0.0F, -2.5F, 3, 9, 5, 0.0F);
        this.neckSailL = new RendererModel(this, 113, 16);
        this.neckSailL.setRotationPoint(0.0F, -10.0F, 2.0F);
        this.neckSailL.addBox(-0.5F, 0.0F, 0.0F, 1, 14, 5, 0.0F);
        this.lowerArmL = new RendererModel(this, 86, 36);
        this.lowerArmL.setRotationPoint(0.0F, 6.5F, 0.1F);
        this.lowerArmL.addBox(-2.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.hoofL = new RendererModel(this, 0, 14);
        this.hoofL.setRotationPoint(0.0F, 7.5F, 3.4F);
        this.hoofL.addBox(-2.5F, 0.0F, -5.0F, 5, 3, 5, 0.0F);
        this.neckLow = new RendererModel(this, 62, 21);
        this.neckLow.setRotationPoint(0.0F, 5.0F, -9.0F);
        this.neckLow.addBox(-3.5F, -8.0F, -3.5F, 7, 10, 7, 0.0F);
        this.hoofLL = new RendererModel(this, 60, 14);
        this.hoofLL.setRotationPoint(1.5F, 10.1F, 1.8F);
        this.hoofLL.addBox(-2.0F, 0.0F, -4.0F, 4, 2, 4, 0.0F);
        this.shoulderL = new RendererModel(this, 0, 0);
        this.shoulderL.setRotationPoint(3.5F, 0.0F, -4.0F);
        this.shoulderL.addBox(0.0F, 0.0F, -3.0F, 4, 8, 6, 0.0F);
        this.neckHigh = new RendererModel(this, 108, 0);
        this.neckHigh.setRotationPoint(0.0F, -9.5F, 0.0F);
        this.neckHigh.addBox(-2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(neckHigh, 0.1832595714594046F, 0.0F, 0.0F);
        this.upperArmL = new RendererModel(this, 38, 32);
        this.upperArmL.setRotationPoint(1.8F, 7.0F, -2.7F);
        this.upperArmL.addBox(-2.0F, 0.0F, 0.0F, 4, 7, 5, 0.0F);
        this.shoulderR = new RendererModel(this, 40, 0);
        this.shoulderR.setRotationPoint(-3.5F, 0.0F, -4.0F);
        this.shoulderR.addBox(-4.0F, 0.0F, -3.0F, 4, 8, 6, 0.0F);
        this.body = new RendererModel(this, 0, 0);
        this.body.setRotationPoint(0.0F, -1.0F, 2.0F);
        this.body.addBox(-4.5F, 0.0F, -11.0F, 9, 10, 22, 0.0F);
        this.setRotateAngle(body, -0.08726646259971647F, 0.0F, 0.0F);
        this.tailpart3 = new RendererModel(this, 52, 50);
        this.tailpart3.setRotationPoint(0.0F, 0.2F, 7.0F);
        this.tailpart3.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 8, 0.0F);
        this.setRotateAngle(tailpart3, 0.136659280431156F, 0.0F, 0.0F);
        this.lowerLegL = new RendererModel(this, 16, 44);
        this.lowerLegL.setRotationPoint(-0.2F, 8.0F, 0.0F);
        this.lowerLegL.addBox(0.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F);
        this.lowerLegR = new RendererModel(this, 30, 44);
        this.lowerLegR.setRotationPoint(0.2F, 8.0F, 0.0F);
        this.lowerLegR.addBox(-3.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F);
        this.upperLegL = new RendererModel(this, 60, 0);
        this.upperLegL.setRotationPoint(4.0F, 4.0F, 9.0F);
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
    public void render(RuggedLurmorusEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tailBase.render(f5);
        this.upperLegR.render(f5);
        this.shoulderL.render(f5);
        this.shoulderR.render(f5);
        this.body.render(f5);
        this.upperLegL.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(RuggedLurmorusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.upperArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.upperArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.upperLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.upperLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;

        //hehe, wag that tail
        this.tailBase.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + -0.2617993877991494F;
        this.tailBase.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.2617993877991494F;
        this.tailpart1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.12217304763960307F;
        this.tailpart1.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 0.12217304763960307F;
        this.tailpart2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.18203784098300857F;
        this.tailpart2.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 0.18203784098300857F;
        this.tailpart3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F + 0.136659280431156F;
        this.tailpart3.rotateAngleY -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + 0.136659280431156F;
    }
}
