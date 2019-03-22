package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDMineralArenthis;
import androsa.gaiadimension.renderer.EntityRenderMineralArenthis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL14;

public class LayerMineralArenthisParts implements LayerRenderer<GDMineralArenthis> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "mineralarenthis_glow.png");
    private final EntityRenderMineralArenthis arentisRender;

    public LayerMineralArenthisParts(EntityRenderMineralArenthis arentisRenderIn) {
        this.arentisRender = arentisRenderIn;
    }

    @Override
    public void doRenderLayer(GDMineralArenthis entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.arentisRender.bindTexture(textureLoc);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.arentisRender.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.glBlendEquation(GL14.GL_FUNC_ADD);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.arentisRender.setLightmap(entitylivingbaseIn);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
