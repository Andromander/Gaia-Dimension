package androsa.gaiadimension.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelGrowthSapper - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class GrowthSapperModel<T extends MobEntity> extends SegmentedModel<T> {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer nozzle;

    public GrowthSapperModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.leg2 = new ModelRenderer(this, 34, 0);
        this.leg2.setPos(-1.4F, 18.0F, 0.0F);
        this.leg2.addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.body = new ModelRenderer(this, 8, 0);
        this.body.setPos(0.0F, 18.0F, -6.0F);
        this.body.addBox(-3.5F, -9.0F, 0.0F, 7, 9, 12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, 15.0F, -6.0F);
        this.head.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 10);
        this.leg1.setPos(-1.4F, 18.0F, -5.5F);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.leg3 = new ModelRenderer(this, 42, 0);
        this.leg3.setPos(-1.4F, 18.0F, 3.5F);
        this.leg3.addBox(-2.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.nozzle = new ModelRenderer(this, 41, 16);
        this.nozzle.setPos(0.0F, 1.0F, -5.0F);
        this.nozzle.addBox(-0.5F, 0.0F, -5.0F, 1, 1, 5, 0.0F);
        this.setRotation(nozzle, 0.27314402793711257F, 0.0F, 0.0F);
        this.leg5 = new ModelRenderer(this, 46, 8);
        this.leg5.setPos(1.4F, 18.0F, 0.0F);
        this.leg5.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.leg4 = new ModelRenderer(this, 50, 0);
        this.leg4.setPos(1.4F, 18.0F, -5.5F);
        this.leg4.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.leg6 = new ModelRenderer(this, 54, 8);
        this.leg6.setPos(1.4F, 18.0F, 3.5F);
        this.leg6.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.head.addChild(this.nozzle);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                this.leg2,
                this.body,
                this.head,
                this.leg1,
                this.leg3,
                this.leg5,
                this.leg4,
                this.leg6
        );
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);

        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.leg4.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.leg5.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.leg6.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
