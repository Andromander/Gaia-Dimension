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
        this.texWidth = 64;
        this.texHeight = 32;

        this.cap = new ModelRenderer(this, 0, 0);
        this.cap.setPos(0.0F, 0.0F, 0.0F);
        this.cap.addBox(-5.0F, 0.0F, -5.0F, 10, 5, 10, 0.0F);
        this.body = new ModelRenderer(this, 48, 9);
        this.body.setPos(0.0F, 3.0F, 0.0F);
        this.body.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.head = new ModelRenderer(this, 30, 0);
        this.head.setPos(0.0F, 5.0F, 0.0F);
        this.head.addBox(-2.5F, 0.0F, -2.5F, 5, 3, 5, 0.0F);
        this.tail = new ModelRenderer(this, 0, 15);
        this.tail.setPos(0.0F, 6.0F, 0.0F);
        this.tail.addBox(-3.5F, 0.0F, 0.0F, 7, 4, 0, 0.0F);
        for (int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new ModelRenderer(this, 0, 0);
            double d0 = (double)j * Math.PI * 2.0D / (double)this.tentacles.length;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 1, 8, 1);
            this.tentacles[j].x = f;
            this.tentacles[j].z = f1;
            this.tentacles[j].y = 5.0F;
            d0 = (double)j * Math.PI * -2.0D / (double)this.tentacles.length + (Math.PI / 2D);
            this.tentacles[j].yRot = (float)d0;
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
    public Iterable<ModelRenderer> parts() {
        return parts;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.xRot = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;
        this.tail.xRot = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;

        for (ModelRenderer modelrenderer : this.tentacles) {
            modelrenderer.xRot = ageInTicks;
        }
    }
}
