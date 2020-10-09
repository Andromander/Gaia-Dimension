package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.CavernTickEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelCavernTick - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CavernTickModel<T extends CavernTickEntity> extends SegmentedModel<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer spike1;
    public ModelRenderer spike2;
    public ModelRenderer spike3;
    public ModelRenderer horn;

    public CavernTickModel() {
        this.textureWidth = 32;
        this.textureHeight = 24;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 20.0F, -3.0F);
        this.body.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 7, 0.0F);
        this.spike1 = new ModelRenderer(this, 0, 11);
        this.spike1.setRotationPoint(0.0F, 0.0F, 1.5F);
        this.spike1.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(spike1, 0.7853981633974483F, 0.0F, 0.0F);
        this.horn = new ModelRenderer(this, 23, 8);
        this.horn.setRotationPoint(0.0F, -1.5F, -1.0F);
        this.horn.addBox(-0.5F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(horn, -0.7853981633974483F, 0.0F, 0.0F);
        this.spike3 = new ModelRenderer(this, 0, 17);
        this.spike3.setRotationPoint(1.5F, 0.5F, 2.0F);
        this.spike3.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(spike3, 0.8726646259971648F, 0.5235987755982988F, 0.6108652381980153F);
        this.spike2 = new ModelRenderer(this, 14, 11);
        this.spike2.setRotationPoint(-1.5F, 0.5F, 2.0F);
        this.spike2.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(spike2, 0.8726646259971648F, -0.5235987755982988F, -0.6108652381980153F);
        this.head = new ModelRenderer(this, 17, 0);
        this.head.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.head.addBox(-2.0F, -2.0F, -2.0F, 4, 3, 2, 0.0F);
        this.body.addChild(this.spike1);
        this.head.addChild(this.horn);
        this.body.addChild(this.spike3);
        this.body.addChild(this.spike2);
        this.body.addChild(this.head);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.body);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.spike1.rotateAngleX = MathHelper.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;
        this.spike2.rotateAngleX = MathHelper.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;
        this.spike3.rotateAngleX = MathHelper.sin(ageInTicks * (float)Math.PI * 0.05F) * 0.1F + 0.7853981633974483F;

        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
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
