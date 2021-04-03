package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.ModGaiaConfig;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

import java.util.Optional;
import java.util.Random;

/**
 * Custom sky render for Gaia:
 * Allowing for manipulation of Star brightness based on config values or biome.
 * Removes the moon as it is not required.
 * TODO: Can we add our own sky effects to Smoldering Bog, Shining Grove, Volcanic Lands, Goldstone Lands, and Static Wasteland? Maybe even the whole of Gaia?
 * TODO: Also fix sky colour blending here?
 * TODO: Could we make our own Sun?
 * If possible, add extra renders for other biomes.
 */
public class GaiaSkyRender implements ISkyRenderHandler {

    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("minecraft:textures/environment/sun.png");
    private VertexBuffer starVBO;
    private final VertexFormat vertexBufferFormat = DefaultVertexFormats.POSITION;

    public GaiaSkyRender() {
        generateStars();
    }

    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrix, ClientWorld world, Minecraft mc) {
        WorldRenderer renderer = mc.levelRenderer;

        RenderSystem.disableTexture();
        Vector3d skycol = world.getSkyColor(mc.gameRenderer.getMainCamera().getBlockPosition(), partialTicks);
        float sRed = (float) skycol.x;
        float sGreen = (float) skycol.y;
        float sBlue = (float) skycol.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        RenderSystem.enableFog();
        RenderSystem.color3f(sRed, sGreen, sBlue);
        renderer.skyBuffer.bind();
        this.vertexBufferFormat.setupBufferState(0L);
        renderer.skyBuffer.draw(matrix.last().pose(), 7);
        VertexBuffer.unbind();
        this.vertexBufferFormat.clearBufferState();
        RenderSystem.disableFog();
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float[] setcol = world.effects().getSunriseColor(world.getTimeOfDay(partialTicks), partialTicks);
        if (setcol != null) {
            RenderSystem.disableTexture();
            RenderSystem.shadeModel(7425);
            matrix.pushPose();
            matrix.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            float f3 = MathHelper.sin(world.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrix.mulPose(Vector3f.ZP.rotationDegrees(f3));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            float ssRed = setcol[0];
            float ssGreen = setcol[1];
            float ssBlue = setcol[2];
            Matrix4f matrix4f = matrix.last().pose();
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(ssRed, ssGreen, ssBlue, setcol[3]).endVertex();

            for (int j = 0; j <= 16; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = MathHelper.sin(f7);
                float f9 = MathHelper.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * setcol[3]).color(setcol[0], setcol[1], setcol[2], 0.0F).endVertex();
            }

            bufferbuilder.end();
            WorldVertexBufferUploader.end(bufferbuilder);
            matrix.popPose();
            RenderSystem.shadeModel(7424);
        }

        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        matrix.pushPose();
//      float f11 = 1.0F - this.world.getRainStrength(p_228424_2_);
//      RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
        matrix.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrix.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));
        Matrix4f matrix4f1 = matrix.last().pose();

        //Sun
        float f12 = 30.0F;
        renderer.textureManager.bind(SUN_TEXTURES);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);

        RenderSystem.disableTexture();
        float f10 = this.getStarBrightness(world, partialTicks);
        if (f10 > 0.0F) {
            RenderSystem.color4f(f10, f10, f10, f10);
            this.starVBO.bind();
            this.vertexBufferFormat.setupBufferState(0L);
            this.starVBO.draw(matrix.last().pose(), 7);
            VertexBuffer.unbind();
            this.vertexBufferFormat.clearBufferState();
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableFog();
        matrix.popPose();
        RenderSystem.disableTexture();
        RenderSystem.color3f(0.0F, 0.0F, 0.0F);
        double y = mc.player.getEyePosition(partialTicks).y - world.getLevelData().getHorizonHeight();
        if (y < 0.0D) {
            matrix.pushPose();
            matrix.translate(0.0D, 12.0D, 0.0D);
            renderer.darkBuffer.bind();
            this.vertexBufferFormat.setupBufferState(0L);
            renderer.darkBuffer.draw(matrix.last().pose(), 7);
            VertexBuffer.unbind();
            this.vertexBufferFormat.clearBufferState();
            matrix.popPose();
        }

        RenderSystem.color3f(sRed * 0.2F + 0.04F, sGreen * 0.2F + 0.04F, sBlue * 0.6F + 0.1F);
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.disableFog();
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private void generateStars() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.starVBO != null) {
            this.starVBO.close();
        }

        this.starVBO = new VertexBuffer(this.vertexBufferFormat);

        Random random = new Random(10842L);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);

        //renderStars
        for(int i = 0; i < 1500; ++i) {
            double x = (double)(random.nextFloat() * 2.0F - 1.0F);
            double y = (double)(random.nextFloat() * 2.0F - 1.0F);
            double z = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
            double area = x * x + y * y + z * z;
            if (area < 1.0D && area > 0.01D) {
                area = 1.0D / Math.sqrt(area);
                x = x * area;
                y = y * area;
                z = z * area;
                double xPos = x * 100.0D;
                double yPos = y * 100.0D;
                double zPos = z * 100.0D;
                double d8 = Math.atan2(x, z);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(x * x + z * z), y);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for(int j = 0; j < 4; ++j) {
                    double d18 = (double)((j & 2) - 1) * d3;
                    double d19 = (double)((j + 1 & 2) - 1) * d3;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    bufferbuilder.vertex(xPos + d25, yPos + d23, zPos + d26).endVertex();
                }
            }
        }

        bufferbuilder.end();
        this.starVBO.upload(bufferbuilder);
    }

    @OnlyIn(Dist.CLIENT)
    public float getStarBrightness(ClientWorld world, float par1) {
        PlayerEntity player = Minecraft.getInstance().player;
        Optional<RegistryKey<Biome>> biome = world.getBiomeName(new BlockPos(player.getX(), player.getY(), player.getZ()));

        return biome.filter(ModGaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElseGet(() -> world.getStarBrightness(par1));
    }
}
