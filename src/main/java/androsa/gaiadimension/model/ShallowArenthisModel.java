package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ShallowArenthisEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

/**
 * ModelShallowArenthis - Undefined
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ShallowArenthisModel<T extends ShallowArenthisEntity> extends SegmentedModel<T> {
    public ModelRenderer cap;
    public ModelRenderer head;
    public ModelRenderer[] tentacles = new ModelRenderer[7];
    public ModelRenderer body;
    public ModelRenderer tail;

    private final ImmutableList<ModelRenderer> parts;

    public ShallowArenthisModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.cap = new ModelRenderer(this, 0, 0);
        this.cap.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.cap.addCuboid(-5.0F, 0.0F, -5.0F, 10, 5, 10, 0.0F);
        this.body = new ModelRenderer(this, 48, 9);
        this.body.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.body.addCuboid(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.head = new ModelRenderer(this, 30, 0);
        this.head.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.head.addCuboid(-2.5F, 0.0F, -2.5F, 5, 3, 5, 0.0F);
        this.tail = new ModelRenderer(this, 0, 15);
        this.tail.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.tail.addCuboid(-3.5F, 0.0F, 0.0F, 7, 4, 0, 0.0F);
        for (int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new ModelRenderer(this, 0, 0);
            double d0 = (double)j * Math.PI * 2.0D / (double)this.tentacles.length;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            this.tentacles[j].addCuboid(-1.0F, 0.0F, -1.0F, 1, 8, 1);
            this.tentacles[j].rotationPointX = f;
            this.tentacles[j].rotationPointZ = f1;
            this.tentacles[j].rotationPointY = 5.0F;
            d0 = (double)j * Math.PI * -2.0D / (double)this.tentacles.length + (Math.PI / 2D);
            this.tentacles[j].rotateAngleY = (float)d0;
        }

        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.add(this.cap);
        builder.addAll(Arrays.asList(this.tentacles));
        this.parts = builder.build();

        this.head.addChild(this.body);
        this.cap.addChild(this.head);
        this.body.addChild(this.tail);
    }

    @Override
    public ImmutableList<ModelRenderer> getParts() {
        return parts;
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
    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.rotateAngleX = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;
        this.tail.rotateAngleX = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;

        for (ModelRenderer modelrenderer : this.tentacles) {
            modelrenderer.rotateAngleX = ageInTicks;
        }
    }
}
