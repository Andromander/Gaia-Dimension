package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.helpers.GaiaConfig;
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
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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
    private static VertexBuffer starVBO;

    public GaiaSkyRender() {
        generateStars();
    }

    public static boolean render(float partialTicks, Matrix4f model, ClientLevel world, Camera camera, Matrix4f matrix, Runnable fog) {
        Minecraft minecraft = Minecraft.getInstance();
        LevelRenderer renderer = minecraft.levelRenderer;

        fog.run();
        FogType fogType = camera.getFluidInCamera();
        if (fogType != FogType.POWDER_SNOW && fogType != FogType.LAVA && !renderer.doesMobEffectBlockSky(camera)) {
            PoseStack stack = new PoseStack();
            stack.mulPose(model);
            Vec3 skycol = world.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), partialTicks);
            float sRed = (float) skycol.x;
            float sGreen = (float) skycol.y;
            float sBlue = (float) skycol.z;
            FogRenderer.levelFogColor();
            Tesselator tesselator = Tesselator.getInstance();
            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(sRed, sGreen, sBlue, 1.0F);
            ShaderInstance instance = RenderSystem.getShader();
            renderer.skyBuffer.bind();
            renderer.skyBuffer.drawWithShader(stack.last().pose(), matrix, instance);
            VertexBuffer.unbind();
            RenderSystem.enableBlend();
            float[] setcol = world.effects().getSunriseColor(world.getTimeOfDay(partialTicks), partialTicks);

            if (setcol != null) {
                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                stack.pushPose();
                stack.mulPose(Axis.XP.rotationDegrees(90.0F));
                float f3 = Mth.sin(world.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
                stack.mulPose(Axis.ZP.rotationDegrees(f3));
                stack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                float ssRed = setcol[0];
                float ssGreen = setcol[1];
                float ssBlue = setcol[2];
                Matrix4f matrix4f = stack.last().pose();
                BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                bufferbuilder.addVertex(matrix4f, 0.0F, 100.0F, 0.0F).setColor(ssRed, ssGreen, ssBlue, setcol[3]);

                for (int j = 0; j <= 16; ++j) {
                    float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                    float f8 = Mth.sin(f7);
                    float f9 = Mth.cos(f7);
                    bufferbuilder.addVertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * setcol[3]).setColor(setcol[0], setcol[1], setcol[2], 0.0F);
                }

                BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
                stack.popPose();
            }
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            stack.pushPose();
//          float f11 = 1.0F - this.world.getRainStrength(p_228424_2_);
//          RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
            stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
            stack.mulPose(Axis.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));
            Matrix4f matrix4f1 = stack.last().pose();

            //Sun
            float f12 = 30.0F;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, SUN_TEXTURES);
            BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
            bufferbuilder.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
            bufferbuilder.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F);
            bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F);
            BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());

            //Stars
            float f10 = getStarBrightness(world, partialTicks);

            if (f10 > 0.0F) {
                RenderSystem.setShaderColor(f10, f10, f10, f10);
                FogRenderer.setupNoFog();
                starVBO.bind();
                starVBO.drawWithShader(stack.last().pose(), matrix, GameRenderer.getPositionShader());
                VertexBuffer.unbind();
                fog.run();
            }

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            stack.popPose();
            RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
            double y = camera.getEntity().getEyePosition(partialTicks).y - world.getLevelData().getHorizonHeight(world);

            if (y < 0.0D) {
                stack.pushPose();
                stack.translate(0.0D, 12.0D, 0.0D);
                renderer.darkBuffer.bind();
                renderer.darkBuffer.drawWithShader(stack.last().pose(), matrix, instance);
                VertexBuffer.unbind();
                stack.popPose();
            }

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.depthMask(true);
        }
        return true;
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private void generateStars() {
        Tesselator tessellator = Tesselator.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        if (this.starVBO != null) {
            this.starVBO.close();
        }

        this.starVBO = new VertexBuffer(VertexBuffer.Usage.STATIC);
        this.starVBO.bind();

        //renderStars
        RandomSource random = RandomSource.create(10842L);
        BufferBuilder bufferbuilder = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        for(int i = 0; i < 1500; ++i) {
            float x = random.nextFloat() * 2.0F - 1.0F;
            float y = random.nextFloat() * 2.0F - 1.0F;
            float z = random.nextFloat() * 2.0F - 1.0F;
            float d3 = 0.15F + random.nextFloat() * 0.1F;
            float area = Mth.lengthSquared(x, y, z);
            if (!(area <= 0.010000001F) && !(area >= 1.0F)) {
                Vector3f normal = new Vector3f(x, y, z).normalize(100.0F);
                float zRot = (float) (random.nextDouble() * (float) Math.PI * 2.0F);
                Quaternionf rotation = new Quaternionf().rotateTo(new Vector3f(0.0F, 0.0F, 1.0F), normal).rotateZ(zRot);
                bufferbuilder.addVertex(normal.add(new Vector3f(d3, -d3, 0.0F).rotate(rotation)));
                bufferbuilder.addVertex(normal.add(new Vector3f(d3, d3, 0.0F).rotate(rotation)));
                bufferbuilder.addVertex(normal.add(new Vector3f(-d3, d3, 0.0F).rotate(rotation)));
                bufferbuilder.addVertex(normal.add(new Vector3f(-d3, -d3, 0.0F).rotate(rotation)));
            }
        }
        this.starVBO.upload(bufferbuilder.buildOrThrow());
        VertexBuffer.unbind();
    }

    public static float getStarBrightness(ClientLevel world, float par1) {
        Player player = Minecraft.getInstance().player;
        Optional<ResourceKey<Biome>> biome = world.getBiome(new BlockPos(player.blockPosition())).unwrapKey();

        return biome.filter(GaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElseGet(() -> world.getStarBrightness(par1));
    }
}
