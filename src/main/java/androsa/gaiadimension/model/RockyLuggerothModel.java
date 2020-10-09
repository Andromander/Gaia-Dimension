package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.RockyLuggerothEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelRockyLuggeroth - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class RockyLuggerothModel<T extends RockyLuggerothEntity> extends SegmentedModel<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer foot3;
    public ModelRenderer foot4;
    public ModelRenderer shell;
    public ModelRenderer ridge;

    public RockyLuggerothModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 32, 0);
        this.head.setRotationPoint(0.0F, 18.8F, -8.0F);
        this.head.addBox(-2.5F, -2.0F, -5.0F, 5, 4, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 16.0F, -3.0F);
        this.body.addBox(-4.0F, 0.0F, -5.0F, 8, 3, 16, 0.0F);
        this.foot3 = new ModelRenderer(this, 64, 0);
        this.foot3.setRotationPoint(-2.5F, 18.0F, 6.0F);
        this.foot3.addBox(-3.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        this.foot4 = new ModelRenderer(this, 76, 0);
        this.foot4.setRotationPoint(2.5F, 18.0F, 6.0F);
        this.foot4.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        this.shell = new ModelRenderer(this, 46, 9);
        this.shell.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.shell.addBox(-5.0F, -18.0F, -10.0F, 10, 18, 20, 0.0F);
        this.ridge = new ModelRenderer(this, 0, 41);
        this.ridge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ridge.addBox(-6.0F, 0.0F, -11.0F, 12, 1, 22, 0.0F);
        this.foot1 = new ModelRenderer(this, 0, 0);
        this.foot1.setRotationPoint(-2.5F, 18.0F, -6.0F);
        this.foot1.addBox(-3.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        this.foot2 = new ModelRenderer(this, 52, 0);
        this.foot2.setRotationPoint(2.5F, 18.0F, -6.0F);
        this.foot2.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        this.body.addChild(this.shell);
        this.shell.addChild(this.ridge);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.head,
                this.body,
                this.foot3,
                this.foot4,
                this.foot1,
                this.foot2
        );
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
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.foot1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.foot2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;

        this.foot3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.foot4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }
}
