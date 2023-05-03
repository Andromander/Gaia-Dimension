package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.ModGaiaConfig;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

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
public class GaiaSkyRender {

    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("minecraft:textures/environment/sun.png");
    private static VertexBuffer starVBO;
    private final VertexFormat vertexBufferFormat = DefaultVertexFormat.POSITION;

    public GaiaSkyRender() {
        generateStars();
    }

    public static boolean render(int ticks, float partialTicks, PoseStack matrix, ClientLevel world, Camera camera, Runnable fog) {
        LevelRenderer renderer = Minecraft.getInstance().levelRenderer;

        fog.run();
        Vec3 skycol = world.getSkyColor(camera.getPosition(), partialTicks);
        float sRed = (float) skycol.x;
        float sGreen = (float) skycol.y;
        float sBlue = (float) skycol.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(sRed, sGreen, sBlue, 1.0F);
        ShaderInstance instance = RenderSystem.getShader();
        renderer.skyBuffer.bind();
        renderer.skyBuffer.drawWithShader(matrix.last().pose(), RenderSystem.getProjectionMatrix(), instance);
        VertexBuffer.unbind();
        RenderSystem.enableBlend();
        float[] setcol = world.effects().getSunriseColor(world.getTimeOfDay(partialTicks), partialTicks);
        if (setcol != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            matrix.pushPose();
            matrix.mulPose(Axis.XP.rotationDegrees(90.0F));
            float f3 = Mth.sin(world.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrix.mulPose(Axis.ZP.rotationDegrees(f3));
            matrix.mulPose(Axis.ZP.rotationDegrees(90.0F));
            float ssRed = setcol[0];
            float ssGreen = setcol[1];
            float ssBlue = setcol[2];
            Matrix4f matrix4f = matrix.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(ssRed, ssGreen, ssBlue, setcol[3]).endVertex();

            for (int j = 0; j <= 16; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = Mth.sin(f7);
                float f9 = Mth.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * setcol[3]).color(setcol[0], setcol[1], setcol[2], 0.0F).endVertex();
            }

            BufferUploader.drawWithShader(bufferbuilder.end());
            matrix.popPose();
        }

        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        matrix.pushPose();
//      float f11 = 1.0F - this.world.getRainStrength(p_228424_2_);
//      RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
        matrix.mulPose(Axis.YP.rotationDegrees(-90.0F));
        matrix.mulPose(Axis.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));
        Matrix4f matrix4f1 = matrix.last().pose();

        //Sun
        float f12 = 30.0F;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SUN_TEXTURES);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());

        float f10 = getStarBrightness(world, partialTicks);
        if (f10 > 0.0F) {
            RenderSystem.setShaderColor(f10, f10, f10, f10);
            FogRenderer.setupNoFog();
            starVBO.bind();
            starVBO.drawWithShader(matrix.last().pose(), RenderSystem.getProjectionMatrix(), GameRenderer.getPositionShader());
            VertexBuffer.unbind();
            fog.run();
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        matrix.popPose();
        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
        double y = Minecraft.getInstance().player.getEyePosition(partialTicks).y - world.getLevelData().getHorizonHeight(world);
        if (y < 0.0D) {
            matrix.pushPose();
            matrix.translate(0.0D, 12.0D, 0.0D);
            renderer.darkBuffer.bind();
            renderer.darkBuffer.drawWithShader(matrix.last().pose(), RenderSystem.getProjectionMatrix(), instance);
            VertexBuffer.unbind();
            matrix.popPose();
        }

        RenderSystem.setShaderColor(sRed * 0.2F + 0.04F, sGreen * 0.2F + 0.04F, sBlue * 0.6F + 0.1F, 1.0F);
        RenderSystem.depthMask(true);
        return true;
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private void generateStars() {
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        if (this.starVBO != null) {
            this.starVBO.close();
        }

        this.starVBO = new VertexBuffer();

        //renderStars
        RandomSource random = RandomSource.create(10842L);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
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

        this.starVBO.bind();
        this.starVBO.upload(bufferbuilder.end());
        VertexBuffer.unbind();
    }

    @OnlyIn(Dist.CLIENT)
    public static float getStarBrightness(ClientLevel world, float par1) {
        Player player = Minecraft.getInstance().player;
        Optional<ResourceKey<Biome>> biome = world.getBiome(new BlockPos(player.blockPosition())).unwrapKey();

        return biome.filter(ModGaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElseGet(() -> world.getStarBrightness(par1));
    }
}
