package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.SpellElementEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelSpellElement - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class SpellElementModel extends EntityModel<SpellElementEntity> {
    public RendererModel head;
    public RendererModel horn1R;
    public RendererModel horn2R;
    public RendererModel horn1L;
    public RendererModel horn2L;
    public RendererModel chin;
    public RendererModel bodyA;
    public RendererModel bodyB;
    public RendererModel core;
    public RendererModel scytheR;
    public RendererModel elbowR;
    public RendererModel clawR;
    public RendererModel scytheL;
    public RendererModel elbowL;
    public RendererModel clawL;
    public RendererModel tip;
    public RendererModel tipR;
    public RendererModel tipL;

    public SpellElementModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        //head
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.5F, -5.0F, -1.5F, 5, 5, 3, 0.0F);
        //right horn 1
        this.horn1R = new RendererModel(this, 52, 3);
        this.horn1R.setRotationPoint(-0.5F, -4.6F, 0.0F);
        this.horn1R.addBox(-3.0F, 0.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(horn1R, 0.0F, 0.0F, 0.4553564018453205F);
        //right horn 2
        this.horn2R = new RendererModel(this, 46, 5);
        this.horn2R.setRotationPoint(-1.6F, 1.5F, 0.1F);
        this.horn2R.addBox(0.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(horn2R, 0.0F, 0.0F, 2.367539130330308F);
        //left horn 1
        this.horn1L = new RendererModel(this, 16, 5);
        this.horn1L.setRotationPoint(0.5F, -4.6F, 0.0F);
        this.horn1L.addBox(0.0F, 0.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(horn1L, 0.0F, 0.0F, -0.4553564018453205F);
        //left horn 2
        this.horn2L = new RendererModel(this, 0, 8);
        this.horn2L.setRotationPoint(1.6F, 1.5F, 0.1F);
        this.horn2L.addBox(-2.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(horn2L, 0.0F, 0.0F, -2.367539130330308F);
        //chin
        this.chin = new RendererModel(this, 52, 0);
        this.chin.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.chin.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        //top body
        this.bodyA = new RendererModel(this, 16, 0);
        this.bodyA.setRotationPoint(0.0F, 8.4F, 0.0F);
        this.bodyA.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
        //bottom body
        this.bodyB = new RendererModel(this, 6, 10);
        this.bodyB.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyB.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 1, 0.0F);
        //core
        this.core = new RendererModel(this, 50, 8);
        this.core.setRotationPoint(0.0F, -1.5F, -0.1F);
        this.core.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        //right scythe
        this.scytheR = new RendererModel(this, 26, 0);
        this.scytheR.setRotationPoint(-8.0F, 3.0F, 0.0F);
        this.scytheR.addBox(-3.0F, 0.0F, -1.0F, 3, 11, 2, 0.0F);
        this.setRotation(scytheR, 0.0F, 0.0F, -0.08726646259971647F);
        //right elbow
        this.elbowR = new RendererModel(this, 46, 10);
        this.elbowR.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.elbowR.addBox(0.0F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        //right claw
        this.clawR = new RendererModel(this, 57, 11);
        this.clawR.setRotationPoint(-0.7F, 10.3F, 0.0F);
        this.clawR.addBox(-2.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(clawR, 0.0F, 0.0F, -0.36425021489121656F);
        //left scythe
        this.scytheL = new RendererModel(this, 36, 0);
        this.scytheL.setRotationPoint(8.0F, 3.0F, 0.0F);
        this.scytheL.addBox(0.0F, 0.0F, -1.0F, 3, 11, 2, 0.0F);
        this.setRotation(scytheL, 0.0F, 0.0F, 0.08726646259971647F);
        //left elbow
        this.elbowL = new RendererModel(this, 50, 12);
        this.elbowL.setRotationPoint(2.0F, 0.0F, 0.0F);
        this.elbowL.addBox(0.0F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        //left claw
        this.clawL = new RendererModel(this, 0, 13);
        this.clawL.setRotationPoint(0.7F, 10.3F, 0.0F);
        this.clawL.addBox(0.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(clawL, 0.0F, 0.0F, 0.36425021489121656F);
        //center tip
        this.tip = new RendererModel(this, 46, 0);
        this.tip.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.tip.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
        //right tip part
        this.tipR = new RendererModel(this, 6, 8);
        this.tipR.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.tipR.addBox(-2.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotation(tipR, 0.0F, 0.0F, 0.4363323129985824F);
        //left tip part
        this.tipL = new RendererModel(this, 56, 8);
        this.tipL.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.tipL.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotation(tipL, 0.0F, 0.0F, -0.4363323129985824F);

        //give the parts their kiddies
        this.head.addChild(this.chin);
        this.head.addChild(this.horn1L);
        this.horn1L.addChild(this.horn2L);
        this.bodyA.addChild(this.bodyB);
        this.scytheR.addChild(this.clawR);
        this.bodyA.addChild(this.core);
        this.tip.addChild(this.tipR);
        this.head.addChild(this.horn1R);
        this.horn1R.addChild(this.horn2R);
        this.scytheL.addChild(this.elbowL);
        this.tip.addChild(this.tipL);
        this.scytheL.addChild(this.clawL);
        this.scytheR.addChild(this.elbowR);
    }

    @Override
    public void render(SpellElementEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.scytheR.render(f5);
        this.scytheL.render(f5);
        this.bodyA.render(f5);
        this.tip.render(f5);
        this.head.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotation(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(SpellElementEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.scytheR.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F;
        this.scytheL.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.scytheR.rotateAngleZ += MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.08726646259971647F;
        this.scytheL.rotateAngleZ -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.08726646259971647F;

    }
}
