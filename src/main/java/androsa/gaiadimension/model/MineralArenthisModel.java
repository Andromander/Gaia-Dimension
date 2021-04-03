package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.MineralArenthisEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

/**
 * ModelMineralArenthis - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class MineralArenthisModel<T extends MineralArenthisEntity> extends SegmentedModel<T> {
    public ModelRenderer capTop;
    public ModelRenderer capBottom;
    public ModelRenderer head;
    public ModelRenderer[] tentacles = new ModelRenderer[10];
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer finL;
    public ModelRenderer finR;
    public ModelRenderer tailfin;

    private final ImmutableList<ModelRenderer> parts;

    public MineralArenthisModel() {
        this.texWidth = 128;
        this.texHeight = 80;

        this.capTop = new ModelRenderer(this, 0, 0);
        this.capTop.setPos(0.0F, -5.0F, 0.0F);
        this.capTop.addBox(-8.0F, 0.0F, -8.0F, 16, 7, 16, 0.0F);
        this.head = new ModelRenderer(this, 0, 23);
        this.head.setPos(0.0F, 6.0F, 0.0F);
        this.head.addBox(-3.5F, 0.0F, -3.5F, 7, 4, 7, 0.0F);
        this.body = new ModelRenderer(this, 104, 0);
        this.body.setPos(0.0F, 4.0F, 0.0F);
        this.body.addBox(-2.5F, 0.0F, -2.0F, 5, 5, 4, 0.0F);
        this.capBottom = new ModelRenderer(this, 44, 3);
        this.capBottom.setPos(0.0F, 6.0F, 0.0F);
        this.capBottom.addBox(-10.0F, 0.0F, -10.0F, 20, 6, 20, 0.0F);
        this.finR = new ModelRenderer(this, -4, 36);
        this.finR.setPos(-2.2F, 0.5F, 0.1F);
        this.finR.addBox(-10.0F, 0.0F, -2.0F, 10, 0, 4, 0.0F);
        this.setRotateAngle(finR, 0.0F, 0.0F, -1.2217304763960306F);
        this.finL = new ModelRenderer(this, -4, 41);
        this.finL.setPos(2.2F, 0.5F, 0.1F);
        this.finL.addBox(0.0F, 0.0F, -2.0F, 10, 0, 4, 0.0F);
        this.setRotateAngle(finL, 0.0F, 0.0F, 1.2217304763960306F);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setPos(0.0F, 5.0F, 0.0F);
        this.tail.addBox(-2.0F, 0.0F, -1.0F, 4, 7, 2, 0.0F);
        this.tailfin = new ModelRenderer(this, 86, 30);
        this.tailfin.setPos(0.0F, 7.0F, 0.0F);
        this.tailfin.addBox(-5.5F, 0.0F, 0.0F, 11, 6, 0, 0.0F);
        for (int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new ModelRenderer(this, 28, 23);
            double d0 = (double)j * Math.PI * 2.0D / (double)this.tentacles.length;
            float f = (float)Math.cos(d0) * 8.0F;
            float f1 = (float)Math.sin(d0) * 8.0F;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
            this.tentacles[j].x = f;
            this.tentacles[j].z = f1;
            this.tentacles[j].y = 5.0F;
            d0 = (double)j * Math.PI * -2.0D / (double)this.tentacles.length + (Math.PI / 2D);
            this.tentacles[j].yRot = (float)d0;
        }

        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.add(this.capTop);
        builder.addAll(Arrays.asList(this.tentacles));
        parts = builder.build();

        this.capBottom.addChild(this.head);
        this.head.addChild(this.body);
        this.capTop.addChild(this.capBottom);
        this.body.addChild(this.finR);
        this.body.addChild(this.finL);
        this.body.addChild(this.tail);
        this.tail.addChild(this.tailfin);
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
        this.tailfin.xRot = MathHelper.sin(ageInTicks * (float)Math.PI * 0.025F) * 3.0F;

        for (ModelRenderer modelrenderer : this.tentacles) {
            modelrenderer.xRot = ageInTicks;
        }
    }
}
