package androsa.gaiadimension.model;

import androsa.gaiadimension.entity.SpellElementEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelSpellElement - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class SpellElementModel<T extends SpellElementEntity> extends SegmentedModel<T> {
    public ModelRenderer head;
    public ModelRenderer horn1R;
    public ModelRenderer horn2R;
    public ModelRenderer horn1L;
    public ModelRenderer horn2L;
    public ModelRenderer chin;
    public ModelRenderer bodyA;
    public ModelRenderer bodyB;
    public ModelRenderer core;
    public ModelRenderer scytheR;
    public ModelRenderer elbowR;
    public ModelRenderer clawR;
    public ModelRenderer scytheL;
    public ModelRenderer elbowL;
    public ModelRenderer clawL;
    public ModelRenderer tip;
    public ModelRenderer tipR;
    public ModelRenderer tipL;

    public SpellElementModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        //head
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.5F, -5.0F, -1.5F, 5, 5, 3, 0.0F);
        //right horn 1
        this.horn1R = new ModelRenderer(this, 52, 3);
        this.horn1R.setPos(-0.5F, -4.6F, 0.0F);
        this.horn1R.addBox(-3.0F, 0.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(horn1R, 0.0F, 0.0F, 0.4553564018453205F);
        //right horn 2
        this.horn2R = new ModelRenderer(this, 46, 5);
        this.horn2R.setPos(-1.6F, 1.5F, 0.1F);
        this.horn2R.addBox(0.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(horn2R, 0.0F, 0.0F, 2.367539130330308F);
        //left horn 1
        this.horn1L = new ModelRenderer(this, 16, 5);
        this.horn1L.setPos(0.5F, -4.6F, 0.0F);
        this.horn1L.addBox(0.0F, 0.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(horn1L, 0.0F, 0.0F, -0.4553564018453205F);
        //left horn 2
        this.horn2L = new ModelRenderer(this, 0, 8);
        this.horn2L.setPos(1.6F, 1.5F, 0.1F);
        this.horn2L.addBox(-2.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(horn2L, 0.0F, 0.0F, -2.367539130330308F);
        //chin
        this.chin = new ModelRenderer(this, 52, 0);
        this.chin.setPos(0.0F, 0.0F, -1.5F);
        this.chin.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        //top body
        this.bodyA = new ModelRenderer(this, 16, 0);
        this.bodyA.setPos(0.0F, 8.4F, 0.0F);
        this.bodyA.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
        //bottom body
        this.bodyB = new ModelRenderer(this, 6, 10);
        this.bodyB.setPos(0.0F, 0.0F, 0.0F);
        this.bodyB.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 1, 0.0F);
        //core
        this.core = new ModelRenderer(this, 50, 8);
        this.core.setPos(0.0F, -1.5F, -0.1F);
        this.core.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        //right scythe
        this.scytheR = new ModelRenderer(this, 26, 0);
        this.scytheR.setPos(-8.0F, 3.0F, 0.0F);
        this.scytheR.addBox(-3.0F, 0.0F, -1.0F, 3, 11, 2, 0.0F);
        this.setRotation(scytheR, 0.0F, 0.0F, -0.08726646259971647F);
        //right elbow
        this.elbowR = new ModelRenderer(this, 46, 10);
        this.elbowR.setPos(-3.0F, 0.0F, 0.0F);
        this.elbowR.addBox(0.0F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        //right claw
        this.clawR = new ModelRenderer(this, 57, 11);
        this.clawR.setPos(-0.7F, 10.3F, 0.0F);
        this.clawR.addBox(-2.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(clawR, 0.0F, 0.0F, -0.36425021489121656F);
        //left scythe
        this.scytheL = new ModelRenderer(this, 36, 0);
        this.scytheL.setPos(8.0F, 3.0F, 0.0F);
        this.scytheL.addBox(0.0F, 0.0F, -1.0F, 3, 11, 2, 0.0F);
        this.setRotation(scytheL, 0.0F, 0.0F, 0.08726646259971647F);
        //left elbow
        this.elbowL = new ModelRenderer(this, 50, 12);
        this.elbowL.setPos(2.0F, 0.0F, 0.0F);
        this.elbowL.addBox(0.0F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        //left claw
        this.clawL = new ModelRenderer(this, 0, 13);
        this.clawL.setPos(0.7F, 10.3F, 0.0F);
        this.clawL.addBox(0.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotation(clawL, 0.0F, 0.0F, 0.36425021489121656F);
        //center tip
        this.tip = new ModelRenderer(this, 46, 0);
        this.tip.setPos(0.0F, 16.0F, 0.0F);
        this.tip.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
        //right tip part
        this.tipR = new ModelRenderer(this, 6, 8);
        this.tipR.setPos(-0.5F, 0.0F, 0.0F);
        this.tipR.addBox(-2.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotation(tipR, 0.0F, 0.0F, 0.4363323129985824F);
        //left tip part
        this.tipL = new ModelRenderer(this, 56, 8);
        this.tipL.setPos(0.5F, 0.0F, 0.0F);
        this.tipL.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotation(tipL, 0.0F, 0.0F, -0.4363323129985824F);

        //give the parts their kiddies
        this.head.addChild(this.chin);
        this.head.addChild(this.horn1L);
        this.horn1L.addChild(this.horn2L);
        this.bodyA.addChild(this.bodyB);
        this.scytheR.addChild(this.clawR);
        this.bodyA.addChild(this.core);
        this.tip.addChild(this.tipR);
        this.head.addChild(this.horn1R);
        this.horn1R.addChild(this.horn2R);
        this.scytheL.addChild(this.elbowL);
        this.tip.addChild(this.tipL);
        this.scytheL.addChild(this.clawL);
        this.scytheR.addChild(this.elbowR);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(
                this.scytheR,
                this.scytheL,
                this.bodyA,
                this.tip,
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

        this.scytheR.zRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount * 0.5F;
        this.scytheL.zRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.scytheR.zRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.08726646259971647F;
        this.scytheL.zRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F + -0.08726646259971647F;
    }
}
