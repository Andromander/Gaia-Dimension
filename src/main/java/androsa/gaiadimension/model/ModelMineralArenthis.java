package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelMineralArenthis - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelMineralArenthis extends ModelBase {
    public ModelRenderer capTop;
    public ModelRenderer capBottom;
    public ModelRenderer head;
    /*
    public ModelRenderer tentacle1;
    public ModelRenderer tentacle2;
    public ModelRenderer tentacle3;
    public ModelRenderer tentacle4;
    public ModelRenderer tentacle5;
    public ModelRenderer tentacle6;
    public ModelRenderer tentacle7;
    */
    public ModelRenderer[] tentacles = new ModelRenderer[10];
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer finL;
    public ModelRenderer finR;
    public ModelRenderer shape6;

    public ModelMineralArenthis() {
        this.textureWidth = 128;
        this.textureHeight = 80;

        this.capTop = new ModelRenderer(this, 0, 0);
        this.capTop.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.capTop.addBox(-8.0F, 0.0F, -8.0F, 16, 7, 16, 0.0F);
        this.head = new ModelRenderer(this, 0, 23);
        this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head.addBox(-3.5F, 0.0F, -3.5F, 7, 4, 7, 0.0F);
        this.body = new ModelRenderer(this, 104, 0);
        this.body.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.body.addBox(-2.5F, 0.0F, -2.0F, 5, 5, 4, 0.0F);
        this.capBottom = new ModelRenderer(this, 44, 3);
        this.capBottom.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.capBottom.addBox(-10.0F, 0.0F, -10.0F, 20, 6, 20, 0.0F);
        this.finR = new ModelRenderer(this, -4, 36);
        this.finR.setRotationPoint(-2.2F, 0.5F, 0.1F);
        this.finR.addBox(-10.0F, 0.0F, -2.0F, 10, 0, 4, 0.0F);
        this.setRotateAngle(finR, 0.0F, 0.0F, -1.2217304763960306F);
        this.finL = new ModelRenderer(this, -4, 41);
        this.finL.setRotationPoint(2.2F, 0.5F, 0.1F);
        this.finL.addBox(0.0F, 0.0F, -2.0F, 10, 0, 4, 0.0F);
        this.setRotateAngle(finL, 0.0F, 0.0F, 1.2217304763960306F);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.tail.addBox(-2.0F, 0.0F, -1.0F, 4, 7, 2, 0.0F);
        this.shape6 = new ModelRenderer(this, 86, 30);
        this.shape6.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.shape6.addBox(-5.5F, 0.0F, 0.0F, 11, 6, 0, 0.0F);
        for (int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new ModelRenderer(this, 28, 23);
            double d0 = (double)j * Math.PI * 2.0D / (double)this.tentacles.length;
            float f = (float)Math.cos(d0) * 8.0F;
            float f1 = (float)Math.sin(d0) * 8.0F;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
            this.tentacles[j].rotationPointX = f;
            this.tentacles[j].rotationPointZ = f1;
            this.tentacles[j].rotationPointY = 5.0F;
            d0 = (double)j * Math.PI * -2.0D / (double)this.tentacles.length + (Math.PI / 2D);
            this.tentacles[j].rotateAngleY = (float)d0;
        }
        /*
        this.tentacle1 = new ModelRenderer(this, 28, 23);
        this.tentacle1.setRotationPoint(-7.0F, 5.0F, -7.0F);
        this.tentacle1.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.setRotateAngle(tentacle1, 0.0F, -0.7853981633974483F, 0.0F);
        this.tentacle2 = new ModelRenderer(this, 36, 23);
        this.tentacle2.setRotationPoint(-8.0F, 5.0F, 0.0F);
        this.tentacle2.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.tentacle4 = new ModelRenderer(this, 52, 29);
        this.tentacle4.setRotationPoint(0.0F, 5.0F, 8.0F);
        this.tentacle4.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.tentacle3 = new ModelRenderer(this, 44, 29);
        this.tentacle3.setRotationPoint(-7.0F, 5.0F, 7.0F);
        this.tentacle3.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.setRotateAngle(tentacle3, 0.0F, 0.7853981633974483F, 0.0F);
        this.tentacle5 = new ModelRenderer(this, 60, 29);
        this.tentacle5.setRotationPoint(7.0F, 5.0F, 7.0F);
        this.tentacle5.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.setRotateAngle(tentacle5, 0.0F, 0.7853981633974483F, 0.0F);
        this.tentacle7 = new ModelRenderer(this, 76, 29);
        this.tentacle7.setRotationPoint(7.0F, 5.0F, -7.0F);
        this.tentacle7.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.setRotateAngle(tentacle7, 0.0F, 0.7853981633974483F, 0.0F);
        this.tentacle6 = new ModelRenderer(this, 68, 29);
        this.tentacle6.setRotationPoint(8.0F, 5.0F, 0.0F);
        this.tentacle6.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        */

        this.capBottom.addChild(this.head);
        this.head.addChild(this.body);
        this.capTop.addChild(this.capBottom);
        this.body.addChild(this.finR);
        this.body.addChild(this.finL);
        this.body.addChild(this.tail);
        this.tail.addChild(this.shape6);
        /*
        this.capBottom.addChild(this.tentacle1);
        this.capBottom.addChild(this.tentacle2);
        this.capBottom.addChild(this.tentacle4);
        this.capBottom.addChild(this.tentacle3);
        this.capBottom.addChild(this.tentacle5);
        this.capBottom.addChild(this.tentacle7);
        this.capBottom.addChild(this.tentacle6);
        */
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.capTop.render(scale);
        for (ModelRenderer modelrenderer : this.tentacles) {
            modelrenderer.render(scale);
        }
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
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        for (ModelRenderer modelrenderer : this.tentacles) {
            modelrenderer.rotateAngleX = ageInTicks;
        }
    }
}
