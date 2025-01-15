package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.helpers.GaiaConfig;
import com.mojang.blaze3d.buffers.BufferUsage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

import java.util.Optional;

/**
 * Custom sky render for Gaia:
 * Allowing for manipulation of Star brightness based on config values or biome.
 * Removes the moon as it is not required.
 * TODO: Can we add our own sky effects to Smoldering Bog, Shining Grove, Volcanic Lands, Goldstone Lands, and Static Wasteland? Maybe even the whole of Gaia?
 * TODO: Also fix sky colour blending here?
 * TODO: Could we make our own Sun?
 * TODO: I've decided Corrupt biomes need their own sun as well
 * If possible, add extra renders for other biomes.
 */
public class GaiaSkyRender {

    private static final ResourceLocation SUN_TEXTURES = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");
    private final VertexBuffer starVBO = this.generateStars();

    public GaiaSkyRender() {
    }

    public boolean render(float partialTicks, ClientLevel world, Camera camera, Matrix4f matrix, Runnable fog) {
        Minecraft minecraft = Minecraft.getInstance();
        LevelRenderer renderer = minecraft.levelRenderer;

        fog.run();
        RenderStateShard.MAIN_TARGET.setupRenderState();
        PoseStack stack = new PoseStack();
        Tesselator tesselator = Tesselator.getInstance();
        float star = getStarBrightness(world, partialTicks);
        int skycol = world.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), partialTicks);
        float red = ARGB.redFloat(skycol);
        float grn = ARGB.greenFloat(skycol);
        float blu = ARGB.blueFloat(skycol);

        //renderSkyDisc
        RenderSystem.depthMask(false);
        RenderSystem.setShader(CoreShaders.POSITION);
        RenderSystem.setShaderColor(red, grn, blu, 1.0F);
        renderer.skyRenderer.topSkyBuffer.bind();
        renderer.skyRenderer.topSkyBuffer.drawWithShader(RenderSystem.getModelViewMatrix(), RenderSystem.getProjectionMatrix(), RenderSystem.getShader());
        VertexBuffer.unbind();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.depthMask(true);
        //

        //renderSunMoonAndStars (without the Moon)
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        stack.mulPose(Axis.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));

        ///renderSun
        float f12 = 30.0F;
        BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        Matrix4f matrix4f1 = stack.last().pose();
        RenderSystem.depthMask(false);
        RenderType.OVERLAY_TRANSPARENCY.setupRenderState();
        RenderSystem.setShader(CoreShaders.POSITION_TEX);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F /*rain level*/);
        RenderSystem.setShaderTexture(0, SUN_TEXTURES);
        RenderSystem.enableBlend();
        bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
        bufferbuilder.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
        bufferbuilder.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F);
        bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F);
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        ///

        ///renderStars, kind of
        if (star > 0.0F) {
            Matrix4fStack matrixstack = RenderSystem.getModelViewStack();
            matrixstack.pushMatrix();
            matrixstack.mul(stack.last().pose());
            RenderSystem.depthMask(false);
            RenderType.OVERLAY_TRANSPARENCY.setupRenderState();
            RenderSystem.setShader(CoreShaders.POSITION);
            RenderSystem.setShaderColor(star, star, star, star);
            RenderSystem.enableBlend();
            RenderSystem.setShaderFog(FogParameters.NO_FOG);
            starVBO.bind();
            starVBO.drawWithShader(matrixstack, RenderSystem.getProjectionMatrix(), RenderSystem.getShader());
            VertexBuffer.unbind();
            fog.run();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(true);
            matrixstack.popMatrix();
        }
        ///

        stack.popPose();
        //

        //renderDarkDisc
        if (camera.getEntity().getEyePosition(partialTicks).y - world.getLevelData().getHorizonHeight(world) < 0.0D) {
            RenderSystem.depthMask(false);
            RenderSystem.setShader(CoreShaders.POSITION);
            RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
            stack.pushPose();
            stack.translate(0.0D, 12.0D, 0.0D);
            renderer.skyRenderer.bottomSkyBuffer.bind();
            renderer.skyRenderer.bottomSkyBuffer.drawWithShader(RenderSystem.getModelViewMatrix(), RenderSystem.getProjectionMatrix(), RenderSystem.getShader());
            VertexBuffer.unbind();
            stack.popPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.depthMask(true);
        }
        //

        return true;
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private VertexBuffer generateStars() {
        VertexBuffer vertexBuffer = new VertexBuffer(BufferUsage.STATIC_WRITE);
        vertexBuffer.bind();

        //renderStars
        RandomSource random = RandomSource.create(10842L);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        for(int i = 0; i < 1500; ++i) {
            float x = (random.nextFloat() * 2.0F - 1.0F);
            float y = (random.nextFloat() * 2.0F - 1.0F);
            float z = (random.nextFloat() * 2.0F - 1.0F);
            float d3 = (0.15F + random.nextFloat() * 0.1F);
            float area = x * x + y * y + z * z;
            if (area < 1.0F && area > 0.01F) {
                area = (float) (1.0F / Math.sqrt(area));
                x = x * area;
                y = y * area;
                z = z * area;
                float xPos = x * 100.0F;
                float yPos = y * 100.0F;
                float zPos = z * 100.0F;
                float d8 = (float) Math.atan2(x, z);
                float d9 = (float) Math.sin(d8);
                float d10 = (float) Math.cos(d8);
                float d11 = (float) Math.atan2(Math.sqrt(x * x + z * z), y);
                float d12 = (float) Math.sin(d11);
                float d13 = (float) Math.cos(d11);
                float d14 = random.nextFloat() * (float) Math.PI * 2.0F;
                float d15 = (float) Math.sin(d14);
                float d16 = (float) Math.cos(d14);

                for(int j = 0; j < 4; ++j) {
                    float d18 = ((j & 2) - 1) * d3;
                    float d19 = ((j + 1 & 2) - 1) * d3;
                    float d21 = d18 * d16 - d19 * d15;
                    float d22 = d19 * d16 + d18 * d15;
                    float d23 = d21 * d12 + 0.0F * d13;
                    float d24 = 0.0F * d12 - d21 * d13;
                    float d25 = d24 * d9 - d22 * d10;
                    float d26 = d22 * d9 + d24 * d10;
                    bufferbuilder.addVertex(xPos + d25, yPos + d23, zPos + d26);
                }
            }
        }

        vertexBuffer.upload(bufferbuilder.buildOrThrow());
        VertexBuffer.unbind();
        return vertexBuffer;
    }

    public static float getStarBrightness(ClientLevel world, float par1) {
        Player player = Minecraft.getInstance().player;
        Optional<ResourceKey<Biome>> biome = world.getBiome(player.blockPosition()).unwrapKey();

        return biome.filter(GaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElseGet(() -> world.getStarBrightness(par1));
    }
}
