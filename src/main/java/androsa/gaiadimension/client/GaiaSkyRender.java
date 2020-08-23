package androsa.gaiadimension.client;

import androsa.gaiadimension.biomes.BaseGaiaBiome;
import androsa.gaiadimension.registry.GaiaSkyColors;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.SkyRenderHandler;

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
public class GaiaSkyRender implements SkyRenderHandler {

    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("minecraft:textures/environment/sun.png");
    private VertexBuffer starVBO;
    private final VertexFormat vertexBufferFormat = DefaultVertexFormats.POSITION;

    public GaiaSkyRender() {
        generateStars();
    }

    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrix, ClientWorld world, Minecraft mc) {
        WorldRenderer renderer = mc.worldRenderer;

        RenderSystem.disableTexture();
        Vec3d skycol = this.getSkyColor(world, mc);
        float sRed = (float) skycol.x;
        float sGreen = (float) skycol.y;
        float sBlue = (float) skycol.z;
        FogRenderer.setFogBlack();
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.depthMask(false);
        RenderSystem.enableFog();
        RenderSystem.color3f(sRed, sGreen, sBlue);
        renderer.skyVBO.bindBuffer();
        this.vertexBufferFormat.startDrawing(0L);
        renderer.skyVBO.draw(matrix.peek().getModel(), 7);
        VertexBuffer.unbindBuffer();
        this.vertexBufferFormat.endDrawing();
        RenderSystem.disableFog();
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float[] setcol = world.dimension.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);
        if (setcol != null) {
            RenderSystem.disableTexture();
            RenderSystem.shadeModel(7425);
            matrix.push();
            matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90.0F));
            float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(f3));
            matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90.0F));
            float ssRed = setcol[0];
            float ssGreen = setcol[1];
            float ssBlue = setcol[2];
            Matrix4f matrix4f = matrix.peek().getModel();
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(ssRed, ssGreen, ssBlue, setcol[3]).endVertex();

            for (int j = 0; j <= 16; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = MathHelper.sin(f7);
                float f9 = MathHelper.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * setcol[3]).color(setcol[0], setcol[1], setcol[2], 0.0F).endVertex();
            }

            bufferbuilder.finishDrawing();
            WorldVertexBufferUploader.draw(bufferbuilder);
            matrix.pop();
            RenderSystem.shadeModel(7424);
        }

        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        matrix.push();
//      float f11 = 1.0F - this.world.getRainStrength(p_228424_2_);
//      RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
        matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
        matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(world.getCelestialAngle(partialTicks) * 360.0F));
        Matrix4f matrix4f1 = matrix.peek().getModel();

        //Sun
        float f12 = 30.0F;
        renderer.textureManager.bindTexture(SUN_TEXTURES);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).texture(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).texture(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).texture(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).texture(0.0F, 1.0F).endVertex();
        bufferbuilder.finishDrawing();
        WorldVertexBufferUploader.draw(bufferbuilder);

        RenderSystem.disableTexture();
        float f10 = this.getStarBrightness(world, partialTicks);
        if (f10 > 0.0F) {
            RenderSystem.color4f(f10, f10, f10, f10);
            this.starVBO.bindBuffer();
            this.vertexBufferFormat.startDrawing(0L);
            this.starVBO.draw(matrix.peek().getModel(), 7);
            VertexBuffer.unbindBuffer();
            this.vertexBufferFormat.endDrawing();
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableFog();
        matrix.pop();
        RenderSystem.disableTexture();
        RenderSystem.color3f(0.0F, 0.0F, 0.0F);
        double d0 = mc.player.getEyePosition(partialTicks).y - world.getSkyDarknessHeight();
        if (d0 < 0.0D) {
            matrix.push();
            matrix.translate(0.0D, 12.0D, 0.0D);
            renderer.sky2VBO.bindBuffer();
            this.vertexBufferFormat.startDrawing(0L);
            renderer.sky2VBO.draw(matrix.peek().getModel(), 7);
            VertexBuffer.unbindBuffer();
            this.vertexBufferFormat.endDrawing();
            matrix.pop();
        }

        RenderSystem.color3f(sRed * 0.2F + 0.04F, sGreen * 0.2F + 0.04F, sBlue * 0.6F + 0.1F);
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.disableFog();
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private void generateStars() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        if (this.starVBO != null) {
            this.starVBO.close();
        }

        this.starVBO = new VertexBuffer(this.vertexBufferFormat);

        Random random = new Random(10842L);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);

        //renderStars
        for(int i = 0; i < 1500; ++i) {
            double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d4 < 1.0D && d4 > 0.01D) {
                d4 = 1.0D / Math.sqrt(d4);
                d0 = d0 * d4;
                d1 = d1 * d4;
                d2 = d2 * d4;
                double d5 = d0 * 100.0D;
                double d6 = d1 * 100.0D;
                double d7 = d2 * 100.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
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
                    bufferbuilder.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }

        bufferbuilder.finishDrawing();
        this.starVBO.upload(bufferbuilder);
    }

    @OnlyIn(Dist.CLIENT)
    private double[] currentSkyColor;
    @OnlyIn(Dist.CLIENT)
    private short[] targetSkyColor;


    @OnlyIn(Dist.CLIENT)
    public Vec3d getSkyColor(ClientWorld world, Minecraft mc) {
        PlayerEntity player = mc.player;
        Biome biome = world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ()));
        targetSkyColor = ModGaiaConfig.skyColors.get().getSkyColorRGB();

        if (ModGaiaConfig.enableSkyFog.get())
            if (biome instanceof BaseGaiaBiome)
                targetSkyColor = ((BaseGaiaBiome) biome).getSkyRGB();
        else
            targetSkyColor = ModGaiaConfig.skyColors.get().getSkyColorRGB();

        if (currentSkyColor == null) {
            currentSkyColor = new double[3];
            for (int a = 0; a < 3; a++)
                currentSkyColor[a] = targetSkyColor[a];
        }

        for (int a = 0; a < 3; a++)
            if (currentSkyColor[a] != targetSkyColor[a])
                if (currentSkyColor[a] < targetSkyColor[a]) {
                    currentSkyColor[a] += 2D;
                    if (currentSkyColor[a] > targetSkyColor[a])
                        currentSkyColor[a] = targetSkyColor[a];
                } else if (currentSkyColor[a] > targetSkyColor[a]) {
                    currentSkyColor[a] -= 2D;
                    if (currentSkyColor[a] < targetSkyColor[a])
                        currentSkyColor[a] = targetSkyColor[a];
                }

        return new Vec3d(currentSkyColor[0] / 255D, currentSkyColor[1] / 255D, currentSkyColor[2] / 255D);
    }

    @OnlyIn(Dist.CLIENT)
    public float getStarBrightness(ClientWorld world, float par1) {
        PlayerEntity player = Minecraft.getInstance().player;
        Biome biome = world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ()));

        if (biome instanceof BaseGaiaBiome) {
            if ((ModGaiaConfig.skyColors.get() == GaiaSkyColors.PURPLE_AGATE && !ModGaiaConfig.enableSkyFog.get()) || ((BaseGaiaBiome)biome).skyColor == GaiaSkyColors.PURPLE_AGATE) {
                return 0.5F;
            }
        }
        return world.func_228330_j_(par1);
    }
}
