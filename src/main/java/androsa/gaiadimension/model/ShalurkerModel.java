package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.ShalurkerEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelShalurker - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ShalurkerModel<T extends ShalurkerEntity> extends SegmentedModel<T> {
    public ModelRenderer body;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer armR;
    public ModelRenderer armL;
    public ModelRenderer head;
    public ModelRenderer legRL;
    public ModelRenderer legLL;
    public ModelRenderer spike1;
    public ModelRenderer spike2;

    public ShalurkerModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.legLL = new ModelRenderer(this, 44, 12);
        this.legLL.setPos(-0.1F, 5.0F, 0.1F);
        this.legLL.addBox(-1.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotation(legLL, 0.6108652381980153F, 0.0F, 0.0F);
        this.armR = new ModelRenderer(this, 40, 0);
        this.armR.setPos(-2.0F, 1.0F, -2.0F);
        this.armR.addBox(-2.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotation(armR, -0.2617993877991494F, 0.0F, 0.0F);
        this.spike1 = new ModelRenderer(this, 0, 0);
        this.spike1.setPos(-1.5F, -6.0F, -4.5F);
        this.spike1.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotation(spike1, -0.3490658503988659F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 30, 0);
        this.legL.setPos(2.5F, 8.5F, 3.0F);
        this.legL.addBox(-1.0F, 0.0F, 0.0F, 2, 6, 3, 0.0F);
        this.setRotation(legL, -0.6108652381980153F, 0.0F, 0.0F);
        this.legR = new ModelRenderer(this, 20, 0);
        this.legR.setPos(-2.5F, 8.5F, 3.0F);
        this.legR.addBox(-1.0F, 0.0F, 0.0F, 2, 6, 3, 0.0F);
        this.setRotation(legR, -0.6108652381980153F, 0.0F, 0.0F);
        this.spike2 = new ModelRenderer(this, 15, 0);
        this.spike2.setPos(1.5F, -6.0F, -4.5F);
        this.spike2.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotation(spike2, -0.3490658503988659F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 0.0F, -6.0F);
        this.body.addBox(-2.5F, 0.0F, 0.0F, 5, 15, 5, 0.0F);
        this.setRotation(body, 0.6108652381980153F, 0.0F, 0.0F);
        this.armL = new ModelRenderer(this, 48, 0);
        this.armL.setPos(2.0F, 1.0F, -2.0F);
        this.armL.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotation(armL, -0.2617993877991494F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 20, 9);
        this.head.setPos(0.0F, 0.0F, -1.5F);
        this.head.addBox(-3.0F, -6.0F, -6.5F, 6, 6, 6, 0.0F);
        this.legRL = new ModelRenderer(this, 54, 10);
        this.legRL.setPos(0.1F, 5.0F, 0.1F);
        this.legRL.addBox(-1.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotation(legRL, 0.6108652381980153F, 0.0F, 0.0F);
        this.legL.addChild(this.legLL);
        this.head.addChild(this.spike1);
        this.head.addChild(this.spike2);
        this.legR.addChild(this.legRL);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                this.armR,
                this.legL,
                this.legR,
                this.body,
                this.armL,
                this.head
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

        this.armR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + -0.2617993877991494F;
        this.armL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + -0.2617993877991494F;

        this.legL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount + -0.6108652381980153F;
        this.legR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount + -0.6108652381980153F;
    }
}
