package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MineralArenthisEntity;
import androsa.gaiadimension.model.MineralArenthisModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MineralArenthisPartsLayer<T extends MineralArenthisEntity, M extends MineralArenthisModel<T>> extends LayerRenderer<T, M> {
    private static final RenderType textureLoc = RenderType.getEyes(new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "mineralarenthis_glow.png"));

    public MineralArenthisPartsLayer(IEntityRenderer<T, M> arentisRenderIn) {
        super(arentisRenderIn);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        IVertexBuilder builder = buffer.getBuffer(textureLoc);
        this.getEntityModel().render(matrixStack, builder, 15728640, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }

//    @Override
//    public void render(MineralArenthisEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//        this.bindTexture(textureLoc);
//        GlStateManager.enableBlend();
//        GlStateManager.disableAlphaTest();
//        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
//        GlStateManager.disableLighting();
//        GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
//        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
//        GlStateManager.enableLighting();
//        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//        Minecraft.getInstance().gameRenderer.setupFogColor(true);
//        this.getSegmentedModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//        Minecraft.getInstance().gameRenderer.setupFogColor(false);
//        this.func_215334_a(entitylivingbaseIn);
//        GlStateManager.depthMask(true);
//        GlStateManager.disableBlend();
//        GlStateManager.enableAlphaTest();
//    }

//    @Override
//    public boolean shouldCombineTextures() {
//        return false;
//    }
}
