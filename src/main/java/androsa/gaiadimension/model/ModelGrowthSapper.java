package androsa.gaiadimension.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGrowthSapper - Androsa
 * Created using Tabula 7.0.0
 */
public class ModelGrowthSapper extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer nozzle;

    public ModelGrowthSapper() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leg2 = new ModelRenderer(this, 34, 0);
        this.leg2.setRotationPoint(-1.4F, 18.0F, 0.0F);
        this.leg2.addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.body = new ModelRenderer(this, 8, 0);
        this.body.setRotationPoint(0.0F, 18.0F, -6.0F);
        this.body.addBox(-3.5F, -9.0F, 0.0F, 7, 9, 12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 15.0F, -6.0F);
        this.head.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 10);
        this.leg1.setRotationPoint(-1.4F, 18.0F, -5.5F);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.leg3 = new ModelRenderer(this, 42, 0);
        this.leg3.setRotationPoint(-1.4F, 18.0F, 3.5F);
        this.leg3.addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.nozzle = new ModelRenderer(this, 41, 16);
        this.nozzle.setRotationPoint(0.0F, 1.0F, -5.0F);
        this.nozzle.addBox(-0.5F, 0.0F, -5.0F, 1, 1, 5, 0.0F);
        this.setRotateAngle(nozzle, 0.27314402793711257F, 0.0F, 0.0F);
        this.leg5 = new ModelRenderer(this, 46, 8);
        this.leg5.setRotationPoint(1.4F, 18.0F, 0.0F);
        this.leg5.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.leg4 = new ModelRenderer(this, 50, 0);
        this.leg4.setRotationPoint(1.4F, 18.0F, -5.5F);
        this.leg4.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.leg6 = new ModelRenderer(this, 54, 8);
        this.leg6.setRotationPoint(1.4F, 18.0F, 3.5F);
        this.leg6.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.head.addChild(this.nozzle);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leg2.render(f5);
        this.body.render(f5);
        this.head.render(f5);
        this.leg1.render(f5);
        this.leg3.render(f5);
        this.leg5.render(f5);
        this.leg4.render(f5);
        this.leg6.render(f5);
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
