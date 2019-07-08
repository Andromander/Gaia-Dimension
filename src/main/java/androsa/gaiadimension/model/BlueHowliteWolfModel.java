package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelBlueHowliteWolf - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class BlueHowliteWolfModel extends EntityModel<BlueHowliteWolfEntity> {
    public RendererModel frontPaw1;
    public RendererModel frontPaw2;
    public RendererModel frontLowerLeg1;
    public RendererModel frontLowerLeg2;
    public RendererModel frontUpperLeg1;
    public RendererModel frontUpperLeg2;
    public RendererModel chestMane;
    public RendererModel body;
    public RendererModel neck;
    public RendererModel head;
    public RendererModel nose;
    public RendererModel ear1;
    public RendererModel ear2;
    public RendererModel backPaw1;
    public RendererModel backPaw2;
    public RendererModel backLowerLeg1;
    public RendererModel backLowerLeg2;
    public RendererModel backUpperLeg1;
    public RendererModel backUpperLeg2;
    public RendererModel tail1;
    public RendererModel tail2;

    public BlueHowliteWolfModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        //head
        this.head = new RendererModel(this, 14, 9);
        this.head.setRotationPoint(0F, -6.0F, -9.0F);
        this.head.addBox(-2.5F, -6.0F, -3.0F, 5, 6, 6, 0.0F);
        //right ear
        this.ear1 = new RendererModel(this, 0, 56);
        this.ear1.setRotationPoint(0F, 0F, 0F);
        this.ear1.addBox(-3.5F, -10.0F, 0.0F, 2, 7, 1, 0.0F);
        //left ear
        this.ear2 = new RendererModel(this, 122, 56);
        this.ear2.setRotationPoint(0F, -0F, 0F);
        this.ear2.addBox(1.5F, -10.0F, 0.0F, 2, 7, 1, 0.0F);
        //nose
        this.nose = new RendererModel(this, 0, 9);
        this.nose.setRotationPoint(0F, 0F, 0F);
        this.nose.addBox(-1.5F, -3.5F, -6.0F, 3, 3, 4, 0.0F);
        this.setRotation(nose, 0.17627825445142728F, 0.0F, 0.0F);
        //neck
        this.neck = new RendererModel(this, 74, 33);
        this.neck.setRotationPoint(-3.5F, -7.8F, -11.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 7, 10, 6, 0.0F);
        //chest mane
        this.chestMane = new RendererModel(this, 6, 46);
        this.chestMane.setRotationPoint(-6.0F, 0.0F, -13.0F);
        this.chestMane.addBox(0.0F, 0.0F, 0.0F, 12, 8, 10, 0.0F);
        //body
        this.body = new RendererModel(this, 0, 25);
        this.body.setRotationPoint(-4.0F, 0.7F, -3.1F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 6, 15, 0.0F);
        this.setRotation(body, -0.27314402793711257F, 0.0F, 0.0F);
        //front upper right leg
        this.frontUpperLeg1 = new RendererModel(this, 66, 49);
        this.frontUpperLeg1.setRotationPoint(-8.0F, 2.0F, -11.0F);
        this.frontUpperLeg1.addBox(0.0F, 0.0F, 0.0F, 3, 10, 5, 0.0F);
        //front lower right leg
        this.frontLowerLeg1 = new RendererModel(this, 98, 51);
        this.frontLowerLeg1.setRotationPoint(0.1F, 9.3F, 1.8F);
        this.frontLowerLeg1.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        //right front paw
        this.frontPaw1 = new RendererModel(this, 0, 0);
        this.frontPaw1.setRotationPoint(-1.5F, 9.7F, -2.8F);
        this.frontPaw1.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
        //front upper left leg
        this.frontUpperLeg2 = new RendererModel(this, 82, 49);
        this.frontUpperLeg2.setRotationPoint(5.0F, 2.0F, -11.0F);
        this.frontUpperLeg2.addBox(0.0F, 0.0F, 0.0F, 3, 10, 5, 0.0F);
        //front lower left leg
        this.frontLowerLeg2 = new RendererModel(this, 110, 51);
        this.frontLowerLeg2.setRotationPoint(-0.1F, 9.3F, 1.8F);
        this.frontLowerLeg2.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        //front left paw
        this.frontPaw2 = new RendererModel(this, 24, 0);
        this.frontPaw2.setRotationPoint(-1.5F, 9.7F, -2.8F);
        this.frontPaw2.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
        //upper back right leg
        this.backUpperLeg1 = new RendererModel(this, 116, 27);
        this.backUpperLeg1.setRotationPoint(-5.2F, 6.3F, 7.5F);
        this.backUpperLeg1.addBox(0.0F, 0.0F, 0.0F, 2, 7, 4, 0.0F);
        this.setRotation(backUpperLeg1, -0.7285004297824331F, 0.0F, 0.0F);
        //lower back right leg
        this.backLowerLeg1 = new RendererModel(this, 100, 36);
        this.backLowerLeg1.setRotationPoint(0.1F, 6.5F, 2.3F);
        this.backLowerLeg1.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotation(backLowerLeg1, 1.06011298766135573F, 0.0F, 0.0F);
        //back right paw
        this.backPaw1 = new RendererModel(this, 50, 52);
        this.backPaw1.setRotationPoint(-1.0F, 9.5F, -1.5F);
        this.backPaw1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F);
        this.setRotation(backPaw1, -0.3285004297824331F, 0.0F, 0.0F);
        //upper back left leg
        this.backUpperLeg2 = new RendererModel(this, 116, 38);
        this.backUpperLeg2.setRotationPoint(3.0F, 6.3F, 7.5F);
        this.backUpperLeg2.addBox(0.0F, 0.0F, 0.0F, 2, 7, 4, 0.0F);
        this.setRotation(backUpperLeg2, -0.7285004297824331F, 0.0F, 0.0F);
        //lower back left leg
        this.backLowerLeg2 = new RendererModel(this, 108, 36);
        this.backLowerLeg2.setRotationPoint(-0.1F, 6.5F, 2.3F);
        this.backLowerLeg2.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotation(backLowerLeg2, 1.06011298766135573F, 0.0F, 0.0F);
        //back left paw
        this.backPaw2 = new RendererModel(this, 50, 58);
        this.backPaw2.setRotationPoint(-1.0F, 9.5F, -1.5F);
        this.backPaw2.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F);
        this.setRotation(backPaw2, -0.3285004297824331F, 0.0F, 0.0F);
        //tail 1
        this.tail1 = new RendererModel(this, 48, 0);
        this.tail1.setRotationPoint(-2.0F, 4.7F, 11.3F);
        this.tail1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 12, 0.0F);
        this.setRotation(tail1, -0.7234389749516497F, 0.0F, 0.0F);
        //tail 2
        this.tail2 = new RendererModel(this, 48, 15);
        this.tail2.setRotationPoint(-2.0F, 12.3F, 16.7F);
        this.tail2.addBox(0.0F, 0.0F, 0.0F, 4, 3, 12, 0.0F);
        this.setRotation(tail2, 0.6108652381980153F, 0.0F, 0.0F);

        head.addChild(nose);
        head.addChild(ear1);
        head.addChild(ear2);
        frontUpperLeg1.addChild(frontLowerLeg1);
        frontLowerLeg1.addChild(frontPaw1);
        frontUpperLeg2.addChild(frontLowerLeg2);
        frontLowerLeg2.addChild(frontPaw2);
        backUpperLeg1.addChild(backLowerLeg1);
        backLowerLeg1.addChild(backPaw1);
        backUpperLeg2.addChild(backLowerLeg2);
        backLowerLeg2.addChild(backPaw2);
    }

    @Override
    public void render(BlueHowliteWolfEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);

        this.head.render(f5);
        this.neck.render(f5);
        this.chestMane.render(f5);
        this.body.render(f5);
        this.frontUpperLeg1.render(f5);
        this.frontUpperLeg2.render(f5);
        this.backUpperLeg1.render(f5);
        this.backUpperLeg2.render(f5);
        this.tail1.render(f5);
        this.tail2.render(f5);
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
    public void setRotationAngles(BlueHowliteWolfEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.frontUpperLeg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.frontUpperLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.backUpperLeg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + -0.7285004297824331F;
        this.backUpperLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + -0.7285004297824331F;
    }
}
