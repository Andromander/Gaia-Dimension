package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelSaltion - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelSaltion extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer legL1;
    public ModelRenderer legL2;
    public ModelRenderer legL3;
    public ModelRenderer legR1;
    public ModelRenderer legR2;
    public ModelRenderer legR1_1;
    public ModelRenderer tail1;
    public ModelRenderer head;
    public ModelRenderer armR;
    public ModelRenderer armL;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer bulb;
    public ModelRenderer stinger;
    public ModelRenderer pincerR;
    public ModelRenderer clawR;
    public ModelRenderer pincerL;
    public ModelRenderer clawL;

    public ModelSaltion() {
        this.textureWidth = 64;
        this.textureHeight = 40;
        this.legR1_1 = new ModelRenderer(this, 46, 8);
        this.legR1_1.setRotationPoint(2.0F, 0.6F, 8.8F);
        this.legR1_1.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(legR1_1, 0.0F, -0.20943951023931953F, 0.3490658503988659F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.0F, -4.0F);
        this.body.addBox(-2.5F, -1.5F, 0.0F, 5, 3, 10, 0.0F);
        this.tail2 = new ModelRenderer(this, 36, 18);
        this.tail2.setRotationPoint(0.0F, 0.0F, 3.9F);
        this.tail2.addBox(-1.5F, 0.0F, -0.8F, 3, 2, 5, 0.0F);
        this.setRotateAngle(tail2, 0.9560913642424937F, 0.0F, 0.0F);
        this.stinger = new ModelRenderer(this, 0, 0);
        this.stinger.setRotationPoint(0.0F, -0.8F, 2.0F);
        this.stinger.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(stinger, 1.3089969389957472F, 0.0F, 0.0F);
        this.legR2 = new ModelRenderer(this, 30, 8);
        this.legR2.setRotationPoint(2.0F, 0.6F, 5.8F);
        this.legR2.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(legR2, 0.0F, -0.17453292519943295F, 0.3490658503988659F);
        this.tail1 = new ModelRenderer(this, 25, 12);
        this.tail1.setRotationPoint(0.0F, -1.5F, 8.6F);
        this.tail1.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 5, 0.0F);
        this.setRotateAngle(tail1, 0.7853981633974483F, 0.0F, 0.0F);
        this.tail3 = new ModelRenderer(this, 0, 20);
        this.tail3.setRotationPoint(0.0F, 0.0F, 3.1F);
        this.tail3.addBox(-1.5F, 0.0F, -0.6F, 3, 2, 5, 0.0F);
        this.setRotateAngle(tail3, 0.8651597102135892F, 0.0F, 0.0F);
        this.clawR = new ModelRenderer(this, 50, 2);
        this.clawR.setRotationPoint(2.5F, 0.0F, 0.0F);
        this.clawR.addBox(0.0F, -0.5F, -1.0F, 4, 1, 2, 0.0F);
        this.setRotateAngle(clawR, 0.0F, -0.2617993877991494F, 0.0F);
        this.pincerL = new ModelRenderer(this, 0, 27);
        this.pincerL.setRotationPoint(1.0F, -0.1F, -5.2F);
        this.pincerL.addBox(-8.0F, -1.0F, -2.0F, 8, 2, 3, 0.0F);
        this.setRotateAngle(pincerL, 0.0F, -0.3490658503988659F, 0.0F);
        this.legL2 = new ModelRenderer(this, 36, 0);
        this.legL2.setRotationPoint(-2.0F, 0.6F, 5.8F);
        this.legL2.addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(legL2, 0.0F, 0.17453292519943295F, -0.3490658503988659F);
        this.legR1 = new ModelRenderer(this, 36, 4);
        this.legR1.setRotationPoint(2.0F, 0.6F, 2.8F);
        this.legR1.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(legR1, 0.0F, -0.13962634015954636F, 0.3490658503988659F);
        this.legL3 = new ModelRenderer(this, 20, 4);
        this.legL3.setRotationPoint(-2.0F, 0.6F, 8.8F);
        this.legL3.addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(legL3, 0.0F, 0.20943951023931953F, -0.3490658503988659F);
        this.bulb = new ModelRenderer(this, 16, 21);
        this.bulb.setRotationPoint(0.0F, 1.1F, 4.0F);
        this.bulb.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.armL = new ModelRenderer(this, 14, 14);
        this.armL.setRotationPoint(2.0F, 1.0F, 0.8F);
        this.armL.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
        this.setRotateAngle(armL, 0.0F, -0.8377580409572781F, 0.0F);
        this.legL1 = new ModelRenderer(this, 20, 0);
        this.legL1.setRotationPoint(-2.0F, 0.6F, 2.8F);
        this.legL1.addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(legL1, 0.0F, 0.13962634015954636F, -0.3490658503988659F);
        this.head = new ModelRenderer(this, 41, 12);
        this.head.setRotationPoint(0.0F, 0.3F, 1.1F);
        this.head.addBox(-2.0F, -1.5F, -4.0F, 4, 3, 3, 0.0F);
        this.pincerR = new ModelRenderer(this, 25, 25);
        this.pincerR.setRotationPoint(-1.0F, -0.1F, -5.2F);
        this.pincerR.addBox(0.0F, -1.0F, -2.0F, 8, 2, 3, 0.0F);
        this.setRotateAngle(pincerR, 0.0F, 0.3490658503988659F, 0.0F);
        this.clawL = new ModelRenderer(this, 47, 18);
        this.clawL.setRotationPoint(-2.5F, 0.0F, 0.0F);
        this.clawL.addBox(-4.0F, -0.5F, -1.0F, 4, 1, 2, 0.0F);
        this.setRotateAngle(clawL, 0.0F, 0.2617993877991494F, 0.0F);
        this.armR = new ModelRenderer(this, 0, 13);
        this.armR.setRotationPoint(-2.0F, 1.0F, 0.8F);
        this.armR.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
        this.setRotateAngle(armR, 0.0F, 0.8377580409572781F, 0.0F);
        this.body.addChild(this.legR1_1);
        this.tail1.addChild(this.tail2);
        this.bulb.addChild(this.stinger);
        this.body.addChild(this.legR2);
        this.body.addChild(this.tail1);
        this.tail2.addChild(this.tail3);
        this.pincerR.addChild(this.clawR);
        this.armL.addChild(this.pincerL);
        this.body.addChild(this.legL2);
        this.body.addChild(this.legR1);
        this.body.addChild(this.legL3);
        this.tail3.addChild(this.bulb);
        this.body.addChild(this.armL);
        this.body.addChild(this.legL1);
        this.body.addChild(this.head);
        this.armR.addChild(this.pincerR);
        this.pincerL.addChild(this.clawL);
        this.body.addChild(this.armR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
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
