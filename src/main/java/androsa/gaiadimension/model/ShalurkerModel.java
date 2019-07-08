package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ShalurkerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelShalurker - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ShalurkerModel extends EntityModel<ShalurkerEntity> {
    public RendererModel body;
    public RendererModel legR;
    public RendererModel legL;
    public RendererModel armR;
    public RendererModel armL;
    public RendererModel head;
    public RendererModel legRL;
    public RendererModel legLL;
    public RendererModel spike1;
    public RendererModel spike2;

    public ShalurkerModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.legLL = new RendererModel(this, 44, 12);
        this.legLL.setRotationPoint(-0.1F, 5.0F, 0.1F);
        this.legLL.addBox(-1.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotation(legLL, 0.6108652381980153F, 0.0F, 0.0F);
        this.armR = new RendererModel(this, 40, 0);
        this.armR.setRotationPoint(-2.0F, 1.0F, -2.0F);
        this.armR.addBox(-2.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotation(armR, -0.2617993877991494F, 0.0F, 0.0F);
        this.spike1 = new RendererModel(this, 0, 0);
        this.spike1.setRotationPoint(-1.5F, -6.0F, -4.5F);
        this.spike1.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotation(spike1, -0.3490658503988659F, 0.0F, 0.0F);
        this.legL = new RendererModel(this, 30, 0);
        this.legL.setRotationPoint(2.5F, 8.5F, 3.0F);
        this.legL.addBox(-1.0F, 0.0F, 0.0F, 2, 6, 3, 0.0F);
        this.setRotation(legL, -0.6108652381980153F, 0.0F, 0.0F);
        this.legR = new RendererModel(this, 20, 0);
        this.legR.setRotationPoint(-2.5F, 8.5F, 3.0F);
        this.legR.addBox(-1.0F, 0.0F, 0.0F, 2, 6, 3, 0.0F);
        this.setRotation(legR, -0.6108652381980153F, 0.0F, 0.0F);
        this.spike2 = new RendererModel(this, 15, 0);
        this.spike2.setRotationPoint(1.5F, -6.0F, -4.5F);
        this.spike2.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotation(spike2, -0.3490658503988659F, 0.0F, 0.0F);
        this.body = new RendererModel(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.body.addBox(-2.5F, 0.0F, 0.0F, 5, 15, 5, 0.0F);
        this.setRotation(body, 0.6108652381980153F, 0.0F, 0.0F);
        this.armL = new RendererModel(this, 48, 0);
        this.armL.setRotationPoint(2.0F, 1.0F, -2.0F);
        this.armL.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotation(armL, -0.2617993877991494F, 0.0F, 0.0F);
        this.head = new RendererModel(this, 20, 9);
        this.head.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.head.addBox(-3.0F, -6.0F, -6.5F, 6, 6, 6, 0.0F);
        this.legRL = new RendererModel(this, 54, 10);
        this.legRL.setRotationPoint(0.1F, 5.0F, 0.1F);
        this.legRL.addBox(-1.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotation(legRL, 0.6108652381980153F, 0.0F, 0.0F);
        this.legL.addChild(this.legLL);
        this.head.addChild(this.spike1);
        this.head.addChild(this.spike2);
        this.legR.addChild(this.legRL);
    }

    @Override
    public void render(ShalurkerEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.armR.render(f5);
        this.legL.render(f5);
        this.legR.render(f5);
        this.body.render(f5);
        this.armL.render(f5);
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
    public void setRotationAngles(ShalurkerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.armR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + -0.2617993877991494F;
        this.armL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + -0.2617993877991494F;

        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + -0.6108652381980153F;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + -0.6108652381980153F;
    }
}
